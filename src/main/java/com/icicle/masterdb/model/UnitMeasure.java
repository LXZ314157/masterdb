package com.icicle.masterdb.model;

import javax.persistence.Id;
/**
 * @author liurenhua
 */
public class UnitMeasure {
    @Id
    private Integer id;
    private String uomCode;
    private String unitOfMeasure;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUomCode() {
        return uomCode;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
