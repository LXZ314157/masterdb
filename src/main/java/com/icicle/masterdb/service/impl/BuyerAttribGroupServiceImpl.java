package com.icicle.masterdb.service.impl;


import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.model.BuyerAttribGroup;
import com.icicle.masterdb.service.BuyerAttribGroupService;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.LogUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.icicle.masterdb.manager.SessionManager.getLoginName;

/**
 * @author CodeGeneratorUtil
 */
@Service
public class BuyerAttribGroupServiceImpl extends AbstractService<BuyerAttribGroup> implements BuyerAttribGroupService {

    @Resource
    BuyerAttribGroupService buyerAttribGroupService;

    @Override
    @LogAction(logDesc = "新增代理商属性组")
    public int insertBuyerAttributeGroup(String buyerAttribGroupName, String buyerAttribGroupCode) {
        BuyerAttribGroup bag = new BuyerAttribGroup();
        bag.setBuyerAttribGroupCode(buyerAttribGroupCode);
        bag.setBuyerAttribGroupName(buyerAttribGroupName);
        bag.setCreatedBy(getLoginName());
        bag.setCreationDate(DateUtil.now());
        bag.setStatus(1);
        try {
            return buyerAttribGroupService.save(bag);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    @LogAction(logDesc = "删除代理商属性组")
    public int deleteBuyerAttributeGroup(BuyerAttribGroup buyerAttribGroup) {
        if (buyerAttribGroup != null) {
            buyerAttribGroup.setStatus(0);
            try {
                return buyerAttribGroupService.update(buyerAttribGroup);
            } catch (Exception e) {
                LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                return 0;
            }
        } else {
            return 0;
        }
    }
}