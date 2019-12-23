package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.SubExpenditureCategory;
import com.icicle.masterdb.pojo.vo.SubExpenditureCategoryVO;
import com.icicle.masterdb.pojo.vo.SyncPropertyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubExpenditureCategoryMapper extends MyMapper<SubExpenditureCategory> {

    SubExpenditureCategoryVO findsubExInfoBySubExcategoryId(@Param("subExcategoryId") String subExcategoryId,@Param("lanCode") String lanCode);

    /**
     * 获取开支子类的同步列表
     * @param subExcategoryIdList
     * @param lanCode
     * @return
     */
    List<SyncPropertyVO> getSyncSubExCategoryInfo(@Param("subExcategoryIdList") String [] subExcategoryIdList, @Param("lanCode") String lanCode);

    /**
     * 获取开支子类的导出列表
     * @param subExIdOrName
     * @param lanCode
     * @return
     */
    List<SubExpenditureCategoryVO> findByQueryCondition(@Param("subExIdOrName") String subExIdOrName,@Param("lanCode") String lanCode);


}