package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.ProductDimensionTrainingMapper;
import com.icicle.masterdb.model.ProductDimensionTraining;
import com.icicle.masterdb.service.ProductDimensionTrainingService;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PageUtil;
import com.icicle.masterdb.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class ProductDimensionTrainingServiceImpl extends AbstractService<ProductDimensionTraining> implements ProductDimensionTrainingService {
    @Resource
    private ProductDimensionTrainingMapper productDimensionTrainingMapper;

    @Override
    public DataTableRecord listFeature(String sEcho, Integer pageIndex, Integer pageSize, String sSearch, String productCode) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ProductDimensionTraining> productDimensionTrainingList = null;
        Condition condition = new Condition(ProductDimensionTraining.class);
        if (!StringUtils.isBlank(sSearch)) {
            String words = StringUtil.gsFormat("%{0}%", sSearch);
            condition.and(condition.createCriteria().andLike("productCode", words));
        }
        if (!StringUtils.isBlank(productCode)) {
            condition.and(condition.createCriteria().andEqualTo("productCode", productCode));
        }
        try {
            productDimensionTrainingList = productDimensionTrainingMapper.selectByCondition(condition);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }
        PageInfo pageInfo = new PageInfo(productDimensionTrainingList);
        dataTableRecord.setITotalRecords(pageSize);
        dataTableRecord.setITotalDisplayRecords((int) pageInfo.getTotal());
        dataTableRecord.setAaData(productDimensionTrainingList);
        return dataTableRecord;
    }

    @Override
    @LogAction(logDesc = "更新产品特点")
    public int updateDimensionTrain(ProductDimensionTraining productDimensionTraining) {
        try {
            int con = super.update(productDimensionTraining);
            if (con > 0) {
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    @LogAction(logDesc = "新增产品特点")
    public int insertDimensionTrain(ProductDimensionTraining productDimensionTraining) {
        try {
            int cont = super.save(productDimensionTraining);
            if (cont > 0) {
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public ProductDimensionTraining findFeatureCodeDetail(String productCode) {
        Condition condition = new Condition(ProductDimensionTraining.class);
        condition.createCriteria().andEqualTo("productCode", productCode);
        ProductDimensionTraining productDimensionTraining = super.findOneByCondition(condition);
        if (productDimensionTraining != null) {
            return productDimensionTraining;
        } else {
            return null;
        }

    }

    @Override
    public ProductDimensionTraining findByFeatureCode(String productCode) {
        ProductDimensionTraining productDimensionTraining = productDimensionTrainingMapper.searchByProductCode(productCode);
        if (productDimensionTraining != null) {
            return productDimensionTraining;
        } else {
            return null;
        }
    }
}