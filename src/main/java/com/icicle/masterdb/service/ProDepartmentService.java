package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ProDepartment;

/**
 * @author liurenhua
 */
public interface ProDepartmentService extends Service<ProDepartment> {

    /**
     * 新增项目组
     *
     * @param proDepartment
     * @return
     */
    int saveProDepartment(ProDepartment proDepartment);

}