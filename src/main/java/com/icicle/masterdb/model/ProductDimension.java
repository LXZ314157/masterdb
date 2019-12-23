package com.icicle.masterdb.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "product_dimension")
public class ProductDimension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "class_dimension_code")
    private String classDimensionCode;

    @Column(name = "class_dimension_name")
    private String classDimensionName;

    @Column(name = "class_dimension_desc")
    private String classDimensionDesc;

    private Boolean status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

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
     * @return class_dimension_code
     */
    public String getClassDimensionCode() {
        return classDimensionCode;
    }

    /**
     * @param classDimensionCode
     */
    public void setClassDimensionCode(String classDimensionCode) {
        this.classDimensionCode = classDimensionCode;
    }

    /**
     * @return class_dimension_name
     */
    public String getClassDimensionName() {
        return classDimensionName;
    }

    /**
     * @param classDimensionName
     */
    public void setClassDimensionName(String classDimensionName) {
        this.classDimensionName = classDimensionName;
    }

    /**
     * @return class_dimension_desc
     */
    public String getClassDimensionDesc() {
        return classDimensionDesc;
    }

    /**
     * @param classDimensionDesc
     */
    public void setClassDimensionDesc(String classDimensionDesc) {
        this.classDimensionDesc = classDimensionDesc;
    }

    /**
     * @return status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Boolean status) {
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
}