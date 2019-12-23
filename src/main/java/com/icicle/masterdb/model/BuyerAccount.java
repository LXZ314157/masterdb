package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "buyer_account")
public class BuyerAccount {
    @Id
    @Column(name = "bank_account_id")
    private Integer bankAccountId;

    @Column(name = "buyer_no")
    private Integer buyerNo;

    @Column(name = "bank_number")
    private String bankNumber;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_account")
    private String bankAccount;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_state")
    private Integer accountState;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    /**
     * @return bank_account_id
     */
    public Integer getBankAccountId() {
        return bankAccountId;
    }

    /**
     * @param bankAccountId
     */
    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
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
     * @return bank_number
     */
    public String getBankNumber() {
        return bankNumber;
    }

    /**
     * @param bankNumber
     */
    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    /**
     * @return bank_name
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return bank_account
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * @param bankAccount
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * @return account_name
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return account_state
     */
    public Integer getAccountState() {
        return accountState;
    }

    /**
     * @param accountState
     */
    public void setAccountState(Integer accountState) {
        this.accountState = accountState;
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