package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewMaterialAttrErpList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewMaterialAttrErpListMapper extends MyMapper<ViewMaterialAttrErpList> {

    /**
     * 根据款号查询详细信息
     *
     * @param codes
     * @return 详细信息list
     */
    List<ViewMaterialAttrErpList> getMaterialAttrErpList(@Param("codes") String[] codes);
}