package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewSelectproductinfo;

/**
 * @author CodeGeneratorUtil
 */
public interface ViewSelectproductinfoMapper extends MyMapper<ViewSelectproductinfo> {
    /**
     * 条件查询
     * @param productCode
     * @return
     */
    ViewSelectproductinfo  searchByCondition(String productCode);
}