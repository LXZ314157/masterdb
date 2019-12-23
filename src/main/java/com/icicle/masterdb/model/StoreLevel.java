package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * @author liurenhua
 */
@Table(name = "store_level")
public class StoreLevel {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "store_level_id")
    private Integer storeLevelId;

    @Column(name = "store_level_name")
    private String storeLevelName;

    @Column(name = "store_level_desc")
    private String storeLevelDesc;

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
     * @return store_level_id
     */
    public Integer getStoreLevelId() {
        return storeLevelId;
    }

    /**
     * @param storeLevelId
     */
    public void setStoreLevelId(Integer storeLevelId) {
        this.storeLevelId = storeLevelId;
    }

    /**
     * @return store_level_name
     */
    public String getStoreLevelName() {
        return storeLevelName;
    }

    /**
     * @param storeLevelName
     */
    public void setStoreLevelName(String storeLevelName) {
        this.storeLevelName = storeLevelName;
    }

    /**
     * @return store_level_desc
     */
    public String getStoreLevelDesc() {
        return storeLevelDesc;
    }

    /**
     * @param storeLevelDesc
     */
    public void setStoreLevelDesc(String storeLevelDesc) {
        this.storeLevelDesc = storeLevelDesc;
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