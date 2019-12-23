package com.icicle.masterdb.web;

import com.icicle.masterdb.authorization.annotation.Authorization;
import com.icicle.masterdb.config.IcicleConfig;
import com.icicle.masterdb.config.SynConfigEntity;
import com.icicle.masterdb.core.AbstractPageController;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.core.ResultGenerator;
import com.icicle.masterdb.model.*;
import com.icicle.masterdb.pojo.vo.*;
import com.icicle.masterdb.service.*;
import com.icicle.masterdb.util.ExcelUtil;
import com.icicle.masterdb.util.LanguageUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.SyncHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.icicle.masterdb.core.ProjectConstant.UNKNOWN_EXCEPTION_CODE;
import static com.icicle.masterdb.service.constant.ServiceConstant.*;

/**
 * @author wangyuling
 */
@Controller
@RequestMapping("/buyer")
@Authorization
public class BuyerController extends AbstractPageController {
    @Resource
    private BuyerService buyerService;
    @Resource
    private BuyerTypeService buyerTypeservice;
    @Resource
    private BuyerAttribGroupService buyerAttribGroupService;
    @Resource
    private BuyerAttribDefinedService buyerAttribDefinedService;
    @Resource
    private BuyerAttribItemService buyerAttribItemService;
    @Resource
    private BuyerGroupAttribService buyerGroupAttribService;
    @Resource
    private SynConfigEntity synConfigEntity;
    @Resource
    private ZoneService zoneService;
    @Resource
    private IcicleConfig icicleConfig;

    /**
     * 返回页面
     */
    public BuyerController() {
        super("buyer");
    }

    @GetMapping("/buyertype")
    public String buyerType(Map<String, Object> map, HttpServletRequest request) {
        List<BuyerType> list = buyerTypeservice.findAll();
        map.put("list", list);
        return this.freeMarkerViewResult("buyertype", map, request);
    }


    @GetMapping("/buyerlist")
    public String buyerList(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("buyerlist", map, request);
    }

    @GetMapping("/buyerinfo")
    public String buyerInfo(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("buyerinto", map, request);
    }

    //    代理商attribute
    @RequestMapping("/buyerattribute")
    public String buyerAttribute(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("buyerattribs", map, request);
    }

    @GetMapping("/findtype")
    @ResponseBody
    public Result findType() {
        Condition condition = new Condition(BuyerType.class);
        condition.createCriteria().andEqualTo("status", 1);
        List<BuyerType> list = buyerTypeservice.findByCondition(condition);
        if (list != null && list.size() != 0) {
            return ResultGenerator.genSuccessResult(list);
        } else {
            //数据获取为空，请稍后重试
            return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
        }
    }

    //属性
    @PostMapping("/buyershareddata")
    @ResponseBody
    public Result buyerSharedData() {
        BuyerSharedDefVO result = buyerService.getShareData();
        if (result != null) {
            return ResultGenerator.genSuccessResult(result);
        } else {
            //数据获取为空，请稍后重试
            return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
        }
    }

    @PostMapping("/findbylistid")
    @ResponseBody
    public Result findByListId(@RequestParam Integer buyerNo) {
        try {
            if (buyerNo != null && buyerNo != 0) {
                BuyerVO result = buyerService.findByBuyerNo(buyerNo);
                if (result != null) {
                    return ResultGenerator.genSuccessResult(result);
                } else {
                    //数据获取为空，请稍后重试
                    return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
                }
            } else {
                //获取代理商编号出错
                return ResultGenerator.genFailResult(LanguageUtil.getText(1483));
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    //代理商type数据的更新
    @PostMapping("/typeupdate")
    @ResponseBody
    public Result typeUpdate(@RequestParam("buyerTypeId") Integer buyerTypeId, @RequestParam("buyerTypeName") String buyerTypeName, @RequestParam("buyerTypeDesc") String buyerTypeDesc, @RequestParam("status") Integer status) {
        int check = checkType(buyerTypeName, buyerTypeId);
        if (check == DATA_TRANS_ERROR) {
            //获取数据错误，请稍后
            return ResultGenerator.genFailResult(LanguageUtil.getText(1030));
        }
        if (check == ID_REAPT) {
            return ResultGenerator.genFailResult("代理商类型已经存在，不可重复添加！");
        }
        if (check == 1) {
            int ret = buyerTypeservice.typeUpdate(buyerTypeId, buyerTypeName, buyerTypeDesc, status);
            if (ret > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                //编辑失败,请稍候重试
                return ResultGenerator.genFailResult(LanguageUtil.getText(1004));
            }
        } else {
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    //代理商type数据的插入
    @PostMapping("/typeinsert")
    @ResponseBody
    public Result typeInsert(@RequestParam("buyerTypeName") String buyerTypeName, @RequestParam("buyerTypeDesc") String buyerTypeDesc) {
        int check = checkType(buyerTypeName, 0);
        if (check == DATA_TRANS_ERROR) {
            //获取数据错误，请稍后
            return ResultGenerator.genFailResult(LanguageUtil.getText(1030));
        }
        if (check == ID_REAPT) {
            return ResultGenerator.genFailResult("代理商类型已经存在，不可重复添加！");
        }
        if (check == 1) {
            int ret = buyerTypeservice.typeInsert(buyerTypeName, buyerTypeDesc);
            if (ret > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                //新增失败,请稍候重试
                return ResultGenerator.genFailResult(LanguageUtil.getText(1062));
            }
        } else {
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }

    }


    //代理商attribute添加
    @PostMapping("/addattributegroup")
    @ResponseBody
    public Result addAttributeGroup(@RequestParam("buyerAttribGroupName") String buyerAttribGroupName, @RequestParam("buyerAttribGroupCode") String buyerAttribGroupCode) {
        Condition condition = new Condition(BuyerAttribGroup.class);
        condition.createCriteria().andEqualTo("buyerAttribGroupCode", buyerAttribGroupCode);
        int cont = buyerAttribGroupService.findCountByCondition(condition);
        if (cont == 0) {
            int ret = buyerAttribGroupService.insertBuyerAttributeGroup(buyerAttribGroupName, buyerAttribGroupCode);
            if (ret > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                //代理商属性组添加失败,请稍后重试
                return ResultGenerator.genFailResult(LanguageUtil.getText(1309));
            }
        } else {
            //代理商属性组已经存在，不可重复添加
            return ResultGenerator.genFailResult("代理商属性组已经存在，不可重复添加! ");
        }
    }

    @PostMapping("/attrconnect")
    @ResponseBody
    public Result attrConnect(@RequestBody BuyerGroupAttributeListVO buyerGroupAttributeListVO) {
        if (buyerGroupAttributeListVO == null) {
            //数据获取为空，请稍后重试
            return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
        } else {
            try {
                int ret = buyerGroupAttribService.connectAttribute(buyerGroupAttributeListVO);
                if (ret > 0) {
                    return ResultGenerator.genSuccessResult();
                } else {
                    //属性关联失败，请稍后重试
                    return ResultGenerator.genFailResult(LanguageUtil.getText(1519));
                }
            } catch (Exception e) {
                LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
            }
        }
    }


    @DeleteMapping(value = "/deletegroup/{id}")
    @ResponseBody
    public Result deleteGroup(@PathVariable("id") Integer id) {
        if (id != null) {
            BuyerAttribGroup buyerAttribGroup = buyerAttribGroupService.findById(id);
            if (buyerAttribGroup != null) {
                int ret = buyerAttribGroupService.deleteBuyerAttributeGroup(buyerAttribGroup);
                if (ret > 0) {
                    return ResultGenerator.genSuccessResult();
                } else {
                    //删除失败，请稍后重试
                    return ResultGenerator.genFailResult("删除失败，请稍后重试");
                }
            } else {
                return ResultGenerator.genFailResult("删除失败，请稍后重试");
            }
        } else {
            return ResultGenerator.genFailResult("删除数据获取错误，请稍后重试");
        }
    }

    //属性值新增insert
    @PostMapping("/attributeinsert")
    @ResponseBody
    public Result attributeInsert(@RequestBody BuyerAttribDefinedVO buyerAttribDefinedVO) throws Exception {
        int check = checkBuyerAttribDefinedVO(buyerAttribDefinedVO, 0);
        if (check == ID_REAPT) {
            //新增失败,属性名称或编码已经存在
            return ResultGenerator.genFailResult(LanguageUtil.getText(1008));
        }
        if (check == 1) {
            int ret = buyerAttribDefinedService.attributeInsert(buyerAttribDefinedVO);
            if (ret > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                //新增失败,请稍候重试
                return ResultGenerator.genFailResult(LanguageUtil.getText(1062));
            }
        } else if (check == UNKNOWN_EXCEPTION_CODE) {
            //新增失败,请稍候重试
            return ResultGenerator.genFailResult(LanguageUtil.getText(1062));
        } else {
            //新增失败,属性名称为空或重复啦
            return ResultGenerator.genFailResult(LanguageUtil.getText(1306));
        }
    }

    @PutMapping("/attributeupdate")
    @ResponseBody
    public Result attributeUpdate(@RequestBody BuyerAttribDefinedVO buyerAttribDefinedVO) {
        int check = checkBuyerAttribDefinedVO(buyerAttribDefinedVO, buyerAttribDefinedVO.getBuyerAttribDefId());
        if (check == ID_REAPT) {
            //更新失败,属性名称或编码已经存在
            return ResultGenerator.genFailResult(LanguageUtil.getText(1038));
        }
        if (check == 1) {
            int ret = buyerAttribDefinedService.attributeupdate(buyerAttribDefinedVO);
            if (ret > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                //更新失败,请稍候重试
                return ResultGenerator.genFailResult(LanguageUtil.getText(1018));
            }
        } else if (check == UNKNOWN_EXCEPTION_CODE) {
            //更新失败,请稍候重试
            return ResultGenerator.genFailResult(LanguageUtil.getText(1018));
        } else {
            //更新失败,属性名称为空或重复啦
            return ResultGenerator.genFailResult(LanguageUtil.getText(1040));
        }
    }


    @GetMapping("/itemfindall")
    @ResponseBody
    public Result itemFindAll(@RequestParam Integer buyerAttribDefId) {
        List<BuyerAttribItem> buyerAttribItem = buyerAttribItemService.searchItemByDefId(buyerAttribDefId);
        if (buyerAttribItem == null || buyerAttribItem.size() == 0) {
            //查找失败，数据为空
            return ResultGenerator.genFailResult(LanguageUtil.getText(1019));
        } else {
            return ResultGenerator.genSuccessResult(buyerAttribItem);
        }

    }

    //数据datatable传值
    @PostMapping("/buyerlist")
    @ResponseBody
    public DataTableRecord buyerList(@RequestParam("sEcho") String sEcho,
                                     @RequestParam("iDisplayStart") Integer pageIndex,
                                     @RequestParam("iDisplayLength") Integer pageSize,
                                     @RequestParam("buyerName") String buyerName,
                                     @RequestParam("buyerState") String buyerState) {
        DataTableRecord dataTableRecord = buyerService.getBuyerList(sEcho, pageIndex, pageSize, buyerName, buyerState);
        return dataTableRecord;
    }



    @GetMapping("/checkBuyerId")
    @ResponseBody
    public Result checkBuyerId(@RequestParam String buyerId){
        try {
            int count = buyerService.checkBuyerId(buyerId);
            if(count==0){
                return ResultGenerator.genSuccessResult();
            }else{
                return ResultGenerator.genFailResult("客户ID重复");
            }
        }catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }


    @PostMapping("/insertbuyerdetail")
    @ResponseBody
    public Result insertBuyerDetail(@RequestBody BuyerVO buyerVO) {
        try {
            BuyerVO vo= buyerService.insertBuyer(buyerVO);
            List<String> result = new ArrayList<>();
            if (!StringUtils.isEmpty(vo.getBuyerId())) {
                //成功
                String msg = LanguageUtil.getText(1005);
                String syncTypes = buyerVO.getSyn();
                buyerVO.setBuyerId(vo.getBuyerId());
                buyerVO.setBuyerNo(vo.getBuyerNo());
                msg = getSyn(syncTypes, msg, buyerVO);
                result.add(vo.getBuyerNo().toString());
                result.add(msg);
            }
            if (result != null && result.size() != 0) {
                return ResultGenerator.genSuccessResult(result);
            } else {
                //新增失败,请稍候重试
                return ResultGenerator.genFailResult(LanguageUtil.getText(1062));
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PutMapping("/updatebuyerdetail")
    @ResponseBody
    public Result updateBuyerDetail(@RequestBody BuyerVO buyerVO) {
        if (buyerVO != null) {
            try {
                int ret = buyerService.updateBuyer(buyerVO);
                List<String> result = new ArrayList<>();
                if (ret > 0) {
                    //数据已更新
                    String msg = LanguageUtil.getText(1047);
                    String syncTypes = buyerVO.getSyn();
                    msg = getSyn(syncTypes, msg, buyerVO);
                    result.add(Integer.toString(ret));
                    result.add(msg);
                }
                if (result != null && result.size() != 0) {
                    return ResultGenerator.genSuccessResult(result);
                } else {
                    //更新失败,请稍候重试
                    return ResultGenerator.genFailResult(LanguageUtil.getText(1018));
                }
            } catch (Exception e) {
                LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
                return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
            }
        } else {
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("/buyerattribdefined")
    @ResponseBody
    public Result buyerAttribDefined() {
        BuyerAttribDefItemVO list = buyerAttribDefinedService.loadDefine();
        if (list != null) {
            return ResultGenerator.genSuccessResult(list);
        } else {
            //数据获取为空，请稍后重试
            return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
        }
    }

    @GetMapping("/getZoneList")
    @ResponseBody
    public Result getZoneList(@RequestParam("manageCenterId") String manageCenterId) {
        List<ZoneVO> zoneList = zoneService.findZoneListByManageCenterId(manageCenterId);
        if (zoneList != null) {
            return ResultGenerator.genSuccessResult(zoneList);
        } else {
            //数据获取为空，请稍后重试
            return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
        }
    }

    @PostMapping("/getdropdownlist")
    @ResponseBody
    public Result getDropdownList() {
        BuyerDropDownVO result = buyerService.getDropDownList();
        if (result != null) {
            return ResultGenerator.genSuccessResult(result);
        } else {
            //数据获取为空，请稍后重试
            return ResultGenerator.genFailResult(LanguageUtil.getText(1049));
        }
    }

    /**
     * 代理商列表导出excel
     * @param response
     * @param buyerNameOrId
     * @param buyerState
     */
    @PostMapping("/downLoadBuyerExcel")
    public void downLoadBuyerExcel(HttpServletResponse response,
                     @RequestParam("buyerNameOrId") String buyerNameOrId,
                     @RequestParam("buyerState") String buyerState) {
        OutputStream os = null;
        try {
            List<BuyerVO> list = buyerService.findByQueryCondition(buyerNameOrId,buyerState);
            Workbook workbook = buyerService.exportBuyerExcel(list);
            String tableName = "代理商列表";
            ExcelUtil.downloadExcelWithStream(response,workbook,tableName);
        } catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.getLogger(BuyerController.class).error(ex.getMessage());
        }finally {
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private int checkBuyerAttribDefinedVO(BuyerAttribDefinedVO buyerAttribDefinedVO, int buyerAttribDefId) {
        //传入数据格式不对无法解析
        if (buyerAttribDefinedVO == null) {
            return -1;
        }
        //名称为空
        if (StringUtils.isBlank(buyerAttribDefinedVO.getBuyerAttribDefName())) {
            return DATA_TRANS_ERROR;
        }
        Condition condition = new Condition(BuyerAttribDefined.class);
        if (buyerAttribDefId > 0) {
            condition.createCriteria().andNotEqualTo("buyerAttribDefId", buyerAttribDefId);
            condition.and(condition.createCriteria().andEqualTo("buyerAttribDefName", buyerAttribDefinedVO.getBuyerAttribDefName())
                    .orEqualTo("buyerAttribDefCode", buyerAttribDefinedVO.getBuyerAttribDefCode()));

        } else {
            condition.createCriteria().andEqualTo("buyerAttribDefName", buyerAttribDefinedVO.getBuyerAttribDefName())
                    .orEqualTo("buyerAttribDefCode", buyerAttribDefinedVO.getBuyerAttribDefCode());

        }
        try {
            int count = buyerAttribDefinedService.findCountByCondition(condition);
            return checkResult(count);
        } catch (Exception ex) {
            return UNKNOWN_EXCEPTION_CODE;
        }
    }

    private int checkbuyer(BuyerVO buyer, String buyerid) {
        if (buyer == null) {
            return -1;
        }
        Condition condition = new Condition(Buyer.class);
        if (buyerid != null) {
            //新增 判断id是否重复
            condition.createCriteria().andEqualTo("buyerId", buyerid);
        }
        try {
            int count = buyerService.findCountByCondition(condition);
            return checkResult(count);
        } catch (Exception ex) {
            return UNKNOWN_EXCEPTION_CODE;
        }

    }

    private int checkType(String buyerTypeName, Integer buyerTypeId) {
        if (StringUtils.isBlank(buyerTypeName)) {
            return DATA_TRANS_ERROR;
        }
        Condition condition = new Condition(BuyerType.class);
        if (buyerTypeId != 0) {
            condition.createCriteria().andNotEqualTo("buyerTypeId", buyerTypeId).
                    andEqualTo("buyerTypeName", buyerTypeName);
        } else {
            condition.createCriteria().andEqualTo("buyerTypeName", buyerTypeName);
        }
        try {
            int result = buyerTypeservice.findCountByCondition(condition);
            return checkResult(result);
        } catch (Exception ex) {
            return UNKNOWN_EXCEPTION_CODE;
        }
    }

    /**
     * 获取同步数据
     */
    private String getSyn(String syncTypes, String msg, BuyerVO buyerVO) {
        if (!StringUtils.isBlank(syncTypes)) {
            boolean syncFlag = true;
            if (syncTypes.contains(EMAX)) {
                //同步到旧博俊
                if(buyerVO!=null){
                    if(SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getBuyerEmax()), buyerVO)){
                        String message = LanguageUtil.getText(1024);
                        msg = String.format("%s\n%s", message, msg);
                    }else{
                        String message = LanguageUtil.getText(1568);
                        msg = String.format("%s\n%s", message, msg);
                        LogUtil.getLogger(this.getClass()).info("同步到旧伯俊失败");
                        syncFlag = false;
                    }
                }
            }

            if (syncTypes.contains(ITRANSFER)) {
                //同步到货品平台
                if(buyerVO!=null){
                    if(SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getBuyerItransfer()), buyerVO)){
                        String value = LanguageUtil.getText(1023);
                        msg = String.format("%s\n%s", value, msg);
                    }else{
                        String value = LanguageUtil.getText(1569);
                        msg = String.format("%s\n%s", value, msg);
                        LogUtil.getLogger(this.getClass()).info("同步到货品平台失败");
                        syncFlag = false;
                    }
                }
            }

            if (syncTypes.contains(RFID)) {
                SyncBuyerVO syncBuyerVO = buyerService.getSyncBurgeonBuyerById(buyerVO.getBuyerId());
                //同步到RFID
                if(syncBuyerVO!=null) {
                    if(SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getBuyerRFID()), syncBuyerVO)){
                        String value = LanguageUtil.getText(1565);
                        msg = String.format("%s\n%s", value, msg);
                    }else{
                        String value = LanguageUtil.getText(1570);
                        msg = String.format("%s\n%s", value, msg);
                        LogUtil.getLogger(this.getClass()).info("同步到RFID失败");
                        syncFlag = false;
                    }
                }
            }

            if (syncTypes.contains(BURGEON)) {
                SyncBuyerVO syncBuyerVO = buyerService.getSyncBurgeonBuyerById(buyerVO.getBuyerId());
                //同步到新伯俊
                if(syncBuyerVO!=null){
                    if(SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getBuyerBurgeon()), syncBuyerVO)){
                        String value = LanguageUtil.getText(1567);
                        msg = String.format("%s\n%s", value, msg);
                    }else{
                        String value = LanguageUtil.getText(1571);
                        msg = String.format("%s\n%s", value, msg);
                        LogUtil.getLogger(this.getClass()).info("同步到新伯俊失败");
                        syncFlag = false;
                    }
                 }
            }

            if (syncTypes.contains(YXT)) {
                List<SyncZoneVO> zoneList = new ArrayList<>();
                SyncZoneVO syncZoneVO = buyerService.getSyncYxtStoreByNo(buyerVO.getBuyerId());
                zoneList.add(syncZoneVO);
                //同步到云学堂（代理商模式为代理）
                if(syncZoneVO!=null && !StringUtils.isEmpty(syncZoneVO.getParentID()) && syncZoneVO.getBuyerPattern().equals("2")){
                    if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getZoneSync2Yxt()), zoneList)) {
                        String value = LanguageUtil.getText(1572);
                        msg = String.format("%s\n%s", value, msg);
                    } else {
                        String value = LanguageUtil.getText(1573);
                        msg = String.format("%s\n%s", value, msg);
                        LogUtil.getLogger(this.getClass()).info("同步到云学堂失败");
                        syncFlag = false;
                    }
                }
            }

            //同步到小钛 目前测试环境先注释掉，等正式环境插件发布以后再放开
//            if (syncTypes.contains(XT)) {
//                Map<String,Object> map = new HashMap<>();
//                map.put("BuyerId",buyerVO.getBuyerId());
//                map.put("BuyerType",1);
//                map.put("BuyerUp","00001");
//                map.put("BuyerName",buyerVO.getBuyerName());
//                map.put("ReturnStyle",1);
//                map.put("StopStatus",1);
//                map.put("SupDiscount",new BigDecimal("1.11"));
//                map.put("OrgType",1);
//                map.put("CanPlat",1);
//                map.put("ID",buyerVO.getBuyerNo());
//                map.put("BelongSign",1);
//                map.put("IsAutoCharge",1);
//                map.put("IsAutoAccept",1);
//                if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getBuyerSync2XiaoTai()), map)) {
//                    String value = "同步到小钛成功";
//                    msg = String.format("%s\n%s", value, msg);
//                } else {
//                    String value = "同步到小钛失败";
//                    msg = String.format("%s\n%s", value, msg);
//                    syncFlag = false;
//                }
//            }
            if(!syncFlag){
                String value = LanguageUtil.getText(1574);
                msg = "";
                msg = String.format("%s\n%s", value, msg);
            }
        }
        return msg;
    }

    /**
     * 验证时返回数据两种情况
     *
     * @param count
     * @return
     */
    public int checkResult(Integer count) {
        if (count > 0) {
            //code存在，更新数据
            return -3;
        }
        return 1;
    }
}