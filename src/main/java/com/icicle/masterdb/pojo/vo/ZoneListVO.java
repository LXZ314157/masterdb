package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.ManageCenter;
import com.icicle.masterdb.model.Staff;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public class ZoneListVO {
    /**
     * 现场管理中心列表
     */
    private List<ManageCenterVO> mngList;
    /**
     * 区域列表
     */
    private List<ZoneVO> zoneList;

    /**
     * 负责人列表
     * @return
     */
    private List<Staff> managerList;

    public List<Staff> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<Staff> managerList) {
        this.managerList = managerList;
    }

    public List<ZoneVO> getZoneList() {
        return zoneList;
    }

    public void setZoneList(List<ZoneVO> zoneList) {
        this.zoneList = zoneList;
    }

    public List<ManageCenterVO> getMngList() {
        return mngList;
    }

    public void setMngList(List<ManageCenterVO> mngList) {
        this.mngList = mngList;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}