package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewProductAttributeSelect;

import java.util.List;
/**
 * @author liurenhua
 */
public interface ViewProductAttributeSelectMapper extends MyMapper<ViewProductAttributeSelect> {
    /**
     * 根据defcode查询
     * @param defCode
     * @return
     */
    List<ViewProductAttributeSelect> findByDefindCode(String defCode);
}