package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ViewCatalogMapper;
import com.icicle.masterdb.model.ViewCatalog;
import com.icicle.masterdb.service.ViewCatalogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class ViewCatalogServiceImpl extends AbstractService<ViewCatalog> implements ViewCatalogService {
    @Resource
    private ViewCatalogMapper viewCatalogMapper;
    @Override
    public  ViewCatalog searchBySystemCode(String systemCode){
        return  viewCatalogMapper.searchBySystemCode(systemCode);
    }
}