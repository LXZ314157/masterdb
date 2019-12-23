package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ViewSelectItem;

import java.util.List;

/**
 * @author liurenhua
 */
public interface ViewSelectItemService extends Service<ViewSelectItem> {
    /**
     * 根据表名查找属性
     *
     * @param tableName
     * @return
     */
    List<ViewSelectItem> findByTableName(String tableName);

    /**
     * 获取所有的选项列表
     *
     * @return
     */
    List<ViewSelectItem> findAllItem(List<String> list);

    /**
     * 根据表名查询
     * @return
     */
    List<String> selectTableName();

    /**更新时验证不重复
     * @param viewSelectItem
     * @param tablecatalog
     * @return
     */
    int searchForUpdate(ViewSelectItem viewSelectItem, String tablecatalog);

    /**插入时验证不和其他不重复
     * @param viewSelectItem
     * @param tablecatalog
     * @return
     */
    int searchForInsert(ViewSelectItem viewSelectItem, String tablecatalog);

    /**选项列表的获取
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param itemCategory
     * @param itemSelect
     * @param sortCol
     * @param sortDir
     * @return
     */
    DataTableRecord listItem(String sEcho, Integer pageIndex, Integer pageSize, String itemCategory,String itemSelect, Integer sortCol, String sortDir);

    /**
     * 根据表名添加选项
     *
     * @param viewSelectItem
     * @return
     */
    int itemInsertByTable(ViewSelectItem viewSelectItem);

    /**
     * 根据表名更新选项
     *
     * @param viewSelectItem
     * @return
     */
    int itemUpdateByTable(ViewSelectItem viewSelectItem);
}