package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.BusinessUnit;

/**
 * @author CodeGeneratorUtil
 */
public interface BusinessUnitService extends Service<BusinessUnit> {

    /**
     * 新增事业部
     *
     * @param businessUnit
     * @return
     */
    int saveBusinessUnit(BusinessUnit businessUnit);

    /**
     * 更新事业部
     *
     * @param businessUnit
     * @return
     */
    int updateBusinessUnit(BusinessUnit businessUnit);
}