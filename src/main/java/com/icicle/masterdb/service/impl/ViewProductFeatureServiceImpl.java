package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ViewProductFeatureMapper;
import com.icicle.masterdb.model.ViewProductFeature;
import com.icicle.masterdb.service.ViewProductFeatureService;
import com.icicle.masterdb.util.ListUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class ViewProductFeatureServiceImpl extends AbstractService<ViewProductFeature> implements ViewProductFeatureService {
    @Resource
    private ViewProductFeatureMapper viewProductFeatureMapper;

    @Override
    public ViewProductFeature findAllDetail(String productCode) {
        List<ViewProductFeature> viewProductFeature = viewProductFeatureMapper.searchByProductCode(productCode);
        if (viewProductFeature != null && viewProductFeature.size() != 0) {
            return viewProductFeature.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<ViewProductFeature> getRecommadImage(String productCode) {
        List<String> viewProductFeature = viewProductFeatureMapper.getRecommend(productCode);
        if (ListUtil.isBlank(viewProductFeature)) {
            return null;
        }
        List<ViewProductFeature> imageList = new ArrayList<>();
        if (viewProductFeature.get(0) != null && viewProductFeature.get(0).length() != 0) {
            String[] result = viewProductFeature.get(0).split(",");
            for (String item : result) {
                List<ViewProductFeature> imageUrl = viewProductFeatureMapper.getImageUrl(item);
                if (imageUrl != null && imageUrl.size() != 0) {
                    imageList.add(imageUrl.get(0));
                } else {
                    return null;
                }
            }
            return imageList;
        } else {
            return null;
        }
    }
}