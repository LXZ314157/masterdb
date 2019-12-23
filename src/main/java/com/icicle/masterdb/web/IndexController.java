package com.icicle.masterdb.web;

import com.icicle.masterdb.authorization.annotation.Authorization;
import com.icicle.masterdb.core.AbstractPageController;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.pojo.bo.LocalUserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author liumingming
 */
@Controller
@RequestMapping("/")
@Authorization
public class IndexController extends AbstractPageController {

    public IndexController() {
        super("home");
    }

    @GetMapping
    public String index(Map<String, Object> map, HttpServletRequest request) {
        LocalUserEntity entity = SessionManager.getLocalUserEntity();
        if (null == entity) {
            return "redirect:/checklogin?token=token&sid=sid";
        } else {
            return this.freeMarkerViewResult("index", map, request);
        }
    }
}