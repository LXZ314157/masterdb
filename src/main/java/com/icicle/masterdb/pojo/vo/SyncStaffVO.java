package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.Company;
import com.icicle.masterdb.model.Staff;

import java.util.Date;
import java.util.List;

/**
 * @author lvxuezhan
 * @version 2019-01-24 17:17
 */
public class SyncStaffVO {

    private String staffNum;

    private String staffNameLocal;

    private String staffNameEn;

    private String costCenterId;

    private String credentialType;

    private String credentialNum;

    private Date birthday;

    private String mobile;

    private String email;

    private String phone;

    private String extnum;

    private String officeLocationName;

    private String doorcontrolnum;

    private Integer staffState;

    private Date joinDate;

    private Date demissionDate;

    private String gender;

    private Boolean isDirector;

    private String parisLevel;

    private String postId;

    private String staffLevel;

    private String company;

    private String locationName;

    private String superiorNum;

    private String officeLocation;

    private String staffType;

    private String departmentId;

    private String jobSequence;

    private String league;

    private String referenceId5;

    private String referenceId1;

    private String referenceId2;

    private String referenceId3;

    private String referenceId4;

    private String referenceAttrib5;

    private String referenceAttrib1;

    private String referenceAttrib2;

    private String referenceAttrib3;

    private String referenceAttrib4;

    private String creater;

    private Date createtime;

    private String lasteditor;

    private Date lastedittime;

    private String firstname;

    private String lastname;

    private String workerType;

    private Staff superior;

    private List<DepartmentVO> departments;

    private Company companyEntity;

    private StaffStoreVO storeStaff;

    private List<PostVO> posts;

    public String getWorkerType() {
        return workerType;
    }

    public void setWorkerType(String workerType) {
        this.workerType = workerType;
    }

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum;
    }

    public String getStaffNameLocal() {
        return staffNameLocal;
    }

    public void setStaffNameLocal(String staffNameLocal) {
        this.staffNameLocal = staffNameLocal;
    }

    public String getStaffNameEn() {
        return staffNameEn;
    }

    public void setStaffNameEn(String staffNameEn) {
        this.staffNameEn = staffNameEn;
    }

    public String getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(String costCenterId) {
        this.costCenterId = costCenterId;
    }

    public String getCredentialType() {
        return credentialType;
    }

    public void setCredentialType(String credentialType) {
        this.credentialType = credentialType;
    }

    public String getCredentialNum() {
        return credentialNum;
    }

    public void setCredentialNum(String credentialNum) {
        this.credentialNum = credentialNum;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StaffStoreVO getStoreStaff() {
        return storeStaff;
    }

    public void setStoreStaff(StaffStoreVO storeStaff) {
        this.storeStaff = storeStaff;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExtnum() {
        return extnum;
    }

    public void setExtnum(String extnum) {
        this.extnum = extnum;
    }

    public String getDoorcontrolnum() {
        return doorcontrolnum;
    }

    public void setDoorcontrolnum(String doorcontrolnum) {
        this.doorcontrolnum = doorcontrolnum;
    }

    public Integer getStaffState() {
        return staffState;
    }

    public void setStaffState(Integer staffState) {
        this.staffState = staffState;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getDemissionDate() {
        return demissionDate;
    }

    public void setDemissionDate(Date demissionDate) {
        this.demissionDate = demissionDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getDirector() {
        return isDirector;
    }

    public void setDirector(Boolean director) {
        isDirector = director;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getStaffLevel() {
        return staffLevel;
    }

    public void setStaffLevel(String staffLevel) {
        this.staffLevel = staffLevel;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSuperiorNum() {
        return superiorNum;
    }

    public void setSuperiorNum(String superiorNum) {
        this.superiorNum = superiorNum;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getJobSequence() {
        return jobSequence;
    }

    public void setJobSequence(String jobSequence) {
        this.jobSequence = jobSequence;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getReferenceId5() {
        return referenceId5;
    }

    public void setReferenceId5(String referenceId5) {
        this.referenceId5 = referenceId5;
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

    public String getReferenceId3() {
        return referenceId3;
    }

    public void setReferenceId3(String referenceId3) {
        this.referenceId3 = referenceId3;
    }

    public String getReferenceId4() {
        return referenceId4;
    }

    public void setReferenceId4(String referenceId4) {
        this.referenceId4 = referenceId4;
    }

    public String getReferenceAttrib5() {
        return referenceAttrib5;
    }

    public void setReferenceAttrib5(String referenceAttrib5) {
        this.referenceAttrib5 = referenceAttrib5;
    }

    public String getReferenceAttrib1() {
        return referenceAttrib1;
    }

    public void setReferenceAttrib1(String referenceAttrib1) {
        this.referenceAttrib1 = referenceAttrib1;
    }

    public String getReferenceAttrib2() {
        return referenceAttrib2;
    }

    public void setReferenceAttrib2(String referenceAttrib2) {
        this.referenceAttrib2 = referenceAttrib2;
    }

    public String getReferenceAttrib3() {
        return referenceAttrib3;
    }

    public void setReferenceAttrib3(String referenceAttrib3) {
        this.referenceAttrib3 = referenceAttrib3;
    }

    public String getReferenceAttrib4() {
        return referenceAttrib4;
    }

    public void setReferenceAttrib4(String referenceAttrib4) {
        this.referenceAttrib4 = referenceAttrib4;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getLasteditor() {
        return lasteditor;
    }

    public void setLasteditor(String lasteditor) {
        this.lasteditor = lasteditor;
    }

    public Date getLastedittime() {
        return lastedittime;
    }

    public void setLastedittime(Date lastedittime) {
        this.lastedittime = lastedittime;
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

    public Staff getSuperior() {
        return superior;
    }

    public void setSuperior(Staff superior) {
        this.superior = superior;
    }

    public Company getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(Company companyEntity) {
        this.companyEntity = companyEntity;
    }

    public List<PostVO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostVO> posts) {
        this.posts = posts;
    }

    public List<DepartmentVO> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentVO> departments) {
        this.departments = departments;
    }


    public String getOfficeLocationName() {
        return officeLocationName;
    }

    public void setOfficeLocationName(String officeLocationName) {
        this.officeLocationName = officeLocationName;
    }

    public String getParisLevel() {
        return parisLevel;
    }

    public void setParisLevel(String parisLevel) {
        this.parisLevel = parisLevel;
    }
}

