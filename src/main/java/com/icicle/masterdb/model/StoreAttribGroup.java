package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * @author liurenhua
 */
@Table(name = "store_attrib_group")
public class StoreAttribGroup {
    @Id
    @Column(name = "store_attrib_group_id")
    private Integer storeAttribGroupId;

    @Column(name = "store_attrib_group_code")
    private String storeAttribGroupCode;

    @Column(name = "store_attrib_group_name")
    private String storeAttribGroupName;

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
     * @return store_attrib_group_id
     */
    public Integer getStoreAttribGroupId() {
        return storeAttribGroupId;
    }

    /**
     * @param storeAttribGroupId
     */
    public void setStoreAttribGroupId(Integer storeAttribGroupId) {
        this.storeAttribGroupId = storeAttribGroupId;
    }

    /**
     * @return store_attrib_group_code
     */
    public String getStoreAttribGroupCode() {
        return storeAttribGroupCode;
    }

    /**
     * @param storeAttribGroupCode
     */
    public void setStoreAttribGroupCode(String storeAttribGroupCode) {
        this.storeAttribGroupCode = storeAttribGroupCode;
    }

    /**
     * @return store_attrib_group_name
     */
    public String getStoreAttribGroupName() {
        return storeAttribGroupName;
    }

    /**
     * @param storeAttribGroupName
     */
    public void setStoreAttribGroupName(String storeAttribGroupName) {
        this.storeAttribGroupName = storeAttribGroupName;
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