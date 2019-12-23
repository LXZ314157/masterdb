package com.icicle.masterdb.web;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.core.ResultGenerator;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ProductPromotion;
import com.icicle.masterdb.service.ProductPromotionService;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.ExcelUtil;
import com.icicle.masterdb.util.LanguageUtil;
import com.icicle.masterdb.util.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangyuling
 * @version 2018/6/26 10:03
 */
@Controller
@RequestMapping("promotion")

public class PromotionController {

    @Resource
    private ProductPromotionService productPromotionService;

    @PostMapping("/parsedata")
    @ResponseBody
    public Result parseData(@RequestParam("uploadfile") MultipartFile file) {
        if (null == file) {
            return ResultGenerator.genFailResult("您没有选择任何文件");
        }
        List<ProductPromotion> productPromotionList=new ArrayList<>();
        try {
            if (!ExcelUtil.isExcelFile(file.getInputStream())) {
                return ResultGenerator.genFailResult("此文件不是excel文件，请上传excel文件");
            }
            productPromotionList = productPromotionService.parseExcel(file.getInputStream());
        } catch (IOException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
        }
        return ResultGenerator.genSuccessResult(productPromotionList);

    }



    @PostMapping("/promotionlist")
    @ResponseBody
    public DataTableRecord promotionList(@RequestParam("sEcho") String sEcho,
                                         @RequestParam("iDisplayStart") Integer pageIndex,
                                         @RequestParam("iDisplayLength") Integer pageSize,
                                         @RequestParam("sSearch") String sSearch) {
        return productPromotionService.getList(sEcho, pageIndex, pageSize, sSearch);
    }

    @GetMapping("/getshowtypelist")
    @ResponseBody
    public Result getShowTypeList() {
        return ResultGenerator.genSuccessResult(productPromotionService.getshowTypeList());
    }

    @PostMapping("/addpromotion")
    @ResponseBody
    public Result addPromotion(HttpServletRequest request) {
        MultipartHttpServletRequest result = (MultipartHttpServletRequest) request;

        try {
            ProductPromotion productPromotion = checkPromotion(result);
            productPromotion.setCreateby(SessionManager.getLoginName());
            productPromotion.setCreationDate(DateUtil.now());
            int ret = productPromotionService.save(productPromotion);
            if (ret > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("新增推广信息失败，请稍后再试");
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("/updatepromotion")
    @ResponseBody
    public Result updatePromotion(HttpServletRequest request) {
        MultipartHttpServletRequest result = (MultipartHttpServletRequest) request;
        try {
            ProductPromotion productPromotion = checkPromotion(result);
            productPromotion.setLastUpdateBy(SessionManager.getLoginName());
            productPromotion.setLastUpDate(DateUtil.now());
            int ret = productPromotionService.update(productPromotion);
            if (ret > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("更新推广信息失败，请稍后再试");
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @GetMapping("/checkpromotionid/{id}")
    @ResponseBody
    public Result checkPromotionId(@PathVariable String id) {
        return ResultGenerator.genSuccessResult(productPromotionService.checkPromotionId(id));
    }

    @GetMapping("/checkpromotioncode/{code}")
    @ResponseBody
    public Result checkPromotionCode(@PathVariable String code) {
        return ResultGenerator.genSuccessResult(productPromotionService.checkPromotionCode(code));
    }

    private ProductPromotion checkPromotion(MultipartHttpServletRequest result) throws ParseException {
        String id = result.getParameter("id");
        String code = result.getParameter("code");
        String imgName = result.getParameter("imgname");
        String line = result.getParameter("line");
        String waveBand = result.getParameter("waveBand");
        String year = result.getParameter("year");
        String devSeason = result.getParameter("devSeason");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = result.getParameter("outofdate");
        Date outofdate = null;
        if (!StringUtils.isBlank(date)) {
            outofdate = format.parse(date);
        }
        String showtype = result.getParameter("showType");
        if (null == id) {
            throw new SecurityException("推广id不能为空");
        }
        if (null == code) {
            throw new SecurityException("图片编码不能为空");
        }
        if (null == imgName) {
            throw new SecurityException("图片名不能为空");
        }
        if (!StringUtils.isNumeric(year)) {
            throw new SecurityException("年份只能是数字");
        }
        int types = 0;
        if (!StringUtils.isBlank(showtype)) {
            String[] arr = showtype.split(",");
            for (String type : arr) {
                types = types | Integer.parseInt(type);
            }
        }
        ProductPromotion productPromotion = new ProductPromotion();
        productPromotion.setPromotionId(id);
        productPromotion.setPromotionCode(code);
        productPromotion.setPromotionImgName(imgName);
        productPromotion.setLine(line);
        productPromotion.setYear(year);
        productPromotion.setOutOfDate(outofdate);
        productPromotion.setDevSeason(devSeason);
        productPromotion.setWaveBand(waveBand);
        productPromotion.setShowType(Integer.valueOf(types));
        return productPromotion;
    }
}
