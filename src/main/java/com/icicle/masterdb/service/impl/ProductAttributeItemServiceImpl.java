package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ProductAttributeItemMapper;
import com.icicle.masterdb.model.ProductAttributeItem;
import com.icicle.masterdb.pojo.vo.ProductAttributeItemVO;
import com.icicle.masterdb.service.ProductAttributeItemService;
import com.icicle.masterdb.util.PojoConvertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
@Service
public class ProductAttributeItemServiceImpl extends AbstractService<ProductAttributeItem> implements ProductAttributeItemService {
    @Resource
    private ProductAttributeItemMapper productAttributeItemMapper;

    @Override
    public int updateDimensionItem(List<ProductAttributeItem> productAttributeItemList) {
        return productAttributeItemMapper.updateDimensionItem(productAttributeItemList);
    }

    @Override
    public List<ProductAttributeItemVO> findAttributeItemVO() {
        return PojoConvertUtil.convertPojoList(productAttributeItemMapper.findAttributeItemVO(), ProductAttributeItemVO.class);
    }
}