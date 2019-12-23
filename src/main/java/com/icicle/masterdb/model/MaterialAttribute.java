package com.icicle.masterdb.model;

import javax.persistence.*;
/**
 * @author liurenhua
 */
@Table(name = "material_attribute")
public class MaterialAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "material_code")
    private String materialCode;

    @Column(name = "attr_code")
    private String attrCode;

    @Column(name = "atr_value")
    private String atrValue;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
}