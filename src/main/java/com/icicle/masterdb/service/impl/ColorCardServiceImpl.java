package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.ColorCardMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ColorCard;
import com.icicle.masterdb.pojo.vo.ColorCardVO;
import com.icicle.masterdb.service.ColorCardService;
import com.icicle.masterdb.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

import static com.icicle.masterdb.service.constant.ServiceConstant.ASC;

/**
 * @author wangyuling
 */
@Service
public class ColorCardServiceImpl extends AbstractService<ColorCard> implements ColorCardService {
    @Resource
    private ColorCardMapper colorCardMapper;

    @Override
    public List<ColorCard> selectCardPart() {
        return colorCardMapper.selectCardPart();
    }

    @Override
    public int searchByCondition(ColorCard colorCard) {
        return colorCardMapper.searchByCondition(colorCard);
    }

    @Override
    public DataTableRecord listColorCard(String sEcho, Integer pageIndex, Integer pageSize, String sSearch, Integer sortCol, String sortDir) {
        //获取类里面的所有属性
        sortDir = PageUtil.orderMethod(sortDir);
        String orderColoum = PageUtil.orderBy(sortCol + 1, ColorCard.class);
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ColorCard> colorCardList = null;
        Condition condition = new Condition(ColorCard.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("status", 1);
        if (!StringUtils.isBlank(sSearch)) {
            String words = StringUtil.gsFormat("%{0}%", sSearch);
            condition.and(condition.createCriteria().andLike("colorCardCode", words).orLike("colorCardName", words));
        }
        if (ASC.equals(sortDir)) {
            condition.orderBy(orderColoum).asc();
        } else {
            condition.orderBy(orderColoum).desc();
        }
        try {
            colorCardList = colorCardMapper.selectByCondition(condition);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }
        PageInfo pageInfo = new PageInfo(colorCardList);
        dataTableRecord.setITotalRecords(pageSize);
        dataTableRecord.setITotalDisplayRecords((int) pageInfo.getTotal());
        dataTableRecord.setAaData(colorCardList);
        return dataTableRecord;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "色卡批量插入")
    public int leadInColorCard(ColorCardVO colorCardVO) {
        int con = 0;
        if (colorCardVO == null) {
            return -1;
        }
//       获取解析后的list
        List<ColorCard> colorCardList = colorCardVO.getColorCards();
        if (colorCardList == null || colorCardList.size() == 0) {
            return -1;
        }
        List<List<ColorCard>> list = ListUtil.splitArrayList(colorCardList, 300);
        //小部分
        for (int i = 0; i < list.size(); i++) {
            List<ColorCard> cardList = list.get(i);
            if (cardList != null && cardList.size() != 0) {
                con += colorCardMapper.insertInfoAll(cardList);
            } else {
                return -1;
            }
        }
        return con;
    }


    @Override
    @LogAction(logDesc = "色卡单条插入")
    public int insertColorCard(ColorCard colorCard) {
        colorCard.setCreatedBy(SessionManager.getLoginName());
        colorCard.setLanguage(SessionManager.getLanguage());
        colorCard.setCreatedDate(DateUtil.now());
        colorCard.setStatus(1);
        try {
            int con = super.save(colorCard);
            if (con > 0) {
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    @LogAction(logDesc = "色卡单条更新")
    public int updateColorCard(ColorCard colorCard) {
        if (colorCard == null) {
            return 0;
        }
        colorCard.setLastUpdateBy(SessionManager.getLoginName());
        colorCard.setLanguage(SessionManager.getLanguage());
        colorCard.setStatus(1);
        colorCard.setLastUpdateDate(DateUtil.now());
        try {
            int con = super.update(colorCard);
            if (con > 0) {
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public ColorCard searchCard(String colorCardCode) {
        return colorCardMapper.searchCard(colorCardCode);
    }
}