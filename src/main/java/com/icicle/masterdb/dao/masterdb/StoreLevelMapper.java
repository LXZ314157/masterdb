package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.StoreLevel;

import java.util.List;
/**
 * @author liurenhua
 */
public interface StoreLevelMapper extends MyMapper<StoreLevel> {
    /**
     * 新增店铺级别
     *
     * @param storeLevel
     * @return
     */
    int saveStoreLevel(StoreLevel storeLevel);

    /**
     * 查找所有有效的(status=1)店铺级别
     *
     * @return
     */
    List<StoreLevel> findValidLevel();
}