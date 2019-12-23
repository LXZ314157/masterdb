package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ManageCenter;
import com.icicle.masterdb.pojo.vo.SyncPropertyVO;
import com.icicle.masterdb.pojo.vo.SyncZoneVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManageCenterMapper extends MyMapper<ManageCenter> {

    int saveManageCenter(ManageCenter manageCenter);

    /**
     * 获取区域同步到柏俊的列表
     * @return
     */
    SyncPropertyVO getSyncZoneList(Integer zoneId);


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
    List<SyncZoneVO>  getSyncYxtZoneById(@Param("zoneIdList") List<Integer> zoneIdList);

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
     * 根据店铺编号查找店铺信心
     * @param storeNo
     * @return
     */
    SyncZoneVO getSyncYxtStoreByNo(Integer storeNo);


}


