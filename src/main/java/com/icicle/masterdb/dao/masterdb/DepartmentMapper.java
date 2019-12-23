package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Department;
import com.icicle.masterdb.pojo.vo.DepartmentVO;

import java.util.List;

public interface DepartmentMapper extends MyMapper<Department> {

    public List<Department> getFirstLvlDept();

    public List<Department> getSecondLvlDept();

    public List<Department> getThirdLvlDept();

    public List<Department> getFourthLvlDept();

    public List<Department> getFifthLvlDept();

    public List<Department> getSixthLvlDept();

    public List<Department> getSeventhLvlDept();


    /**
     * 获取部门列表
     * @return
     */
    List<DepartmentVO> getDepartmentList();

    /**
     * 根据部门id获取同步到OA的部门信息
     * @param departmentId
     * @return
     */
    DepartmentVO getDepartmentById(String departmentId);


}