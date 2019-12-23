package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ProductPromotion;

public interface ProductPromotionMapper extends MyMapper<ProductPromotion> {
    /**
     * 验证推广id是否存在
     *
     * @param id
     * @return
     */
    int checkPromotionId(String id);

    /**
     * 验证推广图片编码是否存在
     *
     * @param code
     * @return
     */
    int checkPromotionCode(String code);
}