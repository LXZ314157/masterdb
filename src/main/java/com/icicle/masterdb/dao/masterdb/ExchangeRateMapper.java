package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ExchangeRate;
/**
 * @author liurenhua
 */
public interface ExchangeRateMapper extends MyMapper<ExchangeRate> {
    /**更新汇率
     * @param exchangeRate
     * @return
     */
    int updateByDate(ExchangeRate exchangeRate);

    int saveExchangeRate(ExchangeRate exchangeRate);
}