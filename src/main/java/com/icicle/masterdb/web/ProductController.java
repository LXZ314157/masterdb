package com.icicle.masterdb.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.authorization.annotation.Authorization;
import com.icicle.masterdb.config.SynConfigEntity;
import com.icicle.masterdb.core.AbstractPageController;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.core.ResultGenerator;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.*;
import com.icicle.masterdb.pojo.bo.SyncElastic;
import com.icicle.masterdb.pojo.bo.SyncSKU;
import com.icicle.masterdb.pojo.vo.*;
import com.icicle.masterdb.service.*;
import com.icicle.masterdb.service.constant.CommonConstant;
import com.icicle.masterdb.service.constant.MapConstant;
import com.icicle.masterdb.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

import static com.icicle.masterdb.core.ProjectConstant.UNKNOWN_EXCEPTION_CODE;
import static com.icicle.masterdb.service.constant.ServiceConstant.DATA_TRANS_ERROR;
import static com.icicle.masterdb.service.constant.ServiceConstant.ID_REAPT;

/**
 * @author wangyuling
 */
@Controller
@RequestMapping("/product")
@Authorization
public class ProductController extends AbstractPageController {
    @Resource
    private ProductService productService;
    @Resource
    private ProductAttributeExtendService productAttributeExtendService;
    @Resource
    private ViewSelectItemService viewSelectItemService;
    @Resource
    private ViewProductmessageinfoService viewProductmessageinfoService;
    @Resource
    private ProductExcelService productExcelService;
    @Resource
    private ProductInfoExcelService productInfoExcelService;
    @Resource
    private SynConfigEntity synConfigEntity;
    @Resource
    private ProductInfoService productInfoService;
    @Resource
    private SkuSizeService skuSizeService;
    @Resource
    private FabricService fabricService;
    @Resource
    private ColorCardService colorCardService;
    @Resource
    private ProductDimensionTrainingService productDimensionTrainingService;
    @Resource
    private ViewProductFeatureService viewProductFeatureService;
    @Resource
    private ColorCardExcelService colorCardExcelService;
    @Resource
    private ProductImageService productImageService;
    @Resource
    private ViewSearchimagesElasticService elasticService;
    @Resource
    private ProductCategoryService productCategoryService;
    @Resource
    private ProductCategoryAttributeService productCategoryAttributeService;
    @Resource
    private ProductExtendAttributeDefinedService productExtendAttributeDefinedService;

    private static final int NAME_LENGET = 20;
    /**
     * 返回页面
     */

    public ProductController() {
        super("product");
    }

    @GetMapping("/productlist")
    public String productList(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("productlist", map, request);
    }

    @GetMapping("/skuleadin")
    public String skuleadin(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("skuleadin", map, request);
    }

    @GetMapping("/productleadin")
    public String productLeadIn(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("productleadin", map, request);
    }

    @GetMapping("/productcategoryleadin")
    public String productcategoryleadin(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("productcategoryleadin", map, request);
    }

    @GetMapping("/pstyleupdate")
    public String pStyleUpdate(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("pstyleupdate", map, request);
    }

    @GetMapping("/productmessage")
    public String productMessage(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("productmessage", map, request);
    }

    @GetMapping("/sku")
    public String sku(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("skulist", map, request);
    }

    @GetMapping("/productsubcategory")
    public String productsubcategory(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("productsubcategorylist", map, request);
    }

    @GetMapping("/psizeuupdate")
    public String pSizeuUpdate(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("psizeupdate", map, request);
    }

    @GetMapping("/productupdatestyle")
    public String productUpdateStyle(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("productupdatestyle", map, request);
    }


    @GetMapping("/featurelist")
    public String featureList(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("featurelist", map, request);
    }

    @GetMapping("/editfeature")
    public String editFeature(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("editfeature", map, request);
    }

    @GetMapping("/colorcardlist")
    public String colorCardList(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("colorcard", map, request);
    }

    @GetMapping("/itemlist")
    public String itemList(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("itemdata", map, request);
    }

    @GetMapping("/productdetail")
    public String productDetail(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("productdetail", map, request);
    }

    @GetMapping("/productimageupload")
    public String productImageUpload(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("updaload", map, request);
    }

    @GetMapping("/listproductimage")
    public String listProductImage(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("productimage", map, request);
    }

    @GetMapping("/imagemodelmapping")
    public String imageModelMapping(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("mapping", map, request);
    }

    @GetMapping(value = "/looklist")
    public String lookList(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("list", map, request);
    }


    @GetMapping(value = "/promotionlist")
    public String promotionList(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("promotionlist", map, request);
    }

    @GetMapping("/attrlist")
    public String attrlist(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("attrlist", map, request);
    }

    @GetMapping("/sizeleadin")
    public String sizeleadin(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("sizeleadin", map, request);
    }

    @PostMapping("/attibutedefined")
    @ResponseBody
    public Result attibuteDefined() {
        try {
            List<ProductAttributeExtend> productAttributeExtendList = productAttributeExtendService.findAll();
            ProductAttributeExtendVO productAttributeExtendVOList = PojoConvertUtil.convertPojo(productAttributeExtendList, ProductAttributeExtendVO.class);
            return ResultGenerator.genSuccessResult(productAttributeExtendVOList);
        } catch (Exception e) {
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("/findbyproductcode")
    @ResponseBody
    public Result findByProductCode(@RequestParam String productCode,@RequestParam String productCategoryCode) {
        if (productCode != null) {
            ProductFindVO productFindVO = productAttributeExtendService.findByProductCode(productCode,productCategoryCode);
            if (productFindVO != null) {
                return ResultGenerator.genSuccessResult(productFindVO);
            } else {
                return ResultGenerator.genFailResult("获取数据为空，请稍后重试");
            }
        } else {
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @GetMapping("/getCategoryLevle2List")
    @ResponseBody
    public Result getCategoryLevle2List(@RequestParam("categoryLevel1Code") String categoryLevel1Code, @Param("productCategoryCode") String productCategoryCode) {
        List<ViewSelectItemVO> categoryLevle2List = productService.getProductcategoryLevel2List(categoryLevel1Code,productCategoryCode);
        if (categoryLevle2List != null) {
            return ResultGenerator.genSuccessResult(categoryLevle2List);
        } else {
            //数据获取为空，请稍后重试
            return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
        }
    }

    @PostMapping("/featurecodefind")
    @ResponseBody
    public Result featureCodeFind(@RequestParam String productCode) {
        if (productCode != null) {
            ProductDimensionTraining result = productDimensionTrainingService.findFeatureCodeDetail(productCode);
            if (result != null) {
                return ResultGenerator.genSuccessResult(result);
            } else {
                return ResultGenerator.genFailResult("获取数据为空，请稍后重试");
            }
        } else {
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }

    }

    //    查询款式特点的所有信息
    @PostMapping("/featurefind")
    @ResponseBody
    public Result featureFind(@RequestParam String productCode) {
        if (productCode != null && productCode.length() != 0) {
            ViewProductFeature result = viewProductFeatureService.findAllDetail(productCode);
            if (result != null) {
                return ResultGenerator.genSuccessResult(result);
            } else {
                return ResultGenerator.genFailResult("获取数据为空，请稍后重试");
            }
        } else {
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    //加载推荐图片信息
    @PostMapping("/recommandimage")
    @ResponseBody
    public Result recommandImage(@RequestParam String productCode) {
        if (productCode != null && productCode.length() != 0) {
            List<ViewProductFeature> result = viewProductFeatureService.getRecommadImage(productCode);
            if (result != null) {
                return ResultGenerator.genSuccessResult(result);
            } else {
                return ResultGenerator.genFailResult("获取数据为空，请稍后重试");
            }
        } else {
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }

    }

    @PostMapping("/featurefinddetail")
    @ResponseBody
    public Result featureFindDetail(@RequestParam String productCode) {
        if (productCode != null && productCode.length() != 0) {
            ProductDimensionTraining result = productDimensionTrainingService.findByFeatureCode(productCode);
            if (result != null) {
                return ResultGenerator.genSuccessResult(result);
            } else {
                return ResultGenerator.genFailResult("获取数据为空，请稍后重试");
            }
        } else {
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }

    }

    @PostMapping("/featureimagefind")
    @ResponseBody
    public Result featureImageFind(@RequestParam String imageName) {
        if (!StringUtils.isBlank(imageName)) {
            ProductImage p = productImageService.findOneByCode(imageName);
            if (p != null) {
                return ResultGenerator.genSuccessResult(p.getImageUrl());
            } else {
                return ResultGenerator.genFailResult("获取数据为空，请稍后重试");
            }
        } else {
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }


    /**
     * 同步基本属性到伯俊
     * @param params
     * @return
     */
    @PostMapping("/syncItem")
    @ResponseBody
    public Result syncItem(@RequestBody String params){
        if(StringUtils.isEmpty(params)){
            return ResultGenerator.genFailResult("您没有选择任何选项，无法提交");
        }
        JSONObject jsonObject = JSON.parseObject(params);
        String items = jsonObject.getString("items");
        String syncSelect = jsonObject.getString("syncSelect");
        if(StringUtils.isEmpty(items) || StringUtils.isEmpty(syncSelect)){
            return ResultGenerator.genFailResult("您没有选择同步选项或同步平台，同步失败");
        }
        List<SyncPropertyVO> syncPropertyVOList = new ArrayList<>();
        List<SyncSizeGroupVO> syncSizeGroupVOList = new ArrayList<>();
        String [] itemArray = items.split(",");
        try {
            if(itemArray.length!=0 && syncSelect.indexOf("burgeon")!=-1){
                List<SyncPropertyVO> itemList = productService.getItemSyncList(itemArray);
                for(SyncPropertyVO syncPropertyVO : itemList){
                    if(syncPropertyVO.getTableName().equals("select_size_group")){
                        SyncSizeGroupVO syncSizeGroupVO = new SyncSizeGroupVO();
                        syncSizeGroupVO.setSize_group_id(syncPropertyVO.getCode()).setSize_group_name(syncPropertyVO.getDescription())
                                .setDescription(syncPropertyVO.getDescription());
                        syncSizeGroupVOList.add(syncSizeGroupVO);
                    }else{
                        MapConstant.itemMap.forEach((k,v)->{
                            if(syncPropertyVO.getTableName().equals(k)){
                                syncPropertyVO.setTableName(v.getTableName());
                                syncPropertyVO.setDimFlag(v.getDimFlag());
                            }
                        });
                        syncPropertyVOList.add(syncPropertyVO);
                    }
                }
            }
            StringBuffer message = new StringBuffer();
            if(!ListUtil.isBlank(syncPropertyVOList)){
                if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getPropertySyncBurgeon()),syncPropertyVOList)) {
                    message.append("属性同步到伯俊成功\n");
                } else {
                    message.append("属性同步到伯俊失败\n");
                }
            }
            if(!ListUtil.isBlank(syncSizeGroupVOList)){
                if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getSyncSizeGroup2Burgeon()),syncSizeGroupVOList)) {
                    message.append("尺寸组同步到伯俊成功\n");
                } else {
                    message.append("尺寸组同步到伯俊失败\n");
                }
            }
            return ResultGenerator.genSuccessResult(message.toString());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage());
            return ResultGenerator.genFailResult("属性同步异常，请联系IT");
        }
    }


    @PostMapping("/insertProductSubCategory")
    @ResponseBody
    public Result insertProductSubCategory(@RequestBody ProductSubCategoryVO productSubCategoryVO){
        try {
            if(productSubCategoryVO == null){
                return ResultGenerator.genFailResult("产品子类保存失败，数据为空");
            }
            if(productSubCategoryVO.getPaSubCategoryCode()==null){
                productSubCategoryVO.setPaSubCategoryCode("");
            }
            if(productSubCategoryVO.getSubCategoryKey()==null){
                productSubCategoryVO.setSubCategoryKey("");
            }

            int checkCodeAndLanCode = productService.checkProductSubCategory(productSubCategoryVO);
            if(checkCodeAndLanCode == 1){
                return ResultGenerator.genFailResult("产品子类编号或语言重复，请核查");
            }
            if(productSubCategoryVO.getSubCategoryLevel() != 1){
                int checkPaSubcategoryCode = productService.checkPaSubcategoryCode(productSubCategoryVO.getLanCode(),productSubCategoryVO.getCategoryCode(),
                        productSubCategoryVO.getPaSubCategoryCode(), productSubCategoryVO.getSubCategoryLevel()-1);
                if(checkPaSubcategoryCode == 0){
                    return ResultGenerator.genFailResult("上级编号不存在，请核查");
                }
            }
            if(!StringUtils.isEmpty(productSubCategoryVO.getSubCategoryKey())){
                int checkMaterialKey = productService.checkProdutCheckMaterialKey(productSubCategoryVO.getSubCategoryKey());
                if(checkMaterialKey > 0){
                    return ResultGenerator.genFailResult("物料键已存在，请核查");
                }
            }
            productSubCategoryVO.setCreateBy(SessionManager.getLoginName());
            productSubCategoryVO.setCreationDate(DateUtil.now());
            productSubCategoryVO.setLastUpdateBy(SessionManager.getLoginName());
            productSubCategoryVO.setLastUpdateDate(DateUtil.now());
            int id = productService.insertProductSubCategory(productSubCategoryVO);
            if(id>0){
                return ResultGenerator.genSuccessResult();
            }else{
                return ResultGenerator.genFailResult("产品子类保存失败，请联系IT");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage());
            return ResultGenerator.genFailResult("产品子类保存异常，请联系IT");
        }
    }


    @PutMapping("/updateProductSubCategory")
    @ResponseBody
    public Result updateProductSubCategory(@RequestBody ProductSubCategoryVO productSubCategoryVO){
        if(productSubCategoryVO == null){
            return ResultGenerator.genFailResult("产品子类保存失败，数据为空");
        }
        if(productSubCategoryVO.getProductSubCategoryId() == null){
            return ResultGenerator.genFailResult("产品子类编号为空，修改失败");
        }
        productSubCategoryVO.setLastUpdateBy(SessionManager.getLoginName());
        productSubCategoryVO.setLastUpdateDate(DateUtil.now());
        try {
            if(productSubCategoryVO.getSubCategoryLevel() != 1){
                int checkPaSubcategoryCode = productService.checkPaSubcategoryCode(productSubCategoryVO.getLanCode(),productSubCategoryVO.getCategoryCode(),
                        productSubCategoryVO.getPaSubCategoryCode(), productSubCategoryVO.getSubCategoryLevel()-1);
                if(checkPaSubcategoryCode == 0){
                    return ResultGenerator.genFailResult("上级编号不存在，请核查");
                }
            }
            int updateCount = productService.updateProductSubCategory(productSubCategoryVO);
            if(updateCount<=0){
                return ResultGenerator.genFailResult("产品子类更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage());
            return ResultGenerator.genFailResult("产品子类更新异常");
        }
        return ResultGenerator.genSuccessResult();
    }


    /**
     * 同步产品子类到伯俊
     * @param params
     * @return
     */
    @PostMapping("/syncproductsubcategory")
    @ResponseBody
    public Result syncproductsubcategory(@RequestBody String params){
        if(StringUtils.isEmpty(params)){
            return ResultGenerator.genFailResult("没有同步数据，同步失败");
        }
        JSONObject jsonObject = JSON.parseObject(params);
        String isFirstLevel = jsonObject.getString("isFirstLevel");
        String productSubCategoryIds = jsonObject.getString("productSubCategoryIds");
        String syncSelect = jsonObject.getString("syncSelect");
        Integer id = jsonObject.getInteger("id");
        String categoryKey = jsonObject.getString("categoryKey");
        String categoryName = jsonObject.getString("categoryName");
        if(isFirstLevel.equals("0")){
            if(StringUtils.isEmpty(productSubCategoryIds) || StringUtils.isEmpty(syncSelect)){
                return ResultGenerator.genFailResult("您没有选择同步选项或同步平台，同步失败");
            }
            String [] itemArray = productSubCategoryIds.split(",");
            try {
                if(itemArray.length!=0 && syncSelect.indexOf("burgeon")!=-1){
                    List<ViewProductSubCategoryList> mapList = productService.getProductSubCategoryIdAndLevel(itemArray);
                    List<Integer> subCategoryLevel1IdList = mapList.stream().filter(a->a.getSubCategoryLevel()==1).map(ViewProductSubCategoryList::getProductSubCategoryId).collect(Collectors.toList());
                    List<Integer> subCategoryLevel2IdList = mapList.stream().filter(a->a.getSubCategoryLevel()==2).map(ViewProductSubCategoryList::getProductSubCategoryId).collect(Collectors.toList());
                    List<Integer> subCategoryLevel3IdList = mapList.stream().filter(a->a.getSubCategoryLevel()==3).map(ViewProductSubCategoryList::getProductSubCategoryId).collect(Collectors.toList());
                    if(!ListUtil.isBlank(subCategoryLevel1IdList)){
                        List<SyncProductSubCategoryVO> subCategoryLevel1List = productService.getSubCategoryLevel1List(subCategoryLevel1IdList);
                        if(!ListUtil.isBlank(subCategoryLevel1List)){
                            if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getSecondcategorySync2Burgeon()),subCategoryLevel1List)) {
                                return ResultGenerator.genSuccessResult();
                            } else {
                                return ResultGenerator.genFailResult("同步到伯俊失败\n");
                            }
                        }
                    }
                    if(!ListUtil.isBlank(subCategoryLevel2IdList)){
                        List<SyncProductSubCategoryVO> subCategoryLevel2List = productService.getSubCategoryLevel2List(subCategoryLevel2IdList);
                        if(!ListUtil.isBlank(subCategoryLevel2List)){
                            if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getThirdcategorySync2Burgeon()),subCategoryLevel2List)) {
                                return ResultGenerator.genSuccessResult();
                            } else {
                                return ResultGenerator.genFailResult("同步到伯俊失败\n");
                            }
                        }
                    }
                    if(!ListUtil.isBlank(subCategoryLevel3IdList)){
                        List<SyncProductSubCategoryVO> subCategoryLevel3List = productService.getSubCategoryLevel3List(subCategoryLevel3IdList);
                        if(!ListUtil.isBlank(subCategoryLevel3List)){
                            if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getFourthcategorySync2Burgeon()),subCategoryLevel3List)) {
                                return ResultGenerator.genSuccessResult();
                            } else {
                                return ResultGenerator.genFailResult("同步到伯俊失败\n");
                            }
                        }
                    }
                }
                return ResultGenerator.genFailResult("同步到伯俊失败\n");
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.getLogger(this.getClass()).error(e.getMessage());
                return ResultGenerator.genFailResult("属性同步异常，请联系IT");
            }
        }else{
            try {
                SyncProductSubCategoryVO syncProductSubCategoryVO = new SyncProductSubCategoryVO();
                syncProductSubCategoryVO.setId(id);
                syncProductSubCategoryVO.setCatalog_no(categoryKey);
                syncProductSubCategoryVO.setCatalog_desc(categoryName);
                syncProductSubCategoryVO.setOperation_status(1);
                if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getOnecategorySync2Burgeon()),syncProductSubCategoryVO)) {
                    return ResultGenerator.genSuccessResult();
                } else {
                    return ResultGenerator.genFailResult("同步到伯俊失败\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.getLogger(this.getClass()).error(e.getMessage());
                return ResultGenerator.genFailResult("同步到伯俊失败\n");
            }
        }

    }

    @PostMapping("/sizebyproductcode")
    @ResponseBody
    public Result sizeByProductCode(@RequestParam Integer id) {
        if (id != null) {
            String color = productInfoService.getColor(id);
            if (!StringUtils.isBlank(color)) {
                return ResultGenerator.genSuccessResult(color);
            } else {
                return ResultGenerator.genFailResult("查找失败,请稍候重试");
            }
        } else {
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    //更新款式
    @PutMapping("/updatestyle")
    @ResponseBody
    public Result updateStyle(@RequestBody ProductAttributeItemsVO productAttributeItemsVO) {
        try{
            if(productAttributeItemsVO.getIsCategory()==0){
                try {
                    int ret = productAttributeExtendService.updateStyle(productAttributeItemsVO);
                    if (ret > 0) {
                        String[] products = new String[]{productAttributeItemsVO.getProductAttributeExtend().getProductCode()};
                        SyncElastic syncElastic = new SyncElastic();
                        syncElastic.setAttriItems(elasticService.getAllAttrItems());
                        syncElastic.setElasticList(elasticService.getList(products));
                        boolean success = SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getProductUpgradeService()), syncElastic);
                        if (success) {
                            return ResultGenerator.genSuccessResult(productAttributeItemsVO.getProductAttributeExtend());
                        } else {
                            return ResultGenerator.genFailResult("更新成功,但是同步到其它系统失败");
                        }
                    } else {
                        return ResultGenerator.genFailResult("更新失败,请稍候重试");
                    }
                } catch (Exception e) {
                    LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                    return ResultGenerator.genFailResult("更新失败,请稍候重试");
                }
            }else{
                int count = productService.alterProductCatogoryValue(productAttributeItemsVO);
                if (count>0) {
                    return ResultGenerator.genSuccessResult("更新成功！");
                } else {
                    return ResultGenerator.genFailResult("更新失败,请稍候重试");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage());
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("/skulist")
    @ResponseBody
    public DataTableRecord skulist(@RequestParam(value = "sEcho") String sEcho,
                                   @RequestParam(value = "iDisplayStart") Integer pageIndex,
                                   @RequestParam(value = "iDisplayLength") Integer pageSize,
                                   @RequestParam(value = "batchSku") String batchSku,
                                   @RequestParam(value = "productCodeOrSku") String productCodeOrSku,
                                   @RequestParam(value = "startTime") String startTime,
                                   @RequestParam(value = "endTime") String endTime,
                                   @RequestParam(value = "iSortCol_0") Integer sortCol,
                                   @RequestParam(value = "sSortDir_0") String sortDir) {
        DataTableRecord dataTableRecord = productService.listSku(sEcho, pageIndex,pageSize,batchSku,productCodeOrSku,startTime,endTime,sortCol, sortDir);
        return dataTableRecord;
    }

    @GetMapping("/getProductSelectItem")
    @ResponseBody
    public Result getProductSelectItem(){
        try {
            List<Map<String,String>> productCategoryList = productService.getProductCategoryList();
            List<Integer> productCategoryLevelList = productService.getProductSubCategoryLevelList();
            List<String> itemTableList = productService.getItemTableList();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("productCategoryList",productCategoryList);
            jsonObject.put("productCategoryLevelList",productCategoryLevelList);
            jsonObject.put("itemTableList",itemTableList);
            return ResultGenerator.genSuccessResult(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage());
            return ResultGenerator.genSuccessResult("获取产品类别列表异常，请联系IT处理");
        }

    }

    @PostMapping("/productsubcategorylist")
    @ResponseBody
    public DataTableRecord productsubcategorylist(@RequestParam(value = "sEcho") String sEcho,
                                   @RequestParam(value = "iDisplayStart") Integer pageIndex,
                                   @RequestParam(value = "iDisplayLength") Integer pageSize,
                                   @RequestParam(value = "productSubCategoryCodeOrName") String productSubCategoryCodeOrName,
                                   @RequestParam(value = "categoryCode") String categoryCode,
                                   @RequestParam(value = "lanCode") String lanCode,
                                   @RequestParam(value = "subCategoryLevel") Integer subCategoryLevel) {
        DataTableRecord dataTableRecord = productService.getProductSubCategoryList(sEcho, pageIndex,pageSize, productSubCategoryCodeOrName,categoryCode,lanCode,subCategoryLevel);
        return dataTableRecord;
    }

    @PutMapping("/insertsize")
    @ResponseBody
    public Result insertSize(@RequestBody ProductInfoSkuVO productInfo) {
        String sku = productInfo.getSku();
        if (StringUtils.isBlank(sku)) {
            return ResultGenerator.genFailResult("条码出错,请稍候重试");
        }
        if (StringUtils.isBlank(productInfo.getSize())) {
            return ResultGenerator.genFailResult("尺码出错,请稍候重试");
        }
        try {
            if (StringUtils.isBlank(productInfo.getColor())) {
                productInfo.setColor("");
            }
            productInfo.setCreationDate(DateUtil.now());
            productInfo.setCreatedBy(SessionManager.getLoginName());
            productInfo.setLastUpdateDate(DateUtil.now());
            productInfo.setLastUpdatedBy(SessionManager.getLoginName());
            Integer id = productInfo.getId();
            if(id != 0){
                int result = checkProductCodeAndSku(productInfo);
                if(result == 0){
                    return ResultGenerator.genFailResult("条码格式不正确，请核对");
                }
                Integer skuId = productService.getexistsSkuCount(productInfo);
                if(skuId!=null && !skuId.equals(productInfo.getId())){
                    return ResultGenerator.genFailResult("条码已存在，请核对");
                }
                int updCount = productService.updateProductInfo(productInfo);
                if(updCount>0){
                    return ResultGenerator.genSuccessResult();
                }else{
                    return ResultGenerator.genFailResult("条码信息更新失败，请稍后再试");
                }
            }else{
                Product product = productService.selectProduct(productInfo.getProductCode());
                if (product == null) {
                    return ResultGenerator.genFailResult("输入款号不存在，请核对款号是否输入正确");
                }
                int ret = productInfoService.insertSize(productInfo);
                if (ret == -DATA_TRANS_ERROR) {
                    return ResultGenerator.genFailResult("尺寸添加重复，请核对");
                } else if(ret == 3){
                    return ResultGenerator.genFailResult("条码格式不正确，请核对");
                } else if (ret == 1) {
                    return ResultGenerator.genSuccessResult();
                } else {
                    return ResultGenerator.genFailResult("保存失败,请稍候重试");
                }
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }



    @PostMapping("/checksize")
    @ResponseBody
    public Result checkSize(@RequestParam("size") String size) {
        if (StringUtils.isBlank(size)) {
            return ResultGenerator.genFailResult("数据验证失败");
        } else {
            List<SkuSize> skuSizeList = skuSizeService.findBySize();
            Long skusize = skuSizeList.stream().filter(d -> d.getSizeCode().equals(size)).count();
            if (skusize != null && skusize != 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("数据验证失败");
            }
        }
    }

    @GetMapping("/getskudata")
    @ResponseBody
    public Result getskudata(@RequestParam Integer id,@RequestParam String productCode,@RequestParam String productCategoryCode) {
        try {
            ProductInfo productInfo = null;
            List<Map<String,String>> batchList = productService.getBatchList();
            List<Map<String,String>> sizeList = productService.getSizeList(productCode,productCategoryCode);
            if(id != 0){
                productInfo = productService.getProductInfoById(id);
            }
            Map<String,Object> map = new HashMap<>(3);
            map.put("batchList",batchList);
            map.put("sizeList",sizeList);
            map.put("productInfo",productInfo);
            return ResultGenerator.genSuccessResult(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("获取批次列表失败");
        }
    }

    @PostMapping("/checkcolorcard")
    @ResponseBody
    public Result checkColorCard(@RequestParam("colorCardCode") String colorCardCode) {
        if (StringUtils.isBlank(colorCardCode)) {
            return null;
        } else {
            try {
                ColorCard colorCard = colorCardService.searchCard(colorCardCode);
                if (colorCard != null) {
                    return ResultGenerator.genSuccessResult(colorCard.getColorCardName());
                } else {
                    return ResultGenerator.genFailResult("数据验证失败");
                }
            } catch (Exception e) {
                LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
            }
        }

    }

    @PostMapping("/checkmaterial")
    @ResponseBody
    public Result checkMaterial(@RequestParam("materialNo") String materialNo) {
        if (StringUtils.isBlank(materialNo)) {
            return null;
        } else {
            try {
                Fabric list = fabricService.searchFabric(materialNo);
                if (list != null) {
                    return ResultGenerator.genSuccessResult(list.getFabricName());
                } else {
                    return ResultGenerator.genFailResult("数据验证失败");
                }
            } catch (Exception e) {
                LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
            }
        }
    }

    @PostMapping("/checkproductcode")
    @ResponseBody
    public Result checkProductCode(@RequestParam(value = "productCode") String productCode) {
        if (StringUtils.isBlank(productCode)) {
            return ResultGenerator.genFailResult("数据获取错误，请稍后重试");
        } else {
            try {
                Product product = productService.selectProduct(productCode);
                if (product != null) {
                    return ResultGenerator.genSuccessResult();
                } else {
                    return ResultGenerator.genFailResult("输入款号不存在，请核对款号是否输入正确");
                }
            } catch (Exception e) {
                LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
            }
        }
    }

    @GetMapping("/dropdown")
    @ResponseBody
    public Result dropDown() {
        try {
            List<String> itemTableNameList = StringUtil.itemTableNameList();
            List<ViewSelectItem> list = viewSelectItemService.findAllItem(itemTableNameList);
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("/messagelist")
    @ResponseBody
    public DataTableRecord messageList(@RequestParam(value = "sEcho") String sEcho,
                                       @RequestParam(value = "iDisplayStart") Integer pageIndex,
                                       @RequestParam(value = "iDisplayLength") Integer pageSize,
                                       @RequestParam(value = "batchProductCode") String batchProductCode ,
                                       @RequestParam(value = "productCodeOrName") String productCodeOrName ,
                                       @RequestParam(value = "startTime") String startTime ,
                                       @RequestParam(value = "endTime") String endTime ,
                                       @RequestParam(value = "syncStatus") String syncStatus ,
                                       @RequestParam(value = "type") Integer type,
                                       @RequestParam(value = "iSortCol_0") Integer sortCol,
                                       @RequestParam(value = "sSortDir_0") String sortDir) {
        DataTableRecord dataTableRecord = viewProductmessageinfoService.listProduct(sEcho, pageIndex,
                pageSize, batchProductCode,productCodeOrName, startTime,endTime,syncStatus,sortCol, sortDir, type);
        return dataTableRecord;
    }


    @GetMapping("/getAttributeDefinedList")
    @ResponseBody
    public Result getAttributeDefinedList(@RequestParam("productCode") String productCode,@RequestParam("productCategoryCode") String productCategoryCode){
        ProductAttributeItemsVO  productAttributeItemsVO = productService.getAttributeDefinedItemList(productCode,productCategoryCode);
        if (productAttributeItemsVO != null) {
            return ResultGenerator.genSuccessResult(productAttributeItemsVO);
        } else {
            //数据获取为空，请稍后重试
            return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
        }
    }

    @PostMapping("/syncsku")
    @ResponseBody
    public Result syncsku(@RequestBody ProductSyncBodyVO productSyncBody){
        String ids = productSyncBody.getIds();
        String syncSelect = productSyncBody.getSyncSelect();
        if (StringUtils.isEmpty(ids)) {
            return ResultGenerator.genFailResult("请选择同步数据");
        }
        List<Integer> idList = getIdList(ids);
        try {
            if(syncSelect.indexOf("burgeon-rfid-wms")==-1 &&  syncSelect.indexOf("oa")==-1){
                return ResultGenerator.genFailResult("此次未同步任何条码");
            }
            StringBuffer sb = new StringBuffer();
            Boolean b1 = null;
            Boolean b2 = null;
            if(!ListUtil.isBlank(idList) && syncSelect.indexOf("burgeon-rfid-wms")!=-1){
                List<SyncSkuVO> mainProductInfoList = productService.getMainProductInfo(idList);
                List<String> materialProductCodeList = productService.getProductMaterialCodeList();
                List<SyncSkuVO>  productCategorySkus = productService.getProductCategorySkus(mainProductInfoList,materialProductCodeList);
                if(!SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getSyncSku()), productCategorySkus)){
                    sb.append("条码同步到伯俊失败\n");
                    b1 = false;
                }else{
                    sb.append("条码同步到伯俊成功\n");
                    b1 = true;
                }
            }

            //先注释掉，不要删除，等插件发布到正式环境以后再放开
//            if(!ListUtil.isBlank(idList) && syncSelect.indexOf("oa")!=-1){
//                List<ProductVO> productCategoryCodeList = productService.getProductCategoryCode(idList);
//                List<String> costumeProductCodeList = productCategoryCodeList.stream().filter(a->a.getProductCategoryCode().equals(CommonConstant.COSTUMECATEGORYKEY)).map(ProductVO::getSku).collect(Collectors.toList());
//                List<String> materialProductCodeList = productCategoryCodeList.stream().filter(a->a.getProductCategoryCode().equals(CommonConstant.MATERIALCATEGORYKEY)).map(ProductVO::getSku).collect(Collectors.toList());
//                List<String> categoryProductCodeList = productCategoryCodeList.stream().filter(a->a.getProductCategoryCode().equals(CommonConstant.BOOKCATEGORYKEY) || a.getProductCategoryCode().equals(CommonConstant.HOMEHOLDCATEGORYKEY))
//                        .map(ProductVO::getSku).collect(Collectors.toList());
//                List<Map<String,Object>> syncCostumeProductList = null;
//                if(!ListUtil.isBlank(costumeProductCodeList)){
//                    syncCostumeProductList = productService.getSyncCostumeProductList(costumeProductCodeList);
//                }
//                List<Map<String,Object>> syncMaterialProductList = null;
//                if(!ListUtil.isBlank(materialProductCodeList)){
//                    syncMaterialProductList = productService.getSyncMaterialProductList(costumeProductCodeList);
//                }
//                List<Map<String,Object>> syncCategoryProductList = null;
//                if(!ListUtil.isBlank(categoryProductCodeList)){
//                    syncCategoryProductList = productService.getSyncCategoryProductList(costumeProductCodeList);
//                }
//                List<OaParamVO> oaParamList = new ArrayList<>();
//                if(!getList(syncCostumeProductList).isEmpty()){
//                    oaParamList.addAll(getList(syncCostumeProductList));
//                }
//                if(!getList(syncMaterialProductList).isEmpty()){
//                    oaParamList.addAll(getList(syncMaterialProductList));
//                }
//                if(!getList(syncCategoryProductList).isEmpty()){
//                    oaParamList.addAll(getList(syncCategoryProductList));
//                }
//
//                if(!ListUtil.isBlank(oaParamList)){
//                    boolean flag = true;
//                    for(OaParamVO oaParamVO : oaParamList){
//                        if(!SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getStoreSkuSync2Oa()), oaParamVO)){
//                            flag = false;
//                        }
//                    }
//                    if(flag){
//                        sb.append("条码同步到OA成功\n");
//                    }else{
//                        sb.append("条码同步到OA失败\n");
//                    }
//                    b2 = flag;
//                }
//            }
            
            if(b1!=null && b2!=null){
                if(b1==false || b2==false){
                    return ResultGenerator.genFailResult(sb.toString());
                }else{
                    return ResultGenerator.genSuccessResult(sb.toString());
                }
            }else if(b1!=null && b2==null){
                if(b1){
                    return ResultGenerator.genSuccessResult(sb.toString());
                }else{
                    return ResultGenerator.genFailResult(sb.toString());
                }
            }else if(b1==null && b2!=null){
                if(b2){
                    return ResultGenerator.genSuccessResult(sb.toString());
                }else{
                    return ResultGenerator.genFailResult(sb.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("条码同步失败，请稍后再试");
    }

    @PostMapping("/synproduct")
    @ResponseBody
    public Result synProduct(@RequestBody ProductSyncBodyVO productSyncBody) {
        String productCodes = productSyncBody.getProductCodes();
        String syncSelect = productSyncBody.getSyncSelect();
        if (productCodes != null && productCodes.length() == 0) {
            return ResultGenerator.genFailResult("您未选择同步数据");
        }
        if(StringUtils.isEmpty(syncSelect)){
            return ResultGenerator.genFailResult("您未选择同步平台");
        }
        String[] proList = productCodes.split(",");
        List<String> productCodeList = Arrays.asList(proList);
        List<Map<String,String>> sizeGoroupList = productService.getSizeGroupList();
        List<SelectItemVO> selectItemVoList = productService.selectItemVoList();
        try{
            Boolean syncFlag = true;
            StringBuffer message = new StringBuffer();
            if (syncSelect.indexOf("ERP、Cache")!=-1) {
                SyncSKU syncInfo = productService.getSyncSKUInfo(productCodeList);
                if(SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getProductUpdateService()), syncInfo)){
                    message.append("同步到ERP、Cache成功\n");
                }else{
                    message.append("同步到ERP、Cache失败\n");
                    syncFlag = false;
                }
            }
            if (syncSelect.indexOf("burgeon")!=-1) {
                List<String> bookProductCodeList = new ArrayList<>();
                List<String> homeholdProductCodeList = new ArrayList<>();
                List<String> materialProductCodeList = new ArrayList<>();
                List<String> costumeProductCodeList = new ArrayList<>();
                List<ProductVO> productCocdAndKeyList =  productService.getProductCodeAndKeyList(productCodeList);
                for(ProductVO productVO : productCocdAndKeyList){
                    if(productVO.getProductCategoryCode().equals(CommonConstant.BOOKCATEGORYKEY)){
                        bookProductCodeList.add(productVO.getProductCode());
                    }else if(productVO.getProductCategoryCode().equals(CommonConstant.HOMEHOLDCATEGORYKEY)){
                        homeholdProductCodeList.add(productVO.getProductCode());
                    }else if(productVO.getProductCategoryCode().equals(CommonConstant.MATERIALCATEGORYKEY)){
                        materialProductCodeList.add(productVO.getProductCode());
                    }else if(productVO.getProductCategoryCode().equals(CommonConstant.COSTUMECATEGORYKEY)){
                        costumeProductCodeList.add(productVO.getProductCode());
                    }
                }
                List<String> filterBookProductCodeList = bookProductCodeList.isEmpty()?null:productService.getBookCodeList(bookProductCodeList);
                List<String> filterHomeholdProductCodeList = homeholdProductCodeList.isEmpty()?null:productService.getHomeholdCodeList(homeholdProductCodeList);
                List<String> filterMaterialProductCodeList = materialProductCodeList.isEmpty()?null:productService.getMaterialCodeList(materialProductCodeList);
                List<String> filterCostumeProductCodeList = costumeProductCodeList.isEmpty()?null:productService.getCostumeCodeList(costumeProductCodeList);

                List<String> filterProductCodeList = new ArrayList<>();
                if(!ListUtil.isBlank(filterBookProductCodeList)){
                    filterProductCodeList.addAll(filterBookProductCodeList);
                }
                if(!ListUtil.isBlank(filterHomeholdProductCodeList)){
                    filterProductCodeList.addAll(filterHomeholdProductCodeList);
                }
                if(!ListUtil.isBlank(filterMaterialProductCodeList)){
                    filterProductCodeList.addAll(filterMaterialProductCodeList);
                }
                if(!ListUtil.isBlank(filterCostumeProductCodeList)){
                    filterProductCodeList.addAll(filterCostumeProductCodeList);
                }
                if(filterProductCodeList.isEmpty()){
                    return ResultGenerator.genFailResult("批量同步失败，无可同步数据");
                }
                List<SyncProductInfoVO> mainProductList = productService.getMainProduct(filterProductCodeList);
                List<Map<String,String>> colorList = productService.getColorsByProductCode(filterProductCodeList);
                List<ProductAttribute> productAttributes = productService.getextendProduct(filterProductCodeList);
                List<Map<String,String>> extendAttributeItemList = productService.getExtendAttributeItemList();

                List<SyncProductInfoVO> syncProductInfoVOList = getExtendProductList(mainProductList, productAttributes, selectItemVoList,
                        sizeGoroupList,extendAttributeItemList,colorList);
                List<SyncMaterialContrainVO> materialContrainVOList = productService.getProductMaterialCodeListByCode(filterProductCodeList);
                if (!ListUtil.isBlank(syncProductInfoVOList)) {
                    if(!ListUtil.isBlank(materialContrainVOList)){
                        for(SyncProductInfoVO synProcut : syncProductInfoVOList){
                            for(SyncMaterialContrainVO syncMaterialContrainVO : materialContrainVOList){
                                if(synProcut.getProduct_code().equals(syncMaterialContrainVO.getProduct_code())){
                                    synProcut.setProduct_mat(syncMaterialContrainVO);
                                }
                            }
                        }
                    }
                    if(SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getSyncProduct2Buygeon()), syncProductInfoVOList)){
                        message.append("同步到伯俊成功\n");
                    }else{
                        message.append("同步到伯俊失败\n");
                        syncFlag = false;
                    }
                }
            }
            if (syncFlag) {
                try {
                    String lastUpdateBy = SessionManager.getLoginName();
                    productService.updateSynProduct(lastUpdateBy, DateUtil.now(), proList);
                    return ResultGenerator.genSuccessResult(message.toString());
                } catch (Exception e) {
                    LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                    return ResultGenerator.genFailResult("批量同步失败，请您稍后再试");
                }
            } else {
                return ResultGenerator.genFailResult("批量同步失败，请您稍后再试");
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult("批量同步失败，请您稍后再试");
        }
    }


    @PostMapping("/uploadcostumelist")
    @ResponseBody
    public Result uploadcostumelist( HttpServletRequest request) {
        MultipartFile file = null;
        List<MultipartFile> files = ((MultipartRequest) request).getFiles("uploadcategoryfile");
        if (files != null && !files.isEmpty()) {
            file = files.get(0);
        }
        ProductExcelVO productExcelVO = null;
        try {
            if (!ExcelUtil.isExcelFile(file.getInputStream())) {
                return ResultGenerator.genFailResult("此文件不是excel文件，请上传excel文件");
            }
            String path = ExcelUtil.saveExcelFile(file);
            productExcelVO = productExcelService.parseExcel(false, path);
            if (productExcelVO.getFlag() == false) {
                return ResultGenerator.genFailResult("excel列数出现问题，请检查导入格式");
            }
            if ((productExcelVO.getErrorRows() != null && productExcelVO.getErrorRows().size() > 0)) {
                return ResultGenerator.genSuccessResult(path);
            }
            if (productExcelVO.getExictCodes() != null && productExcelVO.getExictCodes().size() > 0) {
                return ResultGenerator.genSuccessResult(path);
            }
            return getProductResult(productExcelVO, true);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 产品类别导入
     * @param request
     * @return
     */
    @PostMapping("/uploadcategorylist")
    @ResponseBody
    public Result uploadcategorylist( HttpServletRequest request) {
        MultipartFile file = null;
        Map<String,Object> map = new HashMap<>();
        try {
            List<MultipartFile> files = ((MultipartRequest) request).getFiles("uploadcategoryfile");
            if(files!=null && !files.isEmpty()){
                file = files.get(0);
            }
            if (!ExcelUtil.isExcelFile(file.getInputStream())) {
                return ResultGenerator.genFailResult("此文件不是excel文件，请上传excel文件");
            }
            String tableName = request.getParameter("productCategoryCode");
            String path = ExcelUtil.saveExcelFile(file);
            int tableCulumnsCount = productCategoryService.getTableCulomnsCount(tableName);
            List<Map<String,String>> tableNameList = productCategoryService.getSelectTableName(tableName);
            Result finalResult = productCategoryService.parseExcel(path,tableCulumnsCount,tableName,tableNameList);
            if(finalResult.getCode()==200){
                List<Integer> countList = productCategoryService.checkProductList(path,tableName);
                if(countList.isEmpty()){
                    map.put("message","Excel解析异常，请联系IT");
                    map.put("code","202");
                    return ResultGenerator.genFailResult(map);
                }
                int updateCount = countList.get(0);
                int insertCount = countList.get(1);
                if(updateCount>=1 || insertCount>=1){
                    StringBuffer sb = new StringBuffer();
                    if(updateCount>=1){
                        sb.append("有"+updateCount+"条记录待更新,\n");
                    }
                    if(insertCount>=1){
                        sb.append("有"+insertCount+"条记录数据不全,\n");
                    }
                    sb.append("是否下载查看？");
                    ExcelDataVO excelDataVO = new ExcelDataVO();
                    excelDataVO.setResult(finalResult);
                    excelDataVO.setProductCategoryCode(tableName);
                    excelDataVO.setPath(path);

                    map.put("message",sb.toString());
                    map.put("code","201");
                    map.put("excelData",excelDataVO);
                    map.put("path",path);
                    return ResultGenerator.genFailResult(map);
                }

                int count = productCategoryService.saveExcelData((List<Map<String,Object>>)finalResult.getData(),tableName);
                if(count==0){
                    map.put("message","导入Excel异常");
                    map.put("code","202");
                    return ResultGenerator.genFailResult(map);
                }
                if(!finalResult.getMessage().isEmpty()){
                    map.put("message","成功导入"+count+"条数据，请下载查看错误数据");
                    map.put("path",path);
                }else{
                    map.put("message","成功导入"+count+"条数据");
                    map.put("path","");
                }
                return ResultGenerator.genSuccessResult(map);
            }else{
                map.put("message",finalResult.getMessage());
                map.put("code",finalResult.getCode());
                if(finalResult.getCode()==201){
                    map.put("path",path);
                }
                return ResultGenerator.genFailResult(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            map.put("message","程序出现未知错误，请联系IT处理");
            map.put("code","202");
            return ResultGenerator.genFailResult(map);
        }
    }


    @PostMapping("/importData")
    @ResponseBody
    public Result importData(@RequestBody ExcelDataVO excelDataVO){
        Map<String,String> resultMap = new HashMap<>();
        try{
            Result finalResult = excelDataVO.getResult();
            int count = productCategoryService.saveExcelData((List<Map<String,Object>>)finalResult.getData(),excelDataVO.getProductCategoryCode());
            if(count==0){
                resultMap.put("message","导入Excel异常");
                resultMap.put("code","202");
                return ResultGenerator.genFailResult(resultMap);
            }
            if(!finalResult.getMessage().isEmpty()){
                resultMap.put("message","成功导入"+count+"条数据，请下载查看错误数据");
                resultMap.put("path",excelDataVO.getPath());
            }else{
                resultMap.put("message","成功导入"+count+"条数据");
                resultMap.put("path","");
            }
            return ResultGenerator.genSuccessResult(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            resultMap.put("message","程序出现未知错误，请联系IT处理");
            resultMap.put("code","202");
            return ResultGenerator.genFailResult(resultMap);
        }

    }

    /**
     * 条码导入--条码同步
     * @param file
     * @return
     */
    @PostMapping("/uploadproductinfo")
    @ResponseBody
    public Result uploadproductinfo(@RequestParam("uploadfile") MultipartFile file) {
        Map<String,Object> map = new HashMap<>();
        try {
            if (!ExcelUtil.isExcelFile(file.getInputStream())) {
                return ResultGenerator.genFailResult("此文件不是excel文件，请上传excel文件");
            }
            String path = ExcelUtil.saveExcelFile(file);
            List<Object> list = new ArrayList<>();
            List<Map<String,String>> productCodeAndCategoryCodeList = productCategoryService.getProductCodeAndCategoryCodeList();
            List<Map<String,String>> batchList = productService.selectBatchList();
            List<Map<String,Object>> sizeList = productService.selectSize();
            List<Map<String,String>> productCodeSizeGroupList = productService.selectProductCodeSizeGroup();
            list.add(productCodeAndCategoryCodeList);
            list.add(batchList);
            list.add(sizeList);
            list.add(productCodeSizeGroupList);
            Result result = productCategoryService.parseProductInfoExcel(path,list);
            //全部或部分数据导入
            if(result.getCode()==200){
                int count = 0;
                List<Map<String,Object>> readMapList = (List<Map<String,Object>>)result.getData();
                if(ListUtil.isBlank(readMapList)){
                    map.put("message","导入Excel失败，请您稍后再试");
                    map.put("code","202");
                    return ResultGenerator.genFailResult(map);
                }

                List<String> productCodeList = new ArrayList<>();
                for(Map<String,Object> eleMap : readMapList){
                    productCodeList.add(eleMap.get("product_code").toString());
                }
                count = productCategoryService.saveOrUpdateProductInfoExcelData(readMapList);
                if(count==0){
                    map.put("message","导入Excel失败，请您稍后再试");
                    map.put("code","202");
                    return ResultGenerator.genFailResult(map);
                }
                //条码同步
                List<Integer> idList = new ArrayList<>();
                if(!ListUtil.isBlank(readMapList)){
                    List<ProductInfoVO> codeSkuList =  productService.selectProductCodeAndSku();
                    for(Map<String,Object> eleMap :readMapList){
                        String productCode = eleMap.get("product_code").toString();
                        String sku = eleMap.get("sku").toString();
                        Integer id = null;
                        ProductInfoVO productInfoVO = codeSkuList.stream().filter(a->a.getProduct_code().equals(productCode) && a.getSku().equals(sku)).findFirst().get();
                        if(productInfoVO!=null){
                            id = productInfoVO.getId();
                        }
                        if(id!=null){
                            idList.add(id);
                        }
                    }
                }
                List<SyncSkuVO> productCategorySkus = null;
                if(!ListUtil.isBlank(idList)){
                    List<SyncSkuVO> mainProductInfoList = productService.getMainProductInfo(idList);
                    List<String> materialProductCodeList = productService.getProductMaterialCodeList();
                    productCategorySkus = productService.getProductCategorySkus(mainProductInfoList,materialProductCodeList);
                }
                String errorMessage = result.getMessage();
                if(!ListUtil.isBlank(productCategorySkus)){
                    boolean b = SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getSyncSku()), productCategorySkus);
                    if(!b){
                        return ResultGenerator.genFailResult(getFailResultMap(errorMessage,path,count));
                    }else{
                        String resultMessage = StringUtils.isEmpty(errorMessage)?"成功导入"+count+"条数据,同步到其他系统成功":"成功导入"+count+"条数据，同步到其他系统成功\n请下载查看导入错误的数据";
                        if(!StringUtils.isEmpty(errorMessage)){
                            map.put("path",path);
                        }
                        map.put("message",resultMessage);
                        map.put("code","200");
                        return ResultGenerator.genSuccessResult(map);
                    }
                }else{
                    return ResultGenerator.genFailResult(getFailResultMap(errorMessage,path,count));
                }
            }else{
                //模板格式错误202或者全部数据错误201
                map.put("message",result.getMessage());
                map.put("code",String.valueOf(result.getCode()));
                if(result.getCode()==201){
                    map.put("path",path);
                }
                return ResultGenerator.genFailResult(map);
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            map.put("message","excel导入异常，请联系IT处理");
            map.put("code","203");
            return ResultGenerator.genFailResult(map);
        }
    }

    @PostMapping("/uploadstyle")
    @ResponseBody
    public Result uploadStyle(@RequestParam("uploadfile") MultipartFile file ,HttpServletRequest request) {
        ProductExcelVO productExcelVO = null;
        try {
            if (!ExcelUtil.isExcelFile(file.getInputStream())) {
                return ResultGenerator.genFailResult("此文件不是excel文件，请上传excel文件");
            }
            String path = ExcelUtil.saveExcelFile(file);
            productExcelVO = productExcelService.parseExcel(false, path);
            if (productExcelVO.getFlag() == false) {
                return ResultGenerator.genFailResult("excel列数出现问题，请检查导入格式");
            }
            if ((productExcelVO.getErrorRows() != null && productExcelVO.getErrorRows().size() > 0)) {
                return ResultGenerator.genSuccessResult(path);
            }
            if (productExcelVO.getExictCodes() != null && productExcelVO.getExictCodes().size() > 0) {
                return ResultGenerator.genSuccessResult(path);
            }
            return getProductResult(productExcelVO, true);

        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        }
        return ResultGenerator.genFailResult("程序出现未知错误，请联系IT处理");
    }

    @PostMapping("/productModeldownload")
    public void productModeldownload(HttpServletResponse response, HttpServletRequest request, @RequestParam("excelpath") String path) {
        File excelFile = new File(path);
        InputStream fis = null;
        OutputStream toClient = null;
        try {
            // 以流的形式下载文件。
            fis = new BufferedInputStream(new FileInputStream(excelFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            //如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("error".concat(excelFile.getName()), "UTF-8"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
        }
    }
    @PostMapping("/downloadexcelmodel")
    public void downloadexcelmodel(HttpServletRequest request, HttpServletResponse response,@RequestParam("excelpath") String path) throws Exception {
        path = System.getProperty("user.dir")+"/src/main/resources"+path;
        File excelFile = new File(path);
        BufferedInputStream fis = null;
        OutputStream toClient = null;
        try {
            // 以流的形式下载文件。
            fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);

            // 清空response
            response.reset();
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            // 设置输出长度
            response.setHeader("Content-Length",String.valueOf(excelFile.length()));
            response.setHeader("Content-Disposition", "attachment;filename=" +
                    new String(excelFile.getName().getBytes("UTF-8"), "ISO-8859-1"));
            toClient.write(buffer);
            toClient.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (toClient != null) {
                toClient.close();
            }
        }
    }


    @PutMapping("/updatestyleup")//更新上传款式
    @ResponseBody
    public Result updateStyleUp(@RequestParam("uploadfile") MultipartFile file, HttpServletResponse response) {
        ProductExcelVO productExcelVO = null;
        try {
            if (!ExcelUtil.isExcelFile(file.getInputStream())) {
                return ResultGenerator.genFailResult("此文件不是excel文件，请上传excel文件");
            }
            String path = ExcelUtil.saveExcelFile(file);
            productExcelVO = productExcelService.parseExcel(true, path);
            if (productExcelVO.getFlag() == false) {
                return ResultGenerator.genFailResult("excel列数出现问题，请检查导入格式");
            }
            if (productExcelVO.getErrorRows() != null && productExcelVO.getErrorRows().size() > 0 || productExcelVO.getExictCodes() != null && productExcelVO.getExictCodes().size() > 0) {
                //输出到浏览器
                return ResultGenerator.genSuccessResult(path);
            }
            return getProductResult(productExcelVO, false);

        } catch (IOException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        } catch (InvalidFormatException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        }
        return ResultGenerator.genFailResult("程序出现未知错误，请联系IT处理");
    }

    @PostMapping("/sizeinsert")
    @ResponseBody
    public Result sizeInsert(@RequestParam("uploadfile") MultipartFile file) {
        ProductInfoExcelVO productInfoExcelVO = null;
        try {
            if (!ExcelUtil.isExcelFile(file.getInputStream())) {
                return ResultGenerator.genFailResult("此文件不是excel文件，请上传excel文件");
            }
            String path = ExcelUtil.saveExcelFile(file);
            productInfoExcelVO = productInfoExcelService.parseExcel(path, false);
            if (productInfoExcelVO.isFlag() == false) {
                return ResultGenerator.genFailResult("excel列数出现问题，请检查导入格式");
            }
            if (productInfoExcelVO.getErrorRows() != null && productInfoExcelVO.getErrorRows().size() > 0) {
                //输出到浏览器
                return ResultGenerator.genSuccessResult(path);
            } else {
                return getProductInfoResult(productInfoExcelVO);
            }
        } catch (IOException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        }
        return ResultGenerator.genFailResult("程序出现未知错误，请联系IT处理");
    }

    @PostMapping("/colorcardupload")
    @ResponseBody
    public Result colorCardUpload(@RequestParam("uploadfile") MultipartFile file) {
        ColorCardVO colorCardVO = null;
        try {
            if (!ExcelUtil.isExcelFile(file.getInputStream())) {
                return ResultGenerator.genFailResult("此文件不是excel文件，请上传excel文件");
            }
            colorCardVO = colorCardExcelService.parseExcel(file.getInputStream());
            ExcelUtil.saveExcelFile(file);
        } catch (IOException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        }
        return getColorCardResult(colorCardVO);
    }

    @PostMapping("/stylefeature")
    @ResponseBody
    public Result styleFeature(@RequestBody ProductDimensionTraining productDimensionTraining) {
        int check = checkProductFeasure(productDimensionTraining);
        int ret = 0;
        if (check == DATA_TRANS_ERROR) {
            return ResultGenerator.genFailResult("获取数据错误");
        }
        if (check == ID_REAPT) {
            ret += productDimensionTrainingService.updateDimensionTrain(productDimensionTraining);
        } else if (check == 1) {
            ret += productDimensionTrainingService.insertDimensionTrain(productDimensionTraining);
        } else {
            return ResultGenerator.genFailResult("新增失败,请稍候重试");
        }
        if (ret > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("新增失败,请稍候重试");
        }
    }

    @DeleteMapping(value = "/deletesize/{productcode}")
    @ResponseBody
    public Result deleteSize(@PathVariable String productcode) {
        int count = productService.searchSynRecord(productcode);
        if (count > 0) {
            return ResultGenerator.genFailResult("数据已经同步，无法进行删除");
        } else {
            int ret = productInfoService.delSize(productcode);
            if (ret > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("系统发生错误，删除失败");
            }
        }
    }

    @DeleteMapping(value = "/batchdeletesku/{ids}")
    @ResponseBody
    public Result batchdeletesku(@PathVariable String ids) {
        try {
            if(StringUtils.isEmpty(ids)){
                return ResultGenerator.genFailResult("没有选中的条码，删除失败");
            }
            String [] idArray = ids.split(",");
            if(idArray.length == 0){
                return ResultGenerator.genFailResult("没有选中的条码，删除失败");
            }
            int deleteCount = productService.batchDeleteSku(idArray);
            if(deleteCount<=0){
                return ResultGenerator.genFailResult("条码删除失败，请您稍后再试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("条码删除异常，请您稍后再试");
        }
        return ResultGenerator.genSuccessResult();
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

    //色卡模块
    //获取列表
    @PostMapping("/colorcardlist")
    @ResponseBody
    public DataTableRecord colorCardList(@RequestParam(value = "sEcho") String sEcho,
                                         @RequestParam(value = "iDisplayStart") Integer pageIndex,
                                         @RequestParam(value = "iDisplayLength") Integer pageSize,
                                         @RequestParam(value = "sSearch") String sSearch,
                                         @RequestParam(value = "iSortCol_0") Integer sortCol,
                                         @RequestParam(value = "sSortDir_0") String sortDir) {
        DataTableRecord dataTableRecord = colorCardService.listColorCard(sEcho, pageIndex, pageSize, sSearch, sortCol, sortDir);
        return dataTableRecord;
    }

    //色卡新建
    @PostMapping("/insertcolorcard")
    @ResponseBody
    @LogAction(logDesc = "新建色卡")
    public Result insertColorCard(@RequestBody ColorCard colorCard) {
        int check = checkColorCard(colorCard);
        if (check == DATA_TRANS_ERROR) {
            return ResultGenerator.genFailResult("数据获取错误，请检查！");
        }
        if (check == ID_REAPT) {
            return ResultGenerator.genFailResult("该色卡编码或名称已经存在，请勿重复添加");
        }
        if (check == 1) {
            int ret = colorCardService.insertColorCard(colorCard);
            if (ret > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("添加数据失败，请稍后重试");
            }
        } else {
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    //色卡更新
    @PutMapping("/updatecolorcard")
    @ResponseBody
    public Result updateColorCard(@RequestBody ColorCard colorCard) {
        int check = checkColorCard(colorCard);
        if (check == DATA_TRANS_ERROR) {
            return ResultGenerator.genFailResult("数据获取错误，请检查！");
        }
        if (check == ID_REAPT) {
            return ResultGenerator.genFailResult("该色卡编码或名称已经存在，请勿更新重复值");
        }
        if (check == 1) {
            int ret = colorCardService.updateColorCard(colorCard);
            if (ret > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("更新数据失败，请稍后重试");
            }
        } else {
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    //选项处理
    @PostMapping("/itemlist")
    @ResponseBody
    public DataTableRecord itemList(@RequestParam(value = "sEcho") String sEcho,
                                    @RequestParam(value = "iDisplayStart") Integer pageIndex,
                                    @RequestParam(value = "iDisplayLength") Integer pageSize,
                                    @RequestParam(value = "itemCategory") String itemCategory,
                                    @RequestParam(value = "itemSelect") String itemSelect,
                                    @RequestParam(value = "iSortCol_0") Integer sortCol,
                                    @RequestParam(value = "sSortDir_0") String sortDir) {
        DataTableRecord dataTableRecord = viewSelectItemService.listItem(sEcho, pageIndex, pageSize,itemCategory,itemSelect,sortCol, sortDir);
        return dataTableRecord;
    }

    @PostMapping("/iteminsert")
    @ResponseBody
    public Result itemInsert(@RequestBody ViewSelectItem viewSelectItem) {
        int check = checkItemData(viewSelectItem);
        if (check == DATA_TRANS_ERROR || check == -1) {
            return ResultGenerator.genFailResult("数据获取错误，请检查！");
        }
        if (check == ID_REAPT) {
            return ResultGenerator.genFailResult("新增选项键或选项值已经存在，请检查输入数据");
        }
        if (check == 1) {
            int ret = viewSelectItemService.itemInsertByTable(viewSelectItem);
            if (ret > 0) {
                return sync2Burgeon(viewSelectItem);
            } else {
                return ResultGenerator.genFailResult("数据新增失败，请稍后重试！");
            }
        } else {
            return ResultGenerator.genFailResult("数据新增失败，请稍后重试！");
        }
    }

    @PutMapping("/itemupdate")
    @ResponseBody
    public Result itemUpdate(@RequestBody ViewSelectItem viewSelectItem) {
        int check = checkItemData(viewSelectItem);
        if (check == DATA_TRANS_ERROR || check == -1) {
            return ResultGenerator.genFailResult("数据获取错误，请检查！");
        }
        if (check == ID_REAPT) {
            return ResultGenerator.genFailResult("该选项键或选项值已经存在，请勿更新重复值");
        }
        if (check == 1) {
            int ret = viewSelectItemService.itemUpdateByTable(viewSelectItem);
            if (ret > 0) {
                return sync2Burgeon(viewSelectItem);
            } else {
                return ResultGenerator.genFailResult("数据更新失败，请稍后重试！");
            }
        } else {
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
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

    @RequestMapping("/exportpro")
    public void exportPro(HttpServletResponse response, @RequestParam(value = "condition") String condition) {
        if (condition != null) {
            ProExportVO proExport = JSON.parseObject(condition, ProExportVO.class);
            String year = proExport.getYear();
            int yearvalue = 0;
            if (!StringUtils.isBlank(year)) {
                yearvalue = Integer.parseInt(year);
            }
            List<Map<String, Object>> m = productService.getExportData(proExport.getSyncStatus(), proExport.getSyncRecord(), proExport.getLine(), proExport.getProductClass(), yearvalue, proExport.getSeason(), proExport.getDevSeason(), proExport.getWave(), proExport.getBand(), proExport.getIcicleGroup(), proExport.getWorkGroup());
            String a = "product_code,product_name,uom,unit_price,standard_cost," +
                    "security_code,estimated_rate,order_no,dev_no,style_no,model_no,line,material_no," +
                    "material_name,color_name,productclass_value,year,nature_season,dev_season,wave,band,icicle_group,sales_date,color_card_no,color_card_name,colour_system,workgroup_value,size_group,brand,code,standard,batch,style_rule,supplier,opr,op_date";
            List<String> titles = Arrays.asList(a.split(","));
            SXSSFWorkbook workbook = productExcelService.exportToExcel(titles, m);
            try {
                response.reset();
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                //如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("款式信息列表.xlsx", "UTF-8"));
                workbook.write(response.getOutputStream());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载模板--产品
     * @param response
     * @param productCategoryId
     * @param tableName
     */
    @PostMapping("/exportexcel")
    public void exportExcel(HttpServletResponse response,@RequestParam String productCategoryId,@RequestParam String tableName){
        try {
            Workbook workbook = null;
            if (tableName.equals("costume")){
                tableName = "sku";
                workbook = productService.getCostumeWorkbook();
            }else{
                List<ProductCategoryAttributeVO> productCategoryAttributeVOList =  productCategoryAttributeService.findTableAttr(productCategoryId);
                Map<String, String> attrDefsMap=new HashMap<>();
                Map<String, String> attrValuesMap=new HashMap<>();
                Map<String, String> defTypeMap=new HashMap<>();
                List<Object> list = new ArrayList<>();
                List<Map<String,String>> mapList = new ArrayList<>();
                if(null != productCategoryAttributeVOList && !productCategoryAttributeVOList.isEmpty()){
                    for(ProductCategoryAttributeVO productCategoryAttributeVO : productCategoryAttributeVOList){
                        attrDefsMap.put(productCategoryAttributeVO.getDefCode(),productCategoryAttributeVO.getDefName());
                        attrValuesMap.put(productCategoryAttributeVO.getDefCode(),productCategoryAttributeVO.getModelValue());
                        defTypeMap.put(productCategoryAttributeVO.getDefCode(),productCategoryAttributeVO.getDefType());
                    }
                }
                mapList.add(attrDefsMap);
                mapList.add(attrValuesMap);
                mapList.add(defTypeMap);
                List<String> brandNameList = productService.getBrandNameList();
                List<Map<String,String>> itemList = productService.getItemList();
                List<Map<String,Object>> productSubCategoryList = productService.getProductSubCategory();
                List<String> uomList = productService.selectUomList();
                list.add(tableName);
                list.add(brandNameList);
                list.add(itemList);
                list.add(productSubCategoryList);
                list.add(uomList);
                workbook = ExcelUtil.writeProductExcel(mapList,list);
            }
            ExcelUtil.downloadExcelWithStream(response,workbook,tableName);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        }
    }


    /**
     * 下载模板--条码
     * @param response
     */
    @PostMapping("/downloadProductInfoModel")
    public void downloadProductInfoModel(HttpServletResponse response){
        try {
            List<Map<String,String>> batchList = productService.selectBatchList();
            List<Map<String,Object>> sizeList = productService.selectSize();
            Workbook workbook = ExcelUtil.writeProductInfoExcel(batchList,sizeList);
            ExcelUtil.downloadExcelWithStream(response,workbook,"productinfo");
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        }
    }


    @GetMapping("/listattributeitems")
    @ResponseBody
    public Result listAttributeItems() {
        List<ProductCategoryVO> productCategoryVOList;
        try {
            productCategoryVOList = productCategoryCode2TableName(productCategoryService.findAll());
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(productCategoryVOList);
    }

    @PostMapping("/listattributeitem")
    @ResponseBody
    public Result listattributeitem() {
        List<ProductCategory> productCategoryList;
        try {
            productCategoryList = productCategoryService.findAll();
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(productCategoryList);
    }

    @PostMapping("/addproductcategory")
    @ResponseBody
    public Result addproductcategory(@RequestBody ProductCategory productCategory) {
        String message = checkProductCategory(productCategory, false);
        if (message != null) {
            return ResultGenerator.genFailResult(message);
        }
        productCategory.setCreatedBy(SessionManager.getLoginName());
        productCategory.setCreationDate(DateUtil.now());
        try {
            int count = productCategoryService.saveProductCategory(productCategory);
            if (count > 0) {
                return ResultGenerator.genSuccessResult(productCategory);
            } else {
                return ResultGenerator.genFailResult(LanguageUtil.getText(1062));
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @GetMapping("/getproductcategoryList")
    public String getproductcategoryList(Map<String, Object> map, HttpServletRequest request) {
        String loadUrl = request.getParameter("loadUrl");
        request.setAttribute("loadUrl",loadUrl);
        return this.freeMarkerViewResult("productcategory", map, request);
    }

    @PutMapping("/updateproductcategory")
    @ResponseBody
    public Result updateproductcategory(@RequestBody ProductCategory productCategory) {
        int ret = productCategoryService.updateProductCategory(productCategory);
        if (ret > 0) {
            return ResultGenerator.genSuccessResult(productCategory);
        } else {
            return ResultGenerator.genFailResult(LanguageUtil.getText(1063));
        }
    }

    @PostMapping("/listmapkey")
    @ResponseBody
    public Result listmapkey(@RequestParam String loadUrl) {
        List<Map<String,String>> keyMap =null;
        try {
            keyMap = productCategoryService.getKeyList(loadUrl);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(keyMap);
    }

    @PostMapping("/getcategorytable")
    @ResponseBody
    public DataTableRecord getcategorytable(@RequestParam("sEcho") String sEcho, @RequestParam("iDisplayStart") Integer pageIndex,
                                        @RequestParam(value = "sSearch") String sSearch, @RequestParam("iDisplayLength") Integer pageSize,
                                        @RequestParam("productCategoryCode") String productCategoryCode  ){
        DataTableRecord dataTableRecord = productCategoryService.getcategorytable(sEcho, pageIndex, pageSize,productCategoryCode,sSearch);
        return dataTableRecord;
    }


    @PostMapping("/listattributedefined")
    @ResponseBody
    public Result listAttributeDefined() {
        List<ProductExtendAttributeDefinedVO> productExtendAttributeDefinedVOList;
        try {
            productExtendAttributeDefinedVOList = productExtendAttributeDefinedService.findAllProductCategoryAttributeDefinedVO();
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(productExtendAttributeDefinedVOList);
    }

    @GetMapping("finddefinedbyId")
    @ResponseBody
    public Result findDefinedById(@RequestParam(value = "id") Integer id) {
        if (id == null) {
            //属性定义id不能为空
            return ResultGenerator.genFailResult(LanguageUtil.getText(1072));
        }
        List<ProductExtendAttributeItem> productExtendAttributeItemList = productExtendAttributeDefinedService.findItemById(id);
        if (!ListUtil.isBlank(productExtendAttributeItemList)) {
            return ResultGenerator.genSuccessResult(productExtendAttributeItemList);
        } else {
            //查询为空
            return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
        }
    }


    @PostMapping("/attribconnect")
    @ResponseBody
    public Result attribConnect(@RequestBody ProductCategoryVO productCategoryVO,HttpServletRequest request) {
        if (productCategoryVO.getProductCategoryId() == null) {
            return ResultGenerator.genFailResult(LanguageUtil.getText(1564));
        }
        try {
            int ret = productCategoryService.connectAttribute(productCategoryVO);
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

    @PutMapping("updateattributedefine")
    @ResponseBody
    public Result updateAttributeDefine(@RequestBody ProductExtendAttributeDefinedVO productExtendAttributeDefinedVO) {
        try {
            int ret = productExtendAttributeDefinedService.updateAttributeDef(productExtendAttributeDefinedVO);
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

    @PostMapping("/listproductcategoryattri")
    @ResponseBody
    public Result listproductcategoryattri() {
        List<ProductCategoryAttributeVO> productCategoryAttributeVOList;
        try {
            productCategoryAttributeVOList = productCategoryAttributeService.findAllProductCategoryAttriVO();
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(productCategoryAttributeVOList);
    }

    //属性新增
    @PostMapping("/addattributedefine")
    @ResponseBody
    public Result addattributedefine(@RequestBody ProductExtendAttributeDefinedVO productExtendAttributeDefinedVO) {
        String message = checkProductExtendAttribDef(productExtendAttributeDefinedVO, false);
        if (message != null) {
            return ResultGenerator.genFailResult(message);
        }
        try {
            int ret = productExtendAttributeDefinedService.addAttributeDef(productExtendAttributeDefinedVO);
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

    private String checkProductExtendAttribDef(ProductExtendAttributeDefinedVO productExtendAttributeDefinedVO, boolean update) {
        if (productExtendAttributeDefinedVO == null) {
            //更新失败
            return LanguageUtil.getText(1018);
        }
        if (update && productExtendAttributeDefinedVO.getId() == null) {
            //属性定义Id不能为空
            return LanguageUtil.getText(1072);
        }
        if (StringUtils.isBlank(productExtendAttributeDefinedVO.getDefName())) {
            //属性名称不能为空
            return LanguageUtil.getText(1086);
        }

        Condition condition = new Condition(ProductExtendAttributeDefined.class);
        if (update) {
            condition.createCriteria().andNotEqualTo("id", productExtendAttributeDefinedVO.getId());
            condition.and(condition.createCriteria().andEqualTo("defName", productExtendAttributeDefinedVO.getDefName()).
                    orEqualTo("defCode", productExtendAttributeDefinedVO.getDefCode()));
        } else {
            if (StringUtils.isBlank(productExtendAttributeDefinedVO.getDefCode())) {
                //属性编码不能为空
                return LanguageUtil.getText(1085);
            }
            condition.createCriteria().andEqualTo("defName", productExtendAttributeDefinedVO.getDefName()).
                    orEqualTo("defCode", productExtendAttributeDefinedVO.getDefCode());
        }

        try {
            int count = productExtendAttributeDefinedService.findCountByCondition(condition);
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

    private String checkProductCategory(ProductCategory productCategory, boolean update) {
        if (productCategory == null) {
            return LanguageUtil.getText(1079);
        }
        Condition condition = new Condition(ProductCategory.class);

        if (update) {
            condition.createCriteria().andNotEqualTo("productCategoryId", productCategory.getProductCategoryId());
            condition.and(condition.createCriteria().andEqualTo("productCategoryCode", productCategory.getProductCategoryCode())
                    .orEqualTo("categoryName", productCategory.getCategoryName()));
        } else {
            if (StringUtils.isBlank(productCategory.getProductCategoryCode().trim())) {
                //维度编码不能为空
                return LanguageUtil.getText(1562);
            }
            condition.createCriteria().andEqualTo("productCategoryCode", productCategory.getProductCategoryCode())
                    .orEqualTo("categoryName", productCategory.getCategoryName());
        }
        return null;
    }

    public Result getProductResult(ProductExcelVO productExcelVO, boolean insert) {
        int result = 0;
        if (productExcelVO != null) {
            if (productExcelVO.getProductList() != null && productExcelVO.getProductList().size() != 0) {
                try {
                    List<String> productBaseCodeList = productService.selectBaseProductCodes();
                    if (insert) {
                        result = productService.styleInsert(productExcelVO,productBaseCodeList);
                    } else {
                        result = productService.styleUpdate(productExcelVO,productBaseCodeList);
                    }
                    return getCorrectResult(result);
                } catch (Exception e) {
                    LogUtil.getLogger(SyncHelper.class).error(StringUtil.gsFormat("异常信息：", e.getMessage()));
                    return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
                }
            }
            return getCorrectResult(result);
        } else {
            return ResultGenerator.genFailResult("上传文档格式错误，请核对上传数据格式");
        }
    }

    private Result getProductInfoResult(ProductInfoExcelVO productInfoExcelVO) {
        int result = 0;
        if (productInfoExcelVO != null) {
            if (productInfoExcelVO.getProductInfoList() != null && productInfoExcelVO.getProductInfoList().size() != 0) {
                try {
                    result = productInfoService.sizeInsert(productInfoExcelVO);
                } catch (Exception e) {
                    LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                    return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
                }
            }
            return getCorrectResult(result);
        } else {
            return ResultGenerator.genFailResult("上传文档格式错误，请核对上传数据格式！");
        }

    }

    private int checkProductFeasure(ProductDimensionTraining productDimensionTraining) {
        if (StringUtils.isBlank(productDimensionTraining.getProductCode())) {
            return -2;
        }
        Condition condition = new Condition(ProductDimensionTraining.class);
        if (productDimensionTraining.getProductCode() != null) {
            //更新
            condition.createCriteria().andEqualTo("productCode", productDimensionTraining.getProductCode());
        }
        try {
            int count = productDimensionTrainingService.findCountByCondition(condition);
            return checkResult(count);
        } catch (Exception ex) {
            return UNKNOWN_EXCEPTION_CODE;
        }
    }

    private int checkColorCard(ColorCard colorCard) {
        int count = 0;
        if (StringUtils.isBlank(colorCard.getColorCardCode())) {
            //数据传输错误
            return DATA_TRANS_ERROR;
        }
        Condition condition = new Condition(ColorCard.class);
        try {
            if (colorCard.getId() != null) {
                //更新
                count += colorCardService.searchByCondition(colorCard);
            } else {
                //新增
                condition.createCriteria().orEqualTo("colorCardCode", colorCard.getColorCardCode())
                        .orEqualTo("colorCardName", colorCard.getColorCardName());
                count += colorCardService.findCountByCondition(condition);
            }

            return checkResult(count);
        } catch (Exception ex) {
            return UNKNOWN_EXCEPTION_CODE;
        }
    }

    private int checkItemData(ViewSelectItem viewSelectItem) {
        int count = 0;
        String name = viewSelectItem.getTableName();
        if (StringUtils.isBlank(name)) {
            return DATA_TRANS_ERROR;
            //数据传输错误
        }
        if (StringUtil.checkInjection(name) || name.length() > NAME_LENGET) {
            return -1;
        }
        try {
            if (viewSelectItem.getId() != null) {
                //更新
                count += viewSelectItemService.searchForUpdate(viewSelectItem, name);
            } else {
                //新增
                count += viewSelectItemService.searchForInsert(viewSelectItem, name);
            }
            return checkResult(count);
        } catch (Exception ex) {
            return UNKNOWN_EXCEPTION_CODE;
        }
    }

    private Result getColorCardResult(ColorCardVO colorCardVO) {
        int result;
        if (colorCardVO != null) {
            if (colorCardVO.getColorCards() != null && colorCardVO.getColorCards().size() != 0) {
                try {
                    result = colorCardService.leadInColorCard(colorCardVO);
                    if (result < 0) {
                        return ResultGenerator.genFailResult("共导入0行数据，色卡编码或名称不能重复");
                    } else {
                        return ResultGenerator.genSuccessResult(result);
                    }
                } catch (Exception e) {
                    LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                    return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
                }
            } else {
                return ResultGenerator.genFailResult("共导入0行数据，色卡编码或名称不能重复");
            }
        } else {
            return ResultGenerator.genFailResult("上传文档格式错误，请核对上传数据格式！");
        }
    }

    /**
     * 传入数据库操作结果 ，数据错误行数据
     *
     * @param result
     * @return
     */
    private Result getCorrectResult(Integer result) {
        if (result > 0) {
            return ResultGenerator.genSuccessResult(getImportResult(result));
        }else if(result == 0){
            return ResultGenerator.genFailResult("上传保存失败，此次没有写入任何数据");
        }else{
            return ResultGenerator.genFailResult("数据导入成功，属性同步到伯俊失败");
        }
    }

    /**
     * 验证时返回数据两种情况
     *
     * @param count
     * @return
     */
    public int checkResult(Integer count) {
        if (count > 0) {
            //code存在，更新数据
            return -3;
        }
        return 1;
    }

    /**
     * 封装返回导入的结果
     *
     * @param result
     * @return
     */
    private ImportVo getImportResult(Integer result) {
        ImportVo importVo = new ImportVo();
        importVo.setCount(result);
        importVo.setFlag(true);
        return importVo;
    }
    private List<ProductCategoryVO> productCategoryCode2TableName(List<ProductCategory> productCategoryList){
        List<ProductCategoryVO> ProductCategoryVOList = new ArrayList<>();
        if(productCategoryList!= null && !productCategoryList.isEmpty()){
            for(ProductCategory productCategory : productCategoryList){
                ProductCategoryVO productCategoryVO = new ProductCategoryVO();
                String tableName = StringUtil.removeUnderline(productCategory.getProductCategoryCode());
                productCategoryVO.setTableName(tableName);
                productCategoryVO.setProductCategoryCode(productCategory.getProductCategoryCode());
                productCategoryVO.setProductCategoryId(productCategory.getProductCategoryId());
                productCategoryVO.setCategoryName(productCategory.getCategoryName());
                ProductCategoryVOList.add(productCategoryVO);
            }
        }
        return ProductCategoryVOList;

    }

    public Result sync2Burgeon(ViewSelectItem viewSelectItem){
        //尺寸组同步到伯俊
        if(viewSelectItem.getTableName().toLowerCase().equals("select_size_group")){
            SyncSelectItemVO syncSelectItemVO = new SyncSelectItemVO();
            syncSelectItemVO.setSizeGroupId(viewSelectItem.getItemKey());
            syncSelectItemVO.setSizeGroupName(viewSelectItem.getItemValue());
            syncSelectItemVO.setDescription(viewSelectItem.getItemValue());
            List<SyncSelectItemVO> list =  new ArrayList<>();
            list.add(syncSelectItemVO);
            if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getSyncSizeGroup2Burgeon()), list)) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("更新成功，但是同步到其他系统失败！");
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 导出色卡列表excel数据
     * @param response
     * @param colorCardNameOrNo
     */
    @PostMapping("/downLoadColorCardExcel")
    public void downLoadColorCardExcel(HttpServletResponse response, @RequestParam("colorCardNameOrNo") String colorCardNameOrNo) {
        OutputStream os = null;
        try {
            List<ColorCardVO> list = productService.findByQueryCondition(colorCardNameOrNo);
            Workbook workbook = productService.exportColorCardExcel(list);
            String tableName = "色卡列表";
            ExcelUtil.downloadExcelWithStream(response,workbook,tableName);
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage());
            ex.printStackTrace();
        }finally {
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/download")
    public void download(HttpServletResponse response, HttpServletRequest request, @RequestParam("excelpath") String path) {
        File excelFile = new File(path);
        InputStream fis = null;
        OutputStream toClient = null;
        try {
            // 以流的形式下载文件。
            fis = new BufferedInputStream(new FileInputStream(excelFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            //如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("error".concat(excelFile.getName()), "UTF-8"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
        }
    }

    /**
     * 基础选项列表excel数据
     * @param response
     * @param itemCategory
     * @param itemSelect
     */
    @PostMapping("/downLoadItemExcel")
    public void downLoadItemExcel(HttpServletResponse response,
                                  @RequestParam("itemCategorys") String itemCategory,
                                    @RequestParam("itemSelects") String itemSelect) {

        OutputStream os = null;
        try {
            List<SelectItemVO> list = productService.findItemDataByQueryCondition(itemCategory,itemSelect);
            Workbook workbook = productService.exportItemDataExcel(list);
            String tableName = "基础选项列表";
            ExcelUtil.downloadExcelWithStream(response,workbook,tableName);
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage());
            ex.printStackTrace();
        }finally {
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<SyncProductInfoVO> getExtendProductList(List<SyncProductInfoVO> mainProductList,List<ProductAttribute> productAttributes,
                                                         List<SelectItemVO> selectItemVoList,List<Map<String,String>> sizeGroupList,
                                                         List<Map<String,String>> extendItemList,List<Map<String,String>> colorList){
        if(!ListUtil.isBlank(mainProductList) && !ListUtil.isBlank(productAttributes)){
            for(SyncProductInfoVO syncProductInfoVO : mainProductList){
                String productCode = syncProductInfoVO.getProduct_code();
                String productCategoryCode = syncProductInfoVO.getCate_dl_name();
                syncProductInfoVO.setStatus(syncProductInfoVO.getStatus()==1?1:2);
                List<ProductAttribute> productAttributeFilterList =productAttributes.stream().filter(a->a.getProductCode().equals(productCode)).collect(Collectors.toList());
                if(!ListUtil.isBlank(productAttributeFilterList) && productCategoryCode.equals(CommonConstant.COSTUMECATEGORYKEY)){
                    ProductAttribute salesDate = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("00007")).findFirst().orElse(null);
                    String salesDateString = salesDate == null?"":salesDate.getAttrValue()==null?"":salesDate.getAttrValue();
                    Date salesDated = StringUtils.isEmpty(salesDateString)?null:DateUtil.getDateByStrings(salesDateString,"yyyy-MM-dd","yyyy/MM/dd");
                    syncProductInfoVO.setSales_date(salesDated);
                    ProductAttribute year = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("00011")).findFirst().orElse(null);
                    syncProductInfoVO.setYear_name(year==null?"":year.getAttrValue()==null?"":year.getAttrValue());
                    ProductAttribute devNo = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("00014")).findFirst().orElse(null);
                    syncProductInfoVO.setDev_no(devNo==null?"":devNo.getAttrValue()==null?"":devNo.getAttrValue());
                    ProductAttribute materialNameTag = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("material_name_tag")).findFirst().orElse(null);
                    syncProductInfoVO.setMaterial_name_tag(materialNameTag==null?"":materialNameTag.getAttrValue()==null?"":materialNameTag.getAttrValue());
                    ProductAttribute styleNo = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("00015")).findFirst().orElse(null);
                    syncProductInfoVO.setStyle_no(styleNo==null?"":styleNo.getAttrValue()==null?"":styleNo.getAttrValue());
                    ProductAttribute modelNo = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("00016")).findFirst().orElse(null);
                    syncProductInfoVO.setModel_no(modelNo==null?"":modelNo.getAttrValue()==null?"":modelNo.getAttrValue());
                    ProductAttribute materialNo = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("00017")).findFirst().orElse(null);
                    syncProductInfoVO.setMaterial_no(materialNo==null?"":materialNo.getAttrValue()==null?"":materialNo.getAttrValue());
                    ProductAttribute materialName = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("material_name")).findFirst().orElse(null);
                    syncProductInfoVO.setMaterial_name(materialName==null?"":materialName.getAttrValue()==null?"":materialName.getAttrValue());
                    ProductAttribute colorName = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("00027")).findFirst().orElse(null);
                    syncProductInfoVO.setColor_name(colorName==null?"":colorName.getAttrValue()==null?"":colorName.getAttrValue());
                    ProductAttribute colourSystem = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("00025")).findFirst().orElse(null);
                    syncProductInfoVO.setColour_system(colourSystem==null?"":colourSystem.getAttrValue()==null?"":colourSystem.getAttrValue());
                    ProductAttribute colorCardName = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("color_card_name")).findFirst().orElse(null);
                    syncProductInfoVO.setColor_card_name(colorCardName==null?"":colorCardName.getAttrValue()==null?"":colorCardName.getAttrValue());
                    ProductAttribute setWorkgroupValueName = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("00003")).findFirst().orElse(null);
                    syncProductInfoVO.setWorkgroup_value_name(setWorkgroupValueName==null?"":setWorkgroupValueName.getAttrValue()==null?"":setWorkgroupValueName.getAttrValue());
                    syncProductInfoVO.setSize_group_name(getItemValue(productAttributeFilterList,selectItemVoList,"00008","select_size_group"));
                    syncProductInfoVO.setDev_season_name(getItemValue(productAttributeFilterList,selectItemVoList,"00019","select_dev_season"));
                    syncProductInfoVO.setLine_name(getItemValue(productAttributeFilterList,selectItemVoList,"00010","select_line"));
                    syncProductInfoVO.setIcicle_group_name(getItemValue(productAttributeFilterList,selectItemVoList,"00023","select_group"));
                    syncProductInfoVO.setNature_season_name(getItemValue(productAttributeFilterList,selectItemVoList,"00012","select_nature_season"));
                    syncProductInfoVO.setWave_name(getItemValue(productAttributeFilterList,selectItemVoList,"00021","select_wave"));
                    syncProductInfoVO.setBand_name(getItemValue(productAttributeFilterList,selectItemVoList,"00022","select_band"));
                }else{
                    String sizeGroupName = productCategoryCode.equals(CommonConstant.BOOKCATEGORYKEY)?"图书":(productCategoryCode.equals(CommonConstant.HOMEHOLDCATEGORYKEY)?"家具":"物料");
                    syncProductInfoVO.setDev_no("");
                    syncProductInfoVO.setStyle_no("");
                    syncProductInfoVO.setModel_no("");
                    syncProductInfoVO.setMaterial_no("");
                    syncProductInfoVO.setMaterial_name("");
                    syncProductInfoVO.setColor_name("");
                    syncProductInfoVO.setColour_system("");
                    syncProductInfoVO.setMaterial_name_tag("");
                    syncProductInfoVO.setColor_card_name("");
                    syncProductInfoVO.setSize_group_name(sizeGroupName);
                    syncProductInfoVO.setDev_season_name("");
                    syncProductInfoVO.setLine_name("");
                    syncProductInfoVO.setWorkgroup_value_name("");
                    syncProductInfoVO.setIcicle_group_name("");
                    syncProductInfoVO.setYear_name("");
                    syncProductInfoVO.setNature_season_name("");
                    syncProductInfoVO.setWave_name("");
                    syncProductInfoVO.setBand_name("");
                    syncProductInfoVO.setIcicle_group_name("");

                    if(productCategoryCode.equals(CommonConstant.MATERIALCATEGORYKEY)){
                        syncProductInfoVO.setExpenditure_category_name(getName(productAttributeFilterList,extendItemList,"expenditure_category"));
                        syncProductInfoVO.setSub_expenditure_category_name(getName(productAttributeFilterList,extendItemList,"sub_expenditure_category"));
                        syncProductInfoVO.setDepartment_name(getName(productAttributeFilterList,extendItemList,"material_department"));
                        ProductAttribute invoceShow = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("invoice_show")).findFirst().orElse(null);
                        String invoceShowString = invoceShow == null?"":invoceShow.getAttrValue()==null?"":invoceShow.getAttrValue();
                        syncProductInfoVO.setInvoice_show(invoceShowString.equals("1")?"N":"Y");
                        syncProductInfoVO.setIs_advanced_ord("N");
                        ProductAttribute packageMinCount = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("package_min_count")).findFirst().orElse(null);
                        syncProductInfoVO.setPck_minqty(packageMinCount==null?"":packageMinCount.getAttrValue()==null?"":packageMinCount.getAttrValue());
                        ProductAttribute maxApplyCountAttribute = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("max_apply_count")).findFirst().orElse(null);
                        int maxApplyCount = maxApplyCountAttribute==null?9999:(StringUtils.isEmpty(maxApplyCountAttribute.getAttrValue())?9999:maxApplyCountAttribute.getAttrValue().equals("0")?9999:Integer.parseInt(maxApplyCountAttribute.getAttrValue()));
                        syncProductInfoVO.setLimit_matapply_qty(maxApplyCount);
                        ProductAttribute expiredDate = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("expired_date")).findFirst().orElse(null);
                        String expiredDateString = expiredDate == null?"":expiredDate.getAttrValue()==null?"":expiredDate.getAttrValue();
                        syncProductInfoVO.setExpired_date(StringUtils.isEmpty(expiredDateString)?null:DateUtil.getDateByStrings(expiredDateString,"yyyy-MM-dd","yyyy/MM/dd"));
                    }
                    if(productCategoryCode.equals(CommonConstant.HOMEHOLDCATEGORYKEY)){
                        String color = "";
                        if(!ListUtil.isBlank(colorList)){
                            Map<String,String> colorFilterMap = colorList.stream().filter(a->a.get("product_code").equals(productCode)).findFirst().orElse(null);
                            if(colorFilterMap!=null){
                                color = colorFilterMap.get("color");
                            }
                        }
                        syncProductInfoVO.setColor(color);
                        ProductAttribute isAdvancedOrd = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals("is_customized")).findFirst().orElse(null);
                        String isAdvancedOrdString = isAdvancedOrd == null?"":isAdvancedOrd.getAttrValue()==null?"":isAdvancedOrd.getAttrValue();
                        syncProductInfoVO.setIs_advanced_ord(isAdvancedOrdString.equals("1")?"Y":"N");
                    }
                }
            }
        }
        return mainProductList;
    }

    private String getName(List<ProductAttribute> productAttributeFilterList,List<Map<String,String>> extendItemList,String defCode){
        String defName = "";
        ProductAttribute productAttribute = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals(defCode)).findFirst().orElse(null);
        if(productAttribute!=null){
            Map<String,String> sdepartmentFilterMap =  extendItemList.stream().filter(a->a.get("def_code").equals(defCode) && a.get("code").equals(productAttribute.getAttrValue())).findFirst().orElse(null);
            if(sdepartmentFilterMap!=null){
                defName = sdepartmentFilterMap.get("name");
            }
        }
        return defName;
    }

    private String getItemValue(List<ProductAttribute> productAttributeFilterList,List<SelectItemVO> selectItemVoList,String attrCode,String tableName){
        ProductAttribute productAttribute = productAttributeFilterList.stream().filter(a->a.getAttrCode().equals(attrCode)).findFirst().orElse(null);
        String code = productAttribute==null?"":productAttribute.getAttrValue();
        String codeValue = "";
        if(!StringUtils.isEmpty(code)){
            SelectItemVO lineItemVO = selectItemVoList.stream().filter(a->a.getTableName().equals(tableName) && a.getItemKey().equals(code)).findFirst().orElse(null);
            if(lineItemVO!=null){
                codeValue = lineItemVO.getItemValue()==null?"":lineItemVO.getItemValue();
            }
        }
        return codeValue;
    }

    private Map<String,Object> getFailResultMap(String errorMessage,String path,int count){
        Map<String,Object> resultMap = new HashMap<>();
        String resultMesssage= "";
        if(StringUtils.isEmpty(errorMessage)){
            resultMesssage = "成功导入"+count+"条数据,同步到其他系统失败";
        }else{
            resultMesssage = "成功导入"+count+"条数据，同步到其他系统失败，请下载查看导入错误的数据";
            resultMap.put("path",path);
        }
        resultMap.put("message",resultMesssage);
        resultMap.put("code","202");
        return resultMap;
    }

    private int checkProductCodeAndSku(ProductInfoSkuVO productInfo){
        String productCode = productInfo.getProductCode();
        String sku = productInfo.getSku();
        String size = productInfo.getSize();
        int checkResult = 1;
        List<String> matchList = Arrays.asList("AAA","BBB","CCC","DDD");
        if(productCode.length()>=3){
            String threeCharacter = productCode.substring(0,3);
            if(matchList.contains(threeCharacter)){
                if(!sku.equals(productCode.concat(size))){
                    checkResult = 0;
                }
            }else{
                if(productInfo.getProductCode().equals(CommonConstant.MATERIALCATEGORYKEY)){
                    if(!sku.equals(productCode)){
                        checkResult = 0;
                    }
                }
            }
        }
        return checkResult;
    }


    List<Integer> getIdList(String ids){
        List<Integer> idList = new ArrayList<>();
        String[] idArray = ids.split(",");
        if (idArray.length!=0){
            for (String id : idArray){
                idList.add(Integer.parseInt(id));
            }
        }
        return idList;
    }

    List<OaParamVO> getOaParamList(List<Map<String,String>> syncCostumeProductList,List<Map<String,String>> syncMaterialProductList,
                                   List<Map<String,String>> syncCategoryProductList){
        List<OaParamVO> oaParamVOList = new ArrayList<>();
        if(!ListUtil.isBlank(syncCostumeProductList)){
            for(Map<String,String> map : syncCostumeProductList){
                OaParamVO oaParamVO = new OaParamVO();
                oaParamVO.setUserid(1).setModetablename("uf_sku").setModeid(226);
                List<OaKeyValueVO> key_values = new ArrayList<>();
                if(map.get("sku")!=null){
                    key_values.add(new OaKeyValueVO("sku",map.get("sku")));
                }


            }
        }
        if(!ListUtil.isBlank(syncMaterialProductList)){

        }
        if(!ListUtil.isBlank(syncCategoryProductList)){

        }
        return oaParamVOList;
    }


    List<OaParamVO> getList(List<Map<String,Object>> list){
        List<OaParamVO> oaParamVOList = new ArrayList<>();
        if(!ListUtil.isBlank(list)){
            for(Map<String,Object> map : list){
                OaParamVO oaParamVO = new OaParamVO();
                oaParamVO.setUserid(1).setModetablename("uf_sku").setModeid(226);
                List<OaKeyValueVO> key_values = new ArrayList<>();
                map.forEach((k,v)->{
                    key_values.add(new OaKeyValueVO(k,v));
                });
                oaParamVO.setKey_values(key_values);
                oaParamVOList.add(oaParamVO);
            }
        }
        return oaParamVOList;
    }
}

