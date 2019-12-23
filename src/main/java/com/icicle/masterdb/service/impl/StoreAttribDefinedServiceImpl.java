package com.icicle.masterdb.service.impl;


import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.StoreAttribDefinedMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.Buyer;
import com.icicle.masterdb.model.StoreAttribDefined;
import com.icicle.masterdb.model.StoreAttribItem;
import com.icicle.masterdb.pojo.vo.StoreAttribDefVO;
import com.icicle.masterdb.pojo.vo.StoreAttributeItemVO;
import com.icicle.masterdb.service.StoreAttribDefinedService;
import com.icicle.masterdb.service.StoreAttribItemService;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.ListUtil;
import com.icicle.masterdb.util.PojoConvertUtil;
import com.icicle.masterdb.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liurenhua
 */
@Service
public class StoreAttribDefinedServiceImpl extends AbstractService<StoreAttribDefined> implements StoreAttribDefinedService {
    @Resource
    private StoreAttribDefinedMapper storeAttribDefinedMapper;
    @Resource
    private StoreAttribItemService storeAttribItemService;

    @Override
    public List<StoreAttribDefined> findAllValidAttribDef() {
        Condition con = new Condition(StoreAttribDefined.class);
        Example.Criteria criteria = con.createCriteria();
        con.and(criteria.andEqualTo("status", 1)
                .andEqualTo("storeAttribNature",1));
        con.orderBy("defineOrder").asc();
        List<StoreAttribDefined> storeAttribDefinedList = storeAttribDefinedMapper.selectByCondition(con);
        return storeAttribDefinedList;
    }

    @Override
    public int updateStoreAttributeDef(StoreAttribDefined storeAttribDefined) {
        if (storeAttribDefined == null) {
            return 0;
        }
        return storeAttribDefinedMapper.updateStoreAttributeDef(storeAttribDefined);
    }

    @Override
    @LogAction(logDesc = "新增店铺属性定义及选项")
    @Transactional(rollbackFor = Exception.class)
    public int addStoreAttributeDefVO(StoreAttribDefVO storeAttribDefVO) {
        int count = 0;
        StoreAttribDefined storeAttribDefined;
        storeAttribDefined = PojoConvertUtil.convertPojo(storeAttribDefVO, StoreAttribDefined.class);
        List<StoreAttributeItemVO> itemList = storeAttribDefVO.getItemList();

        storeAttribDefined.setCreatedBy(SessionManager.getLoginName());
        storeAttribDefined.setCreationDate(new Date());
        count += storeAttribDefinedMapper.insertStoreAttributeDef(storeAttribDefined);
        List<StoreAttribItem> storeAttributeList = PojoConvertUtil.convertPojoList(itemList, StoreAttribItem.class);
        for (StoreAttribItem item : storeAttributeList) {
            item.setCreatedBy(SessionManager.getLoginName());
            item.setCreationDate(DateUtil.now());
            item.setStoreAttribDefId(storeAttribDefined.getStoreAttribDefId());
            item.setStatus(1);
            if (itemList != null && itemList.size() > 0) {
                count += storeAttribItemService.saveStoreAttribDef(item);
            }
        }


        return count;
    }

    @Override
    @LogAction(logDesc = "更新店铺属性定义及选项")
    @Transactional(rollbackFor = Exception.class)
    public int updateStoreAttributeDefVO(StoreAttribDefVO storeAttribDefVO) {
        int count = 0;
        List<StoreAttributeItemVO> itemList = storeAttribDefVO.getItemList();
        String loginName = SessionManager.getLoginName();
        List<StoreAttribItem> storeAttribItemList = PojoConvertUtil.convertPojoList(itemList, StoreAttribItem.class);
        StoreAttribDefined sad = PojoConvertUtil.convertPojo(storeAttribDefVO, StoreAttribDefined.class);
        sad.setLastUpdateDate(new Date());
        sad.setLastUpdatedBy(SessionManager.getLoginName());
        count += updateStoreAttributeDef(sad);

        if (storeAttribDefVO.getHasItem() && !ListUtil.isBlank(itemList)) {
            List<StoreAttribItem> insertList = new ArrayList<>();
            List<StoreAttribItem> updateList = new ArrayList<>();

            for (StoreAttribItem storeAttribItem : storeAttribItemList) {
                storeAttribItem.setStoreAttribDefId(sad.getStoreAttribDefId());
                if (storeAttribItem.getStoreAttribItemId() == null || storeAttribItem.getStoreAttribItemId() == 0) {
                    storeAttribItem.setCreationDate(DateUtil.now());
                    storeAttribItem.setCreatedBy(loginName);
                    insertList.add(storeAttribItem);
                } else {
                    storeAttribItem.setLastUpdateDate(DateUtil.now());
                    storeAttribItem.setLastUpdatedBy(loginName);
                    updateList.add(storeAttribItem);
                }
            }
            if (updateList.size() != 0) {
                storeAttribItemService.updateList(updateList);
            }

            if (insertList.size() != 0) {
                for (StoreAttribItem storeAttribItem :insertList){
                    storeAttribItemService.saveStoreAttribDef(storeAttribItem);
                }
            }
        }
        return count;
    }
}