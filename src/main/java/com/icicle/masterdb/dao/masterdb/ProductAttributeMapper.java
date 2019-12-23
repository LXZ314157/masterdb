package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ProductAttribute;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductAttributeMapper extends MyMapper<ProductAttribute> {

    /**
     * 根据产品id查找属性
     *
     * @param productCode
     * @return
     */
    List<ProductAttribute> findAttributeByCode(String productCode);

    /**
     * 批量更细属性
     *
     * @param list
     * @return
     */
    int updateAttributeList(List<ProductAttribute> list);

    /**
     * 获取企划表中已经存在的产品编码
     *
     * @param list
     * @return
     */
    List<String> findExistCodeList(List<String> list);
}