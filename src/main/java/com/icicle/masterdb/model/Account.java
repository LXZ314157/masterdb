package com.icicle.masterdb.model;

import javax.persistence.*;

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String loginname;

    private String truename;

    @Column(name = "domain_id")
    private Integer domainId;

    private Boolean manager;

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
     * @return domain_id
     */
    public Integer getDomainId() {
        return domainId;
    }

    /**
     * @param domainId
     */
    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    /**
     * @return manager
     */
    public Boolean getManager() {
        return manager;
    }

    /**
     * @param manager
     */
    public void setManager(Boolean manager) {
        this.manager = manager;
    }
}