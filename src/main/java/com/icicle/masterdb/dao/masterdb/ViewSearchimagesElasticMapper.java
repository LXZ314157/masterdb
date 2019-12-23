package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewSearchimagesElastic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewSearchimagesElasticMapper extends MyMapper<ViewSearchimagesElastic> {
    /**
     * 获取同步到elasticsearch的数据
     *
     * @param products 编号数据
     * @return
     */
    List<ViewSearchimagesElastic> getList(@Param("products") String[] products);
}