package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ProductAttributeExtendMapper;
import com.icicle.masterdb.dao.masterdb.ProductInfoMapper;
import com.icicle.masterdb.dao.masterdb.ProductMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ProductInfo;
import com.icicle.masterdb.pojo.vo.ProductInfoExcelVO;
import com.icicle.masterdb.pojo.vo.ProductInfoSkuVO;
import com.icicle.masterdb.service.ProductInfoService;
import com.icicle.masterdb.service.ProductService;
import com.icicle.masterdb.service.constant.CommonConstant;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.ListUtil;
import com.icicle.masterdb.util.LogUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CodeGeneratorUtil
 */
@Service
public class ProductInfoServiceImpl extends AbstractService<ProductInfo> implements ProductInfoService {
    @Resource
    private ProductInfoMapper productInfoMapper;
    @Resource
    private ProductService productService;
    @Resource
    private ProductAttributeExtendMapper productAttributeExtendMapper;
    @Resource
    private ProductMapper productMapper;


    @Override
    @LogAction(logDesc = "尺码批量导入")
    @Transactional(rollbackFor = Exception.class)
    public int sizeInsert(ProductInfoExcelVO productInfoExcelVO) {
        if (productInfoExcelVO == null) {
            return 0;
        }
        List<ProductInfo> productInfos = productInfoExcelVO.getProductInfoList();

        if (productInfos == null || productInfos.size() == 0) {
            return 0;
        }
        List<List<ProductInfo>> list = ListUtil.splitArrayList(productInfos, 200);
        for (int i = 0; i < list.size(); i++) {
            List<String> productInfoList = productInfoMapper.selectProductInfo(list.get(i));
            List<ProductInfo> productInfoinsert = list.get(i).stream()
                    .filter(p -> !productInfoList.contains(p.getSku())).collect(Collectors.toList());
            List<ProductInfo> productInfoupdate = list.get(i).stream()
                    .filter(p -> productInfoList.contains(p.getSku())).collect(Collectors.toList());
            if (productInfoupdate != null && productInfoupdate.size() != 0) {
                productInfoMapper.updateInfoPart(productInfoupdate);
            }
            if (productInfoinsert != null && productInfoinsert.size() != 0) {
                productInfoMapper.insertInfoAll(productInfoinsert);
            }
        }
        productInfoMapper.updateColorAll();
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "删除尺码和款式")
    public int delSize(String productcode) {
        Condition condition = new Condition(ProductInfo.class);
        condition.createCriteria().andEqualTo("productCode", productcode)
                .andEqualTo("status", 1);
        List<ProductInfo> productInfoList = super.findByCondition(condition);
        int count=0;
        count+=productMapper.deleteUnSycn(productcode);
        count+=productAttributeExtendMapper.deleteUnSycn(productcode);
        if (productInfoList != null && productInfoList.size() != 0) {
            for (ProductInfo item : productInfoList) {
                item.setStatus(false);
                item.setLastUpdateDate(DateUtil.now());
                item.setLastUpdatedBy(SessionManager.getLoginName());
            }
            count+=productInfoMapper.updateInfoPart(productInfoList);
        }
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "单条新增尺码")
    public int insertSize(ProductInfoSkuVO productInfo) {
        String sku = productInfo.getSku();
        int checkResult = checkProductCodeAndSku(productInfo);
        if(checkResult == 0){
            return 3;
        }
        int cot = 0;
        Condition condition = new Condition(ProductInfo.class);
        condition.createCriteria().andEqualTo("sku", sku).andEqualTo("status", 1);
        int con = super.findCountByCondition(condition);
        if (con > 0) {
            return 2;
        } else {
            if (productInfo.getProductCode() != null) {
                String getSku = productInfoMapper.unvalidStatus(sku);
                try {
                    if (getSku != null && getSku.length() != 0) {
                        productInfo.setSku(getSku);
                        productInfo.setStatus(true);
                        cot = productInfoMapper.updateStatus(productInfo);
                    } else {
                        productInfo.setStatus(true);
                        productInfo.setSku(productInfo.getSku()==null?"":productInfo.getSku());
                        productInfo.setSize(productInfo.getSize()==null?"":productInfo.getSize());
                        cot = productInfoMapper.insertProductInfo(productInfo);
                    }
                    if (cot > 0) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                    return 0;
                }
            } else {
                return 0;
            }
        }
    }

    @Override
    public String getColor(Integer id) {
        return productInfoMapper.getColor(id);
    }

    private int checkProductCodeAndSku(ProductInfoSkuVO productInfo){
        String productCode = productInfo.getProductCode();
        String sku = productInfo.getSku();
        String size = productInfo.getSize();
        int checkResult = 1;
        List<String> matchList = Arrays.asList("AAA","BBB","CCC","DDD");
        if(productCode.length()>=3){
            String threeCharacter = productCode.substring(0,3);
            if(matchList.contains(threeCharacter)){
                if(!sku.equals(productCode.concat(size))){
                    checkResult = 0;
                }
            }else{
                if(productInfo.getProductCategoryCode().equals(CommonConstant.MATERIALCATEGORYKEY)){
                    if(!sku.equals(productCode)){
                        checkResult = 0;
                    }
                }
            }
        }
        return checkResult;
    }
}
