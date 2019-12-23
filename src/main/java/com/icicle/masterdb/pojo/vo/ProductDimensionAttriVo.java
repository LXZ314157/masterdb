package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.ProductDimensionAttribute;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public class ProductDimensionAttriVo {
    private String classDimensionCode;
    private  Integer dimensionId;
    List<ProductDimensionAttribute> productDimensionAttributeList;

    public List<ProductDimensionAttribute> getProductDimensionAttributeList() {
        return productDimensionAttributeList;
    }

    public void setProductDimensionAttributeList(List<ProductDimensionAttribute> productDimensionAttributeList) {
        this.productDimensionAttributeList = productDimensionAttributeList;
    }

    public String getClassDimensionCode() {
        return classDimensionCode;
    }

    public void setClassDimensionCode(String classDimensionCode) {
        this.classDimensionCode = classDimensionCode;
    }

    public Integer getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(Integer dimensionId) {
        this.dimensionId = dimensionId;
    }
}
