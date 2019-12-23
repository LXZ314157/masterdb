package com.icicle.masterdb.core;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author liumingming
 */
public class DataTableRecord {
    private String sEcho;
    private Integer iTotalRecords;
    private Integer iTotalDisplayRecords;
    private Object aaData;

    public DataTableRecord(String sEcho, Integer iTotalRecords, Integer iTotalDisplayRecords, Object aaData) {
        this.sEcho = sEcho;
        this.iTotalRecords = iTotalRecords;
        this.iTotalDisplayRecords = iTotalDisplayRecords;
        this.aaData = aaData;
    }

    public String getSEcho() {
        return sEcho;
    }

    public void setSEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public Integer getITotalRecords() {
        return iTotalRecords;
    }

    public void setITotalRecords(Integer iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public Integer getITotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setITotalDisplayRecords(Integer iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public Object getAaData() {
        return aaData;
    }

    public void setAaData(Object aaData) {
        this.aaData = aaData;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
