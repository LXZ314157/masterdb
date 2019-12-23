package com.icicle.masterdb.aop;

import com.alibaba.fastjson.JSON;
import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.config.IcicleConfig;
import com.icicle.masterdb.core.ProjectConstant;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.Oplog;
import com.icicle.masterdb.service.OplogService;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.HttpRequestUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 日志切面处理类
 *
 * @author liumingming
 * @version 2017-12-22 17:45.
 */
@Component
@Aspect
public class LogAspect {

    @Resource
    private IcicleConfig config;

    @Resource
    private OplogService oplogService;

    /**
     * 当前激活的配置文件
     */
    @Value("${spring.profiles.active}")
    private String env;

    /**
     * 通过注解声明切点
     *
     * @param logAction
     */
    @Pointcut(value = "@annotation(logAction)")
    public void annotationPointCut(LogAction logAction) {
    }

    @AfterReturning(value = "annotationPointCut(logAction)", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, LogAction logAction, Object returnValue) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        try {
            Integer ret;
            Class returnType = signature.getReturnType();
            if (Result.class.equals(returnType)) {
                ret = Integer.valueOf(((Result) returnValue).getCode());
            } else if (void.class.equals(returnType) || Void.class.equals(returnType)) {
                ret = Integer.valueOf(1);
            } else if (boolean.class.equals(returnType) || Boolean.class.equals(returnType)) {
                ret = ((Boolean) returnValue) == true ? Integer.valueOf(1) : Integer.valueOf(0);
            }else{
                ret = 1;
            }
            //记录完整方法名
            String methodName = StringUtil.gsFormat("{0}.{1}",
                    joinPoint.getTarget().getClass().getName(), signature.getName());
            String param = JSON.toJSONString(joinPoint.getArgs());
            Oplog log = new Oplog();
            log.setOptitle(logAction.logDesc());
            log.setOpmethod(methodName);
            log.setOpresult(ret);
            log.setDesc(param);
            log.setOpr(SessionManager.getLoginName());
            log.setOpip(HttpRequestUtil.getIpAddress(SessionManager.getRequest()));
            log.setOpdate(DateUtil.now());
            log.setSysid(config.getSystemId());
            LogUtil.getLogger(LogAspect.class).info(log.toString());

            //开发模式下不写日志了
            if (!ProjectConstant.DEBUG.equals(env)) {
                oplogService.writeLOg(log);
            }
        } catch (Exception ex) {
            LogUtil.getLogger(LogAspect.class).error(ex.getMessage(), ex);
        }
    }
}