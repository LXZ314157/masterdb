package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author liumingming
 * @version 2017-12-12 11:59.
 */
public class ProductLookVO {
    private Integer lookId;
    private String lookCode;
    private String lookPhotoName;
    private String line;
    private String waveBand;
    private Integer position;
    private String positionDesc;
    private Boolean currentSeason;
    private String year;
    private String devSeason;

    /**
     * @return look_id
     */
    public Integer getLookId() {
        return lookId;
    }

    /**
     * @param lookId
     */
    public void setLookId(Integer lookId) {
        this.lookId = lookId;
    }

    /**
     * @return look_image
     */
    public String getLookCode() {
        return lookCode;
    }

    /**
     * @param lookCode
     */
    public void setLookCode(String lookCode) {
        this.lookCode = lookCode;
    }

    /**
     * @return look_photo_name
     */
    public String getLookPhotoName() {
        return lookPhotoName;
    }

    /**
     * @param lookPhotoName
     */
    public void setLookPhotoName(String lookPhotoName) {
        this.lookPhotoName = lookPhotoName;
    }

    /**
     * @return line
     */
    public String getLine() {
        return line;
    }

    /**
     * @param line
     */
    public void setLine(String line) {
        this.line = line;
    }

    /**
     * @return wave_band
     */
    public String getWaveBand() {
        return waveBand;
    }

    /**
     * @param waveBand
     */
    public void setWaveBand(String waveBand) {
        this.waveBand = waveBand;
    }

    /**
     * @return position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * @param position
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * @return position_desc
     */
    public String getPositionDesc() {
        return positionDesc;
    }

    /**
     * @param positionDesc
     */
    public void setPositionDesc(String positionDesc) {
        this.positionDesc = positionDesc;
    }

    /**
     * @return current_season
     */
    public Boolean getCurrentSeason() {
        return currentSeason;
    }

    /**
     * @param currentSeason
     */
    public void setCurrentSeason(Boolean currentSeason) {
        this.currentSeason = currentSeason;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDevSeason() {
        return devSeason;
    }

    public void setDevSeason(String devSeason) {
        this.devSeason = devSeason;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
