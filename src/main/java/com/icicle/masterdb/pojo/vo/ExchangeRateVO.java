package com.icicle.masterdb.pojo.vo;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liurenhua
 */
@Table(name = "exchange_rate")
public class ExchangeRateVO {
    @Id
    private Integer id;

    @JSONField(name = "from_currency")
    private String fromCurrency;

    @JSONField(name = "to_currency")
    private String toCurrency;

    @JSONField(name = "conversion_rate")
    private BigDecimal conversionRate;

    @JSONField(name = "conversion_date")
    private String conversionDate;

    @JSONField(name = "conversion_end_date")
    private String conversionEndDate;


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

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }

    public String getConversionDate() {
        return conversionDate;
    }

    public void setConversionDate(String conversionDate) {
        this.conversionDate = conversionDate;
    }

    public String getConversionEndDate() {
        return conversionEndDate;
    }

    public void setConversionEndDate(String conversionEndDate) {
        this.conversionEndDate = conversionEndDate;
    }
}