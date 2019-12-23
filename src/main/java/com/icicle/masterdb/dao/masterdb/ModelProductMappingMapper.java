package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ModelProductMapping;

import java.util.List;

/**
 * @author liurenhua
 */
public interface ModelProductMappingMapper extends MyMapper<ModelProductMapping> {

    /**
     * 获取modelCOde和productCOde这两列数据
     *
     * @return
     */
    List<ModelProductMapping> findMappingVO();

    /**
     * 根据映射关系的Id删除映射关系
     *
     * @param mappingId
     * @return
     */
    int deleteByMappingId(Integer mappingId);

    /**
     * 批量更新映射关系
     *
     * @param list
     * @return
     */
    int updateList(List<ModelProductMapping> list);


    /**
     * 获取list中合法的模特编码
     *
     * @param list
     * @return
     */
    List<String> findLegalCodeList(List<String> list);
}