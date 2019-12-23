package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.Supplier;
import com.icicle.masterdb.pojo.vo.SupplierVO;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-10-09 11:29:36.
*/
public interface SupplierService extends Service<Supplier> {

    /**
     * 获取供应商列表
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param supplierSourceCode
     * @param supplierCodeOrName
     * @param supplierStatus
     * @return
     */
    DataTableRecord getSupplierList(String sEcho, Integer pageIndex, Integer pageSize,String supplierSourceCode, String supplierCodeOrName, String supplierStatus);

    /**
     * 导出供应商信息excel
     * @param list
     * @return
     */
    Workbook exportSupplierExcel(List<SupplierVO> list);

    /**
     * 获取同步到伯俊/WMS/RFID的供应商列表
     * @param supplierCodeList
     * @return
     */
    List<Supplier> getBurgeonSupplier(List<String> supplierCodeList);


}