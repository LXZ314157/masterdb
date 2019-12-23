package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ViewSelectproductinfoMapper;
import com.icicle.masterdb.model.ViewSelectproductinfo;
import com.icicle.masterdb.service.ViewSelectproductinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class ViewSelectproductinfoServiceImpl extends AbstractService<ViewSelectproductinfo> implements ViewSelectproductinfoService {
    @Resource
    private ViewSelectproductinfoMapper viewSelectproductinfoMapper;

    @Override
    public ViewSelectproductinfo searchByCondition(String productCode) {
        return viewSelectproductinfoMapper.searchByCondition(productCode);
    }
}