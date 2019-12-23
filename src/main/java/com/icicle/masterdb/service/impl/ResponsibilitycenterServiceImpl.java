package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.ResponsibilitycenterMapper;
import com.icicle.masterdb.model.*;
import com.icicle.masterdb.pojo.vo.ExpenditureCategoryVO;
import com.icicle.masterdb.pojo.vo.RespCenterVO;
import com.icicle.masterdb.service.ResponsibilitycenterService;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.service.ViewCostcenterListService;
import com.icicle.masterdb.service.ViewRespListService;
import com.icicle.masterdb.service.ViewStaffListService;
import com.icicle.masterdb.util.ExcelUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PageUtil;
import com.icicle.masterdb.util.StringUtil;
import com.icicle.masterdb.web.BuyerController;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-14 10:19:33.
*/
@Service
public class ResponsibilitycenterServiceImpl extends AbstractService<Responsibilitycenter> implements ResponsibilitycenterService {
    @Resource
    private ResponsibilitycenterMapper responsibilitycenterMapper;

    @Resource
    private ViewRespListService viewRespListService;

    @Resource
    private ViewCostcenterListService viewCostcenterListService;

    @Override
    public DataTableRecord getRespList(String sEcho, Integer pageIndex, Integer pageSize, String respcenterId, String lanCode) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        Condition condition = new Condition(Responsibilitycenter.class);

        Example.Criteria criteria = condition.createCriteria();
        Example.Criteria criteria2 = condition.createCriteria();

        if (!StringUtils.isBlank(lanCode)) {
            criteria.andEqualTo("lanCode", lanCode);
        }
        if (!StringUtils.isBlank(respcenterId)) {
            String words = StringUtil.gsFormat("%{0}%", respcenterId);
            condition.and(criteria2.andLike("respcenterId", words)
                    .orLike("respcenterName", words));
        }
        try {
            List<ViewRespList> list = viewRespListService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
            Integer total = (int) pageInfo.getTotal();
            dataTableRecord.setITotalRecords(total);
            dataTableRecord.setITotalDisplayRecords(total);
            dataTableRecord.setAaData(pageInfo.getList());
            return dataTableRecord;
        } catch (Exception ex) {
            LogUtil.getLogger(BuyerController.class).error(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Responsibilitycenter> checkRespcenterZhs(List<Responsibilitycenter> responsibilitycenters) {
        List<Responsibilitycenter> list = new ArrayList<>();
        if(!responsibilitycenters.isEmpty()){
            for(Responsibilitycenter resp : responsibilitycenters){
                if(resp.getLanCode().equals("zhs")){
                    list.add(resp);
                }
            }
        }
        return list;

    }

    @Override
    public RespCenterVO findRespcenterInfoByRespcenterId(String respcenterId, String lanCode) {
        return responsibilitycenterMapper.findRespcenterInfoByRespcenterId(respcenterId,lanCode);
    }

    @Override
    public List<RespCenterVO> findByQueryCondition(String respcenterIdOrName, String lanCode) {
        return responsibilitycenterMapper.findByQueryCondition(respcenterIdOrName,lanCode);
    }

    @Override
    public Workbook exportRespcenterExcel(List<RespCenterVO> list) {
        return ExcelUtil.getRespcenterWorkbook(list);
    }


}