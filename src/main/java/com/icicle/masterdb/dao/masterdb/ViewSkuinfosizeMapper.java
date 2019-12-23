package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewSkuinfosize;

import java.util.List;


/**
 * @author CodeGeneratorUtil
 */
public interface ViewSkuinfosizeMapper extends MyMapper<ViewSkuinfosize> {
    /**
     * 根据id批量查询
     *
     * @param ids
     * @return
     */
    List<ViewSkuinfosize> findByProductCode(List<String> ids);

}