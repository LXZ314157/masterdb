package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Responsibilitycenter;
import com.icicle.masterdb.pojo.vo.RespCenterVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResponsibilitycenterMapper extends MyMapper<Responsibilitycenter> {

    RespCenterVO findRespcenterInfoByRespcenterId(@Param("respcenterId") String respcenterId, @Param("lanCode") String lanCode);

    /**
     * 获取责任中心导出excel的集合
     * @param respcenterIdOrName
     * @param lanCode
     * @return
     */
    List<RespCenterVO> findByQueryCondition(@Param("respcenterIdOrName") String respcenterIdOrName,@Param("lanCode") String lanCode);

}