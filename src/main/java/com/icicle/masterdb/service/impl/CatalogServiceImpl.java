package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.CatalogMapper;
import com.icicle.masterdb.model.Catalog;
import com.icicle.masterdb.service.CatalogService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author  CodeGeneratorUtil
* @version 2019-05-20 17:09:13.
*/
@Service
public class CatalogServiceImpl extends AbstractService<Catalog> implements CatalogService {
    @Resource
    private CatalogMapper catalogMapper;
}