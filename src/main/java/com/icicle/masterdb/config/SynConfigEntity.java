package com.icicle.masterdb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liurenhua
 */
@Component
@ConfigurationProperties(prefix = "syn")
public class SynConfigEntity {
    private String storeEmax;
    private String storeItransfer;
    private String host;
    private String buyerEmax;
    private String buyerWms;
    private String buyerRFID;
    private String storeRFID;

    /*** 主数据代理商同步到新伯俊*/
    private String buyerBurgeon;

    /** 云学堂删除组织同步 */
    private String delOus;

    private String syncSizeGroup2Burgeon;
    private String buyerItransfer;
    private String materialUpdateService;
    private String productUpdateService;
    private String productUpgradeService;
    private String fabricUpgradeService;
    private String syncZone;
    private String areaSyncEmax;
    private String citySyncEmax;
    private String storeTypeSyncEmax;
    private String postSync2OA;
    private String zoneSync2Yxt;

    /** 主数据人员同步到OA和IS */
    private String staffSync2OAIsm;

    /** 主数据店铺同步到小钛 */
    private String storeSync2XiaoTai;

    /** 主数据经销商同步到小钛 */
    private String buyerSync2XiaoTai;

    /** 主数据人员同步到汇联易 */
    private String staffSync2CH;
    /** 主数据部门矩阵同步到OA */
    private String departmentMatrixSync2OA;
    /** 主数据部门信息同步到OA */
    private String departmentSync2OA;
    /** 主数据部门信息同步到汇联易 */
    private String departmentSync2CH;
    /** 币种同步到伯俊 */
    private String currencySync2Burgeon;
    /** 汇率同步到伯俊 */
    private String exchangeRateSync2Burgeon;

    /** 产品（一级）同步到伯俊 */
    private String onecategorySync2Burgeon;

    /** 产品（二级）同步到伯俊 */
    private String secondcategorySync2Burgeon;

    /** 产品（三级）同步到伯俊 */
    private String thirdcategorySync2Burgeon;

    /** 产品（四级）同步到伯俊 */
    private String fourthcategorySync2Burgeon;

    /** 成本中心同步到EHR、汇联易  */
    private String costcenterSync;
    /** 店铺同步到伯俊  */
    private String storeBuygeon;

    /** 店铺/商品同步到OA  */
    private String storeSkuSync2Oa;

    /** 代理商同步到伯俊  */
    private String supplierSync2Burgeon;
    /** 代理商同步到WMS  */
    private String supplierSync2WMS;
    /** 代理商同步到RFID  */
    private String supplierSync2RFID;
    /** 产品线同步到伯俊  */
    private String productLineSync2Burgeon;
    private String storeAddrSync2Burgeon;
    private String mproxy;
    private String citySyncBurgeon;
    private String propertySyncBurgeon;
    private String syncSize2Burgeon;
    private String syncProduct2Buygeon;
    private String syncSku;
    private String burgeonMaterialContrain;


    public String getStoreSync2XiaoTai() {
        return storeSync2XiaoTai;
    }

    public void setStoreSync2XiaoTai(String storeSync2XiaoTai) {
        this.storeSync2XiaoTai = storeSync2XiaoTai;
    }

    public String getBuyerSync2XiaoTai() {
        return buyerSync2XiaoTai;
    }

    public void setBuyerSync2XiaoTai(String buyerSync2XiaoTai) {
        this.buyerSync2XiaoTai = buyerSync2XiaoTai;
    }

    public String getStoreSkuSync2Oa() {
        return storeSkuSync2Oa;
    }

    public void setStoreSkuSync2Oa(String storeSkuSync2Oa) {
        this.storeSkuSync2Oa = storeSkuSync2Oa;
    }

    public String getOnecategorySync2Burgeon() {
        return onecategorySync2Burgeon;
    }

    public void setOnecategorySync2Burgeon(String onecategorySync2Burgeon) {
        this.onecategorySync2Burgeon = onecategorySync2Burgeon;
    }

    public String getSecondcategorySync2Burgeon() {
        return secondcategorySync2Burgeon;
    }

    public void setSecondcategorySync2Burgeon(String secondcategorySync2Burgeon) {
        this.secondcategorySync2Burgeon = secondcategorySync2Burgeon;
    }

    public String getThirdcategorySync2Burgeon() {
        return thirdcategorySync2Burgeon;
    }

    public void setThirdcategorySync2Burgeon(String thirdcategorySync2Burgeon) {
        this.thirdcategorySync2Burgeon = thirdcategorySync2Burgeon;
    }

    public String getFourthcategorySync2Burgeon() {
        return fourthcategorySync2Burgeon;
    }

    public void setFourthcategorySync2Burgeon(String fourthcategorySync2Burgeon) {
        this.fourthcategorySync2Burgeon = fourthcategorySync2Burgeon;
    }

    public String getDelOus() {
        return delOus;
    }

    public void setDelOus(String delOus) {
        this.delOus = delOus;
    }

    public String getBurgeonMaterialContrain() {
        return burgeonMaterialContrain;
    }

    public void setBurgeonMaterialContrain(String burgeonMaterialContrain) {
        this.burgeonMaterialContrain = burgeonMaterialContrain;
    }

    public String getProductLineSync2Burgeon() {
        return productLineSync2Burgeon;
    }

    public void setProductLineSync2Burgeon(String productLineSync2Burgeon) {
        this.productLineSync2Burgeon = productLineSync2Burgeon;
    }

    public String getSupplierSync2Burgeon() {
        return supplierSync2Burgeon;
    }

    public void setSupplierSync2Burgeon(String supplierSync2Burgeon) {
        this.supplierSync2Burgeon = supplierSync2Burgeon;
    }

    public String getSupplierSync2WMS() {
        return supplierSync2WMS;
    }

    public void setSupplierSync2WMS(String supplierSync2WMS) {
        this.supplierSync2WMS = supplierSync2WMS;
    }

    public String getSupplierSync2RFID() {
        return supplierSync2RFID;
    }

    public void setSupplierSync2RFID(String supplierSync2RFID) {
        this.supplierSync2RFID = supplierSync2RFID;
    }

    public String getStoreRFID() {
        return storeRFID;
    }

    public void setStoreRFID(String storeRFID) {
        this.storeRFID = storeRFID;
    }

    public String getBuyerBurgeon() {
        return buyerBurgeon;
    }

    public void setBuyerBurgeon(String buyerBurgeon) {
        this.buyerBurgeon = buyerBurgeon;
    }

    public String getCostcenterSync() {
        return costcenterSync;
    }

    public void setCostcenterSync(String costcenterSync) {
        this.costcenterSync = costcenterSync;
    }

    public String getDepartmentMatrixSync2OA() {
        return departmentMatrixSync2OA;
    }

    public void setDepartmentMatrixSync2OA(String departmentMatrixSync2OA) {
        this.departmentMatrixSync2OA = departmentMatrixSync2OA;
    }

    public String getZoneSync2Yxt() {
        return zoneSync2Yxt;
    }

    public void setZoneSync2Yxt(String zoneSync2Yxt) {
        this.zoneSync2Yxt = zoneSync2Yxt;
    }

    public String getStaffSync2CH() {
        return staffSync2CH;
    }

    public String getCurrencySync2Burgeon() {
        return currencySync2Burgeon;
    }

    public String getDepartmentSync2OA() {
        return departmentSync2OA;
    }

    public String getDepartmentSync2CH() {
        return departmentSync2CH;
    }

    public void setDepartmentSync2CH(String departmentSync2CH) {
        this.departmentSync2CH = departmentSync2CH;
    }

    public void setDepartmentSync2OA(String departmentSync2OA) {
        this.departmentSync2OA = departmentSync2OA;
    }

    public String getExchangeRateSync2Burgeon() {
        return exchangeRateSync2Burgeon;
    }

    public void setExchangeRateSync2Burgeon(String exchangeRateSync2Burgeon) {
        this.exchangeRateSync2Burgeon = exchangeRateSync2Burgeon;
    }

    public void setCurrencySync2Burgeon(String currencySync2Burgeon) {
        this.currencySync2Burgeon = currencySync2Burgeon;
    }


    public String getStoreBuygeon() {
        return storeBuygeon;
    }

    public void setStoreBuygeon(String storeBuygeon) {
        this.storeBuygeon = storeBuygeon;
    }

    public void setStaffSync2CH(String staffSync2CH) {
        this.staffSync2CH = staffSync2CH;
    }

    public String getStaffSync2OAIsm() {
        return staffSync2OAIsm;
    }

    public void setStaffSync2OAIsm(String staffSync2OAIsm) {
        this.staffSync2OAIsm = staffSync2OAIsm;
    }

    public String getSyncSku() {
        return syncSku;
    }

    public void setSyncSku(String syncSku) {
        this.syncSku = syncSku;
    }

    public String getSyncProduct2Buygeon() {
        return syncProduct2Buygeon;
    }

    public void setSyncProduct2Buygeon(String syncProduct2Buygeon) {
        this.syncProduct2Buygeon = syncProduct2Buygeon;
    }

    public String getSyncSize2Burgeon() {
        return syncSize2Burgeon;
    }

    public void setSyncSize2Burgeon(String syncSize2Burgeon) {
        this.syncSize2Burgeon = syncSize2Burgeon;
    }

    public String getSyncSizeGroup2Burgeon() {
        return syncSizeGroup2Burgeon;
    }
    public void setSyncSizeGroup2Burgeon(String syncSizeGroup2Burgeon) {
        this.syncSizeGroup2Burgeon = syncSizeGroup2Burgeon;
    }

    public String getStoreAddrSync2Burgeon() {
        return storeAddrSync2Burgeon;
    }

    public void setStoreAddrSync2Burgeon(String storeAddrSync2Burgeon) {
        this.storeAddrSync2Burgeon = storeAddrSync2Burgeon;
    }

    public String getPropertySyncBurgeon() {
        return propertySyncBurgeon;
    }

    public void setPropertySyncBurgeon(String propertySyncBurgeon) {
        this.propertySyncBurgeon = propertySyncBurgeon;
    }

    public String getBuyerWms() {
        return buyerWms;
    }

    public void setBuyerWms(String buyerWms) {
        this.buyerWms = buyerWms;
    }

    public String getCitySyncBurgeon() {
        return citySyncBurgeon;
    }

    public void setCitySyncBurgeon(String citySyncBurgeon) {
        this.citySyncBurgeon = citySyncBurgeon;
    }


    public String getBuyerRFID() {
        return buyerRFID;
    }

    public void setBuyerRFID(String buyerRFID) {
        this.buyerRFID = buyerRFID;
    }

    public String getPostSync2OA() {
        return postSync2OA;
    }

    public String getMproxy() {
        return mproxy;
    }

    public void setMproxy(String mproxy) {
        this.mproxy = mproxy;
    }

    public void setPostSync2OA(String postSync2OA) {
        this.postSync2OA = postSync2OA;
    }

    public String getProductUpdateService() {
        return productUpdateService;
    }

    public void setProductUpdateService(String productUpdateService) {
        this.productUpdateService = productUpdateService;
    }

    public String getProductUpgradeService() {
        return productUpgradeService;
    }

    public void setProductUpgradeService(String productUpgradeService) {
        this.productUpgradeService = productUpgradeService;
    }

    public String getBuyerItransfer() {
        return buyerItransfer;
    }

    public void setBuyerItransfer(String buyerItransfer) {
        this.buyerItransfer = buyerItransfer;
    }

    public String getBuyerEmax() {

        return buyerEmax;
    }

    public void setBuyerEmax(String buyerEmax) {
        this.buyerEmax = buyerEmax;
    }

    public String getStoreEmax() {
        return storeEmax;
    }

    public void setStoreEmax(String storeEmax) {
        this.storeEmax = storeEmax;
    }

    public String getStoreItransfer() {
        return storeItransfer;
    }

    public void setStoreItransfer(String storeItransfer) {
        this.storeItransfer = storeItransfer;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMaterialUpdateService() {
        return materialUpdateService;
    }

    public void setMaterialUpdateService(String materialUpdateService) {
        this.materialUpdateService = materialUpdateService;
    }

    public String getSyncZone() {
        return syncZone;
    }

    public void setSyncZone(String syncZone) {
        this.syncZone = syncZone;
    }


    public String getAreaSyncEmax() {
        return areaSyncEmax;
    }

    public void setAreaSyncEmax(String areaSyncEmax) {
        this.areaSyncEmax = areaSyncEmax;
    }

    public String getCitySyncEmax() {
        return citySyncEmax;
    }

    public void setCitySyncEmax(String citySyncEmax) {
        this.citySyncEmax = citySyncEmax;
    }

    public String getStoreTypeSyncEmax() {
        return storeTypeSyncEmax;
    }

    public void setStoreTypeSyncEmax(String storeTypeSyncEmax) {
        this.storeTypeSyncEmax = storeTypeSyncEmax;
    }

    public String getFabricUpgradeService() {
        return fabricUpgradeService;
    }

    public void setFabricUpgradeService(String fabricUpgradeService) {
        this.fabricUpgradeService = fabricUpgradeService;
    }
}
