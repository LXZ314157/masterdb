package com.icicle.masterdb.model;

import javax.persistence.*;

public class Supplier {
    @Id
    @Column(name = "supplier_id")
    private Integer supplierId;

    @Column(name = "supplier_source_code")
    private String supplierSourceCode;

    @Column(name = "supplier_code")
    private String supplierCode;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "supplier_description")
    private String supplierDescription;

    @Column(name = "supplier_c_suppliertype_id")
    private String supplierCSuppliertypeId;

    @Column(name = "supplier_legalperson")
    private String supplierLegalperson;

    @Column(name = "supplier_contacter")
    private String supplierContacter;

    @Column(name = "supplier_address")
    private String supplierAddress;

    @Column(name = "supplier_postal")
    private String supplierPostal;

    @Column(name = "supplier_phone")
    private String supplierPhone;

    @Column(name = "supplier_mobile")
    private String supplierMobile;

    @Column(name = "supplier_c_country_id")
    private String supplierCCountryId;

    @Column(name = "supplier_c_province_id")
    private String supplierCProvinceId;

    @Column(name = "supplier_c_city_id")
    private String supplierCCityId;

    @Column(name = "supplier_email")
    private String supplierEmail;

    @Column(name = "supplier_fax")
    private String supplierFax;

    @Column(name = "supplier_deposit_bank")
    private String supplierDepositBank;

    @Column(name = "supplier_account")
    private String supplierAccount;

    @Column(name = "supplier_taxno")
    private String supplierTaxno;

    @Column(name = "supplier_companyname")
    private String supplierCompanyname;

    @Column(name = "supplier_data_status")
    private Integer supplierDataStatus;

    /**
     * @return supplier_id
     */
    public Integer getSupplierId() {
        return supplierId;
    }

    /**
     * @param supplierId
     */
    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * @return supplier_source_code
     */
    public String getSupplierSourceCode() {
        return supplierSourceCode;
    }

    /**
     * @param supplierSourceCode
     */
    public void setSupplierSourceCode(String supplierSourceCode) {
        this.supplierSourceCode = supplierSourceCode;
    }

    /**
     * @return supplier_code
     */
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * @param supplierCode
     */
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * @return supplier_name
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * @param supplierName
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * @return supplier_description
     */
    public String getSupplierDescription() {
        return supplierDescription;
    }

    /**
     * @param supplierDescription
     */
    public void setSupplierDescription(String supplierDescription) {
        this.supplierDescription = supplierDescription;
    }

    /**
     * @return supplier_c_suppliertype_id
     */
    public String getSupplierCSuppliertypeId() {
        return supplierCSuppliertypeId;
    }

    /**
     * @param supplierCSuppliertypeId
     */
    public void setSupplierCSuppliertypeId(String supplierCSuppliertypeId) {
        this.supplierCSuppliertypeId = supplierCSuppliertypeId;
    }

    /**
     * @return supplier_legalperson
     */
    public String getSupplierLegalperson() {
        return supplierLegalperson;
    }

    /**
     * @param supplierLegalperson
     */
    public void setSupplierLegalperson(String supplierLegalperson) {
        this.supplierLegalperson = supplierLegalperson;
    }

    /**
     * @return supplier_contacter
     */
    public String getSupplierContacter() {
        return supplierContacter;
    }

    /**
     * @param supplierContacter
     */
    public void setSupplierContacter(String supplierContacter) {
        this.supplierContacter = supplierContacter;
    }

    /**
     * @return supplier_address
     */
    public String getSupplierAddress() {
        return supplierAddress;
    }

    /**
     * @param supplierAddress
     */
    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    /**
     * @return supplier_postal
     */
    public String getSupplierPostal() {
        return supplierPostal;
    }

    /**
     * @param supplierPostal
     */
    public void setSupplierPostal(String supplierPostal) {
        this.supplierPostal = supplierPostal;
    }

    /**
     * @return supplier_phone
     */
    public String getSupplierPhone() {
        return supplierPhone;
    }

    /**
     * @param supplierPhone
     */
    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    /**
     * @return supplier_mobile
     */
    public String getSupplierMobile() {
        return supplierMobile;
    }

    /**
     * @param supplierMobile
     */
    public void setSupplierMobile(String supplierMobile) {
        this.supplierMobile = supplierMobile;
    }

    /**
     * @return supplier_c_country_id
     */
    public String getSupplierCCountryId() {
        return supplierCCountryId;
    }

    /**
     * @param supplierCCountryId
     */
    public void setSupplierCCountryId(String supplierCCountryId) {
        this.supplierCCountryId = supplierCCountryId;
    }

    /**
     * @return supplier_c_province_id
     */
    public String getSupplierCProvinceId() {
        return supplierCProvinceId;
    }

    /**
     * @param supplierCProvinceId
     */
    public void setSupplierCProvinceId(String supplierCProvinceId) {
        this.supplierCProvinceId = supplierCProvinceId;
    }

    /**
     * @return supplier_c_city_id
     */
    public String getSupplierCCityId() {
        return supplierCCityId;
    }

    /**
     * @param supplierCCityId
     */
    public void setSupplierCCityId(String supplierCCityId) {
        this.supplierCCityId = supplierCCityId;
    }

    /**
     * @return supplier_email
     */
    public String getSupplierEmail() {
        return supplierEmail;
    }

    /**
     * @param supplierEmail
     */
    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    /**
     * @return supplier_fax
     */
    public String getSupplierFax() {
        return supplierFax;
    }

    /**
     * @param supplierFax
     */
    public void setSupplierFax(String supplierFax) {
        this.supplierFax = supplierFax;
    }

    /**
     * @return supplier_deposit_bank
     */
    public String getSupplierDepositBank() {
        return supplierDepositBank;
    }

    /**
     * @param supplierDepositBank
     */
    public void setSupplierDepositBank(String supplierDepositBank) {
        this.supplierDepositBank = supplierDepositBank;
    }

    /**
     * @return supplier_account
     */
    public String getSupplierAccount() {
        return supplierAccount;
    }

    /**
     * @param supplierAccount
     */
    public void setSupplierAccount(String supplierAccount) {
        this.supplierAccount = supplierAccount;
    }

    /**
     * @return supplier_taxno
     */
    public String getSupplierTaxno() {
        return supplierTaxno;
    }

    /**
     * @param supplierTaxno
     */
    public void setSupplierTaxno(String supplierTaxno) {
        this.supplierTaxno = supplierTaxno;
    }

    /**
     * @return supplier_companyname
     */
    public String getSupplierCompanyname() {
        return supplierCompanyname;
    }

    /**
     * @param supplierCompanyname
     */
    public void setSupplierCompanyname(String supplierCompanyname) {
        this.supplierCompanyname = supplierCompanyname;
    }

    /**
     * @return supplier_data_status
     */
    public Integer getSupplierDataStatus() {
        return supplierDataStatus;
    }

    /**
     * @param supplierDataStatus
     */
    public void setSupplierDataStatus(Integer supplierDataStatus) {
        this.supplierDataStatus = supplierDataStatus;
    }
}