package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author CodeGeneratorUtil
 */
public class ProductAttributeItemVO {
    private Integer id;

    private Integer defId;

    private Integer code;

    private String name;

    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDefId() {
        return defId;
    }

    public void setDefId(Integer defId) {
        this.defId = defId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
