package com.icicle.masterdb.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import javax.persistence.*;

public class Staff {
    @Id
    @Column(name = "staff_num")
    private String staffNum;

    @Column(name = "staff_name_local")
    private String staffNameLocal;

    @Column(name = "staff_name_en")
    private String staffNameEn;

    @Column(name = "cost_center_id")
    private String costCenterId;

    @Column(name = "credential_type")
    private String credentialType;


    @Column(name = "credential_num")
    private String credentialNum;

    private Date birthday;

    private String mobile;

    private String email;

    private String phone;

    private String extnum;

    private String doorcontrolnum;

    @Column(name = "staff_state")
    private Integer staffState;

    @Column(name = "join_date")
    private Date joinDate;

    @Column(name = "demission_date")
    private Date demissionDate;

    private String gender;

    @Column(name = "is_director")
    private Boolean isDirector;

    @Column(name = "post_id")
    private String postId;

    @Column(name = "staff_level")
    private String staffLevel;

    private String company;

    @Column(name = "superior_num")
    private String superiorNum;

    @Column(name = "office_location")
    private String officeLocation;

    @JSONField(name = "paris_level")
    private String parisLevel;

    @Column(name = "staff_type")
    private String staffType;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "job_sequence")
    private String jobSequence;

    private String league;

    @Column(name = "reference_id5")
    private String referenceId5;

    @Column(name = "reference_id1")
    private String referenceId1;

    @Column(name = "reference_id2")
    private String referenceId2;

    @Column(name = "reference_id3")
    private String referenceId3;

    @Column(name = "reference_id4")
    private String referenceId4;

    @Column(name = "reference_attrib5")
    private String referenceAttrib5;

    @Column(name = "reference_attrib1")
    private String referenceAttrib1;

    @Column(name = "reference_attrib2")
    private String referenceAttrib2;

    @Column(name = "reference_attrib3")
    private String referenceAttrib3;

    @Column(name = "reference_attrib4")
    private String referenceAttrib4;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    private String creater;

    private Date createtime;

    private String lasteditor;

    private Date lastedittime;


    public Boolean getDirector() {
        return isDirector;
    }

    public void setDirector(Boolean director) {
        isDirector = director;
    }

    public String getParisLevel() {
        return parisLevel;
    }

    public void setParisLevel(String parisLevel) {
        this.parisLevel = parisLevel;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
     * @return staff_name_en
     */
    public String getStaffNameEn() {
        return staffNameEn;
    }

    /**
     * @param staffNameEn
     */
    public void setStaffNameEn(String staffNameEn) {
        this.staffNameEn = staffNameEn;
    }

    /**
     * @return cost_center_id
     */
    public String getCostCenterId() {
        return costCenterId;
    }

    /**
     * @param costCenterId
     */
    public void setCostCenterId(String costCenterId) {
        this.costCenterId = costCenterId;
    }

    /**
     * @return credential_type
     */
    public String getCredentialType() {
        return credentialType;
    }

    /**
     * @param credentialType
     */
    public void setCredentialType(String credentialType) {
        this.credentialType = credentialType;
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
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return extnum
     */
    public String getExtnum() {
        return extnum;
    }

    /**
     * @param extnum
     */
    public void setExtnum(String extnum) {
        this.extnum = extnum;
    }

    /**
     * @return doorcontrolnum
     */
    public String getDoorcontrolnum() {
        return doorcontrolnum;
    }

    /**
     * @param doorcontrolnum
     */
    public void setDoorcontrolnum(String doorcontrolnum) {
        this.doorcontrolnum = doorcontrolnum;
    }

    /**
     * @return staff_state
     */
    public Integer getStaffState() {
        return staffState;
    }

    /**
     * @param staffState
     */
    public void setStaffState(Integer staffState) {
        this.staffState = staffState;
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
     * @return demission_date
     */
    public Date getDemissionDate() {
        return demissionDate;
    }

    /**
     * @param demissionDate
     */
    public void setDemissionDate(Date demissionDate) {
        this.demissionDate = demissionDate;
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
     * @return is_director
     */
    public Boolean getIsDirector() {
        return isDirector;
    }

    /**
     * @param isDirector
     */
    public void setIsDirector(Boolean isDirector) {
        this.isDirector = isDirector;
    }

    /**
     * @return post_id
     */
    public String getPostId() {
        return postId;
    }

    /**
     * @param postId
     */
    public void setPostId(String postId) {
        this.postId = postId;
    }

    /**
     * @return staff_level
     */
    public String getStaffLevel() {
        return staffLevel;
    }

    /**
     * @param staffLevel
     */
    public void setStaffLevel(String staffLevel) {
        this.staffLevel = staffLevel;
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
     * @return superior_num
     */
    public String getSuperiorNum() {
        return superiorNum;
    }

    /**
     * @param superiorNum
     */
    public void setSuperiorNum(String superiorNum) {
        this.superiorNum = superiorNum;
    }

    /**
     * @return office_location
     */
    public String getOfficeLocation() {
        return officeLocation;
    }

    /**
     * @param officeLocation
     */
    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    /**
     * @return staff_type
     */
    public String getStaffType() {
        return staffType;
    }

    /**
     * @param staffType
     */
    public void setStaffType(String staffType) {
        this.staffType = staffType;
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
     * @return job_sequence
     */
    public String getJobSequence() {
        return jobSequence;
    }

    /**
     * @param jobSequence
     */
    public void setJobSequence(String jobSequence) {
        this.jobSequence = jobSequence;
    }

    /**
     * @return league
     */
    public String getLeague() {
        return league;
    }

    /**
     * @param league
     */
    public void setLeague(String league) {
        this.league = league;
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
}