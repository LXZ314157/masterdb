package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.BuyerAttribItem;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface BuyerAttribItemMapper extends MyMapper<BuyerAttribItem> {
    /**
     * 批量更新选项值
     * @param buyerAttribItemList
     * @return
     */
    int updateBuyerItem(List<BuyerAttribItem> buyerAttribItemList);

    /**
     * 查询不同的defid对应的选项值
     * @return
     */
    List<BuyerAttribItem> searchDropItem();


    int saveBuyerItem(BuyerAttribItem buyerAttribItem);

}