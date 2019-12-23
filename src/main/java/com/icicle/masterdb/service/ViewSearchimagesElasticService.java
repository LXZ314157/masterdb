package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ViewAttriItem;
import com.icicle.masterdb.model.ViewSearchimagesElastic;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 * @version 2018-05-11 10:45:29.
 */
public interface ViewSearchimagesElasticService extends Service<ViewSearchimagesElastic> {

    /**
     * 获取同步到elasticsearch的数据
     *
     * @param products 编号数据
     * @return
     */
    List<ViewSearchimagesElastic> getList(String[] products);

    /**
     * 获取属性
     * @return
     */
    List<ViewAttriItem> getAllAttrItems();
}