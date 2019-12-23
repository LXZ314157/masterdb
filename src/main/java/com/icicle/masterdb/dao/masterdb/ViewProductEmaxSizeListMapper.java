package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewProductEmaxSizeList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewProductEmaxSizeListMapper extends MyMapper<ViewProductEmaxSizeList> {

    /**
     * 根据款号查询详细信息
     *
     * @param products
     * @return 详细信息list
     */
    List<ViewProductEmaxSizeList> getSizeList(@Param("products") List<String> products);
}