package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.BuyerTypeMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.BuyerType;
import com.icicle.masterdb.service.BuyerTypeService;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.icicle.masterdb.manager.SessionManager.getLoginName;

/**
 * @author CodeGeneratorUtil
 */
@Service
public class BuyerTypeServiceImpl extends AbstractService<BuyerType> implements BuyerTypeService {
    @Resource
    private BuyerTypeMapper buyerTypeMapper;
    @Resource
    private BuyerTypeService buyerTypeService;
    @Override
    @LogAction(logDesc ="更新代理商类别")
    public int typeUpdate(Integer buyerTypeId,String buyerTypeName,String buyerTypeDesc,Integer status){
        BuyerType content = buyerTypeMapper.selectByPrimaryKey(buyerTypeId);
        if (content != null) {
            content.setBuyerTypeName(buyerTypeName);
            content.setBuyerTypeDesc(buyerTypeDesc);
            if (status == 1) {
                content.setStatus(1);
            } else {
                content.setStatus(0);
            }
            content.setLastUpdateDate(DateUtil.now());
            content.setLastUpdatedBy(getLoginName());
            try {
               return  buyerTypeMapper.updateByPrimaryKey(content);

            } catch (Exception e) {
                LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                return 0;
            }
    }
    else{
        return -1;
    }
    }

    @Override
    @LogAction(logDesc = "新增代理商类别")
    public int typeInsert(String buyerTypeName,String buyerTypeDesc){
        if(StringUtils.isBlank(buyerTypeName)){
            return 0;
        }
        BuyerType data = new BuyerType();
        data.setBuyerTypeName(buyerTypeName);
        data.setBuyerTypeDesc(buyerTypeDesc);
        data.setStatus(1);
        data.setCreatedBy(SessionManager.getLoginName());
        data.setCreationDate(DateUtil.now());
        try {
            return buyerTypeService.save(data);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return 0;
        }
    }
}