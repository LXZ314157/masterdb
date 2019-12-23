package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.ProductMapper;
import com.icicle.masterdb.model.SelectBrand;
import com.icicle.masterdb.model.ViewProductmessageinfo;
import com.icicle.masterdb.service.SelectBrandService;
import com.icicle.masterdb.service.ViewProductmessageinfoService;
import com.icicle.masterdb.util.ListUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PageUtil;
import com.icicle.masterdb.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.icicle.masterdb.service.constant.ServiceConstant.ASC;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class ViewProductmessageinfoServiceImpl extends AbstractService<ViewProductmessageinfo> implements ViewProductmessageinfoService {
    @Resource
    private ViewProductmessageinfoService viewProductmessageinfoService;
    @Resource
    private SelectBrandService selectBrandService;
    @Resource
    private ProductMapper productMapper;

    @Override
    public DataTableRecord listProduct(String sEcho, Integer pageIndex, Integer pageSize,String batchProductCode, String productCodeOrName,String startTime,
                                       String endTime,String syncStatus,Integer sortCol, String sortDir, int type) {
        List<ViewProductmessageinfo> viewProductmessageinfoList = null;
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        try {
            Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
            PageHelper.startPage(pageNum, pageSize);
            Condition condition = new Condition(ViewProductmessageinfo.class);
            Example.Criteria criteria = condition.createCriteria();
            Example.Criteria criteria2 = condition.createCriteria();
            Example.Criteria criteria3 = condition.createCriteria();
            Example.Criteria criteria4 = condition.createCriteria();
            Example.Criteria criteria5 = condition.createCriteria();
            String [] productArray = batchProductCode.split(" ");
            if (type == 0) {
                if (StringUtils.isEmpty(productCodeOrName) && StringUtils.isEmpty(syncStatus) && productArray.length<2
                        && StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime)) {
                    dataTableRecord.setAaData(new ArrayList<>());
                    return dataTableRecord;
                }else{
                    criteria.andEqualTo("syncStatus", "0");
                }
            }else{
                criteria.andNotEqualTo("syncStatus", "0");
            }

            if (!StringUtils.isBlank(syncStatus)) {
                criteria.andEqualTo("syncStatus", syncStatus);
            }

            if (!StringUtils.isBlank(productCodeOrName)) {
                String words = StringUtil.gsFormat("%{0}%", productCodeOrName);
                condition.and(criteria2.andLike("productCode", words)
                        .orLike("productName", words));
            }
            if(productArray.length>1){
                condition.and(criteria5.andIn("productCode", Arrays.asList(productArray)));
            }
            if (!StringUtils.isBlank(startTime)) {
                condition.and(criteria3.andGreaterThanOrEqualTo("lastUpdateDate", startTime));
            }
            if (!StringUtils.isBlank(endTime)) {
                condition.and(criteria4.andLessThanOrEqualTo("lastUpdateDate", endTime));
            }

            if (ASC.equals(sortDir)) {
                condition.orderBy("lastUpdateDate").asc();
            } else {
                condition.orderBy("lastUpdateDate").desc();
            }
            viewProductmessageinfoList = viewProductmessageinfoService.findByCondition(condition);
            List<Map<String,String>> productCategoryList = productMapper.getProductCategoryList();
            List<SelectBrand>  selectBrandList = selectBrandService.findAll();
            if(viewProductmessageinfoList!=null){
                for(ViewProductmessageinfo viewProductmessageinfo : viewProductmessageinfoList){
                    String productCategoryCode = viewProductmessageinfo.getProductCategoryCode();
                    List<Map<String,String>> dzxMapList = productMapper.selectProductDzl();
                    String cateZl = viewProductmessageinfo.getCategoryLevel1()==null?"":viewProductmessageinfo.getCategoryLevel1();
                    String cateXl = viewProductmessageinfo.getCategoryLevel2()==null?"":viewProductmessageinfo.getCategoryLevel2();
                    String dl = "";
                    String zl = "";
                    String xl = "";
                    if(!ListUtil.isBlank(productCategoryList)){
                        Map<String,String> filterMap = productCategoryList.stream().filter(a->a.get("category_key").equals(productCategoryCode)).findFirst().orElse(null);
                        if(filterMap!=null){
                            dl = filterMap.get("category_name");
                        }
                    }
                    viewProductmessageinfo.setCateDl(dl);
                    List<Map<String,String>> fiterZlMapList = dzxMapList.stream().filter(a ->a.get("product_sub_category_code").equals(cateZl)
                    && a.get("category_code").equals(productCategoryCode)).collect(Collectors.toList());
                    if(!ListUtil.isBlank(fiterZlMapList)){
                        zl = fiterZlMapList.get(0).get("product_sub_category_name")==null?"":fiterZlMapList.get(0).get("product_sub_category_name");
                    }
                    viewProductmessageinfo.setCateDl(zl);
                    List<Map<String,String>> fiterXlMapList = dzxMapList.stream().filter(a ->a.get("product_sub_category_code").equals(cateXl)
                    && a.get("category_code").equals(productCategoryCode)).collect(Collectors.toList());
                    if(!ListUtil.isBlank(fiterXlMapList)){
                        xl = fiterXlMapList.get(0).get("product_sub_category_name")==null?"":fiterXlMapList.get(0).get("product_sub_category_name");
                    }
                    viewProductmessageinfo.setCateDl(dl);
                    viewProductmessageinfo.setCateZl(zl);
                    viewProductmessageinfo.setCateXl(xl);

                    String brand = viewProductmessageinfo.getBrand()==null?"":viewProductmessageinfo.getBrand();
                    for(SelectBrand selectBrand : selectBrandList){
                        if(brand.equals(selectBrand.getItemKey())){
                            viewProductmessageinfo.setBrand(selectBrand.getItemValue());
                        }
                    }
                }
            }
            if (viewProductmessageinfoList != null) {
                PageInfo pageInfo = new PageInfo(viewProductmessageinfoList);
                dataTableRecord.setITotalRecords(pageSize);
                dataTableRecord.setITotalDisplayRecords((int) pageInfo.getTotal());
                dataTableRecord.setAaData(viewProductmessageinfoList);
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
}

