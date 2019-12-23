package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ProductAttributeDefined;
import com.icicle.masterdb.model.ProductAttributeItem;
import com.icicle.masterdb.pojo.vo.ProductAttributeDefinedVO;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductAttributeDefinedService extends Service<ProductAttributeDefined> {

    /**
     * 获取所有的产品属性VO
     *
     * @return
     */
    List<ProductAttributeDefinedVO> findAllAttributeDefinedVO();

    /**
     * 获取所有商品类别属性
     * @return
     */
    List<ProductAttributeDefinedVO> findAllProductCategoryAttributeDefinedVO();


    /**
     * 添加属性定义和选项
     * @param productAttributeDefinedVO
     * @return
     */
    int addAttributeDef(ProductAttributeDefinedVO productAttributeDefinedVO);

    /**
     * 添加产品类别属性
     * @param productAttributeDefinedVO
     * @return
     */
    int addAttributeDefs(ProductAttributeDefinedVO productAttributeDefinedVO);

    /**
     *更新属性定义和选项
     * @param productAttributeDefinedVO
     * @return
     */
    int updateAttributeDef(ProductAttributeDefinedVO productAttributeDefinedVO);

    int updateDimensionAttributeDef(ProductAttributeDefinedVO productAttributeDefinedVO);

    /**
     * 根据DefId查询选项
     * @param id
     * @return
     */
    List<ProductAttributeItem>  findItemById(Integer id);

}