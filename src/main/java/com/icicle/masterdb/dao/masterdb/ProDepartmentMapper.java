package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ProDepartment;
/**
 * @author liurenhua
 */
public interface ProDepartmentMapper extends MyMapper<ProDepartment> {

    /**
     * 新增项目组
     * @param proDepartment
     * @return
     */
    int saveProDepartment(ProDepartment proDepartment);
}