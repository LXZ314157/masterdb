package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.ViewBuyerListMapper;
import com.icicle.masterdb.model.ViewBuyerList;
import com.icicle.masterdb.service.ViewBuyerListService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author  CodeGeneratorUtil
* @version 2018-08-06 11:14:30.
*/
@Service
public class ViewBuyerListServiceImpl extends AbstractService<ViewBuyerList> implements ViewBuyerListService {
    @Resource
    private ViewBuyerListMapper viewBuyerListMapper;
}