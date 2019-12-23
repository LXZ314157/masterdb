package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ProductDimensionMerchandising;

import java.util.List;

/**
 * @author GenoratorUtil
 */
public interface ProductDimensionMerchandisingMapper extends MyMapper<ProductDimensionMerchandising> {
    /**
     * 根据款号查询
     *
     * @return
     */
    List<String> findAllProductCode();

    /**
     * 根据产品编码和语言查找企划属性
     *
     * @param productCode
     * @return
     */
    ProductDimensionMerchandising findByCode(String productCode);

    /**
     * 根据productCode和language更新企划
     *
     * @param productDimensionMerchandising
     * @return
     */
    int updateMerchandising(ProductDimensionMerchandising productDimensionMerchandising);

    /**
     * 批量插入企划属性
     *
     * @param list
     * @return
     */
    int saveList(List<ProductDimensionMerchandising> list);
}