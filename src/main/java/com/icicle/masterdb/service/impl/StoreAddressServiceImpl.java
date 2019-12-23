package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.config.SynConfigEntity;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.StoreAddressMapper;
import com.icicle.masterdb.model.StoreAddress;
import com.icicle.masterdb.pojo.vo.StoreAddressVO;
import com.icicle.masterdb.pojo.vo.SyncStoreVO;
import com.icicle.masterdb.service.StoreAddressService;
import com.icicle.masterdb.util.SyncHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author  CodeGeneratorUtil
* @version 2019-02-19 11:15:46.
*/
@Service
public class StoreAddressServiceImpl extends AbstractService<StoreAddress> implements StoreAddressService {
    @Resource
    private StoreAddressMapper storeAddressMapper;
    @Resource
    private SynConfigEntity synConfigEntity;

    @Override
    public int deleteStoreAddrByAddressId(String addressId) {
        return storeAddressMapper.deleteStoreAressByAdressId(addressId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int setAddrDefault(StoreAddress storeAddress) {
        try{
            storeAddressMapper.updateStoreAddressList(String.valueOf(storeAddress.getAddressType()));
            storeAddressMapper.updateStoreAressByAdressId(String.valueOf(storeAddress.getAddressId()));
            SyncStoreVO syncStoreVO = storeAddressMapper.getSyncAddressById(storeAddress.getAddressId());
            if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getStoreAddrSync2Burgeon()), syncStoreVO)) {
                return 1;
            } else {
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int addstoreAddr(StoreAddressVO storeAddressVO) {
        if(storeAddressVO.getDefault()){
            storeAddressMapper.updateStoreAddressList(String.valueOf(storeAddressVO.getAddressType()));
        }
       return storeAddressMapper.addstoreAddr(storeAddressVO);
    }

    @Override
    public int updateStoreAddr(StoreAddressVO storeAddressVO) {
        if(storeAddressVO.getDefault()){
            storeAddressMapper.updateStoreAddressList(String.valueOf(storeAddressVO.getAddressType()));
        }
       return storeAddressMapper.updateStoreAddr(storeAddressVO);
    }

    @Override
    public SyncStoreVO  getSyncAddressById(Integer addressId) {
        return storeAddressMapper.getSyncAddressById(addressId);
    }

}