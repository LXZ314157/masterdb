package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ProductLookMapper;
import com.icicle.masterdb.model.ProductLook;
import com.icicle.masterdb.service.ProductLookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class ProductLookServiceImpl extends AbstractService<ProductLook> implements ProductLookService {
    @Resource
    private ProductLookMapper productLookMapper;
}