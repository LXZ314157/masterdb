package com.icicle.masterdb.web;

import com.alibaba.fastjson.JSON;
import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.authorization.annotation.Authorization;
import com.icicle.masterdb.config.SynConfigEntity;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.core.ResultGenerator;
import com.icicle.masterdb.core.ServiceException;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ManageCenter;
import com.icicle.masterdb.model.ProDepartment;
import com.icicle.masterdb.model.Staff;
import com.icicle.masterdb.model.Zone;
import com.icicle.masterdb.pojo.vo.*;
import com.icicle.masterdb.service.*;
import com.icicle.masterdb.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.icicle.masterdb.core.ProjectConstant.UNKNOWN_EXCEPTION_CODE;

/**
 * @author liumingming
 * @version 2017-10-25 15:48:34.
 */
@Controller
@RequestMapping("/zone")
@Authorization
public class ZoneController {
    @Resource
    private ZoneService zoneService;

    @Resource
    private StaffService staffService;

    @Resource
    private BusinessUnitService businessUnitService;

    @Resource
    private ProDepartmentService proDepartmentService;
    @Resource
    private ManageCenterService manageCenterService;

    @Resource
    private SynConfigEntity synConfigEntity;


    @GetMapping("/zonelist")
    @ResponseBody
    public Result zoneList() {
        try {

            ZoneListVO zoneListVO = new ZoneListVO();

            List<Zone> zoneList = zoneService.findZone2List();
            zoneListVO.setZoneList(PojoConvertUtil.convertPojoList(zoneList, ZoneVO.class));

            List<ManageCenter> mngtList = manageCenterService.findAll();
            zoneListVO.setMngList(PojoConvertUtil.convertPojoList(mngtList, ManageCenterVO.class));

            List<Staff> managerList = staffService.getManagerList();
            zoneListVO.setManagerList(managerList);

            return ResultGenerator.genSuccessResult(zoneListVO);
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            throw new ServiceException("获取区域列表出错，请稍后刷新页面重试");
        }
    }

    @PostMapping("/addmng")
    @ResponseBody
    public Result addMng(@RequestBody ManageCenterVO manageCenterVO) {
        ManageCenter manageCenter = PojoConvertUtil.convertPojo(manageCenterVO, ManageCenter.class);
        manageCenter.setCreatedBy(SessionManager.getLoginName());
        manageCenter.setCreationDate(DateUtil.now());
        try {
            int managerCenterId = manageCenterService.saveManageCenter(manageCenter);
            if(managerCenterId<=0){
                return ResultGenerator.genFailResult("新增失败,请稍候重试");
            }
            ManageCenterVO vo = PojoConvertUtil.convertPojo(manageCenter, ManageCenterVO.class);
            StringBuffer message = new StringBuffer();
            List<SyncZoneVO> syncZoneVOList = new ArrayList<>();
            SyncZoneVO syncZoneVO = manageCenterService.getSyncYxtMngById(vo.getManagerCenterId());
            syncZoneVOList.add(syncZoneVO);
            if(syncZoneVO!=null){
                if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getZoneSync2Yxt()), syncZoneVOList)) {
                    message.append("同步到云学堂成功\n");
                } else {
                    message.append("同步到云学堂失败\n");
                }
            }
            SyncPropertyVO syncManagecenterVO = manageCenterService.getSyncBurgeonManageCenterById(vo.getManagerCenterId());
            if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getPropertySyncBurgeon()), syncManagecenterVO)) {
                message.append("同步到伯俊成功\n");
            } else {
                message.append("同步到伯俊失败\n");
            }
            vo.setMessage(message.toString());
            return ResultGenerator.genSuccessResult(vo);
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult("新增失败,请稍候重试");
        }
    }

    @PutMapping("/updatemng")
    @ResponseBody
    public Result updateMng(@RequestBody ManageCenterVO manageCenterVO) {
        int check = checkManageCenterVO(manageCenterVO, manageCenterVO.getManagerCenterId());
        if (check == 1) {
            ManageCenter manageCenter = PojoConvertUtil.convertPojo(manageCenterVO, ManageCenter.class);
            manageCenter.setLastUpdatedBy(SessionManager.getLoginName());
            manageCenter.setLastUpdateDate(DateUtil.now());
            try {
                int updateCount = manageCenterService.updateManagerCenter(manageCenter);
                if(updateCount<=0){
                    return ResultGenerator.genFailResult("编辑失败,请稍候重试");
                }
                StringBuffer message = new StringBuffer();
                ManageCenterVO vo = PojoConvertUtil.convertPojo(manageCenter, ManageCenterVO.class);
                List<SyncZoneVO> syncZoneVOList = new ArrayList<>();
                SyncZoneVO syncZoneVO = manageCenterService.getSyncYxtMngById(vo.getManagerCenterId());
                syncZoneVOList.add(syncZoneVO);
                if(syncZoneVO!=null){
                    if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getZoneSync2Yxt()), syncZoneVOList)) {
                        message.append("同步到云学堂成功\n");
                    } else {
                        message.append("同步到云学堂失败\n");
                    }
                }
                SyncPropertyVO syncManagecenterVO = manageCenterService.getSyncBurgeonManageCenterById(vo.getManagerCenterId());
                if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getPropertySyncBurgeon()), syncManagecenterVO)) {
                    message.append("同步到伯俊成功\n");
                } else {
                    message.append("同步到伯俊失败\n");
                }
                vo.setMessage(message.toString());
                return ResultGenerator.genSuccessResult(vo);
            } catch (Exception ex) {
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
                return ResultGenerator.genFailResult("编辑失败,请稍候重试");
            }
        } else if (check == UNKNOWN_EXCEPTION_CODE) {
            return ResultGenerator.genFailResult("编辑失败,请稍候重试");

        } else {
            return ResultGenerator.genFailResult("编辑失败,现场管理中心名称为空或重复啦");
        }
    }



    @PostMapping("/addzone")
    @ResponseBody
    public Result addZone(@RequestBody ZoneVO zoneVO) {
        Zone zone = PojoConvertUtil.convertPojo(zoneVO, Zone.class);
        zone.setStatus(1);
        zone.setCreatedBy(SessionManager.getLoginName());
        zone.setCreationDate(DateUtil.now());
        try {
            int ret = zoneService.saveZone(zone);
            if (ret > 0) {
                ZoneVO vo = PojoConvertUtil.convertPojo(zone, ZoneVO.class);
                StringBuffer message = new StringBuffer();
                //如果是小区
                if (vo.getZoneLevel()==2){
                    List<Integer> zoneIdList = Arrays.asList(vo.getZoneId());
                    message.append(getSync2YxtMessage(zoneIdList));
                }
                SyncPropertyVO syncPropertyVO = manageCenterService.getSyncBurgeonZoneById(vo.getZoneId());
                syncPropertyVO.setDescription(syncPropertyVO.getPropertyName());
                if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getPropertySyncBurgeon()), syncPropertyVO)) {
                    message.append("同步到伯俊成功\n");
                } else {
                    message.append("同步到伯俊失败\n");
                }
                vo.setMessage(message.toString());
                return ResultGenerator.genSuccessResult(vo);
            } else {
                return ResultGenerator.genFailResult("新增失败,请稍候重试");
            }
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult("新增失败,请稍候重试");
        }
    }

    @PutMapping("/updatezone")
    @ResponseBody
    @LogAction(logDesc = "更新区域")
    public Result updateZone(@RequestBody ZoneVO zoneVO) {
        Zone zone = PojoConvertUtil.convertPojo(zoneVO, Zone.class);
        zone.setLastUpdatedBy(SessionManager.getLoginName());
        zone.setLastUpdateDate(DateUtil.now());
        try {
            StringBuffer message = new StringBuffer();
            int ret = zoneService.updateZone(zone);
            if (ret > 0) {
                ZoneVO vo = PojoConvertUtil.convertPojo(zone, ZoneVO.class);
                vo.setStatus(1);
                if(vo.getPaZoneId()==0){
                  int updCount = zoneService.updateChildrenZone(vo.getManageCenterId().toString(),vo.getZoneId().toString());
                  if(updCount>0){
                      List<Zone> list = zoneService.selectChildrenZone(vo.getZoneId().toString());
                      if(!ListUtil.isBlank(list)){
                          vo.setChildZoneList(list);
                      }
                      List<Integer> zoneIdList = list.stream().map(Zone::getZoneId).collect(Collectors.toList());
                      message.append(getSync2YxtMessage(zoneIdList));
                  }
                }
                //如果是小区
                if (vo.getZoneLevel()==2){
                    List<Integer> zoneIdList = Arrays.asList(vo.getZoneId());
                    List<SyncZoneVO> zoneList = manageCenterService.getSyncYxtZoneById(zoneIdList);
                    if(!ListUtil.isBlank(zoneList)){
                        if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getZoneSync2Yxt()), zoneList)) {
                            message.append("同步到云学堂成功\n");
                        } else {
                            message.append("同步到云学堂失败\n");
                        }
                    }
                }

                SyncPropertyVO syncPropertyVO = manageCenterService.getSyncBurgeonZoneById(vo.getZoneId());
                syncPropertyVO.setDescription(syncPropertyVO.getPropertyName());
                if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getPropertySyncBurgeon()), syncPropertyVO)) {
                    message.append("同步到伯俊成功\n");
                } else {
                    message.append("同步到伯俊失败\n");
                }

                vo.setMessage(message.toString());
                return ResultGenerator.genSuccessResult(vo);
            } else {
                return ResultGenerator.genFailResult("编辑失败,请稍候重试");
            }
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult("编辑失败,请稍候重试");
        }
    }

    @DeleteMapping("/deletezone/{id}")
    @ResponseBody
    @LogAction(logDesc = "删除区域")
    public Result deletezone(@PathVariable Integer id) {
        Zone zone;
        try {
            zone = zoneService.findZoneById(id);
            if (zone == null) {
                return ResultGenerator.genFailResult("删除失败,该分区不存在");
            }
            if (zone.getStatus() != 1) {
                return ResultGenerator.genFailResult("删除失败,该分区已经被删除");
            }
            if (zone.getZoneLevel() == 1) {
                return ResultGenerator.genFailResult("删除失败,该区域不能直接删除");
            }
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult("删除失败,请稍候重试");
        }
        Zone updateZone = new Zone();
        updateZone.setZoneId(id);
        updateZone.setStatus(0);
        updateZone.setLastUpdatedBy(SessionManager.getLoginName());
        updateZone.setLastUpdateDate(DateUtil.now());
        try {
            if(zoneService.findStoreByZoneId(id.toString())==0){
                int ret = zoneService.updateZoneStatus(updateZone);
                if (ret > 0) {
                    zone.setStatus(0);
                    ZoneVO vo = PojoConvertUtil.convertPojo(zone, ZoneVO.class);
                    //云学堂删除组织
                    String [] array = new String [] {String.valueOf(id)};
                    if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getDelOus()), JSON.toJSONString(array))) {
                        return ResultGenerator.genSuccessResult();
                    } else {
                        return ResultGenerator.genFailResult("删除成功,但是同步到云学堂失败，请稍候重试");
                    }
                } else {
                    return ResultGenerator.genFailResult("删除失败,请稍候重试");
                }
            }else{
                return ResultGenerator.genFailResult("该分区下有店铺，不能删除！");
            }
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult("删除失败,请稍候重试");
        }
    }


    private String getSync2YxtMessage(List<Integer> zoneIdList){
        String message = "";
        try{
            if(!ListUtil.isBlank(zoneIdList)){
                List<SyncZoneVO> zoneList = manageCenterService.getSyncYxtZoneById(zoneIdList);
                if(!ListUtil.isBlank(zoneList)){
                    if (SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getZoneSync2Yxt()), zoneList)) {
                        message = "同步到云学堂成功\n";
                    } else {
                        message = "同步到云学堂失败\n";
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            message = "同步到云学堂失败\n";
            return message;
        }
        return message;
    }

    private int checkManageCenterVO(ManageCenterVO manageCenterVO, Integer mngId) {
        //传入数据格式不对无法解析
        if (manageCenterVO == null) {
            return -1;
        }
        //名称为空
        if (StringUtils.isBlank(manageCenterVO.getManagerCenterName())) {
            return -2;
        }

        Condition condition = new Condition(ManageCenter.class);
        if (mngId > 0) {
            condition.createCriteria().andEqualTo("managerCenterName", manageCenterVO.getManagerCenterName())
                    .andNotEqualTo("managerCenterId", mngId);
        } else {
            condition.createCriteria().andEqualTo("managerCenterName", manageCenterVO.getManagerCenterName());
        }
        try {
            int count = manageCenterService.findCountByCondition(condition);
            if (count > 0) {
                //违反了名称唯一性约束
                return -3;
            }
            return 1;
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return UNKNOWN_EXCEPTION_CODE;
        }
    }

    private int checkProDepartmentVO(ProDepartmentVO proDepartmentVO, Integer depId) {
        //传入数据格式不对无法解析
        if (proDepartmentVO == null) {
            return -1;
        }
        //名称为空
        if (StringUtils.isBlank(proDepartmentVO.getProDeptName())) {
            return -2;
        }

        Condition condition = new Condition(ProDepartment.class);
        if (depId > 0) {
            condition.createCriteria().andEqualTo("proDeptName", proDepartmentVO.getProDeptName())
                    .andNotEqualTo("proDeptId", depId);
        } else {
            condition.createCriteria().andEqualTo("proDeptName", proDepartmentVO.getProDeptName());
        }
        try {
            int count = proDepartmentService.findCountByCondition(condition);
            if (count > 0) {
                //违反了名称唯一性约束
                return -3;
            }
            return 1;
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return UNKNOWN_EXCEPTION_CODE;
        }
    }

    private int checkZoneVO(ZoneVO zoneVO, Integer zoneId) {
        //传入数据格式不对无法解析
        if (zoneVO == null) {
            return -1;
        }
        //名称为空
        if (StringUtils.isBlank(zoneVO.getZoneName())) {
            return -2;
        }

        Condition condition = new Condition(Zone.class);
        if (zoneId > 0) {
            condition.createCriteria().andEqualTo("zoneName", zoneVO.getZoneName())
                    .andNotEqualTo("zoneId", zoneId);
        } else {
            condition.createCriteria().andEqualTo("zoneName", zoneVO.getZoneName());
        }
        try {
            int count = zoneService.findCountByCondition(condition);
            if (count > 0) {
                //违反了名称唯一性约束
                return -3;
            }
            return 1;
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return UNKNOWN_EXCEPTION_CODE;
        }
    }
}