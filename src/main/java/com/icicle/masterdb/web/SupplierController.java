package com.icicle.masterdb.web;

import com.icicle.masterdb.authorization.annotation.Authorization;
import com.icicle.masterdb.config.SynConfigEntity;
import com.icicle.masterdb.core.AbstractPageController;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.core.ResultGenerator;
import com.icicle.masterdb.model.Supplier;
import com.icicle.masterdb.pojo.vo.SupplierVO;
import com.icicle.masterdb.service.SupplierService;
import com.icicle.masterdb.service.ViewSupplierListService;
import com.icicle.masterdb.util.LanguageUtil;
import com.icicle.masterdb.util.ListUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.SyncHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author lvxuezhan
 * @version 2019-10-09 11:12
 */
@Controller
@RequestMapping("/supplier")
@Authorization
public class SupplierController extends AbstractPageController {

    @Resource
    private SupplierService supplierService;

    @Resource
    private ViewSupplierListService viewSupplierListService;

    @Resource
    private SynConfigEntity synConfigEntity;

    /**
     * 返回页面
     */
    public SupplierController() {
        super("supplier");
    }

    @GetMapping("/supplierlist")
    public String supplierlist(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("supplierlist", map, request);
    }

    @PostMapping("/supplierlist")
    @ResponseBody
    public DataTableRecord supplierlist(@RequestParam("sEcho") String sEcho,
                                        @RequestParam("iDisplayStart") Integer pageIndex,
                                        @RequestParam("iDisplayLength") Integer pageSize,
                                        @RequestParam("supplierSourceCode") String supplierSourceCode,
                                        @RequestParam("supplierCodeOrName") String supplierCodeOrName,
                                        @RequestParam("supplierStatus") String supplierStatus) {
        DataTableRecord dataTableRecord = supplierService.getSupplierList(sEcho, pageIndex, pageSize, supplierSourceCode,supplierCodeOrName,supplierStatus);
        return dataTableRecord;
    }


    @GetMapping("/viewsupplierdetail")
    public String viewsupplierdetail(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("viewsupplierdetail", map, request);
    }

    @GetMapping("/viewsupplierdetailinfo")
    @ResponseBody
    public Result viewsupplierdetailinfo(@RequestParam("supplierCode") String supplierCode) {
        try{
            SupplierVO supplierVO = viewSupplierListService.findSupplierInfoBySupplierCode(supplierCode);
            if(supplierVO==null){
                return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
            }else{
                return ResultGenerator.genSuccessResult(supplierVO);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("/downLoadSupplierExcel")
    public void downLoadSupplierExcel(HttpServletResponse response,
                                   @RequestParam("supplierCodeOrNames") String supplierCodeOrName,
                                   @RequestParam("supplierStatuss") String supplierStatus) {
        OutputStream os = null;
        try {
            List<SupplierVO> list = viewSupplierListService.findByQueryCondition(supplierCodeOrName,supplierStatus);
            Workbook workbook = supplierService.exportSupplierExcel(list);
            String tableName = "供应商信息列表";
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


    @PostMapping("/syncsupplier")
    @ResponseBody
    public Result syncsupplier(@RequestParam("supplierCodes") String supplierCodes) {
        if (supplierCodes != null && supplierCodes.length() == 0) {
            return ResultGenerator.genFailResult("请选择同步数据");
        }
        try{
            String[] supplierCodeArray = supplierCodes.split(",");
            List<String> supplierCodeList = Arrays.asList(supplierCodeArray);
            StringBuffer sb = new StringBuffer();
            List<Supplier> supplierBurgeonList = supplierService.getBurgeonSupplier(supplierCodeList);
            if(!ListUtil.isBlank(supplierBurgeonList)) {
                if(SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getSupplierSync2Burgeon()),supplierBurgeonList)){
                    sb.append("同步到伯俊成功\n");
                }else{
                    sb.append("同步到伯俊失败\n");
                }
            }
            if(!ListUtil.isBlank(supplierBurgeonList)) {
                if(SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getSupplierSync2WMS()),supplierBurgeonList)){
                    sb.append("同步到WMS成功\n");
                }else{
                    sb.append("同步到WMS失败\n");
                }
            }
            if(!ListUtil.isBlank(supplierBurgeonList)) {
                if(SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getSupplierSync2RFID()),supplierBurgeonList)){
                    sb.append("同步到RFID成功\n");
                }else{
                    sb.append("同步到RFID失败\n");
                }
            }
            if (!StringUtils.isEmpty(sb.toString())) {
                return ResultGenerator.genSuccessResult(sb.toString());
            } else {
                return ResultGenerator.genFailResult("批量同步失败，请您稍后再试");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("批量同步失败，请您稍后再试");
        }
    }



}
