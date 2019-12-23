package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author CodeGenerator
 */
@Table(name = "product_dimension_merchandising")
public class ProductDimensionMerchandising {
    @Id
    @Column(name = "product_code")
    private String productCode;

    private String language;

    @Column(name = "single_search_cover")
    private Boolean singleSearchCover;

    @Column(name = "planning_location")
    private Integer planningLocation;

    @Column(name = "purchase_location")
    private Integer purchaseLocation;

    @Column(name = "display_location")
    private Integer displayLocation;

    private Integer series;

    @Column(name = "series_remark")
    private String seriesRemark;

    @Column(name = "eco_way_material")
    private Integer ecoWayMaterial;

    @Column(name = "eco_way_color")
    private Integer ecoWayColor;

    @Column(name = "eco_way_technics")
    private Integer ecoWayTechnics;

    @Column(name = "eco_way_remark")
    private String ecoWayRemark;

    @Column(name = "official_weibo")
    private Boolean officialWeibo;

    @Column(name = "selling_point")
    private Integer sellingPoint;

    private String record;

    /**
     * @return product_code
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    /**
     * @return single_search_cover
     */
    public Boolean getSingleSearchCover() {
        return singleSearchCover;
    }

    /**
     * @param singleSearchCover
     */
    public void setSingleSearchCover(Boolean singleSearchCover) {
        this.singleSearchCover = singleSearchCover;
    }

    /**
     * @return planning_location
     */
    public Integer getPlanningLocation() {
        return planningLocation;
    }

    /**
     * @param planningLocation
     */
    public void setPlanningLocation(Integer planningLocation) {
        this.planningLocation = planningLocation;
    }

    /**
     * @return purchase_location
     */
    public Integer getPurchaseLocation() {
        return purchaseLocation;
    }

    /**
     * @param purchaseLocation
     */
    public void setPurchaseLocation(Integer purchaseLocation) {
        this.purchaseLocation = purchaseLocation;
    }

    /**
     * @return display_location
     */
    public Integer getDisplayLocation() {
        return displayLocation;
    }

    /**
     * @param displayLocation
     */
    public void setDisplayLocation(Integer displayLocation) {
        this.displayLocation = displayLocation;
    }

    /**
     * @return series
     */
    public Integer getSeries() {
        return series;
    }

    /**
     * @param series
     */
    public void setSeries(Integer series) {
        this.series = series;
    }

    /**
     * @return series_remark
     */
    public String getSeriesRemark() {
        return seriesRemark;
    }

    /**
     * @param seriesRemark
     */
    public void setSeriesRemark(String seriesRemark) {
        this.seriesRemark = seriesRemark;
    }

    /**
     * @return eco_way_material
     */
    public Integer getEcoWayMaterial() {
        return ecoWayMaterial;
    }

    /**
     * @param ecoWayMaterial
     */
    public void setEcoWayMaterial(Integer ecoWayMaterial) {
        this.ecoWayMaterial = ecoWayMaterial;
    }

    /**
     * @return eco_way_color
     */
    public Integer getEcoWayColor() {
        return ecoWayColor;
    }

    /**
     * @param ecoWayColor
     */
    public void setEcoWayColor(Integer ecoWayColor) {
        this.ecoWayColor = ecoWayColor;
    }

    /**
     * @return eco_way_technics
     */
    public Integer getEcoWayTechnics() {
        return ecoWayTechnics;
    }

    /**
     * @param ecoWayTechnics
     */
    public void setEcoWayTechnics(Integer ecoWayTechnics) {
        this.ecoWayTechnics = ecoWayTechnics;
    }

    /**
     * @return eco_way_remark
     */
    public String getEcoWayRemark() {
        return ecoWayRemark;
    }

    /**
     * @param ecoWayRemark
     */
    public void setEcoWayRemark(String ecoWayRemark) {
        this.ecoWayRemark = ecoWayRemark;
    }

    /**
     * @return official_weibo
     */
    public Boolean getOfficialWeibo() {
        return officialWeibo;
    }
    /**
     * @param officialWeibo
     */
    public void setOfficialWeibo(Boolean officialWeibo) {
        this.officialWeibo = officialWeibo;
    }

    public Integer getSellingPoint() {
        return sellingPoint;
    }

    public void setSellingPoint(Integer sellingPoint) {
        this.sellingPoint = sellingPoint;
    }

    /**
     * @return record
     */
    public String getRecord() {
        return record;
    }

    /**
     * @param record
     */
    public void setRecord(String record) {
        this.record = record;
    }
}