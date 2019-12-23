package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ProductAttributeExtendMapper;
import com.icicle.masterdb.dao.masterdb.ProductCategoryMapper;
import com.icicle.masterdb.dao.masterdb.ProductMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.Product;
import com.icicle.masterdb.model.ProductAttributeExtend;
import com.icicle.masterdb.model.ViewCatalog;
import com.icicle.masterdb.model.ViewSelectproductinfo;
import com.icicle.masterdb.pojo.vo.ProductAttributeItemsVO;
import com.icicle.masterdb.pojo.vo.ProductFindVO;
import com.icicle.masterdb.pojo.vo.ProductVO;
import com.icicle.masterdb.pojo.vo.ViewSelectItemVO;
import com.icicle.masterdb.service.*;
import com.icicle.masterdb.service.constant.CommonConstant;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.ListUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PojoConvertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class ProductAttributeExtendServiceImpl extends AbstractService<ProductAttributeExtend> implements ProductAttributeExtendService {
    @Resource
    private ProductAttributeExtendMapper productAttributeExtendMapper;
    @Resource
    private ViewCatalogService viewCatalogService;
    @Resource
    private ViewProductDescriptionService viewProductDescriptionService;
    @Resource
    private ProductService productService;
    @Resource
    private ViewSelectproductinfoService viewSelectproductinfoService;
    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Resource
    private ProductMapper productMapper;

    @Override
    public int insertAttributeExtendList(List<ProductAttributeExtend> productAttributeExtendList) {
        return productAttributeExtendMapper.insertAttributeExtendList(productAttributeExtendList);
    }

    @Override
    public int updateAttributePart(List<ProductAttributeExtend> productAttributeExtends) {
        return productAttributeExtendMapper.updateAttributePart(productAttributeExtends);
    }

    @Override
    public ProductAttributeExtend searchByCondition(String pro) {
        return productAttributeExtendMapper.searchByCondition(pro);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "单条更新款式")
    public int updateStyle(ProductAttributeItemsVO productAttributeItemsVO) {
        try{
            //更新基本属性
            Product productAttr = productAttributeItemsVO.getProduct();
            ProductAttributeExtend productAttributeExtend = productAttributeItemsVO.getProductAttributeExtend();
            if(productAttr!=null){
                ProductVO productVO = PojoConvertUtil.convertPojo(productAttr,ProductVO.class);
                productVO.setStatus(productAttr.getStatus()==true?1:0);
                productVO.setLastUpdatedBy(SessionManager.getLoginName());
                productVO.setLastUpdateDate(DateUtil.now());
                if(productAttributeExtend!=null){
                    String productLineId = StringUtils.isEmpty(productAttributeExtend.getLine())?"":productMapper.getProductLineIdByLine(productAttributeExtend.getLine());
                    productVO.setProductLineId(productLineId);
                }
                productMapper.updateProduct(productVO);
                //更新条码标的标准单价价格
                if(productVO.getUnitPrice()!=null){
                    productMapper.updateStandardPrice(productVO);
                }
            }
            //更新扩展属性
            if (productAttr!=null && productAttributeExtend != null) {
                super.update(productAttributeExtend);
                Product product = new Product();
                String workGroup = productAttributeExtend.getWorkGroup();
                String clothesType = productAttributeExtend.getProductClass();
                if (workGroup != null && clothesType != null) {
                    String systemCode = workGroup.concat(clothesType);
                    ViewCatalog viewCatalogs = viewCatalogService.searchBySystemCode(systemCode);
                    if (viewCatalogs != null) {
                        product.setCateDl(viewCatalogs.getCa1());
                        product.setCateZl(viewCatalogs.getCa2());
                        product.setCateXl(viewCatalogs.getCa3());
                    }
                }
                product.setLastUpdatedBy(SessionManager.getLoginName());
                product.setLastUpdateDate(DateUtil.now());
                product.setProductCode(productAttributeExtend.getProductCode());
                //更新产品状态(大中小类)
                productService.updateEditProduct(product);
                String productCode = productAttributeExtend.getProductCode();
                String productName = productMapper.getProductNameByCode(productCode)==null?"":productMapper.getProductNameByCode(productCode);
                String desc = productAttributeExtend.getWave()+"/"+productAttributeExtend.getIcicleBand()+"/"+productAttributeExtend.getIcicleGroup()+"/"+
                        productAttributeExtend.getModelNo()+"/"+productAttributeExtend.getMaterialNo()+"/"+productAttributeExtend.getColorCardNo()+"/"+
                        productName+"/"+productAttributeExtend.getColourSystem()+"/"+productAttributeExtend.getColorName();
                String lastUpdatedBy = SessionManager.getLoginName();
                //更新描述
                productService.updateDescOne(productCode, desc, lastUpdatedBy,DateUtil.now());
                //更新竖表
                List<ProductAttributeExtend> productAttributeExtendList = new ArrayList<>();
                List<String> productBaseCodeList = new ArrayList<>();
                productAttributeExtendList.add(productAttributeExtend);
                productBaseCodeList.add(productAttr.getProductCode());
                productService.saveOrUpdateProductAttribute(productAttributeExtendList,productBaseCodeList);
                return 1;
            } else {
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage());
            return 0;
        }
    }

    @Override
    public ProductFindVO findByProductCode(String productCode,String productCategoryCode) {
        try{
            if (!StringUtils.isBlank(productCode)) {
                ProductFindVO productFindVO = new ProductFindVO();
                //基本字段
                List<ViewSelectItemVO> categoryLevelList =  productMapper.getCategoryLevel();
                List<ViewSelectItemVO> categoryLevel1List =  categoryLevelList.stream().filter(a->a.getSubCategoryLevel()==1 && a.getCategoryCode().equals(productCategoryCode)).collect(Collectors.toList());
                List<ViewSelectItemVO> categoryLevel2List = null;
                if(!productCategoryCode.equals(CommonConstant.HOMEHOLDCATEGORYKEY)){
                    categoryLevel2List =  categoryLevelList.stream().filter(a->a.getSubCategoryLevel()==2 && a.getCategoryCode().equals(productCategoryCode)).collect(Collectors.toList());
                }
                ViewSelectproductinfo viewSelectproductinfos = null;
                if(productCategoryCode.equals(CommonConstant.COSTUMECATEGORYKEY) || StringUtils.isEmpty(productCategoryCode)){
                    viewSelectproductinfos = viewSelectproductinfoService.searchByCondition(productCode);
                }
                if(productCategoryCode.equals(CommonConstant.BOOKCATEGORYKEY) || productCategoryCode.equals(CommonConstant.MATERIALCATEGORYKEY)){
                    viewSelectproductinfos = productCategoryMapper.searchByCondition(productCode);
                }
                if(productCategoryCode.equals(CommonConstant.HOMEHOLDCATEGORYKEY)){
                    viewSelectproductinfos = productCategoryMapper.searchHomeholdByCondition(productCode);
                }
                //扩展字段
                Condition con = new Condition((ProductAttributeExtend.class));
                con.createCriteria().andEqualTo("productCode", productCode);
                ProductAttributeExtend productAttributeExtendList = productAttributeExtendMapper.searchByCondition(productCode);
                if (viewSelectproductinfos != null) {
                    productFindVO.setViewSelectproductinfoList(viewSelectproductinfos);
                    productFindVO.setProductAttributeExtendlist(productAttributeExtendList);
                    productFindVO.setCategoryLevel1List(categoryLevel1List);
                    if(!productCategoryCode.equals(CommonConstant.HOMEHOLDCATEGORYKEY)){
                        if(!ListUtil.isBlank(categoryLevel2List)){
                            productFindVO.setCategoryLevel2List(categoryLevel2List);
                        }
                    }
                    return productFindVO;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.getLogger(this.getClass()).error(e.getMessage());
            return null;
        }
    }
}