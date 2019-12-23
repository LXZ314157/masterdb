package com.icicle.masterdb.service.impl;


import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.BuyerAttribItemMapper;
import com.icicle.masterdb.model.BuyerAttribItem;
import com.icicle.masterdb.service.BuyerAttribItemService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
@Service
public class BuyerAttribItemServiceImpl extends AbstractService<BuyerAttribItem> implements BuyerAttribItemService {
    @Resource
    private BuyerAttribItemMapper buyerAttribItemMapper;

    @Override
    public int updateBuyerItem(List<BuyerAttribItem> buyerAttribItemList) {
        return buyerAttribItemMapper.updateBuyerItem(buyerAttribItemList);
    }

    @Override
    public List<BuyerAttribItem> searchItemByDefId(Integer buyerAttribDefId) {
        Condition condition = new Condition(BuyerAttribItem.class);
        condition.createCriteria().andEqualTo("buyerAttribDefId", buyerAttribDefId);
        List<BuyerAttribItem> buyerAttribItem = super.findByCondition(condition);
        if (buyerAttribItem==null||buyerAttribItem.size() == 0) {
            return null;
        } else {
            return buyerAttribItem;
        }
    }

    @Override
    public int saveBuyerItem(BuyerAttribItem buyerAttribItem) {
        return buyerAttribItemMapper.saveBuyerItem(buyerAttribItem);
    }
}