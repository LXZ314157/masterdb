package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "color_card")
public class ColorCard {
    @Id
    private Integer id;

    @Column(name = "color_card_code")
    private String colorCardCode;

    @Column(name = "color_card_name")
    private String colorCardName;

    private Integer status;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    private String language;

    @Column(name = "last_update_by")
    private String lastUpdateBy;

    @Column(name = "created_by")
    private String createdBy;


    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

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
     * @return color_card_code
     */
    public String getColorCardCode() {
        return colorCardCode;
    }

    /**
     * @param colorCardCode
     */
    public void setColorCardCode(String colorCardCode) {
        this.colorCardCode = colorCardCode;
    }

    /**
     * @return color_card_name
     */
    public String getColorCardName() {
        return colorCardName;
    }

    /**
     * @param colorCardName
     */
    public void setColorCardName(String colorCardName) {
        this.colorCardName = colorCardName;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
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