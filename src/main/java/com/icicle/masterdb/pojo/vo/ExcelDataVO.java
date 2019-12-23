package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.core.Result;

public class ExcelDataVO {

    private Result result;

    private String path;

    private String productCategoryCode;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }
}
