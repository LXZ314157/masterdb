package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "view_skuinfosize")
public class ViewSkuinfosize {
    @Id
    @Column(name = "product_code")
    private String productCode;

    private String color;

    @Column(name = "Sizes")
    private String sizes;

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
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return Sizes
     */
    public String getSizes() {
        return sizes;
    }

    /**
     * @param sizes
     */
    public void setSizes(String sizes) {
        this.sizes = sizes;
    }
}