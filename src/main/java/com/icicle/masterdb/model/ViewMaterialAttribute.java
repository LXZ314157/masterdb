package com.icicle.masterdb.model;

import javax.persistence.*;
/**
 * @author liurenhua
 */
@Table(name = "view_material_attribute")
public class ViewMaterialAttribute {
    @Id
    @Column(name = "material_code")
    private String materialCode;

    @Column(name = "attr_code")
    private String attrCode;

    @Column(name = "atr_value")
    private String atrValue;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "is_edit")
    private Integer isEdit;

    @Column(name = "attr_name")
    private String attrName;

    @Column(name = "has_item")
    private Boolean hasItem;

    @Column(name = "item_source")
    private String itemSource;

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
     * @return is_edit
     */
    public Integer getIsEdit() {
        return isEdit;
    }

    /**
     * @param isEdit
     */
    public void setIsEdit(Integer isEdit) {
        this.isEdit = isEdit;
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
     * @return has_item
     */
    public Boolean getHasItem() {
        return hasItem;
    }

    /**
     * @param hasItem
     */
    public void setHasItem(Boolean hasItem) {
        this.hasItem = hasItem;
    }

    /**
     * @return item_source
     */
    public String getItemSource() {
        return itemSource;
    }

    /**
     * @param itemSource
     */
    public void setItemSource(String itemSource) {
        this.itemSource = itemSource;
    }
}