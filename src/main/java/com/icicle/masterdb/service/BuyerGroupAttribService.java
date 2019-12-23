package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.BuyerGroupAttrib;
import com.icicle.masterdb.pojo.vo.BuyerGroupAttributeListVO;

/**
 * @author CodeGeneratorUtil
 */
public interface BuyerGroupAttribService extends Service<BuyerGroupAttrib> {


    /**
     * 代理商属性关联
     * @param buyerGroupAttributeListVO
     * @return
     */
    int connectAttribute(BuyerGroupAttributeListVO buyerGroupAttributeListVO);
}