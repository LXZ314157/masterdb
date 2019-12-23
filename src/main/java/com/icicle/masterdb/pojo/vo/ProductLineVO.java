package com.icicle.masterdb.pojo.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author lvxuezhan
 * @version 2019-02-22 15:20
 */
public class ProductLineVO {

    private Integer productLineNo;
    private String productLineName;
    private Integer productLineState;
    private Boolean isCheck = false;

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

    public Integer getProductLineState() {
        return productLineState;
    }

    public void setProductLineState(Integer productLineState) {
        this.productLineState = productLineState;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }
}
