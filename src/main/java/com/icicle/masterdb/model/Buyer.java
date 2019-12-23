package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author CodeGeneratorUtil
 */
public class Buyer {
    @Id
    @Column(name = "buyer_id")
    private String buyerId;

    @GeneratedValue(generator = "JDBC")
    @Column(name = "buyer_no")
    private Integer buyerNo;

    @Column(name = "buyer_type_id")
    private Integer buyerTypeId;

    @Column(name = "buyer_full_name")
    private String buyerFullName;

    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "buyer_short_name")
    private String buyerShortName;

    @Column(name = "buyer_spell")
    private String buyerSpell;

    @Column(name = "buyer_state")
    private Integer buyerState;

    @Column(name = "buyer_contact")
    private String buyerContact;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "buyer_phone")
    private String buyerPhone;

    @Column(name = "buyer_mobile")
    private String buyerMobile;

    @Column(name = "buyer_email")
    private String buyerEmail;

    @Column(name = "buyer_fax")
    private String buyerFax;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "zone_id")
    private Integer zoneId;

    @Column(name = "buyer_deposit")
    private BigDecimal buyerDeposit;

    @Column(name = "buyer_joinning_fee")
    private BigDecimal buyerJoinningFee;

    @Column(name = "buyer_desc")
    private String buyerDesc;

    @Column(name = "buyer_start_date")
    private Date buyerStartDate;

    @Column(name = "buyer_end_date")
    private Date buyerEndDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "legal_person")
    private String legalPerson;

    @Column(name = "manage_center_id")
    private Integer manageCenterId;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "buyer_address")
    private String buyerAddress;

    /**
     * @return buyer_id
     */
    public String getBuyerId() {
        return buyerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @param buyerId
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
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
     * @return buyer_full_name
     */
    public String getBuyerFullName() {
        return buyerFullName;
    }

    /**
     * @param buyerFullName
     */
    public void setBuyerFullName(String buyerFullName) {
        this.buyerFullName = buyerFullName;
    }

    /**
     * @return buyer_name
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * @param buyerName
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * @return buyer_short_name
     */
    public String getBuyerShortName() {
        return buyerShortName;
    }

    /**
     * @param buyerShortName
     */
    public void setBuyerShortName(String buyerShortName) {
        this.buyerShortName = buyerShortName;
    }

    /**
     * @return buyer_spell
     */
    public String getBuyerSpell() {
        return buyerSpell;
    }

    /**
     * @param buyerSpell
     */
    public void setBuyerSpell(String buyerSpell) {
        this.buyerSpell = buyerSpell;
    }

    /**
     * @return buyer_state
     */
    public Integer getBuyerState() {
        return buyerState;
    }

    /**
     * @param buyerState
     */
    public void setBuyerState(Integer buyerState) {
        this.buyerState = buyerState;
    }

    /**
     * @return buyer_contact
     */
    public String getBuyerContact() {
        return buyerContact;
    }

    /**
     * @param buyerContact
     */
    public void setBuyerContact(String buyerContact) {
        this.buyerContact = buyerContact;
    }

    /**
     * @return buyer_phone
     */
    public String getBuyerPhone() {
        return buyerPhone;
    }

    /**
     * @param buyerPhone
     */
    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    /**
     * @return buyer_mobile
     */
    public String getBuyerMobile() {
        return buyerMobile;
    }

    /**
     * @param buyerMobile
     */
    public void setBuyerMobile(String buyerMobile) {
        this.buyerMobile = buyerMobile;
    }

    /**
     * @return buyer_email
     */
    public String getBuyerEmail() {
        return buyerEmail;
    }

    /**
     * @param buyerEmail
     */
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    /**
     * @return buyer_fax
     */
    public String getBuyerFax() {
        return buyerFax;
    }

    /**
     * @param buyerFax
     */
    public void setBuyerFax(String buyerFax) {
        this.buyerFax = buyerFax;
    }

    /**
     * @return city_id
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * @param cityId
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * @return zone_id
     */
    public Integer getZoneId() {
        return zoneId;
    }

    /**
     * @param zoneId
     */
    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    /**
     * @return buyer_deposit
     */
    public BigDecimal getBuyerDeposit() {
        return buyerDeposit;
    }

    /**
     * @param buyerDeposit
     */
    public void setBuyerDeposit(BigDecimal buyerDeposit) {
        this.buyerDeposit = buyerDeposit;
    }

    /**
     * @return buyer_joinning_fee
     */
    public BigDecimal getBuyerJoinningFee() {
        return buyerJoinningFee;
    }

    /**
     * @param buyerJoinningFee
     */
    public void setBuyerJoinningFee(BigDecimal buyerJoinningFee) {
        this.buyerJoinningFee = buyerJoinningFee;
    }

    /**
     * @return buyer_desc
     */
    public String getBuyerDesc() {
        return buyerDesc;
    }

    /**
     * @param buyerDesc
     */
    public void setBuyerDesc(String buyerDesc) {
        this.buyerDesc = buyerDesc;
    }

    /**
     * @return buyer_start_date
     */
    public Date getBuyerStartDate() {
        return buyerStartDate;
    }

    /**
     * @param buyerStartDate
     */
    public void setBuyerStartDate(Date buyerStartDate) {
        this.buyerStartDate = buyerStartDate;
    }

    /**
     * @return buyer_end_date
     */
    public Date getBuyerEndDate() {
        return buyerEndDate;
    }

    /**
     * @param buyerEndDate
     */
    public void setBuyerEndDate(Date buyerEndDate) {
        this.buyerEndDate = buyerEndDate;
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

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public Integer getManageCenterId() {
        return manageCenterId;
    }

    public void setManageCenterId(Integer manageCenterId) {
        this.manageCenterId = manageCenterId;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }
}