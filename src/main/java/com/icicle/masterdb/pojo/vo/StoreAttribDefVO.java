package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author liurenhua
 */
public class StoreAttribDefVO {
    private Integer storeAttribDefId;
    private String storeAttribDefName;
    private String itemType;
    private Integer itemLength;
    private Integer defineOrder;
    private Boolean isSycn;
    private String storeAttribDefCode;
    private Boolean hasItem;
    private Integer status;
    private Integer storeAttribNature;
    private List<StoreAttributeItemVO> itemList;

    public Integer getStoreAttribDefId() {
        return storeAttribDefId;
    }

    public void setStoreAttribDefId(Integer storeAttribDefId) {
        this.storeAttribDefId = storeAttribDefId;
    }

    public String getStoreAttribDefName() {
        return storeAttribDefName;
    }

    public void setStoreAttribDefName(String storeAttribDefName) {
        this.storeAttribDefName = storeAttribDefName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getItemLength() {
        return itemLength;
    }

    public void setItemLength(Integer itemLength) {
        this.itemLength = itemLength;
    }

    public String getStoreAttribDefCode() {
        return storeAttribDefCode;
    }

    public void setStoreAttribDefCode(String storeAttribDefCode) {
        this.storeAttribDefCode = storeAttribDefCode;
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

    public List<StoreAttributeItemVO> getItemList() {
        return itemList;
    }

    public void setItemList(List<StoreAttributeItemVO> itemList) {
        this.itemList = itemList;
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

    public Integer getStoreAttribNature() {
        return storeAttribNature;
    }

    public void setStoreAttribNature(Integer storeAttribNature) {
        this.storeAttribNature = storeAttribNature;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
