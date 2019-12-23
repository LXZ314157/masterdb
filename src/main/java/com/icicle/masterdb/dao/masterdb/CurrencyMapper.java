package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Currency;

/**
 * @author CodeGeneratorUtil
 */
public interface CurrencyMapper extends MyMapper<Currency> {
    /**
     * 添加币种
     * @param currency
     * @return
     */
    int saveCurrency(Currency currency);

    /**
     * 更新币种
     * @param currency
     * @return
     */
    int updateCurrency(Currency currency);
}