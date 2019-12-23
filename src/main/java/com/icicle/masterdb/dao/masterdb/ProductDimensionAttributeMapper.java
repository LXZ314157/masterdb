package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ProductDimensionAttribute;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductDimensionAttributeMapper extends MyMapper<ProductDimensionAttribute> {
    /**
     * 获取已经关联过得属性id
     * @return
     */
    List<String> findConnectDefId();
 }