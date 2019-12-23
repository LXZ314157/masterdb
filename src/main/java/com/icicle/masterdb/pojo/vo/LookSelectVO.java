package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.ViewProductAttributeSelect;
import com.icicle.masterdb.model.ViewSelectItem;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author liumingming
 * @version 2017-12-12 11:28.
 */
public class LookSelectVO {

    private List<ViewSelectItem> selectItemList;
    private List<ViewProductAttributeSelect> attributeSelectList;

    public List<ViewSelectItem> getSelectItemList() {
        return selectItemList;
    }

    public void setSelectItemList(List<ViewSelectItem> selectItemList) {
        this.selectItemList = selectItemList;
    }

    public List<ViewProductAttributeSelect> getAttributeSelectList() {
        return attributeSelectList;
    }

    public void setAttributeSelectList(List<ViewProductAttributeSelect> attributeSelectList) {
        this.attributeSelectList = attributeSelectList;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}