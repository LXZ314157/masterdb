package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.UnitOfMeasureMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.Currency;
import com.icicle.masterdb.model.UnitOfMeasure;
import com.icicle.masterdb.pojo.vo.CurrencyVO;
import com.icicle.masterdb.pojo.vo.MeasureAndCurrencyVO;
import com.icicle.masterdb.pojo.vo.UnitOfMeasureVO;
import com.icicle.masterdb.service.CurrencyService;
import com.icicle.masterdb.service.UnitOfMeasureService;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PojoConvertUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangyuling
 */
@Service
public class UnitOfMeasureServiceImpl extends AbstractService<UnitOfMeasure> implements UnitOfMeasureService {

    @Resource
    private CurrencyService currencyService;

    @Resource
    private  UnitOfMeasureMapper unitOfMeasureMapper;

    @Override
    @LogAction(logDesc = "添加单位")
    public int addUom(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure == null) {
            return 0;
        }
        if(unitOfMeasure.getUomClass()==null){
            unitOfMeasure.setUomClass("");
        }
        unitOfMeasure.setLanguage(SessionManager.getLanguage());
        unitOfMeasure.setStatus(1);
        unitOfMeasure.setBaseUomFlag(true);
        unitOfMeasure.setCreatedBy(SessionManager.getLoginName());
        unitOfMeasure.setCreationDate(DateUtil.now());
        unitOfMeasure.setLastUpdatedBy(SessionManager.getLoginName());
        unitOfMeasure.setLastUpdateDate(DateUtil.now());
        try {
            return super.save(unitOfMeasure);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    @LogAction(logDesc = "更新单位")
    public int updateUom(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure == null) {
            return 0;
        }
        if (unitOfMeasure.getStatus() != null && unitOfMeasure.getStatus() == 0) {
            unitOfMeasure.setDisableDate(DateUtil.now());
        }
        unitOfMeasure.setLanguage(SessionManager.getLanguage());
        unitOfMeasure.setBaseUomFlag(true);
        unitOfMeasure.setLastUpdatedBy(SessionManager.getLoginName());
        unitOfMeasure.setLastUpdateDate(DateUtil.now());
        try {
            return unitOfMeasureMapper.updateUom(unitOfMeasure);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public MeasureAndCurrencyVO getMesaureAndCurrency() {
        MeasureAndCurrencyVO measureAndCurrencyVO = new MeasureAndCurrencyVO();
        List<UnitOfMeasure> unitOfMeasureList = null;
        List<Currency> currencyList = null;
        try {
            Condition unit = new Condition(UnitOfMeasure.class);
            unit.createCriteria().andEqualTo("status", 1);
            Condition cur = new Condition(UnitOfMeasure.class);
            cur.createCriteria().andEqualTo("status", 1);
            unitOfMeasureList = super.findByCondition(unit);
            currencyList = currencyService.findByCondition(cur);

            List<UnitOfMeasureVO> unitOfMeasureVOList = PojoConvertUtil.convertPojoList(unitOfMeasureList, UnitOfMeasureVO.class);
            List<CurrencyVO> currencyVOList = PojoConvertUtil.convertPojoList(currencyList, CurrencyVO.class);
            measureAndCurrencyVO.setCurrencyVOList(currencyVOList);
            measureAndCurrencyVO.setUnitOfMeasureVOList(unitOfMeasureVOList);
            return measureAndCurrencyVO;
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }
    }
}