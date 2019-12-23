package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.pojo.bo.DynamicColumn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liumingming
 * @version 2018-01-02 15:03.
 */
public interface DynamicDataMapper {
    /**
     * 给表新增字段
     *
     * @param tableName 表名
     * @param column    字段
     * @return
     */
    int alterTableAddColumn(@Param("tableName") String tableName, @Param("column") DynamicColumn column);

    /**
     * 新建表
     *
     * @param tableName 表名
     * @param list      字段
     * @return
     */
    int createTable(@Param("tableName") String tableName, @Param("list") List<DynamicColumn> list);



}