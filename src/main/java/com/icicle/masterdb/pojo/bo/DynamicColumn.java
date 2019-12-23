package com.icicle.masterdb.pojo.bo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author liumingming
 * @version 2018-01-02 14:41.
 */
public class DynamicColumn {
    private String columnName;
    private String dataType;
    private Integer dataLength;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
