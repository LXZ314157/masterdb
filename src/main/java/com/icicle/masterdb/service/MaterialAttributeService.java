package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.MaterialAttribute;

import java.util.List;

/**
 * @author liurenhua
 */
public interface MaterialAttributeService extends Service<MaterialAttribute> {

    /**
     * 更新原材料属性
     *
     * @param materialCode
     * @param list
     * @return
     */
    int updateMaterialAttribute(List<MaterialAttribute> list, String materialCode);
}