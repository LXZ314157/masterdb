package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.*;
import com.icicle.masterdb.model.Material;
import com.icicle.masterdb.pojo.bo.SyncMaterial;
import com.icicle.masterdb.pojo.vo.MaterialVO;
import com.icicle.masterdb.service.MaterialService;
import com.icicle.masterdb.util.ExcelUtil;
import com.icicle.masterdb.util.LogUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author liurenhua
 */
@Service
public class MaterialServiceImpl extends AbstractService<Material> implements MaterialService {
    @Resource
    private MaterialMapper materialMapper;
    @Resource
    private ViewMaterialErpListMapper erpListMapper;
    @Resource
    private ViewMaterialAttrErpListMapper attrErpListMapper;
    @Resource
    private ViewMaterialAttrCatanoMapper attrCatanoMapper;
    @Resource
    private UomMappingMapper uomMappingMapper;

    @Override
    @LogAction(logDesc = "批量同步原材料")
    public int updateSyncStatus(String[] ids) {
        if (ids == null || ids.length == 0) {
            return 0;
        }
        return materialMapper.updateSyncStatus(ids);
    }

    @Override
    public SyncMaterial getSyncMaterial(String[] codes) {
        SyncMaterial syncInfo = new SyncMaterial();
        try {
            syncInfo.setAttrCatanoList(attrCatanoMapper.getMaterialAttrCatano(codes));
            syncInfo.setErpListList(erpListMapper.getMaterialErpList(codes));
            syncInfo.setAttrErpListList(attrErpListMapper.getMaterialAttrErpList(codes));
            syncInfo.setUomMappingList(uomMappingMapper.selectAll());
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
        }
        return syncInfo;
    }

    @Override
    public List<MaterialVO> findByQueryCondition(String materialNameOrNo) {
        return materialMapper.findByQueryCondition(materialNameOrNo);
    }

    @Override
    public Workbook exportMaterialExExcel(List<MaterialVO> list) {
        return ExcelUtil.getMaterialsWorkbook(list);
    }

    @Override
    public List<Map<String, Object>> getCatalogList() {
        return materialMapper.getCatalogList();
    }

    @Override
    public List<Map<String, Object>> getMaterialTypeList() {
        return materialMapper.getMaterialTypeList();
    }

    @Override
    public List<Map<String, Object>> getCurrencyList() {
        return materialMapper.getCurrencyList();
    }
}