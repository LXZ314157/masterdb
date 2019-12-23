package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.StoreGroupAttribMapper;
import com.icicle.masterdb.model.StoreGroupAttrib;
import com.icicle.masterdb.pojo.vo.StoreGroupAttributeListVO;
import com.icicle.masterdb.service.StoreGroupAttribService;
import com.icicle.masterdb.util.ListUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.web.StoreController;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liurenhua
 */
@Service
public class StoreGroupAttribServiceImpl extends AbstractService<StoreGroupAttrib> implements StoreGroupAttribService {
    @Resource
    private StoreGroupAttribMapper storeGroupAttribMapper;

    @Override
    public int updateStatus(List<StoreGroupAttrib> list, int status) {
        return storeGroupAttribMapper.updateStatus(list, status);
    }

    @Override
    @LogAction(logDesc = "店铺属性关联")
    @Transactional(rollbackFor = Exception.class)
    public int attributeConnect(StoreGroupAttributeListVO storeGroupAttributeListVO) {
        int count = 0;
        Integer id = storeGroupAttributeListVO.getStoreAttribGroupId();
        List<StoreGroupAttrib> list = storeGroupAttributeListVO.getStoreGroupAttribList();
        List<StoreGroupAttrib> origList;

        try {
            Condition condition = new Condition(StoreGroupAttrib.class);
            condition.createCriteria().andEqualTo("storeAttribGroupId", id);
            origList = storeGroupAttribMapper.selectByCondition(condition);
            List<StoreGroupAttrib> insertList = ListUtil.getSameOrDiffList(origList, list, false);
            List<StoreGroupAttrib> updateList = ListUtil.getSameOrDiffList(list, origList, false);
            List<StoreGroupAttrib> validUpdateList = ListUtil.getSameOrDiffList(origList, list, true);

            if (!ListUtil.isBlank(validUpdateList)) {
                count += storeGroupAttribMapper.updateStatus(validUpdateList, 1);
            }

            if (!ListUtil.isBlank(updateList)) {
                count += storeGroupAttribMapper.updateStatus(updateList, 0);
            }

            if (!ListUtil.isBlank(insertList)) {
                count += storeGroupAttribMapper.insertList(insertList);
            }

        } catch (Exception e) {
            LogUtil.getLogger(StoreController.class).error(e.getMessage(), e);
            return 0;
        }
        return count;
    }

    @Override
    public List<StoreGroupAttrib> findAllValidGroupAttrib() {
        Condition condition = new Condition(StoreGroupAttrib.class);
        condition.createCriteria().andEqualTo("status", 1);
        return storeGroupAttribMapper.selectByCondition(condition);
    }


}