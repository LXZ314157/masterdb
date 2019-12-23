package com.icicle.masterdb.pojo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.icicle.masterdb.util.StringUtil;

/**
 * @author liurenhua
 */
public class SyncCurrencyVO {
    private Integer id;

    @JSONField(name="currency_code")
    private String currencyCode;

    @JSONField(name="currency_name")
    private String currencyName;

    @JSONField(name="currency_desc")
    private String currencyDesc;

    private Short currencyPrecision;
    private Boolean status;
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyDesc() {
        return currencyDesc;
    }

    public void setCurrencyDesc(String currencyDesc) {
        this.currencyDesc = currencyDesc;
    }

    public Short getCurrencyPrecision() {
        return currencyPrecision;
    }

    public void setCurrencyPrecision(Short currencyPrecision) {
        this.currencyPrecision = currencyPrecision;
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
