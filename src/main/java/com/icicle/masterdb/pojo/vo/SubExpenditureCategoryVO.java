package com.icicle.masterdb.pojo.vo;

import java.util.Date;

/**
 * @author lvxuezhan
 * @version 2019-07-04 18:48
 */
public class SubExpenditureCategoryVO {

    private String subExcategoryId;

    private String lanCode;

    private String sourceId;

    private String subExcategoryName;

    private Integer extype;

    private String costcenterId;

    private String subExcategoryDesc;

    private Date effectiveDate;

    private String subExcategoryState;

    public String getSubExcategoryId() {
        return subExcategoryId;
    }

    public void setSubExcategoryId(String subExcategoryId) {
        this.subExcategoryId = subExcategoryId;
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

    public String getSubExcategoryName() {
        return subExcategoryName;
    }

    public void setSubExcategoryName(String subExcategoryName) {
        this.subExcategoryName = subExcategoryName;
    }

    public Integer getExtype() {
        return extype;
    }

    public void setExtype(Integer extype) {
        this.extype = extype;
    }

    public String getCostcenterId() {
        return costcenterId;
    }

    public void setCostcenterId(String costcenterId) {
        this.costcenterId = costcenterId;
    }

    public String getSubExcategoryDesc() {
        return subExcategoryDesc;
    }

    public void setSubExcategoryDesc(String subExcategoryDesc) {
        this.subExcategoryDesc = subExcategoryDesc;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getSubExcategoryState() {
        return subExcategoryState;
    }

    public void setSubExcategoryState(String subExcategoryState) {
        this.subExcategoryState = subExcategoryState;
    }
}
