package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.ProductExtendAttributeItemMapper;
import com.icicle.masterdb.model.ProductExtendAttributeItem;
import com.icicle.masterdb.service.ProductExtendAttributeItemService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-07-09 17:35:16.
*/
@Service
public class ProductExtendAttributeItemServiceImpl extends AbstractService<ProductExtendAttributeItem> implements ProductExtendAttributeItemService {
    @Resource
    private ProductExtendAttributeItemMapper productExtendAttributeItemMapper;

    @Override
    public int updateProductExtendItem(List<ProductExtendAttributeItem> updateList) {
        return productExtendAttributeItemMapper.updateExtendAttributeItem(updateList);
    }
}