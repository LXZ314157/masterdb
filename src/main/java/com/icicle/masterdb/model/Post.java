package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

public class Post {
    @Id
    @Column(name = "post_id")
    private String postId;

    @Column(name = "lan_code")
    private String lanCode;

    @Column(name = "source_id")
    private String sourceId;

    @Column(name = "post_name")
    private String postName;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "post_state")
    private Integer postState;

    @Column(name = "pa_post_id")
    private String paPostId;

    private String charger;

    @Column(name = "is_main")
    private Boolean isMain;

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
     * @return post_name
     */
    public String getPostName() {
        return postName;
    }

    /**
     * @param postName
     */
    public void setPostName(String postName) {
        this.postName = postName;
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
     * @return pa_post_id
     */
    public String getPaPostId() {
        return paPostId;
    }

    /**
     * @param paPostId
     */
    public void setPaPostId(String paPostId) {
        this.paPostId = paPostId;
    }

    /**
     * @return charger
     */
    public String getCharger() {
        return charger;
    }

    /**
     * @param charger
     */
    public void setCharger(String charger) {
        this.charger = charger;
    }

    /**
     * @return is_main
     */
    public Boolean getIsMain() {
        return isMain;
    }

    /**
     * @param isMain
     */
    public void setIsMain(Boolean isMain) {
        this.isMain = isMain;
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
}