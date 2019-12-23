package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewStaffList;
import com.icicle.masterdb.pojo.vo.StaffVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewStaffListMapper extends MyMapper<ViewStaffList> {

    /**
     * 获取所有代理商的id，no，名称
     *
     * @return
     */
    List<ViewStaffList> findViewStaffVO();

    List<StaffVO> deptStaffList(String deprtmentId);

    StaffVO findStaffInfoByStaffNum(String staffNum);


    /**
     * 根据条件查询staff列表
     * @param staffNumOrName
     * @param staffState
     * @return
     */
    List<StaffVO> findByQueryCondition(@Param("staffNumOrName") String staffNumOrName,@Param("staffState") String staffState);


}