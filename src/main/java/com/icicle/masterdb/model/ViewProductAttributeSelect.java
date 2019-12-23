package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author liurenhua
 */
@Table(name = "view_product_attribute_select")
public class ViewProductAttributeSelect {
    @Id
    @Column(name = "def_code")
    private String defCode;

    private Integer code;

    private String name;

    /**
     * @return def_code
     */
    public String getDefCode() {
        return defCode;
    }

    /**
     * @param defCode
     */
    public void setDefCode(String defCode) {
        this.defCode = defCode;
    }

    /**
     * @return code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}