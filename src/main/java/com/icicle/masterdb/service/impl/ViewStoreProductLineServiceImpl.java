package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.ViewStoreProductLineMapper;
import com.icicle.masterdb.model.ProductLine;
import com.icicle.masterdb.model.ViewStoreProductLine;
import com.icicle.masterdb.pojo.vo.ProductLineVO;
import com.icicle.masterdb.pojo.vo.ViewStoreProductLineVO;
import com.icicle.masterdb.service.ViewStoreProductLineService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-02-22 18:09:21.
*/
@Service
public class ViewStoreProductLineServiceImpl extends AbstractService<ViewStoreProductLine> implements ViewStoreProductLineService {
    @Resource
    private ViewStoreProductLineMapper viewStoreProductLineMapper;

    @Override
    public List<ViewStoreProductLine> findViewProductLineByStoreNo(String storeNo) {

        return viewStoreProductLineMapper.findProductLineByStoreNo(storeNo);
    }

    @Override
    public List<ProductLineVO> checkProductLine(List<ViewStoreProductLine> viewStoreProductLineList, List<ProductLineVO> productLineVOList) {

        if(!viewStoreProductLineList.isEmpty()){
            if(!productLineVOList.isEmpty()){
                for(ProductLineVO productLineVO : productLineVOList){
                    for(ViewStoreProductLine viewStoreProductLine : viewStoreProductLineList){
                        if(productLineVO.getProductLineNo().equals(viewStoreProductLine.getProductLineNo())){
                            productLineVO.setCheck(true);
                        }
                    }
                }
            }

        }
        return productLineVOList;
    }
}