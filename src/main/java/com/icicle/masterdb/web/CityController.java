package com.icicle.masterdb.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.authorization.annotation.Authorization;
import com.icicle.masterdb.config.SynConfigEntity;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.core.ResultGenerator;
import com.icicle.masterdb.core.ServiceException;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.*;
import com.icicle.masterdb.pojo.vo.*;
import com.icicle.masterdb.service.*;
import com.icicle.masterdb.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

import static com.icicle.masterdb.core.ProjectConstant.UNKNOWN_EXCEPTION_CODE;

/**
 * @author liumingming
 */
@Controller
@RequestMapping("/city")
@Authorization
public class CityController {
    @Resource
    private SynConfigEntity synConfigEntity;
    @Resource
    private CityService cityService;
    @Resource
    private ProvinceService provinceService;
    @Resource
    private ViewCityService viewCityService;
    @Resource
    private CityLevelService cityLevelService;
    @Resource
    private AreaService areaService;

    @PostMapping("/citylist")
    @ResponseBody
    public DataTableRecord cityList(@RequestParam("sEcho") String sEcho,
                                    @RequestParam("iDisplayStart") Integer pageIndex,
                                    @RequestParam("iDisplayLength") Integer pageSize,
                                    @RequestParam("cityName") String cityName,
                                    @RequestParam("areaCityId") String areaCityId,
                                    @RequestParam("countyId") String countyId,
                                    @RequestParam("cityLevelId") String cityLevelId) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);

        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        Condition condition = new Condition(ViewCity.class);
        Example.Criteria criteria = condition.createCriteria();
        if (!StringUtils.isBlank(cityName)) {
            criteria.andLike("cityName", cityName);
        }

        if (!StringUtils.isBlank(areaCityId)) {
            criteria.orEqualTo("cityId", areaCityId);
        }

        if (!StringUtils.isBlank(countyId)) {
            criteria.orEqualTo("cityId", countyId);
        }
        if (!StringUtils.isBlank(cityLevelId)) {
            Integer levelId;
            try {
                levelId = Integer.valueOf(cityLevelId);
            } catch (NumberFormatException ex) {
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
                levelId = 0;
            }
            if (levelId != 0) {
                criteria.andEqualTo("cityLevelId", levelId);
            }
        }
        try {
            List<ViewCity> list = viewCityService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
            Integer total = (int) pageInfo.getTotal();
            dataTableRecord.setITotalRecords(total);
            dataTableRecord.setITotalDisplayRecords(total);
            dataTableRecord.setAaData(pageInfo.getList());
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
        }
        return dataTableRecord;
    }

    @GetMapping("/provincelist")
    @ResponseBody
    public Result provinceList() {
        try {
            List<Province> list = provinceService.findAll();
            List<ProvinceVO> voList = PojoConvertUtil.convertPojoList(list, ProvinceVO.class);
            return ResultGenerator.genSuccessResult(voList);
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            throw new ServiceException("获取省份列表出错，请稍后刷新页面重试");
        }
    }

    @GetMapping("/areacitylist")
    @ResponseBody
    public Result areacitylist() {
        try {
            List<City> list = cityService.findAreaCityList();
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            throw new ServiceException("获取地市列表出错，请稍后刷新页面重试");
        }
    }
    @GetMapping("/countylist")
    @ResponseBody
    public Result countylist() {
        try {
            List<City> list = cityService.findCountyList();
            return ResultGenerator.genSuccessResult(list);
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            throw new ServiceException("获取区县列表出错，请稍后刷新页面重试");
        }
    }

    @PutMapping("/updatecity")
    @ResponseBody
    public Result updateCity(@RequestBody CityVO cityVO) {
        if (cityVO != null) {
            City city = PojoConvertUtil.convertPojo(cityVO, City.class);
            city.setLastUpdatedBy(SessionManager.getLoginName());
            city.setLastUpdateDate(DateUtil.now());
            try {
                int ret = cityService.update(city);
                if (ret > 0) {
                    SyncCityVO syncCityVO = cityService.getSyncCityById(city.getCityId());

                   if(SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getCitySyncEmax()), cityVO)
                           && SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getCitySyncBurgeon()), syncCityVO)){
                       return ResultGenerator.genSuccessResult();
                   }
                } else {
                    return ResultGenerator.genFailResult("编辑失败,请稍候重试");
                }
            } catch (Exception ex) {
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
                return ResultGenerator.genFailResult("编辑失败,请稍候重试");
            }
        } else {
            return ResultGenerator.genFailResult("编辑失败,请仔细检查输入值");

        }
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/citylevellist")
    @ResponseBody
    public Result cityLevelList() {
        try {
            List<CityLevel> list = cityLevelService.findAll();
            List<CityLevelVO> voList = PojoConvertUtil.convertPojoList(list, CityLevelVO.class);
            return ResultGenerator.genSuccessResult(voList);
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            throw new ServiceException("获取城市级别列表出错，请稍后刷新页面重试");
        }
    }

    @PostMapping("/addcitylevel")
    @ResponseBody
    public Result addCityLevel(@RequestBody CityLevelVO cityLevelVO) {
        int check = checkCityLevelVO(cityLevelVO, 0);
        if (check == 1) {
            CityLevel cityLevel = PojoConvertUtil.convertPojo(cityLevelVO, CityLevel.class);
            cityLevel.setStatus(1);
            cityLevel.setCreatedBy(SessionManager.getLoginName());
            cityLevel.setCreationDate(DateUtil.now());
            try {
                int ret = cityLevelService.saveCityLevel(cityLevel);
                if (ret > 0) {
                    CityLevelVO vo = PojoConvertUtil.convertPojo(cityLevel, CityLevelVO.class);
                    return ResultGenerator.genSuccessResult(vo);
                } else {
                    return ResultGenerator.genFailResult("新增失败,请稍候重试");
                }
            } catch (Exception ex) {
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
                return ResultGenerator.genFailResult("新增失败,请稍候重试");
            }
        } else if (check == UNKNOWN_EXCEPTION_CODE) {
            return ResultGenerator.genFailResult("新增失败,请稍候重试");

        } else {
            return ResultGenerator.genFailResult("新增失败,城市级别名称为空或重复啦");
        }
    }

    @PutMapping("/updatecitylevel")
    @ResponseBody
    public Result updateCityLevel(@RequestBody CityLevelVO cityLevelVO) {
        int check = checkCityLevelVO(cityLevelVO, cityLevelVO.getCityLevelId());
        if (check == 1) {
            CityLevel cityLevel = PojoConvertUtil.convertPojo(cityLevelVO, CityLevel.class);
            cityLevel.setLastUpdatedBy(SessionManager.getLoginName());
            cityLevel.setLastUpdateDate(DateUtil.now());
            try {
                int ret = cityLevelService.updateCityLevel(cityLevel);
                if (ret > 0) {
                    CityLevelVO vo = PojoConvertUtil.convertPojo(cityLevel, CityLevelVO.class);
                    return ResultGenerator.genSuccessResult(vo);
                } else {
                    return ResultGenerator.genFailResult("编辑失败,请稍候重试");
                }
            } catch (Exception ex) {
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
                return ResultGenerator.genFailResult("编辑失败,请稍候重试");
            }
        } else if (check == UNKNOWN_EXCEPTION_CODE) {
            return ResultGenerator.genFailResult("编辑失败,请稍候重试");

        } else {
            return ResultGenerator.genFailResult("编辑失败,城市级别名称为空或重复啦");
        }
    }

    @DeleteMapping("/deletecitylevel/{id}")
    @ResponseBody
    public Result deleteCityLevel(@PathVariable Integer id) {
        CityLevel level;
        try {
            level = cityLevelService.findById(id);
            if (level == null) {
                return ResultGenerator.genFailResult("删除失败,该城市级别不存在");
            }
            if (level.getStatus() != 1) {
                return ResultGenerator.genFailResult("删除失败,该城市级别已经被删除");
            }
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult("删除失败,请稍候重试");
        }
        level = new CityLevel();
        level.setCityLevelId(id);
        level.setStatus(0);
        level.setLastUpdatedBy(SessionManager.getLoginName());
        level.setLastUpdateDate(DateUtil.now());
        try {
            int ret = cityLevelService.update(level);
            if (ret > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("删除失败,请稍候重试");
            }
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult("删除失败,请稍候重试");

        }
    }

    @GetMapping("/arealist")
    @ResponseBody
    public Result areaList() {
        try {
            List<Area> list = areaService.findAll();
            List<AreaVO> voList = PojoConvertUtil.convertPojoList(list, AreaVO.class);
            return ResultGenerator.genSuccessResult(voList);
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            throw new ServiceException("获取地理区域列表出错，请稍后刷新页面重试");
        }
    }

    @PostMapping("/addarea")
    @ResponseBody
    public Result addArea(@RequestBody AreaVO areaVO) {
        int check = checkAreaVO(areaVO, 0);
        if (check == 1) {
            Area area = PojoConvertUtil.convertPojo(areaVO, Area.class);
            area.setStatus(1);
            area.setCreatedBy(SessionManager.getLoginName());
            area.setCreationDate(DateUtil.now());
            try {
                int ret = areaService.saveArea(area);
                if (ret > 0) {
                    AreaVO vo = PojoConvertUtil.convertPojo(area, AreaVO.class);
                    SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getAreaSyncEmax()), vo);
                    return ResultGenerator.genSuccessResult(vo);
                } else {
                    return ResultGenerator.genFailResult("新增失败,请稍候重试");
                }
            } catch (Exception ex) {
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
                return ResultGenerator.genFailResult("新增失败,请稍候重试");
            }
        } else if (check == UNKNOWN_EXCEPTION_CODE) {
            return ResultGenerator.genFailResult("新增失败,请稍候重试");

        } else {
            return ResultGenerator.genFailResult("新增失败,地理区域编码为空或重复啦");
        }
    }

    @PutMapping("/updatearea")
    @ResponseBody
    public Result updateArea(@RequestBody AreaVO areaVO) {
        int check = checkAreaVO(areaVO, areaVO.getAreaId());
        if (check == 1) {
            Area area = PojoConvertUtil.convertPojo(areaVO, Area.class);
            area.setLastUpdatedBy(SessionManager.getLoginName());
            area.setLastUpdateDate(DateUtil.now());
            try {
                int ret = areaService.update(area);
                if (ret > 0) {
                    AreaVO vo = PojoConvertUtil.convertPojo(area, AreaVO.class);
                    SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getAreaSyncEmax()), vo);
                    return ResultGenerator.genSuccessResult(vo);
                } else {
                    return ResultGenerator.genFailResult("编辑失败,请稍候重试");
                }
            } catch (Exception ex) {
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
                return ResultGenerator.genFailResult("编辑失败,请稍候重试");
            }
        } else if (check == UNKNOWN_EXCEPTION_CODE) {
            return ResultGenerator.genFailResult("编辑失败,请稍候重试");

        } else {
            return ResultGenerator.genFailResult("编辑失败,地理区域编码为空或重复啦");
        }
    }

    @DeleteMapping("/deletearea/{id}")
    @ResponseBody
    public Result deleteArea(@PathVariable Integer id) {
        Area area;
        try {
            area = areaService.findById(id);
            if (area == null) {
                return ResultGenerator.genFailResult("删除失败,该地理区域不存在");
            }
            if (area.getStatus() != 1) {
                return ResultGenerator.genFailResult("删除失败,该地理区域已经被删除");
            }
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult("删除失败,请稍候重试");
        }
        Area updateArea = new Area();
        updateArea.setAreaId(id);
        updateArea.setStatus(0);
        updateArea.setLastUpdatedBy(SessionManager.getLoginName());
        updateArea.setLastUpdateDate(DateUtil.now());
        try {
            int ret = areaService.update(updateArea);
            if (ret > 0) {
                AreaVO vo = PojoConvertUtil.convertPojo(area, AreaVO.class);
                vo.setStatus(0);
                SyncHelper.synData(synConfigEntity.getHost().concat(synConfigEntity.getAreaSyncEmax()), vo);
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("删除失败,请稍候重试");
            }
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            return ResultGenerator.genFailResult("删除失败,请稍候重试");

        }
    }


    private int checkCityLevelVO(CityLevelVO cityLevelVO, int cityLevelId) {
        //传入数据格式不对无法解析
        if (cityLevelVO == null) {
            return -1;
        }
        //名称为空
        if (StringUtils.isBlank(cityLevelVO.getCityLevelName())) {
            return -2;
        }

        Condition condition = new Condition(CityLevel.class);
        if (cityLevelId > 0) {
            condition.createCriteria().andEqualTo("cityLevelName", cityLevelVO.getCityLevelName())
                    .andNotEqualTo("cityLevelId", cityLevelId);
        } else {
            condition.createCriteria().andEqualTo("cityLevelName", cityLevelVO.getCityLevelName());
        }
        try {
            int count = cityLevelService.findCountByCondition(condition);
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

    private int checkAreaVO(AreaVO areaVO, int areaId) {
        //传入数据格式不对无法解析
        if (areaVO == null) {
            return -1;
        }
        //名称为空
        if (StringUtils.isBlank(areaVO.getAreaCode())) {
            return -2;
        }

        Condition condition = new Condition(Area.class);
        if (areaId > 0) {
            condition.createCriteria().andEqualTo("areaCode", areaVO.getAreaCode())
                    .andNotEqualTo("areaId", areaId);
        } else {
            condition.createCriteria().andEqualTo("areaCode", areaVO.getAreaCode());
        }
        try {
            int count = areaService.findCountByCondition(condition);
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