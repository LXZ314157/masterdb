package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ViewProductDescMapper;
import com.icicle.masterdb.model.ViewProductDesc;
import com.icicle.masterdb.service.ViewProductDescService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class ViewProductDescServiceImpl extends AbstractService<ViewProductDesc> implements ViewProductDescService {
    @Resource
    private ViewProductDescMapper viewProductDescMapper;
    @Override
    public ViewProductDesc findByCondition(String productCode){
        return viewProductDescMapper.findByCondition(productCode);
    }
}