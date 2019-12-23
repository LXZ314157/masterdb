package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.BuyerAttribItem;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface BuyerAttribItemService extends Service<BuyerAttribItem> {

    /**
     * 批量更新选项值
     * @param buyerAttribItemList
     * @return
     */
    int updateBuyerItem(List<BuyerAttribItem> buyerAttribItemList);

    /**
     * 根据属性定义id查询所有选项
     * @param buyerAttribDefId
     * @return
     */
    List<BuyerAttribItem> searchItemByDefId(Integer buyerAttribDefId);

    /**
     * 保存选项值
     * @param buyerAttribItem
     * @return
     */
    int saveBuyerItem(BuyerAttribItem buyerAttribItem);


}