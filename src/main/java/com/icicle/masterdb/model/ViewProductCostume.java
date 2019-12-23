package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lvxuezhan
 * @version 2019-07-19 16:38
 */
@Table(name = "view_product_costume")
public class ViewProductCostume {

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


    @Column(name = "security_code")
    private String securityCode;

//    @Column(name = "purchase_rate")
//    private BigDecimal purchaseRate;
//
//    @Column(name = "sale_rate")
//    private BigDecimal saleRate;

    @Column(name = "sync_status")
    private String syncStatus;

    @Column(name = "sync_record")
    private Boolean syncRecord;

    @Column(name = "product_desc")
    private String productDesc;

    private String brand;

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

    private Integer year;

    @Column(name = "nature_season")
    private String natureSeason;

    @Column(name = "dev_season")
    private String devSeason;

    private String wave;
    @Column(name = "band")
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

    @Column(name = "size_group")
    private String sizeGroup;

    private String code;

    private String standard;

    private String batch;

    @Column(name = "style_rule")
    private String styleRule;

    private String supplier;

    private String opr;

    @Column(name = "op_date")
    private String opDate;

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }

    public Boolean getSyncRecord() {
        return syncRecord;
    }

    public void setSyncRecord(Boolean syncRecord) {
        this.syncRecord = syncRecord;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getStandardCost() {
        return standardCost;
    }

    public void setStandardCost(BigDecimal standardCost) {
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

    public void setCateXl(String cateXl) {
        this.cateXl = cateXl;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getEstimatedRate() {
        return estimatedRate;
    }

    public void setEstimatedRate(String estimatedRate) {
        this.estimatedRate = estimatedRate;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDevNo() {
        return devNo;
    }

    public void setDevNo(String devNo) {
        this.devNo = devNo;
    }

    public String getStyleNo() {
        return styleNo;
    }

    public void setStyleNo(String styleNo) {
        this.styleNo = styleNo;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
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

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getProductClass() {
        return productClass;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getNatureSeason() {
        return natureSeason;
    }

    public void setNatureSeason(String natureSeason) {
        this.natureSeason = natureSeason;
    }

    public String getDevSeason() {
        return devSeason;
    }

    public void setDevSeason(String devSeason) {
        this.devSeason = devSeason;
    }

    public String getWave() {
        return wave;
    }

    public void setWave(String wave) {
        this.wave = wave;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getIcicleGroup() {
        return icicleGroup;
    }

    public void setIcicleGroup(String icicleGroup) {
        this.icicleGroup = icicleGroup;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }

    public String getColorCardNo() {
        return colorCardNo;
    }

    public void setColorCardNo(String colorCardNo) {
        this.colorCardNo = colorCardNo;
    }

    public String getColorCardName() {
        return colorCardName;
    }

    public void setColorCardName(String colorCardName) {
        this.colorCardName = colorCardName;
    }

    public String getColourSystem() {
        return colourSystem;
    }

    public void setColourSystem(String colourSystem) {
        this.colourSystem = colourSystem;
    }

    public String getWorkGroup() {
        return workGroup;
    }

    public void setWorkGroup(String workGroup) {
        this.workGroup = workGroup;
    }

    public String getSizeGroup() {
        return sizeGroup;
    }

    public void setSizeGroup(String sizeGroup) {
        this.sizeGroup = sizeGroup;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getStyleRule() {
        return styleRule;
    }

    public void setStyleRule(String styleRule) {
        this.styleRule = styleRule;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getOpr() {
        return opr;
    }

    public void setOpr(String opr) {
        this.opr = opr;
    }

    public String getOpDate() {
        return opDate;
    }

    public void setOpDate(String opDate) {
        this.opDate = opDate;
    }

//    public BigDecimal getPurchaseRate() {
//        return purchaseRate;
//    }
//
//    public void setPurchaseRate(BigDecimal purchaseRate) {
//        this.purchaseRate = purchaseRate;
//    }
//
//    public BigDecimal getSaleRate() {
//        return saleRate;
//    }
//
//    public void setSaleRate(BigDecimal saleRate) {
//        this.saleRate = saleRate;
//    }

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
}
