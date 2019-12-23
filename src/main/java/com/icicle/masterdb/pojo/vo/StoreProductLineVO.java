package com.icicle.masterdb.pojo.vo;

import javax.persistence.Column;

/**
 * @author lvxuezhan
 * @version 2019-02-22 18:05
 */
public class StoreProductLineVO {

    private Integer storeNo;

    private Integer productLineNo;

    private String productLineName;

    public Integer getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(Integer storeNo) {
        this.storeNo = storeNo;
    }

    public Integer getProductLineNo() {
        return productLineNo;
    }

    public void setProductLineNo(Integer productLineNo) {
        this.productLineNo = productLineNo;
    }

    public String getProductLineName() {
        return productLineName;
    }

    public void setProductLineName(String productLineName) {
        this.productLineName = productLineName;
    }
}
