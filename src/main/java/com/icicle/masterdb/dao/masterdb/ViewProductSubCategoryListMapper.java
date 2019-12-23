package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewProductSubCategoryList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewProductSubCategoryListMapper extends MyMapper<ViewProductSubCategoryList> {


    /**
     * 获取产品子列列表
     * @param codeOrName
     * @param key
     * @param lanCode
     * @return
     */
    List<ViewProductSubCategoryList> getProductSubCategeryList(@Param("codeOrName") String codeOrName,@Param("key") String key,
                                                               @Param("lanCode") String lanCode,@Param("subCategoryLevel") Integer subCategoryLevel);

}