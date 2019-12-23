package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ColorCard;
import com.icicle.masterdb.pojo.vo.ColorCardVO;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public interface ColorCardService extends Service<ColorCard> {
    /**
     * 批量查询已经存在的色卡
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
     * 加载色卡列表
     *
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param sSearch
     * @param sortCol
     * @param sortDir
     * @return
     */
    DataTableRecord listColorCard(String sEcho, Integer pageIndex, Integer pageSize, String sSearch, Integer sortCol, String sortDir);

    /**
     * 执行批量导入逻辑操作
     *
     * @param colorCardVO
     * @return
     */
    int leadInColorCard(ColorCardVO colorCardVO);

    /**
     * 单条增加色卡
     *
     * @param colorCard
     * @return
     */
    int insertColorCard(ColorCard colorCard);

    /**
     * 单条更新色卡
     *
     * @param colorCard
     * @return
     */
    int updateColorCard(ColorCard colorCard);

    /**
     * 验证色卡并返回色卡名
     *
     * @param colorCardCode
     * @return
     */
    ColorCard searchCard(String colorCardCode);
}