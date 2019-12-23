package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.BuyerGroupAttrib;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface BuyerGroupAttribMapper extends MyMapper<BuyerGroupAttrib> {

    /**
     * 批量更新组属性
     * @param buyerGroupAttribList
     * @return
     */
    int updateBuyerGroup(List<BuyerGroupAttrib> buyerGroupAttribList);
}