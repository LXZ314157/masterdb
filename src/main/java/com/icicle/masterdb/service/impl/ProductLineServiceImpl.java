package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.ProductLineMapper;
import com.icicle.masterdb.model.ProductDimensionAttribute;
import com.icicle.masterdb.model.ProductLine;
import com.icicle.masterdb.service.ProductLineService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-02-24 16:59:04.
*/
@Service
public class ProductLineServiceImpl extends AbstractService<ProductLine> implements ProductLineService {
    @Resource
    private ProductLineMapper productLineMapper;

    @Override
    public List<ProductLine> findProductLine() {
        return productLineMapper.selectAll();
    }
}