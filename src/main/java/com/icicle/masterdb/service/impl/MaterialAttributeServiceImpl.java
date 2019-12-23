package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.MaterialAttributeMapper;
import com.icicle.masterdb.dao.masterdb.MaterialMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.MaterialAttribute;
import com.icicle.masterdb.service.MaterialAttributeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author liurenhua
 */
@Service
public class MaterialAttributeServiceImpl extends AbstractService<MaterialAttribute> implements MaterialAttributeService {
    @Resource
    private MaterialAttributeMapper materialAttributeMapper;
    @Resource
    private MaterialMapper materialMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "更新原材料属性")
    public int updateMaterialAttribute(List<MaterialAttribute> list, String materialCode) {
        int count = 0;
        List<MaterialAttribute> updateList = new ArrayList<>();
        List<MaterialAttribute> insertList = new ArrayList<>();

        for (MaterialAttribute materialAttribute : list) {
            materialAttribute.setMaterialCode(materialCode);
            if (materialAttribute.getId() == null) {
                insertList.add(materialAttribute);
            } else {
                updateList.add(materialAttribute);
            }
        }

        if (insertList != null && insertList.size() != 0) {
            count += materialAttributeMapper.insertList(insertList);
        }
        if (updateList != null && updateList.size() != 0) {
            count += materialAttributeMapper.updateAttributeList(updateList);
        }
        if (count > 0) {
            materialMapper.updateStatus(materialCode, SessionManager.getLoginName());
        }

        return count;
    }

}