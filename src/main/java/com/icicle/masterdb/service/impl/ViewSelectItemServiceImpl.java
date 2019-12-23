package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.ViewSelectItemMapper;
import com.icicle.masterdb.dao.masterdb.ViewSelectSyncItemMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ViewSelectItem;
import com.icicle.masterdb.model.ViewSelectSyncItem;
import com.icicle.masterdb.service.ViewSelectItemService;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PageUtil;
import com.icicle.masterdb.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author liurenhua
 */
@Service
public class ViewSelectItemServiceImpl extends AbstractService<ViewSelectItem> implements ViewSelectItemService {
    @Resource
    private ViewSelectItemMapper viewSelectItemMapper;

    @Resource
    private ViewSelectSyncItemMapper viewSelectSyncItemMapper;

    @Override
    public List<ViewSelectItem> findByTableName(String tableName) {
        return viewSelectItemMapper.findByTableName(tableName);
    }

    @Override
    public List<ViewSelectItem> findAllItem(List<String> list) {
        return viewSelectItemMapper.findAllItem(list);
    }

    @Override
    public List<String> selectTableName() {
        return viewSelectItemMapper.selectTableName();
    }

    @Override
    public int searchForUpdate(ViewSelectItem viewSelectItem, String tablecatalog) {
        return viewSelectItemMapper.searchForUpdate(viewSelectItem, tablecatalog);
    }

    @Override
    public int searchForInsert(ViewSelectItem viewSelectItem, String tablecatalog) {
        return viewSelectItemMapper.searchForInsert(viewSelectItem, tablecatalog);
    }

    @Override
    public DataTableRecord listItem(String sEcho, Integer pageIndex, Integer pageSize, String itemCategory,String itemSelect, Integer sortCol, String sortDir) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ViewSelectSyncItem> viewSelectSyncItemList = null;
        Condition condition = new Condition(ViewSelectSyncItem.class);
        Example.Criteria criteria = condition.createCriteria();
        Example.Criteria criteria2 = condition.createCriteria();
        criteria.andEqualTo("status", 1);
        if (!StringUtils.isBlank(itemCategory)) {
            String words = StringUtil.gsFormat("%{0}%", itemCategory);
            condition.and(condition.createCriteria().andLike("description", words));
        }

        if (!StringUtils.isBlank(itemSelect)) {
            String words = StringUtil.gsFormat("%{0}%", itemSelect);
            condition.and(criteria2.andLike("itemValue", words));
        }
        condition.orderBy("no").asc();
        try {
            viewSelectSyncItemList = viewSelectSyncItemMapper.selectByCondition(condition);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }
        PageInfo pageInfo = new PageInfo(viewSelectSyncItemList);
        dataTableRecord.setITotalRecords(pageSize);
        dataTableRecord.setITotalDisplayRecords((int) pageInfo.getTotal());
        dataTableRecord.setAaData(viewSelectSyncItemList);
        return dataTableRecord;
    }

    @Override
    @LogAction(logDesc = "基础选项添加")
    public int itemInsertByTable(ViewSelectItem viewSelectItem) {
        if (viewSelectItem == null) {
            return -1;
        }
        String name = viewSelectItem.getTableName();
        try {
            viewSelectItem.setLanguage(SessionManager.getLanguage());
            viewSelectItem.setStatus(1);
            return viewSelectItemMapper.insertTable(viewSelectItem, name);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    @LogAction(logDesc = "基础选项更新")
    public int itemUpdateByTable(ViewSelectItem viewSelectItem) {
        if (viewSelectItem == null) {
            return 0;
        }
        String name = viewSelectItem.getTableName();
        try {
            viewSelectItem.setStatus(1);
            viewSelectItem.setLanguage(SessionManager.getLanguage());
            return viewSelectItemMapper.updateTable(viewSelectItem, name);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return 0;
        }
    }
}