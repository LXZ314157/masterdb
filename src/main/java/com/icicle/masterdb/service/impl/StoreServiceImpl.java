package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.*;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.*;
import com.icicle.masterdb.pojo.vo.StoreVO;
import com.icicle.masterdb.service.StoreService;
import com.icicle.masterdb.service.ViewStoreaddrListService;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.ExcelUtil;
import com.icicle.masterdb.util.ListUtil;
import com.icicle.masterdb.util.PojoConvertUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liurenhua
 */
@Service
public class StoreServiceImpl extends AbstractService<Store> implements StoreService {
    @Resource
    private StoreMapper storeMapper;
    @Resource
    private StoreAttributeMapper storeAttributeMapper;
    @Resource
    private ZoneMapper zoneMapper;
    @Resource
    private StoreLevelMapper storeLevelMapper;
    @Resource
    private StoreTypeMapper storeTypeMapper;
    @Resource
    private ViewStoreaddrListService viewStoreaddrListService;
    @Resource
    private StoreComparisonMapper storeComparisonMapper;

    @Override
    @LogAction(logDesc = "新增店铺")
    @Transactional(rollbackFor = Exception.class)
    public StoreVO saveStore(StoreVO storeVO) {
        int count;
        Store store;

       Zone zone = zoneMapper.findZoneById(storeVO.getZoneId());
        if (zone==null) {
            storeVO.setStoreNo(0);
            return storeVO;
        }
        Condition condition = new Condition(Store.class);
        condition.createCriteria().andEqualTo("storeId", storeVO.getStoreId());
        store = PojoConvertUtil.convertPojo(storeVO, Store.class);
        store.setCreatedBy(SessionManager.getLoginName());
        store.setCreationDate(DateUtil.now());
        store.setLastUpdatedBy(SessionManager.getLoginName());
        store.setLastUpdateDate(DateUtil.now());
        store.setBuId(0);
        store.setProDeptId(0);
        if(StringUtils.isEmpty(storeVO.getStoreId())){
            store.setStoreId("S999999");
        }
        count = storeMapper.saveStore(store);
        if (count > 0) {
            Integer storeNo = store.getStoreNo();
            if(StringUtils.isEmpty(storeVO.getStoreId())){
                count = storeMapper.updateStoreId("S"+storeNo,storeNo);
            }
            if(count>0){
                List<StoreAttribute> storeAttributeList = storeVO.getStoreAttributeList();
                if (!ListUtil.isBlank(storeAttributeList)) {
                    for (StoreAttribute storeAttribute : storeAttributeList) {
                        storeAttribute.setStoreNo(storeNo);
                    }
                    storeAttributeMapper.insertList(storeAttributeList);
                }

                String compareStoreNos = storeVO.getCompareStoreNos();
                Map<String,Object> compareStoreNoMap = new HashMap<>();
                if(!compareStoreNos.isEmpty()){
                    String [] compareStoreNoList = compareStoreNos.split(",");
                    for(String compareStoreNo : compareStoreNoList){
                        compareStoreNoMap.put("compareStoreNo",compareStoreNo);
                        compareStoreNoMap.put("storeNo",String.valueOf(store.getStoreNo()));
                        storeComparisonMapper.addCompareStoreNoByStoreNo(compareStoreNoMap);
                    }
                }

                String productLines = storeVO.getProductLine();
                Map<String,Object> map = new HashMap<>();
                if(!productLines.isEmpty()){
                    String [] productlineList = productLines.split(",");
                    for(String productLine : productlineList){
                        map.put("productLine",productLine);
                        map.put("storeNo",String.valueOf(store.getStoreNo()));
                        storeMapper.addStoreProductLine(map);
                    }
                }
                storeVO.setStoreNo(store.getStoreNo());
                if(StringUtils.isEmpty(storeVO.getStoreId())){
                    storeVO.setStoreId("S"+storeNo);
                }else{
                    storeVO.setStoreId(store.getStoreId());
                }
                return storeVO;
            }else{
                storeVO.setStoreNo(0);
                return storeVO;
            }
        } else {
            storeVO.setStoreNo(0);
            return storeVO;
        }

    }

    @Override
    @LogAction(logDesc = "更新店铺")
    @Transactional(rollbackFor = Exception.class)
    public int updateStore(StoreVO storeVO) {
        int count = 0;
        List<StoreAttribute> storeAttributeList = storeVO.getStoreAttributeList();
        Condition condition = new Condition(Store.class);
        condition.createCriteria().andEqualTo("storeId", storeVO.getStoreId());
        List<Store> storeList = storeMapper.selectByCondition(condition);
        Zone zone = zoneMapper.findZoneById(storeVO.getZoneId());
        if(zone==null){
            return count;
        }
        if (ListUtil.isBlank(storeList)) {
            return count;
        } else {

            List<StoreAttribute> attributeList = storeAttributeMapper.findByStoreNo(storeVO.getStoreNo());
            List<StoreAttribute> updateList = ListUtil.getSameOrDiffList(attributeList, storeAttributeList, true);
            List<StoreAttribute> insertList = ListUtil.getSameOrDiffList(attributeList, storeAttributeList, false);
            List<StoreAttribute> deleteList = ListUtil.getSameOrDiffList(storeAttributeList, attributeList, false);
            if (!ListUtil.isBlank(updateList)) {
                storeAttributeMapper.updateStoreAttributeList(updateList);
            }
            if (!ListUtil.isBlank(insertList)) {
                storeAttributeMapper.insertList(insertList);
            }

            if (!ListUtil.isBlank(deleteList)) {
                for (StoreAttribute storeAttribute : deleteList) {
                    storeAttribute.setStoreAttribValue("");
                }
                storeAttributeMapper.updateStoreAttributeList(deleteList);
            }

            String compareStoreNos = storeVO.getCompareStoreNos();
            storeComparisonMapper.deleteCompareStoreNoByStoreNo(String.valueOf(storeVO.getStoreNo()));
            Map<String,Object> compareStoreNoMap = new HashMap<>();
            if(!compareStoreNos.isEmpty()){
                String [] compareStoreNoList = compareStoreNos.split(",");
                for(String compareStoreNo : compareStoreNoList){
                    compareStoreNoMap.put("compareStoreNo",compareStoreNo);
                    compareStoreNoMap.put("storeNo",String.valueOf(storeVO.getStoreNo()));
                    storeComparisonMapper.addCompareStoreNoByStoreNo(compareStoreNoMap);
                }
            }

            String productLines = storeVO.getProductLine();
            storeMapper.deleteProductLineByStoreNo(String.valueOf(storeVO.getStoreNo()));
            Map<String,Object> productLineMap = new HashMap<>();
            if(!productLines.isEmpty()){
                String [] productlineList = productLines.split(",");
                for(String productLine : productlineList){
                    productLineMap.put("productLine",productLine);
                    productLineMap.put("storeNo",String.valueOf(storeVO.getStoreNo()));
                    storeMapper.addStoreProductLine(productLineMap);
                }
            }
            Store s = PojoConvertUtil.convertPojo(storeVO, Store.class);
            s.setLastUpdateDate(DateUtil.now());
            s.setProDeptId(zone.getProDeptId());
            s.setBuId(zone.getBuId());
            s.setLastUpdatedBy(SessionManager.getLoginName());
            count = storeMapper.updateStore(s);
            return count;
        }

    }

    @Override
    @LogAction(logDesc = "新增店铺级别")
    public int addStoreLevel(StoreLevel storeLevel) {
        int count;
        storeLevel.setStatus(1);
        storeLevel.setCreationDate(DateUtil.now());
        storeLevel.setCreatedBy(SessionManager.getLoginName());
        count = storeLevelMapper.saveStoreLevel(storeLevel);
        return count;
    }

    @Override
    @LogAction(logDesc = "更新店铺级别")
    public int updateStoreLevel(StoreLevel storeLevel) {
        int count = 0;
        storeLevel.setLastUpdateDate(DateUtil.now());
        storeLevel.setLastUpdatedBy(SessionManager.getLoginName());
        count = storeLevelMapper.updateByPrimaryKeySelective(storeLevel);
        return count;
    }

    @Override
    @LogAction(logDesc = "更新店铺类型")
    public int updateStoreType(StoreType storeType) {
        storeType.setLastUpdateDate(DateUtil.now());
        storeType.setLastUpdatedBy(SessionManager.getLoginName());
        int count = storeTypeMapper.updateByPrimaryKeySelective(storeType);
        return count;
    }

    @Override
    @LogAction(logDesc = "新增店铺类型")
    public int addStoreType(StoreType storeType) {
        int count = 0;
        storeType.setCreatedBy(SessionManager.getLoginName());
        storeType.setCreationDate(DateUtil.now());
        storeType.setStatus(1);
        count = storeTypeMapper.saveStoreType(storeType);
        return count;
    }

    @Override
    public List<StoreLevel> findAllStoreLevel() {
        return storeLevelMapper.selectAll();
    }

    @Override
    public List<StoreLevel> findAllValidLevel() {
        return storeLevelMapper.findValidLevel();
    }

    @Override
    public List<StoreComparison> findCompareStoreNoListByStoreNo(String storeNo) {
        return storeComparisonMapper.findCompareStoreNoListByStoreNo(storeNo);
    }

    @Override
    public List<StoreVO> findStoreList() {
        return storeMapper.findStoreList();
    }

    @Override
    public List<StoreType> findAllStoreType() {
        return storeTypeMapper.selectAll();
    }

    @Override
    public List<StoreType> findAllValiedType() {
        return storeTypeMapper.findValidType();
    }

    @Override
    public boolean typeNameExists(StoreType storeType, boolean update) {
        Condition condition = new Condition(StoreType.class);
        if (update) {
            condition.createCriteria().andEqualTo("storeTypeName", storeType.getStoreTypeName()).andNotEqualTo("storeTypeId", storeType.getStoreTypeId());
        } else {
            condition.createCriteria().andEqualTo("storeTypeName", storeType.getStoreTypeName());
        }

        int count = storeTypeMapper.selectCountByCondition(condition);

        return count > 0;
    }

    @Override
    public StoreType findTypeById(Integer storeTypeId) {
        return storeTypeMapper.selectByPrimaryKey(storeTypeId);
    }

    @Override
    public boolean levelNameExists(StoreLevel storeLevel, boolean update) {
        Condition condition = new Condition(StoreLevel.class);
        if (update) {
            condition.createCriteria().andEqualTo("storeLevelName",
                    storeLevel.getStoreLevelName()).andNotEqualTo("storeLevelId", storeLevel.getStoreLevelId());
        } else {
            condition.createCriteria().andEqualTo("storeLevelName", storeLevel.getStoreLevelName());
        }
        int count = storeLevelMapper.selectCountByCondition(condition);
        return count > 0;
    }

    @Override
    public StoreLevel findLevelById(Integer levelId) {
        return storeLevelMapper.selectByPrimaryKey(levelId);
    }


    @Override
    public DataTableRecord getAddrListByStoreNo(String storeNo) {
        return null;
    }

    @Override
    public List<Map<String, String>> findBaseAttributeList() {
        return storeMapper.findBaseAttributeList();
    }

    @Override
    public List<Map<String, String>> findBaseAttributeListById(List<Map<String,String>> list, String storeAttribDefId) {
        List<Map<String,String>> resultList = new ArrayList<>();
        if(!list.isEmpty()){
            for(Map<String,String> map : list){
                String storeAttribDefCode = String.valueOf(map.get("store_attrib_def_code"));
                if(storeAttribDefCode.equals(storeAttribDefId)){
                    resultList.add(map);
                }
            }
        }
        return resultList;
    }

    @Override
    public int verifysotreid(String storeId) {
        return storeMapper.verifysotreid(storeId);
    }

    @Override
    public Workbook exportExcel(List<ViewStore> list,List<Map<String,String>> mapList) {
        List<StoreComparison> storeComparisonList = storeMapper.getStoreComparasionList();
        return ExcelUtil.getStoreWorkbook(list,mapList,storeComparisonList);
    }

    @Override
    public List<Map<String, String>> getStoreAttribMapList() {
        return storeMapper.getStoreAttribMapList();
    }

    @Override
    public String getStoreIdByStoreNo(Integer storeNo) {
        return storeMapper.getStoreIdByStoreNo(storeNo);
    }

    @Override
    public String getStoreIdByAddressId(Integer addressId) {
        return storeMapper.getStoreIdByAddressId(addressId);
    }

}