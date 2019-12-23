package com.icicle.masterdb.pojo.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author lvxuezhan
 * @version 2019-08-15 17:25
 */
public class SyncSelectItemVO {

    @JSONField(name="size_group_id")
    private String sizeGroupId;

    @JSONField(name="size_group_name")
    private String sizeGroupName;

    private String description;


    public String getSizeGroupId() {
        return sizeGroupId;
    }

    public void setSizeGroupId(String sizeGroupId) {
        this.sizeGroupId = sizeGroupId;
    }

    public String getSizeGroupName() {
        return sizeGroupName;
    }

    public void setSizeGroupName(String sizeGroupName) {
        this.sizeGroupName = sizeGroupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
