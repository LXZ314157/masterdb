package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewProductFeature;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ViewProductFeatureMapper extends MyMapper<ViewProductFeature> {
    /**
     * 根据款号查询款式特点信息
     * @param productCode
     * @return
     */
    List<ViewProductFeature> searchByProductCode(String productCode);

    /**
     * 根据产品编号获取推荐款式
     * @param productCode
     * @return
     */
    List<String> getRecommend(String productCode);

    /**
     * 根据产品编号获取图片路径
     * @param productCode
     * @return
     */
    List<ViewProductFeature> getImageUrl(String productCode);
}