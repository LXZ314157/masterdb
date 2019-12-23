package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ProductDimensionTraining;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductDimensionTrainingService extends Service<ProductDimensionTraining> {
    /**
     * 特点列表的获取
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param sSearch
     * @param productCode
     * @return
     */
    DataTableRecord listFeature(String sEcho, Integer pageIndex, Integer pageSize, String sSearch,String productCode);

    /**
     * 更新产品特点
     * @param productDimensionTraining
     * @return
     */
    int updateDimensionTrain(ProductDimensionTraining productDimensionTraining);

    /**
     * 新增产品特点
     * @param productDimensionTraining
     * @return
     */
    int insertDimensionTrain(ProductDimensionTraining productDimensionTraining);

    /**
     * 根据款号查询产品特点信息,单条全部
     * @param productCode
     * @return
     */
    ProductDimensionTraining findFeatureCodeDetail(String productCode);

    /**
     * 根据款号查询产品特点信息,单条部分
     * @param productCode
     * @return
     */
    ProductDimensionTraining findByFeatureCode(String productCode);
}