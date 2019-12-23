package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * @author liurenhua
 */
@Table(name = "pro_department")
public class ProDepartment {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "pro_dept_id")
    private Integer proDeptId;

    @Column(name = "pro_dept_name")
    private String proDeptName;

    @Column(name = "pro_dept_desc")
    private String proDeptDesc;

    @Column(name = "bu_id")
    private Integer buId;

    private Integer status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    /**
     * @return pro_dept_id
     */
    public Integer getProDeptId() {
        return proDeptId;
    }

    /**
     * @param proDeptId
     */
    public void setProDeptId(Integer proDeptId) {
        this.proDeptId = proDeptId;
    }

    /**
     * @return pro_dept_name
     */
    public String getProDeptName() {
        return proDeptName;
    }

    /**
     * @param proDeptName
     */
    public void setProDeptName(String proDeptName) {
        this.proDeptName = proDeptName;
    }

    /**
     * @return pro_dept_desc
     */
    public String getProDeptDesc() {
        return proDeptDesc;
    }

    /**
     * @param proDeptDesc
     */
    public void setProDeptDesc(String proDeptDesc) {
        this.proDeptDesc = proDeptDesc;
    }

    /**
     * @return bu_id
     */
    public Integer getBuId() {
        return buId;
    }

    /**
     * @param buId
     */
    public void setBuId(Integer buId) {
        this.buId = buId;
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