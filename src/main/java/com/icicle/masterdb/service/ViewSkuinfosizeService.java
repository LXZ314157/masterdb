package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ViewSkuinfosize;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ViewSkuinfosizeService extends Service<ViewSkuinfosize> {
    /**
     * 根据id查询所有的sku信息
     * @param ids
     * @return
     */
    List<ViewSkuinfosize> findByProductCode(List<String> ids);

}