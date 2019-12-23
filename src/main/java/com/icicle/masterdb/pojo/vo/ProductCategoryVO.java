package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.ProductCategoryAttribute;
import com.icicle.masterdb.model.ProductDimensionAttribute;

import java.util.List;

/**
 * @author lvxuezhan
 * @version 2019-03-12 11:57
 */
public class ProductCategoryVO {

    private String productCategoryCode;

    private String categoryName;

    private  Integer productCategoryId;

    private String tableName;

    List<ProductCategoryAttribute> productCategoryAttributeList;

    List<ProductCategoryAttributeVO> productCategoryAttributeVOList;

    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ProductCategoryAttributeVO> getProductCategoryAttributeVOList() {
        return productCategoryAttributeVOList;
    }

    public void setProductCategoryAttributeVOList(List<ProductCategoryAttributeVO> productCategoryAttributeVOList) {
        this.productCategoryAttributeVOList = productCategoryAttributeVOList;
    }

    public List<ProductCategoryAttribute> getProductCategoryAttributeList() {
        return productCategoryAttributeList;
    }

    public void setProductCategoryAttributeList(List<ProductCategoryAttribute> productCategoryAttributeList) {
        this.productCategoryAttributeList = productCategoryAttributeList;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
