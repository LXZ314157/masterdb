package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.ExchangeRateMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ExchangeRate;
import com.icicle.masterdb.service.ExchangeRateService;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PageUtil;
import com.icicle.masterdb.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

import static com.icicle.masterdb.service.constant.ServiceConstant.ASC;


/**
 * @author liurenhua
 */
@Service
public class ExchangeRateServiceImpl extends AbstractService<ExchangeRate> implements ExchangeRateService {
    @Resource
    private ExchangeRateMapper exchangeRateMapper;

    private final String CURRENCY_UNVALID = "0";

    @Override
    @LogAction(logDesc = "更新同一天的汇率")
    public int updateByDate(ExchangeRate exchangeRate) {
        try {
            ExchangeRate result = getUpdate(exchangeRate);
            if (result != null) {
                return exchangeRateMapper.updateByDate(result);
            } else {
                return 0;
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public DataTableRecord listExchangeRate(String sEcho, Integer pageIndex, Integer pageSize, String sSearch, Integer sortCol, String sortDir) {
        //获取类里面的所有属性
        sortDir = PageUtil.orderMethod(sortDir);
        String orderColoum = PageUtil.orderBy(sortCol, ExchangeRate.class);
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ExchangeRate> exchangeRateList;
        Condition condition = new Condition(ExchangeRate.class);
        if (!StringUtils.isBlank(sSearch)) {
            String words = StringUtil.gsFormat("%{0}%", sSearch);
            condition.createCriteria().andLike("fromCurrency", words)
                    .orLike("toCurrency", words);
        }
        if (ASC.equals(sortDir)) {
            condition.orderBy(orderColoum).asc();
        } else {
            condition.orderBy(orderColoum).desc();
        }
        try {
            exchangeRateList = exchangeRateMapper.selectByCondition(condition);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }
        PageInfo pageInfo = new PageInfo(exchangeRateList);
        dataTableRecord.setITotalRecords(pageSize);
        dataTableRecord.setITotalDisplayRecords((int) pageInfo.getTotal());
        dataTableRecord.setAaData(exchangeRateList);
        return dataTableRecord;
    }

    @Override
    @LogAction(logDesc = "添加汇率")
    public int addConversion(ExchangeRate exchangeRate) {
        if (exchangeRate == null) {
            return 0;
        }
        if (exchangeRate.getFromCurrency() != CURRENCY_UNVALID && exchangeRate.getToCurrency() != CURRENCY_UNVALID) {
            exchangeRate.setCreatedBy(SessionManager.getLoginName());
            try {
               return exchangeRateMapper.saveExchangeRate(exchangeRate);
            } catch (Exception e) {
                LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                return 0;
            }
        } else {
            return 0;
        }
    }


    @Override
    @LogAction(logDesc = "单条更新汇率")
    public int updateConversion(ExchangeRate exchangeRate) {
        if (exchangeRate == null) {
            return 0;
        }
        try {
            ExchangeRate result = getUpdate(exchangeRate);
            if (result != null) {
                return super.update(result);
            } else {
                return -1;
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return 0;
        }
    }

    private ExchangeRate getUpdate(ExchangeRate exchangeRate) {
        if (exchangeRate == null) {
            return null;
        }
        if (exchangeRate.getFromCurrency() != CURRENCY_UNVALID && exchangeRate.getToCurrency() != CURRENCY_UNVALID) {
            exchangeRate.setLastUpdatedBy(SessionManager.getLoginName());
            exchangeRate.setLastUpdateDate(DateUtil.now());
            return exchangeRate;
        } else {
            return null;
        }
    }

}