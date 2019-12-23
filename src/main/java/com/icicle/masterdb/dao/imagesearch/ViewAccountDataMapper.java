package com.icicle.masterdb.dao.imagesearch;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewAccountData;

import java.util.List;

public interface ViewAccountDataMapper extends MyMapper<ViewAccountData> {

    /**
     * 获取全部帐号信息
     * @return
     */
    List<ViewAccountData> findAll();
}