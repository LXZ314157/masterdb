package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "view_product_sub_category")
public class ViewProductSubCategoryList {

    @Id
    @Column(name = "product_sub_category_id")
    private Integer productSubCategoryId;

    @Column(name = "product_sub_category_code")
    private String productSubCategoryCode;

    @Column(name = "product_sub_category_name")
    private String productSubCategoryName;

    @Column(name = "lan_code")
    private String lanCode;

    @Column(name = "lan_name")
    private String lanName;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "sub_category_level")
    private Integer subCategoryLevel;

    @Column(name = "sub_category_state")
    private Integer subCategoryState;

    @Column(name = "pa_sub_category_code")
    private String paSubCategoryCode;

    @Column(name = "pa_sub_category_name")
    private String paSubCategoryName;

    @Column(name = "sale_tax_rate")
    private BigDecimal saleTaxRate;

    @Column(name = "sub_category_key")
    private String subCategoryKey;

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

    public String getLanName() {
        return lanName;
    }

    public void setLanName(String lanName) {
        this.lanName = lanName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getPaSubCategoryName() {
        return paSubCategoryName;
    }

    public void setPaSubCategoryName(String paSubCategoryName) {
        this.paSubCategoryName = paSubCategoryName;
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
}