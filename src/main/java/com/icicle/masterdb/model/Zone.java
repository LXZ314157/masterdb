package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author CodeGeneratorUtil
 */
public class Zone {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "zone_id")
    private Integer zoneId;

    @Column(name = "zone_name")
    private String zoneName;

    private Integer zoneper;

    @Column(name = "pa_zone_id")
    private Integer paZoneId;

    @Column(name = "zone_level")
    private Integer zoneLevel;

    @Column(name = "zone_desc")
    private String zoneDesc;

    @Column(name = "bu_id")
    private Integer buId;

    @Column(name = "pro_dept_id")
    private Integer proDeptId;

    @Column(name = "manage_center_id")
    private Integer manageCenterId;

    private Integer status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "manager_staff_num")
    private String managerStaffNum;

    @Column(name = "manager_staff_name")
    private String managerStaffName;


    public String getManagerStaffNum() {
        return managerStaffNum;
    }

    public void setManagerStaffNum(String managerStaffNum) {
        this.managerStaffNum = managerStaffNum;
    }


    public String getManagerStaffName() {
        return managerStaffName;
    }

    public void setManagerStaffName(String managerStaffName) {
        this.managerStaffName = managerStaffName;
    }

    /**
     * @return zone_id
     */
    public Integer getZoneId() {
        return zoneId;
    }

    /**
     * @param zoneId
     */
    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    /**
     * @return zone_name
     */
    public String getZoneName() {
        return zoneName;
    }

    public Integer getManageCenterId() {
        return manageCenterId;
    }

    public void setManageCenterId(Integer manageCenterId) {
        this.manageCenterId = manageCenterId;
    }

    /**
     * @param zoneName
     */
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    /**
     * @return zoneper
     */
    public Integer getZoneper() {
        return zoneper;
    }

    /**
     * @param zoneper
     */
    public void setZoneper(Integer zoneper) {
        this.zoneper = zoneper;
    }

    /**
     * @return pa_zone_id
     */
    public Integer getPaZoneId() {
        return paZoneId;
    }

    /**
     * @param paZoneId
     */
    public void setPaZoneId(Integer paZoneId) {
        this.paZoneId = paZoneId;
    }

    /**
     * @return zone_level
     */
    public Integer getZoneLevel() {
        return zoneLevel;
    }

    /**
     * @param zoneLevel
     */
    public void setZoneLevel(Integer zoneLevel) {
        this.zoneLevel = zoneLevel;
    }

    /**
     * @return zone_desc
     */
    public String getZoneDesc() {
        return zoneDesc;
    }

    /**
     * @param zoneDesc
     */
    public void setZoneDesc(String zoneDesc) {
        this.zoneDesc = zoneDesc;
    }

    /**
     * @return bu_id
     */
    public Integer getBuId() {
        return buId;
    }

    /**
     * @param buId
     */
    public void setBuId(Integer buId) {
        this.buId = buId;
    }

    /**
     * @return pro_dept_id
     */
    public Integer getProDeptId() {
        return proDeptId;
    }

    /**
     * @param proDeptId
     */
    public void setProDeptId(Integer proDeptId) {
        this.proDeptId = proDeptId;
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