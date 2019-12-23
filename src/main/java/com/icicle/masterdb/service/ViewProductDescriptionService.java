package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.Product;
import com.icicle.masterdb.model.ViewProductDescription;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ViewProductDescriptionService extends Service<ViewProductDescription> {
    /**
     * 查询满足条件的所有款式
     * @param products
     * @return
     */
    List<ViewProductDescription> findByList(List<Product> products);

    /**
     * 单条查询
     * @param productCode
     * @return
     */
    ViewProductDescription findOnlyDesc(String productCode);
}