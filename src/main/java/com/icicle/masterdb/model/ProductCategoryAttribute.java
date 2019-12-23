package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "product_extend_attribute_group")
public class ProductCategoryAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_category_id")
    private Integer productCategoryId;

    @Column(name = "product_attribute_define_id")
    private Integer productAttributeDefineId;

    @Column(name = "category_attribute_state")
    private Integer categoryAttributeState;

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
     * @return product_category_id
     */
    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    /**
     * @param productCategoryId
     */
    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    /**
     * @return product_attribute_define_id
     */
    public Integer getProductAttributeDefineId() {
        return productAttributeDefineId;
    }

    /**
     * @param productAttributeDefineId
     */
    public void setProductAttributeDefineId(Integer productAttributeDefineId) {
        this.productAttributeDefineId = productAttributeDefineId;
    }

    /**
     * @return category_attribute_state
     */
    public Integer getCategoryAttributeState() {
        return categoryAttributeState;
    }

    /**
     * @param categoryAttributeState
     */
    public void setCategoryAttributeState(Integer categoryAttributeState) {
        this.categoryAttributeState = categoryAttributeState;
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