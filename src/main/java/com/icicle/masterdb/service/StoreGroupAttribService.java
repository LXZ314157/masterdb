package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.StoreGroupAttrib;
import com.icicle.masterdb.pojo.vo.StoreGroupAttributeListVO;

import java.util.List;

/**
 * @author liurenhua
 */
public interface StoreGroupAttribService extends Service<StoreGroupAttrib> {

    /**
     * 更新状态
     *
     * @param list
     * @param status
     * @return
     */
    int updateStatus(List<StoreGroupAttrib> list, int status);


    /**
     * 将属性组与属性关联起来
     *
     * @param storeGroupAttributeListVO
     * @return
     */
    int attributeConnect(StoreGroupAttributeListVO storeGroupAttributeListVO);

    /**
     * 查找所有status为1的属性关联
     *
     * @return
     */
    List<StoreGroupAttrib> findAllValidGroupAttrib();
}