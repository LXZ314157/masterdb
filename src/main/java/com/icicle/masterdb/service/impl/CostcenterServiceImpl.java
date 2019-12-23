package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.CostcenterMapper;
import com.icicle.masterdb.model.Costcenter;
import com.icicle.masterdb.model.ViewCostcenterList;
import com.icicle.masterdb.pojo.vo.CostcenterVO;
import com.icicle.masterdb.service.CostcenterService;
import com.icicle.masterdb.service.ViewCostcenterListService;
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
* @version 2019-01-14 13:27:38.
*/
@Service
public class CostcenterServiceImpl extends AbstractService<Costcenter> implements CostcenterService {
    @Resource
    private CostcenterMapper costcenterMapper;

    @Resource
    private ViewCostcenterListService viewCostcenterListService;

    @Override
    public DataTableRecord getCostCenterList(String sEcho, Integer pageIndex, Integer pageSize, String costcenterId, String lanCode) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        Condition condition = new Condition(Costcenter.class);
        Example.Criteria criteria = condition.createCriteria();
        Example.Criteria criteria2 = condition.createCriteria();
        if (!StringUtils.isBlank(lanCode)) {
            criteria.andEqualTo("lanCode", lanCode);
        }
        if (!StringUtils.isBlank(costcenterId)) {
            String words = StringUtil.gsFormat("%{0}%", costcenterId);
            condition.and(criteria2.andLike("costcenterId", words)
                    .orLike("costCenterName", words));
        }
        try {
            List<ViewCostcenterList> list = viewCostcenterListService.findByCondition(condition);
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
    public List<Costcenter> checkCostcenterZhs(List<Costcenter> list) {
        List<Costcenter> costcenterslist = new ArrayList<>();
        if(!list.isEmpty()){
            for(Costcenter costCenter : list){
                if(costCenter.getLanCode().equals("zhs")){
                    costcenterslist.add(costCenter);
                }
            }
        }
        return costcenterslist;
    }

    @Override
    public List<CostcenterVO> getSyncCostcenterInfo(String[] costcenterIdList, String lanCode) {
        lanCode = StringUtils.isEmpty(lanCode)?"zhs":lanCode;
        return costcenterMapper.getSyncCostcenterInfo(costcenterIdList,lanCode);
    }

    @Override
    public Workbook exportCostcenterExcel(List<CostcenterVO> list) {
        return ExcelUtil.getCostcenterWorkbook(list);
    }

}