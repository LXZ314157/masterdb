package com.icicle.masterdb.pojo.vo;

/**
 * @author wangyuling
 * @version 2018/8/7 9:59
 */
public class ProExportVO {
    private Integer syncStatus;
    private Integer syncRecord;
    private String line;
    private String productClass;
    private String year;
    private String season;
    private String devSeason;
    private String wave;
    private String band;
    private String icicleGroup;
    private String workGroup;


    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getProductClass() {
        return productClass;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getDevSeason() {
        return devSeason;
    }

    public void setDevSeason(String devSeason) {
        this.devSeason = devSeason;
    }



    public String getWorkGroup() {
        return workGroup;
    }

    public void setWorkGroup(String workGroup) {
        this.workGroup = workGroup;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getIcicleGroup() {
        return icicleGroup;
    }

    public void setIcicleGroup(String icicleGroup) {
        this.icicleGroup = icicleGroup;
    }

    public String getWave() {

        return wave;
    }

    public void setWave(String wave) {
        this.wave = wave;
    }

    public Integer getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(Integer syncStatus) {
        this.syncStatus = syncStatus;
    }

    public Integer getSyncRecord() {
        return syncRecord;
    }

    public void setSyncRecord(Integer syncRecord) {
        this.syncRecord = syncRecord;
    }
}
