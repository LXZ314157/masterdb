package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewMaterial;
import com.icicle.masterdb.pojo.vo.ViewMaterialDetailVO;
/**
 * @author liurenhua
 */
public interface ViewMaterialMapper extends MyMapper<ViewMaterial> {
    /**
     * 获取原材料属性的详细信息  包含原材料属性描述、成本和价格
     * @param materialCode
     * @return
     */
    ViewMaterialDetailVO findMateriaByCode(String materialCode);
}