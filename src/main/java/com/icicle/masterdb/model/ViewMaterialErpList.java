package com.icicle.masterdb.model;

import javax.persistence.*;

@Table(name = "view_material_erp_list")
public class ViewMaterialErpList {
    @Id
    @Column(name = "material_code")
    private String materialCode;

    @Column(name = "material_desc")
    private String materialDesc;

    @Column(name = "cate_dl")
    private String cateDl;

    @Column(name = "cate_zl")
    private String cateZl;

    @Column(name = "cate_xl")
    private String cateXl;

    private String uom;

    @Column(name = "item_template_type")
    private Integer itemTemplateType;

    @Column(name = "item_template_type_desc")
    private String itemTemplateTypeDesc;

    @Column(name = "catalog_type")
    private Integer catalogType;

    @Column(name = "catalog_type_desc")
    private String catalogTypeDesc;

    @Column(name = "min_order_qty")
    private String minOrderQty;

    @Column(name = "full_lead_time")
    private String fullLeadTime;

    @Column(name = "storage_class")
    private String storageClass;

    /**
     * @return material_code
     */
    public String getMaterialCode() {
        return materialCode;
    }

    /**
     * @param materialCode
     */
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    /**
     * @return material_desc
     */
    public String getMaterialDesc() {
        return materialDesc;
    }

    /**
     * @param materialDesc
     */
    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    /**
     * @return cate_dl
     */
    public String getCateDl() {
        return cateDl;
    }

    /**
     * @param cateDl
     */
    public void setCateDl(String cateDl) {
        this.cateDl = cateDl;
    }

    /**
     * @return cate_zl
     */
    public String getCateZl() {
        return cateZl;
    }

    /**
     * @param cateZl
     */
    public void setCateZl(String cateZl) {
        this.cateZl = cateZl;
    }

    /**
     * @return cate_xl
     */
    public String getCateXl() {
        return cateXl;
    }

    /**
     * @param cateXl
     */
    public void setCateXl(String cateXl) {
        this.cateXl = cateXl;
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
     * @return item_template_type
     */
    public Integer getItemTemplateType() {
        return itemTemplateType;
    }

    /**
     * @param itemTemplateType
     */
    public void setItemTemplateType(Integer itemTemplateType) {
        this.itemTemplateType = itemTemplateType;
    }

    /**
     * @return item_template_type_desc
     */
    public String getItemTemplateTypeDesc() {
        return itemTemplateTypeDesc;
    }

    /**
     * @param itemTemplateTypeDesc
     */
    public void setItemTemplateTypeDesc(String itemTemplateTypeDesc) {
        this.itemTemplateTypeDesc = itemTemplateTypeDesc;
    }

    /**
     * @return catalog_type
     */
    public Integer getCatalogType() {
        return catalogType;
    }

    /**
     * @param catalogType
     */
    public void setCatalogType(Integer catalogType) {
        this.catalogType = catalogType;
    }

    /**
     * @return catalog_type_desc
     */
    public String getCatalogTypeDesc() {
        return catalogTypeDesc;
    }

    /**
     * @param catalogTypeDesc
     */
    public void setCatalogTypeDesc(String catalogTypeDesc) {
        this.catalogTypeDesc = catalogTypeDesc;
    }

    /**
     * @return min_order_qty
     */
    public String getMinOrderQty() {
        return minOrderQty;
    }

    /**
     * @param minOrderQty
     */
    public void setMinOrderQty(String minOrderQty) {
        this.minOrderQty = minOrderQty;
    }

    /**
     * @return full_lead_time
     */
    public String getFullLeadTime() {
        return fullLeadTime;
    }

    /**
     * @param fullLeadTime
     */
    public void setFullLeadTime(String fullLeadTime) {
        this.fullLeadTime = fullLeadTime;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }
}