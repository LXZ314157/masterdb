package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author liurenhua
 */
public class CityLevelVO {
    private Integer cityLevelId;
    private String cityLevelName;
    private String cityLevelDesc;
    private Integer status;

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

    /**
     * @return city_level_desc
     */
    public String getCityLevelDesc() {
        return cityLevelDesc;
    }

    /**
     * @param cityLevelDesc
     */
    public void setCityLevelDesc(String cityLevelDesc) {
        this.cityLevelDesc = cityLevelDesc;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
