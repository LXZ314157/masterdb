package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ViewFindbyclothesTypeMapper;
import com.icicle.masterdb.model.ViewFindbyclothesType;
import com.icicle.masterdb.service.ViewFindbyclothesTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class ViewFindbyclothesTypeServiceImpl extends AbstractService<ViewFindbyclothesType> implements ViewFindbyclothesTypeService {
    @Resource
    private ViewFindbyclothesTypeMapper viewFindbyclothesTypeMapper;
}