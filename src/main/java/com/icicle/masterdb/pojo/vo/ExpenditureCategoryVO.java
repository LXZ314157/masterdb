package com.icicle.masterdb.pojo.vo;

import java.util.Date;

public class ExpenditureCategoryVO {
    private String excategoryId;

    private String lanCode;

    private String sourceId;

    private String excategoryName;

    private String extype;

    private String costcenterId;

    private String excategoryDesc;

    private Date effectiveDate;

    private String excategoryState;

    public String getExcategoryId() {
        return excategoryId;
    }

    public void setExcategoryId(String excategoryId) {
        this.excategoryId = excategoryId;
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

    public String getExcategoryName() {
        return excategoryName;
    }

    public void setExcategoryName(String excategoryName) {
        this.excategoryName = excategoryName;
    }


    public String getCostcenterId() {
        return costcenterId;
    }

    public void setCostcenterId(String costcenterId) {
        this.costcenterId = costcenterId;
    }

    public String getExcategoryDesc() {
        return excategoryDesc;
    }

    public void setExcategoryDesc(String excategoryDesc) {
        this.excategoryDesc = excategoryDesc;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getExtype() {
        return extype;
    }

    public void setExtype(String extype) {
        this.extype = extype;
    }

    public String getExcategoryState() {
        return excategoryState;
    }

    public void setExcategoryState(String excategoryState) {
        this.excategoryState = excategoryState;
    }
}