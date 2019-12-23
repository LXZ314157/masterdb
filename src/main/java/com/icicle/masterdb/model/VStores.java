package com.icicle.masterdb.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "v_Stores")
public class VStores {
    @Column(name = "BuyerLevelid")
    private Integer buyerlevelid;

    @Column(name = "store_ownership_name")
    private String storeOwnershipName;

    @Column(name = "store_ownership_id")
    private Integer storeOwnershipId;

    @Column(name = "store_class_name")
    private String storeClassName;

    @Column(name = "store_class_id")
    private Integer storeClassId;

    @Column(name = "store_category_name")
    private String storeCategoryName;

    @Column(name = "store_category_id")
    private Integer storeCategoryId;

    @Column(name = "logo_version_name")
    private String logoVersionName;

    @Column(name = "logo_version_id")
    private Integer logoVersionId;

    @Column(name = "mall_type_name")
    private String mallTypeName;

    @Column(name = "mall_type_id")
    private Integer mallTypeId;

    @Column(name = "store_visualize_version_name")
    private String storeVisualizeVersionName;

    @Column(name = "store_visualize_version_id")
    private Integer storeVisualizeVersionId;

    @Column(name = "manage_center_name")
    private String manageCenterName;

    @Column(name = "manage_center_id")
    private Integer manageCenterId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "trim_visualize_name")
    private String trimVisualizeName;

    @Column(name = "trim_visualize_id")
    private Integer trimVisualizeId;

    @Column(name = "resp_center_name")
    private String respCenterName;

    @Column(name = "resp_center_id")
    private String respCenterId;

    @Column(name = "cost_center_name")
    private String costCenterName;

    @Column(name = "cost_center_id")
    private String costCenterId;

    @Column(name = "sub_zone_name")
    private String subZoneName;

    @Column(name = "sub_zone_id")
    private Integer subZoneId;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "last_trim_date")
    private Date lastTrimDate;

    @Column(name = "compare_store_id")
    private String compareStoreId;

    @Column(name = "store_manage_mode")
    private String storeManageMode;

    @Column(name = "store_manage_model_name")
    private String storeManageModelName;

    @Column(name = "province_id")
    private Integer provinceId;

    private String floor;

    private String buyername;

    private String buyershortname;

    @Column(name = "BuyerState")
    private Integer buyerstate;

    private Integer cityid;

    private String cityname;

    private Integer citylevel;

    private String citylevelname;

    private String citycode;

    @Column(name = "Zonename")
    private String zonename;

    private Integer storeunit;

    @Column(name = "AreaCode")
    private String areacode;

    @Column(name = "AreaName")
    private String areaname;

    private String levelname;

    private String storetypename;

    private String storeid;

    @Id
    @Column(name = "storeNO")
    private Integer storeno;

    private String storename;

    private String shortname;

    private String storespell;

    private Integer storecity;

    private String contact;

    private String storeaddress;

    private String phone;

    private String mobile;

    private String fax;

    private Integer storelevel;

    @Column(name = "Buyerid")
    private String buyerid;

    private Date createtime;

    private String creater;

    private String lastediter;

    private Date lastedittime;

    private Integer storestate;

    private Date starttime;

    private Date endtime;

    @Column(name = "ContractPeriod")
    private Integer contractperiod;

    private String storerent;

    private String royaltyrate;

    private Integer storetypeid;

    private Integer zoneid;

    @Column(name = "Expr1")
    private Integer expr1;

    @Column(name = "ProDeptId")
    private Integer prodeptid;

    private String post;

    private String email;

    private BigDecimal storearea;

    private String remark;

    @Column(name = "storeid")
    private Object storeAttributeList;

    /**
     * @return BuyerLevelid
     */
    public Integer getBuyerlevelid() {
        return buyerlevelid;
    }


    public String getStoreManageMode() {
        return storeManageMode;
    }

    public void setStoreManageMode(String storeManageMode) {
        this.storeManageMode = storeManageMode;
    }

    public String getStoreManageModelName() {
        return storeManageModelName;
    }

    public void setStoreManageModelName(String storeManageModelName) {
        this.storeManageModelName = storeManageModelName;
    }

    /**
     * @param buyerlevelid
     */
    public void setBuyerlevelid(Integer buyerlevelid) {
        this.buyerlevelid = buyerlevelid;
    }

    /**
     * @return buyername
     */
    public String getBuyername() {
        return buyername;
    }

    /**
     * @param buyername
     */
    public void setBuyername(String buyername) {
        this.buyername = buyername;
    }

    /**
     * @return buyershortname
     */
    public String getBuyershortname() {
        return buyershortname;
    }

    /**
     * @param buyershortname
     */
    public void setBuyershortname(String buyershortname) {
        this.buyershortname = buyershortname;
    }

    /**
     * @return BuyerState
     */
    public Integer getBuyerstate() {
        return buyerstate;
    }

    /**
     * @param buyerstate
     */
    public void setBuyerstate(Integer buyerstate) {
        this.buyerstate = buyerstate;
    }

    /**
     * @return cityid
     */
    public Integer getCityid() {
        return cityid;
    }

    /**
     * @param cityid
     */
    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    /**
     * @return cityname
     */
    public String getCityname() {
        return cityname;
    }

    /**
     * @param cityname
     */
    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    /**
     * @return citylevel
     */
    public Integer getCitylevel() {
        return citylevel;
    }

    /**
     * @param citylevel
     */
    public void setCitylevel(Integer citylevel) {
        this.citylevel = citylevel;
    }

    /**
     * @return citylevelname
     */
    public String getCitylevelname() {
        return citylevelname;
    }

    /**
     * @param citylevelname
     */
    public void setCitylevelname(String citylevelname) {
        this.citylevelname = citylevelname;
    }

    /**
     * @return citycode
     */
    public String getCitycode() {
        return citycode;
    }

    /**
     * @param citycode
     */
    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    /**
     * @return Zonename
     */
    public String getZonename() {
        return zonename;
    }

    /**
     * @param zonename
     */
    public void setZonename(String zonename) {
        this.zonename = zonename;
    }

    /**
     * @return storeunit
     */
    public Integer getStoreunit() {
        return storeunit;
    }

    /**
     * @param storeunit
     */
    public void setStoreunit(Integer storeunit) {
        this.storeunit = storeunit;
    }

    /**
     * @return AreaCode
     */
    public String getAreacode() {
        return areacode;
    }

    /**
     * @param areacode
     */
    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    /**
     * @return AreaName
     */
    public String getAreaname() {
        return areaname;
    }

    /**
     * @param areaname
     */
    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    /**
     * @return levelname
     */
    public String getLevelname() {
        return levelname;
    }

    /**
     * @param levelname
     */
    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    /**
     * @return storetypename
     */
    public String getStoretypename() {
        return storetypename;
    }

    /**
     * @param storetypename
     */
    public void setStoretypename(String storetypename) {
        this.storetypename = storetypename;
    }

    /**
     * @return storeid
     */
    public String getStoreid() {
        return storeid;
    }

    /**
     * @param storeid
     */
    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    /**
     * @return storeNO
     */
    public Integer getStoreno() {
        return storeno;
    }

    /**
     * @param storeno
     */
    public void setStoreno(Integer storeno) {
        this.storeno = storeno;
    }

    /**
     * @return storename
     */
    public String getStorename() {
        return storename;
    }

    /**
     * @param storename
     */
    public void setStorename(String storename) {
        this.storename = storename;
    }

    /**
     * @return shortname
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * @param shortname
     */
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    /**
     * @return storespell
     */
    public String getStorespell() {
        return storespell;
    }

    /**
     * @param storespell
     */
    public void setStorespell(String storespell) {
        this.storespell = storespell;
    }

    /**
     * @return storecity
     */
    public Integer getStorecity() {
        return storecity;
    }

    /**
     * @param storecity
     */
    public void setStorecity(Integer storecity) {
        this.storecity = storecity;
    }

    /**
     * @return contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return storeaddress
     */
    public String getStoreaddress() {
        return storeaddress;
    }

    /**
     * @param storeaddress
     */
    public void setStoreaddress(String storeaddress) {
        this.storeaddress = storeaddress;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return storelevel
     */
    public Integer getStorelevel() {
        return storelevel;
    }

    /**
     * @param storelevel
     */
    public void setStorelevel(Integer storelevel) {
        this.storelevel = storelevel;
    }

    /**
     * @return Buyerid
     */
    public String getBuyerid() {
        return buyerid;
    }

    /**
     * @param buyerid
     */
    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid;
    }

    /**
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }


    public Date getLastTrimDate() {
        return lastTrimDate;
    }

    public void setLastTrimDate(Date lastTrimDate) {
        this.lastTrimDate = lastTrimDate;
    }

    /**
     * @return creater
     */
    public String getCreater() {
        return creater;
    }

    /**
     * @param creater
     */
    public void setCreater(String creater) {
        this.creater = creater;
    }

    /**
     * @return lastediter
     */
    public String getLastediter() {
        return lastediter;
    }

    /**
     * @param lastediter
     */
    public void setLastediter(String lastediter) {
        this.lastediter = lastediter;
    }

    /**
     * @return lastedittime
     */
    public Date getLastedittime() {
        return lastedittime;
    }

    /**
     * @param lastedittime
     */
    public void setLastedittime(Date lastedittime) {
        this.lastedittime = lastedittime;
    }

    /**
     * @return storestate
     */
    public Integer getStorestate() {
        return storestate;
    }

    /**
     * @param storestate
     */
    public void setStorestate(Integer storestate) {
        this.storestate = storestate;
    }

    /**
     * @return starttime
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * @param starttime
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * @return endtime
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * @param endtime
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * @return ContractPeriod
     */
    public Integer getContractperiod() {
        return contractperiod;
    }

    /**
     * @param contractperiod
     */
    public void setContractperiod(Integer contractperiod) {
        this.contractperiod = contractperiod;
    }

    /**
     * @return storerent
     */
    public String getStorerent() {
        return storerent;
    }

    /**
     * @param storerent
     */
    public void setStorerent(String storerent) {
        this.storerent = storerent;
    }

    /**
     * @return royaltyrate
     */
    public String getRoyaltyrate() {
        return royaltyrate;
    }

    /**
     * @param royaltyrate
     */
    public void setRoyaltyrate(String royaltyrate) {
        this.royaltyrate = royaltyrate;
    }

    /**
     * @return storetypeid
     */
    public Integer getStoretypeid() {
        return storetypeid;
    }

    /**
     * @param storetypeid
     */
    public void setStoretypeid(Integer storetypeid) {
        this.storetypeid = storetypeid;
    }

    /**
     * @return zoneid
     */
    public Integer getZoneid() {
        return zoneid;
    }

    /**
     * @param zoneid
     */
    public void setZoneid(Integer zoneid) {
        this.zoneid = zoneid;
    }

    /**
     * @return Expr1
     */
    public Integer getExpr1() {
        return expr1;
    }

    /**
     * @param expr1
     */
    public void setExpr1(Integer expr1) {
        this.expr1 = expr1;
    }

    /**
     * @return ProDeptId
     */
    public Integer getProdeptid() {
        return prodeptid;
    }

    /**
     * @param prodeptid
     */
    public void setProdeptid(Integer prodeptid) {
        this.prodeptid = prodeptid;
    }

    /**
     * @return post
     */
    public String getPost() {
        return post;
    }

    /**
     * @param post
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return storearea
     */
    public BigDecimal getStorearea() {
        return storearea;
    }

    /**
     * @param storearea
     */
    public void setStorearea(BigDecimal storearea) {
        this.storearea = storearea;
    }

    public String getStoreOwnershipName() {
        return storeOwnershipName;
    }

    public void setStoreOwnershipName(String storeOwnershipName) {
        this.storeOwnershipName = storeOwnershipName;
    }

    public Integer getStoreOwnershipId() {
        return storeOwnershipId;
    }

    public void setStoreOwnershipId(Integer storeOwnershipId) {
        this.storeOwnershipId = storeOwnershipId;
    }

    public String getStoreClassName() {
        return storeClassName;
    }

    public void setStoreClassName(String storeClassName) {
        this.storeClassName = storeClassName;
    }

    public Integer getStoreClassId() {
        return storeClassId;
    }

    public void setStoreClassId(Integer storeClassId) {
        this.storeClassId = storeClassId;
    }

    public String getStoreCategoryName() {
        return storeCategoryName;
    }

    public void setStoreCategoryName(String storeCategoryName) {
        this.storeCategoryName = storeCategoryName;
    }

    public Integer getStoreCategoryId() {
        return storeCategoryId;
    }

    public void setStoreCategoryId(Integer storeCategoryId) {
        this.storeCategoryId = storeCategoryId;
    }

    public String getLogoVersionName() {
        return logoVersionName;
    }

    public void setLogoVersionName(String logoVersionName) {
        this.logoVersionName = logoVersionName;
    }

    public Integer getLogoVersionId() {
        return logoVersionId;
    }

    public void setLogoVersionId(Integer logoVersionId) {
        this.logoVersionId = logoVersionId;
    }

    public String getMallTypeName() {
        return mallTypeName;
    }

    public void setMallTypeName(String mallTypeName) {
        this.mallTypeName = mallTypeName;
    }

    public Integer getMallTypeId() {
        return mallTypeId;
    }

    public void setMallTypeId(Integer mallTypeId) {
        this.mallTypeId = mallTypeId;
    }

    public String getStoreVisualizeVersionName() {
        return storeVisualizeVersionName;
    }

    public void setStoreVisualizeVersionName(String storeVisualizeVersionName) {
        this.storeVisualizeVersionName = storeVisualizeVersionName;
    }

    public Integer getStoreVisualizeVersionId() {
        return storeVisualizeVersionId;
    }

    public void setStoreVisualizeVersionId(Integer storeVisualizeVersionId) {
        this.storeVisualizeVersionId = storeVisualizeVersionId;
    }

    public String getManageCenterName() {
        return manageCenterName;
    }

    public void setManageCenterName(String manageCenterName) {
        this.manageCenterName = manageCenterName;
    }

    public Integer getManageCenterId() {
        return manageCenterId;
    }

    public void setManageCenterId(Integer manageCenterId) {
        this.manageCenterId = manageCenterId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTrimVisualizeName() {
        return trimVisualizeName;
    }

    public void setTrimVisualizeName(String trimVisualizeName) {
        this.trimVisualizeName = trimVisualizeName;
    }

    public Integer getTrimVisualizeId() {
        return trimVisualizeId;
    }

    public void setTrimVisualizeId(Integer trimVisualizeId) {
        this.trimVisualizeId = trimVisualizeId;
    }

    public String getRespCenterName() {
        return respCenterName;
    }

    public void setRespCenterName(String respCenterName) {
        this.respCenterName = respCenterName;
    }

    public String getRespCenterId() {
        return respCenterId;
    }

    public void setRespCenterId(String respCenterId) {
        this.respCenterId = respCenterId;
    }

    public String getCostCenterName() {
        return costCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
    }

    public String getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(String costCenterId) {
        this.costCenterId = costCenterId;
    }

    public String getSubZoneName() {
        return subZoneName;
    }

    public void setSubZoneName(String subZoneName) {
        this.subZoneName = subZoneName;
    }

    public Integer getSubZoneId() {
        return subZoneId;
    }

    public void setSubZoneId(Integer subZoneId) {
        this.subZoneId = subZoneId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCompareStoreId() {
        return compareStoreId;
    }

    public void setCompareStoreId(String compareStoreId) {
        this.compareStoreId = compareStoreId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Object getStoreAttributeList() {
        return storeAttributeList;
    }

    public void setStoreAttributeList(Object storeAttributeList) {
        this.storeAttributeList = storeAttributeList;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}