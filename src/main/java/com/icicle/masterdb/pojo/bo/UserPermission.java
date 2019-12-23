package com.icicle.masterdb.pojo.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.icicle.masterdb.util.StringUtil;

/**
 * @author liumingming
 * @version 2017-12-19 10:44.
 */
public class UserPermission {
    @JSONField(name = "UserID")
    public Integer userId;

    @JSONField(name = "Funid")
    public Integer funId;

    @JSONField(name = "Funname")
    public String funName;

    @JSONField(name = "FunIdentify")
    public String funIdentify;

    @JSONField(name = "FunIsAction")
    public Boolean funIsAction;

    @JSONField(name = "Funtype")
    public Integer funType;

    @JSONField(name = "FunState")
    public Integer funState;

    @JSONField(name = "ModuleId")
    public Integer moduleId;

    @JSONField(name = "Modulename")
    public String moduleName;

    @JSONField(name = "ModulePath")
    public String modulePath;

    @JSONField(name = "ModuleState")
    public Integer moduleState;

    @JSONField(name = "SystemId")
    public Integer systemId;

    @JSONField(name = "ModuleRank")
    public Integer moduleRank;

    @JSONField(name = "FunRank")
    public Integer funRank;

    @JSONField(name = "SystemName")
    public String systemName;

    @JSONField(name = "SystemUrl")
    public String systemUrl;

    @JSONField(name = "SystemState")
    public Integer systemState;

    @JSONField(name = "Funicon")
    public String funIcon;

    @JSONField(name = "Moduleicon")
    public String moduleIcon;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFunId() {
        return funId;
    }

    public void setFunId(Integer funId) {
        this.funId = funId;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public String getFunIdentify() {
        return funIdentify;
    }

    public void setFunIdentify(String funIdentify) {
        this.funIdentify = funIdentify;
    }

    public Boolean getFunIsAction() {
        return funIsAction;
    }

    public void setFunIsAction(Boolean funIsAction) {
        this.funIsAction = funIsAction;
    }

    public Integer getFunType() {
        return funType;
    }

    public void setFunType(Integer funType) {
        this.funType = funType;
    }

    public Integer getFunState() {
        return funState;
    }

    public void setFunState(Integer funState) {
        this.funState = funState;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModulePath() {
        return modulePath;
    }

    public void setModulePath(String modulePath) {
        this.modulePath = modulePath;
    }

    public Integer getModuleState() {
        return moduleState;
    }

    public void setModuleState(Integer moduleState) {
        this.moduleState = moduleState;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Integer getModuleRank() {
        return moduleRank;
    }

    public void setModuleRank(Integer moduleRank) {
        this.moduleRank = moduleRank;
    }

    public Integer getFunRank() {
        return funRank;
    }

    public void setFunRank(Integer funRank) {
        this.funRank = funRank;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemUrl() {
        return systemUrl;
    }

    public void setSystemUrl(String systemUrl) {
        this.systemUrl = systemUrl;
    }

    public Integer getSystemState() {
        return systemState;
    }

    public void setSystemState(Integer systemState) {
        this.systemState = systemState;
    }

    public String getFunIcon() {
        return funIcon;
    }

    public void setFunIcon(String funIcon) {
        this.funIcon = funIcon;
    }

    public String getModuleIcon() {
        return moduleIcon;
    }

    public void setModuleIcon(String moduleIcon) {
        this.moduleIcon = moduleIcon;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
