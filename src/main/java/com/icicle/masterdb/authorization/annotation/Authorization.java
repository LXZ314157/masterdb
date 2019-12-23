package com.icicle.masterdb.authorization.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在Controller或Controller的方法上使用此注解，该方法在映射时会检查用户是否登录已经有操作权限，未授权会被跳转到未登录界面
 *
 * @author liumingming
 * @version 2017-12-19 15:26.
 * @see com.icicle.masterdb.authorization.interceptor.AuthorizationInterceptor
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {
}
