package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "view_productmessageinfo")
public class ViewProductmessageinfo {
    @Id
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "uom")
    private String uom;

    @Column(name = "standard_cost")
    private String standardCost;

    @Column(name = "color_name")
    private String colorName;

    @Column(name = "category_level_1")
    private String categoryLevel1;

    @Column(name = "category_level_2")
    private String categoryLevel2;

    @Column(name = "cate_dl")
    private String cateDl;

    @Column(name = "cate_zl")
    private String cateZl;

    @Column(name = "cate_xl")
    private String cateXl;

    @Column(name = "material_no")
    private String materialNo;

    @Column(name = "material_name")
    private String materialName;

    /**
     * 数据来源的表名称
     */
    @Column(name = "product_category_code")
    private String productCategoryCode;

    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "brand")
    private String brand;

    @Column(name = "status")
    private String status;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "purchase_rate")
    private BigDecimal purchaseRate;

    @Column(name = "sale_rate")
    private BigDecimal saleRate;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "sync_status")
    private Integer syncStatus;

    private String syncRecord;

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

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    /**
     * @return product_code
     */




    public String getProductCode() {
        return productCode;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getStandardCost() {
        return standardCost;
    }

    public void setStandardCost(String standardCost) {
        this.standardCost = standardCost;
    }

    public String getCateDl() {
        return cateDl;
    }

    public void setCateDl(String cateDl) {
        this.cateDl = cateDl;
    }

    public String getCateZl() {
        return cateZl;
    }

    public void setCateZl(String cateZl) {
        this.cateZl = cateZl;
    }

    public String getCateXl() {
        return cateXl;
    }

    public String getCategoryLevel1() {
        return categoryLevel1;
    }

    public void setCategoryLevel1(String categoryLevel1) {
        this.categoryLevel1 = categoryLevel1;
    }

    public String getCategoryLevel2() {
        return categoryLevel2;
    }

    public void setCategoryLevel2(String categoryLevel2) {
        this.categoryLevel2 = categoryLevel2;
    }

    public void setCateXl(String cateXl) {
        this.cateXl = cateXl;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
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


    public String getSyncRecord() {
        return syncRecord;
    }

    public void setSyncRecord(String syncRecord) {
        this.syncRecord = syncRecord;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
}