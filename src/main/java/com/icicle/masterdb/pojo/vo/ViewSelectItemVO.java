package com.icicle.masterdb.pojo.vo;

public class ViewSelectItemVO {

    private String tableName;

    private Integer subCategoryLevel;

    private String itemKey;

    private String itemValue;

    private String categoryCode;

    private String paSubCategoryCode;

    public String getPaSubCategoryCode() {
        return paSubCategoryCode;
    }

    public void setPaSubCategoryCode(String paSubCategoryCode) {
        this.paSubCategoryCode = paSubCategoryCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getSubCategoryLevel() {
        return subCategoryLevel;
    }

    public void setSubCategoryLevel(Integer subCategoryLevel) {
        this.subCategoryLevel = subCategoryLevel;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }
}

