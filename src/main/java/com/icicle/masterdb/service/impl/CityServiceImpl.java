package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.CityMapper;
import com.icicle.masterdb.model.City;
import com.icicle.masterdb.pojo.vo.SyncCityVO;
import com.icicle.masterdb.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
@Service
public class CityServiceImpl extends AbstractService<City> implements CityService {

    @Resource
    private CityMapper cityMapper;

    @Override
    public List<City> findAreaCityList() {
        return cityMapper.findAreaCityList();
    }

    @Override
    public List<City> findCountyList() {
        return cityMapper.findCountyList();
    }

    @Override
    public SyncCityVO getSyncCityById(int cityId) {
        return cityMapper.getSyncCityById(cityId);
    }
}