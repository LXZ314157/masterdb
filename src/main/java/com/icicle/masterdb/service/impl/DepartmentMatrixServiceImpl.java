package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.DepartmentMatrixMapper;
import com.icicle.masterdb.model.Buyer;
import com.icicle.masterdb.model.DepartmentMatrix;
import com.icicle.masterdb.model.ViewDepartmentMatrixList;
import com.icicle.masterdb.service.DepartmentMatrixService;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.service.ViewDepartmentMatrixListService;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PageUtil;
import com.icicle.masterdb.web.BuyerController;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-15 16:50:06.
*/
@Service
public class DepartmentMatrixServiceImpl extends AbstractService<DepartmentMatrix> implements DepartmentMatrixService {

}