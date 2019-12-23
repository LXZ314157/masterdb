package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ViewProductDescriptionMapper;
import com.icicle.masterdb.model.Product;
import com.icicle.masterdb.model.ViewProductDescription;
import com.icicle.masterdb.service.ViewProductDescriptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class ViewProductDescriptionServiceImpl extends AbstractService<ViewProductDescription> implements ViewProductDescriptionService {
    @Resource
    private ViewProductDescriptionMapper viewProductDescriptionMapper;
    @Override
    public List<ViewProductDescription> findByList(List<Product> products){
        return  viewProductDescriptionMapper.findByList(products);
    }
    @Override
    public ViewProductDescription findOnlyDesc(String productCode){
        return  viewProductDescriptionMapper.findOnlyDesc(productCode);
    }
}