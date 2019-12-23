package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.UnitOfMeasure;
import com.icicle.masterdb.pojo.vo.MeasureAndCurrencyVO;

/**
 * @author liurenhua
 */
public interface UnitOfMeasureService extends Service<UnitOfMeasure> {
    /**
     * 添加单位
     * @param unitOfMeasure
     * @return
     */
    int addUom(UnitOfMeasure unitOfMeasure);

    /**
     * 更新单位
     * @param unitOfMeasure
     * @return
     */
    int updateUom(UnitOfMeasure unitOfMeasure);

    /**
     * 获取单位和币种
     * @return
     */
    MeasureAndCurrencyVO getMesaureAndCurrency();
}