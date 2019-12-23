package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.StoreAttributeMapper;
import com.icicle.masterdb.dao.masterdb.ViewStoreProductLineMapper;
import com.icicle.masterdb.model.ProductLine;
import com.icicle.masterdb.model.StoreAttribute;
import com.icicle.masterdb.model.ViewStoreProductLine;
import com.icicle.masterdb.service.StoreAttributeService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liurenhua
 */
@Service
public class StoreAttributeServiceImpl extends AbstractService<StoreAttribute> implements StoreAttributeService {

    @Resource
    private StoreAttributeMapper storeAttributeMapper;

    @Resource
    private ViewStoreProductLineMapper viewStoreProductLineMapper;

    @Override
    public List<StoreAttribute> findByStoreNo(Integer storeNo) {
        Condition condition = new Condition(StoreAttribute.class);
        condition.createCriteria().andEqualTo("storeNo", storeNo);
        return storeAttributeMapper.selectByCondition(condition);
    }

    @Override
    public List<ViewStoreProductLine> findViewProductLineByStoreNo(Integer storeNo) {
        Condition condition = new Condition(ViewStoreProductLine.class);
        condition.createCriteria().andEqualTo("storeNo", storeNo);
        return viewStoreProductLineMapper.selectByCondition(condition);
    }


}