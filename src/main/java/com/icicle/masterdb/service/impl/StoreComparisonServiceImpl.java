package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.StoreComparisonMapper;
import com.icicle.masterdb.model.StoreComparison;
import com.icicle.masterdb.service.StoreComparisonService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author  CodeGeneratorUtil
* @version 2019-04-16 10:54:02.
*/
@Service
public class StoreComparisonServiceImpl extends AbstractService<StoreComparison> implements StoreComparisonService {
    @Resource
    private StoreComparisonMapper storeComparisonMapper;
}