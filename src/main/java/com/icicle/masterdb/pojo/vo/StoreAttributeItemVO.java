package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author liurenhua
 */
public class StoreAttributeItemVO {

    private Integer storeAttribItemId;

    private Integer storeAttribDefId;

    private String storeAttribItemCode;

    private String storeAttribItemName;

    private String storeAttribItemDesc;

    private Integer status;

    public Integer getStoreAttribItemId() {
        return storeAttribItemId;
    }

    public void setStoreAttribItemId(Integer storeAttribItemId) {
        this.storeAttribItemId = storeAttribItemId;
    }

    public Integer getStoreAttribDefId() {
        return storeAttribDefId;
    }

    public void setStoreAttribDefId(Integer storeAttribDefId) {
        this.storeAttribDefId = storeAttribDefId;
    }

    public String getStoreAttribItemCode() {
        return storeAttribItemCode;
    }

    public void setStoreAttribItemCode(String storeAttribItemCode) {
        this.storeAttribItemCode = storeAttribItemCode;
    }

    public String getStoreAttribItemName() {
        return storeAttribItemName;
    }

    public void setStoreAttribItemName(String storeAttribItemName) {
        this.storeAttribItemName = storeAttribItemName;
    }

    public String getStoreAttribItemDesc() {
        return storeAttribItemDesc;
    }

    public void setStoreAttribItemDesc(String storeAttribItemDesc) {
        this.storeAttribItemDesc = storeAttribItemDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
