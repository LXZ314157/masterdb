package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.ViewDepartmentMatrixListMapper;
import com.icicle.masterdb.model.ViewDepartmentMatrixList;
import com.icicle.masterdb.service.ViewDepartmentMatrixListService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-15 16:43:22.
*/
@Service
public class ViewDepartmentMatrixListServiceImpl extends AbstractService<ViewDepartmentMatrixList> implements ViewDepartmentMatrixListService {
    @Resource
    private ViewDepartmentMatrixListMapper viewDepartmentMatrixListMapper;
}