package com.icicle.masterdb.model;

import javax.persistence.*;

@Table(name = "view_product_erp_list")
public class ViewProductErpList {
    @Id
    @Column(name = "item_num")
    private String itemNum;

    @Column(name = "item_desc")
    private String itemDesc;

    @Column(name = "cate_dl")
    private String cateDl;

    @Column(name = "cate_zl")
    private String cateZl;

    @Column(name = "cate_xl")
    private String cateXl;

    @Column(name = "item_template")
    private String itemTemplate;

    @Column(name = "sku_num")
    private String skuNum;

    @Column(name = "product_code")
    private String productCode;

    /**
     * @return item_num
     */
    public String getItemNum() {
        return itemNum;
    }

    /**
     * @param itemNum
     */
    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    /**
     * @return item_desc
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * @param itemDesc
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
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
     * @return item_template
     */
    public String getItemTemplate() {
        return itemTemplate;
    }

    /**
     * @param itemTemplate
     */
    public void setItemTemplate(String itemTemplate) {
        this.itemTemplate = itemTemplate;
    }

    /**
     * @return sku_num
     */
    public String getSkuNum() {
        return skuNum;
    }

    /**
     * @param skuNum
     */
    public void setSkuNum(String skuNum) {
        this.skuNum = skuNum;
    }

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
}