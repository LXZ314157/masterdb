package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Product;
import com.icicle.masterdb.model.ViewProductDescription;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ViewProductDescriptionMapper extends MyMapper<ViewProductDescription> {
    /**
     * 查询符合的条件的所有款
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