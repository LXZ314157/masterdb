package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.UnitOfMeasure;
/**
 * @author liurenhua
 */
public interface UnitOfMeasureMapper extends MyMapper<UnitOfMeasure> {
    /**
     * 更新单位
     * @param unitOfMeasure
     * @return
     */
    int updateUom(UnitOfMeasure unitOfMeasure);
}