package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.BuyerAttribute;
import com.icicle.masterdb.util.StringUtil;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public class BuyerVO {
    private String buyerId;
    private Integer buyerNo;
    private Integer buyerTypeId;
    private String buyerTypeName;
    private String buyerFullName;
    private String buyerName;
    private String buyerShortName;
    private String buyerSpell;
    private Integer buyerState;
    private String buyerStateName;
    private String buyerContact;
    private String buyerPhone;
    private String buyerMobile;
    private String buyerEmail;
    private String bankName;
    private String bankAccount;
    private String companyName;
    private String buyerFax;
    private Integer cityId;
    private String cityName;
    private Integer zoneId;
    private String zoneName;
    private BigDecimal buyerDeposit;
    private BigDecimal buyerJoinningFee;
    private String buyerDesc;
    private Date buyerStartDate;
    private Date buyerEndDate;
    private String createdBy;
    private Date creationDate;
    private String lastUpdatedBy;
    private String legalPerson;
    private Integer manageCenterId;
    private String manageCenterName;
    private String taxNumber;
    private String buyerAddress;
    private BuyerAccountVO buyerAccountVO;

    private Date lastUpdateDate;
    private List<BuyerAttribute> attr;

    private String syn;


    public String getCityName() {
        return cityName;
    }

    public String getManageCenterName() {
        return manageCenterName;
    }

    public void setManageCenterName(String manageCenterName) {
        this.manageCenterName = manageCenterName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getBuyerStateName() {
        return buyerStateName;
    }

    public void setBuyerStateName(String buyerStateName) {
        this.buyerStateName = buyerStateName;
    }

    public String getBuyerTypeName() {
        return buyerTypeName;
    }

    public void setBuyerTypeName(String buyerTypeName) {
        this.buyerTypeName = buyerTypeName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public String getSyn() {
        return syn;
    }

    public BuyerAccountVO getBuyerAccountVO() {
        return buyerAccountVO;
    }

    public void setBuyerAccountVO(BuyerAccountVO buyerAccountVO) {
        this.buyerAccountVO = buyerAccountVO;
    }

    public void setSyn(String syn) {
        this.syn = syn;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getBuyerNo() {
        return buyerNo;
    }

    public void setBuyerNo(Integer buyerNo) {
        this.buyerNo = buyerNo;
    }

    public Integer getBuyerTypeId() {
        return buyerTypeId;
    }

    public void setBuyerTypeId(Integer buyerTypeId) {
        this.buyerTypeId = buyerTypeId;
    }

    public String getBuyerFullName() {
        return buyerFullName;
    }

    public void setBuyerFullName(String buyerFullName) {
        this.buyerFullName = buyerFullName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerShortName() {
        return buyerShortName;
    }

    public void setBuyerShortName(String buyerShortName) {
        this.buyerShortName = buyerShortName;
    }

    public String getBuyerSpell() {
        return buyerSpell;
    }

    public void setBuyerSpell(String buyerSpell) {
        this.buyerSpell = buyerSpell;
    }

    public Integer getBuyerState() {
        return buyerState;
    }

    public void setBuyerState(Integer buyerState) {
        this.buyerState = buyerState;
    }

    public String getBuyerContact() {
        return buyerContact;
    }

    public void setBuyerContact(String buyerContact) {
        this.buyerContact = buyerContact;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerMobile() {
        return buyerMobile;
    }

    public void setBuyerMobile(String buyerMobile) {
        this.buyerMobile = buyerMobile;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerFax() {
        return buyerFax;
    }

    public void setBuyerFax(String buyerFax) {
        this.buyerFax = buyerFax;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public BigDecimal getBuyerDeposit() {
        return buyerDeposit;
    }

    public void setBuyerDeposit(BigDecimal buyerDeposit) {
        this.buyerDeposit = buyerDeposit;
    }

    public BigDecimal getBuyerJoinningFee() {
        return buyerJoinningFee;
    }

    public void setBuyerJoinningFee(BigDecimal buyerJoinningFee) {
        this.buyerJoinningFee = buyerJoinningFee;
    }

    public String getBuyerDesc() {
        return buyerDesc;
    }

    public void setBuyerDesc(String buyerDesc) {
        this.buyerDesc = buyerDesc;
    }

    public Date getBuyerStartDate() {
        return buyerStartDate;
    }

    public void setBuyerStartDate(Date buyerStartDate) {
        this.buyerStartDate = buyerStartDate;
    }

    public Date getBuyerEndDate() {
        return buyerEndDate;
    }

    public void setBuyerEndDate(Date buyerEndDate) {
        this.buyerEndDate = buyerEndDate;
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

    public List<BuyerAttribute> getAttr() {
        return attr;
    }

    public void setAttr(List<BuyerAttribute> attr) {
        this.attr = attr;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
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

    public Integer getManageCenterId() {
        return manageCenterId;
    }

    public void setManageCenterId(Integer manageCenterId) {
        this.manageCenterId = manageCenterId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
