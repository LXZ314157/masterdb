


package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Fabric;
import com.icicle.masterdb.pojo.vo.FabricVO;

import java.util.List;

/**
 * @author liurenhua
 */
public interface FabricMapper extends MyMapper<Fabric> {
    /**
     * 添加一个面料
     *
     * @param fabric
     * @return
     */
    int saveFabric(Fabric fabric);

    /**
     * 获取所有的物料编码和物料名称
     *
     * @return
     */
    List<Fabric> selectFabricPart();

    /**
     * 得到list中存在的编码
     *
     * @param list
     * @return
     */
    List<String> checkFabricCode(List<Fabric> list);

    /**
     * 验证面料并返回面料名
     *
     * @param fabricCode
     * @return
     */
    Fabric searchFabric(String fabricCode);

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    int batchUpdateFabric(List<Fabric> list);


    /**
     * 获取面料列表
     * @param fabiricNameOrNo
     * @return
     */
    List<FabricVO> findByQueryCondition(String fabiricNameOrNo);

}