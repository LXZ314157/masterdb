package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.pojo.vo.AccountSelectVO;
import com.icicle.masterdb.pojo.vo.AccountVO;

/**
 * @author liumingming
 * @version 2018-01-30 13:27.
 */
public interface AccountService {
    /**
     * 获取下拉列表
     *
     * @return
     */
    AccountSelectVO getSelectData();

    /**
     * 获取帐号列表
     *
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param search
     * @return
     */
    DataTableRecord getList(String sEcho, Integer pageIndex, Integer pageSize, String search);

    /**
     * 新增look
     *
     * @param vo look实体
     * @return
     */
    int save(AccountVO vo);

    /**
     * 编辑look
     *
     * @param vo look实体
     * @return
     */
    int update(AccountVO vo);
}