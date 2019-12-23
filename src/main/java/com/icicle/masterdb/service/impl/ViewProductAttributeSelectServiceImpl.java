package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ViewProductAttributeSelectMapper;
import com.icicle.masterdb.model.ViewProductAttributeSelect;
import com.icicle.masterdb.service.ViewProductAttributeSelectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author liurenhua
 */
@Service
public class ViewProductAttributeSelectServiceImpl extends AbstractService<ViewProductAttributeSelect> implements ViewProductAttributeSelectService {
    @Resource
    private ViewProductAttributeSelectMapper viewProductAttributeSelectMapper;
}