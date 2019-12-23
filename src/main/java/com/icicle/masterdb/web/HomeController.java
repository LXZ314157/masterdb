package com.icicle.masterdb.web;

import com.alibaba.fastjson.JSON;
import com.icicle.masterdb.config.IcicleConfig;
import com.icicle.masterdb.core.ProjectConstant;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.pojo.bo.LocalUserEntity;
import com.icicle.masterdb.pojo.bo.SsoCheckEntity;
import com.icicle.masterdb.util.HttpRequestUtil;
import com.icicle.masterdb.util.LanguageUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author liumingming
 * @version 2017-12-20 19:03.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Resource
    private IcicleConfig icicleConfig;
    /**
     * 当前激活的配置文件
     */
    @Value("${spring.profiles.active}")
    private String env;

    @GetMapping(value = "/msg")
    public String msg(Map<String, Object> map) {
        return "home/msg";
    }

    @GetMapping(value = "/nopermission")
    public String noPermission(Map<String, Object> map) {
        map.put("message", LanguageUtil.getText(401));
        return "home/msg";
    }

    @GetMapping(value = "/checklogin")
    public String checkLogin(@RequestParam("token") String token,
                             @RequestParam("sid") String sId,
                             @RequestParam(value = "lang", defaultValue = "zhs") String language,
                             HttpServletRequest request,
                             Map<String, Object> map) {
        SessionManager.clearLocalUserEntity();
        SessionManager.setLanguage(language);
        if (StringUtils.isBlank(token) || StringUtils.isBlank(sId)) {
            map.put("message", LanguageUtil.getText(1));
            return msg(map);
        }
        LocalUserEntity entity;
        if (ProjectConstant.DEBUG.equals(env)) {
            entity = new LocalUserEntity();
            entity.setUserId(2077);
            entity.setLoginName("mml");
            entity.setTrueName("mml");
        } else {
            String url = StringUtil.gsFormat("{0}/check", icicleConfig.getSsoHost());
            try {
                SsoCheckEntity checkEntity = new SsoCheckEntity(HttpRequestUtil.getIpAddress(request),
                        URLEncoder.encode(token, "UTF-8"), sId);
                entity = HttpRequestUtil.postRequest(url, JSON.toJSONString(checkEntity), LocalUserEntity.class);
            } catch (Exception ex) {
                LogUtil.getLogger(SessionManager.class).error(ex.getMessage(), ex);
                entity = null;
            }
        }
        if (null != entity && entity.getUserId().intValue() > 0) {
            entity.setSessionId(sId);
            SessionManager.setLocalUserEntity(entity, icicleConfig);
            return "redirect:/";
        } else {
            map.put("message", LanguageUtil.getText(2));
            return msg(map);
        }
    }
}
