package com.icicle.masterdb.service;

import com.icicle.masterdb.model.ProductCategoryAttribute;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.pojo.vo.ProductCategoryAttributeVO;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-03-12 14:38:41.
*/
public interface ProductCategoryAttributeService extends Service<ProductCategoryAttribute> {

    /**
     *
     * @return
     */
    List<ProductCategoryAttributeVO> findAllProductCategoryAttriVO();

    /**
     * 根据productCategoryId获取已经关联的字段
     * @param productCategoryId
     * @return
     */
    List<ProductCategoryAttributeVO> findTableAttr(String productCategoryId);
}