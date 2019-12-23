package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ViewProductmessageinfo;

/**
 * @author CodeGeneratorUtil
 */
public interface ViewProductmessageinfoService extends Service<ViewProductmessageinfo> {
    /**
     *产品列表的获取
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param batchProductCode
     * @param productCodeOrName
     * @param startTime
     * @param endTime
     * @param syncStatus
     * @param endTime
     * @param sortCol 排序列
     * @param sortDir 排序规则
     * @param type  同步状态条件问题（1||0）
     * @return
     */
    DataTableRecord listProduct(String sEcho, Integer pageIndex, Integer pageSize,String batchProductCode, String productCodeOrName,String startTime,
                                String endTime,String syncStatus,Integer sortCol, String sortDir, int type);


}