package com.icicle.masterdb.model;

import javax.persistence.*;

@Table(name = "view_post_list")
public class ViewPostList {
    @Id
    @Column(name = "post_id")
    private String postId;

    @Column(name = "lan_name")
    private String lanName;

    @Column(name = "lan_code")
    private String lanCode;

    @Column(name = "source_id")
    private String sourceId;

    @Column(name = "post_name")
    private String postName;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "pa_post_id")
    private String paPostId;

    @Column(name = "staff_name_local")
    private String staffNameLocal;

    @Column(name = "reference_id2")
    private String referenceId2;

    public String getReferenceId2() {
        return referenceId2;
    }

    public void setReferenceId2(String referenceId2) {
        this.referenceId2 = referenceId2;
    }

    public String getLanCode() {
        return lanCode;
    }

    public void setLanCode(String lanCode) {
        this.lanCode = lanCode;
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
     * @return lan_name
     */
    public String getLanName() {
        return lanName;
    }

    /**
     * @param lanName
     */
    public void setLanName(String lanName) {
        this.lanName = lanName;
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
}