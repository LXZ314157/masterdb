package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.CurrencyMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.Currency;
import com.icicle.masterdb.service.CurrencyService;
import com.icicle.masterdb.util.DateUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class CurrencyServiceImpl extends AbstractService<Currency> implements CurrencyService {
    @Resource
    private CurrencyMapper currencyMapper;

    @Override
    @LogAction(logDesc = "更新币种")
    public int updateCurrency(Currency currency) {
        if (currency != null) {
            Condition condition = new Condition(Currency.class);
            condition.createCriteria().andNotEqualTo("id", currency.getId())
                    .andEqualTo("currencyName", currency.getCurrencyName());
            int count = super.findCountByCondition(condition);
            if (count > 0) {
                //币种名称重复
                return -3;
            }
            currency.setLanguage(SessionManager.getLanguage());
            currency.setLastUpdateDate(DateUtil.now());
            currency.setLastUpdatedBy(SessionManager.getLoginName());
            return currencyMapper.updateCurrency(currency);
        } else {
            return 0;
        }
    }

    @Override
    @LogAction(logDesc = "插入币种")
    public int insertCurrency(Currency currency) {
        if (currency != null) {
            Condition condition = new Condition(Currency.class);
            condition.createCriteria().andEqualTo("currencyCode", currency.getCurrencyCode())
                    .orEqualTo("currencyName", currency.getCurrencyName());
            int count = super.findCountByCondition(condition);
            if (count > 0) {
                //币种编码重复
                return -3;
            }
            currency.setCreatedBy(SessionManager.getLoginName());
            currency.setCreationDate(DateUtil.now());
            currency.setLanguage(SessionManager.getLanguage());
            currency.setLastUpdatedBy(SessionManager.getLoginName());
            currency.setLastUpdateDate(DateUtil.now());
            return currencyMapper.saveCurrency(currency);
        } else {
            return 0;
        }
    }
}