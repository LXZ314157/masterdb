package com.icicle.masterdb.model;

import javax.persistence.*;

@Table(name = "view_storeaddr_list")
public class ViewStoreaddrList {

    @Column(name = "address_id")
    private Integer addressId;

    @Id
    @Column(name = "store_no")
    private Integer storeNo;

    @Column(name = "address_type")
    private Integer addressType;

    @Column(name = "address_type_name")
    private String addressTypeName;

    @Column(name = "is_default")
    private Boolean isDefault;

    private String contact;

    private String phone;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    private String address;

    @Column(name = "address_state")
    private Integer addressState;


    public String getAddressTypeName() {
        return addressTypeName;
    }

    public void setAddressTypeName(String addressTypeName) {
        this.addressTypeName = addressTypeName;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

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
}