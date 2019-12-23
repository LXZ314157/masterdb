package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.UnitMeasure;

import java.util.List;

/**
 * @author liurenhua
 */
public interface UnitMeasureMapper extends MyMapper<UnitMeasure> {
    /**
     * 查询全部
     *
     * @return
     */
    @Override
    List<UnitMeasure> selectAll();
}
