package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.Product;
import com.icicle.masterdb.model.ProductAttributeExtend;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author liurenhua
 * Created by liurenhua on 2017/11/10.
 * @apiNote  productList 解析后的productList  productAttributeExtendList
 *           解析后的 productAttributeExtendList
 *           errorRows 出错的行数
 */
public class ProductExcelVO {
    private List<Product> productList;
    private List<ProductAttributeExtend> productAttributeExtendList;
    private List<ExcelCell> errorRows;
    private List<ExcelCell> exictCodes;
    private Boolean flag;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<ProductAttributeExtend> getProductAttributeExtendList() {
        return productAttributeExtendList;
    }

    public void setProductAttributeExtendList(List<ProductAttributeExtend> productAttributeExtendList) {
        this.productAttributeExtendList = productAttributeExtendList;
    }

    public List<ExcelCell> getErrorRows() {
        return errorRows;
    }

    public void setErrorRows(List<ExcelCell> errorRows) {
        this.errorRows = errorRows;
    }

    public List<ExcelCell> getExictCodes() {
        return exictCodes;
    }

    public void setExictCodes(List<ExcelCell> exictCodes) {
        this.exictCodes = exictCodes;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
