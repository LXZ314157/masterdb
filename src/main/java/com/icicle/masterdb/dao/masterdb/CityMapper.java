package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.City;
import com.icicle.masterdb.pojo.vo.SyncCityVO;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface CityMapper extends MyMapper<City> {

    public List<City> findAreaCityList();

    public List<City> findCountyList();

    SyncCityVO getSyncCityById(int cityId);

}