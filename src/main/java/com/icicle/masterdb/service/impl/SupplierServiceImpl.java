package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.SupplierMapper;
import com.icicle.masterdb.model.Supplier;
import com.icicle.masterdb.model.ViewSupplierList;
import com.icicle.masterdb.pojo.vo.SupplierVO;
import com.icicle.masterdb.service.SupplierService;
import com.icicle.masterdb.service.ViewSupplierListService;
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
* @author  CodeGeneratorUtil
* @version 2019-10-09 11:29:36.
*/
@Service
public class SupplierServiceImpl extends AbstractService<Supplier> implements SupplierService {

    @Resource
    private ViewSupplierListService viewSupplierListService;

    @Resource
    private SupplierMapper supplierMapper;

    @Override
    public DataTableRecord getSupplierList(String sEcho, Integer pageIndex, Integer pageSize,String supplierSourceCode,String supplierCodeOrName, String supplierStatus) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        Condition condition = new Condition(ViewSupplierList.class);
        Example.Criteria criteria = condition.createCriteria();
        Example.Criteria criteria2 = condition.createCriteria();
        Example.Criteria criteria3 = condition.createCriteria();
        if (!StringUtils.isBlank(supplierStatus)) {
            criteria.andEqualTo("supplierDataStatus", supplierStatus);
        }
        if (!StringUtils.isBlank(supplierCodeOrName)) {
            String words = StringUtil.gsFormat("%{0}%", supplierCodeOrName);
            condition.and(criteria2.andLike("supplierCode", words)
                    .orLike("supplierName", words));
        }
        if (!StringUtils.isBlank(supplierSourceCode)) {
            String words = StringUtil.gsFormat("%{0}%", supplierSourceCode);
            condition.and(criteria3.andLike("supplierSourceCode", words));
        }

        try {
            List<ViewSupplierList> list = viewSupplierListService.findByCondition(condition);
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
    public Workbook exportSupplierExcel(List<SupplierVO> list) {
        return ExcelUtil.getSupplierWorkbook(list);
    }

    @Override
    public List<Supplier> getBurgeonSupplier(List<String> supplierCodeList) {
        return supplierMapper.getBurgeonSupplier(supplierCodeList);
    }

}