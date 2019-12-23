package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewProductErpAttrList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewProductErpAttrListMapper extends MyMapper<ViewProductErpAttrList> {

    /**
     * 根据款号查询详细信息
     *
     * @param products
     * @return 详细信息list
     */
    List<ViewProductErpAttrList> getAttrList(@Param("products") List<String> products);
}