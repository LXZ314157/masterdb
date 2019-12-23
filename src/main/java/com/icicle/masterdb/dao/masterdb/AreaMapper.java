package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Area;
/**
 * @author liurenhua
 */
public interface AreaMapper extends MyMapper<Area> {

    /**
     * 新增地区
     * @param area
     * @return
     */
    int saveArea(Area area);
}