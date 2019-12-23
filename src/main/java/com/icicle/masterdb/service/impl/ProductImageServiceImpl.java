package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.ModelProductMappingMapper;
import com.icicle.masterdb.dao.masterdb.ProductImageMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ModelProductMapping;
import com.icicle.masterdb.model.ProductImage;
import com.icicle.masterdb.service.FtpService;
import com.icicle.masterdb.service.ProductImageService;
import com.icicle.masterdb.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.icicle.masterdb.service.constant.ServiceConstant.VALID_LENGTH;

/**
 * @author liurenhua
 */
@Service
public class ProductImageServiceImpl extends AbstractService<ProductImage> implements ProductImageService {
    Logger imgLogger = LoggerFactory.getLogger("upimage");
    /**
     * 文件名最大长度
     */
    private final int MAX_FILENAME_LENGTH = 30;
    private final String MODEL_DIR = "model";
    private final String PRODUCT_DIR = "product";
    private final String PROMOTION_DIR = "promotion";
    /**
     * 产品和图片的两个类型，在控制层中会用到，定义成静态 public 类型
     */
    public static final int TYPE_PRODUCT = 1;
    public static final int TYPE_MODEL = 2;
    public static final int TYPE_PROMOTION = 3;

    /**
     * 编码规则
     */
    public static final int OLD_CODEING = 1;
    public static final int NEW_20_CODEING = 2;

    /**
     * 新版产品编号截取年份的起始和终止位置
     */
    private final int PRODUCT_NEW_CODEING_SUBSTRING_START = 4;
    private final int PRODUCT_NEW_CODEING_SUBSTRING_END = 6;

    /**
     * 产品编号截取年份的起始和终止位置
     */
    private final int PRODUCT_SUBSTRING_START = 5;
    private final int PRODUCT_SUBSTRING_END = 7;

    /**
     * 模特编号截取年份的起始和终止位置
     */
    private final int MODEL_SUBSTRING_START = 7;
    private final int MODEL_SUBSTRING_END = 9;

    /**
     * 推广编号截取年份的起始和终止位置
     */
    private final int PROMOTION_SUBSTRING_START = 3;
    private final int PROMOTION_SUBSTRING_END = 5;

    @Resource
    private ProductImageMapper productImageMapper;

    @Resource
    private ModelProductMappingMapper modelProductMappingMapper;

    @Resource
    private FtpService ftpService;

    @Override
    public ProductImage findOneByCode(String imageName) {
        return productImageMapper.findOneByCode(imageName);
    }

    @Override
    public List<ProductImage> uploadImage(MultipartFile[] file, Integer imageType, Integer codeRule) {

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
                //获取图片的原始名称 通过原始名称得到productCode，验证该productCode是否存在
                String productCode = StringUtil.getProductCode(fileOriginalName);
                //文件名过长的，文件舍弃掉，不保存
                if (productCode.length() > MAX_FILENAME_LENGTH) {
                    continue;
                }
                //获取当前编码的年份信息，若获取的信息不符合要求，则返回null
                String year = getImageYear(productCode, imageType, codeRule);
                if (StringUtils.isBlank(year)) {
                    continue;
                }
                if (!ExcelUtil.isImageFile(file[i].getInputStream())) {
                    continue;
                } else {
                    String imgUrl = year.concat(fileOriginalName);
                    //如果本张图片上传失败则不写入到数据库，继续上传下一张
                    if (!ftpService.uploadFile(ftp, file[i].getInputStream(), year, fileOriginalName)) {
                        continue;
                    }
                    //如果本次已经上传过本张图片，则不写入数据库，继续上传下一张
                    if (productImageList.stream().anyMatch(p -> Objects.equals(p.getImageUrl(), imgUrl))) {
                        continue;
                    }
                    ProductImage productImage = new ProductImage();
                    if (fileOriginalName.contains("_")) {
                        productImage.setDefaultImage(Boolean.FALSE);
                    } else {
                        productImage.setDefaultImage(Boolean.TRUE);
                    }
                    productImage.setImageName(productCode);
                    productImage.setImageType(imageType);
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


    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "批量上传图片")
    public int saveList(List<ProductImage> list) {
        int count = 0;
        if (ListUtil.isBlank(list)) {
            return count;
        }
        List<ProductImage> insertList = new ArrayList<>();
        List<String> updateList = new ArrayList<>();
        String userName = SessionManager.getLoginName();
        List<String> existList = productImageMapper.findExistList(list);
        if (ListUtil.isBlank(existList)) {
            insertList = list;
        } else {
            for (ProductImage productImage : list) {
                if (existList.stream().anyMatch(p -> Objects.equals(p, productImage.getImageUrl()))) {
                    updateList.add(productImage.getImageUrl());
                } else {
                    insertList.add(productImage);
                }
            }
        }

        //批量插入数据
        if (!ListUtil.isBlank(insertList)) {
            count += save(insertList);
        }

        if (!ListUtil.isBlank(updateList)) {
            count += productImageMapper.updateBatch(userName, DateUtil.now(), updateList);
        }

        return count;
    }

    @Override
    @LogAction(logDesc = "删除一张图片")
    public int deleteByImageId(Integer imgId) {
        if (imgId == null) {
            return 0;
        }
        return productImageMapper.deleteByImageId(imgId);
    }

    @Override
    public ProductImage findDefaultImage(String code) {
        return productImageMapper.findDefaultImage(code);
    }

    @Override
    public DataTableRecord createDataTable(
            String sEcho,
            Integer pageIndex,
            Integer pageSize,
            String sSearch) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        Condition condition = new Condition(ModelProductMapping.class);
        Example.Criteria criteria = condition.createCriteria();
        if (!StringUtils.isBlank(sSearch)) {
            String anyWords = StringUtil.gsFormat("%{0}%", sSearch);
            criteria.andLike("productCode", anyWords).orLike("modelCode", anyWords);
        }
        criteria.andEqualTo("status", 1);
        condition.orderBy("createdDate").desc();
        List<ModelProductMapping> mappingList;
        try {
            mappingList = modelProductMappingMapper.selectByCondition(condition);
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }

        PageInfo pageInfo = new PageInfo(mappingList);
        dataTableRecord.setITotalRecords(pageSize);
        dataTableRecord.setITotalDisplayRecords((int) pageInfo.getTotal());
        dataTableRecord.setAaData(mappingList);
        return dataTableRecord;
    }

    @Override
    public List<ProductImage> findProImgByMolCodes(String modelCode) {
        return productImageMapper.findProImgByMolCode(modelCode);
    }

    @Override
    public List<ProductImage> findModImgByProCode(String productCode) {
        return productImageMapper.findModImgByProCode(productCode);
    }

    @Override
    public void getServerTime(long time, String content) {
        if (time <= 0) {
            imgLogger.info("图片上传时间获取错误，请检查");
        } else {
            String format;
            Object[] array;
            //获取秒
            double s = Math.floor(time / 1000);
            Integer hour = (int) (s / (60 * 60));
            Integer min = (int) (s / 60 - hour * 60);
            Integer seconds = (int) (s - min * 60 - hour * 60 * 60);
            Integer milliSecond = (int) (s - min * 60 * 1000 - hour * 60 * 60 * 1000);
            if (hour > 0) {
                format = "%1$,d时%2$,d分%3$,d秒";
                array = new Object[]{hour, min, seconds};
            } else if (min > 0) {
                format = "%1$,d分%2$,d秒";
                array = new Object[]{min, seconds};
            } else if (seconds > 0) {
                format = "%1$,d秒%2$,d毫秒";
                array = new Object[]{seconds, milliSecond};
            } else {
                format = "%1$,d毫秒";
                array = new Object[]{time};
            }
            String val = String.format(format, array);
            String value = StringUtil.gsFormat("{0}{1}", content, val);
            imgLogger.info(value);
        }
    }

    @Override
    public List<ProductImage> findImageNotDefault(String code) {
        return productImageMapper.findImageNotDefault(code);
    }

    private String getImageYear(String code, int imageType, int codeRule) {
        String year = null;

        if (StringUtils.isBlank(code) || code.length() < VALID_LENGTH) {
            return year;
        }

        if (imageType == TYPE_MODEL) {
            year = code.substring(MODEL_SUBSTRING_START, MODEL_SUBSTRING_END);
            if (!StringUtils.isNumeric(year)) {
                return null;
            }
            year = StringUtil.gsFormat("{0}/{1}/", MODEL_DIR, year);
        } else if (imageType == TYPE_PROMOTION) {
            year = code.substring(PROMOTION_SUBSTRING_START, PROMOTION_SUBSTRING_END);
            if (!StringUtils.isNumeric(year)) {
                return null;
            }
            year = StringUtil.gsFormat("{0}/{1}/", PROMOTION_DIR, year);
        } else {
            if (codeRule == OLD_CODEING) {
                year = code.substring(PRODUCT_SUBSTRING_START, PRODUCT_SUBSTRING_END);
            } else if (codeRule == NEW_20_CODEING) {
                year = code.substring(PRODUCT_NEW_CODEING_SUBSTRING_START, PRODUCT_NEW_CODEING_SUBSTRING_END);
            }
            if (!StringUtils.isNumeric(year)) {
                return null;
            }
            year = StringUtil.gsFormat("{0}/{1}/", PRODUCT_DIR, year);
        }

        return year;
    }
}