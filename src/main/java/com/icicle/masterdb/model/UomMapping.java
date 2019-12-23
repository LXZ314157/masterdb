package com.icicle.masterdb.model;

import javax.persistence.*;

@Table(name = "uom_mapping")
public class UomMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uom_code")
    private String uomCode;

    @Column(name = "erp_uom_code")
    private String erpUomCode;

    @Column(name = "plm_uom_code")
    private String plmUomCode;

    @Column(name = "wms_uom_code")
    private String wmsUomCode;

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
     * @return uom_code
     */
    public String getUomCode() {
        return uomCode;
    }

    /**
     * @param uomCode
     */
    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    /**
     * @return erp_uom_code
     */
    public String getErpUomCode() {
        return erpUomCode;
    }

    /**
     * @param erpUomCode
     */
    public void setErpUomCode(String erpUomCode) {
        this.erpUomCode = erpUomCode;
    }

    /**
     * @return plm_uom_code
     */
    public String getPlmUomCode() {
        return plmUomCode;
    }

    /**
     * @param plmUomCode
     */
    public void setPlmUomCode(String plmUomCode) {
        this.plmUomCode = plmUomCode;
    }

    /**
     * @return wms_uom_code
     */
    public String getWmsUomCode() {
        return wmsUomCode;
    }

    /**
     * @param wmsUomCode
     */
    public void setWmsUomCode(String wmsUomCode) {
        this.wmsUomCode = wmsUomCode;
    }
}