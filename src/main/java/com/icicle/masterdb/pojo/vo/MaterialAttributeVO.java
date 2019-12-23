package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author liurenhua
 */
public class MaterialAttributeVO {
    private String attrCode;

    private String atrValue;

    private Integer id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAttrCode() {
        return attrCode;
    }

    public void setAttrCode(String attrCode) {
        this.attrCode = attrCode;
    }

    public String getAtrValue() {
        return atrValue;
    }

    public void setAtrValue(String atrValue) {
        this.atrValue = atrValue;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
