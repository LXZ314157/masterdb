package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ProductDimension;

import java.util.List;

/**
 * @author liurenhua
 */
public interface ProductDimensionMapper extends MyMapper<ProductDimension> {
    /**
     * 查找所有有效（status=1）的维度
     *
     * @return
     */
    List<ProductDimension> findAllDimension();

    /**
     * 插入一个维度，自动将维度id设置到这个实体中
     *
     * @param productDimension
     * @return
     */
    int saveDimension(ProductDimension productDimension);

    /**
     * 更新维度
     * @param productDimension
     * @return
     */
    int updateDimension(ProductDimension productDimension);
}