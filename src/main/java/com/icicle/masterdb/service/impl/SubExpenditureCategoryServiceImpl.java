package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.SubExpenditureCategoryMapper;
import com.icicle.masterdb.model.Buyer;
import com.icicle.masterdb.model.SubExpenditureCategory;
import com.icicle.masterdb.model.ViewExList;
import com.icicle.masterdb.model.ViewSubExList;
import com.icicle.masterdb.pojo.vo.SubExpenditureCategoryVO;
import com.icicle.masterdb.pojo.vo.SyncPropertyVO;
import com.icicle.masterdb.service.SubExpenditureCategoryService;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.service.ViewExListService;
import com.icicle.masterdb.service.ViewSubExListService;
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
* @version 2019-01-15 11:22:13.
*/
@Service
public class SubExpenditureCategoryServiceImpl extends AbstractService<SubExpenditureCategory> implements SubExpenditureCategoryService {
    @Resource
    private SubExpenditureCategoryMapper subExpeditureCategoryMapper;

    @Resource
    private ViewSubExListService viewSubExListService;

    @Override
    public DataTableRecord getSubExList(String sEcho, Integer pageIndex, Integer pageSize, String subExcategoryId, String lanCode) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        Condition condition = new Condition(SubExpenditureCategory.class);
        Example.Criteria criteria = condition.createCriteria();
        Example.Criteria criteria2 = condition.createCriteria();
        if (!StringUtils.isBlank(lanCode)) {
            criteria.andEqualTo("lanCode", lanCode);
        }
        if (!StringUtils.isBlank(subExcategoryId)) {
            String words = StringUtil.gsFormat("%{0}%", subExcategoryId);
            condition.and(criteria2.andLike("subExcategoryId", words)
                    .orLike("subExcategoryName", words));
        }
        try {
            List<ViewSubExList> list = viewSubExListService.findByCondition(condition);
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
    public SubExpenditureCategoryVO findsubExInfoBySubExcategoryId(String subExcategoryId, String lanCode) {
        return subExpeditureCategoryMapper.findsubExInfoBySubExcategoryId(subExcategoryId,lanCode);
    }

    @Override
    public List<SyncPropertyVO> getSyncSubExCategoryInfo(String[] subExcategoryIdList, String lanCode) {
        lanCode = StringUtils.isEmpty(lanCode)?"zhs":lanCode;
        return subExpeditureCategoryMapper.getSyncSubExCategoryInfo(subExcategoryIdList,lanCode);
    }

    @Override
    public List<SubExpenditureCategoryVO> findByQueryCondition(String subExIdOrName, String lanCode) {
        return subExpeditureCategoryMapper.findByQueryCondition(subExIdOrName,lanCode);
    }

    @Override
    public Workbook exportExExcel(List<SubExpenditureCategoryVO> list) {
        return ExcelUtil.getSubExWorkbook(list);
    }
}