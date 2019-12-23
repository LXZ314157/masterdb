package com.icicle.masterdb.web;

import com.icicle.masterdb.authorization.annotation.Authorization;
import com.icicle.masterdb.config.SynConfigEntity;
import com.icicle.masterdb.core.AbstractPageController;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.core.ResultGenerator;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.Fabric;
import com.icicle.masterdb.model.Material;
import com.icicle.masterdb.model.MaterialAttribute;
import com.icicle.masterdb.pojo.bo.SyncMaterial;
import com.icicle.masterdb.pojo.vo.FabricVO;
import com.icicle.masterdb.pojo.vo.MaterialAttributeListVO;
import com.icicle.masterdb.pojo.vo.MaterialDetailVO;
import com.icicle.masterdb.pojo.vo.MaterialVO;
import com.icicle.masterdb.service.FabricService;
import com.icicle.masterdb.service.MaterialAttributeService;
import com.icicle.masterdb.service.MaterialService;
import com.icicle.masterdb.service.ViewMaterialService;
import com.icicle.masterdb.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author liurenhua
 */
@Controller
@RequestMapping("/material")
@Authorization
public class MaterialController extends AbstractPageController {
    /**
     * 原材料显示的列数
     */
    private final int TABLE_COL_NUM = 8;
    @Resource
    private MaterialService materialService;
    @Resource
    private SynConfigEntity synConfigEntity;
    @Resource
    private ViewMaterialService viewMaterialService;
    @Resource
    private MaterialAttributeService materialAttributeService;
    @Resource
    private FabricService fabricService;


    public MaterialController() {
        super("material");
    }

    @GetMapping("/material")
    public String material(Map<String, Object> map, HttpServletRequest request) {

        map.put("home", LanguageUtil.getText(1560));
        map.put("module", LanguageUtil.getText(1000));
        map.put("title", LanguageUtil.getText(1559));
        map.put("allMaterial", LanguageUtil.getText(1552));
        map.put("notUpdateMaterial", LanguageUtil.getText(1551));
        map.put("materialCode", LanguageUtil.getText(1522));
        map.put("materialName", LanguageUtil.getText(1523));
        map.put("dlName", LanguageUtil.getText(1524));
        map.put("zlName", LanguageUtil.getText(1525));
        map.put("xlName", LanguageUtil.getText(1526));
        map.put("uom", LanguageUtil.getText(1175));
        map.put("status", LanguageUtil.getText(1025));
        map.put("lastSynDate", LanguageUtil.getText(1016));
        map.put("operate", LanguageUtil.getText(1014));

        return this.freeMarkerViewResult("material", map, request);
    }

    @GetMapping("/fabriclist")
    public String fabricList(Map<String, Object> map, HttpServletRequest request) {
        map.put("home", LanguageUtil.getText(1560));
        map.put("module", LanguageUtil.getText(1000));
        map.put("title", LanguageUtil.getText(1556));
        map.put("addFabric", LanguageUtil.getText(1555));
        map.put("fabricCode", LanguageUtil.getText(1536));
        map.put("fabricName", LanguageUtil.getText(1537));
        map.put("lastUpdatedDate", LanguageUtil.getText(1097));
        map.put("operate", LanguageUtil.getText(1014));
        map.put("sure", LanguageUtil.getText(1007));
        map.put("select", LanguageUtil.getText(1059));
        map.put("change", LanguageUtil.getText(1252));
        map.put("remove", LanguageUtil.getText(1182));
        map.put("downLoadTemplate", LanguageUtil.getText(1054));
        return this.freeMarkerViewResult("fabric", map, request);
    }

    @GetMapping("/updatematerial")
    public String updateMaterial(Map<String, Object> map, HttpServletRequest request) {
        map.put("home", LanguageUtil.getText(1560));
        map.put("module", LanguageUtil.getText(1000));
        map.put("title", LanguageUtil.getText(1556));
        map.put("materialDetail", LanguageUtil.getText(1000));
        map.put("basicMessage", LanguageUtil.getText(1090));
        map.put("materialCode", LanguageUtil.getText(1522));
        map.put("materialName", LanguageUtil.getText(1523));
        map.put("erpStatus", LanguageUtil.getText(1025));
        map.put("userStatus", LanguageUtil.getText(1022));
        map.put("dlName", LanguageUtil.getText(1528));
        map.put("zlName", LanguageUtil.getText(1529));
        map.put("xlName", LanguageUtil.getText(1530));
        map.put("uom", LanguageUtil.getText(1359));
        map.put("samplePrice", LanguageUtil.getText(1531));
        map.put("productPrice", LanguageUtil.getText(1532));

        return this.freeMarkerViewResult("attribute", map, request);
    }

    @PostMapping("/list")
    @ResponseBody
    public DataTableRecord list(@RequestParam(value = "sEcho") String sEcho,
                                @RequestParam(value = "iDisplayStart") Integer pageIndex,
                                @RequestParam(value = "iDisplayLength") Integer pageSize,
                                @RequestParam(value = "sSearch") String sSearch,
                                @RequestParam(value = "type") Integer type,
                                @RequestParam(value = "iSortCol_0") Integer sortCol,
                                @RequestParam(value = "sSortDir_0") String sortDir) {
        if (sortCol == null || sortCol < 0 || sortCol > TABLE_COL_NUM) {
            sortCol = 0;
        } else {
            sortCol--;
        }

        DataTableRecord dataTableRecord = viewMaterialService.listMaterial(sEcho, pageIndex, pageSize, sSearch, type, sortCol, sortDir);
        return dataTableRecord;
    }

    @PostMapping("/createdatables")
    @ResponseBody
    public DataTableRecord createDatables(@RequestParam(value = "sEcho") String sEcho,
                                          @RequestParam(value = "iDisplayStart") Integer pageIndex,
                                          @RequestParam(value = "iDisplayLength") Integer pageSize,
                                          @RequestParam(value = "sSearch") String sSearch,
                                          @RequestParam(value = "sSortDir_0") String sortDir,
                                          @RequestParam(value = "iSortCol_0") Integer sortCol) {

        DataTableRecord dataTables = fabricService.createDataTables(sEcho, pageIndex, pageSize, sSearch, sortDir, sortCol);
        return dataTables;
    }

    @PostMapping("/synmaterial")
    @ResponseBody
    public Result synMaterial(@RequestParam("materialCodeList") String materialArr) {
        if (StringUtils.isBlank(materialArr)) {
            return ResultGenerator.genFailResult("传入数据不能为空");
        }

        String[] materials = materialArr.split(",");
        SyncMaterial syncInfo = materialService.getSyncMaterial(materials);
        boolean success = SyncHelper.synData(synConfigEntity.getHost()
                .concat(synConfigEntity.getMaterialUpdateService()), syncInfo);
        if (success) {
            try {
                int count = materialService.updateSyncStatus(materials);
                if (count > 0) {
                    return ResultGenerator.genSuccessResult();
                } else {
                    return ResultGenerator.genFailResult("批量同步成功，但更改本地状态失败");
                }
            } catch (Exception e) {
                LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                return ResultGenerator.genFailResult("批量同步失败，请您稍后再试");
            }
        } else {
            return ResultGenerator.genFailResult("批量同步失败，请您稍后再试");
        }
    }

    @GetMapping("/findattributebycode")
    @ResponseBody
    public Result findAttributeByCode(@RequestParam("materialCode") String materialCode) {
        if (StringUtils.isBlank(materialCode)) {
            return ResultGenerator.genFailResult("请输入有效的材料编码");
        }
        try {
            MaterialDetailVO materialDetailVO = viewMaterialService.getMaterialDetailAndAttribute(materialCode);
            return ResultGenerator.genSuccessResult(materialDetailVO);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("/updateattribute")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result updateAttribute(@RequestBody MaterialAttributeListVO materialAttributeListVO) {
        String materialCode = materialAttributeListVO.getMaterialCode();
        if (StringUtils.isBlank(materialCode)) {
            return ResultGenerator.genFailResult("物料编码不能为空");
        }
        List<MaterialAttribute> materialAttributeList = materialAttributeListVO.getMaterialAttributeList();
        try {
            Condition condition = new Condition(Material.class);
            condition.createCriteria().andEqualTo("materialCode", materialCode);
            if (materialService.findCountByCondition(condition) <= 0) {
                return ResultGenerator.genFailResult("此物料编码不存在");
            }
            for (MaterialAttribute materialAttribute : materialAttributeList) {
                materialAttribute.setMaterialCode(materialCode);
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage());
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }

        int count = materialAttributeService.updateMaterialAttribute(materialAttributeList, materialCode);
        if (count > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("此面料编码不存在");
        }
    }

    @PutMapping("/updatefabric")
    @ResponseBody
    public Result updateFabric(@RequestBody Fabric fabric) {

        String message = checkFabric(fabric, true);
        if (message != null) {
            return ResultGenerator.genFailResult(message);
        }

        try {
            fabric.setLastUpdateBy(SessionManager.getLoginName());
            fabric.setLastUpdateDate(DateUtil.now());
            int count = fabricService.updateFabric(fabric);
            fabricService.updateProductFabric(fabric);
            if (count > 0) {
                List<Fabric> fabrics = new ArrayList<>();
                fabrics.add(fabric);
                boolean success = SyncHelper.synData(synConfigEntity.getHost().concat(
                        synConfigEntity.getFabricUpgradeService()), fabrics);
                if (success) {
                    return ResultGenerator.genSuccessResult();
                } else {
                    return ResultGenerator.genFailResult("更新面料成功，但是同步到伯俊失败");
                }
            } else {
                return ResultGenerator.genFailResult("更新面料失败，原因可能是此面料不存在");
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("/addfabric")
    @ResponseBody
    public Result addFabric(@RequestBody Fabric fabric) {
        String message = checkFabric(fabric, false);
        if (message != null) {
            return ResultGenerator.genFailResult(message);
        }
        try {
            fabric.setCreatedBy(SessionManager.getLoginName());
            fabric.setCreatedDate(DateUtil.now());
            fabric.setLanguage(SessionManager.getLanguage());
            int count = fabricService.saveFabric(fabric);
            if (count > 0) {
                List<Fabric> fabrics = new ArrayList<>();
                fabrics.add(fabric);
                boolean success = SyncHelper.synData(synConfigEntity.getHost().concat(
                        synConfigEntity.getFabricUpgradeService()), fabrics);
                if (success) {
                    return ResultGenerator.genSuccessResult();
                } else {
                    return ResultGenerator.genFailResult("添加面料成功，但是同步到伯俊失败");
                }
            } else {
                return ResultGenerator.genFailResult("添加面料失败");
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }


    @GetMapping("/findfabricbyid")
    @ResponseBody
    public Result findFabricById(Integer id) {
        if (id == null) {
            return ResultGenerator.genFailResult("您没有提供面料Id，获取信息失败");
        }
        Fabric fabric;
        try {
            fabric = fabricService.findById(id);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(fabric);
    }

    @PostMapping("/leadfabricexcel")
    @ResponseBody
    public Result leadFabricExcel(@RequestParam("uploadfile") MultipartFile file) {
        try {
            if (file == null || !ExcelUtil.isExcelFile(file.getInputStream())) {
                return ResultGenerator.genFailResult("此文件不是excel文件");
            }
        } catch (IOException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        }
        int count;

        try {
            List<Fabric> fabricList = fabricService.parseExcel(file.getInputStream());
            if (ListUtil.isBlank(fabricList)) {
                return ResultGenerator.genFailResult("此文档没有可用数据");
            } else {
                count = fabricService.saveList(fabricList);
                if (count > 0) {
                    boolean success = SyncHelper.synData(synConfigEntity.getHost().concat(
                            synConfigEntity.getFabricUpgradeService()), fabricList);
                    if (success) {
                        return ResultGenerator.genSuccessResult(count);
                    } else {
                        return ResultGenerator.genFailResult("添加面料成功，但是同步到伯俊失败");
                    }
                } else if (count == -1) {
                    boolean success = SyncHelper.synData(synConfigEntity.getHost().concat(
                            synConfigEntity.getFabricUpgradeService()), fabricList);
                    if (success) {
                        return ResultGenerator.genSuccessResult(count);
                    } else {
                        return ResultGenerator.genFailResult("修改面料成功，但是同步到伯俊失败");
                    }
                } else {
                    return ResultGenerator.genFailResult("此文档数据已经被导入");
                }
            }
        } catch (IOException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genSuccessResult();
        } catch (InvalidFormatException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult("文件不是excel文件");
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }

    }


    /**
     * 这个方法用于在更新和插入面料的时候校验客户端上传过来的数据的
     *
     * @param fabric   面料实体
     * @param isUpdate 是否为更新  更新和插入操作 ，数据校验条件是不一样的
     * @return 如果没有错误则会返回null  有错误 则返回一条错误信息
     */
    private String checkFabric(Fabric fabric, boolean isUpdate) {
        if (fabric == null) {
            return "更新失败";
        }

        if (fabric.getId() == null && isUpdate) {
            return "面料Id不能为空";
        }

        if (StringUtils.isBlank(fabric.getFabricCode())) {
            return "面料编码不能为空";
        }

        if (StringUtils.isBlank(fabric.getFabricName())) {
            return "面料名称不能为空";
        }
        Condition condition = new Condition(Fabric.class);

        /**
         * 更新的时候的数据校验条件和插入的事后的数据校验条件是不一样的
         * 更新的时候要校验传来的编码和名称均不能存在
         * 而插入的时候，传来的编码，名称和除了本条以外的其他数据不能相同的
         */
        if (isUpdate) {
            condition.createCriteria().andNotEqualTo("id", fabric.getId());
            condition.and(condition.createCriteria().andEqualTo("fabricCode",
                    fabric.getFabricCode()));
        } else {
            condition.createCriteria().andEqualTo("fabricCode", fabric.getFabricCode());
        }
        try {
            int count = fabricService.findCountByCondition(condition);
            if (count > 0) {
                return "面料名或者面料编码已经存在，请更换面料名或者面料编码";
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return LanguageUtil.getText(-999);
        }

        return null;
    }

    /**
     * 导出原材料列表excel数据
     * @param response
     * @param materialNameOrNo
     */
    @PostMapping("/downLoadMaterialExcel")
    public void downLoadMaterialExcel(HttpServletResponse response,@RequestParam("materialNameOrNo") String materialNameOrNo) {
        OutputStream os = null;
        try {
            List<MaterialVO> list = materialService.findByQueryCondition(materialNameOrNo);
            if(!ListUtil.isBlank(list)){
                List<Map<String,Object>> catalogList = materialService.getCatalogList();
                List<Map<String,Object>> materialTypeList = materialService.getMaterialTypeList();
                List<Map<String,Object>> currencyList = materialService.getCurrencyList();
                for(MaterialVO materialVO : list){
                    if(!ListUtil.isBlank(catalogList)){
                        Map<String,Object> dlMap = catalogList.stream().filter(a->a.get("catalog_no").toString().equals(materialVO.getCateDl()) && a.get("catalog_level").toString().equals("1")).findFirst().orElse(null);
                        if(dlMap!=null){
                            materialVO.setCateDl(dlMap.get("catalog_desc").toString());
                        }
                        Map<String,Object> zlMap = catalogList.stream().filter(a->a.get("catalog_no").toString().equals(materialVO.getCateZl()) && a.get("catalog_level").toString().equals("2")).findFirst().orElse(null);
                        if(zlMap!=null){
                            materialVO.setCateZl(zlMap.get("catalog_desc").toString());
                        }
                        Map<String,Object> xlMap = catalogList.stream().filter(a->a.get("catalog_no").toString().equals(materialVO.getCateXl()) && a.get("catalog_level").toString().equals("3")).findFirst().orElse(null);
                        if(xlMap!=null){
                            materialVO.setCateXl(xlMap.get("catalog_desc").toString());
                        }
                    }
                    if(!ListUtil.isBlank(materialTypeList)){
                        Map<String,Object> typeMap = materialTypeList.stream().filter(a->a.get("material_type").toString().equals(materialVO.getMaterialType())).findFirst().orElse(null);
                        if(typeMap!=null){
                            materialVO.setMaterialType(typeMap.get("material_type_desc").toString());
                        }
                    }
                    if(!ListUtil.isBlank(currencyList)){
                        Map<String,Object> currencyMap = currencyList.stream().filter(a->a.get("currency_code").toString().equals(materialVO.getCurrencyName())).findFirst().orElse(null);
                        if(currencyMap!=null){
                            materialVO.setCurrencyName(currencyMap.get("currency_name").toString());
                        }
                    }
                }
            }
            Workbook workbook = materialService.exportMaterialExExcel(list);
            String tableName = "原材料列表";
            ExcelUtil.downloadExcelWithStream(response,workbook,tableName);
        } catch (Exception ex) {
            LogUtil.getLogger(HrController.class).error(ex.getMessage());
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

    /**
     * 导出面料列表excel数据
     * @param response
     * @param fabiricNameOrNo
     */
    @PostMapping("/downLoadFabricExcel")
    public void downLoadFabricExcel(HttpServletResponse response,@RequestParam("fabiricNameOrNo") String fabiricNameOrNo) {
        OutputStream os = null;
        try {
            List<FabricVO> list = fabricService.findByQueryCondition(fabiricNameOrNo);
            Workbook workbook = fabricService.exportFabricExExcel(list);
            String tableName = "面料列表";
            String excelFileName=new String(tableName.getBytes("utf-8"),"iso8859-1");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("content-disposition", "attachment;filename="+excelFileName+".xlsx");
            os = response.getOutputStream();
            workbook.write(os);
        } catch (Exception ex) {
            LogUtil.getLogger(HrController.class).error(ex.getMessage());
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


}