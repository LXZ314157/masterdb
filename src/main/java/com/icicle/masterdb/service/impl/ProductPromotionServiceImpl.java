package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.ProductPromotionMapper;
import com.icicle.masterdb.dao.masterdb.ViewProductAttributeSelectMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ProductImage;
import com.icicle.masterdb.model.ProductPromotion;
import com.icicle.masterdb.model.ViewProductAttributeSelect;
import com.icicle.masterdb.service.FtpService;
import com.icicle.masterdb.service.ProductPromotionService;
import com.icicle.masterdb.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wangyuling
 * @version 2018/6/26 10:23
 */
@Service
public class ProductPromotionServiceImpl extends AbstractService<ProductPromotion> implements ProductPromotionService {
    private final int ROW_COUNT = 8;
    private final int YEAR_SEASON = 0;
    private final int LINE = 1;
    private final int WAVR_BAND = 2;
    private final int PROMOTION_ID = 3;
    private final int PROMOTION_CODE = 4;
    private final int IMG_NAME = 5;
    private final int OUT_DATE = 6;
    private final int SHOW_TYPE = 7;
    private final String PROMOTION_DIR = "promotion";

    /**
     * 秋冬季
     */
    private final String AW = "aw";
    /**
     * 春夏季
     */
    private final String SS = "ss";
    List<String> imgNameList;

    @Resource
    FtpService ftpService;

    @Resource
    private ProductPromotionMapper productPromotionMapper;
    @Resource
    private ViewProductAttributeSelectMapper viewProductAttributeSelectMapper;
    List<ViewProductAttributeSelect> list;

    @Override
    public List<ProductPromotion> parseExcel(InputStream inputStream) {
        List<ProductPromotion> productPromotionList = new ArrayList<>();
        Workbook workbook;
        list = new ArrayList<>();
        list = viewProductAttributeSelectMapper.findByDefindCode("show_type");
        try {
            workbook = WorkbookFactory.create(inputStream);
        } catch (IOException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        } catch (InvalidFormatException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }
        Sheet sheet = workbook.getSheetAt(0);
        //以第一行数据为准，如果之后的行没有导入第一行所在列中包含的数据，则忽略
        List<Integer> indexRow = new ArrayList<>();
        //获取第一行
        Row firstRow = sheet.getRow(0);
        //如果第一行的数据不存在，直接返回空
        if (firstRow == null) {
            return null;
        }
        //获取列数
        int colNum = firstRow.getPhysicalNumberOfCells();
        //获得总行数
        int rowNum = sheet.getLastRowNum() + 1;
        //如果列数不是ROWCOUNT列，直接返回空
        if (colNum != ROW_COUNT) {
            return null;
        }

        //获取第一行中不包含的列
        for (int i = 0; i < ROW_COUNT; i++) {
            if (firstRow.getCell(i) == null) {
                indexRow.add(i);
            }
        }

        for (int i = 1; i < rowNum; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                break;
            }

            String[] colValues = ExcelUtil.parseRow(row, colNum);
            Boolean checkRow = dataValidate(colValues);
            if (checkRow == true) {
                ProductPromotion productPromotion = getPromotion(colValues);
                if (productPromotion != null) {
                    productPromotionList.add(productPromotion);
                }

            }
        }
        return productPromotionList;
    }

    @Override
    public List<ProductImage> uploadImage(MultipartFile[] file, List<ProductPromotion> list) {
        if (file == null || file.length == 0) {
            return null;
        }

        List<ProductImage> productImageList = new ArrayList<>();
        //上传成功的图片数量

        String userName = SessionManager.getLoginName();
        FTPClient ftp = ftpService.getConnection();

        try {
            //当前这个编码是否为产品编码i
            for (int i = 0; i < file.length; i++) {
                //获取文件的原始名
                String fileOriginalName = file[i].getOriginalFilename();
                //获取图片的原始名称
                String name = fileOriginalName.split(".")[0];
                //验证提供的
                List<ProductPromotion> isexist = list.stream().filter(p -> Objects.equals(p.getPromotionImgName(), name)).collect(Collectors.toList());
                if (isexist == null || isexist.size() == 0) {
                    continue;
                }
                ProductPromotion productPromotion = isexist.get(0);
                String imgCode = productPromotion.getPromotionCode();
                //获取当前年份信息，创建图片分类路径，若获取的信息不符合要求，则返回null
                String year = getImagePath();
                if (StringUtils.isBlank(year)) {
                    continue;
                }
                if (!ExcelUtil.isImageFile(file[i].getInputStream())) {
                    continue;
                } else {
                    String imgUrl = year.concat(name);
                    //如果本张图片上传失败则不写入到数据库，继续上传下一张
                    if (!ftpService.uploadFile(ftp, file[i].getInputStream(), year, name)) {
                        continue;
                    }
                    //如果本次已经上传过本张图片，则不写入数据库，继续上传下一张
                    if (productImageList.stream().anyMatch(p -> Objects.equals(p.getImageUrl(), imgUrl))) {
                        continue;
                    }
                    ProductImage productImage = new ProductImage();
                    productImage.setDefaultImage(Boolean.FALSE);
                    productImage.setImageName(imgCode);
                    productImage.setImageType(3);
                    productImage.setImageUrl(imgUrl);
                    productImage.setCreatedDate(DateUtil.now());
                    productImage.setCreatedBy(userName);
                    productImage.setStatus(Integer.valueOf(1));
                    productImageList.add(productImage);
                }
            }

        } catch (IOException e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }
        //使用完毕后关闭链接
        ftpService.closeConnection(ftp);
        return productImageList;
    }

    Boolean dataValidate(String[] colValues) {
        if (colValues[PROMOTION_ID] == null || colValues[PROMOTION_CODE] == null || colValues[IMG_NAME] == null) {
            return false;
        }
        return true;
    }

    ProductPromotion getPromotion(String[] colValues) {
        if (colValues == null || colValues.length < ROW_COUNT) {
            return null;
        }
        ProductPromotion productPromotion = new ProductPromotion();
        String year = null;
        String season = null;
        if (colValues[YEAR_SEASON] != null) {
            year = colValues[YEAR_SEASON].substring(0, 2);
            season = colValues[YEAR_SEASON].substring(2, 4);
            String devSeason;
            productPromotion.setYear("20".concat(year));
            if (SS.equalsIgnoreCase(season)) {
                devSeason = "A";
            } else if (AW.equalsIgnoreCase(season)) {
                devSeason = "B";
            } else {
                devSeason = "";
            }
            productPromotion.setDevSeason(devSeason);
        }

        productPromotion.setLine(colValues[LINE]);
        productPromotion.setWaveBand(colValues[WAVR_BAND]);
        productPromotion.setOutOfDate(DateUtil.getDateByString(colValues[OUT_DATE], "yyyy/MM/dd"));
        productPromotion.setPromotionId(colValues[PROMOTION_ID]);
        productPromotion.setPromotionCode(colValues[PROMOTION_CODE]);
        productPromotion.setPromotionImgName(colValues[IMG_NAME]);
        int[] posi = {0};
        if (!StringUtils.isBlank(colValues[SHOW_TYPE])) {
            String[] types = colValues[SHOW_TYPE].split(",");
            for (String type : types) {
                if (!StringUtils.isBlank(type)) {
                    list.stream().filter(a -> Objects.equals(a.getName(), type))
                            .findFirst().ifPresent(a -> posi[0] = a.getCode().intValue() | posi[0]);
                }
            }
        }
        productPromotion.setShowType(posi[0]);
        productPromotion.setCreateby(SessionManager.getLoginName());
        productPromotion.setCreationDate(DateUtil.now());
        return productPromotion;
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    private String getImagePath() {
        String year = null;
        year = DateUtil.currentYear();
        year = StringUtil.gsFormat("{0}/{1}/", PROMOTION_DIR, year);
        return year;
    }

    @Override
    public DataTableRecord getList(String sEcho, Integer pageIndex, Integer pageSize, String sSearch) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        List<ProductPromotion> list;
        Integer total;
        if (null != sSearch) {
            list = new ArrayList<>();
            Condition condition = new Condition(ProductPromotion.class);
            String words = StringUtil.gsFormat("%{0}%", sSearch);
            condition.and(condition.createCriteria().andLike("promotionCode", words).orLike("promotionImgName", words));
            try {
                list = productPromotionMapper.selectByCondition(condition);
            } catch (Exception ex) {
                list = null;
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            }
            total = Integer.valueOf(list.size());
            dataTableRecord.setAaData(list);
        } else {
            Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
            try {
                PageHelper.startPage(pageNum, pageSize);
                list = productPromotionMapper.selectAll();
            } catch (Exception ex) {
                list = new ArrayList<>();
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            }
            PageInfo pageInfo = new PageInfo(list);
            total = (int) pageInfo.getTotal();
            dataTableRecord.setAaData(pageInfo.getList());
        }
        dataTableRecord.setITotalRecords(total);
        dataTableRecord.setITotalDisplayRecords(total);
        return dataTableRecord;
    }

    @Override
    public List<ViewProductAttributeSelect> getshowTypeList() {
        List<ViewProductAttributeSelect> list;
        try {
            list = viewProductAttributeSelectMapper.findByDefindCode("show_type");
        } catch (Exception ex) {
            list = new ArrayList<>();
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
        }
        return list;
    }

    @Override
    public int checkPromotionId(String id) {
        return productPromotionMapper.checkPromotionId(id);
    }

    @Override
    public int checkPromotionCode(String code) {
        return productPromotionMapper.checkPromotionCode(code);
    }

    @Override
    public int savePromotionList(List<ProductPromotion> list) {
        if (list == null) {
            return -1;
        }
        return 1;
    }
}
