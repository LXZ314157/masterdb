package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.UnitMeasureMapper;
import com.icicle.masterdb.model.UnitMeasure;
import com.icicle.masterdb.service.UnitMeasureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liurenhua
 */
@Service
public class UnitMeasureServiceImpl extends AbstractService<UnitMeasure> implements UnitMeasureService {
    @Resource
    private UnitMeasureMapper unitMeasureMapper;

    @Override
    public List<UnitMeasure> selectAll() {
        return unitMeasureMapper.selectAll();
    }


}
