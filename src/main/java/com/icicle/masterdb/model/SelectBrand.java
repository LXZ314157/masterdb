package com.icicle.masterdb.model;

import javax.persistence.*;

@Table(name = "select_brand")
public class SelectBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "item_key")
    private String itemKey;

    @Column(name = "item_value")
    private String itemValue;

    private Integer status;

    private String language;

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
}