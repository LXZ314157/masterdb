package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wangyuling
 */
@Table(name = "view_mechand_item")
public class ViewMechandItem {
    @Id
    @Column(name = "def_code")
    private String defCode;

    @Column(name = "def_id")
    private Integer defId;

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
     * @return def_id
     */
    public Integer getDefId() {
        return defId;
    }

    /**
     * @param defId
     */
    public void setDefId(Integer defId) {
        this.defId = defId;
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