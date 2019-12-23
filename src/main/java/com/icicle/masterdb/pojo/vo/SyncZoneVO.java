package com.icicle.masterdb.pojo.vo;

import java.util.Date;

/**
 * @author lvxuezhan
 * @version 2019-09-16 15:42
 */
public class SyncZoneVO {

    /** 用户编号 必填 */
    private String userno;
    /** 用户名 必填 */
    private String userName;
    /** 部门编号 必填*/
    private String orgOuCode;
    /** 部门名称 必填 */
    private String ouName;
    /** 岗位编号 */
    private String postionNo;
    /** 上级部门编号 */
    private String parentID;
    /** 直接上级编号 必填*/
    private String superiorNum;

    /** 性别 */
    private String sex;
    /** 生日 */
    private Date birthday;
    /** 手机 */
    private String mobile;
    /** 邮箱 */
    private String mail;
    /** 入职时间 2014-03-31 */
    private Date entrytime;

    /** 代理商模式 */
    private String buyerPattern;


    public String getBuyerPattern() {
        return buyerPattern;
    }

    public void setBuyerPattern(String buyerPattern) {
        this.buyerPattern = buyerPattern;
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno;
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

    public String getOuName() {
        return ouName;
    }

    public void setOuName(String ouName) {
        this.ouName = ouName;
    }

    public String getPostionNo() {
        return postionNo;
    }

    public void setPostionNo(String postionNo) {
        this.postionNo = postionNo;
    }

    public String getSuperiorNum() {
        return superiorNum;
    }

    public void setSuperiorNum(String superiorNum) {
        this.superiorNum = superiorNum;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }
}
