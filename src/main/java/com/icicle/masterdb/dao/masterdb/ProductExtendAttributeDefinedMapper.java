package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ProductExtendAttributeDefined;
import com.icicle.masterdb.pojo.vo.ProductExtendAttributeDefinedVO;

import java.util.List;

public interface ProductExtendAttributeDefinedMapper extends MyMapper<ProductExtendAttributeDefined> {


    int  insertAllProductCategoryAttri(ProductExtendAttributeDefined productExtendAttributeDefined);

    List<ProductExtendAttributeDefinedVO> findAllProductCategoryAttributeDefinedVO();

    int updateDefindItem(ProductExtendAttributeDefined productExtendAttributeDefined);

}