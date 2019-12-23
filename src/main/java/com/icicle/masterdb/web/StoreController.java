package com.icicle.masterdb.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.authorization.annotation.Authorization;
import com.icicle.masterdb.config.IcicleConfig;
import com.icicle.masterdb.config.SynConfigEntity;
import com.icicle.masterdb.core.*;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.*;
import com.icicle.masterdb.pojo.vo.*;
import com.icicle.masterdb.service.*;
import com.icicle.masterdb.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.icicle.masterdb.core.ProjectConstant.UNKNOWN_EXCEPTION_CODE;
import static com.icicle.masterdb.service.constant.ServiceConstant.*;

/**
 * @author liurenhua
 */
@Controller
@RequestMapping("/store")
@Authorization
public class StoreController extends AbstractPageController {
    @Resource
    private SynConfigEntity synConfigEntity;
    @Resource
    private StoreService storeService;
    @Resource
    private StoreAttribGroupService storeAttribGroupService;
    @Resource
    private StoreAttribDefinedService storeAttribDefinedService;
    @Resource
    private StoreGroupAttribService storeGroupAttribService;
    @Resource
    private CityService cityService;
    @Resource
    private BusinessUnitService businessUnitService;
    @Resource
    private BuyerService buyerService;
    @Resource
    private ViewStoreService viewStoreService;
    @Resource
    private ViewStoreaddrListService viewStoreaddrListService;
    @Resource
    private StoreAttribItemService storeAttribItemService;
    @Resource
    private StoreComparisonService storeComparisonService;
    @Resource
    private ZoneService zoneService;
    @Resource
    private StoreAttributeService storeAttributeService;
    @Resource
    private VStoresService vStoresService;
    @Resource
    private StoreAddressService storeAddressService;
    @Resource
    private ProductLineService productLineService;
    @Resource
    private StaffService staffService;
    @Resource
    private ViewStoreProductLineService viewStoreProductLineService;
    @Resource
    private CostcenterService costcenterService;
    @Resource
    private ResponsibilitycenterService responsibilitycenterService;
    @Resource
    private ManageCenterService manageCenterService;
    @Resource
    private CompanyService companyService;
    @Resource
    private StoreProductLineService storeProductLineService;
    @Resource
    private IcicleConfig icicleConfig;

    private final String CLOSE_SUFFIX = "(关闭店)";
    /**
     * 关闭店铺的状态
     */
    private final int COLSE_STORE_STATE = 0;

    public StoreController() {
        //将当前 controller 名传入，与 viewer 中的 hello 文件夹名对应
        super("store");
    }

    @GetMapping("/index")
    public String index(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("list", map, request);
    }
    @GetMapping("/storeaddr")
    public String storeaddr(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("storeaddr", map, request);
    }

    @GetMapping("/addstore")
    public String addStore(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("addstore", map, request);
    }

    @GetMapping("/storeattributes")
    public String storeAttributes(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("attri", map, request);
    }

    /**
     * 店铺级别
     **/
    @GetMapping("/storelevel")
    public String storeLevel(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("storerex", map, request);
    }

    @GetMapping("/city")
    public String city(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("city", map, request);
    }

    @GetMapping("/zone")
    public String zone(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("zone", map, request);
    }


    @GetMapping("/verifysotreid")
    @ResponseBody
    public Result verifysotreid(@RequestParam String storeId){
        try {
            int count = storeService.verifysotreid(storeId);
            if(count==0){
                return ResultGenerator.genSuccessResult();
            }else{
                return ResultGenerator.genFailResult("店铺ID重复");
            }
        }catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PutMapping("/update")
    @ResponseBody
    public Result update(@RequestBody StoreVO storeVO) {
        List<SyncProductLineVO> oldProductLineVOList = zoneService.getProductLineByStoreNo(storeVO.getStoreNo());
        storeVO.setOldProductLineList(oldProductLineVOList);
        List<String> message = new ArrayList<>();
        if (storeVO == null) {
            return ResultGenerator.genFailResult("店铺信息不能为空");
        }
        //关键字段不能为空
        if (StringUtils.isBlank(storeVO.getStoreId())) {
            return ResultGenerator.genFailResult("店铺Id不能为空");
        }
        if (storeVO.getBuyerId() == null) {
            return ResultGenerator.genFailResult("代理商不能为空");
        }
        if (storeVO.getStoreLevelId() == null) {
            return ResultGenerator.genFailResult("店铺级别不能为空");
        }
        if (storeVO.getStoreTypeId() == null) {
            return ResultGenerator.genFailResult("店铺类型不能为空");
        }
        if (StringUtils.isBlank(storeVO.getStoreName().trim())) {
            return ResultGenerator.genFailResult("店铺名称不能为空");
        }
        if (storeVO.getStoreState() == COLSE_STORE_STATE) {
            if (!storeVO.getStoreName().contains(CLOSE_SUFFIX)) {
                storeVO.setStoreName(storeVO.getStoreName().concat(CLOSE_SUFFIX));
            }
        } else {
            if (storeVO.getStoreName().contains(CLOSE_SUFFIX)) {
                storeVO.setStoreName(storeVO.getStoreName().replace(CLOSE_SUFFIX, ""));
            }
        }
        try{
            int count = storeService.updateStore(storeVO);
            if (count > 0) {
                message.add(synStoreMessage(storeVO,false));
                message.add(storeVO.getStoreNo().toString());
            } else {
                return ResultGenerator.genFailResult("更新失败，此店铺不存在");
            }
            return ResultGenerator.genSuccessResult(message);
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.getLogger(StoreController.class).error(e.getMessage(), e);
            return ResultGenerator.genFailResult("店铺更新失败");
        }

    }

    @GetMapping("/find")
    @ResponseBody
    public Result find(@RequestParam("id") Integer id) {
        if (id == null) {
            return ResultGenerator.genFailResult("店铺ID不能为空");
        }
        try {
            ViewStore viewStore = viewStoreService.findById(id);
            if (viewStore == null) {
                return ResultGenerator.genFailResult("当前店铺No不存在");
            } else {
                StoreVO storeVO = PojoConvertUtil.convertPojo(viewStore, StoreVO.class);
                List<StoreAttribute> storeAttributeList = storeAttributeService.findByStoreNo(id);
                List<ProductLine> productLineLists = productLineService.findProductLine();
                List<StoreProductLine> productlineList = storeProductLineService.findProductLineByStoreNo(String.valueOf(id));
                List<StoreComparison> compareByStoreNoList = storeService.findCompareStoreNoListByStoreNo(String.valueOf(id));
                storeVO.setCompareByStoreNoList(compareByStoreNoList);
                storeVO.setStoreAttributeList(storeAttributeList);
                storeVO.setProductLineLists(productLineLists);
                storeVO.setProductLineList(productlineList);
                return ResultGenerator.genSuccessResult(storeVO);
            }
        } catch (Exception e) {
            LogUtil.getLogger(StoreController.class).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }


    @PostMapping("/addstoredata")
    @ResponseBody
    public Result addStoreData(@RequestBody StoreVO storeVO) {
//        int check = checkStoreVO(storeVO);
        try{
            List<String> message = new ArrayList<>();
            storeVO.setStoreId(storeVO.getStoreId().toUpperCase());
            StoreVO vo  = storeService.saveStore(storeVO);
            String storeId = vo.getStoreId();
            storeVO.setStoreId(storeId);
            if (vo.getStoreNo() > 0) {
                message.add(synStoreMessage(storeVO,true));
                message.add(vo.getStoreNo().toString());
                message.add(storeId);
            } else {
                return ResultGenerator.genFailResult("新增店铺失败");
            }
            return ResultGenerator.genSuccessResult(message);
        }catch (Exception e){
            e.printStackTrace();
            LogUtil.getLogger(StoreController.class).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }


    @PostMapping("/storeattribdefinedvo")
    @ResponseBody
    public Result storeAttribDefinedVO() {
        List<StoreAttribDefVO> list;
        try {
            List<StoreAttribDefined> storeAttribDefinedList = storeAttribDefinedService.findAllValidAttribDef();
            list = PojoConvertUtil.convertPojoList(storeAttribDefinedList, StoreAttribDefVO.class);
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }

    }

    @PostMapping("/listattributeitem")
    @ResponseBody
    public Result listAttributeItem() {
        try {
            List<StoreAttribItem> itemList = storeAttribItemService.findAll();
            List<StoreAttributeItemVO> list = PojoConvertUtil.convertPojoList(itemList, StoreAttributeItemVO.class);
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("/list")
    @ResponseBody
    public DataTableRecord list(
            @RequestParam(value = "sEcho") String sEcho,
                                @RequestParam(value = "iDisplayStart") Integer pageIndex,
                                @RequestParam(value = "iDisplayLength") Integer pageSize,
                                @RequestParam(value = "sSearch") String sSearch,
                                @RequestParam(value = "buyerId") String buyerId,
                                @RequestParam(value = "storeLevelId") String storeLevelId,
                                @RequestParam(value = "storeTypeId") String storeTypeId,
                                @RequestParam(value = "anywords") String anywords) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        try {
            Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
            PageHelper.startPage(pageNum, pageSize);
            List<ViewStore> list;
            Condition condition = new Condition(ViewStore.class);
            if (sSearch.trim().length() == 0 && buyerId.trim().length() == 0 && storeLevelId.trim().length() == 0 && storeTypeId.trim().length() == 0 && anywords.trim().length() == 0) {
                list = viewStoreService.findAll();
            } else {
                if (anywords.trim().length() == 0) {
                    anywords = sSearch;
                }
                Example.Criteria criteria = condition.createCriteria();
                if (storeTypeId.length() != 0) {
                    criteria.andEqualTo("storeTypeId", storeTypeId);
                }
                if (buyerId.trim().length() > 0) {
                    criteria.andEqualTo("buyerId", buyerId);
                }
                if (storeLevelId.trim().length() > 0) {
                    criteria.andEqualTo("storeLevelId", storeLevelId);
                }
                if (anywords.trim().length() > 0) {
                    if(sSearch.trim().length() > 0){
                        String words = StringUtil.gsFormat("%{0}%", anywords);
                        String search = StringUtil.gsFormat("%{0}%", sSearch);
                        condition.and(condition.createCriteria().andLike("storeId", words).orLike("storeName", words));
                        condition.and(condition.createCriteria().andLike("storeId", search).orLike("storeName", search));
                    }else{
                        String words = StringUtil.gsFormat("%{0}%", anywords);
                        condition.and(condition.createCriteria().andLike("storeId", words).orLike("storeName", words));
                    }
                }
                list = viewStoreService.findByCondition(condition);
            }

            List<StoreViewVO> vList = PojoConvertUtil.convertPojoList(list, StoreViewVO.class);
            PageInfo pageInfo = new PageInfo(list);
            Integer total = (int) pageInfo.getTotal();
            dataTableRecord.setITotalRecords(total);
            dataTableRecord.setITotalDisplayRecords(total);
            dataTableRecord.setAaData(vList);
        } catch (Exception e) {
            LogUtil.getLogger(StoreController.class).error(e.getMessage(), e);
        }
        return dataTableRecord;
    }

    @PostMapping("/downLoadExcel")
    public void downLoadExcel(HttpServletResponse response,
                     @RequestParam(value = "sSearch") String sSearch,
                     @RequestParam(value = "buyerId") String buyerId,
                     @RequestParam(value = "storeLevelId") String storeLevelId,
                     @RequestParam(value = "storeTypeId") String storeTypeId,
                     @RequestParam(value = "anywords") String anywords){
        try {
            List<ViewStore> list;
            Condition condition = new Condition(ViewStore.class);
            if (sSearch.trim().length() == 0 && buyerId.trim().length() == 0 && storeLevelId.trim().length() == 0 && storeTypeId.trim().length() == 0 && anywords.trim().length() == 0) {
                list = viewStoreService.findAll();
            } else {
                if (anywords.trim().length() == 0) {
                    anywords = sSearch;
                }
                Example.Criteria criteria = condition.createCriteria();
                if (storeTypeId.length() != 0) {
                    criteria.andEqualTo("storeTypeId", storeTypeId);
                }
                if (buyerId.trim().length() > 0) {
                    criteria.andEqualTo("buyerId", buyerId);
                }
                if (storeLevelId.trim().length() > 0) {
                    criteria.andEqualTo("storeLevelId", storeLevelId);
                }
                if (anywords.trim().length() > 0) {
                    if(sSearch.trim().length() > 0){
                        String words = StringUtil.gsFormat("%{0}%", anywords);
                        String search = StringUtil.gsFormat("%{0}%", sSearch);
                        condition.and(condition.createCriteria().andLike("storeId", words).orLike("storeName", words));
                        condition.and(condition.createCriteria().andLike("storeId", search).orLike("storeName", search));
                    }else{
                        String words = StringUtil.gsFormat("%{0}%", anywords);
                        condition.and(condition.createCriteria().andLike("storeId", words).orLike("storeName", words));
                    }
                }
                list = viewStoreService.findByCondition(condition);
            }
            List<Map<String,String>> storeAttribMapList = storeService.getStoreAttribMapList();
            Workbook workbook = storeService.exportExcel(list,storeAttribMapList);
            String tableName = "店铺信息列表";
            ExcelUtil.downloadExcelWithStream(response,workbook,tableName);
        } catch (Exception e) {
            LogUtil.getLogger(StoreController.class).error(e.getMessage(), e);
        }
    }




    @GetMapping("/attributedefine")
    @ResponseBody
    public Result attributeDefine() {
        try {
            List<StoreAttribDefined> list = storeAttribDefinedService.findAllValidAttribDef();
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("/addattributedefine")
    @ResponseBody
    public Result addAttributeDefine(@RequestBody StoreAttribDefVO storeAttribDefVO) {
        String message = checkStoreAttribDef(storeAttribDefVO, false);
        if (message != null) {
            return ResultGenerator.genFailResult(message);
        }
        try {
            int count = storeAttribDefinedService.addStoreAttributeDefVO(storeAttribDefVO);
            if (count > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("添加失败");
            }
        } catch (Exception e) {
            LogUtil.getLogger(StoreController.class).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    /*店铺属性操作*/
    @GetMapping("/findattribute")
    @ResponseBody
    public Result findAttribute(@RequestParam("storeAttribDefiId") Integer storeAttribDefiId) {
        StoreAttribDefined storeAttribDefined;
        StoreAttribDefVO storeAttribDefVO = null;
        try {
            storeAttribDefined = storeAttribDefinedService.findById(storeAttribDefiId);
            storeAttribDefVO = PojoConvertUtil.convertPojo(storeAttribDefined, StoreAttribDefVO.class);
            List<StoreAttribItem> itemList;
            itemList = storeAttribItemService.findByAttribDefId(storeAttribDefiId);

            List<StoreAttributeItemVO> list = PojoConvertUtil.convertPojoList(itemList, StoreAttributeItemVO.class);

            storeAttribDefVO.setItemList(list);
        } catch (Exception e) {
            LogUtil.getLogger(StoreController.class).error(e.getMessage(), e);
            ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(storeAttribDefVO);
    }

    @PostMapping("/updatestoreattribdef")
    @ResponseBody
    public Result updateStoreAttribDef(@RequestBody StoreAttribDefVO storeAttribDefVO) {
        String message = checkStoreAttribDef(storeAttribDefVO, true);
        if (message != null) {
            return ResultGenerator.genFailResult(message);
        }

        try {
            int count = storeAttribDefinedService.updateStoreAttributeDefVO(storeAttribDefVO);
            if (count > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("更新失败");
            }
        } catch (Exception e) {
            LogUtil.getLogger(StoreController.class).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    /***店铺属性操作**/
    @DeleteMapping("/deletestoreattributegroup/{id}")
    @ResponseBody
    public Result deleteStoreAttributeGroup(@PathVariable("id") Integer id) {
        StoreAttribGroup storeAttribGroup = storeAttribGroupService.findById(id);
        if (storeAttribGroup != null) {
            storeAttribGroup.setStatus(0);
            int count = storeAttribGroupService.updateAttribGroup(storeAttribGroup);
            if (count > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("您要删除的项不存在");
            }
        }
        return ResultGenerator.genFailResult("您要删除的项不存在");
    }

    @GetMapping("/add")
    @ResponseBody
    public Result add(String gcode, String gname) {

        if (StringUtils.isBlank(gcode) || StringUtils.isBlank(gname)) {
            return ResultGenerator.genFailResult("属性组编码或属性组名称不能为空");
        }

        try {
            if (!storeAttribGroupService.attribCodeExists(gcode, gname)) {
                StoreAttribGroup sag = new StoreAttribGroup();
                sag.setCreationDate(DateUtil.now());
                sag.setCreatedBy(SessionManager.getLoginName());
                sag.setStatus(1);
                sag.setStoreAttribGroupCode(gcode);
                sag.setStoreAttribGroupName(gname);
                int count = storeAttribGroupService.saveAttribGroup(sag);
                if (count > 0) {
                    return ResultGenerator.genSuccessResult();
                } else {
                    return ResultGenerator.genFailResult("添加失败");
                }
            } else {
                return ResultGenerator.genFailResult("属性组编码或属性组名重复,请更换属性组编码或属性组名");
            }
        } catch (Exception e) {
            LogUtil.getLogger(StoreController.class).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }

    }

    /***店铺属性操作结束**/
    @PostMapping("/attributeconnect")
    @ResponseBody
    public Result attributeConnect(@RequestBody StoreGroupAttributeListVO storeGroupAttributeListVO) {
        int count = storeGroupAttribService.attributeConnect(storeGroupAttributeListVO);
        if (count > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("属性关联失败");
        }
    }

    /**
     * 搜索店铺时候的下拉列表
     */
    @PostMapping("/getsearchcondition")
    @ResponseBody
    public Result getSearchCondition() {
        SearchContionVO searchContionVO = new SearchContionVO();
        List<StoreLevel> storeLevelList;
        List<StoreType> storeTypeList;
        List<SharedBuyerVO> buyerList;

        try {
            buyerList = buyerService.findSharedBuyerList();
            storeTypeList = storeService.findAllValiedType();
            storeLevelList = storeService.findAllValidLevel();
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }

        List<BuyerVO> buyerVOList = PojoConvertUtil.convertPojoList(buyerList, BuyerVO.class);
        List<StoreLevelVO> storeLevelVOList = PojoConvertUtil.convertPojoList(storeLevelList, StoreLevelVO.class);
        List<StoreTypeVO> storeTypeVOList = PojoConvertUtil.convertPojoList(storeTypeList, StoreTypeVO.class);

        searchContionVO.setBuyerVOList(buyerVOList);
        searchContionVO.setStoreLevelVOList(storeLevelVOList);
        searchContionVO.setStoreTypeVOList(storeTypeVOList);

        return ResultGenerator.genSuccessResult(searchContionVO);
    }


    //获取店铺级别
    @PostMapping("/levellist")
    @ResponseBody
    public Result levelList() {
        List<StoreLevel> storeLevelList = storeService.findAllStoreLevel();
        List<StoreLevelVO> voList = PojoConvertUtil.convertPojoList(storeLevelList, StoreLevelVO.class);
        return ResultGenerator.genSuccessResult(voList);
    }

    @PostMapping("/addstorelevel")
    @ResponseBody
    public Result addStoreLevel(@RequestParam(value = "storeLevelName") String storeLevelName,
                                @RequestParam(value = "storeLevelDesc") String storeLevelDesc) {
        if (StringUtils.isBlank(storeLevelName)) {
            return ResultGenerator.genFailResult("店铺级别名不能为空");
        }
        StoreLevel storeLevel = new StoreLevel();
        storeLevel.setStoreLevelName(storeLevelName);
        storeLevel.setStoreLevelDesc(storeLevelDesc);
        if (storeService.levelNameExists(storeLevel, false)) {
            return ResultGenerator.genFailResult("当前店铺级别名已经存在，请换一个级别名后再试");
        }
        int count = storeService.addStoreLevel(storeLevel);
        if (count > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("服务器当前正忙，请稍后再试");
        }
    }

    @PutMapping("/updatestorelevel")
    @ResponseBody
    public Result updateStoreLevel(@RequestBody StoreLevel storeLevel) {
        StoreLevel s = storeService.findLevelById(storeLevel.getStoreLevelId());
        if (s == null) {
            return ResultGenerator.genFailResult("您要删除的数据不存在，请稍后再试");
        }
        if (storeService.levelNameExists(storeLevel, true)) {
            return ResultGenerator.genFailResult("当前店铺级别名已经存在，请换一个店铺级别名");
        } else {
            int count = storeService.updateStoreLevel(storeLevel);
            if (count > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("服务器当前正忙，请稍后再试");
            }
        }
    }

    @PostMapping("/storedata")
    @ResponseBody
    public Result storeData() {
        StoreSharedDataListVO vo = new StoreSharedDataListVO();
        try {
            List<StoreAttribDefined> storeAttribDefinedList = storeAttribDefinedService.findAll();
            List<StoreGroupAttrib> storeGroupAttribList = storeGroupAttribService.findAllValidGroupAttrib();
            List<StoreAttribGroup> storeAttribGroupList = storeAttribGroupService.findAllValidAttribGroup();
            List<StoreAttribDefVO> storeAttribDefVOList = PojoConvertUtil.convertPojoList(storeAttribDefinedList, StoreAttribDefVO.class);

            vo.setStoreAttribDefVOList(storeAttribDefVOList);
            vo.setStoreGroupAttribList(storeGroupAttribList);
            vo.setSotreAttribGroupList(storeAttribGroupList);
        } catch (Exception e) {
            LogUtil.getLogger(StoreController.class).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(vo);
    }

    //店铺类型
    @GetMapping("/storetype")
    @ResponseBody
    public Result storeType() {
        List<StoreType> list;
        try {
            list = storeService.findAllStoreType();
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/addstoretype")
    @ResponseBody
    public Result addStoreType(@RequestParam(value = "storeTypeName") String storeTypeName) {

        if (StringUtils.isBlank(storeTypeName)) {
            return ResultGenerator.genFailResult("店铺类型名不能为空");
        }

        StoreType storeType = new StoreType();
        storeType.setStoreTypeName(storeTypeName);
        storeType.setStatus(1);
        if (storeService.typeNameExists(storeType, false)) {
            return ResultGenerator.genFailResult("当前店铺类型名称已经存在，请更换一个类型名称");
        }
        int count = storeService.addStoreType(storeType);
        if (count > 0) {
            SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getStoreTypeSyncEmax()), storeType);
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("服务器当前正忙，请稍后再试");
        }
    }

    @PutMapping("/updatestoretype")
    @ResponseBody
    public Result updateStoreType(@RequestBody StoreType storeType) {
        if (storeService.findTypeById(storeType.getStoreTypeId()) == null) {
            return ResultGenerator.genFailResult("您要删除的数据不存在");
        }
        Condition condition = new Condition(StoreType.class);
        condition.createCriteria().andEqualTo("storeTypeName", storeType.getStoreTypeName()).andNotEqualTo("storeTypeId", storeType.getStoreTypeId());
        if (storeService.typeNameExists(storeType, true)) {
            return ResultGenerator.genFailResult("当前店铺类型名称已经存在，请更换一个类型名称");
        }

        int count = storeService.updateStoreType(storeType);

        if (count > 0) {
            SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getStoreTypeSyncEmax()), storeType);
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("服务器当前正忙，请稍后再试");
        }
    }

    @PostMapping("/getdropdownlist")
    @ResponseBody
    public Result getDropdownList() {
        DropDownListVO dropDownListVO = new DropDownListVO();
        Condition businessCondition = new Condition(BusinessUnit.class);
        try {
            List<Zone> zoneList = zoneService.findZone2ListByCondition();
            List<ZoneVO> zoneVOList = PojoConvertUtil.convertPojoList(zoneList, ZoneVO.class);
            List<City> cityList = cityService.findAll();
            List<StoreLevel> storeLevelList = storeService.findAllValidLevel();
            List<StoreType> storeTypeList = storeService.findAllValiedType();
            List<BusinessUnit> businessUnitList = businessUnitService.findByCondition(businessCondition);
            List<ProductLine> productLineList = productLineService.findProductLine();
            List<CityVO> cityVOList = PojoConvertUtil.convertPojoList(cityList, CityVO.class);
            List<StoreLevelVO> storeLevelVOList = PojoConvertUtil.convertPojoList(storeLevelList, StoreLevelVO.class);
            List<SharedBuyerVO> buyerVOList = buyerService.findSharedBuyerList();
            List<BusinessUnitVO> businessUnitVOList = PojoConvertUtil.convertPojoList(businessUnitList, BusinessUnitVO.class);
            List<Costcenter> costcenterList = costcenterService.checkCostcenterZhs(costcenterService.findAll());
            List<Responsibilitycenter> respcenterList = responsibilitycenterService.checkRespcenterZhs(responsibilitycenterService.findAll());
            List<ManageCenter> manageCenterList =   manageCenterService.findAll();
            List<Company> companyList = companyService.findAll();
            List<StoreVO> compareStoreList = storeService.findStoreList();
            List<ProductLine> productLineLists = productLineService.findProductLine();
            List<Staff> managerList = staffService.getManagerList();
            List<Map<String,String>> list = storeService.findBaseAttributeList();

            List<Map<String,String>> storeOwnerShipList = storeService.findBaseAttributeListById(list,"store_ownership");
            List<Map<String,String>> storeClassList = storeService.findBaseAttributeListById(list,"store_class");
            List<Map<String,String>> storeCategoryList = storeService.findBaseAttributeListById(list,"store_category");
            List<Map<String,String>> logoVersionList = storeService.findBaseAttributeListById(list,"LOGO_version");
            List<Map<String,String>> mallTypeList = storeService.findBaseAttributeListById(list,"mall_type");
            List<Map<String,String>> storeImageCategoryList = storeService.findBaseAttributeListById(list,"store_image_category");
            List<Map<String,String>> installVersionList = storeService.findBaseAttributeListById(list,"install_version");
            List<Map<String,String>> addressTypeList = storeService.findBaseAttributeListById(list,"address_type");
            dropDownListVO.setStoreOwnerShipList(storeOwnerShipList);
            dropDownListVO.setStoreClassList(storeClassList);
            dropDownListVO.setStoreCategoryList(storeCategoryList);
            dropDownListVO.setLogoVersionList(logoVersionList);
            dropDownListVO.setMallTypeList(mallTypeList);
            dropDownListVO.setStoreImageCategoryList(storeImageCategoryList);
            dropDownListVO.setInstallVersionList(installVersionList);
            dropDownListVO.setAddressTypeList(addressTypeList);

            dropDownListVO.setManagerCenterList(manageCenterList);
            dropDownListVO.setCompanyList(companyList);
            dropDownListVO.setManagerList(managerList);
            dropDownListVO.setProductLineLists(productLineLists);

            dropDownListVO.setCompareStoreList(compareStoreList);

            dropDownListVO.setCostcenterList(costcenterList);
            dropDownListVO.setRespcenterList(respcenterList);
            dropDownListVO.setProductLineLists(productLineList);
            dropDownListVO.setBuyerVOList(buyerVOList);
            dropDownListVO.setBusinessUnitVOList(businessUnitVOList);
            dropDownListVO.setZoneVOList(zoneVOList);
            dropDownListVO.setStoreLevelVOList(storeLevelVOList);
            dropDownListVO.setCityVOList(cityVOList);
            dropDownListVO.setStoreTypeList(storeTypeList);
        } catch (Exception e) {
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(dropDownListVO);
    }

    private int checkStoreVO(StoreVO storeVO) {
        if (storeVO == null) {
            return -1;
        }
        //关键字段不能为空
        if (StringUtils.isBlank(storeVO.getStoreId())
                || StringUtils.isBlank(storeVO.getStoreName())
                || StringUtils.isBlank(storeVO.getStoreShortName())
                || StringUtils.isBlank(storeVO.getStoreSpell())
                || storeVO.getStoreTypeId() == null
                || storeVO.getBuyerId() == null
                || storeVO.getStoreLevelId() == null
                || storeVO.getZoneId() == null
                || storeVO.getCityId() == null
                ) {
            return -2;
        }

        Condition condition = new Condition(Store.class);
        condition.createCriteria().andEqualTo("storeId", storeVO.getStoreId()).orEqualTo("storeName", storeVO.getStoreName());
        try {
            int count = storeService.findCountByCondition(condition);
            if (count > 0) {
                return -3;
            }
            return 1;
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return UNKNOWN_EXCEPTION_CODE;
        }
    }

    private String synStoreMessage(StoreVO storeVO,Boolean insert) {
        if (storeVO == null) {
            return "";
        }
        StringBuffer message = new StringBuffer();
        List<Zone> zones = zoneService.findAllZone2List();
        VStores syncStore = vStoresService.findStoreInfo(storeVO.getStoreId());
        VStoresVO vStoresVO = PojoConvertUtil.convertPojo(syncStore,VStoresVO.class);
        vStoresVO.setStorearea(storeVO.getStoreSize());

        Zone zone = zones.stream().filter(z -> z.getZoneId().intValue() == storeVO.getZoneId().intValue()).findFirst().get();
        if (null == zone) {
            vStoresVO.setZoneid(storeVO.getZoneId());
        } else {
            vStoresVO.setZoneid(zone.getPaZoneId());
        }
        vStoresVO.setStoreAttributeList(storeVO.getStoreAttributeList());
        String sync = storeVO.getSyn();
        if (!StringUtils.isBlank(storeVO.getSyn())) {
            if (sync.contains(ITRANSFER)) {
                Boolean flag = SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getStoreItransfer()), vStoresVO);
                if (flag) {
                    message.append("同步到itransfer成功\n");
                } else {
                    message.append("同步到itransfer失败\n");
                }
            }
            if (sync.contains(YXT)) {
                List<SyncZoneVO> zoneList = new ArrayList<>();
                SyncZoneVO syncZoneVO = manageCenterService.getSyncYxtStoreByNo(storeVO.getStoreNo());
                zoneList.add(syncZoneVO);
                if(!zoneList.isEmpty()){
                    if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getZoneSync2Yxt()), zoneList)) {
                        message.append("同步到云学堂成功\n");
                    } else {
                        message.append("同步到云学堂失败\n");
                    }
                }
            }
            if (sync.contains(RFID)) {
                if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getStoreRFID()), vStoresVO)) {
                    message.append("同步到RFID成功\n");
                } else {
                    message.append("同步到RFID失败\n");
                }
            }
            if (sync.contains(WMS)) {
                if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getBuyerWms()), vStoresVO)) {
                    message.append("同步到WMS成功\n");
                } else {
                    message.append("同步到WMS失败\n");
                }
            }

            if (sync.contains(BURGEON)) {
                if(vStoresVO!=null){
                    List<SyncProductLineVO> newProductLineVOList = zoneService.getProductLineByStoreNo(storeVO.getStoreNo());
                    List<SyncProductLineVO> productLineVOList = getProductLineResult(storeVO.getOldProductLineList(),newProductLineVOList,insert);
                    if(!ListUtil.isBlank(productLineVOList)){
                        vStoresVO.setProductLines(productLineVOList);
                    }
                }
                if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getStoreBuygeon()), vStoresVO)) {
                    message.append("同步到伯俊成功\n");
                } else {
                    message.append("同步到伯俊失败\n");
                }
            }
            //同步到小钛 目前测试环境先注释掉，等正式环境插件发布以后再放开
//            if (sync.contains(XT)) {
//                Map<String,Object> map = new HashMap<>();
//                map.put("Store",storeVO.getStoreId());
//                map.put("StoreName",storeVO.getStoreName());
//                map.put("BuyerId",storeVO.getBuyerId());
//                map.put("CanOrig",1);
//                map.put("CanDest",1);
//                map.put("CanRetail",1);
//                map.put("Closed",1);
//                map.put("MarkDis",new BigDecimal("1.11"));
//                map.put("Center",1);
//                map.put("LimitStock",1);
//                map.put("ID", storeVO.getStoreNo());
//                map.put("BespeakState",1);
//                map.put("AssignRate",new BigDecimal("1.11"));
//                map.put("CanTicket",1);
//                map.put("IfTranAcp",1);
//                map.put("IfPTranAcp",1);
//                if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getStoreSync2XiaoTai()), map)) {
//                    message.append("同步到小钛成功\n");
//                } else {
//                    message.append("同步到小钛失败\n");
//                }
//            }
        }
        return message.toString();
    }


    private String checkStoreAttribDef(StoreAttribDefVO storeAttribDefVO, boolean update) {
        if (storeAttribDefVO == null) {
            return "更新属性定义失败";
        }
        if (update && storeAttribDefVO.getStoreAttribDefId() == null) {
            return "属性定义Id不能为空";
        }
        if (StringUtils.isBlank(storeAttribDefVO.getStoreAttribDefCode())) {
            return "属性编码不能为空";
        }
        if (StringUtils.isBlank(storeAttribDefVO.getStoreAttribDefName())) {
            return "属性名称不能为空";
        }

        Condition condition = new Condition(StoreAttribDefined.class);
        if (update) {
            condition.createCriteria().andNotEqualTo("storeAttribDefId", storeAttribDefVO.getStoreAttribDefId());
            condition.and(condition.createCriteria().andEqualTo("storeAttribDefName", storeAttribDefVO.getStoreAttribDefName()).
                    orEqualTo("storeAttribDefCode", storeAttribDefVO.getStoreAttribDefCode()));
        } else {
            condition.createCriteria().andEqualTo("storeAttribDefName", storeAttribDefVO.getStoreAttribDefName()).
                    orEqualTo("storeAttribDefCode", storeAttribDefVO.getStoreAttribDefCode());
        }

        try {
            int count = storeAttribDefinedService.findCountByCondition(condition);
            if (count > 0) {
                return "当前编码或者名称已经存在，请您更改后再尝试";
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return LanguageUtil.getText(-999);
        }

        return null;
    }

    @GetMapping("/findAddrListByStoreNo")
    @ResponseBody
    public Result findAddrListByStoreNo(@RequestParam("id") String storeNo) {
        if (storeNo == null) {
            return ResultGenerator.genFailResult("店铺ID不能为空");
        }
        try {
            List<ViewStoreaddrList> list = viewStoreaddrListService.findAddrListByStoreNo(storeNo);
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception ex) {
            LogUtil.getLogger(StoreController.class).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @GetMapping("/storeAddrTypeList")
    @ResponseBody
    public Result storeAddrTypeList() {
        try {
            List<ViewStoreaddrList> list = viewStoreaddrListService.findStoreAddrTypeList();
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception ex) {
            LogUtil.getLogger(StoreController.class).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    /**
     * 删除地址
     * @param addressId
     * @return
     */
    @DeleteMapping(value = "/deleteaddr/{addressId}")
    @ResponseBody
    public Result deleteaddr(@PathVariable String addressId) {
        int ret = storeAddressService.deleteStoreAddrByAddressId(addressId);
        if (ret > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("系统发生错误，删除失败");
        }
    }

    /**
     * 设置默认地址
     * @param storeAddress
     * @return
     */
    @PutMapping(value = "/setAddrDefault")
    @ResponseBody
    public Result setAddrDefault(@RequestBody StoreAddress storeAddress) {
        int ret = storeAddressService.setAddrDefault(storeAddress);
        if (ret > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("默认地址设置失败");
        }
    }


    /**
     * 添加商铺地址
     * @param storeAddressVO
     * @return
     */
    @PostMapping("/addstoreAddr")
    @ResponseBody
    public Result addstoreAddr(@RequestBody StoreAddressVO storeAddressVO) {
        storeAddressVO.setCreatedBy(SessionManager.getLoginName());
        storeAddressVO.setCreationDate(DateUtil.now());
        storeAddressVO.setLastUpdatedBy(SessionManager.getLoginName());
        storeAddressVO.setLastUpdateDate(DateUtil.now());
        try{
            int ret = storeAddressService.addstoreAddr(storeAddressVO);
            if(ret>0){
                String storeId = storeService.getStoreIdByAddressId(storeAddressVO.getAddressId());
                return getStoreAddressSyncResult(storeId,storeAddressVO.getAddressId());
            }else{
                return ResultGenerator.genFailResult("店铺地址保存失败，同步到伯俊失败");
            }
        }catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            throw new ServiceException("添加店铺地址异常，请稍后刷新页面重试");
        }
    }

    /**
     * 修改商铺地址
     * @param storeAddressVO
     * @return
     */
    @PutMapping("/updateStoreAddr")
    @ResponseBody
    public Result updateStoreAddr(@RequestBody StoreAddressVO storeAddressVO) {
        storeAddressVO.setLastUpdatedBy(SessionManager.getLoginName());
        storeAddressVO.setLastUpdateDate(DateUtil.now());
        try{
            int ret = storeAddressService.updateStoreAddr(storeAddressVO);
            if(ret>0){
                String storeId = storeService.getStoreIdByAddressId(storeAddressVO.getAddressId());
                return getStoreAddressSyncResult(storeId,storeAddressVO.getAddressId());
            }else{
                return ResultGenerator.genFailResult("修改店铺地址失败,同步到伯俊失败");
            }
        }catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            throw new ServiceException("修改店铺地址异常，请稍后刷新页面重试");
        }
    }

    private Result getStoreAddressSyncResult(String storeId,Integer addressId ){
        if(!StringUtils.isEmpty(storeId)){
            VStores syncStore = vStoresService.findStoreInfo(storeId);
            if(syncStore!=null){
                if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getStoreBuygeon()), syncStore)) {
                    SyncStoreVO syncStoreVO = storeAddressService.getSyncAddressById(addressId);
                    if(syncStoreVO==null){
                        return ResultGenerator.genFailResult("店铺地址保存成功，同步到伯俊失败");
                    }
                    if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getStoreAddrSync2Burgeon()), syncStoreVO)) {
                        return ResultGenerator.genSuccessResult();
                    } else {
                        return ResultGenerator.genFailResult("店铺地址保存成功，同步到伯俊失败");
                    }
                } else {
                    return ResultGenerator.genFailResult("店铺地址保存成功，同步到伯俊失败");
                }
            }else{
                return ResultGenerator.genFailResult("店铺地址保存成功，同步到伯俊失败");
            }
        }else{
            return ResultGenerator.genFailResult("店铺地址保存成功，同步到伯俊失败");
        }
    }


    private List<SyncProductLineVO> getProductLineResult(List<SyncProductLineVO> oldProductLineVOList,List<SyncProductLineVO> newProductLineVOList,Boolean insert){
        List<SyncProductLineVO> syncProductLineVOList = null;
        List<String> oldProductLineNoList = new ArrayList<>();
        List<String> newProductLineNoList = new ArrayList<>();
        if(insert){
            if(!ListUtil.isBlank(newProductLineVOList)){
                for(SyncProductLineVO syncProductLineVO : newProductLineVOList){
                    syncProductLineVO.setOperation_status(1);//insertAll
                }
            }
            syncProductLineVOList = newProductLineVOList;
        }else{
            if(ListUtil.isBlank(newProductLineVOList)){//deleteAll
                if(!ListUtil.isBlank(oldProductLineVOList)){
                    for(SyncProductLineVO syncProductLineVO : oldProductLineVOList){
                        syncProductLineVO.setOperation_status(2);
                        syncProductLineVOList.add(syncProductLineVO);
                    }
                }
            }else{
                if(ListUtil.isBlank(oldProductLineVOList)){
                    for(SyncProductLineVO syncProductLineVO : newProductLineVOList){
                        syncProductLineVO.setOperation_status(1);//insertAll
                    }
                    syncProductLineVOList = newProductLineVOList;
                }else{
                    syncProductLineVOList = new ArrayList<>();
                    for(SyncProductLineVO syncProductLineVO : oldProductLineVOList){
                        oldProductLineNoList.add(syncProductLineVO.getProduct_line_no());
                    }
                    for(SyncProductLineVO syncProductLineVO : newProductLineVOList){
                        newProductLineNoList.add(syncProductLineVO.getProduct_line_no());
                    }

                    for(SyncProductLineVO syncProductLineVO : oldProductLineVOList){
                        if(!newProductLineNoList.contains(syncProductLineVO.getProduct_line_no())){
                            syncProductLineVO.setOperation_status(2);//deleteOne
                            syncProductLineVOList.add(syncProductLineVO);
                        }else{
                            syncProductLineVO.setOperation_status(1);//insertOrUpdate
                            syncProductLineVOList.add(syncProductLineVO);
                        }
                    }
                    for(SyncProductLineVO syncProductLineVO : newProductLineVOList){
                        if(!oldProductLineNoList.contains(syncProductLineVO.getProduct_line_no())){
                            syncProductLineVO.setOperation_status(1);//insertOrUpdate
                            syncProductLineVOList.add(syncProductLineVO);
                        }
                    }
                }
            }
        }
        return syncProductLineVOList;
    }

}