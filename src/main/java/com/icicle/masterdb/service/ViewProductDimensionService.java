package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ViewProductDimension;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ViewProductDimensionService extends Service<ViewProductDimension> {
    /**
     * 根据属性编码查找属性
     *
     * @param dimensionCode
     * @return
     */
    List<ViewProductDimension> findByDimensionCode(String dimensionCode);
}