package com.icicle.masterdb.service;

import java.util.Map;

/**
 * @author wangyuling
 * @version 2018/8/8 9:20
 */
public interface ProductColumnService {
    /**
     * 根据提供的选项列自动生成excel头
     * @param column
     * @return
     */
    String matchTitle(String column, Map<String, String> columns);

    /**
     * 存储所有产品导出信息的列
     * @return
     */
    Map<String, String>  inserProductColum();
}
