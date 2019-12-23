package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.model.ViewProductAttributeSelect;
import com.icicle.masterdb.pojo.vo.LookSelectVO;
import com.icicle.masterdb.pojo.vo.ProductLookVO;

import java.util.List;

/**
 * @author liumingming
 * @version 2017-12-12 12:02.
 */
public interface LookService {
    /**
     * 获取下拉列表
     *
     * @return
     */
    LookSelectVO getSelectData(String defCode);

    /**
     * 获取下拉列表
     *
     * @return
     */
    List<ViewProductAttributeSelect> getPositionList();

    /**
     * 验证lookid是否重复
     *
     * @param lookId lookid
     * @return
     */
    Integer checkLookId(Integer lookId);

    /**
     * 验证lookCode是否重复
     *
     * @param lookCode lookCode
     * @return
     */
    Integer checkLookCode(String lookCode);

    /**
     * 新增look
     *
     * @param vo look实体
     * @return
     */
    int save(ProductLookVO vo);

    /**
     * 编辑look
     *
     * @param vo look实体
     * @return
     */
    int update(ProductLookVO vo);

    /**
     * 获取look列表
     *
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param lookId
     * @return
     */
    DataTableRecord getList(String sEcho, Integer pageIndex, Integer pageSize, Integer lookId);

    /**
     * 导入look
     * @param list
     * @return
     */
    int importLook(List<ProductLookVO> list);
}