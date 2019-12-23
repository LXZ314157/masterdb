package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ProductCategory;
import com.icicle.masterdb.model.ViewSelectproductinfo;
import com.icicle.masterdb.pojo.vo.ProductInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductCategoryMapper extends MyMapper<ProductCategory> {

    /**
     *
     * @param productCategory
     * @return
     */
    int saveProductCategory(ProductCategory productCategory);


    /**
     *
     * @return
     */
    List<ProductCategory> findAllAttributeDefied();

    /**
     *
     * @param productCategory
     * @return
     */
    int updateProductCategory(ProductCategory productCategory);

    /**
     *  添加主键
     * @param map
     * @return
     */
    int insertPrimarykey(Map<String,String> map);

    /**
     * 更改其他字段
     * @param map
     * @return
     */
    int alterColumnValue(Map<String,String> map);

    /**
     * 查询重复记录
     * @param map
     * @return
     */
    int selectExistCount(Map<String,String> map);


    /**
     * 获取表列数
     * @param tableName
     * @return
     */
    int getTableCulomnsCount(String tableName);

    /**
     *
     * @param productCategoryCode
     * @return
     */
    List<Map<String,String>> getKeyList(String productCategoryCode);

    /**
     *
     * @param productCategoryCode
     * @param sSearch
     * @return
     */
    List<Map<String,Object>> getSearchList(@Param("productCategoryCode") String productCategoryCode,@Param("sSearch") String sSearch);

    /**
     *
     * @param productCategoryCode
     * @return
     */
    String getProductName(String productCategoryCode);


    /**
     * 扩展表添加基本字段
     * @param tableName
     * @param productCode
     * @param productId
     * @return
     */
    int insertCategoryTable(@Param("tableName") String tableName,@Param("productCode") String productCode,@Param("productId") String productId,@Param("loginName") String loginName);

    /**
     * 根据商品小类查询大中小类信息
     * @param cateZl
     * @param cateXl
     * @return
     */
    Map<String,String> findDzxlInfo(@Param("cateZl") String  cateZl,@Param("cateXl") String  cateXl);


    /**
     * @return
     */
    List<Map<String,Object>>getResultValue();

    /**
     * 根据key查找item的值
     * @return
     */
    List<Map<String,Object>> getHasItemTable();


    /**
     * 查询表是否存在
     * @param productCategory
     * @return
     */
    int selectTable(String productCategory);


    /**
     * 批量将扩展属性插入到竖表中
     * @param list
     * @return
     */
    int insertProductAttributeList(List<Map<String,String>> list);

    /**
     * 批量将扩展属性插入到产品类别表中
     * @param extendKeyList
     * @param extendValuesList
     * @return
     */
    int batchInsertProductExtendAttributeList(@Param("extendKeyList") List<String> extendKeyList,
                                              @Param("extendValuesList")List<List<String>> extendValuesList,
                                              @Param("productCategoryCode")String productCategoryCode);


    /**
     * 批量将扩展属性更新
     * @param extendUpdateValuesMapList
     * @param productCategoryCode
     * @return
     */
    int batchUpdateProductExtendAttributeList(@Param("extendUpdateValuesMapList") List<Map<String,Object>>extendUpdateValuesMapList,
                                              @Param("productCategoryCode") String productCategoryCode);

    /**
     * 更新竖表信息
     * @param map
     * @return
     */
    int updateProductAttribute(Map<String,String> map);


    /**
     * 获取selectTable
     * @param tableName
     * @return
     */
    List<Map<String,String>> getSelectTableName(@Param("tableName") String tableName);

    /**
     * 获取selectTable
     * @param cateXl
     * @param bookFourth
     * @return
     */
    Map<String,String> findBookDzxlInfo(@Param("cateXl") String cateXl,@Param("bookFourth") String bookFourth);

    /**
     * 根据productcode查询productInfo的记录
     * @param productCode
     * @return
     */
    int getProductInfoByProductCode(String productCode);

    /**
     * 批量保存productInfo
     * @param list
     * @return
     */
    int batchInsertProductInfo(@Param("list") List<ProductInfoVO> list);

    /**
     * 批量更新productInfo
     * @param list
     * @return
     */
    int batchUpdateProductInfo(List<ProductInfoVO> list);

    /**
     * 根据productCode和attrCode查询product_attribute记录
     * @param productCode
     * @param attrCode
     * @return
     */
    int selectProductAttrCount(@Param("productCode") String productCode,@Param("attrCode") String attrCode);


    Map<String,String> findHomeholdDzxlInfo(String cateZl);


    /**
     * 查询非服饰的基本字段信息
     * @param productCode
     * @return
     */
    ViewSelectproductinfo searchByCondition(String productCode);


    List<Map<String,Object>> getItemkeys();


    int batchInsertProductAttributeList(@Param("list") List<Map<String,String>> list);

    int batchUpdateProductAttributeList(@Param("list")List<Map<String,String>> list);


    List<String> selectMaterialContrainProductIds();


    int batchDeleteMaterialContrain(List<String> list);

    int batchInsertMaterialContrain(List<Map<String,String>> list);


    ViewSelectproductinfo searchHomeholdByCondition(String productCode);


    String getPrimaryNumkey(String subCategoryKey);


    String getProductCodeKey(String productCategoryCode);

    String getProductCategoryCode(String categoryKey);

    int updateProductExtendAttributeList(@Param("fields") Map<String,Object> fields,@Param("productCategoryCode") String productCategoryCode,@Param("productCode") String productCode);


    /**
     * 批量删除竖表数据
     * @param delProductCodeList
     * @return
     */
    int batchDeleteProductAttributeList(@Param("delProductCodeList") List<String> delProductCodeList);


    /**
     * 获取条码信息列表的productCode和sku
     * @return
     */
    List<Map<String,String>> setProductCodeAndSku();

    int deleteProductAttributeList(@Param("list") List<String> list);


    /**
     * 查询产品的productCode和categoryCode
     * @return
     */
    List<Map<String,String>> getProductCodeAndCategoryCodeList();

    /**
     * 获取sku是否存在
     * @return
     */
    String getProductCodeBySku(@Param("sku") String sku);


    int updateAttribute(Map<String,String> map);



}