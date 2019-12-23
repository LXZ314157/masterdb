package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ViewStoreMapper;
import com.icicle.masterdb.model.ViewStore;
import com.icicle.masterdb.service.ViewStoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CodeGeneratorUtil
 */
@Service
public class ViewStoreServiceImpl extends AbstractService<ViewStore> implements ViewStoreService {
    @Resource
    private ViewStoreMapper viewStoreMapper;
}