package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewProductDimension;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ViewProductDimensionMapper extends MyMapper<ViewProductDimension> {
    /**
     * 根据属性编码查找属性
     *
     * @param dimensionCode
     * @return
     */
    List<ViewProductDimension> findByDimensionCode(String dimensionCode);
}

