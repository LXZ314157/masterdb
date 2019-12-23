package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.StoreProductLine;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-04-16 17:52:13.
*/
public interface StoreProductLineService extends Service<StoreProductLine> {

    /**
     * 根据店铺编号查找产品线
     * @param storeNo
     * @return
     */
    List<StoreProductLine> findProductLineByStoreNo(String storeNo);
}