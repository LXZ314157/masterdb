package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.StoreComparison;

import java.util.List;
import java.util.Map;

public interface StoreComparisonMapper extends MyMapper<StoreComparison> {

    /**
     * 根据店铺编号查找对比店铺编号
     * @param storeNo
     * @return
     */
    List<StoreComparison> findCompareStoreNoListByStoreNo (String storeNo);

    /**
     * 根据店铺编号删除对比店铺编号
     * @param storeNo
     */
    void deleteCompareStoreNoByStoreNo(String storeNo);

    /**
     * 添加对比店铺编号
     * @param map
     * @return
     */
    int addCompareStoreNoByStoreNo(Map<String,Object> map);

}