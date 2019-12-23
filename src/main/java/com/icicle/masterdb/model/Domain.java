package com.icicle.masterdb.model;

import javax.persistence.*;

public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "domain_name")
    private String domainName;

    @Column(name = "domain_host")
    private String domainHost;

    @Column(name = "domain_port")
    private Short domainPort;

    @Column(name = "domain_desc")
    private String domainDesc;

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
     * @return domain_host
     */
    public String getDomainHost() {
        return domainHost;
    }

    /**
     * @param domainHost
     */
    public void setDomainHost(String domainHost) {
        this.domainHost = domainHost;
    }

    /**
     * @return domain_port
     */
    public Short getDomainPort() {
        return domainPort;
    }

    /**
     * @param domainPort
     */
    public void setDomainPort(Short domainPort) {
        this.domainPort = domainPort;
    }

    /**
     * @return domain_desc
     */
    public String getDomainDesc() {
        return domainDesc;
    }

    /**
     * @param domainDesc
     */
    public void setDomainDesc(String domainDesc) {
        this.domainDesc = domainDesc;
    }
}