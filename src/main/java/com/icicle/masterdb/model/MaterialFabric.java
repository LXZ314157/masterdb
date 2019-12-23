package com.icicle.masterdb.model;

import javax.persistence.*;
/**
 * @author liurenhua
 */
@Table(name = "material_fabric")
public class MaterialFabric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "material_code")
    private String materialCode;

    private String fabric;

    private String language;

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
     * @return material_code
     */
    public String getMaterialCode() {
        return materialCode;
    }

    /**
     * @param materialCode
     */
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    /**
     * @return fabric
     */
    public String getFabric() {
        return fabric;
    }

    /**
     * @param fabric
     */
    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    /**
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }
}