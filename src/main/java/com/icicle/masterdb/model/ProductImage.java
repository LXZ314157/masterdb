package com.icicle.masterdb.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author liurenhua
 */
@Table(name = "product_image")
public class ProductImage {
    @Id
    @Column(name = "image_id")
    private Integer id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_type")
    private Integer imageType;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    private Integer status;

    @Column(name = "default_image")
    private Boolean defaultImage;

    /**
     * @return imgae_id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param imageId
     */
    public void setId(Integer imageId) {
        this.id = imageId;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(Boolean defaultImage) {
        this.defaultImage = defaultImage;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return super.equals(obj);
        }
        if (obj instanceof ProductImage) {
            ProductImage p = (ProductImage) obj;
            return Objects.equals(p.getImageUrl(), this.getImageUrl());
        }
        return super.equals(obj);
    }


}