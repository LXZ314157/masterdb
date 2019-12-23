package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.Area;

/**
 * @author CodeGeneratorUtil
 */
public interface AreaService extends Service<Area> {

    /**
     * 新增地区
     *
     * @param area
     * @return
     */
    int saveArea(Area area);

}