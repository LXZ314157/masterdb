package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ProductInfo;
import com.icicle.masterdb.pojo.vo.ProductInfoExcelVO;
import com.icicle.masterdb.pojo.vo.ProductInfoSkuVO;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductInfoService extends Service<ProductInfo> {

    /**
     * 尺码批量导入 方法
     * @param productInfoExcelVO
     * @return
     */
    int sizeInsert(ProductInfoExcelVO productInfoExcelVO);

    /**
     * 删除未同步过的尺寸
     * @param productcode
     * @return
     */
    int delSize(String productcode);

    /**
     * 单条添加尺码
     * @param productInfo
     * @return
     */
    int insertSize(ProductInfoSkuVO productInfo);

    /**
     * 根据编号获取颜色
     * @param id
     * @return
     */
    String getColor(Integer id);


}