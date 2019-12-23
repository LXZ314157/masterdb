package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "view_selectproductinfo")
public class ViewSelectproductinfo {
    @Id
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name")
    private String productName;

    private String uom;

    private String c1;

    private String c2;

    private String c3;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    private BigDecimal cost;

    @Column(name = "product_desc")
    private String productDesc;

    private String brand;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    private Boolean status;

    @Column(name = "sync_status")
    private Integer syncStatus;

    /**
     * @return product_code
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return product_name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return uom
     */
    public String getUom() {
        return uom;
    }

    /**
     * @param uom
     */
    public void setUom(String uom) {
        this.uom = uom;
    }

    /**
     * @return c1
     */
    public String getC1() {
        return c1;
    }

    /**
     * @param c1
     */
    public void setC1(String c1) {
        this.c1 = c1;
    }

    /**
     * @return c2
     */
    public String getC2() {
        return c2;
    }

    /**
     * @param c2
     */
    public void setC2(String c2) {
        this.c2 = c2;
    }

    /**
     * @return c3
     */
    public String getC3() {
        return c3;
    }

    /**
     * @param c3
     */
    public void setC3(String c3) {
        this.c3 = c3;
    }

    /**
     * @return unit_price
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * @param cost
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * @return product_desc
     */
    public String getProductDesc() {
        return productDesc;
    }

    /**
     * @param productDesc
     */
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    /**
     * @return brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
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

    /**
     * @return status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return sync_status
     */
    public Integer getSyncStatus() {
        return syncStatus;
    }

    /**
     * @param syncStatus
     */
    public void setSyncStatus(Integer syncStatus) {
        this.syncStatus = syncStatus;
    }
}