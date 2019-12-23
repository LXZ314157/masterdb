package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.model.ProductCategory;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.pojo.vo.ProductCategoryVO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.util.List;
import java.util.Map;

/**
* @author  CodeGeneratorUtil
* @version 2019-03-11 18:56:49.
*/
public interface ProductCategoryService extends Service<ProductCategory> {

    /**
     *
     * @param productCategory
     * @return
     */
    int  saveProductCategory(ProductCategory productCategory);

    /**
     *
     * @return
     */
    List<ProductCategory> findAllAttributeDefined();

    /**
     *
     * @param productCategoryVO
     * @return
     */
    int connectAttribute(ProductCategoryVO productCategoryVO);


    /**
     *
     * @param productCategory
     * @return
     */
    int updateProductCategory(ProductCategory productCategory);

    /**
     *
     * @param mapList
     * @param productCategoryCode
     * @return
     */
    int saveExcelData(List<Map<String,Object>> mapList, String productCategoryCode);

    /**
     *
     */
    int  getTableCulomnsCount(String tableName);

    /**
     *
     * @param productCategoryCode
     * @return
     */
    List<Map<String,String>> getKeyList(String productCategoryCode);


    /**
     *
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @return
     */
    DataTableRecord getcategorytable(String sEcho, Integer pageIndex, Integer pageSize,String productCategoryCode,String sSearch);



    /**
     * 获取select表名
     * @param tableName
     * @return
     */
    List<Map<String,String>> getSelectTableName(String tableName);


    int saveOrUpdateProductInfoExcelData(List<Map<String,Object>> mapList);


    Result parseExcel(String path,int tableCulumnsCount,String productCategoryCode,List<Map<String,String>> tableNameList) throws InvalidFormatException;

    List<Map<String,String>> setProductCodeAndSku();

    /**
     * 查询产品的productCode和categoryCode
     * @return
     */
    List<Map<String,String>> getProductCodeAndCategoryCodeList();

    /**
     * 获取条码翻译的结果
     * @param path
     * @param list
     * @return
     */
    Result parseProductInfoExcel(String path,List<Object> list);

    /**
     * 获取重复的product_code的行
     * @return
     */
    List<Integer> checkProductList(String path,String tableName);



}