package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "city_level")
public class CityLevel {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "city_level_id")
    private Integer cityLevelId;

    @Column(name = "city_level_name")
    private String cityLevelName;

    @Column(name = "city_level_desc")
    private String cityLevelDesc;

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
     * @return city_level_id
     */
    public Integer getCityLevelId() {
        return cityLevelId;
    }

    /**
     * @param cityLevelId
     */
    public void setCityLevelId(Integer cityLevelId) {
        this.cityLevelId = cityLevelId;
    }

    /**
     * @return city_level_name
     */
    public String getCityLevelName() {
        return cityLevelName;
    }

    /**
     * @param cityLevelName
     */
    public void setCityLevelName(String cityLevelName) {
        this.cityLevelName = cityLevelName;
    }

    /**
     * @return city_level_desc
     */
    public String getCityLevelDesc() {
        return cityLevelDesc;
    }

    /**
     * @param cityLevelDesc
     */
    public void setCityLevelDesc(String cityLevelDesc) {
        this.cityLevelDesc = cityLevelDesc;
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