package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

public class Department {
    @Id
    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "lan_code")
    private String lanCode;

    @Column(name = "source_id")
    private String sourceId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "pa_department_id")
    private String paDepartmentId;

    @Column(name = "department_level")
    private Integer departmentLevel;

    @Column(name = "department_state")
    private Integer departmentState;

    @Column(name = "department_path")
    private String departmentPath;

    @Column(name = "reference_prop1")
    private String referenceProp1;

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

    private String creater;

    private Date createtime;

    private String lasteditor;

    private Date lastedittime;

    @Column(name = "reference_prop2")
    private String referenceProp2;

    @Column(name = "reference_prop3")
    private String referenceProp3;

    @Column(name = "reference_prop4")
    private String referenceProp4;

    @Column(name = "reference_prop5")
    private String referenceProp5;

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
     * @return department_name
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return pa_department_id
     */
    public String getPaDepartmentId() {
        return paDepartmentId;
    }

    /**
     * @param paDepartmentId
     */
    public void setPaDepartmentId(String paDepartmentId) {
        this.paDepartmentId = paDepartmentId;
    }

    /**
     * @return department_level
     */
    public Integer getDepartmentLevel() {
        return departmentLevel;
    }

    /**
     * @param departmentLevel
     */
    public void setDepartmentLevel(Integer departmentLevel) {
        this.departmentLevel = departmentLevel;
    }

    /**
     * @return department_state
     */
    public Integer getDepartmentState() {
        return departmentState;
    }

    /**
     * @param departmentState
     */
    public void setDepartmentState(Integer departmentState) {
        this.departmentState = departmentState;
    }

    /**
     * @return department_path
     */
    public String getDepartmentPath() {
        return departmentPath;
    }

    /**
     * @param departmentPath
     */
    public void setDepartmentPath(String departmentPath) {
        this.departmentPath = departmentPath;
    }

    /**
     * @return reference_prop1
     */
    public String getReferenceProp1() {
        return referenceProp1;
    }

    /**
     * @param referenceProp1
     */
    public void setReferenceProp1(String referenceProp1) {
        this.referenceProp1 = referenceProp1;
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

    /**
     * @return reference_prop2
     */
    public String getReferenceProp2() {
        return referenceProp2;
    }

    /**
     * @param referenceProp2
     */
    public void setReferenceProp2(String referenceProp2) {
        this.referenceProp2 = referenceProp2;
    }

    /**
     * @return reference_prop3
     */
    public String getReferenceProp3() {
        return referenceProp3;
    }

    /**
     * @param referenceProp3
     */
    public void setReferenceProp3(String referenceProp3) {
        this.referenceProp3 = referenceProp3;
    }

    /**
     * @return reference_prop4
     */
    public String getReferenceProp4() {
        return referenceProp4;
    }

    /**
     * @param referenceProp4
     */
    public void setReferenceProp4(String referenceProp4) {
        this.referenceProp4 = referenceProp4;
    }

    /**
     * @return reference_prop5
     */
    public String getReferenceProp5() {
        return referenceProp5;
    }

    /**
     * @param referenceProp5
     */
    public void setReferenceProp5(String referenceProp5) {
        this.referenceProp5 = referenceProp5;
    }
}