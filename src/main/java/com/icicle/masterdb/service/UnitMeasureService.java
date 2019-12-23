package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.UnitMeasure;

import java.util.List;

/**
 * @author liurenhua
 */
public interface UnitMeasureService  extends Service<UnitMeasure>{
    /**
     * 获取完整数据
     * @return
     */
    List<UnitMeasure> selectAll();
}
