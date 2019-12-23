package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.Currency;

/**
 * @author CodeGeneratorUtil
 */
public interface CurrencyService extends Service<Currency> {

    /**
     *单条插入币种
     * @param currency
     * @return
     */
   int insertCurrency(Currency currency);

    /**
     * 单条更新币种
     * @param currency
     * @return
     */
    int updateCurrency(Currency currency);


}