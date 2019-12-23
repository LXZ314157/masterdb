package com.icicle.masterdb.pojo.vo;

import java.util.Date;

/**
 * @author lvxuezhan
 * @version 2019-08-09 17:00
 */
public class SyncPostVO {

    private String postId;

    private String lanCode;

    private String sourceId;

    private String postName;

    private String departmentId;

    private String paPostId;

    private String charger;

    private Integer postState;

    private Boolean isMain;

    private String referenceId1;

    private String referenceId2;

    private String referenceId3;

    private String referenceId4;

    private String referenceId5;

    private String creater;

    private Date createtime;

    private String lasteditor;

    private Date lastedittime;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getLanCode() {
        return lanCode;
    }

    public void setLanCode(String lanCode) {
        this.lanCode = lanCode;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getPaPostId() {
        return paPostId;
    }

    public void setPaPostId(String paPostId) {
        this.paPostId = paPostId;
    }

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

    public Integer getPostState() {
        return postState;
    }

    public void setPostState(Integer postState) {
        this.postState = postState;
    }

    public Boolean getMain() {
        return isMain;
    }

    public void setMain(Boolean main) {
        isMain = main;
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

    public String getReferenceId5() {
        return referenceId5;
    }

    public void setReferenceId5(String referenceId5) {
        this.referenceId5 = referenceId5;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
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
}
