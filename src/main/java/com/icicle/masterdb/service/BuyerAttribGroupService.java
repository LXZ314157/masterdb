package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.BuyerAttribGroup;

/**
 * @author CodeGeneratorUtil
 */
public interface BuyerAttribGroupService extends Service<BuyerAttribGroup> {
    /**
     * 新增代理商属性组
     * @param buyerAttribGroupName
     * @param buyerAttribGroupCode
     * @return
     */
    int insertBuyerAttributeGroup(String buyerAttribGroupName,String buyerAttribGroupCode);

    /**
     * 软性删除更改状态为0
     * @param buyerAttribGroup
     * @return
     */
    int deleteBuyerAttributeGroup(BuyerAttribGroup buyerAttribGroup);
}