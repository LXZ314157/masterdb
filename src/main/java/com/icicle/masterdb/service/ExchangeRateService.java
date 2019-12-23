package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ExchangeRate;

/**
 * @author liurenhua
 */
public interface ExchangeRateService extends Service<ExchangeRate> {
    /**
     * 更新同一天的汇率
     * @param exchangeRate
     * @return
     */
    int updateByDate(ExchangeRate exchangeRate);

    /**
     * 加载dataTable
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param sSearch
     * @param sortCol 列
     * @param sortDir 排序方式
     * @return
     */
    DataTableRecord listExchangeRate(String sEcho, Integer pageIndex, Integer pageSize, String sSearch, Integer sortCol, String sortDir);

    /**
     * 创建汇率
     * @param exchangeRate
     * @return
     */
    int addConversion(ExchangeRate exchangeRate);

    /**
     * 更新单条汇率（编辑功能）
     * @param exchangeRate
     * @return
     */
    int updateConversion(ExchangeRate exchangeRate);
}