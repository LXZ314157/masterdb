package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.StoreProductLine;

import java.util.List;

public interface StoreProductLineMapper extends MyMapper<StoreProductLine> {

    List<StoreProductLine> findProductLineByStoreNo(String storeNo);
}