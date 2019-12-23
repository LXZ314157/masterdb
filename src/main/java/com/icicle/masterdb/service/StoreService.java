package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.*;
import com.icicle.masterdb.pojo.vo.StoreVO;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;

/**
 * @author liurenhua
 *         Created by CodeGeneratorUtil on 2017-11-02 13:34:51.
 */
public interface StoreService extends Service<Store> {
    /**
     * 新增店铺以及店铺属性
     *
     * @param storeVO
     * @return
     */
    StoreVO saveStore(StoreVO storeVO);

    /**
     * 更新店铺以及店铺属性
     *
     * @param storeVO
     * @return
     */
    int updateStore(StoreVO storeVO);


    /**
     * 添加店铺级别
     *
     * @param storeLevel
     * @return
     */
    int addStoreLevel(StoreLevel storeLevel);

    /**
     * 更新店铺级别
     *
     * @param storeLevel
     * @return
     */
    int updateStoreLevel(StoreLevel storeLevel);

    /**
     * 更新店铺类型
     *
     * @param storeType
     * @return
     */
    int updateStoreType(StoreType storeType);

    /**
     * 添加一个店铺类型
     *
     * @param storeType
     * @return
     */
    int addStoreType(StoreType storeType);


    /**
     * 获取所有的店铺级别
     *
     * @return
     */
    List<StoreLevel> findAllStoreLevel();

    /**
     * 获取所有有效的店铺级别
     *
     * @return
     */
    List<StoreLevel> findAllValidLevel();

    /**
     * 查询店铺对比编号集合
     * @param storeNo
     * @return
     */
    List<StoreComparison> findCompareStoreNoListByStoreNo(String storeNo);

    /**
     * 获取所有店铺id
     *
     * @return
     */
    List<StoreVO> findStoreList();

    /**
     * 获取所有的店铺类型
     *
     * @return
     */
    List<StoreType> findAllStoreType();

    /**
     * 获取所有status为1的店铺类型
     *
     * @return
     */
    List<StoreType> findAllValiedType();

    /**
     * 检查店铺类型名称是否重复
     *
     * @param storeType
     * @param update    表示是否是更新   更新和插入的检验逻辑是不一样的
     * @return
     */
    boolean typeNameExists(StoreType storeType, boolean update);

    /**
     * 根据店铺类型id查找店铺类型
     *
     * @param storeTypeId
     * @return
     */
    StoreType findTypeById(Integer storeTypeId);

    /**
     * 检查店铺级别名称是否重复
     *
     * @param storeLevel
     * @param update     表示是否是更新   更新和插入的检验逻辑是不一样的
     * @return
     */

    boolean levelNameExists(StoreLevel storeLevel, boolean update);


    /**
     * 根据店铺级别id查找店铺级别
     *
     * @param levelId
     * @return
     */
    StoreLevel findLevelById(Integer levelId);

    /**
     *
     * @param storeNo
     * @return
     */
    DataTableRecord getAddrListByStoreNo(String storeNo);

    /**
     *
     * 查询基本属性的值
     * @return
     */
    List<Map<String,String>> findBaseAttributeList();

    /**
     * 根据字段定义id查询code和value
     * @param storeAttribDefId
     * @return
     */
    List<Map<String,String>> findBaseAttributeListById(List<Map<String,String>>list,  String storeAttribDefId);


    /**
     *
     * @param storeId
     * @return
     */
    int verifysotreid(String storeId);

    /**
     *导出店铺list到excel
     * @param list
     */
    Workbook exportExcel(List<ViewStore> list,List<Map<String,String>> mapList);


    /**
     * 获取店铺属性列表
     * @return
     */
    List<Map<String,String>> getStoreAttribMapList();

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