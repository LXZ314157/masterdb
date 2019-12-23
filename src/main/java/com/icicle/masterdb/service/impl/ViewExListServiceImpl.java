package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.ViewExListMapper;
import com.icicle.masterdb.model.ViewExList;
import com.icicle.masterdb.service.ViewExListService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-15 10:36:34.
*/
@Service
public class ViewExListServiceImpl extends AbstractService<ViewExList> implements ViewExListService {
    @Resource
    private ViewExListMapper viewExListMapper;
}