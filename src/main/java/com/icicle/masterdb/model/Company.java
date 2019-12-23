package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

public class Company {
    @Id
    @Column(name = "company_id")
    private String companyId;

    @Column(name = "company_name_local")
    private String companyNameLocal;

    @Column(name = "company_name_en")
    private String companyNameEn;

    @Column(name = "company_state")
    private Integer companyState;

    private String creater;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

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

    @Column(name = "reference_id6")
    private String referenceId6;

    @Column(name = "reference_id7")
    private String referenceId7;

    @Column(name = "reference_id8")
    private String referenceId8;

    @Column(name = "reference_id9")
    private String referenceId9;

    @Column(name = "reference_id10")
    private String referenceId10;

    /**
     * @return company_id
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * @return company_name_local
     */
    public String getCompanyNameLocal() {
        return companyNameLocal;
    }

    /**
     * @param companyNameLocal
     */
    public void setCompanyNameLocal(String companyNameLocal) {
        this.companyNameLocal = companyNameLocal;
    }

    /**
     * @return company_name_en
     */
    public String getCompanyNameEn() {
        return companyNameEn;
    }

    /**
     * @param companyNameEn
     */
    public void setCompanyNameEn(String companyNameEn) {
        this.companyNameEn = companyNameEn;
    }

    /**
     * @return company_state
     */
    public Integer getCompanyState() {
        return companyState;
    }

    /**
     * @param companyState
     */
    public void setCompanyState(Integer companyState) {
        this.companyState = companyState;
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
     * @return last_updated_by
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return last_update_date
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * @param lastUpdateDate
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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
     * @return reference_id6
     */
    public String getReferenceId6() {
        return referenceId6;
    }

    /**
     * @param referenceId6
     */
    public void setReferenceId6(String referenceId6) {
        this.referenceId6 = referenceId6;
    }

    /**
     * @return reference_id7
     */
    public String getReferenceId7() {
        return referenceId7;
    }

    /**
     * @param referenceId7
     */
    public void setReferenceId7(String referenceId7) {
        this.referenceId7 = referenceId7;
    }

    /**
     * @return reference_id8
     */
    public String getReferenceId8() {
        return referenceId8;
    }

    /**
     * @param referenceId8
     */
    public void setReferenceId8(String referenceId8) {
        this.referenceId8 = referenceId8;
    }

    /**
     * @return reference_id9
     */
    public String getReferenceId9() {
        return referenceId9;
    }

    /**
     * @param referenceId9
     */
    public void setReferenceId9(String referenceId9) {
        this.referenceId9 = referenceId9;
    }

    /**
     * @return reference_id10
     */
    public String getReferenceId10() {
        return referenceId10;
    }

    /**
     * @param referenceId10
     */
    public void setReferenceId10(String referenceId10) {
        this.referenceId10 = referenceId10;
    }
}