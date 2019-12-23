package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.BuyerAttribDefined;
import com.icicle.masterdb.pojo.vo.BuyerAttribDefItemVO;
import com.icicle.masterdb.pojo.vo.BuyerAttribDefinedVO;

/**
 * @author CodeGeneratorUtil
 */
public interface BuyerAttribDefinedService extends Service<BuyerAttribDefined> {

    /**
     * 属性定义新增
     * @param buyerAttribDefinedVO
     * @return
     */
    int attributeInsert(BuyerAttribDefinedVO buyerAttribDefinedVO);

    /**
     *属性定义更新
     * @param buyerAttribDefinedVO
     * @return
     */
    int attributeupdate(BuyerAttribDefinedVO buyerAttribDefinedVO);

    /**
     * 自动加载属性
     * @return
     */
    BuyerAttribDefItemVO loadDefine();
}