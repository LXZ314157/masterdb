package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.ProductAttributeExtend;
import com.icicle.masterdb.model.ViewSelectproductinfo;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public class ProductFindVO {
    ViewSelectproductinfo viewSelectproductinfoList;
    ProductAttributeExtend productAttributeExtendlist;
    List<ViewSelectItemVO> categoryLevel1List;
    List<ViewSelectItemVO> categoryLevel2List;

    public List<ViewSelectItemVO> getCategoryLevel1List() {
        return categoryLevel1List;
    }

    public void setCategoryLevel1List(List<ViewSelectItemVO> categoryLevel1List) {
        this.categoryLevel1List = categoryLevel1List;
    }

    public List<ViewSelectItemVO> getCategoryLevel2List() {
        return categoryLevel2List;
    }

    public void setCategoryLevel2List(List<ViewSelectItemVO> categoryLevel2List) {
        this.categoryLevel2List = categoryLevel2List;
    }

    public ViewSelectproductinfo getViewSelectproductinfoList() {
        return viewSelectproductinfoList;
    }

    public void setViewSelectproductinfoList(ViewSelectproductinfo viewSelectproductinfoList) {
        this.viewSelectproductinfoList = viewSelectproductinfoList;
    }

    public ProductAttributeExtend getProductAttributeExtendlist() {
        return productAttributeExtendlist;
    }

    public void setProductAttributeExtendlist(ProductAttributeExtend productAttributeExtendlist) {
        this.productAttributeExtendlist = productAttributeExtendlist;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
