package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ViewCostcenterList;
import com.icicle.masterdb.pojo.vo.CostcenterVO;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-14 14:19:27.
*/
public interface ViewCostcenterListService extends Service<ViewCostcenterList> {


    CostcenterVO findCostcenterInfoByCostcenterId(String costcenterId, String lanCode);

    /**
     * 获取成本中心excel数据
     * @param costcenterIdOrName
     * @param lanCode
     * @return
     */
    List<CostcenterVO> findByQueryCondition(String costcenterIdOrName,String lanCode);



}