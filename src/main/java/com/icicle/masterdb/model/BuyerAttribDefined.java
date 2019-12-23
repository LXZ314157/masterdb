package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "buyer_attrib_defined")
public class BuyerAttribDefined {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "buyer_attrib_def_id")
    private Integer buyerAttribDefId;

    @Column(name = "buyer_attrib_def_code")
    private String buyerAttribDefCode;

    @Column(name = "buyer_attrib_def_name")
    private String buyerAttribDefName;

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "item_length")
    private Integer itemLength;
    private Integer defineOrder;
    @Column(name = "is_sycn")
    private Boolean isSycn;

    @Column(name = "has_item")
    private Boolean hasItem;

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
     * @return buyer_attrib_def_code
     */
    public String getBuyerAttribDefCode() {
        return buyerAttribDefCode;
    }

    /**
     * @param buyerAttribDefCode
     */
    public void setBuyerAttribDefCode(String buyerAttribDefCode) {
        this.buyerAttribDefCode = buyerAttribDefCode;
    }

    /**
     * @return buyer_attrib_def_name
     */
    public String getBuyerAttribDefName() {
        return buyerAttribDefName;
    }

    /**
     * @param buyerAttribDefName
     */
    public void setBuyerAttribDefName(String buyerAttribDefName) {
        this.buyerAttribDefName = buyerAttribDefName;
    }

    /**
     * @return item_type
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * @param itemType
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * @return item_length
     */
    public Integer getItemLength() {
        return itemLength;
    }

    /**
     * @param itemLength
     */
    public void setItemLength(Integer itemLength) {
        this.itemLength = itemLength;
    }

    /**
     * @return has_item
     */
    public Boolean getHasItem() {
        return hasItem;
    }

    /**
     * @param hasItem
     */
    public void setHasItem(Boolean hasItem) {
        this.hasItem = hasItem;
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

    public Integer getDefineOrder() {
        return defineOrder;
    }

    public void setDefineOrder(Integer defineOrder) {
        this.defineOrder = defineOrder;
    }

    public Boolean getSycn() {
        return isSycn;
    }

    public void setSycn(Boolean sycn) {
        isSycn = sycn;
    }
}