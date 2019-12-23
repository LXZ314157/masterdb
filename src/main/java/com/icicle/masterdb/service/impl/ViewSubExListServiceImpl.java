package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.ViewSubExListMapper;
import com.icicle.masterdb.model.ViewSubExList;
import com.icicle.masterdb.service.ViewSubExListService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-15 13:05:35.
*/
@Service
public class ViewSubExListServiceImpl extends AbstractService<ViewSubExList> implements ViewSubExListService {
    @Resource
    private ViewSubExListMapper viewSubExListMapper;
}