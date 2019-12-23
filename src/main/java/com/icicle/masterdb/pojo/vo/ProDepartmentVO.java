package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author liurenhua
 */
public class ProDepartmentVO {
    private Integer proDeptId;
    private String proDeptName;
    private String proDeptDesc;
    private Integer buId;
    private Integer status;

    /**
     * @return pro_dept_id
     */
    public Integer getProDeptId() {
        return proDeptId;
    }

    /**
     * @param proDeptId
     */
    public void setProDeptId(Integer proDeptId) {
        this.proDeptId = proDeptId;
    }

    /**
     * @return pro_dept_name
     */
    public String getProDeptName() {
        return proDeptName;
    }

    /**
     * @param proDeptName
     */
    public void setProDeptName(String proDeptName) {
        this.proDeptName = proDeptName;
    }

    public String getProDeptDesc() {
        return proDeptDesc;
    }

    public void setProDeptDesc(String proDeptDesc) {
        this.proDeptDesc = proDeptDesc;
    }

    public Integer getBuId() {
        return buId;
    }

    public void setBuId(Integer buId) {
        this.buId = buId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
