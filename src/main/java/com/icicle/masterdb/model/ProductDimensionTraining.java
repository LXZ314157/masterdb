package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "product_dimension_training")
public class ProductDimensionTraining {
    @Id
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_feature")
    private String productFeature;

    @Column(name = "product_recommend")
    private String productRecommend;

    private String language;

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
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }
}