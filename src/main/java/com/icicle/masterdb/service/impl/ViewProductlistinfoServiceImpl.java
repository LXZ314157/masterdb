package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ViewProductlistinfoMapper;
import com.icicle.masterdb.model.ViewProductlistinfo;
import com.icicle.masterdb.service.ViewProductlistinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CodeGeneratorUtil
 */
@Service
public class ViewProductlistinfoServiceImpl extends AbstractService<ViewProductlistinfo> implements ViewProductlistinfoService {
    @Resource
    private ViewProductlistinfoMapper viewProductlistinfoMapper;
}