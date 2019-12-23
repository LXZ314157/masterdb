package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.config.SynConfigEntity;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.dao.masterdb.DynamicDataMapper;
import com.icicle.masterdb.dao.masterdb.ProductAttributeDefinedMapper;
import com.icicle.masterdb.dao.masterdb.ProductCategoryMapper;
import com.icicle.masterdb.dao.masterdb.ProductMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.*;
import com.icicle.masterdb.pojo.bo.DynamicColumn;
import com.icicle.masterdb.pojo.vo.ProductAttrDefCodeVO;
import com.icicle.masterdb.pojo.vo.ProductCategoryVO;
import com.icicle.masterdb.pojo.vo.ProductInfoVO;
import com.icicle.masterdb.service.*;
import com.icicle.masterdb.service.constant.CommonConstant;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.*;
import com.icicle.masterdb.web.ProductController;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author  CodeGeneratorUtil
 * @version 2019-03-11 18:56:49.
 */
@Service
public class ProductCategoryServiceImpl extends AbstractService<ProductCategory> implements ProductCategoryService {

    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private DynamicDataMapper dynamicDataMapper;

    @Resource
    private ProductAttributeDefinedMapper productAttributeDefinedMapper;

    @Resource
    private ProductCategoryAttributeService productCategoryAttributeService;
    @Resource
    private SelectBrandService selectBrandService;

    @Resource
    private CatalogService catalogService;

    @Resource
    private ProductService productService;

    @Resource
    private SynConfigEntity synConfigEntity;

    @Resource
    private ViewProductCostumeService viewProductCostumeService;





    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "添加商品种类")
    public int saveProductCategory(ProductCategory productCategory) {
        String tableName = productCategory.getProductCategoryCode();
        if (StringUtil.checkInjection(tableName)) {
            return 0;
        }
        int count = productCategoryMapper.selectTable(productCategory.getProductCategoryCode());
        if(count==0){
            productCategoryMapper.saveProductCategory(productCategory);
            //加表
            List<DynamicColumn> columns = new ArrayList<>();
            dynamicDataMapper.createTable(tableName, columns);
        }
        return 1;
    }

    @Override
    public List<ProductCategory> findAllAttributeDefined() {
        return productCategoryMapper.findAllAttributeDefied();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "关联产品类别属性")
    public int connectAttribute(ProductCategoryVO productCategoryVO) {
        String tableName = productCategoryVO.getProductCategoryCode();
        if (StringUtil.checkInjection(tableName)) {
            return 0;
        }
        List<ProductCategoryAttribute> list = productCategoryVO.getProductCategoryAttributeList();
        if (!ListUtil.isBlank(list)) {
            productCategoryAttributeService.save(getForInsert(list, 1));
            //添加列
            List<ProductAttributeDefined> defDetailList = productAttributeDefinedMapper.searchByAttributeIds(list);
            if (!ListUtil.isBlank(defDetailList)) {
                for (ProductAttributeDefined item : defDetailList) {
                    DynamicColumn column = new DynamicColumn();
                    column.setColumnName(item.getDefCode());
                    column.setDataLength(item.getDefLength());
                    column.setDataType(item.getDefType());
                    dynamicDataMapper.alterTableAddColumn(tableName, column);
                }
                return 1;
            } else {
                //无法动态添加列
                return -1;
            }
        } else {
            //关联属性为空
            return -2;
        }
    }


    @Override
    @LogAction(logDesc = "更新产品类别")
    public int updateProductCategory(ProductCategory productCategory) {
        if (productCategory == null) {
            return 0;
        }
        productCategory.setLastUpdateDate(DateUtil.now());
        productCategory.setLastUpdatedBy(SessionManager.getLoginName());
        try {
            return productCategoryMapper.updateProductCategory(productCategory);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "批量新增/更新产品类别")
    public int saveExcelData(List<Map<String, Object>> mapList, String productCategoryCode) {
        int resultCount = 0;
        try{
            List<String> productBaseCodeList = productMapper.selectBaseProductCodes();
            List<String> productExtendCodeList = productMapper.selectExtendCodes(productCategoryCode);
            if(mapList!=null && !mapList.isEmpty()){
                List<Map<String,Object>> baseProductMapList = new ArrayList<>();
                List<Map<String,String>> storeTypeClassMapList = new ArrayList<>();
                List<Map<String,String>> productAttrMapList = new ArrayList<>();

                //扩展字段--获取扩展字段key
                List<String> excludeList = new ArrayList<>();
                List<String> extendKeyList = new ArrayList<>();
                excludeList.addAll(ExcelUtil.getBaseKeyList());
                excludeList.addAll(ExcelUtil.getStoreTypeClassKeyList());
                excludeList.add("product_category_code");
                excludeList.add("last_updated_by");
                excludeList.add("sale_tax_rate");
                excludeList.add("created_by");
                excludeList.add("cate_dl");
                mapList.get(0).forEach((k,v)->{
                    if(!excludeList.contains(k)){
                        extendKeyList.add(k);
                    }
                });
                extendKeyList.add("product_code");
                for(Map<String,Object> map : mapList){
                    String categoryKey = map.get("cate_dl").toString();
                    Map<String,Object> baseProductMap = new HashMap<>();
                    String loginName = SessionManager.getLoginName();
                    map.put("created_by",loginName);
                    map.put("last_updated_by",loginName);
                    map.put("product_category_code",categoryKey);
                    String productCode = map.get("product_code").toString();
                    List<String> storeTypeClassList =  ExcelUtil.getStoreTypeClassKeyList();
                    map.forEach( (k,v)->{

                        //获取基本表数据
                        List<String> baseKeyList = ExcelUtil.getBaseKeyList();
                        baseKeyList.add("sale_tax_rate");
                        baseKeyList.add("created_by");
                        baseKeyList.add("last_updated_by");

                        if(baseKeyList.contains(k)){
                            baseProductMap.put(k,v.toString());
                        }

                        if(productCategoryCode.equals("product_material")){
                            //获取物料数据
                            if(storeTypeClassList.contains(k)){
                                if(map.get(k).equals("✔")){
                                    Map<String,String> storeTypeClassMap = new HashMap<>();
                                    String [] resultArray = getTypeClass(k);
                                    if(resultArray.length>0){
                                        storeTypeClassMap.put("store_class",resultArray[0]);
                                        storeTypeClassMap.put("store_type_id",resultArray[1]);
                                    }
                                    storeTypeClassMap.put("product_code",productCode);
                                    storeTypeClassMapList.add(storeTypeClassMap);
                                }
                            }
                        }

                        //获取竖表所需数据
                        if(extendKeyList.contains(k)){
                            if(v!=null){
                                Map<String,String> productAttrMap = new HashMap<>();
                                productAttrMap.put("product_code",productCode);
                                productAttrMap.put("attr_code",k);
                                productAttrMap.put("attr_value",v.toString());
                                productAttrMapList.add(productAttrMap);
                            }
                        }
                    });
                    baseProductMap.put("product_category_code",categoryKey);
                    baseProductMap.put("last_update_date",DateUtil.now());
                    baseProductMapList.add(baseProductMap);
                }

                //基本字段
                List<Map<String,Object>> baseInsertFilterMapList = baseProductMapList.stream().filter(a->!productBaseCodeList.contains(a.get("product_code"))).collect(Collectors.toList());
                List<Map<String,Object>> baseUpdateFilterMapList = baseProductMapList.stream().filter(a->productBaseCodeList.contains(a.get("product_code"))).collect(Collectors.toList());

                List<String> baseDeleteProductCodeList = new ArrayList<>();
                for(Map<String,Object> map : baseProductMapList){
                    if(productBaseCodeList.contains(map.get("product_code").toString())){
                        baseDeleteProductCodeList.add(map.get("product_code").toString());
                    }
                }

                //基本表批量新增
                if(!ListUtil.isBlank(baseInsertFilterMapList)){
                    int pageSize = 100;
                    int total = baseInsertFilterMapList.size();
                    int maxPageNum =  total % pageSize == 0 ? total / pageSize : total/ pageSize + 1;
                    for(int i=0;i<maxPageNum;i++) {
                        int startIndex = i * pageSize;
                        int endIndex = i + 1 == maxPageNum ? baseInsertFilterMapList.size() : startIndex + pageSize;
                        List<Map<String,Object>> subList = baseInsertFilterMapList.subList(startIndex, endIndex);
                        if (subList != null && !subList.isEmpty()) {
                            int insertcount= productMapper.batchInsertPrimarykey(subList);
                            if(insertcount!=0){
                                resultCount+=subList.size();
                            }
                        }
                    }
                }
                //基本表批量更新
                if(!ListUtil.isBlank(baseUpdateFilterMapList)){
                    int pageSize = 100;
                    int total = baseUpdateFilterMapList.size();
                    int maxPageNum =  total % pageSize == 0 ? total / pageSize : total/ pageSize + 1;
                    for(int i=0;i<maxPageNum;i++) {
                        int startIndex = i * pageSize;
                        int endIndex = i + 1 == maxPageNum ? baseUpdateFilterMapList.size() : startIndex + pageSize;
                        List<Map<String, Object>> subList = baseUpdateFilterMapList.subList(startIndex, endIndex);
                        if (subList != null && !subList.isEmpty()) {
                            productMapper.batchUpdatePrimarykey(subList);
                            resultCount+=subList.size();
                        }
                    }
                }

                //获取所有product code-id
                List<Map<String,String>> productBaseCodeIdsList = productMapper.selectBaseProductCodesAndIds();

                //获取扩展表的insert数据集合
                List<Map<String,Object>> extendInsertFilterMapList = mapList.stream().filter(a->!productExtendCodeList.contains(a.get("product_code"))).collect(Collectors.toList());
                //获取扩展表的update数据集合
                List<Map<String,Object>> extendUpdateFilterMapList = mapList.stream().filter(a->productExtendCodeList.contains(a.get("product_code"))).collect(Collectors.toList());


                //扩展表新增
                if(!ListUtil.isBlank(extendInsertFilterMapList) && !ListUtil.isBlank(extendKeyList)){
                    if(!ListUtil.isBlank(extendInsertFilterMapList)) {
                        extendKeyList.add("created_by");
                        extendKeyList.add("last_updated_by");
                        extendKeyList.add("product_id");
                        List<List<String>> extendInsertValuesList = new ArrayList<>();
                        for (Map<String, Object> extendMap : extendInsertFilterMapList) {
                            List<Map<String, String>> extendFilterMapList = productBaseCodeIdsList.stream().filter(a -> a.get("product_code").equals(extendMap.get("product_code"))).collect(Collectors.toList());
                            if (!ListUtil.isBlank(extendFilterMapList)) {
                                extendMap.put("product_id", extendFilterMapList.get(0).get("product_id"));
                            }
                            List<String> extendItemValueList = new ArrayList<>();
                            for (String extendKey : extendKeyList) {
                                extendMap.forEach((k, v) -> {
                                    if (extendKey.equals(k)) {
                                        extendItemValueList.add(v.toString());
                                    }
                                });
                            }
                            extendInsertValuesList.add(extendItemValueList);
                        }

                        int pageSize = 500;
                        int total = extendInsertValuesList.size();
                        int maxPageNum =  total % pageSize == 0 ? total / pageSize : total/ pageSize + 1;
                        for (int i = 0; i < maxPageNum; i++) {
                            int startIndex = i * pageSize;
                            int endIndex = i + 1 == maxPageNum ? extendInsertValuesList.size() : startIndex + pageSize;
                            List<List<String>> subList = extendInsertValuesList.subList(startIndex, endIndex);
                            if (subList != null && !subList.isEmpty()) {
                                productCategoryMapper.batchInsertProductExtendAttributeList(extendKeyList, subList, productCategoryCode);
                            }
                        }
                    }
                }
                //扩展表更新
                if(!ListUtil.isBlank(extendUpdateFilterMapList) && !ListUtil.isBlank(extendKeyList)){
                    extendKeyList.add("last_updated_by");
                    List<Map<String,Object>> extendUpdateValuesMapList = new ArrayList<>();
                    for(Map<String,Object> extendMap : extendUpdateFilterMapList){
                        extendMap.put("last_updated_by",SessionManager.getLoginName());
                        Map<String,Object> extendItemValueMap = new HashMap<>();
                        for(String extendKey : extendKeyList){
                            extendMap.forEach((k,v)->{
                                if(extendKey.equals(k)){
                                    extendItemValueMap.put(k,v);
                                }
                            });
                        }
                        extendUpdateValuesMapList.add(extendItemValueMap);
                    }
                    for(Map<String,Object> updateMap : extendUpdateValuesMapList){
                        String productCode = updateMap.get("product_code").toString();
                        if(!org.springframework.util.StringUtils.isEmpty(productCode)){
                            productCategoryMapper.updateProductExtendAttributeList(updateMap,productCategoryCode,productCode);
                        }
                    }
                }

                //竖表
                List<Map<String,String>> attributeInsertList = productAttrMapList.stream().filter(a->!productBaseCodeList.contains(a.get("product_code"))).collect(Collectors.toList());
                List<Map<String,String>> attributeUpdateList = productAttrMapList.stream().filter(a->productBaseCodeList.contains(a.get("product_code")) && !StringUtils.isEmpty(a.get("attr_value"))).collect(Collectors.toList());

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
                //插入/更新物料管控表
                if(productCategoryCode.equals("product_material")){
                    List<String> productIds = productCategoryMapper.selectMaterialContrainProductIds();
                    List<String> deleteIds = new ArrayList<>();
                    if(!ListUtil.isBlank(storeTypeClassMapList)){
                        for(Map<String,String> map : storeTypeClassMapList){
                            String productCode = map.get("product_code");
                            List<Map<String,String>> productBaseCodeIdsFilterList = productBaseCodeIdsList.stream().filter(a->a.get("product_code").equals(productCode)).collect(Collectors.toList());
                            if(!ListUtil.isBlank(productBaseCodeIdsFilterList)){
                                String productId = productBaseCodeIdsFilterList.get(0).get("product_id");
                                if(!StringUtils.isEmpty(productId)){
                                    map.put("product_id",productId);
                                    if(productIds.contains(productId)){
                                        deleteIds.add(productId);
                                    }
                                }
                            }
                        }
                        if(!ListUtil.isBlank(deleteIds)){
                            productCategoryMapper.batchDeleteMaterialContrain(deleteIds);
                        }

                        int pageSize = 300;
                        int total1 = storeTypeClassMapList.size();
                        int maxPageNum1 =  total1 % pageSize == 0 ? total1 / pageSize : total1/ pageSize + 1;
                        for(int i=0;i<maxPageNum1;i++) {
                            int startIndex = i * pageSize;
                            int endIndex = i + 1 == maxPageNum1 ? storeTypeClassMapList.size() : startIndex + pageSize;
                            List<Map<String, String>> subList = storeTypeClassMapList.subList(startIndex, endIndex);
                            if (subList != null && !subList.isEmpty()) {
                                productCategoryMapper.batchInsertMaterialContrain(subList);
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            resultCount = 0;
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        }
        return resultCount;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveOrUpdateProductInfoExcelData(List<Map<String,Object>> mapList) {
        int count = 0;
        try{
            if(!ListUtil.isBlank(mapList)){
                List<ProductInfoVO> insertList = new ArrayList<>();
                List<ProductInfoVO> updateList = new ArrayList<>();
                List<ProductInfoVO> productInfoVOList = productMapper.selectProductCodeAndSku();
                for(Map<String,Object> map : mapList){
                    String product_code = map.get("product_code")==null?"":map.get("product_code").toString();
                    String sku = map.get("sku")==null?"":map.get("sku").toString();
                    String standard_price = map.get("standard_price")==null?"":map.get("standard_price").toString();
                    String security_code = map.get("security_code")==null?"":map.get("security_code").toString();
                    String color = map.get("color")==null?"":map.get("color").toString();
                    String size = map.get("size")==null?"":map.get("size").toString();
                    String sale_cost = map.get("sale_cost")==null?"":map.get("sale_cost").toString();
                    String batch = map.get("batch")==null?"":map.get("batch").toString();
                    String standard_cost = map.get("standard_cost")==null?"":map.get("standard_cost").toString();
                    String status = map.get("status")==null?"":map.get("status").toString();
                    ProductInfoVO productInfo = new ProductInfoVO();
                    productInfo.setProduct_code(product_code);
                    productInfo.setSku(sku);
                    productInfo.setColor(StringUtils.isEmpty(color)?"":color);
                    productInfo.setSize(StringUtils.isEmpty(size)?"":size);
                    productInfo.setSecurity_code(StringUtils.isEmpty(security_code)?"":security_code);
                    productInfo.setBatch(StringUtils.isEmpty(batch)?"":batch);
                    productInfo.setStatus(StringUtils.isEmpty(status)?0:Integer.parseInt(status));
                    productInfo.setStandard_price(StringUtils.isEmpty(standard_price)?"0":standard_price);
                    productInfo.setSale_cost(StringUtils.isEmpty(sale_cost)?"0":sale_cost);
                    productInfo.setStandard_cost(StringUtils.isEmpty(standard_cost)?"":standard_cost);
                    productInfo.setCreated_by(SessionManager.getLoginName());
                    productInfo.setCreation_date(DateUtil.now());
                    productInfo.setLast_updated_by(SessionManager.getLoginName());
                    productInfo.setLast_update_date(DateUtil.now());
                    Integer exists = null;
                    List<ProductInfoVO> list = productInfoVOList.stream().filter(a->a.getProduct_code().equals(product_code) && a.getSku().equals(sku)).collect(Collectors.toList());
                    if(!ListUtil.isBlank(list)){
                        exists = list.get(0).getId();
                    }
                    if(exists!=null && exists>0){
                        updateList.add(productInfo);
                    }else{
                        insertList.add(productInfo);
                    }
                }
                if(!ListUtil.isBlank(insertList)){
                    int pageSize = 100;
                    int total = insertList.size();
                    int maxPageNum =  total % pageSize == 0 ? total / pageSize : total/ pageSize + 1;
                    for(int i=0;i<maxPageNum;i++) {
                        int startIndex = i * pageSize;
                        int endIndex = i + 1 == maxPageNum ? insertList.size() : startIndex + pageSize;
                        List<ProductInfoVO> subList = insertList.subList(startIndex, endIndex);
                        if(!ListUtil.isBlank(subList)){
                            int insertCount = productCategoryMapper.batchInsertProductInfo(subList);
                            if(insertCount>0){
                                count+=subList.size();
                            }
                        }
                    }
                }
                if(!ListUtil.isBlank(updateList)){
                    int pageSize = 100;
                    int total = updateList.size();
                    int maxPageNum =  total % pageSize == 0 ? total / pageSize : total/ pageSize + 1;
                    for(int i=0;i<maxPageNum;i++) {
                        int startIndex = i * pageSize;
                        int endIndex = i + 1 == maxPageNum ? updateList.size() : startIndex + pageSize;
                        List<ProductInfoVO> subList = updateList.subList(startIndex, endIndex);
                        if(!ListUtil.isBlank(subList)){
                            int updateCount = productCategoryMapper.batchUpdateProductInfo(subList);
                            if(updateCount>0){
                                count+=subList.size();
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return 0;
        }
        return count;
    }


    @Override
    public int getTableCulomnsCount(String tableName) {
        return  productCategoryMapper.getTableCulomnsCount(tableName);
    }

    @Override
    public List<Map<String, String>> getKeyList(String productCategoryCode) {
        List<Map<String,String>> dataList = new ArrayList<>();
        Map<String,String> keyMap = new HashMap<>();
        Map<String,String> productNameMap = new HashMap<>();
        List<String> codeList = new ArrayList<>();
        if(productCategoryCode.equals("product_costume")){
            dataList= ExcelUtil.getCostumeCodeMapList();
        }else{
            List<Map<String,String>> mapList = ExcelUtil.getTitleMapList();
            for(Map<String, String> map : mapList){
                dataList.add(map);
            }
            List<Map<String,String>> keyMapList = productCategoryMapper.getKeyList(productCategoryCode);
            if(keyMapList!=null && !keyMapList.isEmpty()){
                for(Map<String, String> map : keyMapList){
                    String key = map.get("def_code");
                    String value = map.get("def_name");
                    keyMap.put(key,value);
                    codeList.add(key);
                }
            }

            Set<String> keySet =  keyMap.keySet();
            for(String key : keySet){
                Map<String,String> map = new HashMap<>();
                map.put("data",key);
                map.put("title",keyMap.get(key));
                dataList.add(map);
            }

            String productName = productCategoryMapper.getProductName(productCategoryCode);
            productNameMap.put("productName",productName);
            dataList.add(productNameMap);
        }
        return dataList;
    }

    @Override
    public DataTableRecord getcategorytable(String sEcho, Integer pageIndex, Integer pageSize,String productCategoryCode,String sSearch) {
        int total = 0;
        List<Map<String,Object>> categoryValueMapLists = null;
        List<Map<String,String>> bakValueMapList = new ArrayList<>();
        List<String> extendAttrBaseList = ExcelUtil.getExtendAttrBaseList();
        try {
            if(productCategoryCode.equals("product_costume")){
                DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
                Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
                PageHelper.startPage(pageNum, pageSize);
                Condition condition = new Condition(ViewProductCostume.class);
                List<ViewProductCostume> viewProductmessageinfoList = viewProductCostumeService.findByCondition(condition);
                List<Catalog> categoryList = catalogService.findAll();
                if(!viewProductmessageinfoList.isEmpty()){
                    for(ViewProductCostume viewProductCostume : viewProductmessageinfoList){
                        String syncStatus = viewProductCostume.getSyncStatus();
                        viewProductCostume.setSyncStatus(syncStatus.equals("1")?"有更新":(syncStatus.equals("2")?"新增":"其它"));
                        String cateDl = viewProductCostume.getCateDl()==null?"":viewProductCostume.getCateDl();
                        String cateZl = viewProductCostume.getCateZl()==null?"":viewProductCostume.getCateZl();
                        String cateXl = viewProductCostume.getCateXl()==null?"":viewProductCostume.getCateXl();
                        for(Catalog catalog : categoryList){
                            if(cateDl.equals(catalog.getCatalogNo()) && catalog.getCatalogLevel()==1){
                                viewProductCostume.setCateDl(catalog.getCatalogDesc());
                            }
                            if(cateZl.equals(catalog.getCatalogNo()) && catalog.getCatalogLevel()==2 ){
                                viewProductCostume.setCateZl(catalog.getCatalogDesc());
                            }
                            if(cateXl.equals(catalog.getCatalogNo()) && catalog.getCatalogLevel()==3 ){
                                viewProductCostume.setCateXl(catalog.getCatalogDesc());
                            }
                        }
                    }
                }
                PageInfo<ViewProductCostume> pageInfo = new PageInfo<>(viewProductmessageinfoList);
                dataTableRecord.setITotalRecords(pageSize);
                dataTableRecord.setITotalDisplayRecords((int) pageInfo.getTotal());
                dataTableRecord.setAaData(viewProductmessageinfoList);
                return dataTableRecord;
            }else{
                List<String> codeList = ExcelUtil.getBaseKeyList();
                codeList.add("sync_status");
                List<Map<String,String>> keyMapList = productCategoryMapper.getKeyList(productCategoryCode);
                if(keyMapList!=null && !keyMapList.isEmpty()){
                    for(Map<String, String> map : keyMapList){
                        String key = map.get("def_code");
                        codeList.add(key);
                    }
                }

                categoryValueMapLists = productCategoryMapper.getSearchList(productCategoryCode,sSearch);

                //分页筛选数据
                total = categoryValueMapLists.size();
                if(total>0){

                    int maxPageNum =  total%pageSize==0?total/pageSize:total/pageSize + 1;
                    int pageNum = PageUtil.getPageNum(pageIndex,pageSize);
                    int startIndex = (pageNum-1)*pageSize;
                    int endIndex = pageIndex/pageSize+1==maxPageNum?total:startIndex+pageSize;
                    List<Map<String,Object>> categoryValueMapList = categoryValueMapLists.subList(startIndex,endIndex);
                    List<String> keyResultList = new ArrayList<>();
                    //productCodeList
                    List<String> baseProductCodeList = new ArrayList<>();
                    for(Map<String,Object> map : categoryValueMapList){
                        Iterator<String> it = map.keySet().iterator();
                        while(it.hasNext()){
                            String key = it.next();
                            if(key.equals("product_code")){
                                baseProductCodeList.add(map.get(key).toString());
                            }
                            if(!extendAttrBaseList.contains(key)){
                                if(!keyResultList.contains(key)){
                                    keyResultList.add(key);
                                }
                            }
                        }
                    }
                    List<Map<String,String>> tableNameList =  productCategoryMapper.getSelectTableName(productCategoryCode);
                    List<Map<String,Object>> itemMapList = productCategoryMapper.getHasItemTable();
                    List<Map<String,Object>> resultValueMapList = productCategoryMapper.getResultValue();
                    List<Map<String,Object>> itemKeysMapList = productCategoryMapper.getItemkeys();

                    //扩展字段表List
                    for(Map<String,Object> map : categoryValueMapList){
                        for(String key : keyResultList){
                            if(map.containsKey(key)){
                                int itemCode = 0;
                                String selectTable = "";
                                List<Map<String,Object>> itemFilterMapList = itemMapList.stream().filter(a->a.get("def_code").equals(key)).collect(Collectors.toList());
                                if(!ListUtil.isBlank(itemFilterMapList)){
                                    itemCode = itemFilterMapList.get(0).get("has_item")==null?0:Integer.parseInt(itemFilterMapList.get(0).get("has_item").toString());
                                    selectTable = itemFilterMapList.get(0).get("select_table")==null?"":itemFilterMapList.get(0).get("select_table").toString();
                                }
                                //单选非选项表
                                if(itemCode==1 && StringUtils.isEmpty(selectTable)){
                                    String value = map.get(key).toString();
                                    List<Map<String,Object>> valueFilterMapList = resultValueMapList.stream().filter(a->a.get("def_code").equals(key)).collect(Collectors.toList());
                                    if(!ListUtil.isBlank(valueFilterMapList)){
                                        for(Map<String,Object> map1 : valueFilterMapList){
                                            String value1 = map1.get("code").toString();
                                            if(value1.equals(value)){
                                                String resultValue = map1.get("name").toString();
                                                map.remove(key);
                                                map.put(key,resultValue);

                                            }
                                        }
                                    }
                                }else{
                                    //选项表
                                    if(!tableNameList.isEmpty()){
                                        for(Map<String,String> eleMap : tableNameList){
                                            if(eleMap.get("columnName").equals(key)){
                                                String tableName = eleMap.get("selectTable");
                                                String itemvalue = "";
                                                if(!ListUtil.isBlank(itemKeysMapList)){
                                                    List<Map<String,Object>> itemKeysFilterMapList = itemKeysMapList.stream().filter(a->a.get("table_name").equals(tableName)).collect(Collectors.toList());
                                                    if(!ListUtil.isBlank(itemKeysFilterMapList)){
                                                        for(Map<String,Object> map1 : itemKeysFilterMapList){
                                                            String value1 = map1.get("id").toString();
                                                            String value = map.get(key).toString();
                                                            if(value1.equals(value)){
                                                                itemvalue = map1.get("item_value").toString();
                                                                map.put(key,itemvalue);
                                                            }

                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    List<Map<String,Object>> allCategoryValueMapList = new ArrayList<>();
                    List<Map<String,String>> dzxMapList = productMapper.selectProductDzl();
                    List<SelectBrand>  selectBrandList = selectBrandService.findAll();

                    //基本字段表list
                    List<Map<String,String>> baseValueMapList = productMapper.findBaseProductValue(baseProductCodeList);
                    //遍历循环类别表集合，每个map添加基本属性（根据productCode关联）
                    for(Map<String,Object> map : categoryValueMapList){
                        //扩展字段productCode
                        String productCode = map.get("product_code").toString();
                        List<Map<String,String>> baseValueFilterMapList =baseValueMapList.stream().filter(a->a.get("product_code").equals(productCode)).collect(Collectors.toList());
                        if(!ListUtil.isBlank(baseValueFilterMapList)){
                            Map<String,String> baseValueMap = baseValueFilterMapList.get(0);
                            map.put("security_code",StringUtils.isEmpty(baseValueMap.get("security_code"))?"":baseValueMap.get("security_code"));
                            map.put("standard_cost",baseValueMap.get("standard_cost")==null?"":baseValueMap.get("standard_cost"));
                            String cateZl = StringUtils.isEmpty(baseValueMap.get("cate_zl"))?"":String.valueOf(baseValueMap.get("cate_zl"));
                            String cateXl = StringUtils.isEmpty(baseValueMap.get("cate_xl"))?"":String.valueOf(baseValueMap.get("cate_xl"));

                            List<Map<String,String>> fiterZlMapList = dzxMapList.stream().filter(a ->a.get("product_sub_category_code").equals(cateZl)).collect(Collectors.toList());
                            if(!ListUtil.isBlank(fiterZlMapList)){
                                map.put("cate_zl",fiterZlMapList.get(0).get("product_sub_category_name")==null?"":fiterZlMapList.get(0).get("product_sub_category_name"));
                            }
                            List<Map<String,String>> fiterXlMapList = dzxMapList.stream().filter(a ->a.get("product_sub_category_code").equals(cateXl)).collect(Collectors.toList());
                            if(!ListUtil.isBlank(fiterXlMapList)){
                                map.put("cate_xl",fiterXlMapList.get(0).get("product_sub_category_name")==null?"":fiterXlMapList.get(0).get("product_sub_category_name"));
                            }

                            String brand = StringUtils.isEmpty(baseValueMap.get("brand"))?"":baseValueMap.get("brand");
                            for(SelectBrand selectBrand : selectBrandList){
                                if(brand.equals(selectBrand.getItemKey())){
                                    brand = selectBrand.getItemValue();
                                }
                            }
                            map.put("brand",brand);
                            map.put("product_desc",StringUtils.isEmpty(baseValueMap.get("product_desc"))?"":baseValueMap.get("product_desc"));
                            String status = "";
                            Object o = baseValueMap.get("status");
                            if(baseValueMap.get("status")==null){
                                status = "不可用";
                            }else{
                                Boolean b = (Boolean)o;
                                if(b){
                                    status = "可用";
                                }else{
                                    status = "不可用";
                                }
                            }
                            map.put("status",status);
                            map.put("unit_price",baseValueMap.get("unit_price")==null?"":baseValueMap.get("unit_price"));
                            map.put("purchase_rate",baseValueMap.get("purchase_rate")==null?"":baseValueMap.get("purchase_rate"));
                            map.put("sale_rate",baseValueMap.get("sale_rate")==null?"":baseValueMap.get("sale_rate"));
                            String syn_status = "";
                            if(baseValueMap.get("sync_status")==null){
                                syn_status = "未同步";
                            }else{
                                Object obj =  baseValueMap.get("sync_status");
                                Integer in = (Integer)obj;
                                if(in==1){
                                    syn_status = "已同步";
                                }else{
                                    syn_status = "未同步";
                                }
                            }
                            map.put("sync_status",syn_status);
                            map.put("product_name",StringUtils.isEmpty(baseValueMap.get("product_name"))?"":baseValueMap.get("product_name"));
                            map.put("uom",StringUtils.isEmpty(baseValueMap.get("uom"))?"":baseValueMap.get("uom"));
                            allCategoryValueMapList.add(map);
                        }
                    }
                    if(allCategoryValueMapList!=null && !allCategoryValueMapList.isEmpty()) {
                        for (Map<String, Object> map : allCategoryValueMapList) {
                            Map<String,String> bakMap = new HashMap<>();
                            for(String key : codeList){
                                bakMap.put(key,map.get(key)==null?"":map.get(key).toString());
                            }
                            bakValueMapList.add(bakMap);
                        }
                    }
                }
                DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
                PageInfo<Map<String, String>> pageInfo1 = new PageInfo<>(bakValueMapList);
                dataTableRecord.setITotalRecords(total);
                dataTableRecord.setITotalDisplayRecords(total);
                dataTableRecord.setAaData(pageInfo1.getList());
                return dataTableRecord;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.getLogger(ProductController.class).error(ex.getMessage());
            return null;
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Map<String, String>> getSelectTableName(String tableName) {
        return productCategoryMapper.getSelectTableName(tableName);
    }
    private List<ProductCategoryAttribute> getForInsert(List<ProductCategoryAttribute> list, Integer status) {
        for (ProductCategoryAttribute initem : list) {
            initem.setCreationDate(DateUtil.now());
            initem.setCreatedBy(SessionManager.getLoginName());
            initem.setCategoryAttributeState(status);
        }
        return list;
    }

    // 获取Excel表的真实行数--物料
    private int getExcelMaterialRealRow(Sheet sheet) {
        int count = 0;
        int lastRealNum = 0;
        Row firstRow = sheet.getRow(0);
        int columnCount = firstRow.getPhysicalNumberOfCells();
        int begin = sheet.getFirstRowNum();
        int end = sheet.getLastRowNum();
        for (int i = begin; i <= end; i++) {
            Row row = sheet.getRow(i);
            boolean b = false;
            for(int j=0;j<columnCount;j++){
                try{
                    Cell cell = row.getCell(j);
                    if(cell == null){
                        continue;
                    }else{
                        b = true;
                        lastRealNum = i;
                    }
                }catch (Exception e){
                    continue;
                }
            }
            if(b){
                count+=1;
            }
        }
        if(lastRealNum+1>count){
            return -1;
        }
        return count;
    }

    // 获取Excel表的真实行数--图书、家居
    private int getExcelProductRealRow(Sheet sheet) {
        int count = 0;
        int begin = sheet.getFirstRowNum();
        int end = sheet.getLastRowNum();
        int lastRealNum = 0;
        for (int i = begin; i <= end; i++) {
            Row row = sheet.getRow(i);
            boolean b = false;
            try{
                if(row.getCell(0)==null){
                    continue;
                }else{
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    String productCode = row.getCell(0).getStringCellValue();
                    if(!StringUtils.isEmpty(productCode)){
                        b = true;
                        lastRealNum = i;
                    }
                }
            }catch (Exception e){
                continue;
            }
            if(b){
                count+=1;
            }
        }
        if(lastRealNum+1>count){
            return -1;
        }
        return count;
    }

    @Override
    public Result parseExcel(String path, int tableCulumnsCount, String tableName, List<Map<String, String>> tableNameList) throws InvalidFormatException {
        OutputStream out = null;
        Result result = new Result();
        List<Map<String,Object>> mapList = new ArrayList<>();
        try {
            Workbook workBook = WorkbookFactory.create(new FileInputStream(new File(path)));
            Sheet sheet = workBook.getSheetAt(0);
            int rowNum = 0 ;
            if(!tableName.equals("product_material")){
                rowNum = getExcelProductRealRow(sheet)-1;
            }else{
                rowNum = getExcelMaterialRealRow(sheet)-1;
            }
            CellStyle cellStyle = workBook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

            if(rowNum<2) {
                result.setCode(202);
                result.setMessage("模板格式有误，请检查后再上传");
                return result;
            }
            Row firstRow = sheet.getRow(0);
            Row secondRow = sheet.getRow(1);
            Row thirdRow = sheet.getRow(2);
            Row fourthRow = sheet.getRow(3);
            if(firstRow==null || secondRow==null || thirdRow==null || fourthRow==null){
                result.setCode(202);
                result.setMessage("模板格式有误，请检查后再上传");
                return result;
            }
            int cloumnCount =firstRow.getPhysicalNumberOfCells();
            Cell cell11 = firstRow.getCell(0);
            Cell cell12 = firstRow.getCell(1);

            Cell cell21 = secondRow.getCell(0);
            Cell cell22 = secondRow.getCell(1);

            Cell cell31 = thirdRow.getCell(0);
            Cell cell32 = thirdRow.getCell(1);

            cell11.setCellType(Cell.CELL_TYPE_STRING);
            cell12.setCellType(Cell.CELL_TYPE_STRING);
            cell21.setCellType(Cell.CELL_TYPE_STRING);
            cell22.setCellType(Cell.CELL_TYPE_STRING);
            cell31.setCellType(Cell.CELL_TYPE_STRING);
            cell32.setCellType(Cell.CELL_TYPE_STRING);
            String cellValue11 = cell11==null?"":firstRow.getCell(0).getStringCellValue();
            String cellValue21 = cell21==null?"":secondRow.getCell(0).getStringCellValue();
            String cellValue31 = cell31==null?"":thirdRow.getCell(0).getStringCellValue();
            if(!cellValue11.equals("商品码/款号") ||!cellValue21.equals("product_code") ||!cellValue31.equals("varchar")  ){
                result.setCode(202);
                result.setMessage("模板格式有误，请检查后再上传");
                return result;
            }
            if(!tableName.equals("product_costume")){
                if(tableName.equals("product_material")){
                    if(cloumnCount< tableCulumnsCount+18){
                        result.setCode(202);
                        result.setMessage("模板格式有误，请检查后再上传");
                        return result;
                    }else{
                        cloumnCount =  tableCulumnsCount+18;
                    }
                }
                if(tableName.equals("product_homehold")){
                    if(cloumnCount< tableCulumnsCount+1){
                        result.setCode(202);
                        result.setMessage("模板格式有误，请检查后再上传");
                        return result;
                    }else{
                        cloumnCount =  tableCulumnsCount+1;
                    }
                }
                if(tableName.equals("product_book")){
                    if(cloumnCount< tableCulumnsCount+2){
                        result.setCode(202);
                        result.setMessage("模板格式有误，请检查后再上传");
                        return result;
                    }else{
                        cloumnCount =  tableCulumnsCount+2;
                    }
                }

            }
            StringBuffer sb = new StringBuffer();
            String message = "";
            List<String> codeList = ExcelUtil.getBaseKeyList();
            List<String> extendKeyList = new ArrayList<>();
            //获取各个要翻译的key的列数
            int cateZlColumnNum = 0;
            int cateXlColumnNum = 0;
            int bookFourthColumnNum = 0;
            int productNameColumnNum = 0;
            int brandColumnNum = 0;
            int uomColumnNum = 0;
            int unitPriceColumnNum = 0;
            int standardCostColumnNum = 0;
            for(int m=0;m<cloumnCount;m++){

                String title = secondRow.getCell(m).getStringCellValue();
                if(!codeList.contains(title)){
                    extendKeyList.add(title);
                }
                if(title.equals("cate_zl")){
                    cateZlColumnNum = m;
                }
                if(title.equals("cate_xl")){
                    cateXlColumnNum = m;
                }
                if(title.equals("category_level_third")){
                    bookFourthColumnNum = m;
                }
                if(title.equals("product_name")){
                    productNameColumnNum = m;
                }
                if(title.equals("brand")){
                    brandColumnNum = m;
                }

                if(title.equals("uom")){
                    uomColumnNum = m;
                }
                if(title.equals("unit_price")){
                    unitPriceColumnNum = m;
                }
                if(title.equals("standard_cost")){
                    standardCostColumnNum = m;
                }

            }
            //获取重复的product_code的行
            List<String> productCodeList = new ArrayList<>();
            for(int k = 3;k<=rowNum;k++){
                Row row = sheet.getRow(k);
                Cell productCodeCell = row.getCell(0);
                if(productCodeCell!=null){
                    productCodeCell.setCellType(Cell.CELL_TYPE_STRING);
                    productCodeList.add(productCodeCell.getStringCellValue());
                }
            }

            List<Integer> dumpLineNums = new ArrayList<>();
            if(!ListUtil.isBlank(productCodeList)){
                for(int l = 0;l<productCodeList.size();l++){
                    String ele = productCodeList.get(l);
                    if(!StringUtils.isEmpty(ele)){
                        int frecuency = Collections.frequency(productCodeList, ele);
                        if(frecuency>=2){
                            dumpLineNums.add(l+3);
                        }
                    }
                }
            }

            List<String> productList = productMapper.getProductList();
            List<Map<String,String>> brandKeyValueList = productMapper.getBrandKeyValue();
            List<Map<String,Object>> productLineList = productMapper.selectProductLine();
            //设置产品线
            String bookLineId = "";
            String homeholdLineId = "";
            String materialLineId = "";
            for(Map<String,Object> map :productLineList){
                String productLineName =  map.get("product_line_name").toString();
                String productLineNo = map.get("product_line_no").toString();
                if(productLineName.equals("图书")){
                    bookLineId = productLineNo;
                }
                if(productLineName.equals("家居")){
                    homeholdLineId = productLineNo;
                }
                if(productLineName.equals("物料")){
                    materialLineId = productLineNo;
                }
            }

            //翻译扩展字段
            List<ProductAttrDefCodeVO> productAttrDefCodeVOList = productMapper.getExtendAttrCode(extendKeyList);
            List<Map<String,Object>> tableValueLlist = productMapper.selectTableValues();
            List<Map<String,Object>> saleTaxRateList = productMapper.selectSaleTaxRate();
            List<String> hasItemList = new ArrayList<>();
            for(ProductAttrDefCodeVO productAttrDefCodeVO : productAttrDefCodeVOList){
                hasItemList.add(productAttrDefCodeVO.getDefCode());
            }
            for(int i=3;i<=rowNum;i++){
                Row valueRow = sheet.getRow(i);
                Map<String,Object> rowMap = new HashMap<>();
                Boolean b = new Boolean(true);
                Cell productCodeValueCell = valueRow.getCell(0);
                //标记productCode重复的行
                if (!ListUtil.isBlank(dumpLineNums)){
                    if(dumpLineNums.contains(i)){
                        b = false;
                        sb.append("["+(i+1)+","+1+"]、");
                        productCodeValueCell.setCellStyle(cellStyle);
                    }
                }

                if(productCodeValueCell==null){
                    productCodeValueCell = valueRow.createCell(0);
                    productCodeValueCell.setCellType(Cell.CELL_TYPE_STRING);
                }

                if(!tableName.equals("product_material")){
                    if(productCodeValueCell==null){
                        b = false;
                        sb.append("["+(i+1)+","+1+"]、");
                        productCodeValueCell.setCellStyle(cellStyle);
                    }else{
                        if(StringUtils.isEmpty(productCodeValueCell.getStringCellValue())){
                            b = false;
                            sb.append("["+(i+1)+","+1+"]、");
                            productCodeValueCell.setCellStyle(cellStyle);
                        }
                    }
                }

                //翻译大中小类、判断产品名称和品牌

                Cell cateZlCell = valueRow.getCell(cateZlColumnNum)==null?valueRow.createCell(cateZlColumnNum):valueRow.getCell(cateZlColumnNum);
                Cell cateXlCell = valueRow.getCell(cateXlColumnNum)==null?valueRow.createCell(cateXlColumnNum):valueRow.getCell(cateXlColumnNum);
                Cell bookFourthCell = valueRow.getCell(bookFourthColumnNum)==null?valueRow.createCell(bookFourthColumnNum):valueRow.getCell(bookFourthColumnNum);
                Cell productNameCell = valueRow.getCell(productNameColumnNum)==null?valueRow.createCell(productNameColumnNum):valueRow.getCell(productNameColumnNum);
                Cell brandCell = valueRow.getCell(brandColumnNum)==null?valueRow.createCell(brandColumnNum):valueRow.getCell(brandColumnNum);
                Cell uomCell = valueRow.getCell(uomColumnNum)==null?valueRow.createCell(uomColumnNum):valueRow.getCell(uomColumnNum);
                Cell unitPriceCell = valueRow.getCell(unitPriceColumnNum)==null?valueRow.createCell(unitPriceColumnNum):valueRow.getCell(unitPriceColumnNum);
                Cell standardCostCell = valueRow.getCell(standardCostColumnNum)==null?valueRow.createCell(standardCostColumnNum):valueRow.getCell(standardCostColumnNum);

                String cateZlCellValue = cateZlCell.getStringCellValue();
                String cateXlCellValue = cateXlCell.getStringCellValue();
                String bookFourthCellValue = bookFourthCell.getStringCellValue();
                String productNameCellValue = productNameCell.getStringCellValue();
                String brandCellValue = brandCell.getStringCellValue();
                String uomCellValue = uomCell.getStringCellValue();

                unitPriceCell.setCellType(Cell.CELL_TYPE_STRING);
                standardCostCell.setCellType(Cell.CELL_TYPE_STRING);
                String unitPriceCellValue = unitPriceCell.getStringCellValue();
                String standardCostCellValue = standardCostCell.getStringCellValue();
                String productCode = productCodeValueCell.getStringCellValue();
                if(!StringUtils.isEmpty(productCode) && !productList.contains(productCode)){//新增
                    if(StringUtils.isEmpty(productNameCellValue)){
                        b = false;
                        sb.append("["+(i+1)+","+(productNameColumnNum+1)+"]、");
                        productNameCell.setCellStyle(cellStyle);
                    }
                    if(StringUtils.isEmpty(brandCellValue)){
                        b = false;
                        sb.append("["+(i+1)+","+(brandColumnNum+1)+"]、");
                        brandCell.setCellStyle(cellStyle);
                    }
                    if(StringUtils.isEmpty(uomCellValue)){
                        b = false;
                        sb.append("["+(i+1)+","+(uomColumnNum+1)+"]、");
                        uomCell.setCellStyle(cellStyle);
                    }
                    if(StringUtils.isEmpty(unitPriceCellValue)){
                        b = false;
                        sb.append("["+(i+1)+","+(unitPriceColumnNum+1)+"]、");
                        unitPriceCell.setCellStyle(cellStyle);
                    }
                    if(StringUtils.isEmpty(standardCostCellValue)){
                        b = false;
                        sb.append("["+(i+1)+","+(standardCostColumnNum+1)+"]、");
                        standardCostCell.setCellStyle(cellStyle);
                    }
                }


                if(tableName.equals("product_material")){
                    rowMap.put("product_line",materialLineId);
                    if(!StringUtils.isEmpty(cateZlCellValue) && !StringUtils.isEmpty(cateXlCellValue)) {
                        Map<String, String> dzxMap = productCategoryMapper.findDzxlInfo(cateZlCellValue,cateXlCellValue);
                        if (dzxMap != null) {
                            cateZlCell.setCellValue(dzxMap.get("cateZl"));
                            cateXlCell.setCellValue(dzxMap.get("cateXl"));
                            rowMap.put("cate_dl",dzxMap.get("cateDl"));
                            rowMap.put("cate_zl",dzxMap.get("cateZl"));
                            rowMap.put("cate_xl",dzxMap.get("cateXl"));

                            Float saleTaxRate = 0.13f;
                            Map<String,Object> secondLevelMap = saleTaxRateList.stream().filter(a->a.get("category_code").toString().equals(CommonConstant.MATERIALCATEGORYKEY) && a.get("product_sub_category_code").toString().equals(dzxMap.get("cateXl")) && a.get("sub_category_level").toString().equals("2")).findFirst().orElse(null);
                            if(secondLevelMap.get("sale_tax_rate")!=null && !secondLevelMap.get("sale_tax_rate").toString().equals("") && !secondLevelMap.get("sale_tax_rate").toString().equals("0.0")){
                                saleTaxRate = Float.parseFloat(secondLevelMap.get("sale_tax_rate").toString());
                            }else{
                                Map<String,Object> firstLevelMap = saleTaxRateList.stream().filter(a->a.get("category_code").toString().equals(CommonConstant.MATERIALCATEGORYKEY) && a.get("product_sub_category_code").toString().equals(dzxMap.get("cateZl")) && a.get("sub_category_level").toString().equals("1")).findFirst().orElse(null);
                                if(firstLevelMap.get("sale_tax_rate")!=null && !firstLevelMap.get("sale_tax_rate").toString().equals("") && !firstLevelMap.get("sale_tax_rate").toString().equals("0.0")){
                                    saleTaxRate = Float.parseFloat(firstLevelMap.get("sale_tax_rate").toString());
                                }
                            }
                            rowMap.put("sale_tax_rate",saleTaxRate);
                            //确定product_code
                            String subCategoryKey = dzxMap.get("subCategoryKey");
                            if(productCodeValueCell==null){
                                productCodeValueCell = valueRow.createCell(0);
                                productCodeValueCell.setCellValue(Cell.CELL_TYPE_STRING);
                                String resultNum = productService.generateNum(subCategoryKey);
                                if(!StringUtils.isEmpty(resultNum)){
                                    productCodeValueCell.setCellValue(resultNum);
                                }
                            }else{
                                String cellValue = productCodeValueCell.getStringCellValue();
                                if(StringUtils.isEmpty(cellValue)){
                                    String resultNum = productService.generateNum(subCategoryKey);
                                    if(!StringUtils.isEmpty(resultNum)){
                                        productCodeValueCell.setCellValue(resultNum);
                                    }
                                }
                            }
                        } else {
                            b = false;
                            sb.append("["+(i+1)+","+(cateZlColumnNum+1)+"]、");
                            cateZlCell.setCellStyle(cellStyle);
                            sb.append("["+(i+1)+","+(cateXlColumnNum+1)+"]、");
                            cateXlCell.setCellStyle(cellStyle);
                        }
                    }else{
                        rowMap.put("cate_dl",CommonConstant.MATERIALCATEGORYKEY);
                    }
                }

                if(tableName.equals("product_homehold")){
                    rowMap.put("product_line",homeholdLineId);
                    if(!StringUtils.isEmpty(cateZlCellValue)) {
                        Map<String, String> dzMap = productCategoryMapper.findHomeholdDzxlInfo(cateZlCellValue);
                        if (dzMap != null) {
                            cateZlCell.setCellValue(dzMap.get("cateZl"));
                            rowMap.put("cate_dl",dzMap.get("cateDl"));
                            rowMap.put("cate_zl",dzMap.get("cateZl"));
                            rowMap.put("sale_tax_rate",dzMap.get("saleTaxRate"));
                        } else {
                            b = false;
                            sb.append("["+(i+1)+","+(cateZlColumnNum+1)+"]、");
                            cateZlCell.setCellStyle(cellStyle);
                        }
                    }else{
                        rowMap.put("cate_dl",CommonConstant.HOMEHOLDCATEGORYKEY);
                    }
                }

                if(tableName.equals("product_book")){
                    rowMap.put("product_line",bookLineId);
                    if(!StringUtils.isEmpty(cateZlCellValue) && !StringUtils.isEmpty(cateXlCellValue)) {
                        Map<String, String> dzxMap = productCategoryMapper.findBookDzxlInfo(cateXlCellValue,bookFourthCellValue);
                        if (dzxMap != null) {
                            cateZlCell.setCellValue(cateZlCellValue);
                            cateXlCell.setCellValue(cateXlCellValue);
                            rowMap.put("cate_dl",dzxMap.get("cateDl"));
                            rowMap.put("cate_zl",dzxMap.get("cateZl"));
                            rowMap.put("cate_xl",dzxMap.get("cateXl"));
                            rowMap.put("category_level_third",dzxMap.get("category_level_third"));
                            Float saleTaxRate = 0.13f;
                            Map<String,Object> thirdLevelMap = saleTaxRateList.stream().filter(a->a.get("category_code").toString().equals(CommonConstant.BOOKCATEGORYKEY) && a.get("product_sub_category_code").toString().equals(dzxMap.get("category_level_third")) && a.get("sub_category_level").toString().equals("3")).findFirst().orElse(null);
                            if(thirdLevelMap.get("sale_tax_rate")!=null && !thirdLevelMap.get("sale_tax_rate").toString().equals("") && !thirdLevelMap.get("sale_tax_rate").toString().equals("0.0")){
                                saleTaxRate = Float.parseFloat(thirdLevelMap.get("sale_tax_rate").toString());
                            }else{
                                Map<String,Object> secondLevelMap = saleTaxRateList.stream().filter(a->a.get("category_code").toString().equals(CommonConstant.BOOKCATEGORYKEY) && a.get("product_sub_category_code").toString().equals(dzxMap.get("cateXl")) && a.get("sub_category_level").toString().equals("2")).findFirst().orElse(null);
                                if(secondLevelMap.get("sale_tax_rate")!=null && !secondLevelMap.get("sale_tax_rate").toString().equals("") && !secondLevelMap.get("sale_tax_rate").toString().equals("0.0")){
                                    saleTaxRate = Float.parseFloat(secondLevelMap.get("sale_tax_rate").toString());
                                }else{
                                    Map<String,Object> firstLevelMap = saleTaxRateList.stream().filter(a->a.get("category_code").toString().equals(CommonConstant.BOOKCATEGORYKEY) && a.get("product_sub_category_code").toString().equals(dzxMap.get("cateZl")) && a.get("sub_category_level").toString().equals("1")).findFirst().orElse(null);
                                    if(firstLevelMap.get("sale_tax_rate")!=null && !firstLevelMap.get("sale_tax_rate").toString().equals("") && !firstLevelMap.get("sale_tax_rate").toString().equals("0.0")){
                                        saleTaxRate = Float.parseFloat(firstLevelMap.get("sale_tax_rate").toString());
                                    }
                                }
                            }
                            rowMap.put("sale_tax_rate",saleTaxRate);
                        } else {
                            b = false;
                            sb.append("["+(i+1)+","+(cateZlColumnNum+1)+"]、");
                            cateZlCell.setCellStyle(cellStyle);

                            sb.append("["+(i+1)+","+(bookFourthColumnNum+1)+"]、");
                            bookFourthCell.setCellStyle(cellStyle);
                        }
                    }else{
                        rowMap.put("cate_dl",CommonConstant.BOOKCATEGORYKEY);
                    }
                }


                for (int j = 0; j < cloumnCount; j++) {
                    Cell valueCell = valueRow.getCell(j);

                    if(valueCell==null){
                        valueCell = valueRow.createCell(j);
                    }
                    //取key和value
                    String key = secondRow.getCell(j).getStringCellValue();
                    String type = thirdRow.getCell(j).getStringCellValue();
                    String value = "";

                    if(type.equals("date")){
                        if (Cell.CELL_TYPE_NUMERIC == valueCell.getCellType() && HSSFDateUtil.isCellDateFormatted(valueCell)) {
                            value = DateUtil.getDateStringWithSeperate("-", valueCell.getDateCellValue());
                        }else{
                            valueCell.setCellType(Cell.CELL_TYPE_STRING);
                            if(!DateUtil.isValidDate(valueCell.getStringCellValue())){
                                b = false;
                                sb.append("["+(i+1)+","+(j+1)+"]、");
                                valueCell.setCellStyle(cellStyle);
                            }
                        }
                    } else {
                        valueCell.setCellType(Cell.CELL_TYPE_STRING);
                        value = valueCell.getStringCellValue();
                    }

                    //字段翻译判断--状态
                    if(key.equals("status")){
                        if(!StringUtils.isEmpty(value)){
                            String status = value.equals("可用")?"1":"0";
                            value= status;
                        }else{
                            value="0";
                        }
                    }
                    //字段翻译判断--品牌brandNameList
                    if(key.equals("brand")){
                        if(!StringUtils.isEmpty(value)){
                            String valueTemp = value;
                            List<Map<String,String>> brandKeyValueFilterList =brandKeyValueList.stream().filter(a->a.get("item_value").equals(valueTemp)).collect(Collectors.toList());
                            if(!ListUtil.isBlank(brandKeyValueFilterList)){
                                value = brandKeyValueFilterList.get(0).get("item_key");
                            }
                        }
                    }

                    if(type.equals("float") || type.equals("money")){
                        if(!StringUtils.isEmpty(value) && !NumberUtils.isNumber(value)){
                            b = false;
                            sb.append("["+(i+1)+","+(j+1)+"]、");
                            valueCell.setCellStyle(cellStyle);
                        }
                    }

                    //扩展字段翻译
                    String itemValue = value;
                    if(!hasItemList.contains(key)){
                        if(!tableNameList.isEmpty()){
                            for(Map<String,String> eleMap : tableNameList){
                                if(eleMap.get("columnName").equals(key)){
                                    String tableNames = eleMap.get("selectTable");
                                    List<Map<String,Object>> tableValueFilterLlist = tableValueLlist.stream().filter(a->a.get("tableName").toString().equals(tableNames)).collect(Collectors.toList());
                                    if(!ListUtil.isBlank(tableValueFilterLlist)){
                                        List<Map<String,Object>> filterList = tableValueFilterLlist.stream().filter(a -> a.get("itemValue").equals(itemValue)).collect(Collectors.toList());
                                        if(!filterList.isEmpty()){
                                            value= String.valueOf(filterList.get(0).get("id"));
                                        }else{
                                            b = false;
                                            sb.append("["+(i+1)+","+(j+1)+"]、");
                                            valueCell.setCellStyle(cellStyle);

                                        }
                                    }
                                }
                            }
                        }
                    }else{
                        String tempValue = value;
                        if(!StringUtils.isEmpty(tempValue)){
                            List<ProductAttrDefCodeVO> productAttrDefCodeVoList = productAttrDefCodeVOList.stream().filter(a->a.getDefCode().equals(key)
                                    && a.getName().equals(tempValue)).collect(Collectors.toList());
                            if(!ListUtil.isBlank(productAttrDefCodeVoList)){
                                value = productAttrDefCodeVoList.get(0).getCode();
                            }else{
                                b = false;
                                sb.append("["+(i+1)+","+(j+1)+"]、");
                                valueCell.setCellStyle(cellStyle);
                            }
                        }
                    }
                    if(!key.equals("cate_dl") && !key.equals("cate_zl") && !key.equals("cate_xl") && !key.equals("category_level_third") ){
                        rowMap.put(key,value);
                    }
                }
                if(b){
                    mapList.add(rowMap);
                    message = sb.toString().length()==0?"":sb.toString().substring(0,(sb.toString()).length()-1);
                }else{
                    productCodeValueCell.setCellStyle(cellStyle);
                    message = sb.toString().substring(0,(sb.toString()).length()-1);
                }
            }

            if(mapList.isEmpty()){
                result.setCode(201);
                result.setMessage("导入失败，请下载查看错误数据");
            }else{
                result.setCode(200);
                result.setMessage(message.isEmpty()?"":"坐标："+message+"数据格式错误");
                result.setData(mapList);
            }
            out =  new FileOutputStream(path);
            workBook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out!=null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    @Override
    public List<Map<String, String>> setProductCodeAndSku() {
        return productCategoryMapper.setProductCodeAndSku();
    }

    @Override
    public List<Map<String, String>> getProductCodeAndCategoryCodeList() {
        return productCategoryMapper.getProductCodeAndCategoryCodeList();
    }

    @Override
    public  Result parseProductInfoExcel(String path,List<Object> list) {
        OutputStream out = null;
        Result result = new Result();
        try{
            Workbook workbook = WorkbookFactory.create(new FileInputStream(path));
            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = getExcelProductRealRow(sheet)-1;
            if(rowNum<1) {
                result.setCode(202);
                result.setMessage("模板格式行数有误，请检查后再上传");
                return result;
            }
            Row firstRow = sheet.getRow(0);
            Row secondRow = sheet.getRow(1);
            if(firstRow==null || secondRow==null){
                result.setCode(202);
                result.setMessage("模板格式行数有误，请检查后再上传");
                return result;
            }
            int cloumnCount =firstRow.getPhysicalNumberOfCells();
            if(cloumnCount<10) {
                result.setCode(202);
                result.setMessage("模板格式列数有误，请检查后再上传");
                return result;
            }

            Cell cell11 = firstRow.getCell(0);
            Cell cell12 = firstRow.getCell(1);
            Cell cell13 = firstRow.getCell(2);
            Cell cell14 = firstRow.getCell(3);
            Cell cell15 = firstRow.getCell(4);
            Cell cell16 = firstRow.getCell(5);
            Cell cell17 = firstRow.getCell(6);
            Cell cell18 = firstRow.getCell(7);
            Cell cell19 = firstRow.getCell(8);
            Cell cell10 = firstRow.getCell(9);

            for(int i = 0; i< 10; i++){
                firstRow.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
            }

            String cellValue11 = cell11==null?"":cell11.getStringCellValue();
            String cellValue12 = cell12==null?"":cell12.getStringCellValue();
            String cellValue13 = cell13==null?"":cell13.getStringCellValue();
            String cellValue14 = cell14==null?"":cell14.getStringCellValue();
            String cellValue15 = cell15==null?"":cell15.getStringCellValue();
            String cellValue16 = cell16==null?"":cell16.getStringCellValue();
            String cellValue17 = cell17==null?"":cell17.getStringCellValue();
            String cellValue18 = cell18==null?"":cell18.getStringCellValue();
            String cellValue19 = cell19==null?"":cell19.getStringCellValue();
            String cellValue10 = cell10==null?"":cell10.getStringCellValue();
            if(
                    !cellValue11.equals("商品码/款号") || !cellValue12.equals("SKU") || !cellValue13.equals("颜色") ||
                            !cellValue14.equals("批次") || !cellValue15.equals("尺码") || !cellValue16.equals("吊牌价") ||
                            !cellValue17.equals("标准成本") || !cellValue18.equals("销售成本") || !cellValue19.equals("安全码") || !cellValue10.equals("状态")
                    ){
                result.setCode(202);
                result.setMessage("模板格式有误，请检查后再上传");
                return result;
            }
            List<Map<String,String>> productCodeAndCategoryCodeList = (List<Map<String,String>>)list.get(0);
            List<Map<String,String>> batchList = (List<Map<String,String>>)list.get(1);
            List<Map<String,Object>> sizeList = (List<Map<String,Object>>)list.get(2);
            List<Map<String,String>> productCodeSizeGroupList = (List<Map<String,String>>)list.get(3);

            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            StringBuffer sb = new StringBuffer();
            List<Map<String,Object>> mapList = new ArrayList<>();
            String message = "";

            //统计excel中的sku集合
            List<String> skuList = new ArrayList<>();
            for(int i=1;i<=rowNum;i++){
                Cell itemSkuCell = sheet.getRow(i).getCell(1);
                if(itemSkuCell!=null){
                    itemSkuCell.setCellType(Cell.CELL_TYPE_STRING);
                    skuList.add(itemSkuCell.getStringCellValue());
                }
            }

            List<ProductInfoVO> productInfoVOs =  productMapper.selectProductAndSku();

            for(int i=1;i<=rowNum;i++){
                Row valueRow = sheet.getRow(i);
                Map<String,Object> map = new HashMap<>();
                Boolean b = new Boolean(true);

                Cell productCodeValueCell = valueRow.getCell(0);
                Cell skuValueCell = valueRow.getCell(1);
                Cell colorValueCell = valueRow.getCell(2);
                Cell batchCell = valueRow.getCell(3);
                Cell sizeCell = valueRow.getCell(4);
                Cell standardPriceValueCell = valueRow.getCell(5);
                Cell standardCostValueCell = valueRow.getCell(6);
                Cell saleCostValueCell = valueRow.getCell(7);
                Cell securityCodeValueCell = valueRow.getCell(8);
                Cell statusValueCell = valueRow.getCell(9);

                String productCodeVlue = "";
                boolean isCostume = false;
                if(productCodeValueCell==null){
                    b = false;
                    sb.append("["+(i+1)+","+1+"]、");
                    productCodeValueCell = valueRow.createCell(0);
                    productCodeValueCell.setCellStyle(cellStyle);
                }else{
                    productCodeValueCell.setCellType(Cell.CELL_TYPE_STRING);
                    String productCode = productCodeValueCell.getStringCellValue();
                    //验证productCode是否存在
                    Map<String,String> filterMap = productCodeAndCategoryCodeList.stream().filter(a->a.get("product_code").equals(productCode)).findFirst().orElse(null);
                    if(filterMap == null){
                        b = false;
                        sb.append("["+(i+1)+","+1+"]、");
                        productCodeValueCell.setCellStyle(cellStyle);
                    }else{
                        String productCategoryCode = filterMap.get("product_category_code");
                        if(!org.springframework.util.StringUtils.isEmpty(productCategoryCode) && productCategoryCode.equals(CommonConstant.COSTUMECATEGORYKEY)){
                            isCostume = true;
                        }
                    }
                    map.put("product_code",productCode);
                    productCodeVlue = productCode;

                }

                if(skuValueCell==null){
                    b = false;
                    sb.append("["+(i+1)+","+2+"]、");
                    skuValueCell = valueRow.createCell(1);
                    skuValueCell.setCellStyle(cellStyle);
                }else{
                    skuValueCell.setCellType(Cell.CELL_TYPE_STRING);
                    String skuValue = skuValueCell.getStringCellValue();

                    String productCode = "";
                    List<ProductInfoVO> productInfoVOList = productInfoVOs.stream().filter(a->a.getSku().equals(skuValue)).collect(Collectors.toList());
                    if(!ListUtil.isBlank(productInfoVOList)){
                        productCode = productInfoVOList.get(0).getProduct_code();
                    }
                    if(!StringUtils.isEmpty(productCode)){
                        if(!productCode.equals(productCodeVlue)){
                            b = false;
                            sb.append("["+(i+1)+","+2+"]、");
                            skuValueCell.setCellStyle(cellStyle);
                        }
                    }
                    int frequency = Collections.frequency(skuList,skuValue);
                    if(frequency>=2){
                        b = false;
                        sb.append("["+(i+1)+","+2+"]、");
                        skuValueCell.setCellStyle(cellStyle);
                    }
                    map.put("sku",skuValueCell.getStringCellValue());
                }

                if(colorValueCell==null){
                    map.put("color","");
                }else{
                    colorValueCell.setCellType(Cell.CELL_TYPE_STRING);
                    if(colorValueCell.getStringCellValue().length()>50){
                        b = false;
                        sb.append("["+(i+1)+","+3+"]、");
                        colorValueCell.setCellStyle(cellStyle);
                    }
                    map.put("color",colorValueCell.getStringCellValue());
                }

                if(batchCell==null){
                    map.put("batch","");
                }else{
                    batchCell.setCellType(Cell.CELL_TYPE_STRING);
                    String batchValue = batchCell.getStringCellValue();
                    Map<String,String> filterMap = batchList.stream().filter(a->a.get("item_value").equals(batchValue)).findFirst().orElse(null);
                    if(filterMap==null){
                        b = false;
                        sb.append("["+(i+1)+","+4+"]、");
                        batchCell.setCellStyle(cellStyle);
                        map.put("batch",batchValue);
                    }else{
                        filterMap.get("item_key");
                        map.put("batch", org.springframework.util.StringUtils.isEmpty(filterMap.get("item_key"))?"":filterMap.get("item_key"));
                    }
                }
                if(sizeCell==null){
                    map.put("size","");
                }else{
                    sizeCell.setCellType(Cell.CELL_TYPE_STRING);
                    if(!isCostume){
                        map.put("size","FF");
                    }else{
                        String sizeValue = sizeCell.getStringCellValue();
                        String productCode = productCodeVlue;
                        Map<String,String> sizeGroupMap =  productCodeSizeGroupList.stream().filter(a->a.get("product_code").equals(productCode)).findFirst().orElse(null);
                        if(sizeGroupMap!=null){
                            String sizeGroup = sizeGroupMap.get("size_group");
                            Map<String,Object> filterMap = sizeList.stream().filter(a->a.get("size_group_id").toString().equals(sizeGroup) && a.get("size_code").toString().equals(sizeValue)).findFirst().orElse(null);
                            if(filterMap==null){
                                b = false;
                                sb.append("["+(i+1)+","+5+"]、");
                                sizeCell.setCellStyle(cellStyle);
                                map.put("size",sizeValue);
                            }else{
                                map.put("size", org.springframework.util.StringUtils.isEmpty(filterMap.get("size_code").toString())?"":filterMap.get("size_code").toString());
                            }
                        }
                    }
                }

                if(standardPriceValueCell!=null){
                    standardPriceValueCell.setCellType(Cell.CELL_TYPE_STRING);
                    String standardPriceValueCellValue = standardPriceValueCell.getStringCellValue();
                    if(StringUtils.isEmpty(standardPriceValueCellValue)){
                        map.put("standard_price","0.00");
                    }else{
                        if(!NumberUtils.isNumber(standardPriceValueCellValue)){
                            b = false;
                            sb.append("["+(i+1)+","+6+"]、");
                            standardPriceValueCell.setCellStyle(cellStyle);
                        }else{
                            standardPriceValueCell.setCellType(Cell.CELL_TYPE_STRING);
                            String standardPrice = standardPriceValueCell.getStringCellValue();
                            map.put("standard_price",standardPrice.equals("0")?BigDecimal.ZERO:standardPrice);
                        }
                    }
                }else{
                    standardPriceValueCell = valueRow.createCell(5);
                    standardPriceValueCell.setCellType(Cell.CELL_TYPE_STRING);
                    map.put("standard_price","0.00");
                }

                if(standardCostValueCell!=null){
                    standardCostValueCell.setCellType(Cell.CELL_TYPE_STRING);
                    String standardCostValueCellValue = standardCostValueCell.getStringCellValue();
                    if(StringUtils.isEmpty(standardCostValueCellValue)){
                        map.put("standard_cost","0.00");
                    }else{
                        if(!NumberUtils.isNumber(standardCostValueCellValue)){
                            b = false;
                            sb.append("["+(i+1)+","+7+"]、");
                            standardCostValueCell.setCellStyle(cellStyle);
                        }else{
                            standardCostValueCell.setCellType(Cell.CELL_TYPE_STRING);
                            String standardCost = standardCostValueCell.getStringCellValue();
                            map.put("standard_cost",standardCost.equals("0")?BigDecimal.ZERO:standardCost);
                        }
                    }
                }else{
                    standardCostValueCell = valueRow.createCell(6);
                    standardCostValueCell.setCellType(Cell.CELL_TYPE_STRING);
                    map.put("standard_cost","0.00");
                }

                if(saleCostValueCell!=null){
                    saleCostValueCell.setCellType(Cell.CELL_TYPE_STRING);
                    String saleCostValueCellValue = saleCostValueCell.getStringCellValue();
                    if(StringUtils.isEmpty(saleCostValueCellValue)){
                        map.put("sale_cost","0.00");
                    }else{
                        if(!NumberUtils.isNumber(saleCostValueCellValue)){
                            b = false;
                            sb.append("["+(i+1)+","+8+"]、");
                            saleCostValueCell.setCellStyle(cellStyle);
                        }else{
                            saleCostValueCell.setCellType(Cell.CELL_TYPE_STRING);
                            String saleCost = saleCostValueCell.getStringCellValue();
                            map.put("sale_cost",saleCost.equals("0")?BigDecimal.ZERO:saleCost);
                        }
                    }
                }else{
                    saleCostValueCell = valueRow.createCell(7);
                    saleCostValueCell.setCellType(Cell.CELL_TYPE_STRING);
                    map.put("sale_cost","0.00");
                }

                if(securityCodeValueCell!=null){
                    securityCodeValueCell.setCellType(Cell.CELL_TYPE_STRING);
                    String securityCodeValueCellValue = securityCodeValueCell.getStringCellValue();
                    if(securityCodeValueCellValue.length()>30){
                        b = false;
                        sb.append("["+(i+1)+","+9+"]、");
                        securityCodeValueCell.setCellStyle(cellStyle);
                    }
                    map.put("security_code",securityCodeValueCellValue);

                }else{
                    securityCodeValueCell = valueRow.createCell(8);
                    securityCodeValueCell.setCellType(Cell.CELL_TYPE_STRING);
                    map.put("security_code","");
                }

                if(statusValueCell==null){
                    map.put("status",1);
                }else{
                    statusValueCell.setCellType(Cell.CELL_TYPE_STRING);
                    String statusValueCellValue = statusValueCell.getStringCellValue();
                    if(!statusValueCellValue.equals("有效") && !statusValueCellValue.equals("无效")){
                        b = false;
                        sb.append("["+(i+1)+","+10+"]、");
                        statusValueCell.setCellStyle(cellStyle);
                    }else{
                        Integer statusCode = statusValueCellValue.equals("有效")?1:0;
                        statusValueCell.setCellType(Cell.CELL_TYPE_STRING);
                        map.put("status",statusCode);
                    }
                }

                if(b){
                    mapList.add(map);
                    message = sb.toString().length()==0?"":sb.toString().substring(0,(sb.toString()).length()-1);
                }else{
                    message = sb.toString().substring(0,(sb.toString()).length()-1);
                }
            }

            if(mapList.isEmpty()){
                result.setCode(201);
                result.setMessage("导入失败，请下载查看错误数据");
            }else{
                result.setCode(200);
                result.setMessage(message.isEmpty()?"":"坐标："+message+"数据格式错误");
                result.setData(mapList);
            }
            out =  new FileOutputStream(path);
            workbook.write(out);
        }catch (Exception e){
            e.printStackTrace();
            result.setCode(203);
            result.setMessage("导入异常，请联系IT处理");
            return result;
        }finally {
            try {
                if(out!=null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public List<Integer> checkProductList(String path,String tableName) {
        OutputStream out = null;
        List<Integer> counts = new ArrayList<>();
        int updateCount = 0;
        int insertCount = 0;
        try{
            Workbook workbook = WorkbookFactory.create(new FileInputStream(new File(path)));
            Sheet sheet = workbook.getSheetAt(0);
            Row firstRow = sheet.getRow(0);
            Row secondRow = sheet.getRow(1);
            int cloumnCount =firstRow.getPhysicalNumberOfCells();
            int rowNum = 0 ;
            if(!tableName.equals("table_material")){
                rowNum = getExcelProductRealRow(sheet)-1;
            }else{
                rowNum = getExcelMaterialRealRow(sheet)-1;
            }
            List<String> productBaseCodeList = productMapper.selectBaseProductCodes();
            CellStyle updateStyle = workbook.createCellStyle();
            updateStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            updateStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

            CellStyle insertStyle = workbook.createCellStyle();
            insertStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            insertStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

            for(int k = 3;k<=rowNum;k++){
                Row valueRow = sheet.getRow(k);
                Boolean b = true;
                //获取各个要翻译的key的列数
                int productNameNum = -1;
                int cateZlNum = -1;
                int cateXlNum = -1;
                int unitPriceNum = -1;
                int publishNum = -1;
                int editionNum = -1;
                int priorityNum = -1;
                int expenditureCategoryNum = -1;
                int subExpenditureCategoryNum = -1;
                for(int m=0;m<cloumnCount;m++){
                    if(secondRow.getCell(m).getStringCellValue().equals("product_name")){
                        productNameNum = m;
                    }
                    if(secondRow.getCell(m).getStringCellValue().equals("cate_zl")){
                        cateZlNum = m;
                    }
                    if(secondRow.getCell(m).getStringCellValue().equals("cate_xl")){
                        cateXlNum = m;
                    }
                    if(secondRow.getCell(m).getStringCellValue().equals("unit_price")){
                        unitPriceNum = m;
                    }
                    if(secondRow.getCell(m).getStringCellValue().equals("publish")){
                        publishNum = m;
                    }
                    if(secondRow.getCell(m).getStringCellValue().equals("edition")){
                        editionNum = m;
                    }
                    if(secondRow.getCell(m).getStringCellValue().equals("priority")){
                        priorityNum = m;
                    }
                    if(secondRow.getCell(m).getStringCellValue().equals("expenditure_category")){
                        expenditureCategoryNum = m;
                    }
                    if(secondRow.getCell(m).getStringCellValue().equals("sub_expenditure_category")){
                        subExpenditureCategoryNum = m;
                    }
                }
                Row row = sheet.getRow(k);
                Cell productCodeCell = row.getCell(0);
                if(productCodeCell!=null){
                    productCodeCell.setCellType(Cell.CELL_TYPE_STRING);
                    String productCode = productCodeCell.getStringCellValue();
                    if(productBaseCodeList.contains(productCode)){
                        productCodeCell.setCellStyle(updateStyle);
                        updateCount+=1;
                    }else{
                        if(tableName.equals("product_book")){
                            if(productNameNum !=-1 && valueRow.getCell(productNameNum)==null){
                                Cell productNamelCell = valueRow.createCell(productNameNum);
                                productNamelCell.setCellStyle(insertStyle);
                                b = false;
                            }
                            if(cateZlNum !=-1 && StringUtils.isEmpty(valueRow.getCell(cateZlNum).getStringCellValue())){
                                Cell cateZlCell = valueRow.createCell(cateZlNum);
                                cateZlCell.setCellStyle(insertStyle);
                                b = false;
                            }
                            if(cateXlNum !=-1 && StringUtils.isEmpty(valueRow.getCell(cateXlNum).getStringCellValue())){
                                Cell cateXlCell = valueRow.createCell(cateXlNum);
                                cateXlCell.setCellStyle(insertStyle);
                                b = false;
                            }
                            if(unitPriceNum !=-1 && valueRow.getCell(unitPriceNum)==null){
                                Cell unitPriceCell = valueRow.createCell(unitPriceNum);
                                unitPriceCell.setCellStyle(insertStyle);
                                b = false;
                            }
                            if(publishNum !=-1 && valueRow.getCell(publishNum)==null){
                                Cell publishCell = valueRow.createCell(publishNum);
                                publishCell.setCellStyle(insertStyle);
                                b = false;
                            }
                            if(editionNum !=-1 && valueRow.getCell(editionNum)==null){
                                Cell editionCell = valueRow.createCell(editionNum);
                                editionCell.setCellStyle(insertStyle);
                                b = false;
                            }
                        }else if(tableName.equals("product_homehold")){
                            if(productNameNum !=-1 && valueRow.getCell(productNameNum)==null){
                                Cell productNamelCell = valueRow.createCell(productNameNum);
                                productNamelCell.setCellStyle(insertStyle);
                                b = false;
                            }
                            if(cateZlNum !=-1 && StringUtils.isEmpty(valueRow.getCell(cateZlNum).getStringCellValue())){
                                Cell cateZlCell = valueRow.createCell(cateZlNum);
                                cateZlCell.setCellStyle(insertStyle);
                                b = false;
                            }
                            if(priorityNum !=-1 && valueRow.getCell(priorityNum)==null){
                                Cell priorityCell = valueRow.createCell(priorityNum);
                                priorityCell.setCellStyle(insertStyle);
                                b = false;
                            }
                            if(unitPriceNum !=-1 && valueRow.getCell(unitPriceNum)==null){
                                Cell unitPriceCell = valueRow.createCell(unitPriceNum);
                                unitPriceCell.setCellStyle(insertStyle);
                                b = false;
                            }
                        }else if(tableName.equals("product_material")){
                            if(productNameNum !=-1 && valueRow.getCell(productNameNum)==null){
                                Cell productNamelCell = valueRow.createCell(productNameNum);
                                productNamelCell.setCellStyle(insertStyle);
                                b = false;
                            }
                            if(cateZlNum !=-1 && StringUtils.isEmpty(valueRow.getCell(cateZlNum).getStringCellValue())){
                                Cell cateZlCell = valueRow.createCell(cateZlNum);
                                cateZlCell.setCellStyle(insertStyle);
                                b = false;
                            }
                            if(cateXlNum !=-1 && StringUtils.isEmpty(valueRow.getCell(cateXlNum).getStringCellValue())){
                                Cell cateXlCell = valueRow.createCell(cateXlNum);
                                cateXlCell.setCellStyle(insertStyle);
                                b = false;
                            }
                            if(expenditureCategoryNum !=-1 && valueRow.getCell(expenditureCategoryNum)==null){
                                Cell expenditureCategoryCell = valueRow.createCell(expenditureCategoryNum);
                                expenditureCategoryCell.setCellStyle(insertStyle);
                                b = false;
                            }
                            if(subExpenditureCategoryNum !=-1 && valueRow.getCell(subExpenditureCategoryNum)==null){
                                Cell subExpenditureCategoryCell = valueRow.createCell(subExpenditureCategoryNum);
                                subExpenditureCategoryCell.setCellStyle(insertStyle);
                                b = false;
                            }
                        }

                        if(!b){
                            productCodeCell.setCellStyle(insertStyle);
                            insertCount+=1;
                        }

                    }
                }
            }
            counts.add(updateCount);
            counts.add(insertCount);
            out =  new FileOutputStream(path);
            workbook.write(out);
        }catch (Exception e){
            e.printStackTrace();
            return counts;
        }finally {
            try {
                if(out!=null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return counts;
    }


    public String [] getTypeClass(String typeClass){
        String [] resultArray = new String[]{};
        String result = "";
        switch (typeClass){
            case "gcdzj" : result = "4,1"; break;
            case "nanzxdzj" : result = "2,1"; break;
            case "blxdzj" : result = "3,1"; break;
            case "nvzxdzj" : result = "1,1"; break;
            case "jjdzj" : result = "8,1"; break;
            case "kfdzj" : result = "5,1"; break;
            case "dsdzj" : result = "7,1"; break;
            case "zhdzj" : result = "6,1"; break;
            case "ppzj" : result = "9,1"; break;
            case "kfgcdzj" : result = "11,1"; break;
            case "gcdzk" : result = "4,2"; break;
            case "nanzxdzk" : result = "2,2"; break;
            case "blxdzk" : result = "3,2"; break;
            case "nvzxdzk" : result = "1,2"; break;
            case "jjdzk" : result = "8,2"; break;
            case "kfdzk" : result = "5,2"; break;
            case "dsdzk" : result = "7,2"; break;
            case "zhdzk" : result = "6,2"; break;
            case "ppzk" : result = "9,2"; break;
            case "kfgcdzk" : result = "11,2"; break;
        }
        if(!StringUtils.isEmpty(result)){
            resultArray = result.split(",");
        }
        return resultArray;
    }


}




