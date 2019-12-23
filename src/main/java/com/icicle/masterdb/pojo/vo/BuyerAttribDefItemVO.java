package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.BuyerAttribItem;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public class BuyerAttribDefItemVO {
    private List<BuyerAttribItem> buyerAttribItemList;
    private List<BuyerAttribDefVO> buyerAttribDefVOList;

    public List<BuyerAttribDefVO> getBuyerAttribDefVOList() {
        return buyerAttribDefVOList;
    }

    public void setBuyerAttribDefVOList(List<BuyerAttribDefVO> buyerAttribDefVOList) {
        this.buyerAttribDefVOList = buyerAttribDefVOList;
    }

    public List<BuyerAttribItem> getBuyerAttribItemList() {
        return buyerAttribItemList;
    }

    public void setBuyerAttribItemList(List<BuyerAttribItem> buyerAttribItemList) {
        this.buyerAttribItemList = buyerAttribItemList;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
