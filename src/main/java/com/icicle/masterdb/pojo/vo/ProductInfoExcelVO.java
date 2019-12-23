package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.ProductInfo;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public class ProductInfoExcelVO {
    private List<ProductInfo> productInfoList;
    private List<ExcelCell> errorRows;
    private boolean flag;

    public List<ProductInfo> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductInfo> productInfoList) {
        this.productInfoList = productInfoList;
    }

    public List<ExcelCell> getErrorRows() {
        return errorRows;
    }

    public void setErrorRows(List<ExcelCell> errorRows) {
        this.errorRows = errorRows;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
