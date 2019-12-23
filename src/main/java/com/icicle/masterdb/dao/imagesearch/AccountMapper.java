package com.icicle.masterdb.dao.imagesearch;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Account;

public interface AccountMapper extends MyMapper<Account> {

    /**
     * 新增帐号
     * @param account
     * @return
     */
    int insertAccount(Account account);
}