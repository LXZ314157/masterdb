package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

import java.math.BigDecimal;

/**
 * @author CodeGeneratorUtil
 */
public class ConversionRateVO {
    private Integer id;
    private String fromCurrency;
    private String toCurrency;
    private String conversionDate;
    private BigDecimal conversionRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public String getConversionDate() {
        return conversionDate;
    }

    public void setConversionDate(String conversionDate) {
        this.conversionDate = conversionDate;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
