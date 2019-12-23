package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "view_product_description")
public class ViewProductDescription {
    @Id
    @Column(name = "product_code")
    private String productCode;

    private String descs;

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
     * @return descs
     */
    public String getDescs() {
        return descs;
    }

    /**
     * @param descs
     */
    public void setDescs(String descs) {
        this.descs = descs;
    }
}