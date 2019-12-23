package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.StoreGroupAttrib;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author liurenhua
 */
public class StoreGroupAttributeListVO {
    private Integer storeAttribGroupId;
    private List<StoreGroupAttrib> storeGroupAttribList;

    public Integer getStoreAttribGroupId() {
        return storeAttribGroupId;
    }

    public void setStoreAttribGroupId(Integer storeAttribGroupId) {
        this.storeAttribGroupId = storeAttribGroupId;
    }

    public List<StoreGroupAttrib> getStoreGroupAttribList() {
        return storeGroupAttribList;
    }

    public void setStoreGroupAttribList(List<StoreGroupAttrib> storeGroupAttribList) {
        this.storeGroupAttribList = storeGroupAttribList;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
