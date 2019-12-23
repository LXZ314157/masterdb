package com.icicle.masterdb.pojo.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.icicle.masterdb.util.StringUtil;

import java.util.List;

/**
 * @author liumingming
 * @version 2017-12-14 16:35.
 */
public class LocalUserEntity {
    @JSONField(name = "UserGuid")
    private String userGuid;

    @JSONField(name = "UserId")
    private Integer userId;

    @JSONField(name = "Loginname")
    private String loginName;

    @JSONField(name = "Pwd")
    private String pwd;

    private String ip;

    @JSONField(name = "UserType")
    private Integer userType;

    @JSONField(name = "Truename")
    private String trueName;

    @JSONField(name = "SessionId")
    private String sessionId;

    @JSONField(name = "FirstLogin")
    private Boolean firstLogin;

    private List<Role> rolelist;

    private List<UserPermission> permissionList;

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public List<Role> getRolelist() {
        return rolelist;
    }

    public void setRolelist(List<Role> rolelist) {
        this.rolelist = rolelist;
    }

    public List<UserPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<UserPermission> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
