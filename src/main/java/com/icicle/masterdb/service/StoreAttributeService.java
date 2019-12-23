package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.StoreAttribute;
import com.icicle.masterdb.model.ViewStoreProductLine;

import java.util.List;

/**
 * @author liurenhua
 */
public interface StoreAttributeService extends Service<StoreAttribute> {
    /**
     * 根据店铺No获得属性
     *
     *
     * @param storeNo
     * @return
     */
    List<StoreAttribute> findByStoreNo(Integer storeNo);


    /**
     * 根据店铺No获取产品线
     * @param storeNo
     * @return
     */
    List<ViewStoreProductLine> findViewProductLineByStoreNo(Integer storeNo);

}