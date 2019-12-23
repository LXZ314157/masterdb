package com.icicle.masterdb.pojo.bo;

import com.icicle.masterdb.model.UomMapping;
import com.icicle.masterdb.model.ViewMaterialAttrCatano;
import com.icicle.masterdb.model.ViewMaterialAttrErpList;
import com.icicle.masterdb.model.ViewMaterialErpList;

import java.util.List;

/**
 * @author liumingming
 * @version 2018-03-20 15:34.
 */
public class SyncMaterial {
    private List<ViewMaterialAttrCatano> attrCatanoList;
    private List<ViewMaterialAttrErpList> attrErpListList;
    private List<ViewMaterialErpList> erpListList;
    private List<UomMapping> uomMappingList;

    public List<ViewMaterialAttrCatano> getAttrCatanoList() {
        return attrCatanoList;
    }

    public void setAttrCatanoList(List<ViewMaterialAttrCatano> attrCatanoList) {
        this.attrCatanoList = attrCatanoList;
    }

    public List<ViewMaterialAttrErpList> getAttrErpListList() {
        return attrErpListList;
    }

    public void setAttrErpListList(List<ViewMaterialAttrErpList> attrErpListList) {
        this.attrErpListList = attrErpListList;
    }

    public List<ViewMaterialErpList> getErpListList() {
        return erpListList;
    }

    public void setErpListList(List<ViewMaterialErpList> erpListList) {
        this.erpListList = erpListList;
    }

    public List<UomMapping> getUomMappingList() {
        return uomMappingList;
    }

    public void setUomMappingList(List<UomMapping> uomMappingList) {
        this.uomMappingList = uomMappingList;
    }
}
