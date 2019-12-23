package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.ViewStoreaddrListMapper;
import com.icicle.masterdb.model.ViewStoreaddrList;
import com.icicle.masterdb.service.ViewStoreaddrListService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-02-19 11:25:22.
*/
@Service
public class ViewStoreaddrListServiceImpl extends AbstractService<ViewStoreaddrList> implements ViewStoreaddrListService {
    @Resource
    private ViewStoreaddrListMapper viewStoreaddrListMapper;

    @Override
    public List<ViewStoreaddrList> findAddrListByStoreNo(String storeNo) {
        return viewStoreaddrListMapper.findAddrListByStoreNo(storeNo);
    }

    @Override
    public List<ViewStoreaddrList> findStoreAddrTypeList() {
        return viewStoreaddrListMapper.findStoreAddrTypeList();
    }
}