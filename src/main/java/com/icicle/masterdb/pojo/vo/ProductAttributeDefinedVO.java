package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.ProductAttributeItem;
import com.icicle.masterdb.util.StringUtil;

import javax.persistence.Column;
import java.util.List;
import java.util.Map;

/**
 * @author liurenhua
 */
public class ProductAttributeDefinedVO {
    private Integer id;

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

    private List<Map<String,String>> selectList;

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

    public List<Map<String, String>> getSelectList() {
        return selectList;
    }

    public void setSelectList(List<Map<String, String>> selectList) {
        this.selectList = selectList;
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

    private List<ProductAttributeItem> itemList;

    public List<ProductAttributeItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<ProductAttributeItem> itemList) {
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
