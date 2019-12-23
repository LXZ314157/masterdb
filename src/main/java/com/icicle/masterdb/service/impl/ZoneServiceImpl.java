package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ZoneMapper;
import com.icicle.masterdb.model.Zone;
import com.icicle.masterdb.pojo.vo.SyncProductLineVO;
import com.icicle.masterdb.pojo.vo.ZoneVO;
import com.icicle.masterdb.service.ZoneService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
@Service
public class ZoneServiceImpl extends AbstractService<Zone> implements ZoneService {
    @Resource
    private ZoneMapper zoneMapper;

    @Override
    @LogAction(logDesc = "添加区域")
    public int saveZone(Zone zone) {
        return zoneMapper.saveZone(zone);
    }


    @Override
    public int updateChildrenZone(String manageCenterId, String zoneId) {
        return zoneMapper.updateChildrenZone(manageCenterId,zoneId);
    }

    @Override
    public List<Zone> selectChildrenZone(String zoneId) {
        return zoneMapper.selectChildrenZone(zoneId);
    }

    @Override
    public int findStoreByZoneId(String zoneId) {
        return zoneMapper.findStoreByZoneId(zoneId);
    }

    @Override
    public List<Zone> findZone2List() {
        return zoneMapper.findZone2List();
    }

    @Override
    public int updateZone(Zone zone) {
        return zoneMapper.updateZone(zone);
    }

    @Override
    public Zone findZoneById(Integer id) {
        return zoneMapper.findZoneById(id);
    }

    @Override
    public int updateZoneStatus(Zone updateZone) {
        return zoneMapper.updateZoneStatus(updateZone);
    }

    @Override
    public List<Zone> findZone2ListByCondition() {
        return zoneMapper.findZone2ListByCondition();
    }

    @Override
    public List<Zone> findAllZone2List() {
        return zoneMapper.findAllZone2List();
    }

    @Override
    public List<SyncProductLineVO> getProductLineByStoreNo(Integer storeNo) {
        return zoneMapper.getProductLineByStoreNo(storeNo);
    }

    @Override
    public List<ZoneVO> findZoneListByManageCenterId(String manageCenterId) {
        return zoneMapper.findZoneListByManageCenterId(manageCenterId);
    }

}