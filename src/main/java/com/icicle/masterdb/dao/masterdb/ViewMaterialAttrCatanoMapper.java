package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewMaterialAttrCatano;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewMaterialAttrCatanoMapper extends MyMapper<ViewMaterialAttrCatano> {

    /**
     * 根据款号查询详细信息
     *
     * @param codes
     * @return 详细信息list
     */
    List<ViewMaterialAttrCatano> getMaterialAttrCatano(@Param("codes") String[] codes);
}