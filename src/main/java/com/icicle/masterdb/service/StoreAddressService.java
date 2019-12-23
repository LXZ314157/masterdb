package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.StoreAddress;
import com.icicle.masterdb.pojo.vo.StoreAddressVO;
import com.icicle.masterdb.pojo.vo.SyncStoreVO;

/**
* @author  CodeGeneratorUtil
* @version 2019-02-19 11:15:46.
*/
public interface StoreAddressService extends Service<StoreAddress> {

    /**
     *
     * @param adressId
     * @return
     */
    public int deleteStoreAddrByAddressId(String adressId);

    /**
     *
     * @param storeAddress
     * @return
     */
    public int setAddrDefault(StoreAddress storeAddress);

    /**
     *
     * @param storeAddressVO
     * @return
     */
    public int addstoreAddr(StoreAddressVO storeAddressVO);

    /**
     *
     * @param storeAddressVO
     * @return
     */
    public int updateStoreAddr(StoreAddressVO storeAddressVO);


    /**
     * 根据addressId获取店铺地址
     * @param addressId
     * @return
     */
    SyncStoreVO getSyncAddressById(Integer addressId);


}