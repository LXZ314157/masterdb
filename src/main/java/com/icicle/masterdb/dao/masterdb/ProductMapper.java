package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.*;
import com.icicle.masterdb.pojo.vo.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductMapper extends MyMapper<Product> {
    /**
     * 更新同步状态
     *
     * @param lastUpdateBy
     * @param products
     * @return
     */
    int updateSynProduct(String lastUpdateBy,Date lastUpdateDate, @Param("products") String[] products);

    /**
     * 查询所有存在的款号
     *
     * @param product
     * @return 款号list
     */
    List<String> selectProductCode(List<Product> product);

    /**
     * 批量插入mapper
     *
     * @param product
     * @return
     */
    int insertProductAll(List<Product> product);

    /**
     * 批量更新mapper
     *
     * @param product
     * @return
     */
    int updateProductPart(List<Product> product);

    /**
     * 单条更新mapper
     *
     * @param product
     * @return
     */
    int updateEditProduct(Product product);

    /**
     * 批量更新描述信息mapper
     *
     * @param iewProductDescription
     * @return
     */
    int updateDesc(List<ViewProductDescription> iewProductDescription);

    /**
     * 根据款号查询
     *
     * @param productCode
     * @return
     */
    Product selectProduct(String productCode);

    /**
     * 更新单条描述数据
     *
     * @param desc
     * @param lastUpdatedBy
     * @param productCode
     * @param desc
     * @param lastUpdatedBy
     * @return
     */
    int updateDescOne(String productCode, String desc, String lastUpdatedBy, Date lastUpdateDate);

    /**
     * 得到list中合法的产品编码
     *
     * @param list
     * @return
     */
    List<String> findLegalProductCode(List<String> list);

    /**
     * 删除未同步过的条码
     * @param productCode
     * @return
     */
    int deleteUnSycn(String productCode);

    /**
     * 根据传入的编码字符串判断是否存在
     * @param codeList
     * @return
     */
    List<String> exictProductCode(List<String> codeList);

    /**
     * 批量插入产品基本信息
     * @param list
     * @return
     */
    int batchInsertPrimarykey(List<Map<String,Object>> list);

    /**
     * 批量更新产品基本信息
     * @param list
     * @return
     */
    int batchUpdatePrimarykey(List<Map<String,Object>> list);

    int updatePrimarykey(Map<String,String> map);


    String selectProductId(String productCode);

    List<Map<String,String>> findBaseProductValue(List<String> list);

    List<String> getAttributeDefinedList(@Param("productCategoryCode") String productCategoryCode);

    Map<String,String> getExtendTableValue(@Param("productCode") String productCode,@Param("productCategoryCode") String productCategoryCode);


    List<ProductAttrDefCodeVO> getExtendAttrCode(List<String> list);

    String selectWorkGroup(String workGroup);

    /**
     * 获取select下拉表
     * @param tableName
     * @return
     */
    List<Map<String,String>> getViewSelectTable(@Param("tableName") String tableName);


    List<Map<String,Object>> selectTableValues();


    /***
     * 查找itemkey
     * @param tableName
     * @param id
     * @return
     */
    String selectItemkey(@Param("tableName") String tableName,@Param("id") Integer id);


    /**
     * 查询商品大中小类
     * @return
     */
    List<Map<String,String>> selectProductDzl();


    /**查询产品主键
     * @return
     */
    List<Map<String,String>> getPrimarykeyAndCode();

    /**
     * 新增物料可申请的店铺
     * @param list
     * @return
     */
    int inserMaterialIinfo(List<Map<String,String>> list);


    /**
     * @param productId
     * @return
     */
    int selectProductIdCount(String productId);


    int deleteProductMaterialContrain(String productId);

    String getProductId(String productCode);


    String getProductSubCategoryName(String bookFourthCategory);

    /**
     * 查询产品基本表的productCode
     * @return
     */
    List<String> selectBaseProductCodes();

    /**
     * 查询扩展字段的productCode
     * @param productCategoryCode
     * @return
     */
    List<String> selectExtendCodes(String productCategoryCode);

    /**
     * 查询产品的product_code和product_id
     * @return
     */
    List<Map<String,String>> selectBaseProductCodesAndIds();


    List<Map<String,Object>> selectProductLine();


    /**
     * 根据品种查找服饰小类是否存在
     * @param productKind
     * @param categoryThirdLevel
     * @return
     */
    String getProductSecondLevel(@Param("productKind") String productKind,@Param("categoryThirdLevel") String categoryThirdLevel);

    /**
     * 根据品种获取产品线Id
     * @param productKind
     * @return
     */
    int getProductLineId(String productKind);

    /**
     * 获取产品品牌列表
     * @return
     */
    List<String> getBrandNameList();

    /**
     * 获取产品品牌的key和value
     * @return
     */
    List<Map<String,String>> getBrandKeyValue();

    /**
     * 获取产品编号和分类列表
     * @return
     */
    List<Map<String,String>> getCodeAndCategoryList();



    int batchDeleteProduct(@Param("baseDeleteProductCodeList") List<String> baseDeleteProductCodeList);


    //属性同步
    List<SyncPropertyVO> getPropertyList();

    /**
     * 获取product_attribute表的product_code列表
     * @return
     */
    List<String> getProductCodeList();

    /**
     * 获取色卡列表
     * @param colorCardNameOrNo
     * @return
     */
    List<ColorCardVO> findByQueryCondition(String colorCardNameOrNo);


    /**
     * 获取销售税率
     * @return
     */
    BigDecimal getSaleRate(@Param("productKind") String productKind, @Param("categorySecondLevelCode")String categorySecondLevelCode);


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
    List<SelectItemVO> findItemDataByQueryCondition(@Param("itemCategory") String itemCategory,@Param("itemSelect") String itemSelect);


    String generateNum(Map<String,String> param);

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
     * 获取服饰中类
     * @return
     */
    List<String> selectCostumeCategoryLevel1();

    /**
     * 获取服饰小类
     * @return
     */
    List<String> selectCostumeCategoryLevel2();

    /**
     * 获取批次列表
     * @return
     */
    List<Map<String,String>> selectBatchList();

    /**
     * 获取所有的销售税率列表
     * @return
     */
    List<Map<String,Object>> selectSaleTaxRate();


    /**
     * 获取产品种类
     * @param productCategoryName
     * @return
     */
    String getProductKind(String productCategoryName);

    /**
     * 查询尺寸列表
     */
    List<Map<String,Object>> selectSize();

    String  getProductCodeBySku(String skuValue);

    /**
     * 获取主表数据
     * @return
     */
    List<SyncProductInfoVO> getMainProduct(@Param("productCodeList") List<String> productCodeList);

    /**
     * 获取select列表
     * @return
     */
    List<SelectItemVO> selectItemVoList();


    /**
     * 获取品牌
     * @return
     */
    List<String> selectBrand();

    /**
     * 获取尺码信息主表数据
     * @param ids
     * @return
     */
    List<SyncSkuVO> getMainProductInfo(@Param("ids") List<Integer> ids);



    /**
     * 查询尺码组和产品编码
     * @return
     */
    List<Map<String,String>> selectProductCodeSizeGroup();

    /**
     * 获取view_select_item的列表
     * @return
     */
    List<Map<String,String>> selectItemList();

    /**
     * 查询单位列表
     * @return
     */
    List<String> selectUomList();


    String getProductSecondLevels(String categoryThirdLevel);

    /**
     * 获取尺寸组的code和name
     * @return
     */
    List<Map<String,String>> getSizeGroupList();


    String getProductNameByCode(String productCode);

    /**
     * 获取产品编号和详情列表
     * @param productCodeList
     * @return
     */
    List<ViewProductDescription> getProductDescriptions(@Param("productCodeList") List<String> productCodeList);

    /**
     * 获取所有产品编号
     * @return
     */
    List<String> getProductList();

    /**
     * 查询物料的产品编码
     * @return
     */
    List<String> getProductMaterialCodeList();

    /**
     * 获取服饰的sku
     * @param list
     * @return
     */
    List<SyncSkuVO> getCostumeSyncSkuVOList(@Param("list") List<Integer> list);

    /**
     * 获取物料的sku
     * @param list
     * @return
     */
    List<SyncSkuVO> getMaterailSyncSkuVOList(@Param("list") List<Integer> list);

    /**
     * 获取其它类别（图书、家居）的sku
     * @param list
     * @return
     */
    List<SyncSkuVO> getCategorySyncSkuVOList(@Param("list") List<Integer> list);


    /**
     * 获取扩展表数据
     * @return
     */
    List<ProductAttribute> getextendProduct(@Param("productCodeList") List<String> productCodeList);

    /**
     * 根据产品编号获取物料管控数据
     * @param productCodeList
     * @return
     */
    List<Map<String,String>> getProductMaterialCodeListByCode(@Param("productCodeList") List<String> productCodeList);


    /**
     * 获取色卡列表数据
     */
    List<Map<String,String>> getColorCardList();

    /**
     * 批量插入色卡表
     * @return
     */
    int insertColorCardList(List<ColorCard> list);


    /**
     * 获取产品分类--中类、小类
     * @return
     */
    List<ViewSelectItemVO> getCategoryLevel();


    /**
     * 更新产品基本属性
     * @param productVO
     * @return
     */
    int updateProduct(ProductVO productVO);


    /**
     * 根据产品类别和产品中类编码获取小类列表
     * @param categoryLevel1Code
     * @param productCategoryCode
     * @return
     */
    List<ViewSelectItemVO> getProductcategoryLevel2List(@Param("categoryLevel1Code") String categoryLevel1Code,@Param("productCategoryCode") String productCategoryCode);


    /**
     * 获取产品扩展属性值列表
     * @return
     */
    List<Map<String,String>>getExtendAttributeItemList();


    /**
     * 根据产品码查询颜色
     * @param list
     * @return
     */
    List<Map<String,String>> getColorsByProductCode(List<String> list);


    /**
     * 根据线路获取产品线id
     * @param productLine
     * @return
     */
    String getProductLineIdByLine(String productLine);


    /**
     * 获取批次列表
     * @return
     */
    List<Map<String,String>> getBatchList();

    /**
     * 获取尺寸组
     * @param productCode
     * @return
     */
    String getSizeGroupByProductCode(String productCode);


    /**
     * 根据尺寸组获取尺寸列表
     * @param sizeGroup
     * @return
     */
    List<Map<String,String>> getSizeList(String sizeGroup);

    /**
     * 获取产品类别列表
     * @return
     */
    List<Map<String,String>> getProductCategoryList();

    List<Map<String,String>> getProductCategoryCodeList(@Param("list") List<String> productCodeList);


    /**
     * 根据id查询sku信息
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
    List<SyncPropertyVO> getItemSyncList(@Param("list") String [] list);


    /**
     * 获取产品子类级别列表
     * @return
     */
    List<Integer> getProductSubCategoryLevelList();


    /**
     * 获取产品子类的编号和级别
     * @param list
     * @return
     */
    List<ViewProductSubCategoryList> getProductSubCategoryIdAndLevel(@Param("list") String [] list);

    /**
     * 获取产品子类--大类同步列表
     * @param list
     * @return
     */
    List<SyncProductSubCategoryVO> getSubCategoryLevel1List(@Param("list") List<Integer> list);
    /**
     * 获取产品子类--中类同步列表
     * @param list
     * @return
     */
    List<SyncProductSubCategoryVO> getSubCategoryLevel2List(@Param("list") List<Integer> list);
    /**
     * 获取产品子类--小类同步列表
     * @param list
     * @return
     */
    List<SyncProductSubCategoryVO> getSubCategoryLevel3List(@Param("list") List<Integer> list);

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
    int checkPaSubcategoryCode(@Param("lanCode") String lanCode,@Param("categoryCode") String categoryCode,
                               @Param("paSubCategoryCode") String paSubCategoryCode,@Param("paCategoryLevel") Integer paCategoryLevel);

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
     * 更新条码的标准单价
     * @param productVO
     * @return
     */
    int updateStandardPrice(ProductVO productVO);

    /**
     * 批量修改条
     * @param list
     * @return
     */
    int  batchUpdateStandardPrice(@Param("list") List<Product> list);


    /**
     * 根据id获取产品类别和产品编号
     * @param list
     * @return
     */
    List<ProductVO> getProductCategoryCode(@Param("list")List<Integer> list);


    /**
     * 获取服饰的同步列表
     * @param list
     * @return
     */
    List<Map<String,Object>> getSyncCostumeProductList(@Param("list")List<String> list);

    /**
     * 获取物料的同步列表
     * @param list
     * @return
     */
    List<Map<String,Object>> getSyncMaterialProductList(@Param("list")List<String> list);

    /**
     * 获取其它类别的同步列表
     * @param list
     * @return
     */
    List<Map<String,Object>> getSyncCategoryProductList(@Param("list")List<String> list);

    /**
     * 根据产品编号获取产品类别
     * @param list
     * @return
     */
    List<ProductVO> getProductCodeAndKeyList(@Param("list") List<String> list);

    /**
     * 获取图书列表的编号
     * @param list
     * @return
     */
    List<String> getBookCodeList(@Param("list") List<String> list);

    /**
     * 获取家居列表的编号
     * @param list
     * @return
     */
    List<String> getHomeholdCodeList(@Param("list")List<String> list);

    /**
     * 获取物料列表的编号
     * @param list
     * @return
     */
    List<String> getMaterialCodeList(@Param("list")List<String> list);

    /**
     * 获取服饰列表的编号
     * @param list
     * @return
     */
    List<String> getCostumeCodeList(@Param("list")List<String> list);


    /**
     * 批量删除条码
     * @param list
     * @return
     */
    int batchDeleteSku(@Param("list") String [] list);

    /**
     * 获取所有条码的款号和sku
     * @return
     */
    List<ProductInfoVO> selectProductCodeAndSku();


    /**
     * 查询所有的条码和款号
     * @return
     */
    List<ProductInfoVO> selectProductAndSku();

}

