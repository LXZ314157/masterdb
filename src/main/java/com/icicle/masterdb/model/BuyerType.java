package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "buyer_type")
public class BuyerType {
    @Id
    @Column(name = "buyer_type_id")
    private Integer buyerTypeId;

    @Column(name = "buyer_type_name")
    private String buyerTypeName;

    @Column(name = "buyer_type_desc")
    private String buyerTypeDesc;

    private Integer status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    /**
     * @return buyer_type_id
     */
    public Integer getBuyerTypeId() {
        return buyerTypeId;
    }

    /**
     * @param buyerTypeId
     */
    public void setBuyerTypeId(Integer buyerTypeId) {
        this.buyerTypeId = buyerTypeId;
    }

    /**
     * @return buyer_type_name
     */
    public String getBuyerTypeName() {
        return buyerTypeName;
    }

    /**
     * @param buyerTypeName
     */
    public void setBuyerTypeName(String buyerTypeName) {
        this.buyerTypeName = buyerTypeName;
    }

    /**
     * @return buyer_type_desc
     */
    public String getBuyerTypeDesc() {
        return buyerTypeDesc;
    }

    /**
     * @param buyerTypeDesc
     */
    public void setBuyerTypeDesc(String buyerTypeDesc) {
        this.buyerTypeDesc = buyerTypeDesc;
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
}