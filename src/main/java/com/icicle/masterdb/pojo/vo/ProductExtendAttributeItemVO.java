package com.icicle.masterdb.pojo.vo;

import java.util.List;

public class ProductExtendAttributeItemVO {
    private Integer id;
    private Integer defId;
    private Integer code;
    private String name;
    private Boolean status;
    private List<ProductExtendAttributeDefinedVO> productExtendAttributeDefinedVOList;
    private List<ProductExtendAttributeItemVO> productExtendAttributeItemsVOList;

    public List<ProductExtendAttributeDefinedVO> getProductExtendAttributeDefinedVOList() {
        return productExtendAttributeDefinedVOList;
    }

    public void setProductExtendAttributeDefinedVOList(List<ProductExtendAttributeDefinedVO> productExtendAttributeDefinedVOList) {
        this.productExtendAttributeDefinedVOList = productExtendAttributeDefinedVOList;
    }

    public List<ProductExtendAttributeItemVO> getProductExtendAttributeItemsVOList() {
        return productExtendAttributeItemsVOList;
    }

    public void setProductExtendAttributeItemsVOList(List<ProductExtendAttributeItemVO> productExtendAttributeItemsVOList) {
        this.productExtendAttributeItemsVOList = productExtendAttributeItemsVOList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDefId() {
        return defId;
    }

    public void setDefId(Integer defId) {
        this.defId = defId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}