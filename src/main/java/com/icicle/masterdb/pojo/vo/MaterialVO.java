package com.icicle.masterdb.pojo.vo;

/**
 * @author lvxuezhan
 * @version 2019-08-29 11:33
 */
public class MaterialVO {

    private String materialCode;
    private String materialName;
    private String cateDl;
    private String cateZl;
    private String cateXl;
    private String materialType;
    private String uom;
    private String materialDesc;
    private String vendorPrice;
    private String unitPrice;
    private String currencyName;
    private String syncStatus;


    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getCateDl() {
        return cateDl;
    }

    public void setCateDl(String cateDl) {
        this.cateDl = cateDl;
    }

    public String getCateZl() {
        return cateZl;
    }

    public void setCateZl(String cateZl) {
        this.cateZl = cateZl;
    }

    public String getCateXl() {
        return cateXl;
    }

    public void setCateXl(String cateXl) {
        this.cateXl = cateXl;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    public String getVendorPrice() {
        return vendorPrice;
    }

    public void setVendorPrice(String vendorPrice) {
        this.vendorPrice = vendorPrice;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }
}
