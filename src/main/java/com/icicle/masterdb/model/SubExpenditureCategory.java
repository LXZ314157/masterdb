package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sub_expenditure_category")
public class SubExpenditureCategory {
    @Id
    @Column(name = "sub_excategory_id")
    private String subExcategoryId;

    @Column(name = "lan_code")
    private String lanCode;

    @Column(name = "source_id")
    private String sourceId;

    @Column(name = "sub_excategory_name")
    private String subExcategoryName;

    private Integer extype;

    @Column(name = "costcenter_id")
    private String costcenterId;

    @Column(name = "sub_excategory_desc")
    private String subExcategoryDesc;

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

    @Column(name = "sub_excategory_state")
    private String subExcategoryState;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_edit_by")
    private String lastEditBy;

    @Column(name = "last_edit_time")
    private Date lastEditTime;

    /**
     * @return sub_excategory_id
     */
    public String getSubExcategoryId() {
        return subExcategoryId;
    }

    /**
     * @param subExcategoryId
     */
    public void setSubExcategoryId(String subExcategoryId) {
        this.subExcategoryId = subExcategoryId;
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
     * @return sub_excategory_name
     */
    public String getSubExcategoryName() {
        return subExcategoryName;
    }

    /**
     * @param subExcategoryName
     */
    public void setSubExcategoryName(String subExcategoryName) {
        this.subExcategoryName = subExcategoryName;
    }

    /**
     * @return extype
     */
    public Integer getExtype() {
        return extype;
    }

    /**
     * @param extype
     */
    public void setExtype(Integer extype) {
        this.extype = extype;
    }

    /**
     * @return costcenter_id
     */
    public String getCostcenterId() {
        return costcenterId;
    }

    /**
     * @param costcenterId
     */
    public void setCostcenterId(String costcenterId) {
        this.costcenterId = costcenterId;
    }

    /**
     * @return sub_excategory_desc
     */
    public String getSubExcategoryDesc() {
        return subExcategoryDesc;
    }

    /**
     * @param subExcategoryDesc
     */
    public void setSubExcategoryDesc(String subExcategoryDesc) {
        this.subExcategoryDesc = subExcategoryDesc;
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


    public String getSubExcategoryState() {
        return subExcategoryState;
    }

    public void setSubExcategoryState(String subExcategoryState) {
        this.subExcategoryState = subExcategoryState;
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