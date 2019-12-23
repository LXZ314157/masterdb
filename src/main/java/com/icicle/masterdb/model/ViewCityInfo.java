package com.icicle.masterdb.model;

import javax.persistence.*;

@Table(name = "view_city_info")
public class ViewCityInfo {
    @Id
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "area_id")
    private Integer areaId;

    @Column(name = "area_code")
    private String areaCode;

    @Column(name = "area_name")
    private String areaName;

    @Column(name = "city_level_id")
    private Integer cityLevelId;

    @Column(name = "city_level_name")
    private String cityLevelName;

    /**
     * @return city_id
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * @param cityId
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * @return city_name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return area_id
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * @param areaId
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * @return area_code
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @param areaCode
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * @return area_name
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * @param areaName
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * @return city_level_id
     */
    public Integer getCityLevelId() {
        return cityLevelId;
    }

    /**
     * @param cityLevelId
     */
    public void setCityLevelId(Integer cityLevelId) {
        this.cityLevelId = cityLevelId;
    }

    /**
     * @return city_level_name
     */
    public String getCityLevelName() {
        return cityLevelName;
    }

    /**
     * @param cityLevelName
     */
    public void setCityLevelName(String cityLevelName) {
        this.cityLevelName = cityLevelName;
    }
}