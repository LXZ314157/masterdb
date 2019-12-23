package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.CityLevel;

/**
 * @author CodeGeneratorUtil
 */
public interface CityLevelMapper extends MyMapper<CityLevel> {

    /**
     * 新增城市级别
     * @param cityLevel
     * @return
     */
    int saveCityLevel(CityLevel cityLevel);
}