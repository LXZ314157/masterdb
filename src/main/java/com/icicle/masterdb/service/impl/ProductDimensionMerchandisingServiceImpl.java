package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.ProductDimensionMerchandisingMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ProductAttribute;
import com.icicle.masterdb.model.ProductDimensionMerchandising;
import com.icicle.masterdb.service.ProductAttributeService;
import com.icicle.masterdb.service.ProductDimensionMerchandisingService;
import com.icicle.masterdb.util.ListUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PageUtil;
import com.icicle.masterdb.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class ProductDimensionMerchandisingServiceImpl extends AbstractService<ProductDimensionMerchandising> implements ProductDimensionMerchandisingService {
    @Resource
    private ProductDimensionMerchandisingMapper productDimensionMerchandisingMapper;
    @Resource
    private ProductAttributeService productAttributeService;

    @Override
    public DataTableRecord listMerchandList(String sEcho, Integer pageIndex, Integer pageSize, String sSearch) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ProductDimensionMerchandising> merchandList;
        Condition condition = new Condition(ProductDimensionMerchandising.class);
        if (!StringUtils.isBlank(sSearch)) {
            String words = StringUtil.gsFormat("%{0}%", sSearch);
            condition.and(condition.createCriteria().andLike("productCode", words));
        }
        try {
            merchandList = super.findByCondition(condition);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }
        PageInfo pageInfo = new PageInfo(merchandList);
        dataTableRecord.setITotalRecords(pageSize);
        dataTableRecord.setITotalDisplayRecords((int) pageInfo.getTotal());
        dataTableRecord.setAaData(merchandList);
        return dataTableRecord;
    }


    /**
     * @param list
     * @return
     */
    @Override
    @LogAction(logDesc = "excel导入企划属性")
    @Transactional(rollbackFor = Exception.class)
    public int saveMerchandisingList(List<ProductDimensionMerchandising> list, List<ProductAttribute> attributeList) {
        if (ListUtil.isBlank(list) || ListUtil.isBlank(attributeList)) {
            return 0;
        }
        int count = 0;
        String lan = SessionManager.getLanguage();
        for (ProductDimensionMerchandising p : list) {
            p.setLanguage(lan);
        }

        List<List<ProductDimensionMerchandising>> splitMerchandList = ListUtil.splitArrayList(list, 100);

        for (List<ProductDimensionMerchandising> tempList : splitMerchandList) {
            count += productDimensionMerchandisingMapper.saveList(tempList);
        }

        if (count > 0) {
            productAttributeService.saveList(attributeList);
        }

        return count;
    }

    @Override
    public ProductDimensionMerchandising findByCode(String code) {
        return productDimensionMerchandisingMapper.findByCode(code);
    }

    @Override
    public List<String> findAllMerchandisingCode() {
        return productDimensionMerchandisingMapper.findAllProductCode();
    }

    @Override
    public int saveMerchandising(ProductDimensionMerchandising productDimensionMerchandising) {
        if (productDimensionMerchandising == null) {
            return 0;
        }
        productDimensionMerchandising.setLanguage(SessionManager.getLanguage());
        return productDimensionMerchandisingMapper.insert(productDimensionMerchandising);
    }

    @Override
    public int updateMerchandising(ProductDimensionMerchandising productDimensionMerchandising) {
        if (productDimensionMerchandising == null) {
            return 0;
        }
        productDimensionMerchandising.setLanguage(SessionManager.getLanguage());
        return productDimensionMerchandisingMapper.updateMerchandising(productDimensionMerchandising);
    }

}