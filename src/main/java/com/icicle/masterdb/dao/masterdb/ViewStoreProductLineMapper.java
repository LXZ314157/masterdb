package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewStoreProductLine;

import java.util.List;

public interface ViewStoreProductLineMapper extends MyMapper<ViewStoreProductLine> {

    List<ViewStoreProductLine> findProductLineByStoreNo (String storeNo);

}