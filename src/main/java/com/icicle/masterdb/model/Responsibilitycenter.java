package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

public class Responsibilitycenter {
    @Id
    @Column(name = "respcenter_id")
    private String respcenterId;

    @Column(name = "lan_code")
    private String lanCode;

    @Column(name = "source_id")
    private String sourceId;

    @Column(name = "respcenter_name")
    private String respcenterName;

    @Column(name = "respcenter_manager")
    private String respcenterManager;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "respcenter_desc")
    private String respcenterDesc;

    @Column(name = "respcenter_state")
    private Integer respcenterState;

    @Column(name = "effective_date")
    private Date effectiveDate;

    @Column(name = "reference_id1")
    private String referenceId1;

    @Column(name = "reference_id2")
    private String referenceId2;

    @Column(name = "reference_id3")
    private String referenceId3;

    @Column(name = "reference_id4")
    private String referenceId4;

    @Column(name = "reference_id5")
    private String referenceId5;

    @Column(name = "reference_attrib1")
    private String referenceAttrib1;

    @Column(name = "reference_attrib2")
    private String referenceAttrib2;

    @Column(name = "reference_attrib3")
    private String referenceAttrib3;

    @Column(name = "reference_attrib4")
    private String referenceAttrib4;

    @Column(name = "reference_attrib5")
    private String referenceAttrib5;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_edit_by")
    private String lastEditBy;

    @Column(name = "last_edit_time")
    private Date lastEditTime;

    /**
     * @return respcenter_id
     */
    public String getRespcenterId() {
        return respcenterId;
    }

    /**
     * @param respcenterId
     */
    public void setRespcenterId(String respcenterId) {
        this.respcenterId = respcenterId;
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
     * @return respcenter_name
     */
    public String getRespcenterName() {
        return respcenterName;
    }

    /**
     * @param respcenterName
     */
    public void setRespcenterName(String respcenterName) {
        this.respcenterName = respcenterName;
    }

    /**
     * @return respcenter_manager
     */
    public String getRespcenterManager() {
        return respcenterManager;
    }

    /**
     * @param respcenterManager
     */
    public void setRespcenterManager(String respcenterManager) {
        this.respcenterManager = respcenterManager;
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
     * @return respcenter_desc
     */
    public String getRespcenterDesc() {
        return respcenterDesc;
    }

    /**
     * @param respcenterDesc
     */
    public void setRespcenterDesc(String respcenterDesc) {
        this.respcenterDesc = respcenterDesc;
    }

    /**
     * @return respcenter_state
     */
    public Integer getRespcenterState() {
        return respcenterState;
    }

    /**
     * @param respcenterState
     */
    public void setRespcenterState(Integer respcenterState) {
        this.respcenterState = respcenterState;
    }

    /**
     * @return effective_date
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * @param effectiveDate
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * @return reference_id1
     */
    public String getReferenceId1() {
        return referenceId1;
    }

    /**
     * @param referenceId1
     */
    public void setReferenceId1(String referenceId1) {
        this.referenceId1 = referenceId1;
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
     * @return reference_attrib1
     */
    public String getReferenceAttrib1() {
        return referenceAttrib1;
    }

    /**
     * @param referenceAttrib1
     */
    public void setReferenceAttrib1(String referenceAttrib1) {
        this.referenceAttrib1 = referenceAttrib1;
    }

    /**
     * @return reference_attrib2
     */
    public String getReferenceAttrib2() {
        return referenceAttrib2;
    }

    /**
     * @param referenceAttrib2
     */
    public void setReferenceAttrib2(String referenceAttrib2) {
        this.referenceAttrib2 = referenceAttrib2;
    }

    /**
     * @return reference_attrib3
     */
    public String getReferenceAttrib3() {
        return referenceAttrib3;
    }

    /**
     * @param referenceAttrib3
     */
    public void setReferenceAttrib3(String referenceAttrib3) {
        this.referenceAttrib3 = referenceAttrib3;
    }

    /**
     * @return reference_attrib4
     */
    public String getReferenceAttrib4() {
        return referenceAttrib4;
    }

    /**
     * @param referenceAttrib4
     */
    public void setReferenceAttrib4(String referenceAttrib4) {
        this.referenceAttrib4 = referenceAttrib4;
    }

    /**
     * @return reference_attrib5
     */
    public String getReferenceAttrib5() {
        return referenceAttrib5;
    }

    /**
     * @param referenceAttrib5
     */
    public void setReferenceAttrib5(String referenceAttrib5) {
        this.referenceAttrib5 = referenceAttrib5;
    }

    /**
     * @return create_by
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return last_edit_by
     */
    public String getLastEditBy() {
        return lastEditBy;
    }

    /**
     * @param lastEditBy
     */
    public void setLastEditBy(String lastEditBy) {
        this.lastEditBy = lastEditBy;
    }

    /**
     * @return last_edit_time
     */
    public Date getLastEditTime() {
        return lastEditTime;
    }

    /**
     * @param lastEditTime
     */
    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}