package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.VStores;

public interface VStoresMapper extends MyMapper<VStores> {

    /**
     * 获取同步的店铺信息
     *
     * @param storeid
     * @return
     */
    VStores findStoreInfo(String storeid);
}