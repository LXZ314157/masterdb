package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.BusinessUnitMapper;
import com.icicle.masterdb.model.BusinessUnit;
import com.icicle.masterdb.service.BusinessUnitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class BusinessUnitServiceImpl extends AbstractService<BusinessUnit> implements BusinessUnitService {
    @Resource
    private BusinessUnitMapper businessUnitMapper;

    @Override
    @LogAction(logDesc = "新增事业部")
    public int saveBusinessUnit(BusinessUnit businessUnit) {
        return businessUnitMapper.saveBusinessUnit(businessUnit);
    }

    @Override
    @LogAction(logDesc = "更新事业部")
    public int updateBusinessUnit(BusinessUnit businessUnit) {
        return businessUnitMapper.updateByPrimaryKeySelective(businessUnit);
    }


}