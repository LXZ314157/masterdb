package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "view_ex_list")
public class ViewExList {
    @Id
    @Column(name = "excategory_id")
    private String excategoryId;

    @Column(name = "lan_name")
    private String lanName;

    @Column(name = "lan_code")
    private String lanCode;

    @Column(name = "source_id")
    private String sourceId;

    @Column(name = "excategory_name")
    private String excategoryName;

    private Integer extype;

    @Column(name = "costcenter_id")
    private String costcenterId;

    @Column(name = "excategory_desc")
    private String excategoryDesc;

    @Column(name = "effective_date")
    private Date effectiveDate;

    /**
     * @return excategory_id
     */
    public String getExcategoryId() {
        return excategoryId;
    }

    /**
     * @param excategoryId
     */
    public void setExcategoryId(String excategoryId) {
        this.excategoryId = excategoryId;
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

    public String getLanCode() {
        return lanCode;
    }

    public void setLanCode(String lanCode) {
        this.lanCode = lanCode;
    }

    /**
     * @param sourceId
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * @return excategory_name
     */
    public String getExcategoryName() {
        return excategoryName;
    }

    /**
     * @param excategoryName
     */
    public void setExcategoryName(String excategoryName) {
        this.excategoryName = excategoryName;
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
     * @return excategory_desc
     */
    public String getExcategoryDesc() {
        return excategoryDesc;
    }

    /**
     * @param excategoryDesc
     */
    public void setExcategoryDesc(String excategoryDesc) {
        this.excategoryDesc = excategoryDesc;
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