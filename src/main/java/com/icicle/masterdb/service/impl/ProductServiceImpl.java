package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.*;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.*;
import com.icicle.masterdb.pojo.bo.SyncSKU;
import com.icicle.masterdb.pojo.vo.*;
import com.icicle.masterdb.service.*;
import com.icicle.masterdb.service.constant.CommonConstant;
import com.icicle.masterdb.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.icicle.masterdb.service.constant.ServiceConstant.ASC;

/**
 * @author CodeGeneratorUtil
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl extends AbstractService<Product> implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductService productService;
    @Resource
    private ProductAttributeExtendService productAttributeExtendService;
    @Resource
    private ViewProductdetailMapper viewProductdetailMapper;
    @Resource
    private ViewProductErpAttrListMapper erpAttrListMapper;
    @Resource
    private ViewProductErpListMapper erpListMapper;
    @Resource
    private ViewProductEmaxAttrListMapper emaxAttrListMapper;
    @Resource
    private ViewProductEmaxSizeListMapper emaxSizeListMapper;
    @Resource
    private ProductCategoryMapper productCategoryMapper;
    @Resource
    private ProductExtendAttributeDefinedService productExtendAttributeDefinedService;
    @Resource
    private ProductExtendAttributeItemService productExtendAttributeItemService;
    @Resource
    private ViewProductSubCategoryListMapper viewProductSubCategoryListMapper;
    @Resource
    private ViewSkuListService viewSkuListService;

    @Override
    public Boolean checkProductCode(String productCode) {
        String rule = "^([A-Z]{3}[A-Z0-9]{2}\\d{2}[A-Z0-9]{7})";
        Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(productCode);
        return matcher.matches();
    }

    @Override
    @LogAction(logDesc = "更新同步状态")
    public int updateSynProduct(String lastUpdateBy, Date lastUpdateDate, String[] products) {
        if (products == null || products.length == 0) {
            return 0;
        } else {
            return productMapper.updateSynProduct(lastUpdateBy, lastUpdateDate, products);
        }
    }

    @Override
    public List<String> selectProductCode(List<Product> product) {
        return productMapper.selectProductCode(product);
    }

    @Override
    public int insertProductAll(List<Product> product) {
        return productMapper.insertProductAll(product);
    }

    @Override
    public int updateProductPart(List<Product> product) {
        return productMapper.updateProductPart(product);
    }

    @Override
    public int updateEditProduct(Product product) {
        return productMapper.updateEditProduct(product);
    }

    @Override
    public int updateDesc(List<ViewProductDescription> viewProductDescriptions) {
        return productMapper.updateDesc(viewProductDescriptions);
    }

    @Override
    public int updateDescOne(String productCode, String desc, String lastUpdatedBy, Date lastUpdateDate) {
        return productMapper.updateDescOne(productCode, desc, lastUpdatedBy, lastUpdateDate);
    }

    @Override
    public Product selectProduct(String productCode) {
        return productMapper.selectProduct(productCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "批量新增款式")
    public int styleInsert(ProductExcelVO productExcelVO,List<String> productBaseCodeList) {
        int count = 0;
        try{
            List<Product> products = productExcelVO.getProductList();
            List<ProductAttributeExtend> productAttributeExtends = productExcelVO.getProductAttributeExtendList();

            if (products == null || products.size() == 0) {
                return -1;
            }
            if (productAttributeExtends == null || productAttributeExtends.size() == 0) {
                return -1;
            }
            List<List<ProductAttributeExtend>> paelist = ListUtil.splitArrayList(productAttributeExtends, 50);
            List<List<Product>> prolist = ListUtil.splitArrayList(products, 50);
            for (int i = 0; i < prolist.size(); i++) {
                //产品
                List<String> productList = productService.selectProductCode(prolist.get(i));
                List<Product> productinsert = prolist.get(i).stream().filter(p -> !productList.contains(p.getProductCode())).collect(Collectors.toList());
                //产品属性
                List<ProductAttributeExtend> productattributeinsert = paelist.get(i).stream().filter(p -> !productList.contains(p.getProductCode())).collect(Collectors.toList());
                if (productinsert != null && productinsert.size() != 0) {
                    productService.insertProductAll(productinsert);
                    productAttributeExtendService.insertAttributeExtendList(productattributeinsert);
                    count += productinsert.size();
                    //更新产品详情字段
                    List<ViewProductDescription> viewProductDescriptions = getProductDesc(productinsert,productattributeinsert);
                    if(!ListUtil.isBlank(viewProductDescriptions)){
                        productService.updateDesc(viewProductDescriptions);
                    }
                }
            }
           //竖表
            productService.saveOrUpdateProductAttribute(productExcelVO.getProductAttributeExtendList(),productBaseCodeList);
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.getLogger(SyncHelper.class).error(StringUtil.gsFormat("异常信息：", e.getMessage()));
            return count;
        }
        return count;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "批量更新款式")
    public int styleUpdate(ProductExcelVO productExcelVO,List<String> productBaseCodeList) {
        try{
            List<Product> products = productExcelVO.getProductList();
            List<String> productCodeList = new ArrayList<>();
            List<ProductAttributeExtend> productAttributeExtends = productExcelVO.getProductAttributeExtendList();
            if (products == null || products.size() == 0) {
                return 0;
            }
            if (productAttributeExtends == null || productAttributeExtends.size() == 0) {
                return 0;
            }
            List<List<Product>> pro = ListUtil.splitArrayList(products, 50);
            List<List<ProductAttributeExtend>> list = ListUtil.splitArrayList(productAttributeExtends, 50);
            if (pro == null || pro.size() == 0) {
                return 0;
            }
            for (int i = 0; i < pro.size(); i++) {
                //基本表
                List<Product> productList = pro.get(i);
                if(!ListUtil.isBlank(productList)){
                    for(Product product : productList){
                        product.setLastUpdatedBy(SessionManager.getLoginName());
                        product.setLastUpdateDate(DateUtil.now());
                    }
                    productService.updateProductPart(productList);
                    //批量更新条码的标准价格
                    productMapper.batchUpdateStandardPrice(productList);
                    //扩展表
                    productAttributeExtendService.updateAttributePart(list.get(i));
                }
            }
            //竖表
            productService.saveOrUpdateProductAttribute(productExcelVO.getProductAttributeExtendList(),productBaseCodeList);
            if(!ListUtil.isBlank(products)){
                for(Product product : products){
                    productCodeList.add(product.getProductCode());
                }
            }
            //更新产品详情
            if(!ListUtil.isBlank(productCodeList)){
                List<ViewProductDescription> viewProductDescriptions = productMapper.getProductDescriptions(productCodeList);
                if(!ListUtil.isBlank(viewProductDescriptions)){
                    productService.updateDesc(viewProductDescriptions);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage());
            return 0;
        }
        return 1;
    }


    private  List<ViewProductDescription>  getProductDesc(List<Product> productList,List<ProductAttributeExtend> productattributeinsert){
        List<ViewProductDescription> descriptionList = new ArrayList<>();
        if(!ListUtil.isBlank(productattributeinsert)){
            for(ProductAttributeExtend productAttributeExtend : productattributeinsert){
                ViewProductDescription viewProductDescription = new ViewProductDescription();
                String productCode = productAttributeExtend.getProductCode();
                Product product = productList.stream().filter(a->a.getProductCode().equals(productCode)).findFirst().orElse(null);
                if(product!=null){
                    String productDesc = productAttributeExtend.getWave()+"/"+productAttributeExtend.getIcicleBand()+"/"+productAttributeExtend.getIcicleGroup()+"/"+
                            productAttributeExtend.getModelNo()+"/"+productAttributeExtend.getMaterialNo()+"/"+productAttributeExtend.getColorCardNo()+"/"+
                            product.getProductName()+"/"+productAttributeExtend.getColourSystem()+"/"+productAttributeExtend.getColorName();
                    viewProductDescription.setProductCode(productCode);
                    viewProductDescription.setDescs(productDesc);
                    descriptionList.add(viewProductDescription);
                }
            }
        }
        return descriptionList;
    }
    @Override
    public int searchSynRecord(String productcode) {
//        验证是否已经同步
        Condition synstatus = new Condition(Product.class);
        synstatus.createCriteria().andEqualTo("syncRecord", true)
                .andEqualTo("productCode", productcode);
        return super.findCountByCondition(synstatus);
    }


    @Override
    public List<ViewProductdetail> getDetails(List<String> codes) {
        List<ViewProductdetail> list;
        try {
            list = viewProductdetailMapper.getDetails(codes);
        } catch (Exception ex) {
            list = null;
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
        }
        return list;
    }

    @Override
    public List<ViewProductErpList> getERPProductList(List<String> codes) {
        List<ViewProductErpList> list;
        try {
            list = erpListMapper.getProductList(codes);
        } catch (Exception ex) {
            list = null;
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
        }
        return list;
    }

    @Override
    public List<ViewProductErpAttrList> getERPAttrList(List<String> codes) {
        List<ViewProductErpAttrList> list;
        try {
            list = erpAttrListMapper.getAttrList(codes);
        } catch (Exception ex) {
            list = null;
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
        }
        return list;
    }

    @Override
    public List<ViewProductEmaxAttrList> getEmaxAttrList(List<String> codes) {
        List<ViewProductEmaxAttrList> list;
        try {
            list = emaxAttrListMapper.getEmaxAttrList(codes);
        } catch (Exception ex) {
            list = null;
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
        }
        return list;
    }

    @Override
    public List<ViewProductEmaxSizeList> getSizeList(List<String> codes) {
        List<ViewProductEmaxSizeList> list;
        try {
            list = emaxSizeListMapper.getSizeList(codes);
        } catch (Exception ex) {
            list = null;
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
        }
        return list;
    }

    @Override
    public SyncSKU getSyncSKUInfo(List<String> codes) {
        SyncSKU syncInfo = new SyncSKU();
        syncInfo.setCacheList(getDetails(codes));
        syncInfo.setErpList(getERPProductList(codes));
        syncInfo.setErpAttrList(getERPAttrList(codes));
        syncInfo.setEmaxAttrList(getEmaxAttrList(codes));
        syncInfo.setSizeList(getSizeList(codes));
        return syncInfo;
    }



    @Override
    public List<Map<String, Object>> getExportData(Integer syncStatus,Integer syncRecord,String line, String productClass, Integer year,
                                                   String season, String devSeason, String wave,String band,String icicleGroup, String workGroup) {
        return viewProductdetailMapper.exportContent(syncStatus,syncRecord,line, productClass,year,
                season, devSeason, wave,band,icicleGroup, workGroup);
    }

    @Override
    public ProductAttributeItemsVO getAttributeDefinedItemList(String productCode,String productCategoryCode) {
        try {
            String tableName = productCategoryMapper.getProductCategoryCode(productCategoryCode);
            Map<String,String> valueMap = productMapper.getExtendTableValue(productCode,tableName);
            List<String> attributeDefinedList = productMapper.getAttributeDefinedList(tableName.toLowerCase());
            List<String> list = Arrays.asList("product_code","product_id","created_by","creation_date","last_updated_by","last_update_date");
            attributeDefinedList.removeAll(list);
            List<ProductExtendAttributeDefined> productAttributeDefinedList = productExtendAttributeDefinedService.findAll();
            List<ProductAttributeDefinedVO> productAttributeDefinedVOList = new ArrayList<>();
            if(productAttributeDefinedList!=null && !productAttributeDefinedList.isEmpty()){
                List<ProductAttributeDefinedVO> productAttributeDefinedVOsList = PojoConvertUtil.convertPojoList(productAttributeDefinedList, ProductAttributeDefinedVO.class);
                for(String defKey : attributeDefinedList){
                    for(ProductAttributeDefinedVO productAttributeDefinedVOs : productAttributeDefinedVOsList){
                        if(defKey.equals(productAttributeDefinedVOs.getDefCode())){
                            productAttributeDefinedVOList.add(productAttributeDefinedVOs);
                        }
                    }
                }
            }
            if(!productAttributeDefinedVOList.isEmpty()){
                for(ProductAttributeDefinedVO productAttributeDefinedVO : productAttributeDefinedVOList){
                    if(!StringUtils.isEmpty(productAttributeDefinedVO.getSelectTable())){
                        List<Map<String,String>> viewSelectList = productMapper.getViewSelectTable(productAttributeDefinedVO.getSelectTable());
                        productAttributeDefinedVO.setSelectList(viewSelectList);
                    }
                }
            }
            List<String> keyList = new ArrayList<>();
            List<String> valueList = new ArrayList<>();
            Set<Map.Entry<String,String>> entrySet = valueMap.entrySet();
            for(Map.Entry<String,String> entry : entrySet){
                String key = entry.getKey();
                if(!list.contains(key)){
                    keyList.add(key);
                    valueList.add(entry.getValue());
                }
            }
            for(String key :keyList){
                for(ProductAttributeDefinedVO productAttributeDefinedVO : productAttributeDefinedVOList){
                    if(key.equals(productAttributeDefinedVO.getDefCode())){
                        productAttributeDefinedVO.setValue(valueMap.get(key));
                    }
                }
            }
            List<ProductExtendAttributeItem> productAttributeItemList = productExtendAttributeItemService.findAll();
            List<ProductAttributeItemVO> productAttributeItemsVOList = new ArrayList<>();
            if(productAttributeItemList!=null && !productAttributeItemList.isEmpty()){
                List<ProductAttributeItemVO> productAttributeItemsVOsList = PojoConvertUtil.convertPojoList(productAttributeItemList, ProductAttributeItemVO.class);
                for(ProductAttributeDefinedVO productAttributeDefinedVO : productAttributeDefinedVOList){
                    for(ProductAttributeItemVO productAttributeItemVO : productAttributeItemsVOsList){
                        if(productAttributeDefinedVO.getId().equals(productAttributeItemVO.getDefId())){
                            productAttributeItemsVOList.add(productAttributeItemVO);
                        }
                    }
                }
            }
            ProductAttributeItemsVO productAttributeItemsVO = new ProductAttributeItemsVO();
            productAttributeItemsVO.setProductAttributeDefinedVOList(productAttributeDefinedVOList);
            productAttributeItemsVO.setProductAttributeItemsVOList(productAttributeItemsVOList);
            return productAttributeItemsVO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "单条更新款式")
    public int alterProductCatogoryValue(ProductAttributeItemsVO productAttributeItemsVO) {
        int count = 0;
        try{
            //更新基本属性
            Product product = productAttributeItemsVO.getProduct();
            ProductVO productVO = PojoConvertUtil.convertPojo(product,ProductVO.class);
            if(productVO!=null){
                productVO.setStatus(product.getStatus()==true?1:0);
                productVO.setLastUpdatedBy(SessionManager.getLoginName());
                productVO.setLastUpdateDate(DateUtil.now());
                productMapper.updateProduct(productVO);
            }
            //更新扩展表
            List<Map<String,String>> productAttrList = new ArrayList<>();
            String productCategoryCode = productCategoryMapper.getProductCategoryCode(productAttributeItemsVO.getProductCategoryCode());
            List<ProductAttributeCodeValueVO> productCategoryAttr = productAttributeItemsVO.getProductCategoryAttr();
            for(ProductAttributeCodeValueVO productAttributeCodeValueVO : productCategoryAttr){
                Map<String,String> alterMap = new HashMap<>();
                alterMap.put("attr_code",productAttributeCodeValueVO.getDefCode());
                alterMap.put("attr_value",productAttributeCodeValueVO.getValue());
                alterMap.put("product_code",productAttributeItemsVO.getProductCode());
                alterMap.put("productCategoryCode",productCategoryCode);
                if(!StringUtils.isEmpty(productAttributeCodeValueVO.getValue())){
                    productAttrList.add(alterMap);
                }
                count = productCategoryMapper.alterColumnValue(alterMap);
            }
            //更新竖表
            productCategoryMapper.batchUpdateProductAttributeList(productAttrList);
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage());
            return count;
        }
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveOrUpdateProductAttribute(List<ProductAttributeExtend> productAttributeExtendList,List<String> productBaseCodeList) {
        if(!ListUtil.isBlank(productAttributeExtendList)){
            try{
                List<Map<String,String>> mapList = getProductExtendAttributeMapList(productAttributeExtendList);
                List<Map<String,String>> attributeInsertList = mapList.stream().filter(a->!productBaseCodeList.contains(a.get("product_code"))).collect(Collectors.toList());
                List<Map<String,String>> attributeUpdateList = mapList.stream().filter(a->productBaseCodeList.contains(a.get("product_code")) && !StringUtils.isEmpty(a.get("attr_value"))).collect(Collectors.toList());

                //竖表新增
                if(!ListUtil.isBlank(attributeInsertList)){
                    int pageSize = 200;
                    int total = attributeInsertList.size();
                    int maxPageNum =  total % pageSize == 0 ? total / pageSize : total/ pageSize + 1;
                    for(int i=0;i<maxPageNum;i++) {
                        int startIndex = i * pageSize;
                        int endIndex = i + 1 == maxPageNum ? attributeInsertList.size() : startIndex + pageSize;
                        List<Map<String, String>> subList = attributeInsertList.subList(startIndex, endIndex);
                        if (subList != null && !subList.isEmpty()) {
                            if(!ListUtil.isBlank(subList)){
                                productCategoryMapper.batchInsertProductAttributeList(subList);
                            }

                        }
                    }
                }

                //竖表更新
                if(!ListUtil.isBlank(attributeUpdateList)){
                    int pageSize = 200;
                    int total = attributeUpdateList.size();
                    int maxPageNum =  total % pageSize == 0 ? total / pageSize : total/ pageSize + 1;
                    for(int i=0;i<maxPageNum;i++) {
                        int startIndex = i * pageSize;
                        int endIndex = i + 1 == maxPageNum ? attributeUpdateList.size() : startIndex + pageSize;
                        List<Map<String, String>> subList = attributeUpdateList.subList(startIndex, endIndex);
                        if (subList != null && !subList.isEmpty()) {
                            if(!ListUtil.isBlank(subList)){
                                productCategoryMapper.batchUpdateProductAttributeList(subList);
                            }
                        }
                    }
                }
                return 1;
            }catch (Exception e){
                e.printStackTrace();
                LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                return 0;
            }
        }
        return  0;
    }

    @Override
    public List<String> getBrandNameList() {
        return productMapper.getBrandNameList();
    }

    @Override
    public List<Map<String, String>> getCodeAndCategoryList() {
        return productMapper.getCodeAndCategoryList();
    }

    @Override
    public List<SyncPropertyVO> getPropertyList() {
        return productMapper.getPropertyList();
    }

    @Override
    public List<ColorCardVO> findByQueryCondition(String colorCardNameOrNo) {
        return productMapper.findByQueryCondition(colorCardNameOrNo);
    }

    @Override
    public Workbook exportColorCardExcel(List<ColorCardVO> list) {
        return ExcelUtil.getColorCardWorkbook(list);
    }

    @Override
    public List<Map<String, String>> getProductCodeAndKey() {
        return productMapper.getProductCodeAndKey();
    }

    @Override
    public List<Map<String, String>> getProductCodeLine() {
        return productMapper.getProductCodeLine();
    }

    @Override
    public List<SelectItemVO> findItemDataByQueryCondition(String itemCategory,String itemSelect) {
        return productMapper.findItemDataByQueryCondition(itemCategory,itemSelect);
    }

    @Override
    public Workbook exportItemDataExcel(List<SelectItemVO> list) {
        return ExcelUtil.getItemDataWorkbook(list);
    }



    @Override
    public String generateNum(String numKey)
    {
        Map<String,String> param = new HashMap(4);
        param.put("numkey",numKey);
        param.put("datecheck","0");
        param.put("len","11");
        productMapper.generateNum(param);
        return param.get("num");
    }

    @Override
    public List<Map<String, String>> getItemList() {
        return productMapper.getItemList();
    }

    @Override
    public List<Map<String, Object>> getProductSubCategory() {
        return productMapper.getProductSubCategory();
    }

    @Override
    public Workbook getCostumeWorkbook() {
        List<String> costumeCategoryLevel1List = productMapper.selectCostumeCategoryLevel1();
        List<String> costumeCategoryLevel2List = productMapper.selectCostumeCategoryLevel2();
        List<Map<String,String>> selectItemList = productMapper.selectItemList();
        List<Object> selectList = new ArrayList<>();
        selectList.add(costumeCategoryLevel1List);
        selectList.add(costumeCategoryLevel2List);
        selectList.add(selectItemList);
        OutputStream out = null;
        Workbook workbook = null;
        try {
            workbook = ExcelUtil.getCostumeWorkbook(selectList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }

    @Override
    public List<String> selectUomList() {
        return productMapper.selectUomList();
    }

    @Override
    public List<Map<String,String>> selectBatchList() {
        return productMapper.selectBatchList();
    }

    @Override
    public List<Map<String,Object>> selectSize() {
        return productMapper.selectSize();
    }

    @Override
    public List<SyncProductInfoVO> getMainProduct(List<String> productCodeList) {
        return productMapper.getMainProduct(productCodeList);
    }

    @Override
    public List<SelectItemVO> selectItemVoList() {
        return productMapper.selectItemVoList();
    }

    @Override
    public List<SyncSkuVO> getMainProductInfo(List<Integer> ids) {
        List<SyncSkuVO> list = new ArrayList<>();
        int pageSize = 2000;
        int total = ids.size();
        int maxPageNum =  total % pageSize == 0 ? total / pageSize : total/ pageSize + 1;
        for(int i=0;i<maxPageNum;i++) {
            int startIndex = i * pageSize;
            int endIndex = i + 1 == maxPageNum ? ids.size() : startIndex + pageSize;
            List<Integer> subList = ids.subList(startIndex, endIndex);
            if(!ListUtil.isBlank(subList)){
                List<SyncSkuVO> partailList = productMapper.getMainProductInfo(subList);
                if(!ListUtil.isBlank(partailList)){
                    list.addAll(partailList);
                }
            }
        }
        return list;
    }

    @Override
    public List<Map<String, String>> selectProductCodeSizeGroup() {
        return productMapper.selectProductCodeSizeGroup();
    }

    @Override
    public List<Map<String, String>> getSizeGroupList() {
        return productMapper.getSizeGroupList();
    }

    @Override
    public List<String> selectBaseProductCodes() {
        return productMapper.selectBaseProductCodes();
    }

    @Override
    public List<String> getProductMaterialCodeList() {
        return productMapper.getProductMaterialCodeList();
    }

    @Override
    public List<SyncSkuVO> getProductCategorySkus(List<SyncSkuVO> mainProductInfoList, List<String> materialProductCodeList) {
        List<SyncSkuVO> syncSkuVOList = new ArrayList<>();
        List<Integer> costumeIdList = new ArrayList<>();
        List<Integer> materialIdList = new ArrayList<>();
        List<Integer> categoryIdList = new ArrayList<>();
        if(!ListUtil.isBlank(mainProductInfoList)){
            for(SyncSkuVO syncSkuVO : mainProductInfoList){
                if(syncSkuVO.getProduct_category_code().equals(CommonConstant.COSTUMECATEGORYKEY)){
                    if(!materialProductCodeList.contains(syncSkuVO.getSku())){
                        costumeIdList.add(syncSkuVO.getId());
                    }else{
                        materialIdList.add(syncSkuVO.getId());
                    }
                }else{
                    if(syncSkuVO.getProduct_category_code().equals(CommonConstant.MATERIALCATEGORYKEY)){
                        materialIdList.add(syncSkuVO.getId());
                    }else{
                        categoryIdList.add(syncSkuVO.getId());
                    }
                }
            }
        }
        if(!ListUtil.isBlank(costumeIdList)){
            List<SyncSkuVO> costumeSyncSkuVOList = new ArrayList<>();
            int pageSize = 2000;
            int total = costumeIdList.size();
            int maxPageNum =  total % pageSize == 0 ? total / pageSize : total/ pageSize + 1;
            for(int i=0;i<maxPageNum;i++) {
                int startIndex = i * pageSize;
                int endIndex = i + 1 == maxPageNum ? costumeIdList.size() : startIndex + pageSize;
                List<Integer> subList = costumeIdList.subList(startIndex, endIndex);
                if(!ListUtil.isBlank(subList)){
                    List<SyncSkuVO> partialList = productMapper.getCostumeSyncSkuVOList(subList);
                    if(!ListUtil.isBlank(partialList)){
                        costumeSyncSkuVOList.addAll(partialList);
                    }
                }
            }
            syncSkuVOList.addAll(costumeSyncSkuVOList);
        }
        if(!ListUtil.isBlank(materialIdList)){
            List<SyncSkuVO> materialSyncSkuVOList = new ArrayList<>();
            int pageSize = 2000;
            int total = materialIdList.size();
            int maxPageNum =  total % pageSize == 0 ? total / pageSize : total/ pageSize + 1;
            for(int i=0;i<maxPageNum;i++) {
                int startIndex = i * pageSize;
                int endIndex = i + 1 == maxPageNum ? materialIdList.size() : startIndex + pageSize;
                List<Integer> subList = materialIdList.subList(startIndex, endIndex);
                if(!ListUtil.isBlank(subList)){
                    List<SyncSkuVO> partialList = productMapper.getMaterailSyncSkuVOList(subList);
                    if(!ListUtil.isBlank(partialList)){
                        materialSyncSkuVOList.addAll(partialList);
                    }
                }
            }
            syncSkuVOList.addAll(materialSyncSkuVOList);
        }
        if(!ListUtil.isBlank(categoryIdList)){
            List<SyncSkuVO> categorySyncSkuVOList = new ArrayList<>();
            int pageSize = 2000;
            int total = categoryIdList.size();
            int maxPageNum =  total % pageSize == 0 ? total / pageSize : total/ pageSize + 1;
            for(int i=0;i<maxPageNum;i++) {
                int startIndex = i * pageSize;
                int endIndex = i + 1 == maxPageNum ? categoryIdList.size() : startIndex + pageSize;
                List<Integer> subList = categoryIdList.subList(startIndex, endIndex);
                if(!ListUtil.isBlank(subList)){
                    List<SyncSkuVO> partialList = productMapper.getCategorySyncSkuVOList(subList);
                    if(!ListUtil.isBlank(partialList)){
                        categorySyncSkuVOList.addAll(partialList);
                    }
                }
            }
            syncSkuVOList.addAll(categorySyncSkuVOList);
        }
        return syncSkuVOList;
    }

    private List<Map<String,String>> getProductExtendAttributeMapList(List<ProductAttributeExtend> productAttributeExtendList) {
        List<Map<String, String>> mapList = new ArrayList<>();
        if (!ListUtil.isBlank(productAttributeExtendList)) {
            for (ProductAttributeExtend productAttributeExtend : productAttributeExtendList) {
                String productCode = productAttributeExtend.getProductCode();
                Field[] fields = ProductAttributeExtend.class.getDeclaredFields();
                String workGroup = StringUtils.isEmpty(productAttributeExtend.getWorkGroup()) ? "" : productMapper.selectWorkGroup(productAttributeExtend.getWorkGroup());
                Map<String, String> maps = new HashMap<>();
                maps.put("product_code", productCode);
                maps.put("attr_code", "00003");
                maps.put("attr_value", StringUtils.isEmpty(workGroup) ? "" : workGroup);
                mapList.add(maps);
                for (Field field : fields) {
                    String fieldName = field.getName();
                    if (fieldName.equals("productCode")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00001");
                        map.put("attr_value", productAttributeExtend.getProductCode());
                        mapList.add(map);
                    }
                    if (fieldName.equals("securityCode")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "02068");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getSecurityCode()) ? "" : productAttributeExtend.getSecurityCode());
                        mapList.add(map);
                    }
                    if (fieldName.equals("estimatedRate")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "02069");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getEstimatedRate()) ? "" : productAttributeExtend.getEstimatedRate());
                        mapList.add(map);
                    }
                    if (fieldName.equals("orderNo")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00026");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getOrderNo()) ? "" : productAttributeExtend.getOrderNo());
                        mapList.add(map);
                    }
                    if (fieldName.equals("devNo")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00014");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getDevNo()) ? "" : productAttributeExtend.getDevNo());
                        mapList.add(map);
                    }
                    if (fieldName.equals("styleNo")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00015");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getStyleNo()) ? "" : productAttributeExtend.getStyleNo());
                        mapList.add(map);
                    }
                    if (fieldName.equals("modelNo")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00016");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getModelNo()) ? "" : productAttributeExtend.getModelNo());
                        mapList.add(map);
                    }
                    if (fieldName.equals("line")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00010");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getLine()) ? "" : productAttributeExtend.getLine());
                        mapList.add(map);
                    }
                    if (fieldName.equals("materialNo")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00017");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getMaterialNo()) ? "" : productAttributeExtend.getMaterialNo());
                        mapList.add(map);
                    }
                    if (fieldName.equals("materialName")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "material_name");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getMaterialName()) ? "" : productAttributeExtend.getMaterialName());
                        mapList.add(map);
                    }
                    if (fieldName.equals("colorName")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00027");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getColorName()) ? "" : productAttributeExtend.getColorName());
                        mapList.add(map);
                    }
                    if (fieldName.equals("productClass")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00013");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getProductClass()) ? "" : productAttributeExtend.getProductClass());
                        mapList.add(map);
                    }
                    if (fieldName.equals("year")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00011");
                        map.put("attr_value", productAttributeExtend.getYear() == null ? "" : String.valueOf(productAttributeExtend.getYear()));
                        mapList.add(map);
                    }
                    if (fieldName.equals("natureSeason")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00012");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getNatureSeason()) ? "" : productAttributeExtend.getNatureSeason());
                        mapList.add(map);
                    }
                    if (fieldName.equals("devSeason")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00019");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getDevSeason()) ? "" : productAttributeExtend.getDevSeason());
                        mapList.add(map);
                    }
                    if (fieldName.equals("wave")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00021");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getWave()) ? "" : productAttributeExtend.getWave());
                        mapList.add(map);
                    }
                    if (fieldName.equals("icicleBand")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00022");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getIcicleBand()) ? "" : productAttributeExtend.getIcicleBand());
                        mapList.add(map);
                    }
                    if (fieldName.equals("icicleGroup")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00023");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getIcicleGroup()) ? "" : productAttributeExtend.getIcicleGroup());
                        mapList.add(map);
                    }
                    if (fieldName.equals("salesDate")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00007");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getSalesDate()) ? "" : productAttributeExtend.getSalesDate());
                        mapList.add(map);
                    }

                    if (fieldName.equals("colorCardNo")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00024");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getColorCardNo()) ? "" : productAttributeExtend.getColorCardNo());
                        mapList.add(map);
                    }
                    if (fieldName.equals("materialNameTag")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "material_name_tag");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getMaterialNameTag()) ? "" : productAttributeExtend.getMaterialNameTag());
                        mapList.add(map);
                    }
                    if (fieldName.equals("colorCardName")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "color_card_name");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getColorCardName()) ? "" : productAttributeExtend.getColorCardName());
                        mapList.add(map);
                    }
                    if (fieldName.equals("colourSystem")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00025");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getColourSystem()) ? "" : productAttributeExtend.getColourSystem());
                        mapList.add(map);
                    }
                    if (fieldName.equals("sizeGroup")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00008");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getSizeGroup()) ? "" : productAttributeExtend.getSizeGroup());
                        mapList.add(map);
                    }
                    if (fieldName.equals("code")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00018");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getCode()) ? "" : productAttributeExtend.getCode());
                        mapList.add(map);
                    }
                    if (fieldName.equals("standard")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00020");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getStandard()) ? "" : productAttributeExtend.getStandard());
                        mapList.add(map);
                    }
                    if (fieldName.equals("batch")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00028");
                        map.put("attr_value", productAttributeExtend.getBatch() == null ? "" : productAttributeExtend.getBatch().toString());
                        mapList.add(map);
                    }
                    if (fieldName.equals("styleRule")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00029");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getStyleRule()) ? "" : productAttributeExtend.getStyleRule());
                        mapList.add(map);
                    }
                    if (fieldName.equals("supplier")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00009");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getSupplier()) ? "" : productAttributeExtend.getSupplier());
                        mapList.add(map);
                    }
                    if (fieldName.equals("opr")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00005");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getOpr()) ? "" : productAttributeExtend.getOpr());
                        mapList.add(map);
                    }
                    if (fieldName.equals("opDate")) {
                        Map<String, String> map = new HashMap<>();
                        map.put("product_code", productAttributeExtend.getProductCode());
                        map.put("attr_code", "00006");
                        map.put("attr_value", StringUtils.isEmpty(productAttributeExtend.getOpDate()) ? "" : productAttributeExtend.getOpDate());
                        mapList.add(map);
                    }
                }
            }

        }
        return mapList;
    }

    @Override
    public List<ProductAttribute> getextendProduct(List<String> categoryCodeList) {
        return productMapper.getextendProduct(categoryCodeList);
    }

    @Override
    public List<SyncMaterialContrainVO> getProductMaterialCodeListByCode(List<String> productCodeList) {
        List<SyncMaterialContrainVO> syncMaterialContrainVOList = new ArrayList<>();
        List<Map<String,String>> mapList = productMapper.getProductMaterialCodeListByCode(productCodeList);
        if(!ListUtil.isBlank(productCodeList)){
            for(String productCode : productCodeList){
                List<Map<String,String>> filterMapList = mapList.stream().filter(a->a.get("product_code").equals(productCode)).collect(Collectors.toList());
                if(!ListUtil.isBlank(filterMapList)){
                    SyncMaterialContrainVO syncMaterialContrainVO = new SyncMaterialContrainVO();
                    syncMaterialContrainVO.setProduct_code(productCode);
                    List<Map<String,String>> details = new ArrayList<>();
                    for(Map<String,String> map :filterMapList){
                        Map<String,String> eleMap = new HashMap<>(2);
                        eleMap.put("store_class",map.get("store_class"));
                        eleMap.put("store_type_id",map.get("store_type_id"));
                        details.add(eleMap);
                    }
                    syncMaterialContrainVO.setDetails(details);
                    syncMaterialContrainVOList.add(syncMaterialContrainVO);
                }
            }
        }
        return syncMaterialContrainVOList;
    }

    @Override
    public List<ViewSelectItemVO> getProductcategoryLevel2List(String categoryLevel1Code, String productCategoryCode) {
        return productMapper.getProductcategoryLevel2List(categoryLevel1Code,productCategoryCode);
    }

    @Override
    public List<Map<String, String>> getExtendAttributeItemList() {
        return productMapper.getExtendAttributeItemList();
    }

    @Override
    public List<Map<String,String>> getColorsByProductCode(List<String> productCodeList) {
        return productMapper.getColorsByProductCode(productCodeList);
    }

    @Override
    public DataTableRecord listSku(String sEcho, Integer pageIndex, Integer pageSize,String batchSku, String productCodeOrSku, String startTime, String endTime, Integer sortCol, String sortDir) {
        sortDir = PageUtil.orderMethod(sortDir);
        List<ViewSkuList> productInfoList = null;
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        try {
            Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
            PageHelper.startPage(pageNum, pageSize);
            Condition condition = new Condition(ViewSkuList.class);
            Example.Criteria criteria = condition.createCriteria();
            Example.Criteria criteria1 = condition.createCriteria();
            Example.Criteria criteria2 = condition.createCriteria();
            Example.Criteria criteria3 = condition.createCriteria();
            Example.Criteria criteria4 = condition.createCriteria();
            String [] skuArray = batchSku.split(" ");
            if (!StringUtils.isBlank(productCodeOrSku)) {
                String words = StringUtil.gsFormat("%{0}%", productCodeOrSku);
                condition.and(criteria.andLike("productCode", words).orLike("sku", words));
            }
            if (!StringUtils.isBlank(startTime)) {
                condition.and(criteria1.andGreaterThanOrEqualTo("lastUpdateDate", startTime));
            }
            if (!StringUtils.isBlank(endTime)) {
                condition.and(criteria2.andLessThanOrEqualTo("lastUpdateDate", endTime));
            }
            if(skuArray.length>1){
                condition.and(criteria3.andIn("sku", Arrays.asList(skuArray)));
            }
            if (ASC.equals(sortDir)) {
                condition.orderBy("lastUpdateDate").asc();
            } else {
                condition.orderBy("lastUpdateDate").desc();
            }
            condition.and(criteria4.andEqualTo("status",1));
            productInfoList = viewSkuListService.findByCondition(condition);
            if (productInfoList != null) {
                PageInfo pageInfo = new PageInfo(productInfoList);
                dataTableRecord.setITotalRecords(pageSize);
                dataTableRecord.setITotalDisplayRecords((int) pageInfo.getTotal());
                dataTableRecord.setAaData(pageInfo.getList());
                return dataTableRecord;
            } else {
                dataTableRecord.setAaData(new ArrayList<>());
                return dataTableRecord;
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            dataTableRecord.setAaData(new ArrayList<>());
            return dataTableRecord;
        }

    }

    @Override
    public DataTableRecord getProductSubCategoryList(String sEcho, Integer pageIndex, Integer pageSize,
                                                     String productSubCategoryCodeOrName, String productCategoryKey,
                                                     String lanCode,Integer subCategoryLevel) {
        List<ViewProductSubCategoryList> productSubCategoryList = null;
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        try {
            Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
            PageHelper.startPage(pageNum, pageSize);
            productSubCategoryList = viewProductSubCategoryListMapper.getProductSubCategeryList(productSubCategoryCodeOrName,productCategoryKey,lanCode,subCategoryLevel);
            if (productSubCategoryList != null) {
                PageInfo pageInfo = new PageInfo(productSubCategoryList);
                dataTableRecord.setITotalRecords(pageSize);
                dataTableRecord.setITotalDisplayRecords((int) pageInfo.getTotal());
                dataTableRecord.setAaData(pageInfo.getList());
                return dataTableRecord;
            } else {
                dataTableRecord.setAaData(new ArrayList<>());
                return dataTableRecord;
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            dataTableRecord.setAaData(new ArrayList<>());
            return dataTableRecord;
        }
    }

    @Override
    public List<Map<String, String>> getBatchList() {
        return productMapper.getBatchList();
    }

    @Override
    public List<Map<String, String>> getSizeList(String productCode, String productCategoryCode) {
        List<Map<String,String>> sizeList = new ArrayList<>();
        if(!productCategoryCode.equals(CommonConstant.COSTUMECATEGORYKEY)){
            Map<String,String> map = new HashMap<>(1);
            map.put("size_code","FF");
            map.put("size_desc","均码");
            sizeList.add(map);
        }else{
            String sizeGroup = productMapper.getSizeGroupByProductCode(productCode);
            if(StringUtils.isEmpty(sizeGroup)){
                Map<String,String> map = new HashMap<>(1);
                map.put("size_code","0");
                map.put("size_desc","无可选尺码");
                sizeList.add(map);
            }else{
                List<Map<String,String>> sizeMapList = productMapper.getSizeList(sizeGroup);
                if(!ListUtil.isBlank(sizeMapList)){
                    sizeList = sizeMapList;
                }else{
                    Map<String,String> map = new HashMap<>(1);
                    map.put("size_code","0");
                    map.put("size_desc","无可选尺码");
                    sizeList.add(map);
                }
            }
        }
        return sizeList;
    }

    @Override
    public ProductInfo getProductInfoById(Integer id) {
        return productMapper.getProductInfoById(id);
    }

    @Override
    public int updateProductInfo(ProductInfoSkuVO productInfo) {
        return productMapper.updateProductInfo(productInfo);
    }

    @Override
    public Integer getexistsSkuCount(ProductInfoSkuVO productInfo) {
        return productMapper.getexistsSkuCount(productInfo);
    }

    @Override
    public List<String> getItemTableList() {
        return productMapper.getItemTableList();
    }

    @Override
    public List<SyncPropertyVO> getItemSyncList(String[] list) {
        return productMapper.getItemSyncList(list);
    }

    @Override
    public List<Map<String, String>> getProductCategoryList() {
        return productMapper.getProductCategoryList();
    }

    @Override
    public List<Integer> getProductSubCategoryLevelList() {
        return productMapper.getProductSubCategoryLevelList();
    }

    @Override
    public List<ViewProductSubCategoryList> getProductSubCategoryIdAndLevel(String[] itemArray) {
        return productMapper.getProductSubCategoryIdAndLevel(itemArray);
    }

    @Override
    public List<SyncProductSubCategoryVO> getSubCategoryLevel1List(List<Integer> list) {
        return productMapper.getSubCategoryLevel1List(list);
    }

    @Override
    public List<SyncProductSubCategoryVO> getSubCategoryLevel2List(List<Integer> list) {
        return productMapper.getSubCategoryLevel2List(list);
    }

    @Override
    public List<SyncProductSubCategoryVO> getSubCategoryLevel3List(List<Integer> list) {
        return productMapper.getSubCategoryLevel3List(list);
    }

    @Override
    public int insertProductSubCategory(ProductSubCategoryVO productSubCategoryVO) {
        return productMapper.insertProductSubCategory(productSubCategoryVO);
    }

    @Override
    public int checkProductSubCategory(ProductSubCategoryVO productSubCategoryVO) {
        return productMapper.checkProductSubCategory(productSubCategoryVO);
    }

    @Override
    public int checkPaSubcategoryCode(String lanCode,String categoryCode,String paSubCategoryCode,Integer paCategoryLevel){
        return productMapper.checkPaSubcategoryCode(lanCode,categoryCode,paSubCategoryCode,paCategoryLevel);
    }

    @Override
    public int checkProdutCheckMaterialKey(String subCategoryKey) {
        return productMapper.checkProdutCheckMaterialKey(subCategoryKey);
    }

    @Override
    public int updateProductSubCategory(ProductSubCategoryVO productSubCategoryVO) {
        return productMapper.updateProductSubCategory(productSubCategoryVO);
    }

    @Override
    public List<ProductVO> getProductCategoryCode(List<Integer> idList) {
        return productMapper.getProductCategoryCode(idList);
    }

    @Override
    public List<Map<String, Object>> getSyncCostumeProductList(List<String> list) {
        return productMapper.getSyncCostumeProductList(list);
    }

    @Override
    public List<Map<String, Object>> getSyncMaterialProductList(List<String> list) {
        return productMapper.getSyncMaterialProductList(list);
    }

    @Override
    public List<Map<String, Object>> getSyncCategoryProductList(List<String> list) {
        return productMapper.getSyncCategoryProductList(list);
    }

    @Override
    public List<ProductVO> getProductCodeAndKeyList(List<String> list) {
        return productMapper.getProductCodeAndKeyList(list);
    }

    @Override
    public List<String> getBookCodeList(List<String> list) {
        return productMapper.getBookCodeList(list);
    }

    @Override
    public List<String> getHomeholdCodeList(List<String> list) {
        return productMapper.getHomeholdCodeList(list);
    }

    @Override
    public List<String> getMaterialCodeList(List<String> list) {
        return productMapper.getMaterialCodeList(list);
    }

    @Override
    public List<String> getCostumeCodeList(List<String> list) {
        return productMapper.getCostumeCodeList(list);
    }

    @Override
    public int batchDeleteSku(String[] list) {
        return productMapper.batchDeleteSku(list);
    }

    @Override
    public List<ProductInfoVO> selectProductCodeAndSku() {
        return productMapper.selectProductCodeAndSku();
    }

}