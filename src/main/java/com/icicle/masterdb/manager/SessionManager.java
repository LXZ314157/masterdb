package com.icicle.masterdb.manager;

import com.alibaba.fastjson.JSON;
import com.icicle.masterdb.config.IcicleConfig;
import com.icicle.masterdb.pojo.bo.LocalUserEntity;
import com.icicle.masterdb.pojo.bo.Role;
import com.icicle.masterdb.pojo.bo.UserPermission;
import com.icicle.masterdb.util.HttpRequestUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.StringUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * session管理类
 *
 * @author liumingming
 * @version 2017-12-14 16:47.
 */
public class SessionManager {

    /**
     * session key
     */
    private static final String LONINENTITYKEY = "LOGONENTITY";
    /**
     * 当前使用语言
     */
    private static String currentLanguage = "zhs";

    /**
     * 获取当前使用语言
     *
     * @return
     */
    public static String getLanguage() {
        return currentLanguage;
    }

    /**
     * 设置当前使用语言
     *
     * @param language 语言
     */
    public static void setLanguage(String language) {
        currentLanguage = language;
    }

    /**
     * 拿当前请求的request对象
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    /**
     * 获取当前登录的用户实体
     *
     * @return
     */
    public static LocalUserEntity getLocalUserEntity() {
        HttpSession session = getSession();
        if (null == session) {
            return null;
        }
        Object sessionObj;
        try {
            sessionObj = session.getAttribute(LONINENTITYKEY);
        } catch (Exception ex) {
            sessionObj = null;
            LogUtil.getLogger(SessionManager.class).error(ex.getMessage(), ex);
        }
        if (null != sessionObj) {
            LocalUserEntity entity = JSON.parseObject(sessionObj.toString(), LocalUserEntity.class);
            return entity;
        }
        return null;
    }

    /**
     * 根据sid去服务器上验证当前的登录用户
     *
     * @param config 配置信息
     * @param sid    sid
     * @return
     */
    public static LocalUserEntity getUserEntity(IcicleConfig config, String sid) {
        String url = StringUtil.gsFormat("{0}/getuserentity/{1}", config.getSsoHost(), sid);
        return HttpRequestUtil.getRequest(url, LocalUserEntity.class);
    }

    /**
     * 获取当前登录的用户名
     *
     * @return
     */
    public static String getLoginName() {
        LocalUserEntity userEntity = getLocalUserEntity();
        if (null != userEntity) {
            return userEntity.getLoginName();
        }
        return "";
    }

    /**
     * 从cookie中获取sid
     *
     * @param config 配置信息
     * @return
     */
    public static String getCurrentSessionId(IcicleConfig config) {
        try {
            Cookie ssoCookie = getCookie(config);
            if (ssoCookie != null) {
                return ssoCookie.getValue();
            } else {
                return "";
            }
        } catch (Exception ex) {
            LogUtil.getLogger(SessionManager.class).error(ex.getMessage(), ex);
            return "";
        }
    }

    /**
     * 将登录用户数据写入session(存储在redis)
     *
     * @param entity 登录用户实体
     * @param config 配置文件
     */
    public static void setLocalUserEntity(LocalUserEntity entity, IcicleConfig config) {
        if (null == entity) {
            return;
        }

        //登录成功写cookie
        writerSSOCookie(config, entity.getSessionId());

        HttpSession session = getSession();
        if (null != session && null == getLocalUserEntity()) {
            String systemId = config.getSystemId().toString();
            //去服务器上获取登录用户的角色信息
            String roleUrl = StringUtil.gsFormat("{0}/roles/{1}/{2}", config.getPermissionHost(),
                    entity.getLoginName(), systemId);
            List<Role> roleList = HttpRequestUtil.getRequest2List(roleUrl, Role.class);
            entity.setRolelist(roleList);
            //去服务器上获取登录用户的权限信息
            String permissionUrl = StringUtil.gsFormat("{0}/permission/{1}/{2}", config.getPermissionHost(),
                    entity.getUserId().toString(), systemId);
            List<UserPermission> permissionList = HttpRequestUtil.getRequest2List(permissionUrl, UserPermission.class);
            entity.setPermissionList(permissionList);
            session.setAttribute(LONINENTITYKEY, JSON.toJSONString(entity));
        }
    }

    /**
     * session中清除登录信息
     */
    public static void clearLocalUserEntity() {
        HttpSession session = getSession();
        if (null != session) {
            session.removeAttribute(LONINENTITYKEY);
        }
    }

    /**
     * 获取当前请求的responce对象
     *
     * @return
     */
    private static HttpServletResponse getResponse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getResponse();
    }

    /**
     * 获取当前请求的session对象
     *
     * @return
     */
    private static HttpSession getSession() {
        HttpSession session;
        try {
            session = getRequest().getSession();
        } catch (Exception ex) {
            session = null;
            LogUtil.getLogger(SessionManager.class).error(ex.getMessage(), ex);
        }
        return session;
    }

    /**
     * 获取当前请求的全部cookie
     *
     * @return
     */
    private static Cookie[] getCookie() {
        Cookie[] cookies;
        try {
            cookies = getRequest().getCookies();
        } catch (Exception ex) {
            cookies = null;
            LogUtil.getLogger(SessionManager.class).error(ex.getMessage(), ex);
        }
        return cookies;
    }

    /**
     * 根据cookie名称获取cookie
     *
     * @param cookieName cookie名称
     * @return
     */
    private static Cookie getCookie(String cookieName) {
        Cookie[] cookies = getCookie();
        if (null == cookies) {
            return null;
        } else {
            for (Cookie cookie : cookies) {
                if (Objects.equals(cookieName, cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 根据配置文件获取cookie
     *
     * @param icicleConfig
     * @return
     */
    private static Cookie getCookie(IcicleConfig icicleConfig) {
        return getCookie(icicleConfig.getCookieName());
    }

    /**
     * 写cookie
     *
     * @param config 配置文件
     * @param sid    sid
     */
    private static void writerSSOCookie(IcicleConfig config, String sid) {
        writerSSOCookie(config.getCookieName(), config.getCookieDomain(), config.getCookiePath(), sid);
    }

    /**
     * 写cookie
     *
     * @param cookieName cookie名称
     * @param domain     cookie域名
     * @param path       cookie路径
     * @param sid        sid
     */
    private static void writerSSOCookie(String cookieName, String domain, String path, String sid) {
        Cookie cookie = getCookie(cookieName);
        if (null == cookie) {
            cookie = new Cookie(cookieName, sid);
        }
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setValue(sid);
        addCookie(cookie);
    }

    /**
     * cookie写入responce
     *
     * @param cookie
     */
    private static void addCookie(Cookie cookie) {
        if (null == cookie) {
            return;
        }
        HttpServletResponse response = getResponse();
        if (null != response) {
            response.addCookie(cookie);
        }
    }
}