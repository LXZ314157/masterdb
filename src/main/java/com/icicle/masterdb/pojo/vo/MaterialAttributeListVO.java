package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.MaterialAttribute;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public class MaterialAttributeListVO {
    private String materialCode;
    private List<MaterialAttribute> materialAttributeList;

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public List<MaterialAttribute> getMaterialAttributeList() {
        return materialAttributeList;
    }

    public void setMaterialAttributeList(List<MaterialAttribute> materialAttributeList) {
        this.materialAttributeList = materialAttributeList;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
