package com.icicle.masterdb.model;

import javax.persistence.*;

@Table(name = "view_costcenter_list")
public class ViewCostcenterList {
    @Id
    @Column(name = "costcenter_id")
    private String costcenterId;

    @Column(name = "lan_code")
    private String lanCode;

    @Column(name = "lan_name")
    private String lanName;

    @Column(name = "source_id")
    private String sourceId;

    @Column(name = "cost_center_name")
    private String costCenterName;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "costcenter_manager")
    private String costcenterManager;

    @Column(name = "costcenter_chief")
    private String costcenterChief;

    @Column(name = "costcenter_vp")
    private String costcenterVp;

    @Column(name = "costcenter_desc")
    private String costcenterDesc;

    @Column(name = "effective_date")
    private String effectiveDate;

    @Column(name = "costcenter_state")
    private String costcenterState;

    /**
     * @return costcenter_id
     */
    public String getCostcenterId() {
        return costcenterId;
    }


    public String getLanName() {
        return lanName;
    }

    public void setLanName(String lanName) {
        this.lanName = lanName;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getCostcenterState() {
        return costcenterState;
    }

    public void setCostcenterState(String costcenterState) {
        this.costcenterState = costcenterState;
    }

    /**
     * @param costcenterId
     */
    public void setCostcenterId(String costcenterId) {
        this.costcenterId = costcenterId;
    }

    public String getCostcenterDesc() {
        return costcenterDesc;
    }

    public void setCostcenterDesc(String costcenterDesc) {
        this.costcenterDesc = costcenterDesc;
    }

    /**
     * @return lan_code
     */
    public String getLanCode() {
        return lanCode;
    }

    /**
     * @param lanCode
     */
    public void setLanCode(String lanCode) {
        this.lanCode = lanCode;
    }

    /**
     * @return source_id
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * @param sourceId
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * @return cost_center_name
     */
    public String getCostCenterName() {
        return costCenterName;
    }

    /**
     * @param costCenterName
     */
    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
    }

    /**
     * @return department_id
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return costcenter_manager
     */
    public String getCostcenterManager() {
        return costcenterManager;
    }

    /**
     * @param costcenterManager
     */
    public void setCostcenterManager(String costcenterManager) {
        this.costcenterManager = costcenterManager;
    }

    /**
     * @return costcenter_chief
     */
    public String getCostcenterChief() {
        return costcenterChief;
    }

    /**
     * @param costcenterChief
     */
    public void setCostcenterChief(String costcenterChief) {
        this.costcenterChief = costcenterChief;
    }

    /**
     * @return costcenter_vp
     */
    public String getCostcenterVp() {
        return costcenterVp;
    }

    /**
     * @param costcenterVp
     */
    public void setCostcenterVp(String costcenterVp) {
        this.costcenterVp = costcenterVp;
    }
}