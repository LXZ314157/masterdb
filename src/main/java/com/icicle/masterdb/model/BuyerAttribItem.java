package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "buyer_attrib_item")
public class BuyerAttribItem {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "buyer_attrib_item_id")
    private Integer buyerAttribItemId;

    @Column(name = "buyer_attrib_def_id")
    private Integer buyerAttribDefId;

    @Column(name = "buyer_attrib_item_code")
    private String buyerAttribItemCode;

    @Column(name = "buyer_attrib_item_name")
    private String buyerAttribItemName;

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
     * @return buyer_attrib_item_id
     */
    public Integer getBuyerAttribItemId() {
        return buyerAttribItemId;
    }

    /**
     * @param buyerAttribItemId
     */
    public void setBuyerAttribItemId(Integer buyerAttribItemId) {
        this.buyerAttribItemId = buyerAttribItemId;
    }

    /**
     * @return buyer_attrib_def_id
     */
    public Integer getBuyerAttribDefId() {
        return buyerAttribDefId;
    }

    /**
     * @param buyerAttribDefId
     */
    public void setBuyerAttribDefId(Integer buyerAttribDefId) {
        this.buyerAttribDefId = buyerAttribDefId;
    }

    /**
     * @return buyer_attrib_item_code
     */
    public String getBuyerAttribItemCode() {
        return buyerAttribItemCode;
    }

    /**
     * @param buyerAttribItemCode
     */
    public void setBuyerAttribItemCode(String buyerAttribItemCode) {
        this.buyerAttribItemCode = buyerAttribItemCode;
    }

    /**
     * @return buyer_attrib_item_name
     */
    public String getBuyerAttribItemName() {
        return buyerAttribItemName;
    }

    /**
     * @param buyerAttribItemName
     */
    public void setBuyerAttribItemName(String buyerAttribItemName) {
        this.buyerAttribItemName = buyerAttribItemName;
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