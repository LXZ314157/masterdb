package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ProductInfo;
import com.icicle.masterdb.pojo.vo.ProductInfoSkuVO;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductInfoMapper extends MyMapper<ProductInfo> {
    /**
     * 单条插入尺码信息
     * @param productInfo
     * @return
     */
    int insertProductInfo(ProductInfoSkuVO productInfo);

    /**
     * 单条更新颜色信息
     * @param productInfo
     * @return
     */
    int updateColor(ProductInfoSkuVO productInfo);

    /**
     * 查询已经存在的sku
     * @param productInfo
     * @return
     */
    List<String> selectProductInfo(List<ProductInfo> productInfo);

    /**
     * 批量插入
     * @param productInfos
     * @return
     */
    int insertInfoAll(List<ProductInfo> productInfos);

    /**
     * 批量更新
     * @param productInfos
     * @return
     */
    int updateInfoPart(List<ProductInfo> productInfos);

    /**
     * 按最后创建时间，更新同种款号的color
     *
     * @return
     */
    int updateColorAll();

    /**
     * 单条更新状态
     * @param productInfo
     * @return
     */
    int updateStatus(ProductInfoSkuVO productInfo);

    /**
     * 获取无效的单条sku信息
     * @param sku
     * @return
     */
    String unvalidStatus(String sku);

    /**
     * 根据编号获取颜色
     * @param id
     * @return
     */
    String getColor(Integer id);

}