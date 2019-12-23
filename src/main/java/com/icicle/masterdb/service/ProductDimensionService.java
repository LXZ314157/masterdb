package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ProductDimension;
import com.icicle.masterdb.pojo.vo.ProductAttributeDefinedVO;
import com.icicle.masterdb.pojo.vo.ProductDimensionVO;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductDimensionService extends Service<ProductDimension> {
    /**
     * 获取所有的维度和维度属性定义
     *
     * @return
     */
    List<ProductDimensionVO> findAllProductDimensionVO();


    /**
     * 插入一个维度
     *
     * @param productDimension
     * @return
     */
    int saveDimension(ProductDimension productDimension);

    /**
     * 更新一个维度
     * @param productDimension
     * @return
     */
    int updateDimension(ProductDimension productDimension);


    int updateAttributeDef(ProductAttributeDefinedVO productAttributeDefinedVO);
}