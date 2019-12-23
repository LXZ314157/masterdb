package com.icicle.masterdb.web;

import com.icicle.masterdb.authorization.annotation.Authorization;
import com.icicle.masterdb.config.SynConfigEntity;
import com.icicle.masterdb.core.*;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.*;
import com.icicle.masterdb.pojo.vo.*;
import com.icicle.masterdb.service.*;
import com.icicle.masterdb.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lvxuezhan
 */
@Controller
@RequestMapping("/hr")
@Authorization
public class HrController extends AbstractPageController {
    @Resource
    private StaffService staffService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private ViewStaffListService viewStaffListService;

    @Resource
    private ViewDepartmentMatrixListService viewDepartmentMatrixListService;

    @Resource
    private PostService postService;

    @Resource
    private ViewPostListService viewPostListService;

    @Resource
    private LanguageService languageService;

    @Resource
    private CompanyService companyService;

    @Resource
    private SynConfigEntity synConfigEntity;

    /**
     * 返回页面
     */
    public HrController() {
        super("hr");
    }

    @GetMapping("/stafflist")
    public String stafflist(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("stafflist", map, request);
    }

    @PostMapping("/stafflist")
    @ResponseBody
    public DataTableRecord stafflist(@RequestParam("sEcho") String sEcho,
                                     @RequestParam("iDisplayStart") Integer pageIndex,
                                     @RequestParam("iDisplayLength") Integer pageSize,
                                     @RequestParam("staffNumOrName") String staffNumOrName,
                                     @RequestParam("staffState") String staffState) {
        DataTableRecord dataTableRecord = staffService.getStaffList(sEcho, pageIndex, pageSize, staffNumOrName,staffState);
        return dataTableRecord;
    }

    @GetMapping("/viewstaffdetail")
    public String viewstaffdetail(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("viewstaffdetail", map, request);
    }

    @GetMapping("/viewstaffdetailinfo")
    @ResponseBody
    public Result viewstaffdetailinfo(@RequestParam("staffNum") String staffNum) {
        try{
            StaffVO staffVO = viewStaffListService.findStaffInfoByStaffNum(staffNum);
            if(staffVO==null){
                return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
            }else{
                return ResultGenerator.genSuccessResult(staffVO);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }



    @PostMapping("/deptStaffList")
    @ResponseBody
    public DataTableRecord deptStaffList(@RequestParam("sEcho") String sEcho,
                                     @RequestParam("iDisplayStart") Integer pageIndex,
                                     @RequestParam("iDisplayLength") Integer pageSize,
                                     @RequestParam("departmentId") String departmentId) {
        DataTableRecord dataTableRecord = staffService.deptStaffList(sEcho, pageIndex, pageSize,departmentId);
        return dataTableRecord;
    }


    @GetMapping("/post")
    public String post(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("post", map, request);
    }

    @PostMapping("/post")
    @ResponseBody
    public DataTableRecord post(@RequestParam("sEcho") String sEcho,
                                    @RequestParam("iDisplayStart") Integer pageIndex,
                                    @RequestParam("iDisplayLength") Integer pageSize,
                                    @RequestParam("postId") String postId,
                                    @RequestParam("lanCode") String lanCode) {
        DataTableRecord dataTableRecord = postService.getPostList(sEcho, pageIndex, pageSize, postId, lanCode);
        return dataTableRecord;
    }
    @GetMapping("/postinfo")
    public String postinfo(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("postinfo", map, request);
    }

    @PostMapping("/postinfo")
    @ResponseBody
    public Result postinfo(@RequestParam("postId") String postId,@RequestParam("lanCode") String lanCode) {
        try{
            PostVO postVO = viewPostListService.findPostInfoByPostId(postId,lanCode);
            if(org.springframework.util.StringUtils.isEmpty(postVO)){
                return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
            }else{
                return ResultGenerator.genSuccessResult(postVO);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @GetMapping("/department")
    public String department(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("department", map, request);
    }


    @GetMapping("/deptlist")
    @ResponseBody
    public Result deptlist() {
        try {
            List<Department> listOfLvl1 =  departmentService.getFirstLvlDept();
            List<Department> listOfLvl2 =  departmentService.getSecondLvlDept();
            List<Department> listOfLvl3 =  departmentService.getThirdlDept();
            List<Department> listOfLvl4 =  departmentService.getFourthLvlDept();
            List<Department> listOfLvl5 =  departmentService.getFifthLvlDept();
            List<Department> listOfLvl6 =  departmentService.getSixthLvlDept();
            List<Department> listOfLvl7 =  departmentService.getSeventhLvlDept();
            List<ViewDepartmentMatrixList> departmentMatrixList = viewDepartmentMatrixListService.findAll();

            DeptListVO deptListVO = new DeptListVO();
            deptListVO.setDeptListOfLvl1(PojoConvertUtil.convertPojoList(listOfLvl1, DeptVO.class));
            deptListVO.setDeptListOfLvl2(PojoConvertUtil.convertPojoList(listOfLvl2, DeptVO.class));
            deptListVO.setDeptListOfLvl3(PojoConvertUtil.convertPojoList(listOfLvl3, DeptVO.class));
            deptListVO.setDeptListOfLvl4(PojoConvertUtil.convertPojoList(listOfLvl4, DeptVO.class));
            deptListVO.setDeptListOfLvl5(PojoConvertUtil.convertPojoList(listOfLvl5, DeptVO.class));
            deptListVO.setDeptListOfLvl6(PojoConvertUtil.convertPojoList(listOfLvl6, DeptVO.class));
            deptListVO.setDeptListOfLvl7(PojoConvertUtil.convertPojoList(listOfLvl7, DeptVO.class));
            deptListVO.setDepartmentMatrixList(departmentMatrixList);
            return ResultGenerator.genSuccessResult(deptListVO);
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            throw new ServiceException("获取部门列表出错，请稍后刷新页面重试");
        }
    }

    @GetMapping("/getlanguage")
    @ResponseBody
    public Result getlanguage(){
        try{
            List<Language> languageList = languageService.findAll();
            if(org.springframework.util.StringUtils.isEmpty(languageList)){
                return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
            }else{
                return ResultGenerator.genSuccessResult(languageList);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }


    @PostMapping("/syncstaff")
    @ResponseBody
    public Result syncstaff(@RequestParam("staffNums") String staffNums) {
        if (staffNums != null && staffNums.length() == 0) {
            return ResultGenerator.genFailResult("请选择同步数据");
        }
        try{
            String[] staffNumArray = staffNums.split(",");
            List<String> staffNumList = Arrays.asList(staffNumArray);

            List<Company> companyList = companyService.findAll();
            List<DepartmentVO> departmentVOList = departmentService.getDepartmentList();
            List<PostVO> postVOList = postService.getPostList();
            List<SyncStaffVO> staffVOList = staffService.getSyncStaffInfo();
            List<StaffStoreVO> staffStoreVOList = staffService.getStaffStoreList();

            List<SyncStaffVO> syncStaffList = staffVOList.stream().filter(a->staffNumList.contains(a.getStaffNum())).collect(Collectors.toList());
            if(!ListUtil.isBlank(syncStaffList)){
                for(SyncStaffVO syncStaffVO : syncStaffList){
                    //公司别
                    List<Company> companyFilterList = companyList.stream().filter(a->a.getCompanyId().equals(syncStaffVO.getCompany())).collect(Collectors.toList());
                    if(!ListUtil.isBlank(companyFilterList)){
                        syncStaffVO.setCompanyEntity(companyFilterList.get(0));
                    }
                    //部门
                    List<DepartmentVO> departmentFilterList = departmentVOList.stream().filter(a->a.getDepartmentId().equals(syncStaffVO.getDepartmentId())).collect(Collectors.toList());
                    if(!ListUtil.isBlank(departmentFilterList)){
                        syncStaffVO.setDepartments(departmentFilterList);
                    }
                    //岗位
                    List<PostVO> postVOFilterList = postVOList.stream().filter(a->a.getPostId().equals(syncStaffVO.getPostId())).collect(Collectors.toList());
                    if(!ListUtil.isBlank(postVOFilterList)){
                        syncStaffVO.setPosts(postVOFilterList);
                    }
                    //直接上级
                    if(!StringUtils.isEmpty(syncStaffVO.getSuperiorNum())){
                        List<SyncStaffVO> superStaffList = staffVOList.stream().filter(a->a.getStaffNum().equals(syncStaffVO.getSuperiorNum())).collect(Collectors.toList());
                        if(!ListUtil.isBlank(superStaffList)){
                            Staff staff = PojoConvertUtil.convertPojo(superStaffList.get(0), Staff.class);
                            syncStaffVO.setSuperior(staff);
                        }
                    }
                    //店员信息
                    List<StaffStoreVO> staffStoreVOFilterList = staffStoreVOList.stream().filter(a->a.getStaffNum().equals(syncStaffVO.getStaffNum())).collect(Collectors.toList());
                    if(!ListUtil.isBlank(staffStoreVOFilterList)){
                        syncStaffVO.setStoreStaff(staffStoreVOFilterList.get(0));
                    }
                }
            }
            if(!ListUtil.isBlank(syncStaffList)){
                //同步到OA和门店系统
                StringBuffer sb = new StringBuffer();
                if(SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getStaffSync2OAIsm()),syncStaffList)){
                    sb.append("同步到OA、门店系统成功\n");
                }else{
                    sb.append("同步到OA、门店系统失败\n");
                }

                if(SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getStaffSync2CH()),syncStaffList)){
                    sb.append("同步到汇联易成功\n");
                }else{
                    sb.append("同步到汇联易失败\n");
                }
                String message = sb.toString();
                if (!StringUtils.isEmpty(message)) {
                    try {
                        String lastUpdateBy = SessionManager.getLoginName();
                        int count = staffService.updateSynStaff(lastUpdateBy, staffNumList);
                        if (count > 0) {
                            return ResultGenerator.genSuccessResult(message);
                        } else {
                            return ResultGenerator.genFailResult("同步到OA、ISM和汇联易成功，但同步状态回写失败");
                        }
                    } catch (Exception e) {
                        LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                        return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
                    }
                } else {
                    return ResultGenerator.genFailResult("批量同步失败，请您稍后再试");
                }
            }else{
                return ResultGenerator.genFailResult("批量同步失败，请您稍后再试");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("批量同步失败，请您稍后再试");
        }
    }


    /**
     * 同步部门矩阵和部门信息
     * @param departmentMatrixVO
     * @return
     */
    @PostMapping("/syncdepartmentmatrix")
    @ResponseBody
    public Result syncdepartmentmatrix(@RequestBody DepartmentMatrixVO departmentMatrixVO){
        try{
            if(departmentMatrixVO==null){
                return ResultGenerator.genFailResult("同步失败，数据异常");
            }
            List<DepartmentMatrixVO> departmentMatrixVOList = new ArrayList<>();
            List<DepartmentVO> departmentVOList = new ArrayList<>();
            StringBuffer sb = new StringBuffer();

            departmentMatrixVOList.add(departmentMatrixVO);
            DepartmentMatrix departmentMatrix = PojoConvertUtil.convertPojo(departmentMatrixVO,DepartmentMatrix.class);
            DepartmentVO departmentVO = departmentService.getDepartmentById(departmentMatrixVO.getDepartmentId());
                departmentVO.setDepartmentMatrix(departmentMatrix);
            departmentVOList.add(departmentVO);

            if(SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getDepartmentSync2CH()),departmentVOList)){
                sb.append("同步到汇联易成功\n");
            }else{
                sb.append("同步到汇联易失败\n");
            }

            if(SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getDepartmentSync2OA()),departmentVOList) &&
                    SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getDepartmentMatrixSync2OA()),departmentMatrixVOList)){
                sb.append("同步到OA成功\n");
            }else{
                sb.append("同步到OA失败\n");
            }
            return ResultGenerator.genSuccessResult(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("/syncpost")
    @ResponseBody
    public Result syncpost(@RequestParam("postIds") String postIds,@RequestParam("lanCode") String lanCode) {
        if (postIds != null && postIds.length() == 0) {
            return ResultGenerator.genFailResult("请选择同步数据");
        }
        String[] postIdList = postIds.split(",");
        List<SyncPostVO> syncInfo = postService.getSyncPostInfo(postIdList,lanCode);
        if(!ListUtil.isBlank(syncInfo)){
            boolean success = SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getPostSync2OA()), syncInfo);
            if (success) {
            try {
                String lastUpdateBy = SessionManager.getLoginName();
                int count = postService.updateSynPost(lastUpdateBy, postIdList,lanCode);
                if (count > 0) {
                    return ResultGenerator.genSuccessResult();
                } else {
                    return ResultGenerator.genFailResult("批量同步失败，请您稍后再试");
                }
            } catch (Exception e) {
                LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
            }
            } else {
                return ResultGenerator.genFailResult("批量同步失败，请您稍后再试");
            }
        }else{
            return ResultGenerator.genFailResult("批量同步失败，请您稍后再试");
        }
    }


    /**
     * 导出人员列表excel数据
     * @param response
     * @param staffNumOrName
     * @param staffState
     */
    @PostMapping("/downLoadStaffExcel")
    public void downLoadStaffExcel(HttpServletResponse response,
                     @RequestParam("staffNumOrNames") String staffNumOrName,
                     @RequestParam("staffStates") String staffState) {
        OutputStream os = null;
        try {
            List<StaffVO> list = viewStaffListService.findByQueryCondition(staffNumOrName,staffState);
            Workbook workbook = staffService.exportStaffExcel(list);
            String tableName = "人员信息列表";
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

    /**downLoadStaffExcel
     * 导出岗位列表excel数据
     * @param response
     * @param postIdOrNames
     * @param lanCodes
     */
    @PostMapping("/downLoadPostExcel")
    public void downLoadPostExcel(HttpServletResponse response,
                     @RequestParam("postIdOrNames") String postIdOrNames,
                     @RequestParam("lanCodes") String lanCodes) {
        OutputStream os = null;
        try {
            List<PostVO> list = viewPostListService.findByQueryCondition(postIdOrNames,lanCodes);
            Workbook workbook = postService.exportPostExcel(list);
            String tableName = "岗位信息列表";
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

}