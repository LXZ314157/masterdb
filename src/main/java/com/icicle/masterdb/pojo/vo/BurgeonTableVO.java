package com.icicle.masterdb.pojo.vo;

public class BurgeonTableVO {

    private String dimFlag;

    private String tableName;

    public String getDimFlag() {
        return dimFlag;
    }

    public void setDimFlag(String dimFlag) {
        this.dimFlag = dimFlag;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public BurgeonTableVO(String tableName, String dimFlag) {
        this.dimFlag = dimFlag;
        this.tableName = tableName;
    }
}
