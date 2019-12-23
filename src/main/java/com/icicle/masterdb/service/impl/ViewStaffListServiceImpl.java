package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ViewStaffListMapper;
import com.icicle.masterdb.model.ViewStaffList;
import com.icicle.masterdb.pojo.vo.StaffVO;
import com.icicle.masterdb.service.ViewStaffListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-09 21:26:32.
*/
@Service
public class ViewStaffListServiceImpl extends AbstractService<ViewStaffList> implements ViewStaffListService {
    @Resource
    private ViewStaffListMapper viewStaffListMapper;

    @Override
    public StaffVO findStaffInfoByStaffNum(String staffNum) {
        return viewStaffListMapper.findStaffInfoByStaffNum(staffNum);
    }

    @Override
    public List<StaffVO> findByQueryCondition(String staffNumOrName, String staffState) {
        return viewStaffListMapper.findByQueryCondition(staffNumOrName,staffState);
    }
}