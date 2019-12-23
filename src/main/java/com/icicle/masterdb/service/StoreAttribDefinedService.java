package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.StoreAttribDefined;
import com.icicle.masterdb.pojo.vo.StoreAttribDefVO;

import java.util.List;

/**
 * @author liurenhua
 */
public interface StoreAttribDefinedService extends Service<StoreAttribDefined> {

    /**
     * 获取所有status为1的属性定义
     *
     * @return
     */
    List<StoreAttribDefined> findAllValidAttribDef();

    /**
     * 更新店铺属性定义
     *
     * @param storeAttribDefined
     * @return
     */
    int updateStoreAttributeDef(StoreAttribDefined storeAttribDefined);

    /**
     * 添加属性定义  属性定义选项
     *
     * @param storeAttribDefVO
     * @return
     */
    int addStoreAttributeDefVO(StoreAttribDefVO storeAttribDefVO);

    /**
     * 更新属性定义 属性定义选项
     *@param storeAttribDefVO
     * @return
     */
    int updateStoreAttributeDefVO(StoreAttribDefVO storeAttribDefVO);
}
