package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.MaterialAttribute;

import java.util.List;
/**
 * @author liurenhua
 */
public interface MaterialAttributeMapper extends MyMapper<MaterialAttribute> {

    /**
     * 批量更新原材料属性
     *
     * @param list
     * @return
     */
    int updateAttributeList(List<MaterialAttribute> list);

}