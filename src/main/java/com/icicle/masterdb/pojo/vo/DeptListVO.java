package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.ViewDepartmentMatrixList;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public class DeptListVO {


    /**
     * 一级部门
     */
    private List<DeptVO> deptListOfLvl1;

    /**
     * 二级部门
     */
    private List<DeptVO> deptListOfLvl2;

    /**
     * 三级部门
     */
    private List<DeptVO> deptListOfLvl3;

    /**
     * 四级部门
     */
    private List<DeptVO> deptListOfLvl4;

    /**
     * 五级部门
     */
    private List<DeptVO> deptListOfLvl5;

    /**
     * 六级部门
     */
    private List<DeptVO> deptListOfLvl6;

    /**
     * 七级部门
     */

    private List<DeptVO> deptListOfLvl7;

    /**
     * 部门矩阵视图对象
     */
    private List<ViewDepartmentMatrixList> departmentMatrixList;

    public List<ViewDepartmentMatrixList> getDepartmentMatrixList() {
        return departmentMatrixList;
    }

    public void setDepartmentMatrixList(List<ViewDepartmentMatrixList> departmentMatrixList) {
        this.departmentMatrixList = departmentMatrixList;
    }

    public List<DeptVO> getDeptListOfLvl1() {
        return deptListOfLvl1;
    }

    public void setDeptListOfLvl1(List<DeptVO> deptListOfLvl1) {
        this.deptListOfLvl1 = deptListOfLvl1;
    }

    public List<DeptVO> getDeptListOfLvl2() {
        return deptListOfLvl2;
    }

    public void setDeptListOfLvl2(List<DeptVO> deptListOfLvl2) {
        this.deptListOfLvl2 = deptListOfLvl2;
    }

    public List<DeptVO> getDeptListOfLvl3() {
        return deptListOfLvl3;
    }

    public void setDeptListOfLvl3(List<DeptVO> deptListOfLvl3) {
        this.deptListOfLvl3 = deptListOfLvl3;
    }

    public List<DeptVO> getDeptListOfLvl4() {
        return deptListOfLvl4;
    }

    public void setDeptListOfLvl4(List<DeptVO> deptListOfLvl4) {
        this.deptListOfLvl4 = deptListOfLvl4;
    }

    public List<DeptVO> getDeptListOfLvl5() {
        return deptListOfLvl5;
    }

    public void setDeptListOfLvl5(List<DeptVO> deptListOfLvl5) {
        this.deptListOfLvl5 = deptListOfLvl5;
    }

    public List<DeptVO> getDeptListOfLvl6() {
        return deptListOfLvl6;
    }

    public void setDeptListOfLvl6(List<DeptVO> deptListOfLvl6) {
        this.deptListOfLvl6 = deptListOfLvl6;
    }

    public List<DeptVO> getDeptListOfLvl7() {
        return deptListOfLvl7;
    }

    public void setDeptListOfLvl7(List<DeptVO> deptListOfLvl7) {
        this.deptListOfLvl7 = deptListOfLvl7;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}