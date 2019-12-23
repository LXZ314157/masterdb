package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.BuyerGroupAttrib;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public class BuyerGroupAttributeListVO {
    private Integer buyerAttribGroupId;
    private List<BuyerGroupAttrib> buyerGroupAttribList;

    public Integer getBuyerAttribGroupId() {
        return buyerAttribGroupId;
    }

    public void setBuyerAttribGroupId(Integer buyerAttribGroupId) {
        this.buyerAttribGroupId = buyerAttribGroupId;
    }

    public List<BuyerGroupAttrib> getBuyerGroupAttribList() {
        return buyerGroupAttribList;
    }

    public void setBuyerGroupAttribList(List<BuyerGroupAttrib> buyerGroupAttribList) {
        this.buyerGroupAttribList = buyerGroupAttribList;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
