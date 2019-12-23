package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ViewStoreaddrList;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-02-19 11:25:22.
*/
public interface ViewStoreaddrListService extends Service<ViewStoreaddrList> {

    List<ViewStoreaddrList> findAddrListByStoreNo(String storeNo);


    /**
     * 获取地址类型列表
     * @return
     */
    List<ViewStoreaddrList> findStoreAddrTypeList();

}