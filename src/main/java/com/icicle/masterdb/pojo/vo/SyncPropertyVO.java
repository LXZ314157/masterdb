package com.icicle.masterdb.pojo.vo;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;

/**
 * @author lvxuezhan
 * @version 2019-08-20 17:47
 */
public class SyncPropertyVO {

    @JSONField(name="main_sys_id")
    private String mainSysId;

    @JSONField(name="operation_status")
    private Integer operationStatus;

    @JSONField(name="table_name")
    private String tableName;

    @JSONField(name="dim_flag")
    private String dimFlag;

    private String code;

    private String description;

    private String propertyName;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getMainSysId() {
        return mainSysId;
    }

    public void setMainSysId(String mainSysId) {
        this.mainSysId = mainSysId;
    }

    public Integer getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(Integer operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDimFlag() {
        return dimFlag;
    }

    public void setDimFlag(String dimFlag) {
        this.dimFlag = dimFlag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
