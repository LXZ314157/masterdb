package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.StoreAttribGroup;
import com.icicle.masterdb.model.StoreGroupAttrib;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author liurenhua
 */
public class StoreSharedDataListVO {
    private List<StoreAttribDefVO> storeAttribDefVOList;
    private List<StoreGroupAttrib> storeGroupAttribList;
    private List<StoreAttribGroup> sotreAttribGroupList ;


    public List<StoreAttribGroup> getSotreAttribGroupList() {
        return sotreAttribGroupList;
    }

    public void setSotreAttribGroupList(List<StoreAttribGroup> sotreAttribGroupList) {
        this.sotreAttribGroupList = sotreAttribGroupList;
    }

    public List<StoreAttribDefVO> getStoreAttribDefVOList() {
        return storeAttribDefVOList;
    }

    public void setStoreAttribDefVOList(List<StoreAttribDefVO> storeAttribDefVOList) {
        this.storeAttribDefVOList = storeAttribDefVOList;
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
