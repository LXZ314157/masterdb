package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.ProductCategoryAttributeMapper;
import com.icicle.masterdb.model.ProductCategoryAttribute;
import com.icicle.masterdb.pojo.vo.ProductCategoryAttributeVO;
import com.icicle.masterdb.service.ProductCategoryAttributeService;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.util.PojoConvertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-03-12 14:38:41.
*/
@Service
public class ProductCategoryAttributeServiceImpl extends AbstractService<ProductCategoryAttribute> implements ProductCategoryAttributeService {
    @Resource
    private ProductCategoryAttributeMapper productCategoryAttributeMapper;


    @Override
    public List<ProductCategoryAttributeVO> findAllProductCategoryAttriVO() {
        List<ProductCategoryAttribute> ProductCategoryAttributes = productCategoryAttributeMapper.selectAll();
        List<ProductCategoryAttributeVO> dimensionAttriVOList = PojoConvertUtil.convertPojoList(ProductCategoryAttributes, ProductCategoryAttributeVO.class);
        return dimensionAttriVOList;
    }

    @Override
    public List<ProductCategoryAttributeVO> findTableAttr(String productCategoryId) {
        return productCategoryAttributeMapper.findTableAttr(productCategoryId);
    }
}