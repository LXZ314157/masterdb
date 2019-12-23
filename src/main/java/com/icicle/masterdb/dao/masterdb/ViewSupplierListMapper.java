package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Supplier;
import com.icicle.masterdb.model.ViewSupplierList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewSupplierListMapper extends MyMapper<ViewSupplierList> {

    /**
     * 根据供应商编号查找供应商详情
     * @param supplierCode
     * @return
     */
    Supplier findSupplierInfoBySupplierCode(String supplierCode);

    /**
     * 根据条件查询供应商列表
     * @param supplierCodeOrName
     * @param supplierStatus
     * @return
     */
    List<Supplier> findByQueryCondition(@Param("supplierCodeOrName") String supplierCodeOrName,@Param("supplierStatus") String supplierStatus);


}