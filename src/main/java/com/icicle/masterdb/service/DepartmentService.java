package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.Department;
import com.icicle.masterdb.pojo.vo.DepartmentVO;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-11 15:54:21.
*/
public interface DepartmentService extends Service<Department> {

    /**
     *
     * @return
     */
    public List<Department> getFirstLvlDept ();

    /**
     *
     * @return
     */
    public List<Department> getSecondLvlDept ();

    /**
     *
     * @return
     */
    public List<Department> getThirdlDept ();

    /**
     *
     * @return
     */
    public List<Department> getFourthLvlDept ();

    /**
     *
     * @return
     */
    public List<Department> getFifthLvlDept ();

    /**
     *
     * @return
     */
    public List<Department> getSixthLvlDept ();

    /**
     *
     * @return
     */
    public List<Department> getSeventhLvlDept ();

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