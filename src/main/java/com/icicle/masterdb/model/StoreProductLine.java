package com.icicle.masterdb.model;

import javax.persistence.*;

@Table(name = "store_product_line")
public class StoreProductLine {
    @Id
    @Column(name = "store_no")
    private Integer storeNo;

    @Column(name = "product_line_no")
    private Integer productLineNo;

    /**
     * @return store_no
     */
    public Integer getStoreNo() {
        return storeNo;
    }

    /**
     * @param storeNo
     */
    public void setStoreNo(Integer storeNo) {
        this.storeNo = storeNo;
    }

    /**
     * @return product_line_no
     */
    public Integer getProductLineNo() {
        return productLineNo;
    }

    /**
     * @param productLineNo
     */
    public void setProductLineNo(Integer productLineNo) {
        this.productLineNo = productLineNo;
    }
}