package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.*;
import com.icicle.masterdb.util.StringUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author liurenhua
 */
public class StoreVO {
    private String storeId;
    private Integer storeNo;
    private String storeName;
    private String storeShortName;
    private String storeSpell;
    private String buyerId;
    private String buyerName;
    private Integer zoneId;
    private String zoneName;
    private String subZoneName;
    private Integer storeTypeId;
    private String storeTypeName;
    private Integer storeLevelId;
    private String storeLevelName;
    private String storeContact;
    private String storePhone;
    private String storeFax;
    private String storeMobile;
    private String storeEmail;
    private String storeDesc;
    private String managerNum;
    private Integer proDeptId;
    private String proDeptName;

    private Integer cityId;
    private String cityName;
    private Integer provinceId;
    private String provinceName;
    private Integer countryId;
    private String countryName;
    private String buName;

    private Date openDate;
    private Date closeDate;
    private Integer contractPeriod;
    private BigDecimal storeSize;
    private Integer storeState;
    private Integer buId;
    private String syn;
    private String createdBy;
    private String areaCode;
    private String cityLevelName;
    private Integer storeImageCategory;
    private Integer logoVersion;
    private Integer installVersion;
    private Integer storeOwnership;
    private Integer storeCategory;
    private Integer mallType;
    private Integer manageCenterId;
    private String companyId;
    private String respCenterId;
    private String costCenterId;
    private String compareStoreNames;
    private String compareStoreNos;
    private String storeNum;
    private String lastUpdatedBy;
    private String buyerNum;
    private String storeAddress;
    private String floor;
    private BigDecimal deposit;
    private BigDecimal rental;
    private BigDecimal salePoint;
    private Integer storeClass;
    private String productLine;
    private String creationDate;
    private Date recentResetTime;
    private String decorationStandard;
    private String decorationLimitYears;
    private BigDecimal storeAddressLongitude;
    private BigDecimal storeAddressLatitude;
    private List<StoreAttribute> storeAttributeList;
    private List<ProductLine> productLineLists;
    private List<StoreProductLine> productLineList;
    private List<Staff> managerList;
    private List<SyncProductLineVO> oldProductLineList;

    public List<SyncProductLineVO> getOldProductLineList() {
        return oldProductLineList;
    }

    public void setOldProductLineList(List<SyncProductLineVO> oldProductLineList) {
        this.oldProductLineList = oldProductLineList;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getBuyerNum() {
        return buyerNum;
    }

    public void setBuyerNum(String buyerNum) {
        this.buyerNum = buyerNum;
    }


    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }

    public String getCompareStoreNos() {
        return compareStoreNos;
    }

    public void setCompareStoreNos(String compareStoreNos) {
        this.compareStoreNos = compareStoreNos;
    }

    public Integer getStoreImageCategory() {
        return storeImageCategory;
    }

    public void setStoreImageCategory(Integer storeImageCategory) {
        this.storeImageCategory = storeImageCategory;
    }

    public Integer getLogoVersion() {
        return logoVersion;
    }

    public void setLogoVersion(Integer logoVersion) {
        this.logoVersion = logoVersion;
    }

    public Integer getInstallVersion() {
        return installVersion;
    }

    public void setInstallVersion(Integer installVersion) {
        this.installVersion = installVersion;
    }

    public BigDecimal getStoreAddressLongitude() {
        return storeAddressLongitude;
    }

    public void setStoreAddressLongitude(BigDecimal storeAddressLongitude) {
        this.storeAddressLongitude = storeAddressLongitude;
    }

    public BigDecimal getStoreAddressLatitude() {
        return storeAddressLatitude;
    }

    public void setStoreAddressLatitude(BigDecimal storeAddressLatitude) {
        this.storeAddressLatitude = storeAddressLatitude;
    }

    public List<Staff> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<Staff> managerList) {
        this.managerList = managerList;
    }

    public String getManagerNum() {
        return managerNum;
    }

    public void setManagerNum(String managerNum) {
        this.managerNum = managerNum;
    }

    public String getBuyerName() {
        return buyerName;
    }

    private List<StoreComparison> compareByStoreNoList;

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }


    public List<StoreComparison> getCompareByStoreNoList() {
        return compareByStoreNoList;
    }

    public void setCompareByStoreNoList(List<StoreComparison> compareByStoreNoList) {
        this.compareByStoreNoList = compareByStoreNoList;
    }

    public String getCompareStoreNames() {
        return compareStoreNames;
    }

    public void setCompareStoreNames(String compareStoreNames) {
        this.compareStoreNames = compareStoreNames;
    }

    public List<StoreProductLine> getProductLineList() {
        return productLineList;
    }

    public void setProductLineList(List<StoreProductLine> productLineList) {
        this.productLineList = productLineList;
    }


    public List<ProductLine> getProductLineLists() {
        return productLineLists;
    }

    public void setProductLineLists(List<ProductLine> productLineLists) {
        this.productLineLists = productLineLists;
    }


    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getSubZoneName() {
        return subZoneName;
    }

    public void setSubZoneName(String subZoneName) {
        this.subZoneName = subZoneName;
    }

    public String getStoreTypeName() {
        return storeTypeName;
    }

    public void setStoreTypeName(String storeTypeName) {
        this.storeTypeName = storeTypeName;
    }

    public String getStoreLevelName() {
        return storeLevelName;
    }

    public void setStoreLevelName(String storeLevelName) {
        this.storeLevelName = storeLevelName;
    }

    public String getProDeptName() {
        return proDeptName;
    }

    public Date getRecentResetTime() {
        return recentResetTime;
    }

    public void setRecentResetTime(Date recentResetTime) {
        this.recentResetTime = recentResetTime;
    }

    public String getDecorationStandard() {
        return decorationStandard;
    }

    public void setDecorationStandard(String decorationStandard) {
        this.decorationStandard = decorationStandard;
    }

    public String getDecorationLimitYears() {
        return decorationLimitYears;
    }

    public void setDecorationLimitYears(String decorationLimitYears) {
        this.decorationLimitYears = decorationLimitYears;
    }

    public void setProDeptName(String proDeptName) {
        this.proDeptName = proDeptName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Integer getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(Integer storeNo) {
        this.storeNo = storeNo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreShortName() {
        return storeShortName;
    }

    public void setStoreShortName(String storeShortName) {
        this.storeShortName = storeShortName;
    }

    public String getStoreSpell() {
        return storeSpell;
    }

    public void setStoreSpell(String storeSpell) {
        this.storeSpell = storeSpell;
    }

    public String getBuyerId() {
        return buyerId;
    }


    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public Integer getStoreTypeId() {
        return storeTypeId;
    }

    public void setStoreTypeId(Integer storeTypeId) {
        this.storeTypeId = storeTypeId;
    }

    public Integer getStoreLevelId() {
        return storeLevelId;
    }

    public void setStoreLevelId(Integer storeLevelId) {
        this.storeLevelId = storeLevelId;
    }

    public String getStoreContact() {
        return storeContact;
    }

    public void setStoreContact(String storeContact) {
        this.storeContact = storeContact;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getStoreFax() {
        return storeFax;
    }

    public void setStoreFax(String storeFax) {
        this.storeFax = storeFax;
    }

    public String getStoreMobile() {
        return storeMobile;
    }

    public void setStoreMobile(String storeMobile) {
        this.storeMobile = storeMobile;
    }

    public String getStoreEmail() {
        return storeEmail;
    }

    public void setStoreEmail(String storeEmail) {
        this.storeEmail = storeEmail;
    }

    public String getStoreDesc() {
        return storeDesc;
    }

    public void setStoreDesc(String storeDesc) {
        this.storeDesc = storeDesc;
    }

    public Integer getProDeptId() {
        return proDeptId;
    }

    public void setProDeptId(Integer proDeptId) {
        this.proDeptId = proDeptId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getContractPeriod() {
        return contractPeriod;
    }

    public void setContractPeriod(Integer contractPeriod) {
        this.contractPeriod = contractPeriod;
    }

    public BigDecimal getStoreSize() {
        return storeSize;
    }

    public void setStoreSize(BigDecimal storeSize) {
        this.storeSize = storeSize;
    }

    public Integer getStoreState() {
        return storeState;
    }

    public void setStoreState(Integer storeState) {
        this.storeState = storeState;
    }

    public Integer getBuId() {
        return buId;
    }

    public void setBuId(Integer buId) {
        this.buId = buId;
    }

    public List<StoreAttribute> getStoreAttributeList() {
        return storeAttributeList;
    }

    public void setStoreAttributeList(List<StoreAttribute> storeAttributeList) {
        this.storeAttributeList = storeAttributeList;
    }

    public String getSyn() {
        return syn;
    }

    public void setSyn(String syn) {
        this.syn = syn;
    }


    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCityLevelName() {
        return cityLevelName;
    }

    public void setCityLevelName(String cityLevelName) {
        this.cityLevelName = cityLevelName;
    }

    public Integer getStoreOwnership() {
        return storeOwnership;
    }

    public void setStoreOwnership(Integer storeOwnership) {
        this.storeOwnership = storeOwnership;
    }

    public Integer getStoreCategory() {
        return storeCategory;
    }

    public void setStoreCategory(Integer storeCategory) {
        this.storeCategory = storeCategory;
    }

    public Integer getMallType() {
        return mallType;
    }

    public void setMallType(Integer mallType) {
        this.mallType = mallType;
    }

    public Integer getManageCenterId() {
        return manageCenterId;
    }

    public void setManageCenterId(Integer manageCenterId) {
        this.manageCenterId = manageCenterId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getRespCenterId() {
        return respCenterId;
    }

    public void setRespCenterId(String respCenterId) {
        this.respCenterId = respCenterId;
    }

    public String getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(String costCenterId) {
        this.costCenterId = costCenterId;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getRental() {
        return rental;
    }

    public void setRental(BigDecimal rental) {
        this.rental = rental;
    }

    public BigDecimal getSalePoint() {
        return salePoint;
    }

    public void setSalePoint(BigDecimal salePoint) {
        this.salePoint = salePoint;
    }

    public Integer getStoreClass() {
        return storeClass;
    }

    public void setStoreClass(Integer storeClass) {
        this.storeClass = storeClass;
    }


    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
