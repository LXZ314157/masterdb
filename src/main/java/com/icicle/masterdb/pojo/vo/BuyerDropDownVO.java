package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.ManageCenter;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public class BuyerDropDownVO {
    private List<CityVO> cityVOList;
    private List<ZoneVO> zoneVOList;
    private List<BuyerTypeVO> buyerTypeVOList;
    private List<ManageCenter> manageCenterList;

    public List<ManageCenter> getManageCenterList() {
        return manageCenterList;
    }

    public void setManageCenterList(List<ManageCenter> manageCenterList) {
        this.manageCenterList = manageCenterList;
    }

    public List<CityVO> getCityVOList() {
        return cityVOList;
    }

    public void setCityVOList(List<CityVO> cityVOList) {
        this.cityVOList = cityVOList;
    }

    public List<ZoneVO> getZoneVOList() {
        return zoneVOList;
    }

    public void setZoneVOList(List<ZoneVO> zoneVOList) {
        this.zoneVOList = zoneVOList;
    }

    public List<BuyerTypeVO> getBuyerTypeVOList() {
        return buyerTypeVOList;
    }

    public void setBuyerTypeVOList(List<BuyerTypeVO> buyerTypeVOList) {
        this.buyerTypeVOList = buyerTypeVOList;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
