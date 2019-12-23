package com.icicle.masterdb.pojo.vo;


import com.icicle.masterdb.util.StringUtil;

import java.math.BigDecimal;

/**
 * Created by liurenhua on 2017/11/8.
 * @author  liurenhua
 */
public class ViewMaterialDetailVO {
    private String materialCode;

    private String materialName;

    private String uom;

    private String cateDlName;

    private String cateZlName;

    private String cateXlName;

    private Integer syncStatus;

    private String materialDesc;

    private BigDecimal vendorPrice;

    private BigDecimal unitPrice;

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

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getCateDlName() {
        return cateDlName;
    }

    public void setCateDlName(String cateDlName) {
        this.cateDlName = cateDlName;
    }

    public String getCateZlName() {
        return cateZlName;
    }

    public void setCateZlName(String cateZlName) {
        this.cateZlName = cateZlName;
    }

    public String getCateXlName() {
        return cateXlName;
    }

    public void setCateXlName(String cateXlName) {
        this.cateXlName = cateXlName;
    }

    public Integer getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(Integer syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    public BigDecimal getVendorPrice() {
        return vendorPrice;
    }

    public void setVendorPrice(BigDecimal vendorPrice) {
        this.vendorPrice = vendorPrice;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
