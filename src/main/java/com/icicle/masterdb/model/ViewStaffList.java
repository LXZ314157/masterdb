package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "view_staff_list")
public class ViewStaffList {

    @Id
    @Column(name = "staff_num")
    private String staffNum;

    @Column(name = "staff_name_local")
    private String staffNameLocal;

    @Column(name = "join_date")
    private Date joinDate;

    private String gender;

    @Column(name = "staff_state")
    private Integer staffState;

    @Column(name = "staff_state_name")
    private String staffStateName;

    @Column(name = "credential_num")
    private String credentialNum;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "reference_id1")
    private String referenceId1;

    @Column(name = "reference_id2")
    private String referenceId2;

    @Column(name = "post_name")
    private String postName;

    private String company;

    private String mobile;

    /**
     * @return staff_num
     */
    public String getStaffNum() {
        return staffNum;
    }

    public String getReferenceId1() {
        return referenceId1;
    }

    public void setReferenceId1(String referenceId1) {
        this.referenceId1 = referenceId1;
    }

    public String getReferenceId2() {
        return referenceId2;
    }

    public void setReferenceId2(String referenceId2) {
        this.referenceId2 = referenceId2;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    /**
     * @param staffNum
     */
    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum;
    }

    public Integer getStaffState() {
        return staffState;
    }

    public String getStaffStateName() {
        return staffStateName;
    }

    public void setStaffStateName(String staffStateName) {
        this.staffStateName = staffStateName;
    }

    public void setStaffState(Integer staffState) {
        this.staffState = staffState;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return staff_name_local
     */
    public String getStaffNameLocal() {
        return staffNameLocal;
    }

    /**
     * @param staffNameLocal
     */
    public void setStaffNameLocal(String staffNameLocal) {
        this.staffNameLocal = staffNameLocal;
    }

    /**
     * @return join_date
     */
    public Date getJoinDate() {
        return joinDate;
    }

    /**
     * @param joinDate
     */
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return credential_num
     */
    public String getCredentialNum() {
        return credentialNum;
    }

    /**
     * @param credentialNum
     */
    public void setCredentialNum(String credentialNum) {
        this.credentialNum = credentialNum;
    }

    /**
     * @return company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}