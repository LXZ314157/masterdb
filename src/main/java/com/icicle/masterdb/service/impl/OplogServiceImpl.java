package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.log.OplogMapper;
import com.icicle.masterdb.model.Oplog;
import com.icicle.masterdb.service.OplogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CodeGeneratorUtil
 * @version 2017-12-25 11:41:19.
 */
@Service
public class OplogServiceImpl extends AbstractService<Oplog> implements OplogService {
    @Resource
    private OplogMapper oplogMapper;

    @Override
    public int writeLOg(Oplog oplog) {
        return oplogMapper.writeLOg(oplog);
    }
}