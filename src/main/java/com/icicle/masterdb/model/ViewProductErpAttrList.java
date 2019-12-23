package com.icicle.masterdb.model;

import javax.persistence.*;

@Table(name = "view_product_erp_attr_list")
public class ViewProductErpAttrList {
    @Id
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "attr_code")
    private String attrCode;

    @Column(name = "attr_value")
    private String attrValue;

    @Column(name = "erp_code")
    private String erpCode;

    @Column(name = "attr_name")
    private String attrName;

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
     * @return attr_code
     */
    public String getAttrCode() {
        return attrCode;
    }

    /**
     * @param attrCode
     */
    public void setAttrCode(String attrCode) {
        this.attrCode = attrCode;
    }

    /**
     * @return attr_value
     */
    public String getAttrValue() {
        return attrValue;
    }

    /**
     * @param attrValue
     */
    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    /**
     * @return erp_code
     */
    public String getErpCode() {
        return erpCode;
    }

    /**
     * @param erpCode
     */
    public void setErpCode(String erpCode) {
        this.erpCode = erpCode;
    }

    /**
     * @return attr_name
     */
    public String getAttrName() {
        return attrName;
    }

    /**
     * @param attrName
     */
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }
}