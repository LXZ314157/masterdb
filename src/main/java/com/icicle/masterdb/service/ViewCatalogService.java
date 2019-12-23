package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ViewCatalog;

/**
 * @author CodeGeneratorUtil
 */
public interface ViewCatalogService extends Service<ViewCatalog> {
    /**
     * 根据systermCode获取大中小类信息
     * @param systemCode
     * @return
     */
    ViewCatalog searchBySystemCode(String systemCode);
}