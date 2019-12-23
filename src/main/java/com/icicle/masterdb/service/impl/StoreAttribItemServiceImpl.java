package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.StoreAttribItemMapper;
import com.icicle.masterdb.model.StoreAttribItem;
import com.icicle.masterdb.service.StoreAttribItemService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author liurenhua
 */
@Service
public class StoreAttribItemServiceImpl extends AbstractService<StoreAttribItem> implements StoreAttribItemService {
    @Resource
    private StoreAttribItemMapper storeAttribItemMapper;

    @Override
    public int updateList(List<StoreAttribItem> list) {
        return storeAttribItemMapper.updateList(list);
    }

    @Override
    public List<StoreAttribItem> findByAttribDefId(Integer id) {
        Condition condition = new Condition(StoreAttribItem.class);
        condition.createCriteria().andEqualTo("storeAttribDefId", id);
        return storeAttribItemMapper.selectByCondition(condition);
    }
    @Override
    public int saveStoreAttribDef(StoreAttribItem storeAttribItem){
        return storeAttribItemMapper.saveStoreAttribDef(storeAttribItem);
    }
}