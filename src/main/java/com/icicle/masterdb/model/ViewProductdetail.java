package com.icicle.masterdb.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "view_productdetail")
public class ViewProductdetail {
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

    @Column(name = "product_desc")
    private String productDesc;

    private Boolean status;

    @Column(name = "sync_status")
    private Integer syncStatus;

    @Column(name = "sync_record")
    private Boolean syncRecord;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "security_code")
    private String securityCode;

    @Column(name = "estimated_rate")
    private String estimatedRate;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "dev_no")
    private String devNo;

    @Column(name = "style_no")
    private String styleNo;

    @Column(name = "model_no")
    private String modelNo;

    private String line;

    @Column(name = "material_no")
    private String materialNo;

    @Column(name = "material_name")
    private String materialName;

    @Column(name = "color_name")
    private String colorName;

    @Column(name = "product_class")
    private String productClass;

    @Column(name = "productclass_value")
    private String productclassValue;

    private Integer year;

    @Column(name = "nature_season")
    private String natureSeason;

    @Column(name = "dev_season")
    private String devSeason;

    private String wave;

    private String band;

    @Column(name = "icicle_group")
    private String icicleGroup;

    @Column(name = "sales_date")
    private String salesDate;

    @Column(name = "color_card_no")
    private String colorCardNo;

    @Column(name = "color_card_name")
    private String colorCardName;

    @Column(name = "colour_system")
    private String colourSystem;

    @Column(name = "work_group")
    private String workGroup;

    @Column(name = "workgroup_value")
    private String workgroupValue;

    @Column(name = "size_group")
    private String sizeGroup;
    private String brand;

    private String code;

    private String standard;

    private Integer batch;

    @Column(name = "style_rule")
    private String styleRule;

    private String opr;

    @Column(name = "op_date")
    private String opDate;

    private String supplier;

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
     * @return sync_record
     */
    public Boolean getSyncRecord() {
        return syncRecord;
    }

    /**
     * @param syncRecord
     */
    public void setSyncRecord(Boolean syncRecord) {
        this.syncRecord = syncRecord;
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

    /**
     * @return security_code
     */
    public String getSecurityCode() {
        return securityCode;
    }

    /**
     * @param securityCode
     */
    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    /**
     * @return estimated_rate
     */
    public String getEstimatedRate() {
        return estimatedRate;
    }

    /**
     * @param estimatedRate
     */
    public void setEstimatedRate(String estimatedRate) {
        this.estimatedRate = estimatedRate;
    }

    /**
     * @return order_no
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return dev_no
     */
    public String getDevNo() {
        return devNo;
    }

    /**
     * @param devNo
     */
    public void setDevNo(String devNo) {
        this.devNo = devNo;
    }

    /**
     * @return style_no
     */
    public String getStyleNo() {
        return styleNo;
    }

    /**
     * @param styleNo
     */
    public void setStyleNo(String styleNo) {
        this.styleNo = styleNo;
    }

    /**
     * @return model_no
     */
    public String getModelNo() {
        return modelNo;
    }

    /**
     * @param modelNo
     */
    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    /**
     * @return line
     */
    public String getLine() {
        return line;
    }

    /**
     * @param line
     */
    public void setLine(String line) {
        this.line = line;
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
     * @return product_class
     */
    public String getProductClass() {
        return productClass;
    }

    /**
     * @param productClass
     */
    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    /**
     * @return productclass_value
     */
    public String getProductclassValue() {
        return productclassValue;
    }

    /**
     * @param productclassValue
     */
    public void setProductclassValue(String productclassValue) {
        this.productclassValue = productclassValue;
    }

    /**
     * @return year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * @param year
     */
    public void setYear(Integer year) {
        this.year = year;
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
     * @return dev_season
     */
    public String getDevSeason() {
        return devSeason;
    }

    /**
     * @param devSeason
     */
    public void setDevSeason(String devSeason) {
        this.devSeason = devSeason;
    }

    /**
     * @return wave
     */
    public String getWave() {
        return wave;
    }

    /**
     * @param wave
     */
    public void setWave(String wave) {
        this.wave = wave;
    }

    /**
     * @return band
     */
    public String getBand() {
        return band;
    }

    /**
     * @param band
     */
    public void setBand(String band) {
        this.band = band;
    }

    /**
     * @return icicle_group
     */
    public String getIcicleGroup() {
        return icicleGroup;
    }

    /**
     * @param icicleGroup
     */
    public void setIcicleGroup(String icicleGroup) {
        this.icicleGroup = icicleGroup;
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
     * @return colour_system
     */
    public String getColourSystem() {
        return colourSystem;
    }

    /**
     * @param colourSystem
     */
    public void setColourSystem(String colourSystem) {
        this.colourSystem = colourSystem;
    }

    /**
     * @return work_group
     */
    public String getWorkGroup() {
        return workGroup;
    }

    /**
     * @param workGroup
     */
    public void setWorkGroup(String workGroup) {
        this.workGroup = workGroup;
    }

    /**
     * @return workgroup_value
     */
    public String getWorkgroupValue() {
        return workgroupValue;
    }

    /**
     * @param workgroupValue
     */
    public void setWorkgroupValue(String workgroupValue) {
        this.workgroupValue = workgroupValue;
    }

    /**
     * @return size_group
     */
    public String getSizeGroup() {
        return sizeGroup;
    }

    /**
     * @param sizeGroup
     */
    public void setSizeGroup(String sizeGroup) {
        this.sizeGroup = sizeGroup;
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return standard
     */
    public String getStandard() {
        return standard;
    }

    /**
     * @param standard
     */
    public void setStandard(String standard) {
        this.standard = standard;
    }

    /**
     * @return batch
     */
    public Integer getBatch() {
        return batch;
    }

    /**
     * @param batch
     */
    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    /**
     * @return style_rule
     */
    public String getStyleRule() {
        return styleRule;
    }

    /**
     * @param styleRule
     */
    public void setStyleRule(String styleRule) {
        this.styleRule = styleRule;
    }

    /**
     * @return opr
     */
    public String getOpr() {
        return opr;
    }

    /**
     * @param opr
     */
    public void setOpr(String opr) {
        this.opr = opr;
    }

    /**
     * @return op_date
     */
    public String getOpDate() {
        return opDate;
    }

    /**
     * @param opDate
     */
    public void setOpDate(String opDate) {
        this.opDate = opDate;
    }

    /**
     * @return supplier
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * @param supplier
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}