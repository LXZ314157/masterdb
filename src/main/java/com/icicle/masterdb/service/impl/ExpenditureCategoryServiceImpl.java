package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.ExpenditureCategoryMapper;
import com.icicle.masterdb.model.Buyer;
import com.icicle.masterdb.model.ExpenditureCategory;
import com.icicle.masterdb.model.ViewExList;
import com.icicle.masterdb.model.ViewStaffList;
import com.icicle.masterdb.pojo.vo.ExpenditureCategoryVO;
import com.icicle.masterdb.pojo.vo.SyncPropertyVO;
import com.icicle.masterdb.service.ExpenditureCategoryService;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.service.ViewExListService;
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
import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-15 10:24:39.
*/
@Service
public class ExpenditureCategoryServiceImpl extends AbstractService<ExpenditureCategory> implements ExpenditureCategoryService {
    @Resource
    private ExpenditureCategoryMapper expenditureCategoryMapper;

    @Resource
    private ViewExListService viewExListService;


    @Override
    public DataTableRecord getExList(String sEcho, Integer pageIndex, Integer pageSize, String excategoryId, String lanCode) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        Condition condition = new Condition(ExpenditureCategory.class);
        Example.Criteria criteria = condition.createCriteria();
        Example.Criteria criteria2 = condition.createCriteria();
        if (!StringUtils.isBlank(lanCode)) {
            criteria.andEqualTo("lanCode", lanCode);
        }
        if (!StringUtils.isBlank(excategoryId)) {
            String words = StringUtil.gsFormat("%{0}%", excategoryId);
            condition.and(criteria2.andLike("excategoryId", words)
                    .orLike("excategoryName", words));
        }
        try {
            List<ViewExList> list = viewExListService.findByCondition(condition);
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
    public ExpenditureCategoryVO findExInfoByExcategoryId(String excategoryId, String lanCode) {
        return expenditureCategoryMapper.findExInfoByExcategoryId(excategoryId,lanCode);
    }

    @Override
    public List<SyncPropertyVO> getSyncExCategoryInfo(String [] excategoryIdList, String lanCode) {
        lanCode = StringUtils.isBlank(lanCode)?"zhs":lanCode;
        return expenditureCategoryMapper.getSyncExCategoryInfo(excategoryIdList,lanCode);
    }

    @Override
    public List<ExpenditureCategoryVO> findByQueryCondition(String exIdOrName, String lanCode) {
        return expenditureCategoryMapper.findByQueryCondition(exIdOrName,lanCode);
    }

    @Override
    public Workbook exportExExcel(List<ExpenditureCategoryVO> list) {
        return ExcelUtil.getExWorkbook(list);
    }
}