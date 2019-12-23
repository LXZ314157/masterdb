package com.icicle.masterdb.service.constant;

/**
 * 在service层和控制层中使用的各种常量
 * <p>
 * Created by liurenhua on 2017/12/13.
 *
 * @author liurenhua
 */
public class ServiceConstant {

    /**
     * 同步到emax时传过来的数据
     */
    public static final String EMAX = "emax";
    /**
     * 同步到rfid时传过来的数据
     */
    public static final String RFID = "rfid";
    /**
     * 同步到itransfer时客户端传过来的字符串
     */
    public static final String ITRANSFER = "itransfer";

    /**
     * 同步到yxt时客户端传过来的字符串
     */
    public static final String YXT = "yxt";

    /**
     * 同步到小钛时客户端传过来的字符串
     */
    public static final String XT = "xt";

    /**
     * 同步到wms时客户端传过来的字符串
     */
    public static final String WMS = "wms";

    /**
     * 同步到新伯俊时客户端传过来的字符串
     */
    public static final String BURGEON = "burgeon";

    /**
     * 表示升序排列的字符串
     */
    public static final String ASC = "asc";
    /**
     * 表示降序排列的字符串
     */
    public static final String DESC = "desc";
    /**
     * 数据传输错误
     */
    public static final int DATA_TRANS_ERROR = -2;
    /**
     * ID重复
     */
    public static final int ID_REAPT = -3;
    /**
     * 限制选项的最大长度
     */
    public static final int VALID_LENGTH = 12;

}
