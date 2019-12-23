package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ViewMechandItemMapper;
import com.icicle.masterdb.model.ViewMechandItem;
import com.icicle.masterdb.service.ViewMechandItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author  CodeGeneratorUtil
* @version 2017-12-29 18:53:05.
*/
@Service
public class ViewMechandItemServiceImpl extends AbstractService<ViewMechandItem> implements ViewMechandItemService {
    @Resource
    private ViewMechandItemMapper viewMechandItemMapper;
}