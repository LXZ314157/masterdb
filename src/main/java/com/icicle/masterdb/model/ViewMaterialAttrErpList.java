package com.icicle.masterdb.model;

import javax.persistence.*;

@Table(name = "view_material_attr_erp_list")
public class ViewMaterialAttrErpList {
    @Id
    @Column(name = "material_code")
    private String materialCode;

    @Column(name = "RECEIVING_ROUTING")
    private String receivingRouting;

    @Column(name = "ACCEPTABLE_EARLY_DAYS")
    private String acceptableEarlyDays;

    @Column(name = "PROCESS_DETAILS")
    private String processDetails;

    @Column(name = "CYCLE_COUNT_ENABLED_FLAG")
    private String cycleCountEnabledFlag;

    @Column(name = "ALLOW_SUBSTITUTE_RECEIPTS")
    private String allowSubstituteReceipts;

    @Column(name = "SHRINKAGE_RATE")
    private String shrinkageRate;

    @Column(name = "FIXED_DAYS_SUPPLY")
    private String fixedDaysSupply;

    @Column(name = "APPROVED_VENDOR_FLAG")
    private String approvedVendorFlag;

    @Column(name = "LEAD_TIME_LOT_SIZE")
    private String leadTimeLotSize;

    @Column(name = "FIXED_ORDER_QTY")
    private String fixedOrderQty;

    @Column(name = "LIST_PRICE")
    private String listPrice;

    @Column(name = "PLANNER")
    private String planner;

    @Column(name = "ROUTINE_FLAG")
    private String routineFlag;

    /**
     * @return material_code
     */
    public String getMaterialCode() {
        return materialCode;
    }

    /**
     * @param materialCode
     */
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    /**
     * @return RECEIVING_ROUTING
     */
    public String getReceivingRouting() {
        return receivingRouting;
    }

    /**
     * @param receivingRouting
     */
    public void setReceivingRouting(String receivingRouting) {
        this.receivingRouting = receivingRouting;
    }

    /**
     * @return ACCEPTABLE_EARLY_DAYS
     */
    public String getAcceptableEarlyDays() {
        return acceptableEarlyDays;
    }

    /**
     * @param acceptableEarlyDays
     */
    public void setAcceptableEarlyDays(String acceptableEarlyDays) {
        this.acceptableEarlyDays = acceptableEarlyDays;
    }

    /**
     * @return PROCESS_DETAILS
     */
    public String getProcessDetails() {
        return processDetails;
    }

    /**
     * @param processDetails
     */
    public void setProcessDetails(String processDetails) {
        this.processDetails = processDetails;
    }

    /**
     * @return CYCLE_COUNT_ENABLED_FLAG
     */
    public String getCycleCountEnabledFlag() {
        return cycleCountEnabledFlag;
    }

    /**
     * @param cycleCountEnabledFlag
     */
    public void setCycleCountEnabledFlag(String cycleCountEnabledFlag) {
        this.cycleCountEnabledFlag = cycleCountEnabledFlag;
    }

    /**
     * @return ALLOW_SUBSTITUTE_RECEIPTS
     */
    public String getAllowSubstituteReceipts() {
        return allowSubstituteReceipts;
    }

    /**
     * @param allowSubstituteReceipts
     */
    public void setAllowSubstituteReceipts(String allowSubstituteReceipts) {
        this.allowSubstituteReceipts = allowSubstituteReceipts;
    }

    /**
     * @return SHRINKAGE_RATE
     */
    public String getShrinkageRate() {
        return shrinkageRate;
    }

    /**
     * @param shrinkageRate
     */
    public void setShrinkageRate(String shrinkageRate) {
        this.shrinkageRate = shrinkageRate;
    }

    /**
     * @return FIXED_DAYS_SUPPLY
     */
    public String getFixedDaysSupply() {
        return fixedDaysSupply;
    }

    /**
     * @param fixedDaysSupply
     */
    public void setFixedDaysSupply(String fixedDaysSupply) {
        this.fixedDaysSupply = fixedDaysSupply;
    }

    /**
     * @return APPROVED_VENDOR_FLAG
     */
    public String getApprovedVendorFlag() {
        return approvedVendorFlag;
    }

    /**
     * @param approvedVendorFlag
     */
    public void setApprovedVendorFlag(String approvedVendorFlag) {
        this.approvedVendorFlag = approvedVendorFlag;
    }

    /**
     * @return LEAD_TIME_LOT_SIZE
     */
    public String getLeadTimeLotSize() {
        return leadTimeLotSize;
    }

    /**
     * @param leadTimeLotSize
     */
    public void setLeadTimeLotSize(String leadTimeLotSize) {
        this.leadTimeLotSize = leadTimeLotSize;
    }

    /**
     * @return FIXED_ORDER_QTY
     */
    public String getFixedOrderQty() {
        return fixedOrderQty;
    }

    /**
     * @param fixedOrderQty
     */
    public void setFixedOrderQty(String fixedOrderQty) {
        this.fixedOrderQty = fixedOrderQty;
    }

    /**
     * @return LIST_PRICE
     */
    public String getListPrice() {
        return listPrice;
    }

    /**
     * @param listPrice
     */
    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
    }

    /**
     * @return PLANNER
     */
    public String getPlanner() {
        return planner;
    }

    /**
     * @param planner
     */
    public void setPlanner(String planner) {
        this.planner = planner;
    }

    /**
     * @return ROUTINE_FLAG
     */
    public String getRoutineFlag() {
        return routineFlag;
    }

    /**
     * @param routineFlag
     */
    public void setRoutineFlag(String routineFlag) {
        this.routineFlag = routineFlag;
    }
}