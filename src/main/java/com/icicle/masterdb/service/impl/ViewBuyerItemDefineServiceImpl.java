package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ViewBuyerItemDefineMapper;
import com.icicle.masterdb.model.ViewBuyerItemDefine;
import com.icicle.masterdb.service.ViewBuyerItemDefineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class ViewBuyerItemDefineServiceImpl extends AbstractService<ViewBuyerItemDefine> implements ViewBuyerItemDefineService {
    @Resource
    private ViewBuyerItemDefineMapper viewBuyerItemDefineMapper;
}