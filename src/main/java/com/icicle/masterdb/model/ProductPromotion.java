package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "product_promotion")
public class ProductPromotion {
    @Id
    @Column(name = "promotion_id")
    private String promotionId;

    @Column(name = "promotion_code")
    private String promotionCode;

    @Column(name = "promotion_img_name")
    private String promotionImgName;

    private String year;

    @Column(name = "dev_season")
    private String devSeason;

    @Column(name = "wave_band")
    private String waveBand;

    private String line;

    @Column(name = "out_of_date")
    private Date outOfDate;

    private Integer theme;

    @Column(name = "show_type")
    private Integer showType;

    private String createby;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_up_date")
    private Date lastUpDate;

    @Column(name = "last_update_by")
    private String lastUpdateBy;

    /**
     * @return promotion_id
     */
    public String getPromotionId() {
        return promotionId;
    }

    /**
     * @param promotionId
     */
    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    /**
     * @return promotion_code
     */
    public String getPromotionCode() {
        return promotionCode;
    }

    /**
     * @param promotionCode
     */
    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    /**
     * @return promotion_img_name
     */
    public String getPromotionImgName() {
        return promotionImgName;
    }

    /**
     * @param promotionImgName
     */
    public void setPromotionImgName(String promotionImgName) {
        this.promotionImgName = promotionImgName;
    }

    /**
     * @return year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return dev_season
     */
    public String getDevSeason() {
        return devSeason;
    }

    /**
     * @param devSeason
     */
    public void setDevSeason(String devSeason) {
        this.devSeason = devSeason;
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
     * @return out_of_date
     */
    public Date getOutOfDate() {
        return outOfDate;
    }

    /**
     * @param outOfDate
     */
    public void setOutOfDate(Date outOfDate) {
        this.outOfDate = outOfDate;
    }

    /**
     * @return theme
     */
    public Integer getTheme() {
        return theme;
    }

    /**
     * @param theme
     */
    public void setTheme(Integer theme) {
        this.theme = theme;
    }

    /**
     * @return show_type
     */
    public Integer getShowType() {
        return showType;
    }

    /**
     * @param showType
     */
    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    /**
     * @return createby
     */
    public String getCreateby() {
        return createby;
    }

    /**
     * @param createby
     */
    public void setCreateby(String createby) {
        this.createby = createby;
    }

    /**
     * @return creation_date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return last_up_date
     */
    public Date getLastUpDate() {
        return lastUpDate;
    }

    /**
     * @param lastUpDate
     */
    public void setLastUpDate(Date lastUpDate) {
        this.lastUpDate = lastUpDate;
    }

    /**
     * @return last_update_by
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * @param lastUpdateBy
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
}