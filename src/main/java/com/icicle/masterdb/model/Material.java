package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @author liurenhua
 */
public class Material {
    @Id
    @Column(name = "material_code")
    private String materialCode;

    @Column(name = "material_type")
    private Integer materialType;

    @Column(name = "material_name")
    private String materialName;

    private String uom;

    @Column(name = "vendor_price")
    private BigDecimal vendorPrice;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "material_desc")
    private String materialDesc;

    @Column(name = "cate_dl")
    private String cateDl;

    @Column(name = "cate_zl")
    private String cateZl;

    @Column(name = "cate_xl")
    private String cateXl;

    @Column(name = "catalog_type")
    private Integer catalogType;

    @Column(name = "item_template_type")
    private Integer itemTemplateType;

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

    /**
     * @return material_code
     */
    public String getMaterialCode() {
        return materialCode;
    }

    /**
     * @param materialCode
     */
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    /**
     * @return material_type
     */
    public Integer getMaterialType() {
        return materialType;
    }

    /**
     * @param materialType
     */
    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
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
     * @return vendor_price
     */
    public BigDecimal getVendorPrice() {
        return vendorPrice;
    }

    /**
     * @param vendorPrice
     */
    public void setVendorPrice(BigDecimal vendorPrice) {
        this.vendorPrice = vendorPrice;
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
     * @return currency_code
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * @param currencyCode
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * @return material_desc
     */
    public String getMaterialDesc() {
        return materialDesc;
    }

    /**
     * @param materialDesc
     */
    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
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
     * @return catalog_type
     */
    public Integer getCatalogType() {
        return catalogType;
    }

    /**
     * @param catalogType
     */
    public void setCatalogType(Integer catalogType) {
        this.catalogType = catalogType;
    }

    /**
     * @return item_template_type
     */
    public Integer getItemTemplateType() {
        return itemTemplateType;
    }

    /**
     * @param itemTemplateType
     */
    public void setItemTemplateType(Integer itemTemplateType) {
        this.itemTemplateType = itemTemplateType;
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
}