package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "view_productlistinfo")
public class ViewProductlistinfo {
    @Id
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_type_id")
    private Integer productTypeId;

    @Column(name = "standard_cost")
    private BigDecimal standardCost;

    private Boolean status;

    @Column(name = "nature_season")
    private String natureSeason;

    @Column(name = "material_no")
    private String materialNo;

    @Column(name = "material_name")
    private String materialName;

    @Column(name = "color_card_no")
    private String colorCardNo;

    @Column(name = "color_card_name")
    private String colorCardName;

    @Column(name = "color_name")
    private String colorName;

    @Column(name = "color_name2")
    private String colorName2;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "sales_date")
    private String salesDate;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "sync_status")
    private Integer syncStatus;

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
     * @return product_type_id
     */
    public Integer getProductTypeId() {
        return productTypeId;
    }

    /**
     * @param productTypeId
     */
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
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
     * @return nature_season
     */
    public String getNatureSeason() {
        return natureSeason;
    }

    /**
     * @param natureSeason
     */
    public void setNatureSeason(String natureSeason) {
        this.natureSeason = natureSeason;
    }

    /**
     * @return material_no
     */
    public String getMaterialNo() {
        return materialNo;
    }

    /**
     * @param materialNo
     */
    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    /**
     * @return material_name
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * @param materialName
     */
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    /**
     * @return color_card_no
     */
    public String getColorCardNo() {
        return colorCardNo;
    }

    /**
     * @param colorCardNo
     */
    public void setColorCardNo(String colorCardNo) {
        this.colorCardNo = colorCardNo;
    }

    /**
     * @return color_card_name
     */
    public String getColorCardName() {
        return colorCardName;
    }

    /**
     * @param colorCardName
     */
    public void setColorCardName(String colorCardName) {
        this.colorCardName = colorCardName;
    }

    /**
     * @return color_name
     */
    public String getColorName() {
        return colorName;
    }

    /**
     * @param colorName
     */
    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    /**
     * @return color_name2
     */
    public String getColorName2() {
        return colorName2;
    }

    /**
     * @param colorName2
     */
    public void setColorName2(String colorName2) {
        this.colorName2 = colorName2;
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

    /**
     * @return sales_date
     */
    public String getSalesDate() {
        return salesDate;
    }

    /**
     * @param salesDate
     */
    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
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
}