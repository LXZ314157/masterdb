package com.icicle.masterdb.aop.annotation;

import java.lang.annotation.*;

/**
 * @author liumingming
 * @version 2017-12-22 17:41.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAction {
    String logDesc();
}