package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author liurenhua
 */
public class UnitOfMeasureVO {
    private Integer id;
    private String uomCode;
    private String unitOfMeasure;
    private Boolean baseUomFlag;
    private String uomClass;
    private String uomDescription;
    private String language;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUomCode() {
        return uomCode;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Boolean getBaseUomFlag() {
        return baseUomFlag;
    }

    public void setBaseUomFlag(Boolean baseUomFlag) {
        this.baseUomFlag = baseUomFlag;
    }

    public String getUomClass() {
        return uomClass;
    }

    public void setUomClass(String uomClass) {
        this.uomClass = uomClass;
    }

    public String getUomDescription() {
        return uomDescription;
    }

    public void setUomDescription(String uomDescription) {
        this.uomDescription = uomDescription;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
