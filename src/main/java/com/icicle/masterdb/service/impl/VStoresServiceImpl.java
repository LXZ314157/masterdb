package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.VStoresMapper;
import com.icicle.masterdb.model.VStores;
import com.icicle.masterdb.service.VStoresService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CodeGeneratorUtil
 * @version 2018-11-16 13:49:49.
 */
@Service
public class VStoresServiceImpl extends AbstractService<VStores> implements VStoresService {
    @Resource
    private VStoresMapper vStoresMapper;

    @Override
    public VStores findStoreInfo(String storeid) {
        return vStoresMapper.findStoreInfo(storeid);
    }
}