package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "view_resp_list")
public class ViewRespList {
    @Id
    @Column(name = "respcenter_id")
    private String respcenterId;

    @Column(name = "lan_name")
    private String lanName;

    @Column(name = "lan_code")
    private String lanCode;

    @Column(name = "source_id")
    private String sourceId;

    @Column(name = "respcenter_name")
    private String respcenterName;

    @Column(name = "respcenter_manager")
    private String respcenterManager;

    @Column(name = "respcenter_desc")
    private String respcenterDesc;

    @Column(name = "effective_date")
    private Date effectiveDate;

    @Column(name = "respcenter_state")
    private String respcenterState;

    /**
     * @return respcenter_id
     */
    public String getRespcenterId() {
        return respcenterId;
    }


    public String getLanCode() {
        return lanCode;
    }

    public void setLanCode(String lanCode) {
        this.lanCode = lanCode;
    }

    public String getRespcenterState() {
        return respcenterState;
    }

    public void setRespcenterState(String respcenterState) {
        this.respcenterState = respcenterState;
    }

    /**
     * @param respcenterId
     */
    public void setRespcenterId(String respcenterId) {
        this.respcenterId = respcenterId;
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
     * @return respcenter_name
     */
    public String getRespcenterName() {
        return respcenterName;
    }

    /**
     * @param respcenterName
     */
    public void setRespcenterName(String respcenterName) {
        this.respcenterName = respcenterName;
    }

    /**
     * @return respcenter_manager
     */
    public String getRespcenterManager() {
        return respcenterManager;
    }

    /**
     * @param respcenterManager
     */
    public void setRespcenterManager(String respcenterManager) {
        this.respcenterManager = respcenterManager;
    }


    /**
     * @return respcenter_desc
     */
    public String getRespcenterDesc() {
        return respcenterDesc;
    }

    /**
     * @param respcenterDesc
     */
    public void setRespcenterDesc(String respcenterDesc) {
        this.respcenterDesc = respcenterDesc;
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