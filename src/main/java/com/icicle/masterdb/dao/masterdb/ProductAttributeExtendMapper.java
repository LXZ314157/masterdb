package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Fabric;
import com.icicle.masterdb.model.ProductAttributeExtend;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductAttributeExtendMapper extends MyMapper<ProductAttributeExtend> {
    /**
     * 批量插入产品属性横表
     *
     * @param productAttributeExtendList
     * @return
     */
    int insertAttributeExtendList(List<ProductAttributeExtend> productAttributeExtendList);

    /**
     * 批量更新产品属性横表
     *
     * @param productAttributeExtends
     * @return
     */
    int updateAttributePart(List<ProductAttributeExtend> productAttributeExtends);


    /**
     * 根据条件查询
     *
     * @param productCode
     * @return
     */
    ProductAttributeExtend searchByCondition(String productCode);

    /**
     * 删除未同步过的条码
     *
     * @param productCode
     * @return
     */
    int deleteUnSycn(String productCode);

    /**
     * 批量更新面料名称
     *
     * @param list
     * @return
     */
    int batchUpdateFabric(List<Fabric> list);
}