package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Zone;
import com.icicle.masterdb.pojo.vo.SyncProductLineVO;
import com.icicle.masterdb.pojo.vo.ZoneVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ZoneMapper extends MyMapper<Zone> {

    /**
     * 新增区域
     * @param zone
     * @return
     */
    int saveZone(Zone zone);


    /**
     * 修改区域级联的分区
     * @param manageCenterId
     * @param zoneId
     * @return
     */
    int updateChildrenZone(@Param("manageCenterId") String manageCenterId, @Param("zoneId") String zoneId);

    /**
     * 查询区域的分区
     * @param zoneId
     * @return
     */
    List<Zone> selectChildrenZone(@Param("zoneId") String zoneId);

    /**
     * 根据zoneId查找店铺
     * @param zoneId
     * @return
     */
    int findStoreByZoneId(@Param("zoneId") String zoneId);


    /**
     * 查询zone2表
     * @return
     */
    List<Zone> findZone2List();


    /**
     * 更新区域
     * @param zone
     * @return
     */
    int updateZone(Zone zone);


    /**
     * 查找zone
     * @param id
     * @return
     */
    Zone findZoneById(Integer id);

    /**
     * 更新区域状态
     * @param updateZone
     * @return
     */
    int updateZoneStatus(Zone updateZone);


    /**
     * 根据条件查询zone2List
     * @return
     */
    List<Zone> findZone2ListByCondition();



    /**
     * 查找区域列表
     * @return
     */
    List<Zone> findAllZone2List();

    /**
     * 根据store_no获取产品线
     * @return
     */
    List<SyncProductLineVO> getProductLineByStoreNo(Integer storeNo);

    /**
     * 根据managecenterId获取区域列表
     * @return
     */
    List<ZoneVO> findZoneListByManageCenterId(String manageCenterId);

}