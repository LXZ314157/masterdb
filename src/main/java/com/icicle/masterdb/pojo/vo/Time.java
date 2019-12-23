package com.icicle.masterdb.pojo.vo;

/**
 * @author wangyuling
 * @version 2018/7/9 17:00
 */
public class Time {
    private long statTime;
    private long endTime;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getStatTime() {
        return statTime;
    }

    public void setStatTime(long statTime) {
        this.statTime = statTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
