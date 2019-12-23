package com.icicle.masterdb.dao.log;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Oplog;

/**
 * @author CodeGeneratorUtil
 */
public interface OplogMapper extends MyMapper<Oplog> {

    /**
     * 写操作日志
     * @param oplog
     * @return
     */
    int writeLOg(Oplog oplog);
}