package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "view_findbyclothes_type")
public class ViewFindbyclothesType {
    @Id
    private Integer systemid;

    @Column(name = "master_type_id")
    private String masterTypeId;

    @Column(name = "system_code")
    private String systemCode;

    private String ca1;

    private String ca2;

    private String ca3;

    /**
     * @return systemid
     */
    public Integer getSystemid() {
        return systemid;
    }

    /**
     * @param systemid
     */
    public void setSystemid(Integer systemid) {
        this.systemid = systemid;
    }

    /**
     * @return master_type_id
     */
    public String getMasterTypeId() {
        return masterTypeId;
    }

    /**
     * @param masterTypeId
     */
    public void setMasterTypeId(String masterTypeId) {
        this.masterTypeId = masterTypeId;
    }

    /**
     * @return system_code
     */
    public String getSystemCode() {
        return systemCode;
    }

    /**
     * @param systemCode
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    /**
     * @return ca1
     */
    public String getCa1() {
        return ca1;
    }

    /**
     * @param ca1
     */
    public void setCa1(String ca1) {
        this.ca1 = ca1;
    }

    /**
     * @return ca2
     */
    public String getCa2() {
        return ca2;
    }

    /**
     * @param ca2
     */
    public void setCa2(String ca2) {
        this.ca2 = ca2;
    }

    /**
     * @return ca3
     */
    public String getCa3() {
        return ca3;
    }

    /**
     * @param ca3
     */
    public void setCa3(String ca3) {
        this.ca3 = ca3;
    }
}