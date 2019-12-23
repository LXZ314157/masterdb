package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author liurenhua
 */
public class BusinessUnitVO {
    private Integer buId;
    private String buName;
    private String buDesc;
    private Integer status;

    /**
     * @return bu_id
     */
    public Integer getBuId() {
        return buId;
    }

    /**
     * @param buId
     */
    public void setBuId(Integer buId) {
        this.buId = buId;
    }

    /**
     * @return bu_name
     */
    public String getBuName() {
        return buName;
    }

    /**
     * @param buName
     */
    public void setBuName(String buName) {
        this.buName = buName;
    }

    public String getBuDesc() {
        return buDesc;
    }

    public void setBuDesc(String buDesc) {
        this.buDesc = buDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
