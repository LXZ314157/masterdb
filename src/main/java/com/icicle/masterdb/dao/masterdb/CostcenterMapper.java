package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Costcenter;
import com.icicle.masterdb.pojo.vo.CostcenterVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CostcenterMapper extends MyMapper<Costcenter> {

    /**
     * 获取同步到汇联易和EHR的成本中心
     * @param costcenterIdList
     * @return
     */
    List<CostcenterVO> getSyncCostcenterInfo(@Param("costcenterIdList") String [] costcenterIdList,@Param("lanCode") String lanCode);


}