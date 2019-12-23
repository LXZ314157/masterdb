package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.Zone;
import com.icicle.masterdb.util.StringUtil;

import javax.persistence.Column;
import java.util.List;

/**
 * @author liumingming
 * Created by liumingming on 2017-10-26.
 */
public class ZoneVO {
    private Integer zoneId;
    private String zoneName;
    private Integer zoneper;
    private Integer paZoneId;
    private Integer zoneLevel;
    private Integer buId;
    private Integer proDeptId;
    private String zoneDesc;
    private Integer status;
    private Integer manageCenterId;
    private List<Zone> childZoneList;
    private String managerStaffNum;
    private String managerStaffName;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getManagerStaffNum() {
        return managerStaffNum;
    }

    public void setManagerStaffNum(String managerStaffNum) {
        this.managerStaffNum = managerStaffNum;
    }

    public String getManagerStaffName() {
        return managerStaffName;
    }

    public void setManagerStaffName(String managerStaffName) {
        this.managerStaffName = managerStaffName;
    }

    public List<Zone> getChildZoneList() {
        return childZoneList;
    }

    public void setChildZoneList(List<Zone> childZoneList) {
        this.childZoneList = childZoneList;
    }

    /**
     * @return zone_id
     */
    public Integer getZoneId() {
        return zoneId;
    }

    /**
     * @param zoneId
     */
    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    /**
     * @return zone_name
     */
    public String getZoneName() {
        return zoneName;
    }

    /**
     * @param zoneName
     */
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    /**
     * @return zoneper
     */
    public Integer getZoneper() {
        return zoneper;
    }

    /**
     * @param zoneper
     */
    public void setZoneper(Integer zoneper) {
        this.zoneper = zoneper;
    }

    /**
     * @return pa_zone_id
     */
    public Integer getPaZoneId() {
        return paZoneId;
    }

    /**
     * @param paZoneId
     */
    public void setPaZoneId(Integer paZoneId) {
        this.paZoneId = paZoneId;
    }

    public Integer getZoneLevel() {
        return zoneLevel;
    }

    public void setZoneLevel(Integer zoneLevel) {
        this.zoneLevel = zoneLevel;
    }

    public Integer getBuId() {
        return buId;
    }

    public Integer getManageCenterId() {
        return manageCenterId;
    }

    public void setManageCenterId(Integer manageCenterId) {
        this.manageCenterId = manageCenterId;
    }

    public void setBuId(Integer buId) {
        this.buId = buId;
    }

    public Integer getProDeptId() {
        return proDeptId;
    }

    public void setProDeptId(Integer proDeptId) {
        this.proDeptId = proDeptId;
    }

    public String getZoneDesc() {
        return zoneDesc;
    }

    public void setZoneDesc(String zoneDesc) {
        this.zoneDesc = zoneDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
