package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewCostcenterList;
import com.icicle.masterdb.pojo.vo.CostcenterVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewCostcenterListMapper extends MyMapper<ViewCostcenterList> {

    CostcenterVO findCostcenterInfoByCostcenterId(@Param("costcenterId") String costcenterId, @Param("lanCode") String lanCode);

    /**
     * 获取成本中心excel数据
     * @param costcenterIdOrName
     * @param lanCode
     * @return
     */
    List<CostcenterVO> findByQueryCondition(@Param("costcenterIdOrName") String costcenterIdOrName,@Param("lanCode") String lanCode);

}