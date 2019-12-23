package com.icicle.masterdb.web;

import com.icicle.masterdb.authorization.annotation.Authorization;
import com.icicle.masterdb.core.AbstractPageController;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.core.ResultGenerator;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ModelProductMapping;
import com.icicle.masterdb.model.Product;
import com.icicle.masterdb.model.ProductImage;
import com.icicle.masterdb.service.FtpService;
import com.icicle.masterdb.service.ModelProductMappingService;
import com.icicle.masterdb.service.ProductImageService;
import com.icicle.masterdb.service.ProductService;
import com.icicle.masterdb.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.icicle.masterdb.service.impl.ProductImageServiceImpl.*;

/**
 * Created by liurenhua on 2017/11/13.
 *
 * @author liurenhua
 */
@Controller
@RequestMapping("/image")
@Authorization
public class ImageController extends AbstractPageController {
    @Resource
    private ProductImageService productImageService;
    @Resource
    private FtpService ftpService;
    @Resource
    private ModelProductMappingService modelProductMappingService;
    @Resource
    private ProductService productService;

    public ImageController() {
        super("image");
    }

    @GetMapping(value = "/looklist")
    public String lookList(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("looklist", map, request);
    }

    @PostMapping("/uploadimage")
    @ResponseBody
    public Result uploadImage(@RequestParam("uploadfile[]") MultipartFile[] file,
                              @RequestParam(value = "imageType") Integer imageType, @RequestParam(value = "codeRule") Integer codeRule) {
        //判断上传图片的类型是否合法，
        if (imageType == null) {
            return ResultGenerator.genFailResult("请选择上传图片类型");
        }
        //判断编码类型是不是正确
        if (codeRule == null) {
            return ResultGenerator.genFailResult("请选择编码类型");
        }
        //图片类型只有1 单品图 2 模特图  排除非法的图片类型
        if (imageType != TYPE_MODEL && imageType != TYPE_PRODUCT && imageType != TYPE_PROMOTION) {
            return ResultGenerator.genFailResult("错误的图片类型");
        }

        //编码规则只有1 老编码规则 2 20新编码 排除非法的编码类型
        if (!codeRule.equals(1) && !codeRule.equals(2)) {
            return ResultGenerator.genFailResult("错误的编码类型");
        }

        if (file == null || file.length == 0) {
            return ResultGenerator.genFailResult("您没有选择任何图片");
        }
        try {
            long upstartTime = System.currentTimeMillis();
            List<ProductImage> productImageList = productImageService.uploadImage(file, imageType, codeRule);
            long upendTime = System.currentTimeMillis();
            if (ListUtil.isBlank(productImageList)) {
                return ResultGenerator.genSuccessResult(0);
            }
            long instartTime = System.currentTimeMillis();
            productImageService.saveList(productImageList);
            long inendTime = System.currentTimeMillis();
            productImageService.getServerTime((inendTime - instartTime), "此次上传" + productImageList.size() + "张图片,数据库存储图片用时:");
            productImageService.getServerTime((upendTime - upstartTime), "上传到服务器图片用时:");
            return ResultGenerator.genSuccessResult(productImageList.size());
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }


    @GetMapping("/productimageupload")
    public String productImageUpload(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("updaload", map, request);
    }

    @GetMapping("/imagemodelmapping")
    public String imageModelMapping(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("mapping", map, request);
    }

    @GetMapping("/listproductimage")
    public String listProductImage(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("productimage", map, request);
    }

    @GetMapping(value = "/promotionlist")
    public String promotionList(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("promotionlist", map, request);
    }


    @PostMapping("/updateimage")
    @ResponseBody
    public Result updateImage(@RequestParam("uploadfile") MultipartFile img,
                              @RequestParam("imageUrl") String imageUrl,
                              @RequestParam("imageId") Integer imageId) {
        if (imageId == null) {
            return ResultGenerator.genFailResult("您没有提供图片id,无法更新图片");
        }
        try {
            if (!ExcelUtil.isImageFile(img.getInputStream())) {
                return ResultGenerator.genFailResult("此文件不是图片");
            }
            ProductImage productImage = new ProductImage();
            productImage.setId(imageId);
            productImage.setLastUpdatedDate(DateUtil.now());
            productImage.setLastUpdatedBy(SessionManager.getLoginName());
            int count = productImageService.update(productImage);
            //如果写入到数据库成功并且上传到ftp成功，则表名此次更新成功
            if (count > 0) {
                if (ftpService.uploadFile(img, imageUrl)) {
                    return ResultGenerator.genSuccessResult();
                } else {
                    return ResultGenerator.genFailResult("图片上传到服务器出错");
                }
            } else {
                return ResultGenerator.genFailResult("此图片不存在");
            }

        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("/findimagebycode")
    @ResponseBody
    public Result findImageByCode(@RequestParam("code") String code,
                                  @RequestParam("imageType") Integer imageType) {
        if (StringUtils.isBlank(code) || imageType == null) {
            return ResultGenerator.genFailResult("你传送的数据有误");
        }

        if (imageType != TYPE_MODEL && imageType != TYPE_PRODUCT) {
            return ResultGenerator.genFailResult("无此图片类型");
        }

        List<ProductImage> productImageList;
        try {
            if (imageType == TYPE_MODEL) {
                productImageList = productImageService.findProImgByMolCodes(code);
            } else {
                productImageList = productImageService.findModImgByProCode(code);
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        return ResultGenerator.genSuccessResult(productImageList);
    }

    @GetMapping("/getimagebyid")
    @ResponseBody
    public Result getImageById(@RequestParam("code") String code) {
        if (StringUtils.isBlank(code)) {
            return ResultGenerator.genFailResult("模特编码或者产品编码不能为空");
        }
        try {

            return ResultGenerator.genSuccessResult(productImageService.findDefaultImage(code));
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @GetMapping("/getimgnotdefault")
    @ResponseBody
    public Result getImgNotDefault(@RequestParam("code") String code) {
        if (StringUtils.isBlank(code)) {
            return ResultGenerator.genFailResult("模特编码或者产品编码不能为空");
        }
        try {
            return ResultGenerator.genSuccessResult(productImageService.findImageNotDefault(code));
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("/leadmodelproductmapping")
    @ResponseBody
    public Result leadModelProductMapping(@RequestParam("uploadfile") MultipartFile file) {
        if (file == null) {
            return ResultGenerator.genFailResult("您没有选择任何文件");
        }
        int count;
        try {
            if (!ExcelUtil.isExcelFile(file.getInputStream())) {
                return ResultGenerator.genFailResult("此文件不是excel文件");
            }
            List<ModelProductMapping> list = modelProductMappingService.parseExcel(file.getInputStream());
            ExcelUtil.saveExcelFile(file);
            if (ListUtil.isBlank(list)) {
                return ResultGenerator.genFailResult("此excel文件格式不符合要求，或者包含数据已经被导入");
            }
            count = modelProductMappingService.saveList(list);
            return ResultGenerator.genSuccessResult(count);
        } catch (InvalidFormatException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult("此文件不是excel文件");
        } catch (IOException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult("读取文件出错");
        }
    }

    @ResponseBody
    @DeleteMapping("/deleteimage/{id}")
    public Result deleteImage(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResultGenerator.genFailResult("您必须提供要删除的图片ID");
        }
        try {
            int count = productImageService.deleteByImageId(id);
            if (count > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("该图片不存在");
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @DeleteMapping("/deletemapping/{id}")
    @ResponseBody
    public Result deleteMapping(@PathVariable("id") Integer id) {
        if (id == null) {
            return ResultGenerator.genFailResult("您没有选择任何图片");
        }
        try {
            int count = modelProductMappingService.deleteByMappingId(id);
            if (count > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("此图片不存在");
            }
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
    }

    @PostMapping("/createdatetables")
    @ResponseBody
    public DataTableRecord createDateTables(
            @RequestParam(value = "sEcho") String sEcho,
            @RequestParam(value = "iDisplayStart") Integer pageIndex,
            @RequestParam(value = "iDisplayLength") Integer pageSize,
            @RequestParam(value = "sSearch") String sSearch) {
        try {
            return productImageService.createDataTable(sEcho, pageIndex, pageSize, sSearch);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return new DataTableRecord(sEcho, 0, 0, new ArrayList<>());
        }
    }

    @PostMapping("/updatemapping")
    @ResponseBody
    public Result updateMapping(
            @RequestParam("id") Integer id,
            @RequestParam("productCode") String productCode,
            @RequestParam("modelCode") String modelCode) {

        if (StringUtils.isBlank(productCode) ||
                StringUtils.isBlank(modelCode) ||
                id == null) {
            return ResultGenerator.genFailResult("您提交的数据不完全，无法更新");
        }

        int count;
        try {
            Product product = productService.selectProduct(productCode);
            if (product == null) {
                return ResultGenerator.genFailResult("此产品编码不存在");
            }

            ModelProductMapping m = new ModelProductMapping();
            m.setProductCode(productCode.toUpperCase());
            m.setModelCode(modelCode.toUpperCase());
            m.setId(id);
            m.setLastUpdatedBy(SessionManager.getLoginName());
            m.setLastUpdatedDate(DateUtil.now());
            count = modelProductMappingService.updateMapping(m);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return ResultGenerator.genFailResult(LanguageUtil.getText(-999));
        }
        if (count > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("此对应关系不存在，更新失败");
        }
    }

}
