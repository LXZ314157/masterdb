package com.icicle.masterdb.core;

/**
 * @author liumingming
 * @version 2017-12-19 16:49.
 */
public enum RequestMethod {
    /**
     * GET
     */
    GET(1),
    /**
     * POST
     */
    POST(2),
    /**
     * PUT
     */
    PUT(3),
    /**
     * DELETE
     */
    DELETE(4);

    public int code;

    RequestMethod(int code) {
        this.code = code;
    }
}
