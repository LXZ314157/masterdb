package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewProductErpList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewProductErpListMapper extends MyMapper<ViewProductErpList> {

    /**
     * 根据款号查询详细信息
     *
     * @param products
     * @return 详细信息list
     */
    List<ViewProductErpList> getProductList(@Param("products") List<String> products);
}