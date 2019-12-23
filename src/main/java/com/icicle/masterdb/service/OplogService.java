package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.Oplog;

/**
 * @author CodeGeneratorUtil
 * @version 2017-12-25 11:41:19.
 */
public interface OplogService extends Service<Oplog> {

    /**
     * 写操作日志
     *
     * @param oplog
     * @return
     */
    int writeLOg(Oplog oplog);
}