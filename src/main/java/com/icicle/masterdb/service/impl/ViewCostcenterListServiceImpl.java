package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.ViewCostcenterListMapper;
import com.icicle.masterdb.model.ViewCostcenterList;
import com.icicle.masterdb.pojo.vo.CostcenterVO;
import com.icicle.masterdb.pojo.vo.RespCenterVO;
import com.icicle.masterdb.service.ViewCostcenterListService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-14 14:19:27.
*/
@Service
public class ViewCostcenterListServiceImpl extends AbstractService<ViewCostcenterList> implements ViewCostcenterListService {
    @Resource
    private ViewCostcenterListMapper viewCostcenterListMapper;

    @Override
    public CostcenterVO findCostcenterInfoByCostcenterId(String costcenterId, String lanCode) {
        return viewCostcenterListMapper.findCostcenterInfoByCostcenterId(costcenterId,lanCode);
    }

    @Override
    public List<CostcenterVO> findByQueryCondition(String costcenterIdOrName, String lanCode) {
        return viewCostcenterListMapper.findByQueryCondition(costcenterIdOrName,lanCode);
    }
}