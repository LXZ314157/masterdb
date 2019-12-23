package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.BuyerAttribDefined;

/**
 * @author CodeGeneratorUtil
 */
public interface BuyerAttribDefinedMapper extends MyMapper<BuyerAttribDefined> {
    /**
     * 添加代理商属性
     * @param buyerAttribDefined
     * @return
     */
    int saveBuyerDefine(BuyerAttribDefined buyerAttribDefined);
}