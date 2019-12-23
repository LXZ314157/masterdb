package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Material;
import com.icicle.masterdb.pojo.vo.MaterialVO;

import java.util.List;
import java.util.Map;

/**
 * @author liurenhua
 */
public interface MaterialMapper extends MyMapper<Material> {
    /**
     * 根据materialCode批量更新更新时间和更新状态
     *
     * @param materialCode
     * @param lastUpdatedBy 更新人
     * @return
     */
    int updateStatus(String materialCode, String lastUpdatedBy);

    /**
     * 批量更改物料更新状态的
     *
     * @param ids
     * @return
     */
    int updateSyncStatus(String[] ids);

    /**
     * 查找物料列表
     * @return
     */
    List<MaterialVO> findByQueryCondition(String materialNameOrNo);


    /**
     * 获取产品类别表数据
     * @return
     */
    List<Map<String,Object>> getCatalogList();

    /**
     * 获取物料类型列表
     * @return
     */
    List<Map<String,Object>> getMaterialTypeList();

    /**
     * 获取获取列表数据
     * @return
     */
    List<Map<String,Object>> getCurrencyList();

}