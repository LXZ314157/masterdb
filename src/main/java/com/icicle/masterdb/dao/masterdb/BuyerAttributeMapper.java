package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.BuyerAttribute;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface BuyerAttributeMapper extends MyMapper<BuyerAttribute> {

    /**
     * 批量更新itemValue
     *
     * @param buyerAttributeList
     * @return
     */
    int updateBuyerAttribute(List<BuyerAttribute> buyerAttributeList);

    /**
     * 批量删除代理商属性
     * @param list
     * @return
     */
    int batchDeleteBuyerAttribute(@Param("list") List<Integer> list,@Param("buyerNo") Integer buyerNo);
}