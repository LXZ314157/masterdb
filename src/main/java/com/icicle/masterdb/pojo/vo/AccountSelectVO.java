package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.Domain;
import com.icicle.masterdb.model.PromissionGroup;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author liumingming
 * @version 2018-01-30 13:33.
 */
public class AccountSelectVO {
    private List<Domain> domainList;
    private List<PromissionGroup> promissionGroupList;

    public List<Domain> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<Domain> domainList) {
        this.domainList = domainList;
    }

    public List<PromissionGroup> getPromissionGroupList() {
        return promissionGroupList;
    }

    public void setPromissionGroupList(List<PromissionGroup> promissionGroupList) {
        this.promissionGroupList = promissionGroupList;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
