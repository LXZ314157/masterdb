package com.icicle.masterdb.model;

import com.icicle.masterdb.util.StringUtil;

import javax.persistence.*;

@Table(name = "view_account_data")
public class ViewAccountData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String loginname;

    private String truename;

    @Column(name = "domain_name")
    private String domainName;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "domain_id")
    private Integer domainId;

    @Column(name = "group_id")
    private Integer groupId;

    private boolean manager;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return loginname
     */
    public String getLoginname() {
        return loginname;
    }

    /**
     * @param loginname
     */
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    /**
     * @return truename
     */
    public String getTruename() {
        return truename;
    }

    /**
     * @param truename
     */
    public void setTruename(String truename) {
        this.truename = truename;
    }

    /**
     * @return domain_name
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * @param domainName
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * @return group_name
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}