package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "view_store")
public class ViewStore {

    @Column(name = "store_id")
    private String storeId;
    @Id
    @Column(name = "store_no")
    private Integer storeNo;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_short_name")
    private String storeShortName;

    @Column(name = "store_spell")
    private String storeSpell;

    @Column(name = "buyer_id")
    private String buyerId;

    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "manager_num")
    private String managerNum;

    @Column(name = "staff_name_local")
    private String staffNameLocal;

    @Column(name = "zone2_id")
    private Integer zoneId;

    @Column(name = "store_image_category")
    private Integer storeImageCategory;

    @Column(name = "logo_version")
    private Integer logoVersion;

    @Column(name = "install_version")
    private Integer installVersion;

    @Column(name = "zone_name")
    private String zoneName;

    @Column(name = "store_type_id")
    private Integer storeTypeId;

    @Column(name = "store_type_name")
    private String storeTypeName;

    @Column(name = "store_level_id")
    private Integer storeLevelId;

    @Column(name = "store_level_name")
    private String storeLevelName;

    @Column(name = "store_contact")
    private String storeContact;

    @Column(name = "store_phone")
    private String storePhone;

//    @Column(name = "product_line_name")
//    private String productLineName;

    @Column(name = "store_fax")
    private String storeFax;

    @Column(name = "store_mobile")
    private String storeMobile;

    @Column(name = "store_email")
    private String storeEmail;

    @Column(name = "store_desc")
    private String storeDesc;

    @Column(name = "pro_dept_id")
    private Integer proDeptId;

    @Column(name = "pro_dept_name")
    private String proDeptName;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "open_date")
    private Date openDate;

    @Column(name = "close_date")
    private Date closeDate;

    @Column(name = "contract_period")
    private Integer contractPeriod;

    @Column(name = "store_size")
    private BigDecimal storeSize;

    @Column(name = "store_state")
    private Integer storeState;

    @Column(name = "bu_id")
    private Integer buId;

    @Column(name = "bu_name")
    private String buName;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "store_ownership")
    private Integer storeOwnership;

    @Column(name = "store_category")
    private Integer storeCategory;

    @Column(name = "mall_type")
    private Integer mallType;


    @Column(name = "manage_center_id")
    private Integer manageCenterId;

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "resp_center_id")
    private String respCenterId;

//    @Column(name = "compare_store_name")
//    private String compareStoreName;

    @Column(name = "cost_center_id")
    private String costCenterId;

    @Column(name = "store_address")
    private String storeAddress;

    private String floor;

    private BigDecimal deposit;

    private BigDecimal rental;

    @Column(name = "sale_point")
    private BigDecimal salePoint;

    @Column(name = "store_class")
    private Integer storeClass;

    @Column(name = "recent_reset_time")
    private Date recentResetTime;

    @Column(name = "decoration_standard")
    private String decorationStandard;

    @Column(name = "decoration_limit_years")
    private String decorationLimitYears;

    @Column(name = "store_address_longitude")
    private BigDecimal storeAddressLongitude;

    @Column(name = "store_address_latitude")
    private BigDecimal storeAddressLatitude;

    /**
     * @return store_id
     */
    public String getStoreId() {
        return storeId;
    }

    /**
     * @param storeId
     */
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    /**
     * @return store_no
     */
    public Integer getStoreNo() {
        return storeNo;
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

    public String getStaffNameLocal() {
        return staffNameLocal;
    }

    public void setStaffNameLocal(String staffNameLocal) {
        this.staffNameLocal = staffNameLocal;
    }

    public void setLogoVersion(Integer logoVersion) {
        this.logoVersion = logoVersion;
    }

    public Integer getInstallVersion() {
        return installVersion;
    }

//    public String getProductLineName() {
//        return productLineName;
//    }
//
//    public void setProductLineName(String productLineName) {
//        this.productLineName = productLineName;
//    }

    public void setInstallVersion(Integer installVersion) {
        this.installVersion = installVersion;
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

    /**
     * @param storeNo
     */
    public void setStoreNo(Integer storeNo) {
        this.storeNo = storeNo;
    }

    /**
     * @return store_name
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * @param storeName
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * @return store_short_name
     */
    public String getStoreShortName() {
        return storeShortName;
    }

    /**
     * @param storeShortName
     */
    public void setStoreShortName(String storeShortName) {
        this.storeShortName = storeShortName;
    }

    /**
     * @return store_spell
     */
    public String getStoreSpell() {
        return storeSpell;
    }

    /**
     * @param storeSpell
     */
    public void setStoreSpell(String storeSpell) {
        this.storeSpell = storeSpell;
    }

    /**
     * @return buyer_id
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * @param buyerId
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * @return buyer_name
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * @param buyerName
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
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
     * @return store_type_id
     */
    public Integer getStoreTypeId() {
        return storeTypeId;
    }

    /**
     * @param storeTypeId
     */
    public void setStoreTypeId(Integer storeTypeId) {
        this.storeTypeId = storeTypeId;
    }

    /**
     * @return store_type_name
     */
    public String getStoreTypeName() {
        return storeTypeName;
    }

    /**
     * @param storeTypeName
     */
    public void setStoreTypeName(String storeTypeName) {
        this.storeTypeName = storeTypeName;
    }

    /**
     * @return store_level_id
     */
    public Integer getStoreLevelId() {
        return storeLevelId;
    }

    /**
     * @param storeLevelId
     */
    public void setStoreLevelId(Integer storeLevelId) {
        this.storeLevelId = storeLevelId;
    }

    /**
     * @return store_level_name
     */
    public String getStoreLevelName() {
        return storeLevelName;
    }

    /**
     * @param storeLevelName
     */
    public void setStoreLevelName(String storeLevelName) {
        this.storeLevelName = storeLevelName;
    }

    /**
     * @return store_contact
     */
    public String getStoreContact() {
        return storeContact;
    }

    /**
     * @param storeContact
     */
    public void setStoreContact(String storeContact) {
        this.storeContact = storeContact;
    }

    /**
     * @return store_phone
     */
    public String getStorePhone() {
        return storePhone;
    }

    /**
     * @param storePhone
     */
    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    /**
     * @return store_fax
     */
    public String getStoreFax() {
        return storeFax;
    }

    /**
     * @param storeFax
     */
    public void setStoreFax(String storeFax) {
        this.storeFax = storeFax;
    }

    /**
     * @return store_mobile
     */
    public String getStoreMobile() {
        return storeMobile;
    }

    /**
     * @param storeMobile
     */
    public void setStoreMobile(String storeMobile) {
        this.storeMobile = storeMobile;
    }

    /**
     * @return store_email
     */
    public String getStoreEmail() {
        return storeEmail;
    }

    /**
     * @param storeEmail
     */
    public void setStoreEmail(String storeEmail) {
        this.storeEmail = storeEmail;
    }

    /**
     * @return store_desc
     */
    public String getStoreDesc() {
        return storeDesc;
    }

    /**
     * @param storeDesc
     */
    public void setStoreDesc(String storeDesc) {
        this.storeDesc = storeDesc;
    }

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
     * @return city_id
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * @param cityId
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * @return city_name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    /**
     * @return contract_period
     */
    public Integer getContractPeriod() {
        return contractPeriod;
    }

    /**
     * @param contractPeriod
     */
    public void setContractPeriod(Integer contractPeriod) {
        this.contractPeriod = contractPeriod;
    }

    /**
     * @return store_size
     */
    public BigDecimal getStoreSize() {
        return storeSize;
    }

    /**
     * @param storeSize
     */
    public void setStoreSize(BigDecimal storeSize) {
        this.storeSize = storeSize;
    }

    /**
     * @return store_state
     */
    public Integer getStoreState() {
        return storeState;
    }

    /**
     * @param storeState
     */
    public void setStoreState(Integer storeState) {
        this.storeState = storeState;
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
     * @return bu_name
     */
    public String getBuName() {
        return buName;
    }

    /**
     * @param buName
     */
    public void setBuName(String buName) {
        this.buName = buName;
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

    public String getManagerNum() {
        return managerNum;
    }

    public void setManagerNum(String managerNum) {
        this.managerNum = managerNum;
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
}