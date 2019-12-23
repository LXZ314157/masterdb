package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ProductExtendAttributeDefined;
import com.icicle.masterdb.model.ProductExtendAttributeItem;
import com.icicle.masterdb.pojo.vo.ProductExtendAttributeDefinedVO;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-07-09 17:04:31.
*/
public interface ProductExtendAttributeDefinedService extends Service<ProductExtendAttributeDefined> {

   int  addAttributeDef(ProductExtendAttributeDefinedVO productExtendAttributeDefinedVO);

   List<ProductExtendAttributeDefinedVO> findAllProductCategoryAttributeDefinedVO();

   List<ProductExtendAttributeItem> findItemById(Integer id);

   int updateAttributeDef(ProductExtendAttributeDefinedVO productExtendAttributeDefinedVO);

}