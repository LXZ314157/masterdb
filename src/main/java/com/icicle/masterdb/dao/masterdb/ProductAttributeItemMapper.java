package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ProductAttributeItem;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductAttributeItemMapper extends MyMapper<ProductAttributeItem> {


    /**
     * 批量更新属性选项
     * @param productAttributeItemList
     * @return
     */
    int updateDimensionItem(List<ProductAttributeItem> productAttributeItemList);


    /**
     * 查询选项列表
     * @return
     */
    List<ProductAttributeItem> findAttributeItemVO();
}