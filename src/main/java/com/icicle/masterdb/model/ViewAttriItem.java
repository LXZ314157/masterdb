package com.icicle.masterdb.model;

import javax.persistence.*;

@Table(name = "view_attri_item")
public class ViewAttriItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "def_id")
    private Integer defId;

    private Integer code;

    private String name;

    @Column(name = "def_code")
    private String defCode;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
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
}