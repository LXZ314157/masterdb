package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author CodeGeneratorUtil
 */
public class ProductAttributeExtendVO {
    private String productCode;
    private String productNo;
    private String orderNo;
    private String devNo;
    private String styleNo;
    private String modelNo;
    private String materialNo;
    private String materialName;
    private String colorName;
    private String productClass;
    private String natureSeason;
    private Date salesDate;
    private String colorCardNo;
    private String colorCardName;
    private String colorName2;
    private String workgroup;
    private String brand;
    private String code;
    private String standard;
    private String batch;
    private String styleRule;
    private String supplier;
    private BigDecimal estimatedCost;
    private Integer estimatedRate;
    private String createdBy;
    private Date createdDate;
    private String lastUpdateBy;
    private Date lastUpdateDate;

    public String getProductCode() {
        return productCode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
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

    public String getNatureSeason() {
        return natureSeason;
    }

    public void setNatureSeason(String natureSeason) {
        this.natureSeason = natureSeason;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
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

    public String getColorName2() {
        return colorName2;
    }

    public void setColorName2(String colorName2) {
        this.colorName2 = colorName2;
    }

    public String getWorkgroup() {
        return workgroup;
    }

    public void setWorkgroup(String workgroup) {
        this.workgroup = workgroup;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public BigDecimal getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(BigDecimal estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public Integer getEstimatedRate() {
        return estimatedRate;
    }

    public void setEstimatedRate(Integer estimatedRate) {
        this.estimatedRate = estimatedRate;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
