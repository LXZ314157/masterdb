package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author lvxuezhan
 */
public class DeptVO {
    private String departmentId;
    private String sourceId;
    private String lanCode;
    private String departmentName;
    private String paDepartmentId;
    private String departmentLevel;
    private String departmentState;
    private String referenceProp1;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getLanCode() {
        return lanCode;
    }

    public void setLanCode(String lanCode) {
        this.lanCode = lanCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPaDepartmentId() {
        return paDepartmentId;
    }

    public void setPaDepartmentId(String paDepartmentId) {
        this.paDepartmentId = paDepartmentId;
    }

    public String getDepartmentLevel() {
        return departmentLevel;
    }

    public void setDepartmentLevel(String departmentLevel) {
        this.departmentLevel = departmentLevel;
    }

    public String getDepartmentState() {
        return departmentState;
    }

    public void setDepartmentState(String departmentState) {
        this.departmentState = departmentState;
    }

    public String getReferenceProp1() {
        return referenceProp1;
    }

    public void setReferenceProp1(String referenceProp1) {
        this.referenceProp1 = referenceProp1;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
