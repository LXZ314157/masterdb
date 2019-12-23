package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ProductDimensionTraining;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductDimensionTrainingMapper extends MyMapper<ProductDimensionTraining> {
    /**
     * 按条件款号查询特点
     * @param productCode
     * @return
     */
    ProductDimensionTraining searchByProductCode(String productCode);
}