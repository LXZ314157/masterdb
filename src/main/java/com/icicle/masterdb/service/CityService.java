package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.City;
import com.icicle.masterdb.pojo.vo.SyncCityVO;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface CityService extends Service<City> {

    public List<City> findAreaCityList();

    public List<City> findCountyList();

    SyncCityVO getSyncCityById(int cityId);


}