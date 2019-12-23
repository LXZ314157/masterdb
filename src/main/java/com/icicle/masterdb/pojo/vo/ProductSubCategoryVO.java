package com.icicle.masterdb.pojo.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ProductSubCategoryVO {

    private Integer productSubCategoryId;
    private String productSubCategoryCode;
    private String productSubCategoryName;
    private String lanCode;
    private String categoryCode;
    private Integer subCategoryLevel;
    private Integer subCategoryState;
    private String paSubCategoryCode;
    private BigDecimal saleTaxRate;
    private String subCategoryKey;
    private String createBy;
    private String lastUpdateBy;
    private Date creationDate;
    private Date lastUpdateDate;

    public Integer getProductSubCategoryId() {
        return productSubCategoryId;
    }

    public void setProductSubCategoryId(Integer productSubCategoryId) {
        this.productSubCategoryId = productSubCategoryId;
    }

    public String getProductSubCategoryCode() {
        return productSubCategoryCode;
    }

    public void setProductSubCategoryCode(String productSubCategoryCode) {
        this.productSubCategoryCode = productSubCategoryCode;
    }

    public String getProductSubCategoryName() {
        return productSubCategoryName;
    }

    public void setProductSubCategoryName(String productSubCategoryName) {
        this.productSubCategoryName = productSubCategoryName;
    }

    public String getLanCode() {
        return lanCode;
    }

    public void setLanCode(String lanCode) {
        this.lanCode = lanCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Integer getSubCategoryLevel() {
        return subCategoryLevel;
    }

    public void setSubCategoryLevel(Integer subCategoryLevel) {
        this.subCategoryLevel = subCategoryLevel;
    }

    public Integer getSubCategoryState() {
        return subCategoryState;
    }

    public void setSubCategoryState(Integer subCategoryState) {
        this.subCategoryState = subCategoryState;
    }

    public String getPaSubCategoryCode() {
        return paSubCategoryCode;
    }

    public void setPaSubCategoryCode(String paSubCategoryCode) {
        this.paSubCategoryCode = paSubCategoryCode;
    }

    public BigDecimal getSaleTaxRate() {
        return saleTaxRate;
    }

    public void setSaleTaxRate(BigDecimal saleTaxRate) {
        this.saleTaxRate = saleTaxRate;
    }

    public String getSubCategoryKey() {
        return subCategoryKey;
    }

    public void setSubCategoryKey(String subCategoryKey) {
        this.subCategoryKey = subCategoryKey;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}