package com.icicle.masterdb.pojo.vo;

import java.util.Date;

public class RespCenterVO {
    private String respcenterId;

    private String lanCode;

    private String sourceId;

    private String respcenterName;

    private String respcenterManager;

    private String departmentId;

    private String respcenterDesc;

    private String respcenterState;

    private Date effectiveDate;

    public String getRespcenterId() {
        return respcenterId;
    }

    public void setRespcenterId(String respcenterId) {
        this.respcenterId = respcenterId;
    }

    public String getLanCode() {
        return lanCode;
    }

    public void setLanCode(String lanCode) {
        this.lanCode = lanCode;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getRespcenterName() {
        return respcenterName;
    }

    public void setRespcenterName(String respcenterName) {
        this.respcenterName = respcenterName;
    }

    public String getRespcenterManager() {
        return respcenterManager;
    }

    public void setRespcenterManager(String respcenterManager) {
        this.respcenterManager = respcenterManager;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getRespcenterDesc() {
        return respcenterDesc;
    }

    public String getRespcenterState() {
        return respcenterState;
    }

    public void setRespcenterState(String respcenterState) {
        this.respcenterState = respcenterState;
    }

    public void setRespcenterDesc(String respcenterDesc) {
        this.respcenterDesc = respcenterDesc;
    }


    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}