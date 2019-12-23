package com.icicle.masterdb.model;

import javax.persistence.*;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "sku_size")
public class SkuSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "size_code")
    private String sizeCode;

    @Column(name = "size_desc")
    private String sizeDesc;

    private Integer status;

    private String language;

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
     * @return size_code
     */
    public String getSizeCode() {
        return sizeCode;
    }

    /**
     * @param sizeCode
     */
    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
    }

    /**
     * @return size_desc
     */
    public String getSizeDesc() {
        return sizeDesc;
    }

    /**
     * @param sizeDesc
     */
    public void setSizeDesc(String sizeDesc) {
        this.sizeDesc = sizeDesc;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }
}