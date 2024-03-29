package com.icicle.masterdb.core;

/**
 * @author  liumingming
 * 项目常量
 */
public final class ProjectConstant {
    /**
     * 项目基础包名称，根据自己公司的项目修改
     */
    public static final String BASE_PACKAGE = "com.icicle.masterdb";
    /**
     * Model所在包
     */
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".model";
    /**
     * Mapper所在包
     */
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao.masterdb";
    /**
     * Service所在包
     */
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";
    /**
     * ServiceImpl所在包
     */
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";
    /**
     * Controller所在包
     */
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".web";
    /**
     * Mapper插件基础接口的完全限定名
     */
    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.MyMapper";
    /**
     * 未知异常编码
     */

    public static final int UNKNOWN_EXCEPTION_CODE = -999;

    /**
     * 开发模式
     */
    public static final String DEBUG = "dev";

}