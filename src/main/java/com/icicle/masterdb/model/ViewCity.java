package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "view_city")
public class ViewCity {
    @Id
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "pa_city_name")
    private String paCityName;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "city_level_name")
    private String cityLevelName;

    @Column(name = "area_name")
    private String areaName;

    @Column(name = "province_id")
    private Integer provinceId;

    @Column(name = "city_level_id")
    private Integer cityLevelId;

    @Column(name = "area_id")
    private Integer areaId;

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

    public String getPaCityName() {
        return paCityName;
    }

    public void setPaCityName(String paCityName) {
        this.paCityName = paCityName;
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
     * @return city_code
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * @param cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * @return province_name
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * @param provinceName
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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
     * @return province_id
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * @param provinceId
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
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
}