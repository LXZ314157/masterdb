package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.SkuSize;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface SkuSizeService extends Service<SkuSize> {
    /**
     * 查询条码信息
     * @return
     */
    List<SkuSize> findBySize();
}