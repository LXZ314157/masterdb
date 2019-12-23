package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ProductAttributeExtend;
import com.icicle.masterdb.pojo.vo.ProductAttributeItemsVO;
import com.icicle.masterdb.pojo.vo.ProductFindVO;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductAttributeExtendService extends Service<ProductAttributeExtend> {
    /**属性列表的批量插入
     * @param productAttributeExtendList
     * @return
     */
    int insertAttributeExtendList(List<ProductAttributeExtend> productAttributeExtendList);

    /**
     * 属性的批量更新
     * @param productAttributeExtends
     * @return
     */
    int updateAttributePart(List<ProductAttributeExtend> productAttributeExtends);

    /**
     * 根据款号查询属性
     * @param productCode
     * @return
     */
     ProductAttributeExtend searchByCondition(String productCode);

    /**
     * 更新属性
     * @param productAttributeItemsVO
     * @return
     */
     int updateStyle(ProductAttributeItemsVO productAttributeItemsVO);

    /**
     * 获取单个产品的基本信息
     * @param productCode
     * @return
     */
    ProductFindVO findByProductCode(String productCode,String categoryKey);

}