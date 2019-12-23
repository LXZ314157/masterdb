package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "product_extend_attribute_defined")
public class ProductExtendAttributeDefined {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "def_key")
    private String defKey;

    @Column(name = "def_code")
    private String defCode;

    @Column(name = "def_name")
    private String defName;

    @Column(name = "def_desc")
    private String defDesc;

    @Column(name = "def_type")
    private String defType;

    @Column(name = "def_length")
    private Integer defLength;

    @Column(name = "has_item")
    private Integer hasItem;

    private Boolean status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "select_table")
    private String selectTable;

    @Column(name = "def_group")
    private Integer defGroup;

    @Column(name = "model_value")
    private String modelValue;

    @Column(name = "is_sync")
    private Boolean isSync;

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
     * @return def_code
     */
    public String getDefCode() {
        return defCode;
    }

    /**
     * @param defCode
     */
    public void setDefCode(String defCode) {
        this.defCode = defCode;
    }

    /**
     * @return def_name
     */
    public String getDefName() {
        return defName;
    }

    /**
     * @param defName
     */
    public void setDefName(String defName) {
        this.defName = defName;
    }

    /**
     * @return def_desc
     */
    public String getDefDesc() {
        return defDesc;
    }

    /**
     * @param defDesc
     */
    public void setDefDesc(String defDesc) {
        this.defDesc = defDesc;
    }

    /**
     * @return def_type
     */
    public String getDefType() {
        return defType;
    }

    /**
     * @param defType
     */
    public void setDefType(String defType) {
        this.defType = defType;
    }

    /**
     * @return def_length
     */
    public Integer getDefLength() {
        return defLength;
    }

    /**
     * @param defLength
     */
    public void setDefLength(Integer defLength) {
        this.defLength = defLength;
    }

    /**
     * @return has_item
     */
    public Integer getHasItem() {
        return hasItem;
    }

    /**
     * @param hasItem
     */
    public void setHasItem(Integer hasItem) {
        this.hasItem = hasItem;
    }

    /**
     * @return status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return created_by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return creation_date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return last_updated_by
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return last_update_date
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * @param lastUpdateDate
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * @return select_table
     */
    public String getSelectTable() {
        return selectTable;
    }

    /**
     * @param selectTable
     */
    public void setSelectTable(String selectTable) {
        this.selectTable = selectTable;
    }

    /**
     * @return def_group
     */
    public Integer getDefGroup() {
        return defGroup;
    }

    /**
     * @param defGroup
     */
    public void setDefGroup(Integer defGroup) {
        this.defGroup = defGroup;
    }

    /**
     * @return model_value
     */
    public String getModelValue() {
        return modelValue;
    }

    /**
     * @param modelValue
     */
    public void setModelValue(String modelValue) {
        this.modelValue = modelValue;
    }

    /**
     * @return is_sync
     */
    public Boolean getIsSync() {
        return isSync;
    }

    /**
     * @param isSync
     */
    public void setIsSync(Boolean isSync) {
        this.isSync = isSync;
    }


    public String getDefKey() {
        return defKey;
    }

    public void setDefKey(String defKey) {
        this.defKey = defKey;
    }

    public Boolean getSync() {
        return isSync;
    }

    public void setSync(Boolean sync) {
        isSync = sync;
    }
}