package com.icicle.masterdb.core;

/**
 * @author liumingming
 * 响应结果生成工具
 */
public class ResultGenerator {
    /**
     * 默认成功返回message
     */
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    private static final String DEFAULT_FAIl_MESSAGE = "FAIl";

    /**
     * 生成无返回值的成功结果
     * @return
     */
    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * 生成无返回值的成功结果(带消息)
     * @param message
     * @return
     */
    public static Result genSuccessResult(String message) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(message);
    }

    /**
     * 生成带返回值的成功结果
     * @param data  返回数据
     * @return
     */
    public static Result genSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    /**
     * 生成失败结果
     * @param message   失败信息
     * @return
     */
    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    /**
     * 生成失败结果-object
     * @param data   失败信息
     * @return
     */
    public static Result genFailResult(Object data) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(DEFAULT_FAIl_MESSAGE)
                .setData(data);
    }
}