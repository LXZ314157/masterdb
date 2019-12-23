package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author CodeGeneratorUtil
 */
public class Product {
    @Id
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name")
    private String productName;

    private String uom;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "standard_cost")
    private BigDecimal standardCost;

    @Column(name = "cate_dl")
    private String cateDl;

    @Column(name = "cate_zl")
    private String cateZl;

    @Column(name = "cate_xl")
    private String cateXl;

    @Column(name = "category_level_1")
    private String categoryLevel1;

    @Column(name = "category_level_2")
    private String categoryLevel2;

    @Column(name = "product_line_id")
    private String productLineId;

    @Column(name = "security_code")
    private String securityCode;

    @Column(name = "purchase_rate")
    private BigDecimal purchaseRate;

    @Column(name = "sale_rate")
    private BigDecimal saleRate;

    /**
     * 数据来源的表名称
     */
    @Column(name = "product_category_code")
    private String productCategoryCode;

    @Column(name = "product_desc")
    private String productDesc;
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    private Boolean status;

    @Column(name = "sync_status")
    private Integer syncStatus;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    private Boolean syncRecord;

    public Boolean getSyncRecord() {
        return syncRecord;
    }

    public void setSyncRecord(Boolean syncRecord) {
        this.syncRecord = syncRecord;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public BigDecimal getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(BigDecimal purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    public BigDecimal getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(BigDecimal saleRate) {
        this.saleRate = saleRate;
    }

    public String getCategoryLevel1() {
        return categoryLevel1;
    }

    public void setCategoryLevel1(String categoryLevel1) {
        this.categoryLevel1 = categoryLevel1;
    }


    public String getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(String productLineId) {
        this.productLineId = productLineId;
    }

    public String getCategoryLevel2() {
        return categoryLevel2;
    }

    public void setCategoryLevel2(String categoryLevel2) {
        this.categoryLevel2 = categoryLevel2;
    }

    /**
     * @return product_code
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return product_name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return uom
     */
    public String getUom() {
        return uom;
    }

    /**
     * @param uom
     */
    public void setUom(String uom) {
        this.uom = uom;
    }

    /**
     * @return unit_price
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return standard_cost
     */
    public BigDecimal getStandardCost() {
        return standardCost;
    }

    /**
     * @param standardCost
     */
    public void setStandardCost(BigDecimal standardCost) {
        this.standardCost = standardCost;
    }

    /**
     * @return cate_dl
     */
    public String getCateDl() {
        return cateDl;
    }

    /**
     * @param cateDl
     */
    public void setCateDl(String cateDl) {
        this.cateDl = cateDl;
    }

    /**
     * @return cate_zl
     */
    public String getCateZl() {
        return cateZl;
    }

    /**
     * @param cateZl
     */
    public void setCateZl(String cateZl) {
        this.cateZl = cateZl;
    }

    /**
     * @return cate_xl
     */
    public String getCateXl() {
        return cateXl;
    }

    /**
     * @param cateXl
     */
    public void setCateXl(String cateXl) {
        this.cateXl = cateXl;
    }

    /**
     * @return product_desc
     */
    public String getProductDesc() {
        return productDesc;
    }

    /**
     * @param productDesc
     */
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    /**
     * @return status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return sync_status
     */
    public Integer getSyncStatus() {
        return syncStatus;
    }

    /**
     * @param syncStatus
     */
    public void setSyncStatus(Integer syncStatus) {
        this.syncStatus = syncStatus;
    }

    /**
     * @return created_by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return creation_date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return last_updated_by
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return last_update_date
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * @param lastUpdateDate
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (Objects.equals(obj.getClass().getName(), this.getClass().getName())) {
            Product s = (Product) obj;
            boolean same = Objects.equals(s.getProductCode(), this.getProductCode());
            return same;
        } else {
            return super.equals(obj);
        }
    }
}