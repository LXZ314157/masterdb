package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.StoreAttribGroup;

import java.util.List;

/**
 * @author liurenhua
 */
public interface StoreAttribGroupService extends Service<StoreAttribGroup> {

    /**
     * 查找所有status为1的属性组
     *
     * @return
     */
    List<StoreAttribGroup> findAllValidAttribGroup();

    /**
     * 更新属性组
     *
     * @param storeAttribGroup
     * @return
     */
    int updateAttribGroup(StoreAttribGroup storeAttribGroup);

    /**
     * 添加属性组
     *
     * @param storeAttribGroup
     * @return
     */
    int saveAttribGroup(StoreAttribGroup storeAttribGroup);

    /**
     * 验证属性组编码是否已经存在
     *
     * @param code
     * @param name
     * @return
     */
    boolean attribCodeExists(String code,String name);
}