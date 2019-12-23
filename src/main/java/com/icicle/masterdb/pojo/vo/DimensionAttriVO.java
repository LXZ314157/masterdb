package com.icicle.masterdb.pojo.vo;


import com.icicle.masterdb.util.StringUtil;
/**
 * @author liurenhua
 */
public class DimensionAttriVO {
    private Integer id;

    private Integer dimensionId;

    private Integer attributeId;

    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(Integer dimensionId) {
        this.dimensionId = dimensionId;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
