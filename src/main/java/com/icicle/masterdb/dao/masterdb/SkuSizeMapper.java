package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.SkuSize;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface SkuSizeMapper extends MyMapper<SkuSize> {
    /**
     *进行数据验证
     * @return 得到所有的size
     */
    List<SkuSize>  findBySize();
}