package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewProductDesc;

/**
 * @author CodeGeneratorUtil
 */
public interface ViewProductDescMapper extends MyMapper<ViewProductDesc> {
    /**
     * 根据款号查询
     * @param productCode
     * @return
     */
    ViewProductDesc findByCondition(String productCode);

}