package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.ViewRespListMapper;
import com.icicle.masterdb.model.ViewRespList;
import com.icicle.masterdb.service.ViewRespListService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-14 10:54:14.
*/
@Service
public class ViewRespListServiceImpl extends AbstractService<ViewRespList> implements ViewRespListService {
    @Resource
    private ViewRespListMapper viewRespListMapper;
}