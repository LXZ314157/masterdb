package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author CodeGeneratorUtil
 */
public class SharedBuyerVO {
    private String buyerId;
    private Integer buyerNo;
    private String buyerName;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getBuyerNo() {
        return buyerNo;
    }

    public void setBuyerNo(Integer buyerNo) {
        this.buyerNo = buyerNo;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}