package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ManageCenterMapper;
import com.icicle.masterdb.model.ManageCenter;
import com.icicle.masterdb.pojo.vo.SyncPropertyVO;
import com.icicle.masterdb.pojo.vo.SyncZoneVO;
import com.icicle.masterdb.service.ManageCenterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-02-25 15:27:12.
*/
@Service
public class ManageCenterServiceImpl extends AbstractService<ManageCenter> implements ManageCenterService {
    @Resource
    private ManageCenterMapper manageCenterMapper;

    @Override
    public int saveManageCenter(ManageCenter manageCenter) {
       return manageCenterMapper.saveManageCenter(manageCenter);
    }

    @Override
    public int updateManagerCenter(ManageCenter managerCenter) {
        return manageCenterMapper.updateByPrimaryKeySelective(managerCenter);
    }

    @Override
    public SyncPropertyVO getSyncBurgeonManageCenterById(Integer manageCenterId) {
        return manageCenterMapper.getSyncBurgeonManageCenterById(manageCenterId);
    }

    @Override
    public SyncPropertyVO getSyncBurgeonZoneById(Integer zoneId) {
        return manageCenterMapper.getSyncBurgeonZoneById(zoneId);
    }


    @Override
    public SyncZoneVO getSyncYxtMngById(Integer managerCenterId) {
        return manageCenterMapper.getSyncYxtMngById(managerCenterId);
    }

    @Override
    public List<SyncZoneVO>  getSyncYxtZoneById(List<Integer> zoneIdList) {
        return manageCenterMapper.getSyncYxtZoneById(zoneIdList);
    }

    @Override
    public SyncZoneVO getSyncYxtStoreByNo(Integer storeNo) {
        return manageCenterMapper.getSyncYxtStoreByNo(storeNo);
    }
}