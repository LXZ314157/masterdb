package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ColorCard;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ColorCardMapper extends MyMapper<ColorCard> {
    /**
     * 获取色卡列表
     *
     * @return
     */
    List<ColorCard> selectCardPart();

    /**
     * 根据条件查询
     *
     * @param colorCard
     * @return
     */
    int searchByCondition(ColorCard colorCard);

    /**
     * 批量导入色卡列表
     *
     * @param colorCardList
     * @return
     */
    int insertInfoAll(List<ColorCard> colorCardList);

    /**
     * 验证色卡并返回色卡名
     *
     * @param colorCardCode
     * @return
     */
    ColorCard searchCard(String colorCardCode);

}