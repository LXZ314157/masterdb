package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;


/**
 * @author liurenhua
 * Created by liurenhua on 2017/12/4.
 */
public class ProductDimensionVO {
    private Integer id;

    private String classDimensionCode;

    private String classDimensionName;

    private String classDimensionDesc;

    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassDimensionCode() {
        return classDimensionCode;
    }

    public void setClassDimensionCode(String classDimensionCode) {
        this.classDimensionCode = classDimensionCode;
    }

    public String getClassDimensionName() {
        return classDimensionName;
    }

    public void setClassDimensionName(String classDimensionName) {
        this.classDimensionName = classDimensionName;
    }

    public String getClassDimensionDesc() {
        return classDimensionDesc;
    }

    public void setClassDimensionDesc(String classDimensionDesc) {
        this.classDimensionDesc = classDimensionDesc;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
