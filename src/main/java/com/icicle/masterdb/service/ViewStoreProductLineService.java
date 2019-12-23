package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ViewStoreProductLine;
import com.icicle.masterdb.pojo.vo.ProductLineVO;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-02-22 18:09:21.
*/
public interface ViewStoreProductLineService extends Service<ViewStoreProductLine> {

    public List<ViewStoreProductLine> findViewProductLineByStoreNo(String storeNo);

    public List<ProductLineVO> checkProductLine(List<ViewStoreProductLine> viewStoreProductLineList, List<ProductLineVO> productLineVOList);
}