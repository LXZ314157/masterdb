package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * @author liurenhua
 */
@Table(name = "store_attrib_item")
public class StoreAttribItem {
    @Id
    @Column(name = "store_attrib_item_id")
    private Integer storeAttribItemId;

    @Column(name = "store_attrib_def_id")
    private Integer storeAttribDefId;

    @Column(name = "store_attrib_item_code")
    private String storeAttribItemCode;

    @Column(name = "store_attrib_item_name")
    private String storeAttribItemName;

    @Column(name = "store_attrib_item_desc")
    private String storeAttribItemDesc;

    private Integer status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    /**
     * @return store_attrib_item_id
     */
    public Integer getStoreAttribItemId() {
        return storeAttribItemId;
    }

    /**
     * @param storeAttribItemId
     */
    public void setStoreAttribItemId(Integer storeAttribItemId) {
        this.storeAttribItemId = storeAttribItemId;
    }

    /**
     * @return store_attrib_def_id
     */
    public Integer getStoreAttribDefId() {
        return storeAttribDefId;
    }

    /**
     * @param storeAttribDefId
     */
    public void setStoreAttribDefId(Integer storeAttribDefId) {
        this.storeAttribDefId = storeAttribDefId;
    }

    /**
     * @return store_attrib_item_code
     */
    public String getStoreAttribItemCode() {
        return storeAttribItemCode;
    }

    /**
     * @param storeAttribItemCode
     */
    public void setStoreAttribItemCode(String storeAttribItemCode) {
        this.storeAttribItemCode = storeAttribItemCode;
    }

    /**
     * @return store_attrib_item_name
     */
    public String getStoreAttribItemName() {
        return storeAttribItemName;
    }

    /**
     * @param storeAttribItemName
     */
    public void setStoreAttribItemName(String storeAttribItemName) {
        this.storeAttribItemName = storeAttribItemName;
    }

    /**
     * @return store_attrib_item_desc
     */
    public String getStoreAttribItemDesc() {
        return storeAttribItemDesc;
    }

    /**
     * @param storeAttribItemDesc
     */
    public void setStoreAttribItemDesc(String storeAttribItemDesc) {
        this.storeAttribItemDesc = storeAttribItemDesc;
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
}