package com.icicle.masterdb.service;

import com.icicle.masterdb.model.ProductExtendAttributeItem;
import com.icicle.masterdb.core.Service;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-07-09 17:35:16.
*/
public interface ProductExtendAttributeItemService extends Service<ProductExtendAttributeItem> {


    int updateProductExtendItem(List<ProductExtendAttributeItem> updateList);


}