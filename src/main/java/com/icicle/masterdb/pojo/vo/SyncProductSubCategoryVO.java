package com.icicle.masterdb.pojo.vo;

public class SyncProductSubCategoryVO {

    private Integer id;
    private String catalog_no;
    private String catalog_desc;
    private Integer p_catalog_id;
    private Integer operation_status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCatalog_no() {
        return catalog_no;
    }

    public void setCatalog_no(String catalog_no) {
        this.catalog_no = catalog_no;
    }

    public String getCatalog_desc() {
        return catalog_desc;
    }

    public void setCatalog_desc(String catalog_desc) {
        this.catalog_desc = catalog_desc;
    }

    public Integer getP_catalog_id() {
        return p_catalog_id;
    }

    public void setP_catalog_id(Integer p_catalog_id) {
        this.p_catalog_id = p_catalog_id;
    }

    public Integer getOperation_status() {
        return operation_status;
    }

    public void setOperation_status(Integer operation_status) {
        this.operation_status = operation_status;
    }
}
