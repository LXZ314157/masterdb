package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ProductLine;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-02-24 16:59:04.
*/
public interface ProductLineService extends Service<ProductLine> {

    List<ProductLine> findProductLine();

}