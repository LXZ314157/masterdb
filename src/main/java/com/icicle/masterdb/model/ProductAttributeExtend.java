package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "product_costume")
public class ProductAttributeExtend {
    @Id
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "security_code")
    private String securityCode;

    @Column(name = "estimated_rate")
    private String estimatedRate;

    @Column(name = "material_name_tag")
    private String materialNameTag;

    private String packages;

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
    private String icicleBand;

    @Column(name = "expired_date")
    private Date expiredDate;

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

    private Integer batch;

    @Column(name = "style_rule")
    private String styleRule;

    private String supplier;

    private String opr;

    @Column(name = "op_date")
    private String opDate;

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

    public String getIcicleBand() {
        return icicleBand;
    }

    public void setIcicleBand(String icicleBand) {
        this.icicleBand = icicleBand;
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

    public String getMaterialNameTag() {
        return materialNameTag;
    }

    public void setMaterialNameTag(String materialNameTag) {
        this.materialNameTag = materialNameTag;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
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

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (Objects.equals(obj.getClass().getName(), this.getClass().getName())) {
            ProductAttributeExtend s = (ProductAttributeExtend) obj;
            boolean same = Objects.equals(s.getProductCode(), this.getProductCode());
            return same;
        } else {
            return super.equals(obj);
        }
    }
}