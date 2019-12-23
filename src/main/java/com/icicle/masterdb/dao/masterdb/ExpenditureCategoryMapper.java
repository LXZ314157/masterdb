package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ExpenditureCategory;
import com.icicle.masterdb.pojo.vo.ExpenditureCategoryVO;
import com.icicle.masterdb.pojo.vo.SyncPropertyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExpenditureCategoryMapper extends MyMapper<ExpenditureCategory> {

    ExpenditureCategoryVO findExInfoByExcategoryId(@Param("excategoryId") String excategoryId,@Param("lanCode") String lanCode);

    /**
     * 获取同步到伯俊的开支类别列表
     * @param excategoryIdList
     * @param lanCode
     * @return
     */
    List<SyncPropertyVO> getSyncExCategoryInfo(@Param("excategoryIdList") String [] excategoryIdList,@Param("lanCode") String lanCode);


    /**
     * 获取开支类别导出的excel
     * @param exIdOrName
     * @param lanCode
     * @return
     */
    List<ExpenditureCategoryVO> findByQueryCondition(@Param("exIdOrName") String exIdOrName,@Param("lanCode") String lanCode);

}