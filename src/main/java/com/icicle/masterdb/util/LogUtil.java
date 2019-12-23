package com.icicle.masterdb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 *
 * @author CodeGeneratorUtil
 */
public class LogUtil {


    /**
     * 获取logger
     *
     * @param clazz 当前类
     * @return
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}