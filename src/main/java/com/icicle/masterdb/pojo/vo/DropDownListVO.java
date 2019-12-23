package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.*;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * @author liurenhua
 */
public class DropDownListVO {
    private List<SharedBuyerVO> buyerVOList;
    private List<CityVO> cityVOList;
    private List<ZoneVO> zoneVOList;
    private List<StoreLevelVO> storeLevelVOList;
    private List<StoreType> storeTypeList;
    private List<BusinessUnitVO> businessUnitVOList;
    private List<ProductLine> productLineLists;
    private List<StoreVO> compareStoreList;
    private List<Costcenter> costcenterList;
    private List<Responsibilitycenter> respcenterList;
    private List<Staff> managerList;
    private List<ManageCenter> managerCenterList;
    private List<Company> companyList;

    private List<Map<String,String>> storeOwnerShipList;
    private List<Map<String,String>> storeClassList;
    private List<Map<String,String>> storeCategoryList;
    private List<Map<String,String>> logoVersionList;
    private List<Map<String,String>> mallTypeList;
    private List<Map<String,String>> storeImageCategoryList;
    private List<Map<String,String>> installVersionList;
    private List<Map<String,String>> addressTypeList;

    public List<ManageCenter> getManagerCenterList() {
        return managerCenterList;
    }
    public List<Map<String, String>> getStoreOwnerShipList() {
        return storeOwnerShipList;
    }

    public void setStoreOwnerShipList(List<Map<String, String>> storeOwnerShipList) {
        this.storeOwnerShipList = storeOwnerShipList;
    }

    public List<Map<String, String>> getAddressTypeList() {
        return addressTypeList;
    }

    public void setAddressTypeList(List<Map<String, String>> addressTypeList) {
        this.addressTypeList = addressTypeList;
    }

    public List<Map<String, String>> getStoreClassList() {
        return storeClassList;
    }

    public void setStoreClassList(List<Map<String, String>> storeClassList) {
        this.storeClassList = storeClassList;
    }

    public List<Map<String, String>> getStoreCategoryList() {
        return storeCategoryList;
    }

    public void setStoreCategoryList(List<Map<String, String>> storeCategoryList) {
        this.storeCategoryList = storeCategoryList;
    }


    public List<Map<String, String>> getLogoVersionList() {
        return logoVersionList;
    }

    public void setLogoVersionList(List<Map<String, String>> logoVersionList) {
        this.logoVersionList = logoVersionList;
    }

    public List<Map<String, String>> getMallTypeList() {
        return mallTypeList;
    }

    public void setMallTypeList(List<Map<String, String>> mallTypeList) {
        this.mallTypeList = mallTypeList;
    }

    public List<Map<String, String>> getStoreImageCategoryList() {
        return storeImageCategoryList;
    }

    public void setStoreImageCategoryList(List<Map<String, String>> storeImageCategoryList) {
        this.storeImageCategoryList = storeImageCategoryList;
    }

    public List<Map<String, String>> getInstallVersionList() {
        return installVersionList;
    }

    public void setInstallVersionList(List<Map<String, String>> installVersionList) {
        this.installVersionList = installVersionList;
    }

    public void setManagerCenterList(List<ManageCenter> managerCenterList) {
        this.managerCenterList = managerCenterList;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    public List<Staff> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<Staff> managerList) {
        this.managerList = managerList;
    }

    public List<StoreVO> getCompareStoreList() {
        return compareStoreList;
    }

    public void setCompareStoreList(List<StoreVO> compareStoreList) {
        this.compareStoreList = compareStoreList;
    }

    public List<SharedBuyerVO> getBuyerVOList() {
        return buyerVOList;
    }

    public void setBuyerVOList(List<SharedBuyerVO> buyerVOList) {
        this.buyerVOList = buyerVOList;
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

    public List<StoreLevelVO> getStoreLevelVOList() {
        return storeLevelVOList;
    }

    public void setStoreLevelVOList(List<StoreLevelVO> storeLevelVOList) {
        this.storeLevelVOList = storeLevelVOList;
    }

    public List<StoreType> getStoreTypeList() {
        return storeTypeList;
    }

    public void setStoreTypeList(List<StoreType> storeTypeList) {
        this.storeTypeList = storeTypeList;
    }

    public List<BusinessUnitVO> getBusinessUnitVOList() {
        return businessUnitVOList;
    }

    public void setBusinessUnitVOList(List<BusinessUnitVO> businessUnitVOList) {
        this.businessUnitVOList = businessUnitVOList;
    }

    public List<ProductLine> getProductLineLists() {
        return productLineLists;
    }

    public void setProductLineLists(List<ProductLine> productLineLists) {
        this.productLineLists = productLineLists;
    }

    public List<Costcenter> getCostcenterList() {
        return costcenterList;
    }

    public void setCostcenterList(List<Costcenter> costcenterList) {
        this.costcenterList = costcenterList;
    }

    public List<Responsibilitycenter> getRespcenterList() {
        return respcenterList;
    }

    public void setRespcenterList(List<Responsibilitycenter> respcenterList) {
        this.respcenterList = respcenterList;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
