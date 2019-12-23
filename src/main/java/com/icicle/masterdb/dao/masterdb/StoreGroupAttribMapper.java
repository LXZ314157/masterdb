package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.StoreGroupAttrib;

import java.util.List;
/**
 * @author liurenhua
 */
public interface StoreGroupAttribMapper extends MyMapper<StoreGroupAttrib> {

    /**
     * 软性删除
     *
     * @param list
     * @param status
     * @return
     */
    int updateStatus(List<StoreGroupAttrib> list,int status);
}