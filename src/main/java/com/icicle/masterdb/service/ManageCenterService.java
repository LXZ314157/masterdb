package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ManageCenter;
import com.icicle.masterdb.pojo.vo.SyncPropertyVO;
import com.icicle.masterdb.pojo.vo.SyncZoneVO;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-02-25 15:27:12.
*/
public interface ManageCenterService extends Service<ManageCenter> {

    /**
     *
     * @param manageCenter
     * @return
     */
    int saveManageCenter(ManageCenter manageCenter);


    /**
     *
     * @param managerCenter
     * @return
     */
    int updateManagerCenter(ManageCenter managerCenter);

    /**
     * 获取现场管理中心同步到柏俊的列表
     * @return
     */
    SyncPropertyVO getSyncBurgeonManageCenterById(Integer manageCenterId);

    /**
     * 获取区域同步到柏俊的列表
     * @return
     */
    SyncPropertyVO getSyncBurgeonZoneById(Integer zoneId);


    /**
     * 根据管理中心编号查找区域信息
     * @param managerCenterId
     * @return
     */
    SyncZoneVO getSyncYxtMngById(Integer managerCenterId);

    /**
     * 根据区域编号查找区域信息
     * @param zoneIdList
     * @return
     */
    List<SyncZoneVO>  getSyncYxtZoneById(List<Integer> zoneIdList);

    /**
     * 根据店铺编号查找店铺信心
     * @param storeNo
     * @return
     */
    SyncZoneVO getSyncYxtStoreByNo(Integer storeNo);

}