package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.BuyerAttribItem;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public class BuyerAttribDefinedVO {
    private Integer buyerAttribDefId;
    private String buyerAttribDefCode;
    private String buyerAttribDefName;
    private String itemType;
    private Boolean hasItem;
    private Integer status;
    private Integer defineOrder;
    private Boolean isSycn;

    private Integer itemLength;

    public Integer getItemLength() {
        return itemLength;
    }

    public void setItemLength(Integer itemLength) {
        this.itemLength = itemLength;
    }

    private String buyerAttribItemCode;
    private String buyerAttribItemName;
    private List<BuyerAttribItem> list;

    public List<BuyerAttribItem> getList() {
        return list;
    }

    public void setList(List<BuyerAttribItem> list) {
        this.list = list;
    }

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

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
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

    public String getBuyerAttribItemCode() {
        return buyerAttribItemCode;
    }

    public void setBuyerAttribItemCode(String buyerAttribItemCode) {
        this.buyerAttribItemCode = buyerAttribItemCode;
    }

    public String getBuyerAttribItemName() {
        return buyerAttribItemName;
    }

    public void setBuyerAttribItemName(String buyerAttribItemName) {
        this.buyerAttribItemName = buyerAttribItemName;
    }

    public Integer getDefineOrder() {
        return defineOrder;
    }

    public void setDefineOrder(Integer defineOrder) {
        this.defineOrder = defineOrder;
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
