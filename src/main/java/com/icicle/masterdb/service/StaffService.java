package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.Staff;
import com.icicle.masterdb.pojo.vo.StaffStoreVO;
import com.icicle.masterdb.pojo.vo.StaffVO;
import com.icicle.masterdb.pojo.vo.SyncStaffVO;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-09 21:24:40.
*/
public interface StaffService extends Service<Staff> {

    /**
     *
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param staffNumOrName
     * @param staffState
     * @return
     */
    DataTableRecord getStaffList(String sEcho, Integer pageIndex, Integer pageSize, String staffNumOrName, String staffState);


    DataTableRecord deptStaffList(String sEcho, Integer pageIndex, Integer pageSize,String departmentId);


    /**
     * 获取员工部分信息
     * @return
     */
    List<Staff> getManagerList();

    /**
     * 导出员工信息excel
     * @param list
     * @return
     */
    Workbook exportStaffExcel(List<StaffVO> list);

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
    int updateSynStaff(String lastUpdateBy,List<String> staffNumList);


    /**
     * 获取店员（员工）列表
     * @return
     */
    List<StaffStoreVO> getStaffStoreList();

}