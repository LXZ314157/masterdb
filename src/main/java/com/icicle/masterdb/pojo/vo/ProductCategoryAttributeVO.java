package com.icicle.masterdb.pojo.vo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author lvxuezhan
 * @version 2019-03-12 14:26
 */
public class ProductCategoryAttributeVO {

    private Integer id;

    private Integer productCategoryId;

    private Integer productAttributeDefineId;

    private Integer categoryAttributeState;

    private String defName;

    private String defCode;

    private String modelValue;

    private String defType;

    public String getModelValue() {
        return modelValue;
    }

    public void setModelValue(String modelValue) {
        this.modelValue = modelValue;
    }

    public String getDefName() {
        return defName;
    }

    public void setDefName(String defName) {
        this.defName = defName;
    }

    public String getDefCode() {
        return defCode;
    }

    public void setDefCode(String defCode) {
        this.defCode = defCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDefType() {
        return defType;
    }

    public void setDefType(String defType) {
        this.defType = defType;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Integer getProductAttributeDefineId() {
        return productAttributeDefineId;
    }

    public void setProductAttributeDefineId(Integer productAttributeDefineId) {
        this.productAttributeDefineId = productAttributeDefineId;
    }

    public Integer getCategoryAttributeState() {
        return categoryAttributeState;
    }

    public void setCategoryAttributeState(Integer categoryAttributeState) {
        this.categoryAttributeState = categoryAttributeState;
    }
}
