package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.CompanyMapper;
import com.icicle.masterdb.model.Company;
import com.icicle.masterdb.service.CompanyService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author  CodeGeneratorUtil
* @version 2019-06-14 20:27:06.
*/
@Service
public class CompanyServiceImpl extends AbstractService<Company> implements CompanyService {
    @Resource
    private CompanyMapper companyMapper;
}