package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * @author liurenhua
 */
@Table(name = "unit_of_measure")
public class UnitOfMeasure {
    @Id
    private Integer id;

    @Column(name = "uom_code")
    private String uomCode;

    @Column(name = "unit_of_measure")
    private String unitOfMeasure;

    @Column(name = "base_uom_flag")
    private Boolean baseUomFlag;

    @Column(name = "uom_class")
    private String uomClass;

    @Column(name = "uom_description")
    private String uomDescription;

    private String language;

    private Integer status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "disable_date")
    private Date disableDate;

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
     * @return uom_code
     */
    public String getUomCode() {
        return uomCode;
    }

    /**
     * @param uomCode
     */
    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    /**
     * @return unit_of_measure
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * @param unitOfMeasure
     */
    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * @return base_uom_flag
     */
    public Boolean getBaseUomFlag() {
        return baseUomFlag;
    }

    /**
     * @param baseUomFlag
     */
    public void setBaseUomFlag(Boolean baseUomFlag) {
        this.baseUomFlag = baseUomFlag;
    }

    /**
     * @return uom_class
     */
    public String getUomClass() {
        return uomClass;
    }

    /**
     * @param uomClass
     */
    public void setUomClass(String uomClass) {
        this.uomClass = uomClass;
    }

    /**
     * @return uom_description
     */
    public String getUomDescription() {
        return uomDescription;
    }

    /**
     * @param uomDescription
     */
    public void setUomDescription(String uomDescription) {
        this.uomDescription = uomDescription;
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

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return created_by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return creation_date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
     * @return disable_date
     */
    public Date getDisableDate() {
        return disableDate;
    }

    /**
     * @param disableDate
     */
    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }
}