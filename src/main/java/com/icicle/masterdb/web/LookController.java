package com.icicle.masterdb.web;

import com.icicle.masterdb.authorization.annotation.Authorization;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.core.ResultGenerator;
import com.icicle.masterdb.pojo.vo.LookSelectVO;
import com.icicle.masterdb.pojo.vo.ProductLookVO;
import com.icicle.masterdb.service.LookService;
import com.icicle.masterdb.util.ExcelUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author liumingming
 * @version 2017-12-11 16:26.
 */
@Controller
@RequestMapping(value = "/look")
@Authorization
public class LookController {

    private final int min_row_count = 2;
    /**
     * 秋冬季
     */
    private final String AW = "aw";
    /**
     * 春夏季
     */
    private final String SS = "ss";

    /**
     * 年份前缀
     */
    private final String YEAR_PREFIX = "20";

    @Resource
    private LookService lookService;

    @GetMapping(value = "/getselectdata")
    @ResponseBody
    public Result getSelectData(@RequestParam("defCode") String defCode) {
        return ResultGenerator.genSuccessResult(lookService.getSelectData(defCode));
    }

    @GetMapping(value = "/getpositionlist")
    @ResponseBody
    public Result getPositionList() {
        return ResultGenerator.genSuccessResult(lookService.getPositionList());
    }

    @GetMapping(value = "/checklookid/{id}")
    @ResponseBody
    public Result checkLookId(@PathVariable Integer id) {
        return ResultGenerator.genSuccessResult(lookService.checkLookId(id));
    }

    @GetMapping(value = "/checklookcode/{code}")
    @ResponseBody
    public Result checkLookCode(@PathVariable String code) {
        return ResultGenerator.genSuccessResult(lookService.checkLookCode(code));
    }


    @PostMapping("/looklist")
    @ResponseBody
    public DataTableRecord lookList(@RequestParam("sEcho") String sEcho,
                                    @RequestParam("iDisplayStart") Integer pageIndex,
                                    @RequestParam("iDisplayLength") Integer pageSize,
                                    @RequestParam("sSearch") String sSearch) {
        Integer lookId;
        if (StringUtils.isBlank(sSearch)) {
            lookId = null;
        } else {
            try {
                lookId = Integer.valueOf(sSearch);
            } catch (Exception ex) {
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
                lookId = Integer.valueOf(0);
            }
        }

        return lookService.getList(sEcho, pageIndex, pageSize, lookId);
    }

    @PostMapping(value = "/addlook")
    @ResponseBody
    public Result addLook(HttpServletRequest request) {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        ProductLookVO vo = checkData(mRequest);
        int ret = lookService.save(vo);
        if (ret > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("新增look失败，请稍后重试");
        }
    }

    @PostMapping(value = "/updatelook")
    @ResponseBody
    public Result updateLook(HttpServletRequest request) {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        ProductLookVO vo = checkData(mRequest);
        int ret = lookService.update(vo);
        if (ret > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("编辑look失败，请稍后重试");
        }
    }

    @PostMapping(value = "/importlook")
    @ResponseBody
    public Result importLook(@RequestParam("lookfile") MultipartFile file) {
        if (null != file && file.getSize() > 0) {
            Workbook workbook;
            try {
                if (!ExcelUtil.isExcelFile(file.getInputStream())) {
                    return ResultGenerator.genFailResult("上传文件不是excel文件");
                }
                workbook = WorkbookFactory.create(file.getInputStream());
            } catch (Exception ex) {
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
                return ResultGenerator.genFailResult("读取上传文件出错，请联系IT处理");
            }
            Sheet sheet = workbook.getSheetAt(0);
            if (null == sheet) {
                return ResultGenerator.genFailResult("读取上传文件出错，请联系IT处理");
            }
            int rowNum = sheet.getLastRowNum() + 1;
            if (rowNum < min_row_count) {
                return ResultGenerator.genFailResult("上传文件无有效数据，请先确认");
            }
            LookSelectVO vo = lookService.getSelectData("position");
            int column = 8;
            List<String> checkList = new ArrayList<>();
            List<String> checkCodeList = new ArrayList<>();
            List<ProductLookVO> list = new ArrayList<>();
            long count;
            String trueFlag = "1";
            for (int i = 1; i < rowNum; i++) {
                String[] colValues = ExcelUtil.parseRow(sheet.getRow(i), column);
                if (null == colValues || colValues.length < column) {
                    continue;
                }
                if (!StringUtils.isNumeric(colValues[0])
                        || StringUtils.isBlank(colValues[1])
                        || StringUtils.isBlank(colValues[2])
                        || StringUtils.isBlank(colValues[3])
                        || StringUtils.isBlank(colValues[4])
                        || StringUtils.isBlank(colValues[5])
                        || StringUtils.isBlank(colValues[6])
                        || StringUtils.isBlank(colValues[7])) {
                    continue;
                }
                if (checkList.contains(colValues[0])) {
                    continue;
                }
                if (checkCodeList.contains(colValues[1])) {
                    continue;
                }
                if (colValues[7].length() != 4) {
                    continue;
                }

                String year = colValues[7].substring(0, 2);
                String devS = colValues[7].substring(2, 4);
                String devSeason;
                if (!StringUtils.isNumeric(year)) {
                    continue;
                }

                if (SS.equalsIgnoreCase(devS)) {
                    devSeason = "A";
                } else if (AW.equalsIgnoreCase(devS)) {
                    devSeason = "B";
                } else {
                    continue;
                }


                count = vo.getSelectItemList().stream().filter(s -> "select_line".equals(s.getTableName())
                        && Objects.equals(s.getItemKey(), colValues[3])).count();
                if (count < 1L) {
                    continue;
                }
                count = vo.getSelectItemList().stream().filter(s -> "select_wave_band".equals(s.getTableName())
                        && Objects.equals(s.getItemKey(), colValues[4])).count();
                if (count < 1L) {
                    continue;
                }
                checkList.add(colValues[0]);
                checkCodeList.add(colValues[1]);
                ProductLookVO productLookVO = new ProductLookVO();
                productLookVO.setLookId(Integer.valueOf(colValues[0]));
                productLookVO.setLookCode(colValues[1]);
                productLookVO.setLookPhotoName(colValues[2]);
                productLookVO.setLine(colValues[3]);
                productLookVO.setWaveBand(colValues[4]);
                productLookVO.setYear(YEAR_PREFIX.concat(year));
                productLookVO.setDevSeason(devSeason);
                if (Objects.equals(colValues[5], trueFlag)) {
                    productLookVO.setCurrentSeason(true);
                } else {
                    productLookVO.setCurrentSeason(false);
                }


                //为了使用lambda表达式才定义的数组
                int[] posi = {0};
                if (!StringUtils.isBlank(colValues[6])) {
                    String[] positons = colValues[6].split(",");
                    for (String pos : positons) {
                        if (!StringUtils.isBlank(pos)) {
                            vo.getAttributeSelectList().stream().filter(a -> Objects.equals(a.getName(), pos))
                                    .findFirst().ifPresent(a -> posi[0] = a.getCode().intValue() | posi[0]);
                        }
                    }
                }
                productLookVO.setPosition(Integer.valueOf(posi[0]));
                list.add(productLookVO);
            }
            int ret;
            try {
                ret = lookService.importLook(list);
            } catch (Exception ex) {
                ret = 0;
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            }
            if (ret == 0) {
                return ResultGenerator.genSuccessResult("本次导入未写入任何数据");
            } else {
                String message =
                        StringUtil.gsFormat("本次上传了[{0}]行数据，成功写入[{1}]条数据", rowNum - 1, ret);
                return ResultGenerator.genSuccessResult(message);
            }
        } else {
            return ResultGenerator.genFailResult("上传文件为空");
        }
    }

    private ProductLookVO checkData(MultipartHttpServletRequest mRequest) {
        String id = mRequest.getParameter("lookId");
        if (StringUtils.isBlank(id)) {
            throw new SecurityException("look序号不能为空");
        }

        Integer lookId;
        try {
            lookId = Integer.valueOf(id);
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            throw new SecurityException("look序号格式错误，请确认");
        }
        String lookCode = mRequest.getParameter("lookCode");
        if (StringUtils.isBlank(lookCode)) {
            throw new SecurityException("look编号不能为空");
        }
        String photoName = mRequest.getParameter("lookPhotoName");
        if (StringUtils.isBlank(photoName)) {
            throw new SecurityException("look图片名不能为空");
        }
        String line = mRequest.getParameter("line");
        if (StringUtils.isBlank(line)) {
            throw new SecurityException("线路不能为空");
        }
        String waveBand = mRequest.getParameter("waveBand");
        if (StringUtils.isBlank(waveBand)) {
            throw new SecurityException("波段不能为空");
        }
        String position = mRequest.getParameter("positions");
        String positionDesc = mRequest.getParameter("positionDesc");
        String currentSeason = mRequest.getParameter("currentSeason");
        if (StringUtils.isBlank(currentSeason)) {
            throw new SecurityException("当季引用不能为空");
        }

        String year = mRequest.getParameter("year");
        if (!StringUtils.isNumeric(year)) {
            throw new SecurityException("年份只能是数字");
        }
        String devSeason = mRequest.getParameter("devSeason");
        if (StringUtils.isBlank(devSeason)) {
            throw new SecurityException("开发季不能为空");
        }

        ProductLookVO vo = new ProductLookVO();
        vo.setLookId(lookId);
        vo.setLookCode(lookCode);
        vo.setLookPhotoName(photoName);
        vo.setLine(line);
        vo.setWaveBand(waveBand);
        vo.setDevSeason(devSeason);
        vo.setYear(year);
        int pos = 0;
        if (!StringUtils.isBlank(position)) {
            String[] arr = position.split(",");
            for (String po : arr) {
                pos = pos | Integer.parseInt(po);
            }
        }
        vo.setPosition(Integer.valueOf(pos));
        if (!StringUtils.isBlank(positionDesc)) {
            vo.setPositionDesc(positionDesc);
        }
        vo.setCurrentSeason(Boolean.valueOf(currentSeason));
        return vo;
    }
}