package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.StaffMapper;
import com.icicle.masterdb.dao.masterdb.ViewStaffListMapper;
import com.icicle.masterdb.model.Staff;
import com.icicle.masterdb.model.ViewStaffList;
import com.icicle.masterdb.pojo.vo.StaffVO;
import com.icicle.masterdb.pojo.vo.StaffStoreVO;
import com.icicle.masterdb.pojo.vo.SyncStaffVO;
import com.icicle.masterdb.service.StaffService;
import com.icicle.masterdb.service.ViewStaffListService;
import com.icicle.masterdb.util.ExcelUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PageUtil;
import com.icicle.masterdb.util.StringUtil;
import com.icicle.masterdb.web.HrController;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author wangyuling
 */
@Service
public class StaffServiceImpl extends AbstractService<Staff> implements StaffService {

    @Resource
    private ViewStaffListService viewStaffListService;

    @Resource
    private ViewStaffListMapper viewStaffListMapper;

    @Resource
    private StaffMapper staffMapper;


    @Override
    public DataTableRecord getStaffList(String sEcho, Integer pageIndex, Integer pageSize, String staffNumOrName, String staffState) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        Condition condition = new Condition(Staff.class);
        Example.Criteria criteria = condition.createCriteria();
        Example.Criteria criteria2 = condition.createCriteria();
        if (!StringUtils.isBlank(staffState)) {
            criteria.andEqualTo("staffState", staffState);
        }
        if (!StringUtils.isBlank(staffNumOrName)) {
            String words = StringUtil.gsFormat("%{0}%", staffNumOrName);
            condition.and(criteria2.andLike("staffNum", words)
                    .orLike("staffNameLocal", words));
        }
        try {
            List<ViewStaffList> list = viewStaffListService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
            Integer total = (int) pageInfo.getTotal();
            dataTableRecord.setITotalRecords(total);
            dataTableRecord.setITotalDisplayRecords(total);
            dataTableRecord.setAaData(pageInfo.getList());
            return dataTableRecord;
        } catch (Exception ex) {
            LogUtil.getLogger(HrController.class).error(ex.getMessage());
            return null;
        }
    }

    @Override
    public DataTableRecord deptStaffList(String sEcho, Integer pageIndex, Integer pageSize, String departmentId) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<StaffVO> list = viewStaffListMapper.deptStaffList(departmentId);
            PageInfo pageInfo = new PageInfo(list);
            Integer total = (int) pageInfo.getTotal();
            dataTableRecord.setITotalRecords(total);
            dataTableRecord.setITotalDisplayRecords(total);
            dataTableRecord.setAaData(pageInfo.getList());
            return dataTableRecord;
        } catch (Exception ex) {
            LogUtil.getLogger(HrController.class).error(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Staff> getManagerList() {
        return staffMapper.getManagerList();
    }

    @Override
    public Workbook exportStaffExcel(List<StaffVO> list) {
        return ExcelUtil.getStaffWorkbook(list);
    }

    @Override
    public List<SyncStaffVO> getSyncStaffInfo() {
        return staffMapper.getSyncStaffInfo();
    }

    @Override
    public int updateSynStaff(String lastUpdateBy, List<String> staffNumList) {
        return staffMapper.updateSynStaff(lastUpdateBy,staffNumList);
    }

    @Override
    public List<StaffStoreVO> getStaffStoreList() {
        return staffMapper.getStaffStoreList();
    }
}

