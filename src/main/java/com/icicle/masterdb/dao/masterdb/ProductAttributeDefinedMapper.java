package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ProductAttributeDefined;
import com.icicle.masterdb.model.ProductCategoryAttribute;
import com.icicle.masterdb.model.ProductDimensionAttribute;

import java.util.List;

/**
 * @author liurenhua
 */
public interface ProductAttributeDefinedMapper extends MyMapper<ProductAttributeDefined> {
    /**
     * 获取所有的产品属性定义
     *
     * @return
     */
    List<ProductAttributeDefined> findAllAttributeDefied();

    /**
     * 获取所有商品类别属性定义
     * @return
     */
    List<ProductAttributeDefined> findAllProductCategoryAttributeDefined();

    /**
     * 插入操作
     *
     * @param productAttributeDefined
     * @return
     */
    int insertAll(ProductAttributeDefined productAttributeDefined);

    /**
     * 插入产品类别属性
     * @param productAttributeDefined
     * @return
     */
    int insertAllProductCategoryAttri(ProductAttributeDefined productAttributeDefined);

    /**
     * 获取已被维度关联属性的信息
     *
     * @param productDimensionAttributeList
     * @return
     */
    List<ProductAttributeDefined> searchByAttributeId(List<ProductDimensionAttribute> productDimensionAttributeList);


    /**
     *
     * @param productCategoryAttributeList
     * @return
     */
    List<ProductAttributeDefined> searchByAttributeIds(List<ProductCategoryAttribute> productCategoryAttributeList);

    /**
     * 单条更新
     *
     * @param productAttributeDefined
     * @return
     */
    int updateDefindItem(ProductAttributeDefined productAttributeDefined);


    int updateDimensionAttributeDef(ProductAttributeDefined productAttributeDefinedPojo);

}
