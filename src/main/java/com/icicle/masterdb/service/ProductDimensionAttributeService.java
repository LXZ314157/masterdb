package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ProductDimensionAttribute;
import com.icicle.masterdb.pojo.vo.DimensionAttriVO;
import com.icicle.masterdb.pojo.vo.ProductDimensionAttriVo;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductDimensionAttributeService extends Service<ProductDimensionAttribute> {
    /**
     * 获取dimensionattribute的所有属性
     *
     * @return
     */
    List<DimensionAttriVO> findAllDimensionAttriVO();

    /**
     * 属性关联
     *
     * @param productDimensionAttriVo
     * @return
     */
    int connectAttribute(ProductDimensionAttriVo productDimensionAttriVo);

    /**
     * 获取关联过得属性id
     *
     * @return
     */
    List<String> getConnectDefId();

}
