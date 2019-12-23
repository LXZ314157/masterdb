package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.ViewSelectItem;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public class MaterialDetailVO {

    private List<ViewMaterialAttributeVO> viewMaterialAttributeVOList;

    private List<MaterialAttributeVO> materialAttributeVOList;

    private ViewMaterialDetailVO viewMaterialDetailVO;

    private List<ViewSelectItem> viewSelectItemList;

    public List<MaterialAttributeVO> getMaterialAttributeVOList() {
        return materialAttributeVOList;
    }

    public void setMaterialAttributeVOList(List<MaterialAttributeVO> materialAttributeVOList) {
        this.materialAttributeVOList = materialAttributeVOList;
    }


    public List<ViewMaterialAttributeVO> getViewMaterialAttributeVOList() {
        return viewMaterialAttributeVOList;
    }

    public void setViewMaterialAttributeVOList(List<ViewMaterialAttributeVO> viewMaterialAttributeVOList) {
        this.viewMaterialAttributeVOList = viewMaterialAttributeVOList;
    }

    public ViewMaterialDetailVO getViewMaterialDetailVO() {
        return viewMaterialDetailVO;
    }

    public void setViewMaterialDetailVO(ViewMaterialDetailVO viewMaterialDetailVO) {
        this.viewMaterialDetailVO = viewMaterialDetailVO;
    }

    public List<ViewSelectItem> getViewSelectItemList() {
        return viewSelectItemList;
    }

    public void setViewSelectItemList(List<ViewSelectItem> viewSelectItemList) {
        this.viewSelectItemList = viewSelectItemList;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
