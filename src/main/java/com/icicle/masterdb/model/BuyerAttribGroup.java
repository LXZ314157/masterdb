package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "buyer_attrib_group")
public class BuyerAttribGroup {
    @Id
    @Column(name = "buyer_attrib_group_id")
    private Integer buyerAttribGroupId;

    @Column(name = "buyer_attrib_group_code")
    private String buyerAttribGroupCode;

    @Column(name = "buyer_attrib_group_name")
    private String buyerAttribGroupName;

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
     * @return buyer_attrib_group_id
     */
    public Integer getBuyerAttribGroupId() {
        return buyerAttribGroupId;
    }

    /**
     * @param buyerAttribGroupId
     */
    public void setBuyerAttribGroupId(Integer buyerAttribGroupId) {
        this.buyerAttribGroupId = buyerAttribGroupId;
    }

    /**
     * @return buyer_attrib_group_code
     */
    public String getBuyerAttribGroupCode() {
        return buyerAttribGroupCode;
    }

    /**
     * @param buyerAttribGroupCode
     */
    public void setBuyerAttribGroupCode(String buyerAttribGroupCode) {
        this.buyerAttribGroupCode = buyerAttribGroupCode;
    }

    /**
     * @return buyer_attrib_group_name
     */
    public String getBuyerAttribGroupName() {
        return buyerAttribGroupName;
    }

    /**
     * @param buyerAttribGroupName
     */
    public void setBuyerAttribGroupName(String buyerAttribGroupName) {
        this.buyerAttribGroupName = buyerAttribGroupName;
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