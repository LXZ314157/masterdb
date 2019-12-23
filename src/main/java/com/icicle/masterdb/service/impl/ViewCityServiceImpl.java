package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ViewCityMapper;
import com.icicle.masterdb.model.ViewCity;
import com.icicle.masterdb.service.ViewCityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CodeGeneratorUtil
 */
@Service
public class ViewCityServiceImpl extends AbstractService<ViewCity> implements ViewCityService {
    @Resource
    private ViewCityMapper viewCityMapper;
}