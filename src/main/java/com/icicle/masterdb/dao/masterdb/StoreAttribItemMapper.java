package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.StoreAttribItem;

import java.util.List;
/**
 * @author liurenhua
 */
public interface StoreAttribItemMapper extends MyMapper<StoreAttribItem> {
    /**
     * 批量更新店铺属性选项
     *
     * @param list
     * @return
     */
    int updateList(List<StoreAttribItem> list);

    /**
     *
     * @param storeAttribItem
     * @return
     */
    int saveStoreAttribDef(StoreAttribItem storeAttribItem);
}