package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author CodeGeneratorUtil
 */
public class CityVO {
    private Integer cityId;
    private String cityName;
    private Integer cityLevelId;
    private String cityCode;
    private Integer areaId;
    private Integer provinceId;

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

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
