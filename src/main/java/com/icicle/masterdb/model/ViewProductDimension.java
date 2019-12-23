package com.icicle.masterdb.model;

import javax.persistence.*;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "view_product_dimension")
public class ViewProductDimension {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "def_code")
    private String defCode;

    @Column(name = "def_name")
    private String defName;

    @Column(name = "has_item")
    private Integer hasItem;

    @Column(name = "def_type")
    private String defType;

    @Column(name = "select_table")
    private String selectTable;

    private Boolean status;

    @Column(name = "class_dimension_code")
    private String classDimensionCode;

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
     * @return def_name
     */
    public String getDefName() {
        return defName;
    }

    /**
     * @param defName
     */
    public void setDefName(String defName) {
        this.defName = defName;
    }

    /**
     * @return has_item
     */
    public Integer getHasItem() {
        return hasItem;
    }

    /**
     * @param hasItem
     */
    public void setHasItem(Integer hasItem) {
        this.hasItem = hasItem;
    }

    /**
     * @return status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return class_dimension_code
     */
    public String getClassDimensionCode() {
        return classDimensionCode;
    }

    /**
     * @param classDimensionCode
     */
    public void setClassDimensionCode(String classDimensionCode) {
        this.classDimensionCode = classDimensionCode;
    }

    public String getDefType() {
        return defType;
    }

    public String getSelectTable() {
        return selectTable;
    }

    public void setSelectTable(String selectTable) {
        this.selectTable = selectTable;
    }

    public void setDefType(String defType) {
        this.defType = defType;
    }
}