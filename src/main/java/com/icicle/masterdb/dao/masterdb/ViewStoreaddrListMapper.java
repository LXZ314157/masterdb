package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewStoreaddrList;

import java.util.List;

public interface ViewStoreaddrListMapper extends MyMapper<ViewStoreaddrList> {

    List<ViewStoreaddrList> findAddrListByStoreNo(String storeNo);


    /**
     * 获取地址类型列表
     * @return
     */
    List<ViewStoreaddrList> findStoreAddrTypeList();

}