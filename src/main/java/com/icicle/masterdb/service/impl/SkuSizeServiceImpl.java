package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.SkuSizeMapper;
import com.icicle.masterdb.model.SkuSize;
import com.icicle.masterdb.service.SkuSizeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class SkuSizeServiceImpl extends AbstractService<SkuSize> implements SkuSizeService {
    @Resource
    private SkuSizeMapper skuSizeMapper;
    @Override
    public List<SkuSize> findBySize(){
        return skuSizeMapper.findBySize();
    }
}