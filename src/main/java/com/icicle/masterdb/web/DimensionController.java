package com.icicle.masterdb.web;

import com.icicle.masterdb.authorization.annotation.Authorization;
import com.icicle.masterdb.core.AbstractPageController;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.core.ResultGenerator;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.*;
import com.icicle.masterdb.pojo.vo.*;
import com.icicle.masterdb.service.*;
import com.icicle.masterdb.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @author wangyuling
 */
@Controller
@RequestMapping("dimension")
@Authorization
public class DimensionController extends AbstractPageController {
    @Resource
    private ProductDimensionService productDimensionService;
    @Resource
    private ProductAttributeItemService productAttributeItemService;
    @Resource
    private ViewSelectItemService viewSelectItemService;
    @Resource
    private ProductAttributeService productAttributeService;
    @Resource
    private ProductAttributeDefinedService productAttributeDefinedService;
    @Resource
    private ProductDimensionAttributeService productDimensionAttributeService;
    @Resource
    private ProductService productService;
    @Resource
    private ViewProductDimensionService viewProductDimensionService;
    @Resource
    private ProductDimensionMerchandisingService productDimensionMerchandisingService;
    @Resource
    private ViewMechandItemService viewMechandItemService;
    @Resource
    private ProductDimensionTrainingService productDimensionTrainingService;



    public DimensionController() {
        super("dimension");
    }

    @GetMapping("dimensionattribute")
    public String dimensionAttribute(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("attri", map, request);
    }

    @GetMapping("/featurelist")
    public String featureList(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("featurelist", map, request);
    }

    @GetMapping("addproductattribute")
    public String addProductAttribute(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("addattr", map, request);
    }

    @GetMapping("merchandlist")
    public String merChandList(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("merchandlist", map, request);
    }

    /**
     * 加载所有表名
     */
    @GetMapping("/loadtablename")
    @ResponseBody
    public Result loadTableName() {
        List<String> viewSelectItemList = viewSelectItemService.selectTableName();
        if (viewSelectItemList != null && viewSelectItemList.size() != 0) {
            return ResultGenerator.genSuccessResult(viewSelectItemList);
        } else {
            return ResultGenerator.genFailResult("数据获取错误，请稍后重试！");
        }
    }

    @PostMapping("listattributedefined")
    @ResponseBody
    public Result listAttributeDefined() {
        List<ProductAttributeDefinedVO> productAttributeDefinedVOList;
        try {
            productAttributeDefinedVOList = productAttributeDefinedService.findAllAttributeDefinedVO();
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(productAttributeDefinedVOList);
    }

    @PostMapping("listdimensionattri")
    @ResponseBody
    public Result listDimensionAttri() {
        List<DimensionAttriVO> dimensionAttriVOList;
        try {
            dimensionAttriVOList = productDimensionAttributeService.findAllDimensionAttriVO();
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(dimensionAttriVOList);
    }

    @PostMapping("listattributeitems")
    @ResponseBody
    public Result listAttributeItems() {
        List<ProductDimensionVO> productDimensionVOList;
        try {
            productDimensionVOList = productDimensionService.findAllProductDimensionVO();
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(productDimensionVOList);
    }

    @PostMapping("adddimension")
    @ResponseBody
    public Result addDimension(@RequestBody ProductDimension dimension) {

        String message = checkDimension(dimension, false);
        if (message != null) {
            return ResultGenerator.genFailResult(message);
        }
        dimension.setCreatedBy(SessionManager.getLoginName());
        dimension.setCreationDate(DateUtil.now());
        try {
            int count = productDimensionService.saveDimension(dimension);
            if (count > 0) {
                return ResultGenerator.genSuccessResult(dimension);
            } else {
                //"新增失败，请稍后再试"
                return ResultGenerator.genFailResult(LanguageUtil.getText(1062));
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PutMapping("updatedimention")
    @ResponseBody
    public Result updateDimention(@RequestBody ProductDimension dimension) {
        String message = checkDimension(dimension, true);
        if (message != null) {
            return ResultGenerator.genFailResult(message);
        }
        int ret = productDimensionService.updateDimension(dimension);
        if (ret > 0) {
            return ResultGenerator.genSuccessResult(dimension);
        } else {
            //更新失败，请稍后再试
            return ResultGenerator.genFailResult(LanguageUtil.getText(1063));
        }
    }

    @GetMapping("finddefinedbyId")
    @ResponseBody
    public Result findDefinedById(@RequestParam(value = "id") Integer id) {
        if (id == null) {
            //属性定义id不能为空
            return ResultGenerator.genFailResult(LanguageUtil.getText(1072));
        }
        List<ProductAttributeItem> productAttributeItemList = productAttributeDefinedService.findItemById(id);
        if (!ListUtil.isBlank(productAttributeItemList)) {
            return ResultGenerator.genSuccessResult(productAttributeItemList);
        } else {
            //查询为空
            return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
        }
    }

    //属性新增
    @PostMapping("addattributedefine")
    @ResponseBody
    public Result addAttributeDefine(@RequestBody ProductAttributeDefinedVO productAttributeDefinedVO) {
        String message = checkProductAttribDef(productAttributeDefinedVO, false);
        if (message != null) {
            return ResultGenerator.genFailResult(message);
        }
        try {
            int ret = productAttributeDefinedService.addAttributeDef(productAttributeDefinedVO);
            if (ret > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                //新增失败，请稍后重试
                return ResultGenerator.genFailResult(LanguageUtil.getText(1062));
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("updateattributedefine")
    @ResponseBody
    public Result updateAttributeDefine(@RequestBody ProductAttributeDefinedVO productAttributeDefinedVO) {
        try {
            int ret = productAttributeDefinedService.updateDimensionAttributeDef(productAttributeDefinedVO);
            if (ret > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                //更新失败，请稍后再试
                return ResultGenerator.genFailResult(LanguageUtil.getText(1063));
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }


    }

    @PostMapping("attribconnect")
    @ResponseBody
    public Result attribConnect(@RequestBody ProductDimensionAttriVo productDimensionAttriVo) {
        if (productDimensionAttriVo.getDimensionId() == null) {
            //维度id不能为空,数据获取错误，请稍后重试
            return ResultGenerator.genFailResult(LanguageUtil.getText(1520));
        }
        try {
            int ret = productDimensionAttributeService.connectAttribute(productDimensionAttriVo);
            if (ret > 0) {
                return ResultGenerator.genSuccessResult();
            } else if (ret == -1) {
                return ResultGenerator.genFailResult("此属性无法关联，请刷新页面");
            } else {
                //属性关联失败，请稍后重试
                return ResultGenerator.genFailResult(LanguageUtil.getText(1519));
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("listattributedefvo")
    @ResponseBody
    public Result listAttributeDefVO() {
        List<ViewProductDimension> list;
        try {
            list = viewProductDimensionService.findByDimensionCode("product_dimension_merchandising");
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("listattributeitem")
    @ResponseBody
    public Result listAttributeItem() {
        List<ProductAttributeItemVO> list;
        try {
            list = productAttributeItemService.findAttributeItemVO();
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("listitem")
    @ResponseBody
    public Result listItem() {
        List<ViewMechandItem> list;
        try {
            list = viewMechandItemService.findAll();
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("insertproductattribute")
    @ResponseBody
    public Result insertProductAttribute(@RequestBody List<ProductAttribute> list) {
        if (ListUtil.isBlank(list)) {
            //您没有插入任何属性
            return ResultGenerator.genFailResult(LanguageUtil.getText(1518));
        }
        String productCode = list.get(0).getProductCode();
        try {
            Product pr = productService.selectProduct(productCode);
            if (pr == null) {
                //此产品编码不存在
                return ResultGenerator.genFailResult(LanguageUtil.getText(1403));
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        ProductDimensionMerchandising productDimensionMerchandising = new ProductDimensionMerchandising();
        productDimensionMerchandising.setProductCode(productCode);

        try {
            int count = productAttributeService.editProductAttribute(list, productCode);
            if (count > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                //服务器当前正忙，请稍后再试
                return ResultGenerator.genFailResult(LanguageUtil.getText(1001));
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }

    }


    @GetMapping("getattributebycode")
    @ResponseBody
    public Result getAttributeByCode(@RequestParam("productCode") String productCode) {
        List<ProductAttribute> list;
        try {
            list = productAttributeService.findAttributeByCode(productCode);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("listmerchand")
    @ResponseBody
    public DataTableRecord listMerchand(@RequestParam(value = "sEcho") String sEcho,
                                        @RequestParam(value = "iDisplayStart") Integer pageIndex,
                                        @RequestParam(value = "iDisplayLength") Integer pageSize,
                                        @RequestParam(value = "sSearch") String sSearch) {
        DataTableRecord dataTableRecord = productDimensionMerchandisingService.listMerchandList(sEcho, pageIndex, pageSize, sSearch);
        return dataTableRecord;
    }

    @PostMapping("importmerchandising")
    @ResponseBody
    public Result importMerchandising(@RequestParam("uploadfile") MultipartFile file) {
        int count = 0;

        try {
            if (!ExcelUtil.isExcelFile(file.getInputStream())) {
                //此文件不是excel文件
                return ResultGenerator.genFailResult(LanguageUtil.getText(1060));
            }
            List<ProductAttribute> list = productAttributeService.parseExcel(file.getInputStream());
            ExcelUtil.saveExcelFile(file);
            if (ListUtil.isBlank(list)) {
                //此文档不符合要求，导入失败
                return ResultGenerator.genFailResult("此文档没有可用数据或包含数据已被导入");
            }
            List<ProductDimensionMerchandising> merchandisingList = productAttributeService.transferAttrToMerchandising(list);
            if (!ListUtil.isBlank(merchandisingList)) {
                count += productDimensionMerchandisingService.saveMerchandisingList(merchandisingList, list);
            }
            if (count > 0) {
                return ResultGenerator.genSuccessResult(count);
            } else {
                return ResultGenerator.genFailResult("此文档不符合要求，或者其数据已经被导入");
            }
        } catch (IOException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            //服务器当前正忙，请稍后再试
            return ResultGenerator.genFailResult(LanguageUtil.getText(1001));
        } catch (InvalidFormatException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            //服务器当前正忙，请稍后再试
            return ResultGenerator.genFailResult(LanguageUtil.getText(1060));
        }
    }

    @PostMapping("connectdefid")
    @ResponseBody
    public Result connectDefId() {
        List<String> result = productDimensionAttributeService.getConnectDefId();
        if (result != null) {
            return ResultGenerator.genSuccessResult(result);
        } else {
            //数据获取为空，请稍后重试
            return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
        }
    }


    //产品特点模块
    @PostMapping("/featurelist")
    @ResponseBody
    public DataTableRecord featureList(@RequestParam(value = "sEcho") String sEcho,
                                       @RequestParam(value = "iDisplayStart") Integer pageIndex,
                                       @RequestParam(value = "iDisplayLength") Integer pageSize,
                                       @RequestParam(value = "sSearch") String sSearch,
                                       @RequestParam(value = "productCode") String productCode) {
        DataTableRecord dataTableRecord = productDimensionTrainingService.listFeature(sEcho, pageIndex, pageSize, sSearch, productCode);
        return dataTableRecord;
    }


    /**
     * 验证客户端传过来的维度信息是否合法，更新和添加时候的判断逻辑不相同
     * 此处参数update表示是否为更新操作
     *
     * @param dimension 接收到的客户端才传过来的实体
     * @param update    当前是否执行的是更心操作
     * @return 如果不合法则返回一个字符串，否则返回null
     */
    private String checkDimension(ProductDimension dimension, boolean update) {
        if (dimension == null) {
            //您传了空数据
            return LanguageUtil.getText(1079);
        }
        if (update && dimension.getId() == null) {
            //更新维度必须提供维度id
            return LanguageUtil.getText(1503);
        }
        if (StringUtils.isBlank(dimension.getClassDimensionName().trim())) {
            //维度名称不能为空
            return LanguageUtil.getText(1505);
        }

        Condition condition = new Condition(ProductDimension.class);

        if (update) {
            condition.createCriteria().andNotEqualTo("id", dimension.getId());
            condition.and(condition.createCriteria().andEqualTo("classDimensionCode", dimension.getClassDimensionCode())
                    .orEqualTo("classDimensionName", dimension.getClassDimensionName()));
        } else {
            if (StringUtils.isBlank(dimension.getClassDimensionCode().trim())) {
                //维度编码不能为空
                return LanguageUtil.getText(1504);
            }
            condition.createCriteria().andEqualTo("classDimensionCode", dimension.getClassDimensionCode())
                    .orEqualTo("classDimensionName", dimension.getClassDimensionName());
        }

        try {
            int count = productDimensionService.findCountByCondition(condition);
            if (count > 0) {
                //维度编码和维度名不能和已有的重复，请更换后再尝试
                return LanguageUtil.getText(1506);
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return LanguageUtil.getText(-999);
        }

        return null;
    }

    private String checkProductAttribDef(ProductAttributeDefinedVO productAttributeDefinedVO, boolean update) {
        if (productAttributeDefinedVO == null) {
            //更新失败
            return LanguageUtil.getText(1018);
        }
        if (update && productAttributeDefinedVO.getId() == null) {
            //属性定义Id不能为空
            return LanguageUtil.getText(1072);
        }
        if (StringUtils.isBlank(productAttributeDefinedVO.getDefName())) {
            //属性名称不能为空
            return LanguageUtil.getText(1086);
        }

        Condition condition = new Condition(ProductAttributeDefined.class);
        if (update) {
            condition.createCriteria().andNotEqualTo("id", productAttributeDefinedVO.getId());
            condition.and(condition.createCriteria().andEqualTo("defName", productAttributeDefinedVO.getDefName()).
                    orEqualTo("defCode", productAttributeDefinedVO.getDefCode()));
        } else {
            if (StringUtils.isBlank(productAttributeDefinedVO.getDefCode())) {
                //属性编码不能为空
                return LanguageUtil.getText(1085);
            }
            condition.createCriteria().andEqualTo("defName", productAttributeDefinedVO.getDefName()).
                    orEqualTo("defCode", productAttributeDefinedVO.getDefCode());
        }

        try {
            int count = productAttributeDefinedService.findCountByCondition(condition);
            if (count > 0) {
                //当前编码或者名称已经存在，请您更改后再尝试
                return LanguageUtil.getText(1087);
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return LanguageUtil.getText(-999);
        }
        return null;
    }

}
