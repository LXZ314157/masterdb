package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
/**
 * @author liurenhua
 */
public class ViewMaterial {
    @Id
    @Column(name = "material_code")
    private String materialCode;
    @Column(name = "material_name")
    private String materialName;
    @Column(name = "cate_dl_name")
    private String cateDlName;
    @Column(name = "cate_zl_name")
    private String cateZlName;
    @Column(name = "cate_xl_name")
    private String cateXlName;
    private String uom;
    @Column(name = "sync_status")
    private Integer syncStatus;
    @Column(name = "last_sync_date")
    private Date lastSyncDate;

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getCateDlName() {
        return cateDlName;
    }

    public void setCateDlName(String cateDlName) {
        this.cateDlName = cateDlName;
    }

    public String getCateZlName() {
        return cateZlName;
    }

    public void setCateZlName(String cateZlName) {
        this.cateZlName = cateZlName;
    }

    public String getCateXlName() {
        return cateXlName;
    }

    public void setCateXlName(String cateXlName) {
        this.cateXlName = cateXlName;
    }

    public Integer getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(Integer syncStatus) {
        this.syncStatus = syncStatus;
    }

    public Date getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(Date lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }
}