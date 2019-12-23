package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author liumingming
 * @version 2018-01-30 13:36.
 */
public class AccountVO {
    private Integer id;
    private String loginName;
    private String trueName;
    private Integer domainId;
    private Integer groupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }


    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
