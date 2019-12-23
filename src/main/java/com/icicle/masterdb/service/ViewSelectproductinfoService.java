package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ViewSelectproductinfo;

/**
 * @author CodeGeneratorUtil
 */
public interface ViewSelectproductinfoService extends Service<ViewSelectproductinfo> {
    /**
     * 根据款号查询
     * @param productCode
     * @return
     */
    ViewSelectproductinfo  searchByCondition(String productCode);
}