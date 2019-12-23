package com.icicle.masterdb.pojo.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author lvxuezhan
 * @version 2019-02-25 15:41
 */
public class ManageCenterVO {

    private Integer managerCenterId;

    private String managerCenterName;

    private String staffNum;

    private String managerName;

    private Integer manageCenterState;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getManagerCenterId() {
        return managerCenterId;
    }

    public void setManagerCenterId(Integer managerCenterId) {
        this.managerCenterId = managerCenterId;
    }

    public String getManagerCenterName() {
        return managerCenterName;
    }

    public void setManagerCenterName(String managerCenterName) {
        this.managerCenterName = managerCenterName;
    }

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Integer getManageCenterState() {
        return manageCenterState;
    }

    public void setManageCenterState(Integer manageCenterState) {
        this.manageCenterState = manageCenterState;
    }
}
