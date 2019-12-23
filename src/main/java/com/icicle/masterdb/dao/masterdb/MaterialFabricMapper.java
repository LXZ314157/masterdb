package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.MaterialFabric;
/**
 * @author liurenhua
 */
public interface MaterialFabricMapper extends MyMapper<MaterialFabric> {
    /**
     * 根据原材料code来获取该原材料的materialCode
     * @param materialCode
     * @return
     */
    MaterialFabric findMaterialFabricByCode(String materialCode);
}