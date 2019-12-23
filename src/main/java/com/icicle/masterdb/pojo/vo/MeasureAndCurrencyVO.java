package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public class MeasureAndCurrencyVO {
    private List<CurrencyVO> currencyVOList;
    private List<UnitOfMeasureVO> unitOfMeasureVOList;

    public List<CurrencyVO> getCurrencyVOList() {
        return currencyVOList;
    }

    public void setCurrencyVOList(List<CurrencyVO> currencyVOList) {
        this.currencyVOList = currencyVOList;
    }

    public List<UnitOfMeasureVO> getUnitOfMeasureVOList() {
        return unitOfMeasureVOList;
    }

    public void setUnitOfMeasureVOList(List<UnitOfMeasureVO> unitOfMeasureVOList) {
        this.unitOfMeasureVOList = unitOfMeasureVOList;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
