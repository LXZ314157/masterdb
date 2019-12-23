package com.icicle.masterdb.model;

import javax.persistence.*;

@Table(name = "store_comparison")
public class StoreComparison {
    @Id
    @Column(name = "store_no")
    private Integer storeNo;

    @Column(name = "compare_store_no")
    private Integer compareStoreNo;

    @Column(name = "compare_store_name")
    private String compareStoreName;

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

    public String getCompareStoreName() {
        return compareStoreName;
    }

    public void setCompareStoreName(String compareStoreName) {
        this.compareStoreName = compareStoreName;
    }

    /**
     * @return compare_store_no
     */
    public Integer getCompareStoreNo() {
        return compareStoreNo;
    }

    /**
     * @param compareStoreNo
     */
    public void setCompareStoreNo(Integer compareStoreNo) {
        this.compareStoreNo = compareStoreNo;
    }
}