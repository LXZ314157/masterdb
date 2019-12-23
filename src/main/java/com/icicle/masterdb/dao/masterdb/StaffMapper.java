package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Staff;
import com.icicle.masterdb.pojo.vo.StaffStoreVO;
import com.icicle.masterdb.pojo.vo.SyncStaffVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface StaffMapper extends MyMapper<Staff> {

    /**
     * 获取员工部分信息
     *
     */
    List<Staff> getManagerList();


    /**
     * 获取要同步的员工列表
     * @return
     */
    List<SyncStaffVO> getSyncStaffInfo();

    /**
     * 更新人员同步标识
     * @param lastUpdateBy
     * @param staffNumList
     * @return
     */
    int updateSynStaff(@Param("lastUpdateBy") String lastUpdateBy,@Param("staffNumList") List<String> staffNumList);


    /**
     * 获取店员（员工）列表
     * @return
     */
    List<StaffStoreVO> getStaffStoreList();

}