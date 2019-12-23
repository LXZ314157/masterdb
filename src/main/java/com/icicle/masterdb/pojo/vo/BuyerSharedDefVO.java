package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.BuyerAttribGroup;
import com.icicle.masterdb.model.BuyerGroupAttrib;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public class BuyerSharedDefVO {
    private List<BuyerAttribDefinedVO> buyerAttribDefVOList;
    private List<BuyerGroupAttrib> buyerGroupAttribList;
    private List<BuyerAttribGroup> buyerAttribGroupList ;

    public List<BuyerAttribDefinedVO> getBuyerAttribDefVOList() {
        return buyerAttribDefVOList;
    }

    public void setBuyerAttribDefVOList(List<BuyerAttribDefinedVO> buyerAttribDefVOList) {
        this.buyerAttribDefVOList = buyerAttribDefVOList;
    }

    public List<BuyerGroupAttrib> getBuyerGroupAttribList() {
        return buyerGroupAttribList;
    }

    public void setBuyerGroupAttribList(List<BuyerGroupAttrib> buyerGroupAttribList) {
        this.buyerGroupAttribList = buyerGroupAttribList;
    }

    public List<BuyerAttribGroup> getBuyerAttribGroupList() {
        return buyerAttribGroupList;
    }

    public void setBuyerAttribGroupList(List<BuyerAttribGroup> buyerAttribGroupList) {
        this.buyerAttribGroupList = buyerAttribGroupList;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
