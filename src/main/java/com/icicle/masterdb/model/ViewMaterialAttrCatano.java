package com.icicle.masterdb.model;

import javax.persistence.*;

@Table(name = "view_material_attr_catano")
public class ViewMaterialAttrCatano {
    @Id
    @Column(name = "material_code")
    private String materialCode;

    @Column(name = "attr_code")
    private String attrCode;

    @Column(name = "atr_value")
    private String atrValue;

    @Column(name = "erp_code")
    private String erpCode;

    @Column(name = "attr_name")
    private String attrName;

    @Column(name = "attr_type")
    private String attrType;

    /**
     * @return material_code
     */
    public String getMaterialCode() {
        return materialCode;
    }

    /**
     * @param materialCode
     */
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
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
     * @return atr_value
     */
    public String getAtrValue() {
        return atrValue;
    }

    /**
     * @param atrValue
     */
    public void setAtrValue(String atrValue) {
        this.atrValue = atrValue;
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

    /**
     * @return attr_type
     */
    public String getAttrType() {
        return attrType;
    }

    /**
     * @param attrType
     */
    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }
}