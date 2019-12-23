package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ProductAttribute;
import com.icicle.masterdb.model.ProductDimensionMerchandising;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductDimensionMerchandisingService extends Service<ProductDimensionMerchandising> {
    /**
     * 加载企划列表
     *
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param sSearch
     * @return
     */
    DataTableRecord listMerchandList(String sEcho, Integer pageIndex, Integer pageSize, String sSearch);


    /**
     * 批量插入企划属性
     * @param list
     * @param attributeList
     * @return
     */
    int saveMerchandisingList(List<ProductDimensionMerchandising> list, List<ProductAttribute> attributeList);

    /**
     * 根据编码查找维度
     *
     * @param code
     * @return
     */
    ProductDimensionMerchandising findByCode(String code);

    /**
     * 获取所有的维度编码
     *
     * @return
     */
    List<String> findAllMerchandisingCode();

    /**
     * 插入单挑的数据
     *
     * @param productDimensionMerchandising
     * @return
     */
    int saveMerchandising(ProductDimensionMerchandising productDimensionMerchandising);

    /**
     * 更新企划
     *
     * @param productDimensionMerchandising
     * @return
     */
    int updateMerchandising(ProductDimensionMerchandising productDimensionMerchandising);
}