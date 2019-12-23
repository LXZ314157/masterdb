


package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
/**
 * @author liurenhua
 */
public class Fabric {

    @Column(name = "fabric_code")
    private String fabricCode;

    @Column(name = "fabric_name")
    private String fabricName;

    @Column(name = "has_feature")
    private Boolean hasFeature;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "last_update_by")
    private String lastUpdateBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "fabric_feature")
    private String fabricFeature;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer status;

    private String language;


    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return fabric_code
     */
    public String getFabricCode() {
        return fabricCode;
    }

    /**
     * @param fabricCode
     */
    public void setFabricCode(String fabricCode) {
        this.fabricCode = fabricCode;
    }

    /**
     * @return fabric_name
     */
    public String getFabricName() {
        return fabricName;
    }

    /**
     * @param fabricName
     */
    public void setFabricName(String fabricName) {
        this.fabricName = fabricName;
    }

    /**
     * @return fabric_feature
     */
    public String getFabricFeature() {
        return fabricFeature;
    }

    /**
     * @param fabricFeature
     */
    public void setFabricFeature(String fabricFeature) {
        this.fabricFeature = fabricFeature;
    }

    /**
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getHasFeature() {
        return hasFeature;
    }

    public void setHasFeature(Boolean hasFeature) {
        this.hasFeature = hasFeature;
    }
}