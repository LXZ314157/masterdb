package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.BuyerMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.*;
import com.icicle.masterdb.pojo.vo.*;
import com.icicle.masterdb.service.*;
import com.icicle.masterdb.util.*;
import com.icicle.masterdb.web.BuyerController;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.icicle.masterdb.manager.SessionManager.getLoginName;


/**
 * @author wangyuling
 */
@Service
public class BuyerServiceImpl extends AbstractService<Buyer> implements BuyerService {
    @Resource
    private BuyerMapper buyerMapper;
    @Resource
    private BuyerService buyerService;
    @Resource
    private BuyerAttribDefinedService buyerAttribDefinedService;
    @Resource
    private BuyerGroupAttribService buyerGroupAttribService;
    @Resource
    private BuyerAttribGroupService buyerAttribGroupService;
    @Resource
    private BuyerAttributeService buyerAttributeService;
    @Resource
    private BuyerTypeService buyerTypeService;
    @Resource
    private ZoneService zoneService;
    @Resource
    private CityService cityService;
    @Resource
    private ViewBuyerListService viewBuyerListService;
    @Resource
    private ManageCenterService manageCenterService;

    @Override
    public int saveBuyer(Buyer buyer) {
        return buyerMapper.saveBuyer(buyer);
    }

    @Override
    public int updateBuyer(Buyer buyer) {
        return buyerMapper.updateBuyer(buyer);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "添加代理商")
    public BuyerVO insertBuyer(BuyerVO buyerVO) {
        BuyerVO vo = new BuyerVO();
        Buyer buyer = PojoConvertUtil.convertPojo(buyerVO, Buyer.class);
        buyer.setCreatedBy(SessionManager.getLoginName());
        buyer.setLastUpdatedBy(SessionManager.getLoginName());
        buyer.setCreationDate(DateUtil.now());
        buyer.setBuyerState(1);
        buyer.setZoneId(buyerVO.getZoneId()==null?0:buyerVO.getZoneId());
        if(StringUtils.isEmpty(buyerVO.getBuyerId())){
            buyer.setBuyerId("B999999");
        }
        int count = buyerService.saveBuyer(buyer);
        if (count > 0) {
            if(buyer.getBuyerId().equals("B999999")){
                int updateCount = buyerMapper.updateBuyerId("B"+buyer.getBuyerNo(),buyer.getBuyerNo());
                if(updateCount<=0){
                    return vo;
                }
            }
            List<BuyerAttribute> buyerAttributeslist = buyerVO.getAttr();
            List<BuyerAttribute> buyerFilterAttributeslist = buyerAttributeslist.stream().filter(a->!StringUtils.isEmpty(a.getBuyerAttriValue())).collect(Collectors.toList());
            if (buyerFilterAttributeslist.size() != 0) {
                for (BuyerAttribute item : buyerFilterAttributeslist) {
                    item.setBuyerNo(buyer.getBuyerNo());
                    item.setCreatedBy(SessionManager.getLoginName());
                    item.setCreationDate(DateUtil.now());
                }
                buyerAttributeService.save(buyerFilterAttributeslist);
            }
            String bankName = buyerVO.getBankName();
            String bankAccount = buyerVO.getBankAccount();
            if(!StringUtils.isEmpty(bankName) || !StringUtils.isEmpty(bankAccount)){
                BuyerAccount buyerAccount = new BuyerAccount();
                buyerAccount.setBuyerNo(buyer.getBuyerNo());
                buyerAccount.setBankName(bankName);
                buyerAccount.setBankAccount(bankAccount);
                buyerAccount.setCreatedBy(SessionManager.getLoginName());
                buyerAccount.setCreationDate(DateUtil.now());
                buyerAccount.setLastUpdateDate(DateUtil.now());
                count =  buyerMapper.saveAccountByBuyerNo(buyerAccount);
                if(count<=0){
                    return vo;
                }
            }
            vo.setBuyerId(buyer.getBuyerId().equals("B999999")?"B"+buyer.getBuyerNo():buyer.getBuyerId());
            vo.setBuyerNo(buyer.getBuyerNo());
            return vo;

        } else {
            return vo;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "更新代理商")
    public int updateBuyer(BuyerVO buyerVO) {
        int count = -1;
        Buyer buyer = PojoConvertUtil.convertPojo(buyerVO, Buyer.class);
        buyer.setLastUpdatedBy(SessionManager.getLoginName());
        buyerVO.setCreatedBy(SessionManager.getLoginName());
        buyerVO.setCreationDate(DateUtil.now());
        buyer.setLastUpdateDate(DateUtil.now());
        buyer.setZoneId(buyerVO.getZoneId()==null?0:buyerVO.getZoneId());
        count = buyerService.updateBuyer(buyer);
        if (count > 0) {
            int id = buyer.getBuyerNo();
            List<BuyerAttribute> buyerAttributeslist = buyerVO.getAttr();
            if (buyerAttributeslist != null && buyerAttributeslist.size() != 0) {
                for (BuyerAttribute item : buyerAttributeslist) {
                    item.setBuyerNo(id);
                }
                int ret = dealAttribute(buyerAttributeslist, id);
                if (ret < 0) {
                    return -1;
                }
            }
            BuyerAccount buyerAccount = buyerMapper.findAccountByBuyerNo(buyerVO.getBuyerNo());
            if(buyerAccount==null){
                if(!StringUtils.isEmpty(buyerVO.getBankName()) || !StringUtils.isEmpty(buyerVO.getBankAccount())){
                    BuyerAccount buyerAccount1 = new BuyerAccount();
                    buyerAccount1.setBuyerNo(buyerVO.getBuyerNo());
                    buyerAccount1.setCreatedBy(SessionManager.getLoginName());
                    buyerAccount1.setBankName(buyerVO.getBankName());
                    buyerAccount1.setBankAccount(buyerVO.getBankAccount());
                    buyerAccount1.setCreationDate(DateUtil.now());
                    buyerAccount1.setLastUpdateDate(DateUtil.now());
                    count =  buyerMapper.saveAccountByBuyerNo(buyerAccount1);
                }
            }else{
                buyerAccount.setBuyerNo(buyerVO.getBuyerNo());
                buyerAccount.setBankName(buyerVO.getBankName());
                buyerAccount.setBankAccount(buyerVO.getBankAccount());
                buyerAccount.setLastUpdateDate(DateUtil.now());
                buyerAccount.setLastUpdatedBy(SessionManager.getLoginName());
                count =  buyerMapper.updateAccountByBuyerNo(buyerAccount);
            }
            if(count<=0){
                id = -1;
            }
            return id;
        } else {
            return -1;
        }
    }

    @Override
    public BuyerSharedDefVO getShareData() {
        BuyerSharedDefVO vo = new BuyerSharedDefVO();
        try {
            List<BuyerAttribDefined> buyerAttribDefinedList = buyerAttribDefinedService.findAll();
            List<BuyerGroupAttrib> buyerGroupAttribList = buyerGroupAttribService.findAll();
            List<BuyerAttribGroup> buyerAttribGroupList = buyerAttribGroupService.findAll();
            List<BuyerAttribDefinedVO> buyerAttribDefVOList = PojoConvertUtil.convertPojoList(buyerAttribDefinedList, BuyerAttribDefinedVO.class);
            vo.setBuyerAttribDefVOList(buyerAttribDefVOList);
            vo.setBuyerGroupAttribList(buyerGroupAttribList);
            vo.setBuyerAttribGroupList(buyerAttribGroupList);
            return vo;
        } catch (Exception e) {
            LogUtil.getLogger(BuyerController.class).error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public BuyerVO findByBuyerNo(Integer buyerNo) {
        if (buyerNo == null) {
            return null;
        }
        Condition condition = new Condition(BuyerAttribute.class);
        condition.createCriteria().andEqualTo("buyerNo", buyerNo);
        Condition con = new Condition(Buyer.class);
        con.createCriteria().andEqualTo("buyerNo", buyerNo);
        Buyer buyer = buyerService.findOneByCondition(condition);
        List<BuyerAttribute> buyerAttributes = buyerAttributeService.findByCondition(condition);
        BuyerVO buyerVO = PojoConvertUtil.convertPojo(buyer, BuyerVO.class);
        BuyerAccount buyerAccount = buyerService.findAccountByBuyerNo(buyerNo);
        BuyerAccountVO buyerAccountVO = null;
        if(buyerAccount!=null){
            buyerAccountVO = PojoConvertUtil.convertPojo(buyerAccount, BuyerAccountVO.class);
        }
        buyerVO.setAttr(buyerAttributes);
        buyerVO.setBuyerAccountVO(buyerAccountVO);
        return buyerVO;
    }

    @Override
    public BuyerAccount findAccountByBuyerNo(Integer buyerNo) {
       return  buyerMapper.findAccountByBuyerNo(buyerNo);
    }

    @Override
    public BuyerDropDownVO getDropDownList() {
        BuyerDropDownVO buyerDropDownVO = new BuyerDropDownVO();
        try {
            Condition buyertype = new Condition(BuyerType.class);
            buyertype.createCriteria().andEqualTo("status", 1);
            Condition zone = new Condition(Zone.class);
            zone.createCriteria().andEqualTo("status", 1)
                    .andEqualTo("zoneLevel", 2);
            Condition city = new Condition(City.class);
            city.createCriteria().andEqualTo("status", 1);
            List<City> cityList = cityService.findByCondition(city);
            List<BuyerType> buyerTypeList = buyerTypeService.findByCondition(buyertype);
            List<ManageCenter> manageCenterList =   manageCenterService.findAll();
            List<Zone> zoneList = zoneService.findZone2ListByCondition();
            List<ZoneVO> zoneVOList = PojoConvertUtil.convertPojoList(zoneList, ZoneVO.class);
            List<CityVO> cityVOList = PojoConvertUtil.convertPojoList(cityList, CityVO.class);
            List<BuyerTypeVO> buyerTypeVOList = PojoConvertUtil.convertPojoList(buyerTypeList, BuyerTypeVO.class);
            buyerDropDownVO.setBuyerTypeVOList(buyerTypeVOList);
            buyerDropDownVO.setCityVOList(cityVOList);
            buyerDropDownVO.setZoneVOList(zoneVOList);
            buyerDropDownVO.setManageCenterList(manageCenterList);
            return buyerDropDownVO;
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public DataTableRecord getBuyerList(String sEcho, Integer pageIndex, Integer pageSize, String buyerName, String buyerState) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        Condition condition = new Condition(Buyer.class);
        Example.Criteria criteria = condition.createCriteria();
        Example.Criteria criteria2 = condition.createCriteria();
        if (!StringUtils.isBlank(buyerState)) {
            criteria.andEqualTo("buyerState", buyerState);
        }
        if (!StringUtils.isBlank(buyerName)) {
            String words = StringUtil.gsFormat("%{0}%", buyerName);
            condition.and(criteria2.andLike("buyerName", words)
                    .orLike("buyerId", words));
        }
        try {
            List<ViewBuyerList> list = viewBuyerListService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
            Integer total = (int) pageInfo.getTotal();
            dataTableRecord.setITotalRecords(total);
            dataTableRecord.setITotalDisplayRecords(total);
            dataTableRecord.setAaData(pageInfo.getList());
            return dataTableRecord;
        } catch (Exception ex) {
            LogUtil.getLogger(BuyerController.class).error(ex.getMessage());
            return null;
        }
    }

    /**
     * 代理商编辑属性，存在更新，不存在插入
     *
     * @param buyerAttributeslist
     * @param id
     * @return
     */
    @LogAction(logDesc = "新增或更新代理商属性值")
    private int dealAttribute(List<BuyerAttribute> buyerAttributeslist, Integer id) {
        if (buyerAttributeslist == null || buyerAttributeslist.size() == 0) {
            return -1;
        }
        if (id == null) {
            return -2;
        }
        Condition condition = new Condition(BuyerAttribute.class);
        condition.createCriteria().andEqualTo("buyerNo", id);
        List<BuyerAttribute> exeits = buyerAttributeService.findByCondition(condition);
        List<BuyerAttribute> insertList;
        List<BuyerAttribute> updateList;
        List<BuyerAttribute> buyerAttributesDeletelist = buyerAttributeslist.stream().filter(a->StringUtils.isEmpty(a.getBuyerAttriValue())).collect(Collectors.toList());
        List<Integer> delAttribDefIdList = getDeleteAttribDefId(exeits,buyerAttributesDeletelist);
        if(!ListUtil.isBlank(delAttribDefIdList)){
            buyerAttributeService.batchDeleteBuyerAttribute(delAttribDefIdList,id);
        }
        List<BuyerAttribute> buyerAttributesFilterlist = buyerAttributeslist.stream().filter(a->!StringUtils.isEmpty(a.getBuyerAttriValue())).collect(Collectors.toList());
        insertList = ListUtil.getSameOrDiffList(exeits, buyerAttributesFilterlist, false);
        updateList = ListUtil.getSameOrDiffList(exeits, buyerAttributesFilterlist, true);
        if (!ListUtil.isBlank(updateList)) {
            buyerAttributeService.updateBuyerAttribute(updateAndInsert(updateList, true));
        }
        if (!ListUtil.isBlank(insertList)) {
            buyerAttributeService.save(updateAndInsert(insertList, false));
        }
        return 1;
    }

    private List<BuyerAttribute> updateAndInsert(List<BuyerAttribute> list, boolean update) {
        if (ListUtil.isBlank(list)) {
            return null;
        }
        if (!update) {
            for (BuyerAttribute initem : list) {
                initem.setCreationDate(DateUtil.now());
                initem.setCreatedBy(getLoginName());
                initem.setLastUpdateDate(DateUtil.now());
            }
        } else {
            for (BuyerAttribute upitem : list) {
                upitem.setLastUpdateDate(DateUtil.now());
                upitem.setLastUpdatedBy(getLoginName());
            }
        }
        return list;
    }

    @Override
    public List<SharedBuyerVO> findSharedBuyerList() {
        return PojoConvertUtil.convertPojoList(buyerMapper.findBuyerVO(), SharedBuyerVO.class);
    }

    @Override
    public int checkBuyerId(String buyerId) {
        return buyerMapper.checkBuyerId(buyerId);
    }

    @Override
    public List<BuyerVO> findByQueryCondition(String buyerNameOrId, String buyerState) {
        return buyerMapper.findByQueryCondition(buyerNameOrId,buyerState);
    }

    @Override
    public Workbook exportBuyerExcel(List<BuyerVO> list) {
        return ExcelUtil.getBuyerWorkbook(list);
    }

    @Override
    public SyncBuyerVO getSyncBuyerById(String buyerId) {
        return buyerMapper.getSyncBuyerById(buyerId);
    }

    @Override
    public SyncBuyerVO getSyncBurgeonBuyerById(String buyerId) {
        return buyerMapper.getSyncBurgeonBuyerById(buyerId);
    }

    @Override
    public SyncZoneVO getSyncYxtStoreByNo(String buyerId) {
        return buyerMapper.getSyncYxtStoreByNo(buyerId);
    }


    private List<Integer> getDeleteAttribDefId(List<BuyerAttribute> exeits,List<BuyerAttribute> buyerAttributesDeletelist){
        List<Integer> list = new ArrayList<>();
        if(!ListUtil.isBlank(exeits) && !ListUtil.isBlank(buyerAttributesDeletelist)){
            List<Integer> existsAttDefIdList = exeits.stream().map(BuyerAttribute::getAttribDefId).collect(Collectors.toList());
            List<Integer> deleteAttDefIdList = buyerAttributesDeletelist.stream().map(BuyerAttribute::getAttribDefId).collect(Collectors.toList());
            existsAttDefIdList.retainAll(deleteAttDefIdList);
            list = existsAttDefIdList;
        }
        return list;
    }

}