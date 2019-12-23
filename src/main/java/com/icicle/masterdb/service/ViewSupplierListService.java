package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ViewSupplierList;
import com.icicle.masterdb.pojo.vo.SupplierVO;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-09 21:26:32.
*/
public interface ViewSupplierListService extends Service<ViewSupplierList> {

    /**
     * 根据供应商编号查找供应商详情
     * @param supplierCode
     * @return
     */
    SupplierVO findSupplierInfoBySupplierCode(String supplierCode);

    /**
     * 根据条件查询供应商列表
     * @param supplierCodeOrName
     * @param supplierStatus
     * @return
     */
    List<SupplierVO> findByQueryCondition(String supplierCodeOrName,String supplierStatus);

}