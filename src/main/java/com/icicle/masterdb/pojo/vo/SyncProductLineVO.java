package com.icicle.masterdb.pojo.vo;

public class SyncProductLineVO {

    private Integer store_product_id;
    private Integer operation_status;
    private String store_name;
    private String product_line;
    private String product_line_no;

    public String getProduct_line_no() {
        return product_line_no;
    }

    public void setProduct_line_no(String product_line_no) {
        this.product_line_no = product_line_no;
    }

    public Integer getStore_product_id() {
        return store_product_id;
    }

    public void setStore_product_id(Integer store_product_id) {
        this.store_product_id = store_product_id;
    }

    public Integer getOperation_status() {
        return operation_status;
    }

    public void setOperation_status(Integer operation_status) {
        this.operation_status = operation_status;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getProduct_line() {
        return product_line;
    }

    public void setProduct_line(String product_line) {
        this.product_line = product_line;
    }
}
