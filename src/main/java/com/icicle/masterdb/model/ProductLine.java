package com.icicle.masterdb.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "product_line")
public class ProductLine {
    @Id
    @Column(name = "product_line_no")
    private Integer productLineNo;

    @Column(name = "product_line_name")
    private String productLineName;

    @Column(name = "product_line_state")
    private Integer productLineState;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    /**
     * @return product_line_no
     */
    public Integer getProductLineNo() {
        return productLineNo;
    }

    /**
     * @param productLineNo
     */
    public void setProductLineNo(Integer productLineNo) {
        this.productLineNo = productLineNo;
    }

    /**
     * @return product_line_name
     */
    public String getProductLineName() {
        return productLineName;
    }

    /**
     * @param productLineName
     */
    public void setProductLineName(String productLineName) {
        this.productLineName = productLineName;
    }

    /**
     * @return product_line_state
     */
    public Integer getProductLineState() {
        return productLineState;
    }

    /**
     * @param productLineState
     */
    public void setProductLineState(Integer productLineState) {
        this.productLineState = productLineState;
    }

    /**
     * @return created_by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return creation_date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return last_updated_by
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return last_update_date
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * @param lastUpdateDate
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}