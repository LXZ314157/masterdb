package com.icicle.masterdb.web;

import com.icicle.masterdb.authorization.annotation.Authorization;
import com.icicle.masterdb.config.SynConfigEntity;
import com.icicle.masterdb.core.AbstractPageController;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.core.ResultGenerator;
import com.icicle.masterdb.pojo.vo.*;
import com.icicle.masterdb.service.*;
import com.icicle.masterdb.util.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author lvxuezhan
 */
@Controller
@RequestMapping("/finance")
@Authorization
public class FinanceController extends AbstractPageController {

    @Resource
    private CostcenterService costcenterService;

    @Resource
    private ViewCostcenterListService viewCostcenterListService;

    @Resource
    private ResponsibilitycenterService responsibilitycenterService;

    @Resource
    private ExpenditureCategoryService expenditureCategoryService;

    @Resource
    private SubExpenditureCategoryService subExpenditureCategoryService;

    @Resource
    private SynConfigEntity synConfigEntity;



    /**
     * 返回页面
     */
    public FinanceController() {
        super("finance");
    }

    @GetMapping("/costcenterlist")
    public String costcenterlist(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("costcenterlist", map, request);
    }

    @PostMapping("/costcenterlist")
    @ResponseBody
    public DataTableRecord costcenterlist(@RequestParam("sEcho") String sEcho,
                                          @RequestParam("iDisplayStart") Integer pageIndex,
                                          @RequestParam("iDisplayLength") Integer pageSize,
                                          @RequestParam("costcenterId") String costcenterId,
                                          @RequestParam("lanCode") String lanCode) {
        DataTableRecord dataTableRecord = costcenterService.getCostCenterList(sEcho, pageIndex,  pageSize, costcenterId, lanCode);
        return dataTableRecord;
    }

    @GetMapping("/costcenterinfo")
    public String costcenterinfo(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("costcenterinfo", map, request);
    }

    @PostMapping("/costcenterinfo")
    @ResponseBody
    public Result costcenterinfo(@RequestParam("costcenterId") String costcenterId,@RequestParam("lanCode") String lanCode) {
        try{
            CostcenterVO costcenterVO = viewCostcenterListService.findCostcenterInfoByCostcenterId(costcenterId,lanCode);
            if(org.springframework.util.StringUtils.isEmpty(costcenterVO)){
                return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
            }else{
                return ResultGenerator.genSuccessResult(costcenterVO);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }


    @GetMapping("/resplist")
    public String resplist(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("resplist", map, request);
    }

    @PostMapping("/resplist")
    @ResponseBody
    public DataTableRecord resplist(@RequestParam("sEcho") String sEcho,
                                    @RequestParam("iDisplayStart") Integer pageIndex,
                                    @RequestParam("iDisplayLength") Integer pageSize,
                                    @RequestParam("respcenterId") String respcenterId,
                                    @RequestParam("lanCode") String lanCode) {
        DataTableRecord dataTableRecord = responsibilitycenterService.getRespList(sEcho, pageIndex, pageSize, respcenterId, lanCode);
        return dataTableRecord;
    }


    @GetMapping("/respcenterinfo")
    public String respcenterinfo(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("respcenterinfo", map, request);
    }

    @PostMapping("/respcenterinfo")
    @ResponseBody
    public Result respcenterinfo(@RequestParam("respcenterId") String respcenterId,@RequestParam("lanCode") String lanCode) {
        try{
            RespCenterVO respCenterVO = responsibilitycenterService.findRespcenterInfoByRespcenterId(respcenterId,lanCode);
            if(org.springframework.util.StringUtils.isEmpty(respCenterVO)){
                return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
            }else{
                return ResultGenerator.genSuccessResult(respCenterVO);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @GetMapping("/exinfo")
    public String exinfo(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("exinfo", map, request);
    }

    @PostMapping("/exinfo")
    @ResponseBody
    public Result exinfo(@RequestParam("excategoryId") String excategoryId,@RequestParam("lanCode") String lanCode) {
        try{
            ExpenditureCategoryVO expenditureCategoryVO = expenditureCategoryService.findExInfoByExcategoryId(excategoryId,lanCode);
            if(org.springframework.util.StringUtils.isEmpty(expenditureCategoryVO)){
                return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
            }else{
                return ResultGenerator.genSuccessResult(expenditureCategoryVO);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @GetMapping("/subexinfo")
    public String subexinfo(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("subexinfo", map, request);
    }

    @PostMapping("/subexinfo")
    @ResponseBody
    public Result subexinfo(@RequestParam("subExcategoryId") String subExcategoryId,@RequestParam("lanCode") String lanCode) {
        try{
            SubExpenditureCategoryVO subExpenditureCategoryVO = subExpenditureCategoryService.findsubExInfoBySubExcategoryId(subExcategoryId,lanCode);
            if(org.springframework.util.StringUtils.isEmpty(subExpenditureCategoryVO)){
                return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
            }else{
                return ResultGenerator.genSuccessResult(subExpenditureCategoryVO);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }


    @GetMapping("/exlist")
    public String exlist(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("exlist", map, request);
    }

    @PostMapping("/exlist")
    @ResponseBody
    public DataTableRecord exlist(@RequestParam("sEcho") String sEcho,
                                          @RequestParam("iDisplayStart") Integer pageIndex,
                                          @RequestParam("iDisplayLength") Integer pageSize,
                                          @RequestParam("excategoryId") String excategoryId,
                                          @RequestParam("lanCode") String lanCode) {
        DataTableRecord dataTableRecord = expenditureCategoryService.getExList(sEcho, pageIndex, pageSize, excategoryId, lanCode);
        return dataTableRecord;
    }

    @GetMapping("/subexlist")
    public String subexlist(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("subexlist", map, request);
    }

    @PostMapping("/subexlist")
    @ResponseBody
    public DataTableRecord subexlist(@RequestParam("sEcho") String sEcho,
                                          @RequestParam("iDisplayStart") Integer pageIndex,
                                          @RequestParam("iDisplayLength") Integer pageSize,
                                          @RequestParam("subExcategoryId") String subExcategoryId,
                                          @RequestParam("lanCode") String lanCode) {
        DataTableRecord dataTableRecord = subExpenditureCategoryService.getSubExList(sEcho, pageIndex, pageSize, subExcategoryId, lanCode);
        return dataTableRecord;
    }


    @PostMapping("/syncexlist")
    @ResponseBody
    public Result syncexlist(@RequestParam("excategoryIds") String excategoryIds,@RequestParam("lanCode") String lanCode) {
        if (excategoryIds != null && excategoryIds.length() == 0) {
            return ResultGenerator.genFailResult("请选择同步数据");
        }
        String [] excategoryIdList = excategoryIds.split(",");
        List<SyncPropertyVO> syncInfo = expenditureCategoryService.getSyncExCategoryInfo(excategoryIdList,lanCode);
        if(!ListUtil.isBlank(syncInfo)){
            boolean success = SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getPropertySyncBurgeon()), syncInfo);
            if (success) {
                return ResultGenerator.genSuccessResult("同步到伯俊成功");
            } else {
                return ResultGenerator.genFailResult("同步到伯俊失败");
            }
        }else{
            return ResultGenerator.genFailResult("批量同步失败，请您稍后再试");
        }
    }


    @PostMapping("/syncsubexlist")
    @ResponseBody
    public Result syncsubexlist(@RequestParam("subExcategoryIds") String subExcategoryIds,@RequestParam("lanCode") String lanCode) {
        if (subExcategoryIds != null && subExcategoryIds.length() == 0) {
            return ResultGenerator.genFailResult("请选择同步数据");
        }
        String []subExcategoryIdList = subExcategoryIds.split(",");
        try {
            List<SyncPropertyVO> syncInfo = subExpenditureCategoryService.getSyncSubExCategoryInfo(subExcategoryIdList,lanCode);
            if(!ListUtil.isBlank(syncInfo)){
                boolean success = SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getPropertySyncBurgeon()), syncInfo);
                if (success) {
                    return ResultGenerator.genSuccessResult("同步到伯俊成功");
                } else {
                    return ResultGenerator.genFailResult("同步到伯俊失败");
                }
            }else{
                return ResultGenerator.genFailResult("批量同步失败，请您稍后再试");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }

    }


    @PostMapping("/syncostcenter")
    @ResponseBody
    public Result syncostcenter(@RequestParam("costcenterIds") String costcenterIds,@RequestParam("lanCode") String lanCode) {
        if (costcenterIds != null && costcenterIds.length() == 0) {
            return ResultGenerator.genFailResult("请选择同步数据");
        }
        String [] costcenterIdList = costcenterIds.split(",");
        try {
            List<CostcenterVO> syncInfo = costcenterService.getSyncCostcenterInfo(costcenterIdList,lanCode);
            if(!ListUtil.isBlank(syncInfo)){
                //同步到汇联易、EHR
                boolean Flag = SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getCostcenterSync()), syncInfo);
                if(Flag){
                    return ResultGenerator.genSuccessResult("同步到汇联易、EHR成功");
                }else{
                    return ResultGenerator.genFailResult("同步到汇联易、EHR失败");
                }
            }else{
                return ResultGenerator.genFailResult("批量同步失败，请您稍后再试");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }


    /**
     * 导出成本中心列表excel数据
     * @param response
     * @param costcenterIdOrName
     * @param lanCode
     */
    @PostMapping("/downLoadCostcenterExcel")
    public void downLoadCostcenterExcel(HttpServletResponse response,
                                  @RequestParam("costcenterIdOrName") String costcenterIdOrName,
                                  @RequestParam("lanCodes") String lanCode) {
        OutputStream os = null;
        try {
            List<CostcenterVO> list = viewCostcenterListService.findByQueryCondition(costcenterIdOrName,lanCode);
            Workbook workbook = costcenterService.exportCostcenterExcel(list);
            String tableName = "成本中心列表";
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

    /**
     * 导出责任中心列表excel数据
     * @param response
     * @param respcenterIdOrName
     * @param lanCode
     */
    @PostMapping("/downLoadRespcenterExcel")
    public void downLoadRespcenterExcel(HttpServletResponse response,
                                  @RequestParam("respcenterIdOrName") String respcenterIdOrName,
                                  @RequestParam("lanCodes") String lanCode) {
        OutputStream os = null;
        try {
            List<RespCenterVO> list = responsibilitycenterService.findByQueryCondition(respcenterIdOrName,lanCode);
            Workbook workbook = responsibilitycenterService.exportRespcenterExcel(list);
            String tableName = "责任中心列表";
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

    /**
     * 导出开支类别列表excel数据
     * @param response
     * @param exIdOrName
     * @param lanCode
     */
    @PostMapping("/downLoadExExcel")
    public void downLoadExExcel(HttpServletResponse response,
                                  @RequestParam("exIdOrName") String exIdOrName,
                                  @RequestParam("lanCodes") String lanCode) {
        OutputStream os = null;
        try {
            List<ExpenditureCategoryVO> list = expenditureCategoryService.findByQueryCondition(exIdOrName,lanCode);
            Workbook workbook = expenditureCategoryService.exportExExcel(list);
            String tableName = "开支类别列表";
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

    /**
     * 导出开支子类列表excel数据
     * @param response
     * @param subExIdOrName
     * @param lanCode
     */
    @PostMapping("/downLoadsubExExcel")
    public void downLoadsubExExcel(HttpServletResponse response,
                                  @RequestParam("subExIdOrName") String subExIdOrName,
                                  @RequestParam("lanCodes") String lanCode) {
        OutputStream os = null;
        try {
            List<SubExpenditureCategoryVO> list = subExpenditureCategoryService.findByQueryCondition(subExIdOrName,lanCode);
            Workbook workbook = subExpenditureCategoryService.exportExExcel(list);
            String tableName = "开支类别列表";
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

}





