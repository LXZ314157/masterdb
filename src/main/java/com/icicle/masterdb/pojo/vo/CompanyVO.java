package com.icicle.masterdb.pojo.vo;

import javax.persistence.Column;

/**
 * @author lvxuezhan
 * @version 2019-06-14 20:14
 */
public class CompanyVO {

    private String companyId;

    private String companyNameLocal;

    private String companyNameEn;

    private Integer companyState;


    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyNameLocal() {
        return companyNameLocal;
    }

    public void setCompanyNameLocal(String companyNameLocal) {
        this.companyNameLocal = companyNameLocal;
    }

    public String getCompanyNameEn() {
        return companyNameEn;
    }

    public void setCompanyNameEn(String companyNameEn) {
        this.companyNameEn = companyNameEn;
    }

    public Integer getCompanyState() {
        return companyState;
    }

    public void setCompanyState(Integer companyState) {
        this.companyState = companyState;
    }
}
