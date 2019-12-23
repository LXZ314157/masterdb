package com.icicle.masterdb.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "buyer_attribute")
public class BuyerAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "buyer_no")
    private Integer buyerNo;

    @Column(name = "attrib_def_id")
    private Integer attribDefId;

    @Column(name = "attrib_item_id")
    private Integer attribItemId;

    @Column(name = "buyer_attri_value")
    private String buyerAttriValue;
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

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
     * @return buyer_no
     */
    public Integer getBuyerNo() {
        return buyerNo;
    }

    /**
     * @param buyerNo
     */
    public void setBuyerNo(Integer buyerNo) {
        this.buyerNo = buyerNo;
    }

    /**
     * @return attrib_def_id
     */
    public Integer getAttribDefId() {
        return attribDefId;
    }

    /**
     * @param attribDefId
     */
    public void setAttribDefId(Integer attribDefId) {
        this.attribDefId = attribDefId;
    }

    /**
     * @return attrib_item_id
     */
    public Integer getAttribItemId() {
        return attribItemId;
    }

    /**
     * @param attribItemId
     */
    public void setAttribItemId(Integer attribItemId) {
        this.attribItemId = attribItemId;
    }

    /**
     * @return buyer_attri_value
     */
    public String getBuyerAttriValue() {
        return buyerAttriValue;
    }

    /**
     * @param buyerAttriValue
     */
    public void setBuyerAttriValue(String buyerAttriValue) {
        this.buyerAttriValue = buyerAttriValue;
    }

    @Override
    public boolean equals(Object obj) {

        if (Objects.equals(obj.getClass().getName(), this.getClass().getName())) {
            BuyerAttribute s = (BuyerAttribute) obj;
            boolean same = Objects.equals(s.getBuyerNo(), this.getBuyerNo())
                    && Objects.equals(s.getAttribDefId(), this.getAttribDefId());
            return same;
        } else {
            return super.equals(obj);
        }

    }
}