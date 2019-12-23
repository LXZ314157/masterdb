package com.icicle.masterdb.pojo.vo;


import com.icicle.masterdb.util.StringUtil;

/**
 * @author CodeGeneratorUtil
 */
public class ProvinceVO {
    private Integer provinceId;
    private String provinceName;

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

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}