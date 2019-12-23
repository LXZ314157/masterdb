package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.StoreAddress;
import com.icicle.masterdb.pojo.vo.StoreAddressVO;
import com.icicle.masterdb.pojo.vo.SyncStoreVO;

import java.util.List;

public interface StoreAddressMapper extends MyMapper<StoreAddress> {

    /**
     *
     * @param adressId
     * @return
     */
    public int deleteStoreAressByAdressId(String adressId);

    /**
     *
     * @param addressType
     * @return
     */
    public List<StoreAddress> selectStoreAddrDefault(String addressType);

    /**
     *
     * @param addressType
     * @return
     */
    public int updateStoreAddressList(String addressType);

    /**
     *
     * @param adressId
     * @return
     */
    public int updateStoreAressByAdressId(String adressId);

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