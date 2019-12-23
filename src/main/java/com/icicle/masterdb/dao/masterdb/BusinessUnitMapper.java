package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.BusinessUnit;

/**
 * @author CodeGeneratorUtil
 */
public interface BusinessUnitMapper extends MyMapper<BusinessUnit> {

    /**
     * 新增事业部
     * @param businessUnit
     * @return
     */
    int saveBusinessUnit(BusinessUnit businessUnit);
}