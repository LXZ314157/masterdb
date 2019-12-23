package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author liurenhua
 */
public class ViewMaterialAttributeVO {

    private String materialCode;

    private String attrCode;

    private String atrValue;

    private Integer id;

    private Integer isEdit;

    private String attrName;

    private Boolean hasItem;

    private String itemSource;

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getAttrCode() {
        return attrCode;
    }

    public void setAttrCode(String attrCode) {
        this.attrCode = attrCode;
    }

    public String getAtrValue() {
        return atrValue;
    }

    public void setAtrValue(String atrValue) {
        this.atrValue = atrValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Integer isEdit) {
        this.isEdit = isEdit;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public Boolean getHasItem() {
        return hasItem;
    }

    public void setHasItem(Boolean hasItem) {
        this.hasItem = hasItem;
    }

    public String getItemSource() {
        return itemSource;
    }

    public void setItemSource(String itemSource) {
        this.itemSource = itemSource;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
