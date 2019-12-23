package com.icicle.masterdb.model;

import javax.persistence.*;

public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "catalog_no")
    private String catalogNo;

    @Column(name = "p_catalog_id")
    private String pCatalogId;

    @Column(name = "catalog_level")
    private Integer catalogLevel;

    @Column(name = "catalog_all")
    private String catalogAll;

    @Column(name = "catalog_desc")
    private String catalogDesc;

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
     * @return catalog_no
     */
    public String getCatalogNo() {
        return catalogNo;
    }

    /**
     * @param catalogNo
     */
    public void setCatalogNo(String catalogNo) {
        this.catalogNo = catalogNo;
    }

    /**
     * @return p_catalog_id
     */
    public String getpCatalogId() {
        return pCatalogId;
    }

    /**
     * @param pCatalogId
     */
    public void setpCatalogId(String pCatalogId) {
        this.pCatalogId = pCatalogId;
    }

    /**
     * @return catalog_level
     */
    public Integer getCatalogLevel() {
        return catalogLevel;
    }

    /**
     * @param catalogLevel
     */
    public void setCatalogLevel(Integer catalogLevel) {
        this.catalogLevel = catalogLevel;
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
     * @return catalog_desc
     */
    public String getCatalogDesc() {
        return catalogDesc;
    }

    /**
     * @param catalogDesc
     */
    public void setCatalogDesc(String catalogDesc) {
        this.catalogDesc = catalogDesc;
    }
}