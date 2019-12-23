package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewMaterialErpList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewMaterialErpListMapper extends MyMapper<ViewMaterialErpList> {

    /**
     * 根据款号查询详细信息
     *
     * @param codes
     * @return 详细信息list
     */
    List<ViewMaterialErpList> getMaterialErpList(@Param("codes") String[] codes);
}