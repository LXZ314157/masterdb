package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.DepartmentMapper;
import com.icicle.masterdb.model.Department;
import com.icicle.masterdb.model.ViewStaffList;
import com.icicle.masterdb.pojo.vo.DepartmentVO;
import com.icicle.masterdb.service.DepartmentService;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PojoConvertUtil;
import com.icicle.masterdb.web.BuyerController;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-11 15:54:21.
*/
@Service
public class DepartmentServiceImpl extends AbstractService<Department> implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getFirstLvlDept() {
        return PojoConvertUtil.convertPojoList(departmentMapper.getFirstLvlDept(), Department.class);
    }

    @Override
    public List<Department> getSecondLvlDept() {
        return PojoConvertUtil.convertPojoList(departmentMapper.getSecondLvlDept(), Department.class);
    }

    @Override
    public List<Department> getThirdlDept() {
        return PojoConvertUtil.convertPojoList(departmentMapper.getThirdLvlDept(), Department.class);
    }

    @Override
    public List<Department> getFourthLvlDept() {
        return PojoConvertUtil.convertPojoList(departmentMapper.getFourthLvlDept(), Department.class);
    }

    @Override
    public List<Department> getFifthLvlDept() {
        return PojoConvertUtil.convertPojoList(departmentMapper.getFifthLvlDept(), Department.class);
    }

    @Override
    public List<Department> getSixthLvlDept() {
        return PojoConvertUtil.convertPojoList(departmentMapper.getSixthLvlDept(), Department.class);
    }

    @Override
    public List<Department> getSeventhLvlDept() {
        return PojoConvertUtil.convertPojoList(departmentMapper.getSeventhLvlDept(), Department.class);
    }

    @Override
    public List<DepartmentVO> getDepartmentList() {
        return departmentMapper.getDepartmentList();
    }

    @Override
    public DepartmentVO getDepartmentById(String departmentId) {
        return departmentMapper.getDepartmentById(departmentId);
    }


}