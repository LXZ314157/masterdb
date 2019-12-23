package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.StoreAttribute;

import java.util.List;

/**
 * @author liurenhua
 */
public interface StoreAttributeMapper extends MyMapper<StoreAttribute> {

    /**
     * 批量更新店铺属性
     *
     * @param list
     * @return
     */
    int updateStoreAttributeList(List<StoreAttribute> list);

    /**
     * 根据店铺No查找店铺属性
     *
     * @param storeNo
     * @return
     */
    List<StoreAttribute> findByStoreNo(Integer storeNo);
}