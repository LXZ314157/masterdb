package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.StoreAttribDefined;

/**
 * @author liurenhua
 */
public interface StoreAttribDefinedMapper extends MyMapper<StoreAttribDefined> {
    /**
     * 插入店铺属性定义
     *
     * @param storeAttribDefined
     * @return
     */
    int insertStoreAttributeDef(StoreAttribDefined storeAttribDefined);

    /**
     * 根据主键来更新属性定义
     *
     * @param storeAttribDefined
     * @return
     */
    int updateStoreAttributeDef(StoreAttribDefined storeAttribDefined);
}