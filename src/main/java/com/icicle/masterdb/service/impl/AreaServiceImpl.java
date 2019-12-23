package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.AreaMapper;
import com.icicle.masterdb.model.Area;
import com.icicle.masterdb.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liurenhua
 */
@Service
public class AreaServiceImpl extends AbstractService<Area> implements AreaService {
    @Resource
    private AreaMapper areaMapper;

    @Override
    @LogAction(logDesc = "添加区域")
    public int saveArea(Area area) {
        return areaMapper.saveArea(area);
    }

}