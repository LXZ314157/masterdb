package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "view_select_item")
public class ViewSelectItem {
    @Column(name = "table_name")
    private String tableName;

    private String description;


    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "item_key")
    private String itemKey;

    @Column(name = "item_value")
    private String itemValue;

    private Integer status;

    private String language;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return table_name
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return item_key
     */
    public String getItemKey() {
        return itemKey;
    }

    /**
     * @param itemKey
     */
    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    /**
     * @return item_value
     */
    public String getItemValue() {
        return itemValue;
    }

    /**
     * @param itemValue
     */
    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}