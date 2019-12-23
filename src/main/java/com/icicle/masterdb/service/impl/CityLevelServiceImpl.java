package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.CityLevelMapper;
import com.icicle.masterdb.model.CityLevel;
import com.icicle.masterdb.service.CityLevelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CodeGeneratorUtil
 */
@Service
public class CityLevelServiceImpl extends AbstractService<CityLevel> implements CityLevelService {
    @Resource
    private CityLevelMapper cityLevelMapper;

    @Override
    @LogAction(logDesc = "新增城市级别")
    public int saveCityLevel(CityLevel cityLevel) {
        return cityLevelMapper.saveCityLevel(cityLevel);
    }

    @Override
    @LogAction(logDesc = "更新城市级别")
    public int updateCityLevel(CityLevel cityLevel) {
        return cityLevelMapper.updateByPrimaryKeySelective(cityLevel);
    }


}