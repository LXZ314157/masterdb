package com.icicle.masterdb.core;

/**
 * 这里其实不算异常，只是为了方便统一提示
 *
 * @author liumingming
 * @version 2017-11-10 15:43.
 */
public class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataNotFoundException() {
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
