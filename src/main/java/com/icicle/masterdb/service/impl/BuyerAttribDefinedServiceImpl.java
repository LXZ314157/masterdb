package com.icicle.masterdb.service.impl;


import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.BuyerAttribDefinedMapper;
import com.icicle.masterdb.dao.masterdb.BuyerAttribItemMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.BuyerAttribDefined;
import com.icicle.masterdb.model.BuyerAttribItem;
import com.icicle.masterdb.pojo.vo.BuyerAttribDefItemVO;
import com.icicle.masterdb.pojo.vo.BuyerAttribDefVO;
import com.icicle.masterdb.pojo.vo.BuyerAttribDefinedVO;
import com.icicle.masterdb.service.BuyerAttribDefinedService;
import com.icicle.masterdb.service.BuyerAttribItemService;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.ListUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PojoConvertUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.icicle.masterdb.manager.SessionManager.getLoginName;

/**
 * @author CodeGeneratorUtil
 */
@Service
public class BuyerAttribDefinedServiceImpl extends AbstractService<BuyerAttribDefined> implements BuyerAttribDefinedService {
    @Resource
    private BuyerAttribDefinedMapper buyerAttribDefinedMapper;

    @Resource
    BuyerAttribItemService buyerAttribItemService;

    @Resource
    private BuyerAttribDefinedService buyerAttribDefinedService;

    @Resource
    private BuyerAttribItemMapper buyerAttribItemMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "代理商属性新增")
    public int attributeInsert(BuyerAttribDefinedVO buyerAttribDefinedVO) {
        BuyerAttribDefined buyerAttribDefined = PojoConvertUtil.convertPojo(buyerAttribDefinedVO, BuyerAttribDefined.class);
        buyerAttribDefined.setStatus(1);
        buyerAttribDefined.setCreatedBy(getLoginName());
        buyerAttribDefined.setCreationDate(DateUtil.now());
        buyerAttribDefined.setLastUpdatedBy(getLoginName());
        buyerAttribDefined.setLastUpdateDate(DateUtil.now());
        int count = buyerAttribDefinedMapper.saveBuyerDefine(buyerAttribDefined);
        if (count < 0) {
            return 0;
        }
        int id = buyerAttribDefined.getBuyerAttribDefId();
        List<BuyerAttribItem> buyerAttribItemlist = buyerAttribDefinedVO.getList();
        buyerSaveItem(id, buyerAttribItemlist);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "代理商属性更新")
    public int attributeupdate(BuyerAttribDefinedVO buyerAttribDefinedVO) {
        BuyerAttribDefined buyerAttribDefined = PojoConvertUtil.convertPojo(buyerAttribDefinedVO, BuyerAttribDefined.class);
        buyerAttribDefined.setLastUpdateDate(new Date());
        buyerAttribDefined.setLastUpdatedBy(getLoginName());
        int count = buyerAttribDefinedService.update(buyerAttribDefined);
        if (count > 0) {
            int id = buyerAttribDefined.getBuyerAttribDefId();
            boolean hasitem = buyerAttribDefined.getHasItem();
            if (!hasitem) {
                boolean result = dealItem(id);
                if (result) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                List<BuyerAttribItem> buyerAttribItemlist = buyerAttribDefinedVO.getList();
                int value = buyerSaveItem(id, buyerAttribItemlist);
                if (value > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } else {
            return 0;
        }

    }


    //无选项更改状态为无效
    @LogAction(logDesc = "更新选项无效")
    private boolean dealItem(Integer id) {
        Condition condition = new Condition(BuyerAttribItem.class);
        condition.createCriteria().andEqualTo("buyerAttribDefId", id);
        List<BuyerAttribItem> exeits = buyerAttribItemService.findByCondition(condition);
        if (ListUtil.isBlank(exeits)) {
            return true;
        }
        for (BuyerAttribItem item : exeits) {
            item.setStatus(0);
            item.setLastUpdateDate(DateUtil.now());
            item.setLastUpdatedBy(SessionManager.getLoginName());
        }
        if (!ListUtil.isBlank(exeits)) {
            int con = buyerAttribItemService.updateBuyerItem(exeits);
            if (con <= 0) {
                return false;
            }
        }
        return true;
    }

    //有选项处理数据
    @LogAction(logDesc = "新增或编辑选项")
    @Transactional(rollbackFor = Exception.class)
    public int buyerSaveItem(Integer id, List<BuyerAttribItem> buyerAttribItemlist) {
        if (ListUtil.isBlank(buyerAttribItemlist)) {
            return -1;
        }
        if (id == null) {
            return -1;
        }
        List<BuyerAttribItem> updateList = new ArrayList<>();
        List<BuyerAttribItem> insertList = new ArrayList<>();
        if (!ListUtil.isBlank(buyerAttribItemlist)) {
            for (BuyerAttribItem item : buyerAttribItemlist) {
                item.setBuyerAttribDefId(id);
                //新增
                if (item.getBuyerAttribItemId() == null) {
                    item.setCreatedBy(SessionManager.getLoginName());
                    item.setCreationDate(DateUtil.now());
                    insertList.add(item);
                } else {
                    item.setLastUpdatedBy(SessionManager.getLoginName());
                    item.setLastUpdateDate(DateUtil.now());
                    updateList.add(item);
                }
            }

            if (insertList != null && insertList.size() != 0) {
                for(BuyerAttribItem buyerAttribItem : insertList){
                    buyerAttribItem.setCreatedBy(SessionManager.getLoginName());
                    buyerAttribItem.setCreationDate(DateUtil.now());
                    int count = buyerAttribItemService.saveBuyerItem(buyerAttribItem);
                }

            }

            if (updateList != null && updateList.size() != 0) {
                buyerAttribItemService.updateBuyerItem(updateList);
            }
            return 1;

        } else {
            return 0;
        }
    }

    @Override
    public BuyerAttribDefItemVO loadDefine() {
        try {
            BuyerAttribDefItemVO buyerAttribDefItemVO = new BuyerAttribDefItemVO();
            Condition condition=new Condition(BuyerAttribDefined.class);
            condition.createCriteria().andEqualTo("status",1);
            condition.orderBy("defineOrder");
            List<BuyerAttribDefined> buyerAttribDefinedList = buyerAttribDefinedService.findByCondition(condition);
            List<BuyerAttribDefVO> buyerAttribDefinedVOList = PojoConvertUtil.convertPojoList(buyerAttribDefinedList, BuyerAttribDefVO.class);
            List<BuyerAttribItem> buyerAttribItemList = buyerAttribItemMapper.searchDropItem();
            buyerAttribDefItemVO.setBuyerAttribDefVOList(buyerAttribDefinedVOList);
            buyerAttribDefItemVO.setBuyerAttribItemList(buyerAttribItemList);
            return buyerAttribDefItemVO;
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }
    }
}