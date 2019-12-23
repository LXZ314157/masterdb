package com.icicle.masterdb.pojo.vo;

/**
 * @author lvxuezhan
 * @version 2019-08-12 17:37
 */
public class SyncCityVO {

    private Integer city_id;

    private Integer opration_status;

    private String city_code;

    private String city_name;

    private String province_name;

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public Integer getOpration_status() {
        return opration_status;
    }

    public void setOpration_status(Integer opration_status) {
        this.opration_status = opration_status;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }
}
