package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.CityLevel;

/**
 * @author CodeGeneratorUtil
 */
public interface CityLevelService extends Service<CityLevel> {

    /**
     * 新增城市级别
     *
     * @param cityLevel 城市级别对象
     * @return
     */
    int saveCityLevel(CityLevel cityLevel);

    /**
     * 更新城市级别
     *
     * @param cityLevel
     * @return
     */
    int updateCityLevel(CityLevel cityLevel);
}