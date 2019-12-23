package com.icicle.masterdb.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "dimension_attribute_group")
public class ProductDimensionAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dimension_id")
    private Integer dimensionId;

    @Column(name = "attribute_id")
    private Integer attributeId;

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
     * @return dimension_id
     */
    public Integer getDimensionId() {
        return dimensionId;
    }

    /**
     * @param dimensionId
     */
    public void setDimensionId(Integer dimensionId) {
        this.dimensionId = dimensionId;
    }

    /**
     * @return attribute_id
     */
    public Integer getAttributeId() {
        return attributeId;
    }

    /**
     * @param attributeId
     */
    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
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

    @Override
    public boolean equals(Object obj) {
        if (Objects.equals(obj.getClass().getName(), this.getClass().getName())) {
            ProductDimensionAttribute s = (ProductDimensionAttribute) obj;
            boolean same = Objects.equals(s.getAttributeId(), this.getAttributeId())
                    && Objects.equals(s.getDimensionId(), this.getDimensionId());
            return same;
        } else {
            return super.equals(obj);
        }
    }
}