package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.BuyerType;

/**
 * @author CodeGeneratorUtil
 */
public interface BuyerTypeService extends Service<BuyerType> {
    /**
     * 代理商类型的更新
     * @param buyerTypeId
     * @param buyerTypeName
     * @param buyerTypeDesc
     * @param status
     * @return
     */
    int typeUpdate(Integer buyerTypeId,String buyerTypeName,String buyerTypeDesc,Integer status);

    /**
     * 代理商类型添加
     * @param buyerTypeName
     * @param buyerTypeDesc
     * @return
     */
    int typeInsert(String buyerTypeName,String buyerTypeDesc);
}