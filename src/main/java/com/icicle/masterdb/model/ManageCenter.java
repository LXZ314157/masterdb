package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "manage_center")
public class ManageCenter {
    @Id
    @Column(name = "manager_center_id")
    private Integer managerCenterId;

    @Column(name = "manager_center_name")
    private String managerCenterName;

    @Column(name = "staff_num")
    private String staffNum;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "manage_center_state")
    private Integer manageCenterState;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    /**
     * @return manager_center_id
     */
    public Integer getManagerCenterId() {
        return managerCenterId;
    }

    /**
     * @param managerCenterId
     */
    public void setManagerCenterId(Integer managerCenterId) {
        this.managerCenterId = managerCenterId;
    }

    /**
     * @return manager_center_name
     */
    public String getManagerCenterName() {
        return managerCenterName;
    }

    /**
     * @param managerCenterName
     */
    public void setManagerCenterName(String managerCenterName) {
        this.managerCenterName = managerCenterName;
    }

    /**
     * @return staff_num
     */
    public String getStaffNum() {
        return staffNum;
    }

    /**
     * @param staffNum
     */
    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum;
    }

    /**
     * @return manager_name
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * @param managerName
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    /**
     * @return manage_center_state
     */
    public Integer getManageCenterState() {
        return manageCenterState;
    }

    /**
     * @param manageCenterState
     */
    public void setManageCenterState(Integer manageCenterState) {
        this.manageCenterState = manageCenterState;
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