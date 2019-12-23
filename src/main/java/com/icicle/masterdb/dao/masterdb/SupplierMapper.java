package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Supplier;
import com.icicle.masterdb.pojo.vo.SyncRFIDSupplierVO;
import com.icicle.masterdb.pojo.vo.SyncWMSSupplierVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierMapper extends MyMapper<Supplier> {

    /**
     * 获取同步到伯俊/WMS/RFID的供应商列表
     * @param supplierCodeList
     * @return
     */
    List<Supplier> getBurgeonSupplier(@Param("supplierCodeList") List<String> supplierCodeList);


}