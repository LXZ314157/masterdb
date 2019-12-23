package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.DynamicDataMapper;
import com.icicle.masterdb.dao.masterdb.ProductDimensionMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ProductDimension;
import com.icicle.masterdb.pojo.bo.DynamicColumn;
import com.icicle.masterdb.pojo.vo.ProductAttributeDefinedVO;
import com.icicle.masterdb.pojo.vo.ProductDimensionVO;
import com.icicle.masterdb.service.ProductDimensionService;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PojoConvertUtil;
import com.icicle.masterdb.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liurenhua
 */
@Service
public class ProductDimensionServiceImpl extends AbstractService<ProductDimension> implements ProductDimensionService {
    @Resource
    private ProductDimensionMapper productDimensionMapper;

    @Resource
    private DynamicDataMapper dynamicDataMapper;


    @Override
    public List<ProductDimensionVO> findAllProductDimensionVO() {
        List<ProductDimension> productDimensionList = productDimensionMapper.findAllDimension();
        List<ProductDimensionVO> productDimensionVOList = PojoConvertUtil.convertPojoList(productDimensionList, ProductDimensionVO.class);
        return productDimensionVOList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "添加维度")
    public int saveDimension(ProductDimension productDimension) {
        String tableName = productDimension.getClassDimensionCode();
        if (StringUtil.checkInjection(tableName)) {
            return 0;
        }
        productDimensionMapper.saveDimension(productDimension);
        //加表
        List<DynamicColumn> columns = new ArrayList<>();
        dynamicDataMapper.createTable(tableName, columns);
        return 1;
    }

    @Override
    @LogAction(logDesc = "更新维度")
    public int updateDimension(ProductDimension productDimension) {
        if (productDimension == null) {
            return 0;
        }
        productDimension.setLastUpdateDate(DateUtil.now());
        productDimension.setLastUpdatedBy(SessionManager.getLoginName());
        try {
            return productDimensionMapper.updateDimension(productDimension);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public int updateAttributeDef(ProductAttributeDefinedVO productAttributeDefinedVO) {
        return 0;
    }
}