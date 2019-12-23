package com.icicle.masterdb.pojo.vo;

import java.util.List;
import java.util.Map;

public class SyncMaterialContrainVO {

    private String product_code;

    List<Map<String,String>> details;

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public List<Map<String, String>> getDetails() {
        return details;
    }

    public void setDetails(List<Map<String, String>> details) {
        this.details = details;
    }
}


