package com.icicle.masterdb.config;

import com.icicle.masterdb.util.StringUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author liumingming
 * @version 2017-12-19 11:29.
 */
@Component
@ConfigurationProperties(prefix = "icicle")
public class IcicleConfig {
    /**
     * 当前系统ID
     */
    private Integer systemId;
    /**
     * 各类服务路径
     */
    private Map<String, String> services;
    /**
     * cookie相关配置
     */
    private Map<String, String> cookie;

    /**
     * 小钛店铺同步地址
     */
    private String xtstoreurl;

    /**
     * 小钛经销商同步地址
     */
    private String xtbuyerurl;


    public String getXtstoreurl() {
        return xtstoreurl;
    }

    public void setXtstoreurl(String xtstoreurl) {
        this.xtstoreurl = xtstoreurl;
    }

    public String getXtbuyerurl() {
        return xtbuyerurl;
    }

    public void setXtbuyerurl(String xtbuyerurl) {
        this.xtbuyerurl = xtbuyerurl;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Map<String, String> getServices() {
        return services;
    }

    public void setServices(Map<String, String> services) {
        this.services = services;
    }

    public Map<String, String> getCookie() {
        return cookie;
    }

    public void setCookie(Map<String, String> cookie) {
        this.cookie = cookie;
    }

    public String getSsoHost() {
        return services.get("sso");
    }

    public String getPermissionHost() {
        return services.get("permission");
    }

    public String getCookieName() {
        return cookie.get("name");
    }

    public String getCookieDomain() {
        return cookie.get("domain");
    }

    public String getCookiePath() {
        return cookie.get("path");
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
