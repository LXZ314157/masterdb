package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.BuyerAttributeMapper;
import com.icicle.masterdb.model.BuyerAttribute;
import com.icicle.masterdb.service.BuyerAttributeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
@Service
public class BuyerAttributeServiceImpl extends AbstractService<BuyerAttribute> implements BuyerAttributeService {
    @Resource
    private BuyerAttributeMapper buyerAttributeMapper;

    @Override
    public int updateBuyerAttribute(List<BuyerAttribute> buyerAttributeList){
        return buyerAttributeMapper.updateBuyerAttribute(buyerAttributeList);
    }

    @Override
    public int batchDeleteBuyerAttribute(List<Integer> list,Integer buyerNo) {
        return buyerAttributeMapper.batchDeleteBuyerAttribute(list,buyerNo);
    }
}