package com.icicle.masterdb.service.impl;


import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.DynamicDataMapper;
import com.icicle.masterdb.dao.masterdb.ProductAttributeDefinedMapper;
import com.icicle.masterdb.dao.masterdb.ProductDimensionAttributeMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ProductAttributeDefined;
import com.icicle.masterdb.model.ProductDimensionAttribute;
import com.icicle.masterdb.pojo.bo.DynamicColumn;
import com.icicle.masterdb.pojo.vo.DimensionAttriVO;
import com.icicle.masterdb.pojo.vo.ProductDimensionAttriVo;
import com.icicle.masterdb.service.ProductDimensionAttributeService;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.ListUtil;
import com.icicle.masterdb.util.PojoConvertUtil;
import com.icicle.masterdb.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class ProductDimensionAttributeServiceImpl extends AbstractService<ProductDimensionAttribute> implements ProductDimensionAttributeService {
    @Resource
    private ProductDimensionAttributeMapper productDimensionAttributeMapper;
    @Resource
    private ProductDimensionAttributeService productDimensionAttributeService;
    @Resource
    private DynamicDataMapper dynamicDataMapper;
    @Resource
    private ProductAttributeDefinedMapper productAttributeDefinedMapper;


    @Override
    public List<DimensionAttriVO> findAllDimensionAttriVO() {
        List<ProductDimensionAttribute> productDimensionAttributes = productDimensionAttributeMapper.selectAll();
        List<DimensionAttriVO> dimensionAttriVOList = PojoConvertUtil.convertPojoList(productDimensionAttributes, DimensionAttriVO.class);
        return dimensionAttriVOList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "关联维度属性")
    public int connectAttribute(ProductDimensionAttriVo productDimensionAttriVo) {
        String tableName = productDimensionAttriVo.getClassDimensionCode();
        if (StringUtil.checkInjection(tableName)) {
            return 0;
        }
        List<ProductDimensionAttribute> list = productDimensionAttriVo.getProductDimensionAttributeList();
        if (!ListUtil.isBlank(list)) {
            productDimensionAttributeService.save(getForInsert(list, true));
            //添加列
            List<ProductAttributeDefined> defDetailList = productAttributeDefinedMapper.searchByAttributeId(list);
            if (!ListUtil.isBlank(defDetailList)) {
                for (ProductAttributeDefined item : defDetailList) {
                    DynamicColumn column = new DynamicColumn();
                    column.setColumnName(item.getDefCode());
                    column.setDataLength(item.getDefLength());
                    column.setDataType(item.getDefType());
                    int con = dynamicDataMapper.alterTableAddColumn(tableName, column);
                    if (con == 0) {
                        return 1;
                    } else {
                        return -1;
                    }

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
    public List<String> getConnectDefId() {
        List<String> defList = productDimensionAttributeMapper.findConnectDefId();
        if (defList != null) {
            return defList;
        } else {
            return null;
        }
    }

    private List<ProductDimensionAttribute> getForInsert(List<ProductDimensionAttribute> list, boolean status) {
        for (ProductDimensionAttribute initem : list) {
            initem.setCreationDate(DateUtil.now());
            initem.setCreatedBy(SessionManager.getLoginName());
            initem.setStatus(status);
        }
        return list;
    }

}