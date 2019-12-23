package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

public class Costcenter {
    @Id
    @Column(name = "costcenter_id")
    private String costcenterId;

    @Column(name = "lan_code")
    private String lanCode;

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

    @Column(name = "costcenter_state")
    private Integer costcenterState;

    @Column(name = "reference_id")
    private String referenceId;

    @Column(name = "reference_id2")
    private String referenceId2;

    @Column(name = "reference_id3")
    private String referenceId3;

    @Column(name = "reference_id4")
    private String referenceId4;

    @Column(name = "reference_id5")
    private String referenceId5;


    @Column(name = "effective_date")
    private String effectiveDate;

    private String creater;

    private Date createtime;

    private String lasteditor;

    private Date lastedittime;

    /**
     * @return costcenter_id
     */
    public String getCostcenterId() {
        return costcenterId;
    }

    /**
     * @param costcenterId
     */
    public void setCostcenterId(String costcenterId) {
        this.costcenterId = costcenterId;
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

    /**
     * @return costcenter_desc
     */
    public String getCostcenterDesc() {
        return costcenterDesc;
    }

    /**
     * @param costcenterDesc
     */
    public void setCostcenterDesc(String costcenterDesc) {
        this.costcenterDesc = costcenterDesc;
    }

    /**
     * @return costcenter_state
     */
    public Integer getCostcenterState() {
        return costcenterState;
    }

    /**
     * @param costcenterState
     */
    public void setCostcenterState(Integer costcenterState) {
        this.costcenterState = costcenterState;
    }

    /**
     * @return reference_id
     */
    public String getReferenceId() {
        return referenceId;
    }

    /**
     * @param referenceId
     */
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    /**
     * @return reference_id2
     */
    public String getReferenceId2() {
        return referenceId2;
    }

    /**
     * @param referenceId2
     */
    public void setReferenceId2(String referenceId2) {
        this.referenceId2 = referenceId2;
    }

    /**
     * @return reference_id3
     */
    public String getReferenceId3() {
        return referenceId3;
    }

    /**
     * @param referenceId3
     */
    public void setReferenceId3(String referenceId3) {
        this.referenceId3 = referenceId3;
    }

    /**
     * @return reference_id4
     */
    public String getReferenceId4() {
        return referenceId4;
    }

    /**
     * @param referenceId4
     */
    public void setReferenceId4(String referenceId4) {
        this.referenceId4 = referenceId4;
    }

    /**
     * @return reference_id5
     */
    public String getReferenceId5() {
        return referenceId5;
    }

    /**
     * @param referenceId5
     */
    public void setReferenceId5(String referenceId5) {
        this.referenceId5 = referenceId5;
    }

    /**
     * @return creater
     */
    public String getCreater() {
        return creater;
    }

    /**
     * @param creater
     */
    public void setCreater(String creater) {
        this.creater = creater;
    }

    /**
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return lasteditor
     */
    public String getLasteditor() {
        return lasteditor;
    }

    /**
     * @param lasteditor
     */
    public void setLasteditor(String lasteditor) {
        this.lasteditor = lasteditor;
    }

    /**
     * @return lastedittime
     */
    public Date getLastedittime() {
        return lastedittime;
    }

    /**
     * @param lastedittime
     */
    public void setLastedittime(Date lastedittime) {
        this.lastedittime = lastedittime;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}