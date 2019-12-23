package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "view_supplier_list")
public class ViewSupplierList {

    @Id
    @Column(name = "supplier_code")
    private String supplierCode;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "supplier_source_code")
    private String supplierSourceCode;

    @Column(name = "supplier_description")
    private String supplierDescription;

    @Column(name = "supplier_c_suppliertype_id")
    private String supplierCSuppliertypeId;

    @Column(name = "supplier_legalperson")
    private String supplierLegalperson;

    @Column(name = "supplier_contacter")
    private String supplierContacter;

    @Column(name = "supplier_email")
    private String supplierEmail;

    @Column(name = "supplier_data_status")
    private Integer supplierDataStatus;

    @Column(name = "supplier_data_status_name")
    private String supplierDataStatusName;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierDescription() {
        return supplierDescription;
    }

    public void setSupplierDescription(String supplierDescription) {
        this.supplierDescription = supplierDescription;
    }

    public String getSupplierCSuppliertypeId() {
        return supplierCSuppliertypeId;
    }

    public void setSupplierCSuppliertypeId(String supplierCSuppliertypeId) {
        this.supplierCSuppliertypeId = supplierCSuppliertypeId;
    }

    public String getSupplierLegalperson() {
        return supplierLegalperson;
    }

    public void setSupplierLegalperson(String supplierLegalperson) {
        this.supplierLegalperson = supplierLegalperson;
    }

    public String getSupplierContacter() {
        return supplierContacter;
    }

    public void setSupplierContacter(String supplierContacter) {
        this.supplierContacter = supplierContacter;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public Integer getSupplierDataStatus() {
        return supplierDataStatus;
    }

    public void setSupplierDataStatus(Integer supplierDataStatus) {
        this.supplierDataStatus = supplierDataStatus;
    }

    public String getSupplierDataStatusName() {
        return supplierDataStatusName;
    }

    public void setSupplierDataStatusName(String supplierDataStatusName) {
        this.supplierDataStatusName = supplierDataStatusName;
    }

    public String getSupplierSourceCode() {
        return supplierSourceCode;
    }

    public void setSupplierSourceCode(String supplierSourceCode) {
        this.supplierSourceCode = supplierSourceCode;
    }
}