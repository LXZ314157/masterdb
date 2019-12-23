package com.icicle.masterdb.pojo.vo;


import com.icicle.masterdb.util.StringUtil;

/**
 * @author liurenhua
 */
public class StoreTypeVO {
    private Integer storeTypeId;
    private String storeTypeName;

    public Integer getStoreTypeId() {
        return storeTypeId;
    }

    public void setStoreTypeId(Integer storeTypeId) {
        this.storeTypeId = storeTypeId;
    }

    public String getStoreTypeName() {
        return storeTypeName;
    }

    public void setStoreTypeName(String storeTypeName) {
        this.storeTypeName = storeTypeName;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
