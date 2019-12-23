package com.icicle.masterdb.model;

import javax.persistence.*;

@Table(name = "view_searchimages_elastic")
public class ViewSearchimagesElastic {
    @Id
    @Column(name = "image_id")
    private Integer imageId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_type")
    private Integer imageType;

    @Column(name = "product_name")
    private String productName;

    private Integer year;

    @Column(name = "dev_season")
    private String devSeason;

    private String line;

    @Column(name = "wave_band")
    private String waveBand;

    @Column(name = "product_class")
    private String productClass;

    @Column(name = "dev_no")
    private String devNo;

    @Column(name = "style_no")
    private String styleNo;

    @Column(name = "model_no")
    private String modelNo;

    @Column(name = "material_no")
    private String materialNo;

    @Column(name = "material_name")
    private String materialName;

    @Column(name = "color_name")
    private String colorName;

    @Column(name = "color_card_no")
    private String colorCardNo;

    @Column(name = "colour_system")
    private String colourSystem;

    @Column(name = "planning_location")
    private Integer planningLocation;

    @Column(name = "purchase_location")
    private Integer purchaseLocation;

    @Column(name = "display_location")
    private Integer displayLocation;

    private Integer series;

    @Column(name = "eco_way_material")
    private Integer ecoWayMaterial;

    @Column(name = "eco_way_color")
    private Integer ecoWayColor;

    @Column(name = "eco_way_technics")
    private Integer ecoWayTechnics;

    @Column(name = "selling_point")
    private Integer sellingPoint;

    private Integer position;

    @Column(name = "default_image")
    private Boolean defaultImage;

    /**
     * @return image_id
     */
    public Integer getImageId() {
        return imageId;
    }

    /**
     * @param imageId
     */
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    /**
     * @return image_url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return image_name
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * @param imageName
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * @return image_type
     */
    public Integer getImageType() {
        return imageType;
    }

    /**
     * @param imageType
     */
    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    /**
     * @return product_name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * @param year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * @return dev_season
     */
    public String getDevSeason() {
        return devSeason;
    }

    /**
     * @param devSeason
     */
    public void setDevSeason(String devSeason) {
        this.devSeason = devSeason;
    }

    /**
     * @return line
     */
    public String getLine() {
        return line;
    }

    /**
     * @param line
     */
    public void setLine(String line) {
        this.line = line;
    }

    /**
     * @return wave_band
     */
    public String getWaveBand() {
        return waveBand;
    }

    /**
     * @param waveBand
     */
    public void setWaveBand(String waveBand) {
        this.waveBand = waveBand;
    }

    /**
     * @return product_class
     */
    public String getProductClass() {
        return productClass;
    }

    /**
     * @param productClass
     */
    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    /**
     * @return dev_no
     */
    public String getDevNo() {
        return devNo;
    }

    /**
     * @param devNo
     */
    public void setDevNo(String devNo) {
        this.devNo = devNo;
    }

    /**
     * @return style_no
     */
    public String getStyleNo() {
        return styleNo;
    }

    /**
     * @param styleNo
     */
    public void setStyleNo(String styleNo) {
        this.styleNo = styleNo;
    }

    /**
     * @return model_no
     */
    public String getModelNo() {
        return modelNo;
    }

    /**
     * @param modelNo
     */
    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    /**
     * @return material_no
     */
    public String getMaterialNo() {
        return materialNo;
    }

    /**
     * @param materialNo
     */
    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    /**
     * @return material_name
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * @param materialName
     */
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    /**
     * @return color_name
     */
    public String getColorName() {
        return colorName;
    }

    /**
     * @param colorName
     */
    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    /**
     * @return color_card_no
     */
    public String getColorCardNo() {
        return colorCardNo;
    }

    /**
     * @param colorCardNo
     */
    public void setColorCardNo(String colorCardNo) {
        this.colorCardNo = colorCardNo;
    }

    /**
     * @return colour_system
     */
    public String getColourSystem() {
        return colourSystem;
    }

    /**
     * @param colourSystem
     */
    public void setColourSystem(String colourSystem) {
        this.colourSystem = colourSystem;
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
     * @return selling_point
     */
    public Integer getSellingPoint() {
        return sellingPoint;
    }

    /**
     * @param sellingPoint
     */
    public void setSellingPoint(Integer sellingPoint) {
        this.sellingPoint = sellingPoint;
    }

    /**
     * @return position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * @param position
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * @return default_image
     */
    public Boolean getDefaultImage() {
        return defaultImage;
    }

    /**
     * @param defaultImage
     */
    public void setDefaultImage(Boolean defaultImage) {
        this.defaultImage = defaultImage;
    }
}