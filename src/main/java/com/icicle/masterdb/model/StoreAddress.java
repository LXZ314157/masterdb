package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "store_address")
public class StoreAddress {
    @Id
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "store_no")
    private Integer storeNo;

    @Column(name = "address_type")
    private Integer addressType;

    @Column(name = "is_default")
    private Boolean isDefault;

    private String contact;

    private String phone;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    private String address;

    @Column(name = "address_state")
    private Integer addressState;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    /**
     * @return address_id
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * @param addressId
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * @return store_no
     */
    public Integer getStoreNo() {
        return storeNo;
    }

    /**
     * @param storeNo
     */
    public void setStoreNo(Integer storeNo) {
        this.storeNo = storeNo;
    }

    /**
     * @return address_type
     */
    public Integer getAddressType() {
        return addressType;
    }

    /**
     * @param addressType
     */
    public void setAddressType(Integer addressType) {
        this.addressType = addressType;
    }

    /**
     * @return is_default
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * @param isDefault
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * @return contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return mobile_phone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return address_state
     */
    public Integer getAddressState() {
        return addressState;
    }

    /**
     * @param addressState
     */
    public void setAddressState(Integer addressState) {
        this.addressState = addressState;
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