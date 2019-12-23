package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.Material;
import com.icicle.masterdb.pojo.bo.SyncMaterial;
import com.icicle.masterdb.pojo.vo.MaterialVO;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;

/**
 * Created by CodeGeneratorUtil on 2017-11-09 15:50:19.
 *
 * @author liurenhua
 */
public interface MaterialService extends Service<Material> {

    /**
     * 批量更新原材料编码的更新状态
     *
     * @param materialList
     * @return
     */
    int updateSyncStatus(String[] materialList);

    /**
     * 获取原材料同步时的全部数据
     * @param codes
     * @return
     */
    SyncMaterial getSyncMaterial(String[] codes);

    /**
     * 查找物料列表
     * @return
     */
    List<MaterialVO> findByQueryCondition(String materialNameOrNo);


    /**
     * 导出物料列表
     * @param list
     * @return
     */
    Workbook exportMaterialExExcel(List <MaterialVO> list);

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