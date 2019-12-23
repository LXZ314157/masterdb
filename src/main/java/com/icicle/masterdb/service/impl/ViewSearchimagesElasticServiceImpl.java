package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.ViewAttriItemMapper;
import com.icicle.masterdb.dao.masterdb.ViewSearchimagesElasticMapper;
import com.icicle.masterdb.model.ViewAttriItem;
import com.icicle.masterdb.model.ViewSearchimagesElastic;
import com.icicle.masterdb.service.ViewSearchimagesElasticService;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.util.LogUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CodeGeneratorUtil
 * @version 2018-05-11 10:45:29.
 */
@Service
public class ViewSearchimagesElasticServiceImpl extends AbstractService<ViewSearchimagesElastic>
        implements ViewSearchimagesElasticService {
    @Resource
    private ViewSearchimagesElasticMapper viewSearchimagesElasticMapper;

    @Resource
    private ViewAttriItemMapper viewAttriItemMapper;

    @Override
    public List<ViewSearchimagesElastic> getList(String[] products) {
        try {
            return viewSearchimagesElasticMapper.getList(products);
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return new ArrayList<>();
        }
    }

    @Override
    public List<ViewAttriItem> getAllAttrItems() {
        return viewAttriItemMapper.selectAll();
    }
}