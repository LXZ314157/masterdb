package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.*;
import com.icicle.masterdb.pojo.bo.SyncSKU;
import com.icicle.masterdb.pojo.vo.*;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductService extends Service<Product> {
    /**
     * 更新产品同步状态
     * @param lastUpdateBy 最后更新人
     * @param products
     * @param lastUpdateBy
     * @return
     */
    int updateSynProduct(String lastUpdateBy,Date lastUpdateDate,String[] products);

    /**
     * 批量查询已经存在的款号
     * @param product
     * @return
     */
    List<String> selectProductCode(List<Product> product);

    /**
     * 批量插入款的信息 数据库操作
     * @param product
     * @return
     */
    int insertProductAll(List<Product> product);

    /**
     * 批量的更新款式的信息 数据操作
     * @param product
     * @return
     */
    int updateProductPart(List<Product> product);

    /**
     * 款式插入 方法
     * @param productExcelVO
     * @param productBaseCodeList
     * @return
     */
    int styleInsert(ProductExcelVO productExcelVO,List<String> productBaseCodeList);

    /**
     * 款式更新 方法
     * @param productExcelVO
     * @param productBaseCodeList
     * @return
     */
    int styleUpdate(ProductExcelVO productExcelVO,List<String> productBaseCodeList);

    /**
     * 更新产品信息
     * @param product
     * @return
     */
    int updateEditProduct(Product product);

    /**
     * 批量更新描述信息
     * @param viewProductDescription
     * @return
     */
    int updateDesc(List<ViewProductDescription> viewProductDescription);

    /**
     * 单个更新描述信息
     * @param productCode
     * @param desc
     * @param lastUpdatedBy
     * @return
     */
    int updateDescOne(String productCode,String desc,String lastUpdatedBy,Date lastUpdateDate);

    /**
     * 根据款号查询是否存在是否存在该产品信息
     * @param productCode
     * @return
     */
    Product selectProduct(String productCode);

    /**
     * 更具款号查询同步状态
     * @param productcode
     * @return
     */
    int searchSynRecord(String productcode);

    /**
     * 验证产品和模特编码是否符合要求AAAP218A20131A
     * 产品编码以AAA开头 第6 、7 位是年份必须是数字 其余只允许数字和大写字母
     * @param productCode
     * @return
     */
    Boolean checkProductCode(String productCode);

    /**
     * 根据款号查询详细信息
     *
     * @param codes
     * @return 详细信息list
     */
    List<ViewProductdetail> getDetails(List<String> codes);

    /**
     * 根据款号查询同步到ERP的款式数据
     *
     * @param codes
     * @return 详细信息list
     */
    List<ViewProductErpList> getERPProductList(List<String> codes);

    /**
     * 根据款号查询同步到ERP的款式属性
     *
     * @param codes
     * @return 详细信息list
     */
    List<ViewProductErpAttrList> getERPAttrList(List<String> codes);

    /**
     * 根据款号查询详细信息
     *
     * @param codes
     * @return 详细信息list
     */
    List<ViewProductEmaxAttrList> getEmaxAttrList(List<String> codes);

    /**
     * 根据款号查询详细信息
     *
     * @param codes
     * @return 详细信息list
     */
    List<ViewProductEmaxSizeList> getSizeList(List<String> codes);

    /**
     * 获取同步的数据对象
     * @param codes
     * @return
     */
    SyncSKU getSyncSKUInfo(List<String> codes);


    List<Map<String, Object>> getExportData(Integer syncStatus,Integer syncRecord,String line,String productClass,Integer year,
                                            String season,String devSeason,String wave,String band,String icicleGroup,String workGroup);

    ProductAttributeItemsVO getAttributeDefinedItemList(String productCode,String productCategoryCode);

    int alterProductCatogoryValue(ProductAttributeItemsVO productAttributeItemsVO);

    int saveOrUpdateProductAttribute(List<ProductAttributeExtend> productAttributeExtendList,List<String> productBaseCodeList);

    /**
     * 获取产品品牌列表
     * @return
     */
    List<String> getBrandNameList();


    /**
     * 获取产品编号和分类列表
     * @return
     */
    List<Map<String,String>> getCodeAndCategoryList();


    List<SyncPropertyVO> getPropertyList();

    /**
     * 获取色卡列表
     * @param colorCardNameOrNo
     * @return
     */
    List<ColorCardVO> findByQueryCondition(String colorCardNameOrNo);

    /**
     * 获取色卡workbook
     * @param list
     * @return
     */
    Workbook exportColorCardExcel(List<ColorCardVO> list);


    /**
     * 获取产品编号和产品类别key
     * @return
     */
    List<Map<String,String>> getProductCodeAndKey();


    /**
     * 获取产品编码和线路列表
     * @return
     */
    List<Map<String,String>> getProductCodeLine();


    /**
     * 获取基础选项列表
     * @param itemCategory
     * @param itemSelect
     * @return
     */
    List<SelectItemVO> findItemDataByQueryCondition(String itemCategory,String itemSelect);

    /**
     * 获取基础选项列表的workbook
     * @param list
     * @return
     */
    Workbook exportItemDataExcel(List<SelectItemVO> list);


    String generateNum(String numKey);


    /**
     * 获取所有扩展字段的item
     * @return
     */
    List<Map<String,String>> getItemList();


    /**
     * 获取产品类别子类列表
     * @return
     */
    List<Map<String,Object>> getProductSubCategory();

    /**
     * 获取服饰模板的workbook
     * @return
     */
    Workbook getCostumeWorkbook();


    /**
     * 查询单位列表
     * @return
     */
    List<String> selectUomList();

    /**
     * 查询批次列表
     */
    List<Map<String,String>> selectBatchList();

    /**
     * 查询尺寸列表
     */
    List<Map<String,Object>> selectSize();


    /**
     * 获取主表数据
     * @return
     */
    List<SyncProductInfoVO> getMainProduct(List<String> categoryCodeList);


    /**
     * 获取select列表
     * @return
     */
    List<SelectItemVO> selectItemVoList();


    /**
     * 获取尺码信息主表数据
     * @param ids
     * @return
     */
    List<SyncSkuVO> getMainProductInfo(List<Integer> ids);

    /**
     * 查询尺码组和产品编码
     * @return
     */
    List<Map<String,String>> selectProductCodeSizeGroup();


    /**
     * 获取尺寸组的code和name
     * @return
     */
    List<Map<String,String>> getSizeGroupList();


    List<String> selectBaseProductCodes();

    /**
     * 查询物料的产品编码
     * @return
     */
    List<String> getProductMaterialCodeList();

    /**
     * 获取要同步的sku列表
     * @param mainProductInfoList
     * @param materialProductCodeList
     * @return
     */
    List<SyncSkuVO> getProductCategorySkus(List<SyncSkuVO> mainProductInfoList,List<String> materialProductCodeList );

    /**
     * 获取扩展表数据
     * @return
     */
    List<ProductAttribute> getextendProduct(List<String> categoryCodeList);

    /**
     * 根据产品编号获取物料管控数据
     * @param productCodeList
     * @return
     */
    List<SyncMaterialContrainVO> getProductMaterialCodeListByCode(List<String> productCodeList);


    /**
     * 根据产品类别和产品中类编码获取小类列表
     * @param categoryLevel1Code
     * @param productCategoryCode
     * @return
     */
    List<ViewSelectItemVO> getProductcategoryLevel2List(String categoryLevel1Code,String productCategoryCode);


    /**
     * 获取产品扩展属性值列表
     * @return
     */
    List<Map<String,String>> getExtendAttributeItemList();


    /**
     * 根据产品码查询颜色
     * @param productCodeList
     * @return
     */
    List<Map<String,String>> getColorsByProductCode(List<String> productCodeList);


    /**
     * SKU列表的获取
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param batchSku
     * @param productCodeOrSku
     * @param startTime
     * @param endTime
     * @param sortCol
     * @param sortDir
     * @return
     */
    DataTableRecord listSku(String sEcho, Integer pageIndex, Integer pageSize,String batchSku, String productCodeOrSku, String startTime,
                            String endTime, Integer sortCol, String sortDir);

    /**
     *
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param productSubCategoryCodeOrName
     * @param productCategoryKey
     * @param lanCode
     * @return
     */
    DataTableRecord getProductSubCategoryList(String sEcho, Integer pageIndex, Integer pageSize, String productSubCategoryCodeOrName, String productCategoryKey,
                            String lanCode,Integer subCategoryLevel);

    /**
     * 获取批次列表
     * @return
     */
    List<Map<String,String>> getBatchList();

    /**
     * 获取尺寸组
     * @param productCode
     * @param productCategoryCode
     * @return
     */
    List<Map<String,String>> getSizeList(String productCode,String productCategoryCode);

    /**
     * 根据id查询sku
     * @param id
     * @return
     */
    ProductInfo getProductInfoById(Integer id);

    /**
     * 更新sku信息
     * @param productInfo
     * @return
     */
    int updateProductInfo(ProductInfoSkuVO productInfo);


    /**
     * 查询sku是否存在
     * @param productInfo
     * @return
     */
    Integer getexistsSkuCount(ProductInfoSkuVO productInfo);


    /**
     * 获取select选项列表名称
     * @return
     */
    List<String> getItemTableList();

    /**
     * 获取同步到伯俊的属性列表
     * @param list
     * @return
     */
    List<SyncPropertyVO> getItemSyncList(String [] list);


    /**
     * 获取产品类别列表
     * @return
     */
    List<Map<String,String>> getProductCategoryList();


    /**
     * 获取产品子类级别列表
     * @return
     */
    List<Integer> getProductSubCategoryLevelList();


    /**
     * 获取产品子类的编号和级别
     * @param itemArray
     * @return
     */
    List<ViewProductSubCategoryList> getProductSubCategoryIdAndLevel(String [] itemArray);


    /**
     * 获取产品子类--大类同步列表
     * @param list
     * @return
     */
    List<SyncProductSubCategoryVO> getSubCategoryLevel1List(List<Integer> list);
    /**
     * 获取产品子类--中类同步列表
     * @param list
     * @return
     */
    List<SyncProductSubCategoryVO> getSubCategoryLevel2List(List<Integer> list);
    /**
     * 获取产品子类--小类同步列表
     * @param list
     * @return
     */
    List<SyncProductSubCategoryVO> getSubCategoryLevel3List(List<Integer> list);

    /**
     * 保存产品子类数据
     * @return
     */
    int insertProductSubCategory(ProductSubCategoryVO productSubCategoryVO);


    /**
     * 检查产品子类是否存在
     * @param productSubCategoryVO
     * @return
     */
    int checkProductSubCategory(ProductSubCategoryVO productSubCategoryVO);

    /**
     * 根据语言、产品分类编号、产品编号查找产品子类
     * @param lanCode
     * @param categoryCode
     * @param paSubCategoryCode
     * @param paCategoryLevel
     * @return
     */
    int checkPaSubcategoryCode(String lanCode,String categoryCode,String paSubCategoryCode,Integer paCategoryLevel);

    /**
     * 查询物料键是否存在
     * @param subCategoryKey
     * @return
     */
    int checkProdutCheckMaterialKey(String subCategoryKey);

    /**
     * @param productSubCategoryVO
     * @return
     */
    int updateProductSubCategory(ProductSubCategoryVO productSubCategoryVO);


    /**
     * 根据id获取产品类别和产品编号
     * @param idList
     * @return
     */
    List<ProductVO> getProductCategoryCode(List<Integer> idList);


    /**
     * 获取服饰的同步列表
     * @param list
     * @return
     */
    List<Map<String,Object>> getSyncCostumeProductList(List<String> list);

    /**
     * 获取物料的同步列表
     * @param list
     * @return
     */
    List<Map<String,Object>> getSyncMaterialProductList(List<String> list);

    /**
     * 获取其它类别的同步列表
     * @param list
     * @return
     */
    List<Map<String,Object>> getSyncCategoryProductList(List<String> list);


    /**
     * 根据产品编号获取产品类别
     * @param list
     * @return
     */
    List<ProductVO> getProductCodeAndKeyList(List<String> list);

    /**
     * 获取图书列表的编号
     * @param list
     * @return
     */
    List<String> getBookCodeList(List<String> list);

    /**
     * 获取家居列表的编号
     * @param list
     * @return
     */
    List<String> getHomeholdCodeList(List<String> list);

    /**
     * 获取物料列表的编号
     * @param list
     * @return
     */
    List<String> getMaterialCodeList(List<String> list);

    /**
     * 获取服饰列表的编号
     * @param list
     * @return
     */
    List<String> getCostumeCodeList(List<String> list);

    /**
     * 批量删除条码
     * @param list
     * @return
     */
    int batchDeleteSku(String [] list);

    /**
     * 获取条码表的款号和条码
     * @return
     */
    List<ProductInfoVO> selectProductCodeAndSku();


}