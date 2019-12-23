package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.StoreType;

import java.util.List;
/**
 * @author liurenhua
 */
public interface StoreTypeMapper extends MyMapper<StoreType> {
    /**
     * 添加店铺类型
     *
     * @param storeType
     * @return
     */
    int saveStoreType(StoreType storeType);

    /**
     * 获取所有stauts=1的店铺类型
     *
     * @return
     */
    List<StoreType> findValidType();
}