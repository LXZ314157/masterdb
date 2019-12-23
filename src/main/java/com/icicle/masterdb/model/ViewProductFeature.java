package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "view_product_feature")
public class ViewProductFeature {
    @Id
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "wave")
    private String wave;

    @Column(name = "band")
    private String band;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "color_name")
    private String colorName;

    @Column(name = "material_no")
    private String materialNo;

    @Column(name = "fabric_feature")
    private String fabricFeature;

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

    public String getFabricFeature() {
        return fabricFeature;
    }

    public void setFabricFeature(String fabricFeature) {
        this.fabricFeature = fabricFeature;
    }

    @Column(name = "material_name")
    private String materialName;

    @Column(name = "product_feature")
    private String productFeature;

    @Column(name = "product_recommend")
    private String productRecommend;

    @Column(name = "image_id")
    private Integer imageId;

    @Column(name = "image_url")
    private String imageUrl;

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
     * @return product_feature
     */
    public String getProductFeature() {
        return productFeature;
    }

    /**
     * @param productFeature
     */
    public void setProductFeature(String productFeature) {
        this.productFeature = productFeature;
    }

    /**
     * @return product_recommend
     */
    public String getProductRecommend() {
        return productRecommend;
    }

    /**
     * @param productRecommend
     */
    public void setProductRecommend(String productRecommend) {
        this.productRecommend = productRecommend;
    }

    /**
     * @return image_id
     */
    public Integer getImageId() {
        return imageId;
    }

    /**
     * @param imageId
     */
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    /**
     * @return image_url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}