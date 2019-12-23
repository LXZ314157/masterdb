package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ViewProductDesc;

/**
 * @author CodeGeneratorUtil
 */
public interface ViewProductDescService extends Service<ViewProductDesc> {
    /**
     * 根据款号查找的mapper方法
     * @param productCode
     * @return
     */
    ViewProductDesc findByCondition(String productCode);
}