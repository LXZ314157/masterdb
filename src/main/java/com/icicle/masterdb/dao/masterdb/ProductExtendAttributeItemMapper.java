package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ProductExtendAttributeItem;

import java.util.List;

public interface ProductExtendAttributeItemMapper extends MyMapper<ProductExtendAttributeItem> {

    int updateExtendAttributeItem(List<ProductExtendAttributeItem> updateList);


}