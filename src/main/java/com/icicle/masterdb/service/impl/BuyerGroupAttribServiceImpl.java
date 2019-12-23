package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.BuyerGroupAttribMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.BuyerGroupAttrib;
import com.icicle.masterdb.pojo.vo.BuyerGroupAttributeListVO;
import com.icicle.masterdb.service.BuyerGroupAttribService;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.ListUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class BuyerGroupAttribServiceImpl extends AbstractService<BuyerGroupAttrib> implements BuyerGroupAttribService {
    @Resource
    private BuyerGroupAttribMapper buyerGroupAttribMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "代理商属性关联")
    public int connectAttribute(BuyerGroupAttributeListVO buyerGroupAttributeListVO) {
        int id = buyerGroupAttributeListVO.getBuyerAttribGroupId();
        List<BuyerGroupAttrib> list = buyerGroupAttributeListVO.getBuyerGroupAttribList();
        Condition condition = new Condition(BuyerGroupAttrib.class);
        condition.createCriteria().andEqualTo("buyerAttribGroupId", id);
        List<BuyerGroupAttrib> exeits = super.findByCondition(condition);
        List<BuyerGroupAttrib> updateList;
        List<BuyerGroupAttrib> insertList;
        List<BuyerGroupAttrib> stateList;
        insertList = ListUtil.getSameOrDiffList(exeits, list, false);
        updateList = ListUtil.getSameOrDiffList(exeits, list, true);
        stateList = ListUtil.getSameOrDiffList(list, exeits, false);
        if (!ListUtil.isBlank(updateList)) {
            buyerGroupAttribMapper.updateBuyerGroup(getForUpdate(updateList, true));
        }
        if (!ListUtil.isBlank(insertList)) {
            super.save(getForInsert(insertList, true));
        }
        if (!ListUtil.isBlank(stateList)) {
            buyerGroupAttribMapper.updateBuyerGroup(getForUpdate(stateList, false));
        }
        return 1;

    }

    private List<BuyerGroupAttrib> getForUpdate(List<BuyerGroupAttrib> list, boolean status) {
        for (BuyerGroupAttrib upitem : list) {
            upitem.setLastUpdateDate(DateUtil.now());
            upitem.setLastUpdatedBy(SessionManager.getLoginName());
            upitem.setStatus(status);
        }
        return list;
    }

    private List<BuyerGroupAttrib> getForInsert(List<BuyerGroupAttrib> list, boolean status) {
        for (BuyerGroupAttrib initem : list) {
            initem.setCreationDate(DateUtil.now());
            initem.setCreatedBy(SessionManager.getLoginName());
            initem.setStatus(status);
        }
        return list;
    }
}