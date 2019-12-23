package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author liurenhua
 */
@Table(name = "view_product_desc")
public class ViewProductDesc {
    @Id
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "material_no")
    private String materialNo;

    @Column(name = "model_no")
    private String modelNo;

    @Column(name = "color_name")
    private String colorName;

    @Column(name = "color_card_no")
    private String colorCardNo;

    @Column(name = "colour_system")
    private String colourSystem;

    private String wave;

    private String band;

    @Column(name = "icicle_group")
    private String icicleGroup;

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
}