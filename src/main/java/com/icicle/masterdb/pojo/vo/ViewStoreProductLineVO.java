package com.icicle.masterdb.pojo.vo;

/**
 * @author lvxuezhan
 * @version 2019-02-24 18:16
 */
public class ViewStoreProductLineVO {

    private Integer storeNo;

    private Integer productLineNo;

    private String productLineName;

    private Boolean ischecked = false;

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

    public Boolean getIschecked() {
        return ischecked;
    }

    public void setIschecked(Boolean ischecked) {
        this.ischecked = ischecked;
    }
}
