package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author wangyuling
 * @version 2018/1/11 15:01
 */
public class BuyerAttribDefVO {
    private Integer buyerAttribDefId;
    private String buyerAttribDefCode;
    private String buyerAttribDefName;
    private Boolean hasItem;
    private Integer status;
    private Integer defineOrder;
    private Boolean isSycn;
    private Integer itemLength;
    private String itemType;

    public Integer getBuyerAttribDefId() {
        return buyerAttribDefId;
    }

    public void setBuyerAttribDefId(Integer buyerAttribDefId) {
        this.buyerAttribDefId = buyerAttribDefId;
    }

    public String getBuyerAttribDefCode() {
        return buyerAttribDefCode;
    }

    public void setBuyerAttribDefCode(String buyerAttribDefCode) {
        this.buyerAttribDefCode = buyerAttribDefCode;
    }

    public String getBuyerAttribDefName() {
        return buyerAttribDefName;
    }

    public void setBuyerAttribDefName(String buyerAttribDefName) {
        this.buyerAttribDefName = buyerAttribDefName;
    }

    public Boolean getHasItem() {
        return hasItem;
    }

    public void setHasItem(Boolean hasItem) {
        this.hasItem = hasItem;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getItemLength() {
        return itemLength;
    }

    public void setItemLength(Integer itemLength) {
        this.itemLength = itemLength;
    }

    public Integer getDefineOrder() {
        return defineOrder;
    }

    public void setDefineOrder(Integer defineOrder) {
        this.defineOrder = defineOrder;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Boolean getSycn() {
        return isSycn;
    }

    public void setSycn(Boolean sycn) {
        isSycn = sycn;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
