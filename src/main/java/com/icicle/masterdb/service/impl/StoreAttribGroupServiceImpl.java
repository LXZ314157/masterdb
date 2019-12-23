package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.StoreAttribGroupMapper;
import com.icicle.masterdb.model.StoreAttribGroup;
import com.icicle.masterdb.service.StoreAttribGroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liurenhua
 */
@Service
public class StoreAttribGroupServiceImpl extends AbstractService<StoreAttribGroup> implements StoreAttribGroupService {
    @Resource
    private StoreAttribGroupMapper storeAttribGroupMapper;

    @Override
    public List<StoreAttribGroup> findAllValidAttribGroup() {
        Condition condition = new Condition(StoreAttribGroup.class);
        condition.createCriteria().andEqualTo("status", 1);
        return storeAttribGroupMapper.selectByCondition(condition);
    }

    @Override
    @LogAction(logDesc = "更新属性组")
    public int updateAttribGroup(StoreAttribGroup storeAttribGroup) {
        if (storeAttribGroup == null || storeAttribGroup.getStoreAttribGroupId() == null) {
            return 0;
        }
        return storeAttribGroupMapper.updateByPrimaryKeySelective(storeAttribGroup);
    }

    @Override
    @LogAction(logDesc = "添加属性组")
    public int saveAttribGroup(StoreAttribGroup storeAttribGroup) {
        if (storeAttribGroup == null) {
            return 0;
        }
        return super.save(storeAttribGroup);
    }

    @Override
    public boolean attribCodeExists(String code, String name) {
        if (StringUtils.isBlank(code) || StringUtils.isBlank(name)) {
            return true;
        }
        Condition condition = new Condition(StoreAttribGroup.class);
        condition.createCriteria().andEqualTo("storeAttribGroupCode", code).orEqualTo("storeAttribGroupName",name);
        int count = storeAttribGroupMapper.selectCountByCondition(condition);
        return count > 0;
    }
}