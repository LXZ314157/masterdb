package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ViewProductFeature;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ViewProductFeatureService extends Service<ViewProductFeature> {
    /**
     * 根据产品编号获取单个单品的面料，款式等信息
     * @param productCode
     * @return
     */
    ViewProductFeature findAllDetail(String productCode);

    /**
     * 加载推荐图片信息
     * @param productCode
     * @return
     */
    List<ViewProductFeature> getRecommadImage(String productCode);


}