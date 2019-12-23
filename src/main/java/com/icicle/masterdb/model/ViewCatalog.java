package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "view_catalog")
public class ViewCatalog {
    @Id
    @Column(name = "system_id")
    private Integer systemId;

    @Column(name = "system_code")
    private String systemCode;

    @Column(name = "catalog_all")
    private String catalogAll;

    private String ca1;

    private String ca2;

    private String ca3;

    /**
     * @return system_id
     */
    public Integer getSystemId() {
        return systemId;
    }

    /**
     * @param systemId
     */
    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    /**
     * @return system_code
     */
    public String getSystemCode() {
        return systemCode;
    }

    /**
     * @param systemCode
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    /**
     * @return catalog_all
     */
    public String getCatalogAll() {
        return catalogAll;
    }

    /**
     * @param catalogAll
     */
    public void setCatalogAll(String catalogAll) {
        this.catalogAll = catalogAll;
    }

    /**
     * @return ca1
     */
    public String getCa1() {
        return ca1;
    }

    /**
     * @param ca1
     */
    public void setCa1(String ca1) {
        this.ca1 = ca1;
    }

    /**
     * @return ca2
     */
    public String getCa2() {
        return ca2;
    }

    /**
     * @param ca2
     */
    public void setCa2(String ca2) {
        this.ca2 = ca2;
    }

    /**
     * @return ca3
     */
    public String getCa3() {
        return ca3;
    }

    /**
     * @param ca3
     */
    public void setCa3(String ca3) {
        this.ca3 = ca3;
    }
}