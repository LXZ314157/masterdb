package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewSelectItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ViewSelectItemMapper extends MyMapper<ViewSelectItem> {
    /**
     * 根据表名查找属性
     *
     * @param tableName
     * @return
     */
    List<ViewSelectItem> findByTableName(String tableName);

    /**
     * 查询选项
     * @param ids
     * @return
     */
    List<ViewSelectItem> findSelectItem(List<String> ids);

    /**
     * 查询视图中的所有选项
     * @return
     */
    List<ViewSelectItem> findAllItem(@Param("list") List<String> list);

    /**根据表名，更新数据
     * @param viewSelectItem
     * @param tablecatalog   传入的表名     * @return
     */
    int updateTable(@Param("viewSelectItem") ViewSelectItem viewSelectItem, @Param("tablecatalog") String tablecatalog);

    /**
     * 插入数据
     * @param viewSelectItem
     * @param tablecatalog
     * @return
     */
    int insertTable(@Param("viewSelectItem") ViewSelectItem viewSelectItem, @Param("tablecatalog") String tablecatalog);
    /**
     * 加载所有的表名
     * @return
     */
    List<String> selectTableName();

    /**
     * 根据键和值两者判断重复
     * @param viewSelectItem
     * @param tablecatalog
     * @return
     */
    int searchForUpdate(@Param("viewSelectItem") ViewSelectItem viewSelectItem, @Param("tablecatalog") String tablecatalog);

    /**
     * 为不同的表添加新的选项
     * @param viewSelectItem
     * @param tablecatalog
     * @return
     */
    int searchForInsert(@Param("viewSelectItem") ViewSelectItem viewSelectItem, @Param("tablecatalog") String tablecatalog);

}