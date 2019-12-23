package com.icicle.masterdb.authorization.interceptor;

import com.alibaba.fastjson.JSON;
import com.icicle.masterdb.authorization.annotation.Authorization;
import com.icicle.masterdb.config.IcicleConfig;
import com.icicle.masterdb.core.*;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.pojo.bo.LocalUserEntity;
import com.icicle.masterdb.pojo.bo.UserPermission;
import com.icicle.masterdb.util.HttpRequestUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 自定义拦截器，判断此次请求是否有权限
 *
 * @author liumingming
 * @version 2017-12-19 15:27.
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    /**
     * 当前激活的配置文件
     */
    @Value("${spring.profiles.active}")
    private String env;

    @Resource
    private IcicleConfig icicleConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //开发模式直接通过
        if (ProjectConstant.DEBUG.equals(env)) {
            return true;
        }
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (handlerMethod.getBeanType().getAnnotation(Authorization.class) != null ||
                method.getAnnotation(Authorization.class) != null) {
            String controller = handlerMethod.getBeanType().getSimpleName()
                    .toLowerCase().replace("controller", "");
            String action = method.getName().toLowerCase();
            int requestMethod = getRequestMethod(request.getMethod().toUpperCase());

            LocalUserEntity user = SessionManager.getLocalUserEntity();
            //当session失效时，通过cookie中记录的sid去服务器上验证登录信息
            if (null == user) {
                String sid = SessionManager.getCurrentSessionId(icicleConfig);
                if (StringUtils.isBlank(sid)) {
                    doResponse(method, request, response);
                    return false;
                }

                user = SessionManager.getUserEntity(icicleConfig, sid);
                if (null == user || user.getUserId().intValue() <= 0) {
                    doResponse(method, request, response);
                    return false;
                }

                SessionManager.setLocalUserEntity(user, icicleConfig);
            }

            //权限校验
            List<UserPermission> permissionList = user.getPermissionList();
            if (null == permissionList || permissionList.size() == 0) {
                doResponse(method, request, response);
                return false;
            }

            int[] hit = {0};
            permissionList.stream()
                    .filter(p -> Objects.equals(controller, p.getModulePath().toLowerCase())
                            && Objects.equals(action, p.getFunIdentify().toLowerCase())
                            && Objects.equals(requestMethod, p.getFunType().intValue()))
                    .findFirst()
                    .ifPresent(h -> hit[0] = 1);
            if (hit[0] == 0) {
                doResponse(method, request, response);
                return false;
            }
        }

        return true;
    }

    /**
     * 获取请求类型
     *
     * @param method
     * @return
     */
    private int getRequestMethod(String method) {
        if (RequestMethod.GET.toString().equals(method)) {
            return RequestMethod.GET.code;
        }

        if (RequestMethod.POST.toString().equals(method)) {
            return RequestMethod.POST.code;
        }

        if (RequestMethod.PUT.toString().equals(method)) {
            return RequestMethod.PUT.code;
        }

        return RequestMethod.DELETE.code;
    }

    /**
     * 根据实际返回类型返回无权限时的数据
     *
     * @param method
     * @param request
     * @param response
     * @throws Exception
     */
    private void doResponse(Method method, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Class<?> returnType = method.getReturnType();
        //AJAX时返回json
        if (Result.class.equals(returnType)) {
            Result result = new Result();
            result.setCode(ResultCode.UNAUTHORIZED);

            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            response.setStatus(200);
            try {
                response.getWriter().write(JSON.toJSONString(result));
            } catch (IOException ex) {
                LogUtil.getLogger(HttpRequestUtil.class).error(ex.getMessage(), ex);
            }
            return;
        }
        //datatable时构造一个空datatable
        if (DataTableRecord.class.equals(returnType)) {
            DataTableRecord dataTableRecord = new DataTableRecord("", 0, 0,
                    new ArrayList<>());
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            response.setStatus(200);
            try {
                response.getWriter().write(JSON.toJSONString(dataTableRecord));
            } catch (IOException ex) {
                LogUtil.getLogger(HttpRequestUtil.class).error(ex.getMessage(), ex);
            }
            return;
        }
        //默认返回无权限的错误页
        response.sendRedirect(StringUtil.gsFormat("{0}/nopermission", request.getContextPath()));
    }
}