package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewProductdetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ViewProductdetailMapper extends MyMapper<ViewProductdetail> {

    /**
     * 根据款号查询详细信息
     *
     * @param products
     * @return 详细信息list
     */
    List<ViewProductdetail> getDetails(@Param("products") List<String> products);

    /**
     * 根据条件获取导出内容
     * @return
     */
    List<Map<String,Object>> exportContent(@Param("syncStatus") Integer syncStatus, @Param("syncRecord") Integer syncRecord, @Param("iline") String iline, @Param("productClass") String productClass, @Param("year") Integer year, @Param("season") String season, @Param("devSeason") String devSeason, @Param("wave") String wave,
                                           @Param("iband") String iband, @Param("icicleGroup") String iciclegroup, @Param("workGroup") String workGroup);
}