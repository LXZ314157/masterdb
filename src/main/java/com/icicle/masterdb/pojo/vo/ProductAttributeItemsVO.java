package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.Product;
import com.icicle.masterdb.model.ProductAttributeExtend;

import java.util.List;

/**
 * @author lvxuezhan
 * @version 2019-06-28 9:42
 */
public class ProductAttributeItemsVO {

    private List<ProductAttributeDefinedVO> productAttributeDefinedVOList;

    private List<ProductExtendAttributeDefinedVO> productExtendAttributeDefinedVOList;

    private List<ProductAttributeItemVO> productAttributeItemsVOList;

    private ProductAttributeExtend productAttributeExtend;

    private Product product;

    private List<ProductAttributeCodeValueVO> productCategoryAttr;

    private Integer isCategory;

    private String productCode;

    private String productCategoryCode;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }

    public Integer getIsCategory() {
        return isCategory;
    }

    public void setIsCategory(Integer isCategory) {
        this.isCategory = isCategory;
    }

    public List<ProductAttributeCodeValueVO> getProductCategoryAttr() {
        return productCategoryAttr;
    }

    public void setProductCategoryAttr(List<ProductAttributeCodeValueVO> productCategoryAttr) {
        this.productCategoryAttr = productCategoryAttr;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductAttributeExtend getProductAttributeExtend() {
        return productAttributeExtend;
    }

    public void setProductAttributeExtend(ProductAttributeExtend productAttributeExtend) {
        this.productAttributeExtend = productAttributeExtend;
    }

    public List<ProductExtendAttributeDefinedVO> getProductExtendAttributeDefinedVOList() {
        return productExtendAttributeDefinedVOList;
    }

    public void setProductExtendAttributeDefinedVOList(List<ProductExtendAttributeDefinedVO> productExtendAttributeDefinedVOList) {
        this.productExtendAttributeDefinedVOList = productExtendAttributeDefinedVOList;
    }

    public List<ProductAttributeDefinedVO> getProductAttributeDefinedVOList() {
        return productAttributeDefinedVOList;
    }

    public void setProductAttributeDefinedVOList(List<ProductAttributeDefinedVO> productAttributeDefinedVOList) {
        this.productAttributeDefinedVOList = productAttributeDefinedVOList;
    }

    public List<ProductAttributeItemVO> getProductAttributeItemsVOList() {
        return productAttributeItemsVOList;
    }

    public void setProductAttributeItemsVOList(List<ProductAttributeItemVO> productAttributeItemsVOList) {
        this.productAttributeItemsVOList = productAttributeItemsVOList;
    }
}
