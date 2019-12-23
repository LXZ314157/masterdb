package com.icicle.masterdb.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "buyer_group_attrib")
public class BuyerGroupAttrib {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "buyer_attrib_group_id")
    private Integer buyerAttribGroupId;

    @Column(name = "buyer_attrib_def_id")
    private Integer buyerAttribDefId;

    private Boolean status;

    @Column(name = "created_by")
    private String createdBy;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

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

    @Override
    public boolean equals(Object obj) {

        if (Objects.equals(obj.getClass().getName(), this.getClass().getName())) {
            BuyerGroupAttrib s = (BuyerGroupAttrib) obj;
            boolean same = Objects.equals(s.getBuyerAttribDefId(), this.getBuyerAttribDefId())
                    && Objects.equals(s.getBuyerAttribGroupId(), this.getBuyerAttribGroupId());
            return same;
        } else {
            return super.equals(obj);
        }

    }

}