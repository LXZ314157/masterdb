package com.icicle.masterdb.web;

import com.icicle.masterdb.util.LanguageUtil;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author liumingming
 * @version 2017-12-25 16:48.
 */
@Controller
@RequestMapping("/error")
public class BaseErrorController implements ErrorController {

    private int page404 = 404;

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping
    public String error(Map<String, Object> map, HttpServletRequest request) {
        Integer httpStatus = getStatus(request);
        if (httpStatus.intValue() == page404) {
            map.put("message", LanguageUtil.getText(404));
        } else {
            map.put("message", LanguageUtil.getText(500));
        }
        return "home/msg";
    }

    protected Integer getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        return statusCode;
    }
}
