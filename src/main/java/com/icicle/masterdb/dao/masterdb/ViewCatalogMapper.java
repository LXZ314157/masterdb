package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewCatalog;

/**
 * @author CodeGeneratorUtil
 */
public interface ViewCatalogMapper extends MyMapper<ViewCatalog> {
    /**
     * 根据systemCode获取大中小类信息
     * @param systemCode
     * @return
     */
    ViewCatalog searchBySystemCode(String systemCode);
}