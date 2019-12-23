package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.SelectBrandMapper;
import com.icicle.masterdb.model.SelectBrand;
import com.icicle.masterdb.service.SelectBrandService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author  CodeGeneratorUtil
* @version 2019-05-20 16:11:23.
*/
@Service
public class SelectBrandServiceImpl extends AbstractService<SelectBrand> implements SelectBrandService {
    @Resource
    private SelectBrandMapper selectBrandMapper;
}