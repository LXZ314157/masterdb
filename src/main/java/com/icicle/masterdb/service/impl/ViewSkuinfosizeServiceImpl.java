package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ViewSkuinfosizeMapper;
import com.icicle.masterdb.model.ViewSkuinfosize;
import com.icicle.masterdb.service.ViewSkuinfosizeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
@Service
public class ViewSkuinfosizeServiceImpl extends AbstractService<ViewSkuinfosize> implements ViewSkuinfosizeService {
    @Resource
    private ViewSkuinfosizeMapper viewSkuinfosizeMapper;

    @Override
    public List<ViewSkuinfosize> findByProductCode(List<String> ids) {
        return viewSkuinfosizeMapper.findByProductCode(ids);
    }

}