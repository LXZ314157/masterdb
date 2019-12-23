package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Store;
import com.icicle.masterdb.model.StoreComparison;
import com.icicle.masterdb.pojo.vo.StoreVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author liurenhua
 */
public interface StoreMapper extends MyMapper<Store> {
    /**
     * 插入店铺信息
     *
     * @param store
     * @return
     */
    int saveStore(Store store);

    /**
     * 更新店铺信息
     *
     * @param store
     * @return
     */
    int updateStore(Store store);

    /**
     * 根据storeNo删除产品线
     * @param storeNo
     * @return
     */
    int deleteProductLineByStoreNo(String storeNo);

    /**
     * 新增商铺商品线
     * @param map
     * @return
     */
    int addStoreProductLine(Map<String,Object> map);

    /**
     * 获取所有店铺id集合
     * @return
     */
    List<StoreVO> findStoreList();

    /**
     * 查询基本属性的值列表
     * @return
     */
    List<Map<String,String>> findBaseAttributeList();

    /**
     * 查询店铺ID是否存在
     * @param storeId
     * @return
     */
    int verifysotreid(String storeId);

    /**
     * 更新店铺storeId
     * @param storeId
     * @param storeNo
     * @return
     */
    int updateStoreId(@Param("storeId") String storeId,@Param("storeNo") Integer storeNo);


    /**
     * 获取店铺属性列表
     * @return
     */
    List<Map<String,String>> getStoreAttribMapList();


    /**
     * 查询所有对比店铺
     * @return
     */
    List<StoreComparison> getStoreComparasionList();


    /**
     * 根据店铺编号查找店铺id
     * @return
     */
    String getStoreIdByStoreNo(Integer storeNo);


    /**
     * 根据店铺地址id获取店铺ID
     * @return
     */
    String getStoreIdByAddressId(Integer addressId);

}