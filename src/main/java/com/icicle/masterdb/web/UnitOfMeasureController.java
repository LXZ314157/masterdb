package com.icicle.masterdb.web;

import com.icicle.masterdb.authorization.annotation.Authorization;
import com.icicle.masterdb.config.SynConfigEntity;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.core.ResultGenerator;
import com.icicle.masterdb.model.Currency;
import com.icicle.masterdb.model.ExchangeRate;
import com.icicle.masterdb.model.UnitOfMeasure;
import com.icicle.masterdb.pojo.vo.ExchangeRateVO;
import com.icicle.masterdb.pojo.vo.MeasureAndCurrencyVO;
import com.icicle.masterdb.pojo.vo.SyncCurrencyVO;
import com.icicle.masterdb.service.CurrencyService;
import com.icicle.masterdb.service.ExchangeRateService;
import com.icicle.masterdb.service.UnitOfMeasureService;
import com.icicle.masterdb.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;

import static com.icicle.masterdb.core.ProjectConstant.UNKNOWN_EXCEPTION_CODE;
import static com.icicle.masterdb.service.constant.ServiceConstant.DATA_TRANS_ERROR;
import static com.icicle.masterdb.service.constant.ServiceConstant.ID_REAPT;

/**
 * @author wangyuling
 */
@Controller
@RequestMapping("/unitofmeasure")
@Authorization
public class UnitOfMeasureController {
    @Resource
    private UnitOfMeasureService unitOfMeasureService;
    @Resource
    private CurrencyService currencyService;
    @Resource
    private ExchangeRateService exchangeRateService;
    @Resource
    private SynConfigEntity synConfigEntity;

    @PostMapping("/adduom")
    @ResponseBody
    public Result addUom(@RequestBody UnitOfMeasure unitOfMeasure) {
        int check = checkUom(unitOfMeasure);
        if (check == DATA_TRANS_ERROR) {
            //单位编码不能为空
            return ResultGenerator.genFailResult(LanguageUtil.getText(1386));
        } else if (check == ID_REAPT) {
            //单位编码或单位名称已经存在，不可重复添加
            return ResultGenerator.genFailResult("单位编码或单位名称已经存在，不可重复添加");
        } else {
            int ret = unitOfMeasureService.addUom(unitOfMeasure);
            if (ret > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                //"添加失败，请稍候再试"
                return ResultGenerator.genFailResult(LanguageUtil.getText(1068));
            }
        }
    }

    @PutMapping("/updateuom")
    @ResponseBody
    public Result updateUom(@RequestBody UnitOfMeasure unitOfMeasure) {
        int check = checkUom(unitOfMeasure);
        if (check == DATA_TRANS_ERROR) {
            return ResultGenerator.genFailResult(LanguageUtil.getText(1386));
        } else if (check == ID_REAPT) {
            //""单位名称已经存在，无法更新"
            return ResultGenerator.genFailResult("单位名称已经存在，无法更新");
        } else {
            int ret = unitOfMeasureService.updateUom(unitOfMeasure);
            if (ret > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("更新失败，请稍候再试");
            }
        }

    }

    @PostMapping("/addconversion")
    @ResponseBody
    public Result addConversion(@RequestBody ExchangeRate exchangeRate) {
        int check = checkConversionRate(exchangeRate);
        int ret = 0;
        if (check == DATA_TRANS_ERROR) {
            //币种从和币种至种均不能为空
            return ResultGenerator.genFailResult(LanguageUtil.getText(1397));
        } else if (check == ID_REAPT) {
            ret = exchangeRateService.updateByDate(exchangeRate);
        } else {
            ret = exchangeRateService.addConversion(exchangeRate);
        }
        if (ret > 0) {
            return getSyncExchangeRateResult(exchangeRate);
        } else {
            //"添加失败，请稍候再试"
            return ResultGenerator.genFailResult(LanguageUtil.getText(1068));
        }
    }

    private Result getSyncExchangeRateResult(ExchangeRate exchangeRate){
        ExchangeRateVO exchangeRateVO = new ExchangeRateVO();
        exchangeRateVO.setId(exchangeRate.getId());
        exchangeRateVO.setFromCurrency(exchangeRate.getFromCurrency());
        exchangeRateVO.setToCurrency(exchangeRate.getToCurrency());
        exchangeRateVO.setConversionRate(exchangeRate.getConversionRate());
        exchangeRateVO.setConversionDate(DateUtil.date2YYMMDDs(exchangeRate.getConversionDate()));
        exchangeRateVO.setConversionEndDate(DateUtil.date2YYMMDDs(exchangeRate.getConversionEndDate()));
        boolean success = SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getExchangeRateSync2Burgeon()),exchangeRateVO);
        if(success){
            return ResultGenerator.genSuccessResult("税率同步成功");
        }else{
            return ResultGenerator.genSuccessResult("税率同步失败");
        }
    }

    @PutMapping("/updateconversionrate")
    @ResponseBody
    public Result updateConversionRate(@RequestBody ExchangeRate exchangeRate) {
        int check = checkConversionRate(exchangeRate);
        if (check == DATA_TRANS_ERROR) {
            //币种从和币种至种均不能为空
            return ResultGenerator.genFailResult(LanguageUtil.getText(1397));
        } else if (check == ID_REAPT) {
            //币种从和币种至不存在，不能更新
            return ResultGenerator.genFailResult(LanguageUtil.getText(1400));
        } else {
            int ret = exchangeRateService.updateConversion(exchangeRate);
            if (ret > 0) {
                return getSyncExchangeRateResult(exchangeRate);
            } else {
                return ResultGenerator.genFailResult("更新失败，请稍候再试");
            }
        }

    }

    @PostMapping("/addcurrency")
    @ResponseBody
    public Result addCurrency(@RequestBody Currency currency) {
        int check = checkCurrency(currency);
        if (check == DATA_TRANS_ERROR) {
            //币种编码或币种名称不能为空
            return ResultGenerator.genFailResult(LanguageUtil.getText(1401));
        } else if (check == -1) {
            //币种语言或币种精度不能为空
            return ResultGenerator.genFailResult(LanguageUtil.getText(1424));
        } else {
            try {
                int ret = currencyService.insertCurrency(currency);

                if (ret == ID_REAPT) {
                    //币种编码或币种名称不能重复
                    return ResultGenerator.genFailResult("币种编码或币种名称不能重复");
                }
                if (ret <= 0) {
                    //添加失败，请稍后重试
                    return ResultGenerator.genFailResult(LanguageUtil.getText(1068));
                }
                SyncCurrencyVO syncCurrencyVO = PojoConvertUtil.convertPojo(currency,SyncCurrencyVO.class);
                if(SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getCurrencySync2Burgeon()),syncCurrencyVO)){
                    return ResultGenerator.genSuccessResult("币种同步成功");
                }
                return ResultGenerator.genSuccessResult();
            } catch (Exception e) {
                LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
            }
        }
    }

    @PutMapping("/updatecurrency")
    @ResponseBody
    public Result updateCurrency(@RequestBody Currency currency) {
        int check = checkCurrency(currency);
        if (check == DATA_TRANS_ERROR) {
            //币种编码或币种名称不能为空
            return ResultGenerator.genFailResult(LanguageUtil.getText(1401));
        } else if (check == -1) {
            //"币种语言或币种精度不能为空"
            return ResultGenerator.genFailResult(LanguageUtil.getText(1424));
        } else {
            try {
                int ret = currencyService.updateCurrency(currency);
                if (ret == ID_REAPT) {
                    //币种名称不能重复
                    return ResultGenerator.genFailResult("币种编码或币种名称不能重复");
                }
                if (ret > 0) {
                    SyncCurrencyVO syncCurrencyVO = PojoConvertUtil.convertPojo(currency,SyncCurrencyVO.class);
                    if(SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getCurrencySync2Burgeon()),syncCurrencyVO)){
                        return ResultGenerator.genSuccessResult("币种同步成功");
                    }
                    return ResultGenerator.genSuccessResult();
                } else {
                    return ResultGenerator.genFailResult("更新失败，请稍候再试");
                }
            } catch (Exception e) {
                return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
            }
        }
    }

    @PostMapping("/getmesaureandcurrency")
    @ResponseBody
    public Result getMesaureAndCurrency() {
        try {
            MeasureAndCurrencyVO measureAndCurrencyVO = unitOfMeasureService.getMesaureAndCurrency();
            if (measureAndCurrencyVO != null) {
                return ResultGenerator.genSuccessResult(measureAndCurrencyVO);
            } else {
                //数据获取为空，请稍后重试
                return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }

    }

    @PostMapping("/listexchangerate")
    @ResponseBody
    public DataTableRecord listExchangeRate(@RequestParam(value = "sEcho") String sEcho,
                                            @RequestParam(value = "iDisplayStart") Integer pageIndex,
                                            @RequestParam(value = "iDisplayLength") Integer pageSize,
                                            @RequestParam(value = "sSearch") String sSearch,
                                            @RequestParam(value = "iSortCol_0") Integer sortCol,
                                            @RequestParam(value = "sSortDir_0") String sortDir) {
        DataTableRecord dataTableRecord = exchangeRateService.listExchangeRate(sEcho, pageIndex, pageSize, sSearch, sortCol, sortDir);
        return dataTableRecord;
    }

    private int checkConversionRate(ExchangeRate exchangeRate) {
        if (StringUtils.isBlank(exchangeRate.getFromCurrency()) || StringUtils.isBlank(exchangeRate.getToCurrency())) {
            return -2;
        }
        Condition condition = new Condition(ExchangeRate.class);
        if (exchangeRate.getId() != null) {
            //更新
            condition.createCriteria().andEqualTo("id", exchangeRate.getId())
                    .andNotEqualTo("fromCurrency", exchangeRate.getFromCurrency())
                    .andNotEqualTo("toCurrency", exchangeRate.getToCurrency());
        } else {
            //新增
            condition.createCriteria()
                    .andEqualTo("fromCurrency", exchangeRate.getFromCurrency())
                    .andEqualTo("toCurrency", exchangeRate.getToCurrency())
                    .andEqualTo("conversionDate", exchangeRate.getConversionDate());
        }
        try {
            int count = exchangeRateService.findCountByCondition(condition);
            if (count > 0) {
                //违反了名称唯一性约束
                return -3;
            }
            return 1;
        } catch (Exception ex) {
            return UNKNOWN_EXCEPTION_CODE;
        }

    }

    private int checkUom(UnitOfMeasure unitOfMeasure) {
        if (StringUtils.isBlank(unitOfMeasure.getUomCode())) {
            return DATA_TRANS_ERROR;
        }
        Condition condition = new Condition(UnitOfMeasure.class);
        if (unitOfMeasure.getId() != null) {
            //更新
            condition.createCriteria().andNotEqualTo("id", unitOfMeasure.getId())
                    .andEqualTo("unitOfMeasure", unitOfMeasure.getUnitOfMeasure());
        } else {
            condition.createCriteria().andEqualTo("uomCode", unitOfMeasure.getUomCode())
                    .orEqualTo("unitOfMeasure", unitOfMeasure.getUnitOfMeasure());
        }
        try {
            int count = unitOfMeasureService.findCountByCondition(condition);
            if (count > 0) {
                //违反了名称唯一性约束
                return ID_REAPT;
            }
            return 1;
        } catch (Exception ex) {
            return UNKNOWN_EXCEPTION_CODE;
        }
    }

    private int checkCurrency(Currency currency) {
        if (StringUtils.isBlank(currency.getCurrencyCode())
                || StringUtils.isBlank(currency.getCurrencyName())) {
            return -2;
        }
        if (currency.getCurrencyPrecision() == null) {
            return -1;
        }
        //编码转换为大写
        currency.setCurrencyCode(currency.getCurrencyCode().toUpperCase());
        return 1;
    }
}