package com.icicle.masterdb.pojo.vo;

import java.util.Date;

/**
 * @author lvxuezhan
 * @version 2019-04-29 10:19
 */
public class ManagerVO {

    private String userno;
    private String userName;
    private String sex;
    private Date birthday;
    private String mobile;
    private String mail;
    private Date entrytime;
    private String orgOuCode;
    private String ouName;
    private String postionNo;
    private String pNames;
    private String parentID;

    public String getOuName() {
        return ouName;
    }

    public void setOuName(String ouName) {
        this.ouName = ouName;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getpNames() {
        return pNames;
    }

    public void setpNames(String pNames) {
        this.pNames = pNames;
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno;
    }

    public String getPostionNo() {
        return postionNo;
    }

    public void setPostionNo(String postionNo) {
        this.postionNo = postionNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getEntrytime() {
        return entrytime;
    }

    public void setEntrytime(Date entrytime) {
        this.entrytime = entrytime;
    }

    public String getOrgOuCode() {
        return orgOuCode;
    }

    public void setOrgOuCode(String orgOuCode) {
        this.orgOuCode = orgOuCode;
    }
}
