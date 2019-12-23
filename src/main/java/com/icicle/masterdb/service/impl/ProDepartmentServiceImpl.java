package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ProDepartmentMapper;
import com.icicle.masterdb.model.ProDepartment;
import com.icicle.masterdb.service.ProDepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author liurenhua
 */
@Service
public class ProDepartmentServiceImpl extends AbstractService<ProDepartment> implements ProDepartmentService {
    @Resource
    private ProDepartmentMapper proDepartmentMapper;

    @Override
    @LogAction(logDesc = "新增项目部")
    public int saveProDepartment(ProDepartment proDepartment) {
        return proDepartmentMapper.saveProDepartment(proDepartment);
    }

}