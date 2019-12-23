package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author liurenhua
 */
public class StoreLevelVO {
    private String storeLevelName;
    private Integer status;
    private String storeLevelDesc;
    private Integer storeLevelId;

    public Integer getStoreLevelId() {
        return storeLevelId;
    }

    public void setStoreLevelId(Integer storeLevelId) {
        this.storeLevelId = storeLevelId;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }

    public String getStoreLevel() {
        return storeLevelName;
    }

    public void setStoreLevel(String storeLevel) {
        this.storeLevelName = storeLevel;
    }

    public String getStoreLevelDesc() {
        return storeLevelDesc;
    }

    public void setStoreLevelDesc(String storeLevelDesc) {
        this.storeLevelDesc = storeLevelDesc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
