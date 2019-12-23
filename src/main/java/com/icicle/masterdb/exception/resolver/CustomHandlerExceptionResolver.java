package com.icicle.masterdb.exception.resolver;

import com.icicle.masterdb.core.DataNotFoundException;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.core.ResultCode;
import com.icicle.masterdb.core.ServiceException;
import com.icicle.masterdb.util.HttpRequestUtil;
import com.icicle.masterdb.util.LogUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用异常处理
 *
 * @author liumingming
 * @version 2017-12-19 15:47.
 */
@Component
public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        Result result = new Result();
        String uri = request.getRequestURI();
        String errMsg = ex.getMessage();
        //业务失败的异常，如“账号或密码错误”
        if (ex instanceof ServiceException) {
            result.setCode(ResultCode.FAIL).setMessage(errMsg);
        } else if (ex instanceof DataNotFoundException) {
            result.setCode(ResultCode.DATA_NOT_FOUND).setMessage("未能获取到数据");
        } else if (ex instanceof NoHandlerFoundException) {
            String msg = String.format("接口 [%s] 不存在", uri);
            result.setCode(ResultCode.NOT_FOUND).setMessage(msg);

            LogUtil.getLogger(CustomHandlerExceptionResolver.class).warn(msg);
        } else if (ex instanceof ServletException) {
            result.setCode(ResultCode.FAIL).setMessage(errMsg);

            LogUtil.getLogger(CustomHandlerExceptionResolver.class).error(errMsg, ex);
        } else {
            String msg = String.format("接口 [%s] 内部错误，请联系管理员", uri);
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage(msg);
            String message;
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                        uri,
                        handlerMethod.getBean().getClass().getName(),
                        handlerMethod.getMethod().getName(),
                        errMsg);
            } else {
                message = errMsg;
            }

            LogUtil.getLogger(CustomHandlerExceptionResolver.class).error(message, ex);
        }
        HttpRequestUtil.responseResult(response, result);
        return new ModelAndView();
    }
}
