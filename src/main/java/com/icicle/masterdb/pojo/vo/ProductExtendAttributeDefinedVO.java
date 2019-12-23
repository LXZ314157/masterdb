package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.ProductAttributeItem;
import com.icicle.masterdb.model.ProductExtendAttributeItem;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author liurenhua
 */
public class ProductExtendAttributeDefinedVO {
    private Integer id;

    private String defKey;

    private String defCode;

    private String defName;

    private String defDesc;

    private String defType;

    private Integer defLength;

    private Integer hasItem;

    private Boolean status;

    private String selectTable;

    private Integer defGroup;

    private String modelValue;

    private Boolean isSync;

    /**
     * 扩展属性的值
     */
    private Object value;

    public Boolean getSync() {
        return isSync;
    }

    public void setSync(Boolean sync) {
        isSync = sync;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getSelectTable() {
        return selectTable;
    }

    public void setSelectTable(String selectTable) {
        this.selectTable = selectTable;
    }

    private List<ProductExtendAttributeItem> itemList;

    public String getDefKey() {
        return defKey;
    }

    public void setDefKey(String defKey) {
        this.defKey = defKey;
    }

    public List<ProductExtendAttributeItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<ProductExtendAttributeItem> itemList) {
        this.itemList = itemList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDefCode() {
        return defCode;
    }

    public String getModelValue() {
        return modelValue;
    }

    public void setModelValue(String modelValue) {
        this.modelValue = modelValue;
    }

    public void setDefCode(String defCode) {
        this.defCode = defCode;
    }

    public String getDefName() {
        return defName;
    }

    public void setDefName(String defName) {
        this.defName = defName;
    }

    public String getDefDesc() {
        return defDesc;
    }

    public void setDefDesc(String defDesc) {
        this.defDesc = defDesc;
    }

    public String getDefType() {
        return defType;
    }

    public Integer getDefGroup() {
        return defGroup;
    }

    public void setDefGroup(Integer defGroup) {
        this.defGroup = defGroup;
    }

    public void setDefType(String defType) {
        this.defType = defType;
    }

    public Integer getDefLength() {
        return defLength;
    }

    public void setDefLength(Integer defLength) {
        this.defLength = defLength;
    }

    public Integer getHasItem() {
        return hasItem;
    }

    public void setHasItem(Integer hasItem) {
        this.hasItem = hasItem;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
