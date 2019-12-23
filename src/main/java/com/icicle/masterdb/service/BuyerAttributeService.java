package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.BuyerAttribute;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface BuyerAttributeService extends Service<BuyerAttribute> {

    /**
     * 批量更新itemValue
     * @param buyerAttributeList
     * @return
     */
    int updateBuyerAttribute(List<BuyerAttribute> buyerAttributeList);


    /**
     * 批量删除代理商属性
     * @param list
     * @param buyerNo
     * @return
     */
    int batchDeleteBuyerAttribute(List<Integer> list,Integer buyerNo);
}