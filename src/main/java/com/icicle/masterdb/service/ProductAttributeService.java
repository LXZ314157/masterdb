package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ProductAttribute;
import com.icicle.masterdb.model.ProductDimensionMerchandising;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductAttributeService extends Service<ProductAttribute> {

    /**
     * 编辑产品属性，将属性中已经存在的更新，不存在的 添加进去
     *
     * @param productCode
     * @param list
     * @return
     */
    int editProductAttribute(List<ProductAttribute> list, String productCode);


    /**
     * 根据产品编码查找属性
     *
     * @param productCode
     * @return
     */
    List<ProductAttribute> findAttributeByCode(String productCode);


    /**
     * 将竖表中的数据存入到横表里面
     *
     * @param list
     * @param productCode
     * @return
     */
    int saveAttributeListAsModel(List<ProductAttribute> list, String productCode);

    /**
     * 将横表中的数据转到竖表里面
     *
     * @param list
     * @return
     */
    List<ProductDimensionMerchandising> transferAttrToMerchandising(List<ProductAttribute> list);

    /**
     * 解析excel封装成竖表
     *
     * @param inputStream
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    List<ProductAttribute> parseExcel(InputStream inputStream) throws IOException, InvalidFormatException;


    /**
     * 批量插入productAttribute
     *
     * @param list
     * @return
     **/
    int saveList(List<ProductAttribute> list);

}