package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.StoreAttribItem;

import java.util.List;

/**
 * @author liurenhua
 */
public interface StoreAttribItemService extends Service<StoreAttribItem> {

    /**
     * 批量更新店铺属性下拉列表
     *
     * @param list
     * @return
     */
    int updateList(List<StoreAttribItem> list);


    /**
     * 根据属性定义id查找所有status为1的下拉列表
     *
     * @param id
     * @return
     */
    List<StoreAttribItem> findByAttribDefId(Integer id);


    /**
     * 
     * @param storeAttribItem
     * @return
     */
    int saveStoreAttribDef(StoreAttribItem storeAttribItem);

}