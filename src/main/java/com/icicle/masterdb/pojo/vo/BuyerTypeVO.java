package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author CodeGeneratorUtil
 */
public class BuyerTypeVO {
    private  Integer buyerTypeId;
    private  String buyerTypeDesc;
    private String buyerTypeName;
    private Integer status;

    public Integer getBuyerTypeId() {
        return buyerTypeId;
    }

    public void setBuyerTypeId(Integer buyerTypeId) {
        this.buyerTypeId = buyerTypeId;
    }

    public String getBuyerTypeDesc() {
        return buyerTypeDesc;
    }

    public void setBuyerTypeDesc(String buyerTypeDesc) {
        this.buyerTypeDesc = buyerTypeDesc;
    }

    public String getBuyerTypeName() {
        return buyerTypeName;
    }

    public void setBuyerTypeName(String buyerTypeName) {
        this.buyerTypeName = buyerTypeName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
