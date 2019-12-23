package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ViewStaffList;
import com.icicle.masterdb.pojo.vo.StaffVO;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-09 21:26:32.
*/
public interface ViewStaffListService extends Service<ViewStaffList> {

    StaffVO findStaffInfoByStaffNum(String staffNum);

    /**
     * 根据条件查询staff列表
     * @param staffNumOrName
     * @param staffState
     * @return
     */
    List<StaffVO> findByQueryCondition(String staffNumOrName,String staffState);
}