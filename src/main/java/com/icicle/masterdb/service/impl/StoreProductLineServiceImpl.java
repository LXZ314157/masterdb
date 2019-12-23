package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.StoreProductLineMapper;
import com.icicle.masterdb.model.StoreProductLine;
import com.icicle.masterdb.service.StoreProductLineService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-04-16 17:52:13.
*/
@Service
public class StoreProductLineServiceImpl extends AbstractService<StoreProductLine> implements StoreProductLineService {
    @Resource
    private StoreProductLineMapper storeProductLineMapper;

    @Override
    public List<StoreProductLine> findProductLineByStoreNo(String storeNo) {
        return storeProductLineMapper.findProductLineByStoreNo(storeNo);
    }
}