package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ProductAttributeItem;
import com.icicle.masterdb.pojo.vo.ProductAttributeItemVO;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductAttributeItemService extends Service<ProductAttributeItem> {

    /**
     * 批量更新属性选项
     *
     * @param productAttributeItemList
     * @return
     */
    int updateDimensionItem(List<ProductAttributeItem> productAttributeItemList);


    /**
     * 获取所有的下拉选项VO
     *
     * @return
     */
    List<ProductAttributeItemVO> findAttributeItemVO();
}