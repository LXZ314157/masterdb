package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "view_sub_ex_list")
public class ViewSubExList {
    @Id
    @Column(name = "sub_excategory_id")
    private String subExcategoryId;

    @Column(name = "lan_code")
    private String lanCode;

    @Column(name = "lan_name")
    private String lanName;

    @Column(name = "source_id")
    private String sourceId;

    @Column(name = "sub_excategory_state")
    private String subExcategoryState;

    @Column(name = "sub_excategory_name")
    private String subExcategoryName;

    private Integer extype;

    @Column(name = "costcenter_id")
    private String costcenterId;

    @Column(name = "sub_excategory_desc")
    private String subExcategoryDesc;

    @Column(name = "effective_date")
    private Date effectiveDate;


    public String getSubExcategoryState() {
        return subExcategoryState;
    }

    public void setSubExcategoryState(String subExcategoryState) {
        this.subExcategoryState = subExcategoryState;
    }

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

    public String getLanCode() {
        return lanCode;
    }

    public void setLanCode(String lanCode) {
        this.lanCode = lanCode;
    }

    public String getLanName() {
        return lanName;
    }

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
}