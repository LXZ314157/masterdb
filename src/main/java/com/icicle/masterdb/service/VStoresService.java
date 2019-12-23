package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.VStores;

/**
* @author  CodeGeneratorUtil
* @version 2018-11-16 13:49:49.
*/
public interface VStoresService extends Service<VStores> {

    /**
     * 获取同步的店铺信息
     *
     * @param storeid
     * @return
     */
    VStores findStoreInfo(String storeid);
}