package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ProductCategoryAttribute;
import com.icicle.masterdb.pojo.vo.ProductCategoryAttributeVO;

import java.util.List;

public interface ProductCategoryAttributeMapper extends MyMapper<ProductCategoryAttribute> {

    List<ProductCategoryAttributeVO> findTableAttr(String productCategoryId);

}