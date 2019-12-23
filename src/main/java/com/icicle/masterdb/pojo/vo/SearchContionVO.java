package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public class SearchContionVO {
    private List<BuyerVO> buyerVOList;
    private List<StoreLevelVO> storeLevelVOList;
    private List<StoreTypeVO> storeTypeVOList;

    public List<BuyerVO> getBuyerVOList() {
        return buyerVOList;
    }

    public void setBuyerVOList(List<BuyerVO> buyerVOList) {
        this.buyerVOList = buyerVOList;
    }

    public List<StoreLevelVO> getStoreLevelVOList() {
        return storeLevelVOList;
    }

    public void setStoreLevelVOList(List<StoreLevelVO> storeLevelVOList) {
        this.storeLevelVOList = storeLevelVOList;
    }

    public List<StoreTypeVO> getStoreTypeVOList() {
        return storeTypeVOList;
    }

    public void setStoreTypeVOList(List<StoreTypeVO> storeTypeVOList) {
        this.storeTypeVOList = storeTypeVOList;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
