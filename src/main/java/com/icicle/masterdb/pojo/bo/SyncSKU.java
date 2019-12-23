package com.icicle.masterdb.pojo.bo;

import com.icicle.masterdb.model.*;

import java.util.List;

/**
 * @author liumingming
 * @version 2018-03-15 13:50.
 */
public class SyncSKU {
    private List<ViewProductdetail> cacheList;
    private List<ViewProductErpList> erpList;
    private List<ViewProductErpAttrList> erpAttrList;
    private List<ViewProductEmaxAttrList> emaxAttrList;
    private List<ViewProductEmaxSizeList> sizeList;

    public List<ViewProductdetail> getCacheList() {
        return cacheList;
    }

    public void setCacheList(List<ViewProductdetail> cacheList) {
        this.cacheList = cacheList;
    }

    public List<ViewProductErpList> getErpList() {
        return erpList;
    }

    public void setErpList(List<ViewProductErpList> erpList) {
        this.erpList = erpList;
    }

    public List<ViewProductErpAttrList> getErpAttrList() {
        return erpAttrList;
    }

    public void setErpAttrList(List<ViewProductErpAttrList> erpAttrList) {
        this.erpAttrList = erpAttrList;
    }

    public List<ViewProductEmaxAttrList> getEmaxAttrList() {
        return emaxAttrList;
    }

    public void setEmaxAttrList(List<ViewProductEmaxAttrList> emaxAttrList) {
        this.emaxAttrList = emaxAttrList;
    }

    public List<ViewProductEmaxSizeList> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<ViewProductEmaxSizeList> sizeList) {
        this.sizeList = sizeList;
    }
}
