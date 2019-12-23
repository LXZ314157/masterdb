package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ViewProductDimensionMapper;
import com.icicle.masterdb.model.ViewProductDimension;
import com.icicle.masterdb.service.ViewProductDimensionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class ViewProductDimensionServiceImpl extends AbstractService<ViewProductDimension> implements ViewProductDimensionService {
    @Resource
    private ViewProductDimensionMapper viewProductDimensionMapper;

    @Override
    public List<ViewProductDimension> findByDimensionCode(String dimensionCode) {
        return viewProductDimensionMapper.findByDimensionCode(dimensionCode);
    }
}