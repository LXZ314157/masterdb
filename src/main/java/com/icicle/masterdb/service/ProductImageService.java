package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by CodeGeneratorUtil on 2017-11-13 15:56:11.
 *
 * @author liurenuha
 */
public interface ProductImageService extends Service<ProductImage> {

    /**
     * 批量上传多张图片
     *
     * @param file
     * @param imageType
     * @return
     */
    List<ProductImage> uploadImage(MultipartFile[] file, Integer imageType, Integer codeRule);


    /**
     * 创建datatable形式
     *
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param sSearch
     * @return
     */
    DataTableRecord createDataTable(String sEcho, Integer pageIndex,
                                    Integer pageSize, String sSearch);

    /**
     * 查找一个模特相关的所有产品图
     *
     * @param modelCode 模特code
     * @return
     */
    List<ProductImage> findProImgByMolCodes(String modelCode);

    /**
     * 查找一个产品相关的所有模特图
     *
     * @param productCode
     * @return
     */
    List<ProductImage> findModImgByProCode(String productCode);

    /**
     * 通过款号查询产品的imageurl
     *
     * @param productCode
     * @return
     */
    ProductImage findOneByCode(String productCode);

    /**
     * 批量分组插入图片
     *
     * @param list
     * @return
     */
    int saveList(List<ProductImage> list);

    /**
     * 单张删除图片
     *
     * @param imgId
     * @return
     */
    int deleteByImageId(Integer imgId);


    /**
     * 获取模特或者产品的默认显示图片
     *
     * @param code
     * @return
     */
    ProductImage findDefaultImage(String code);

    /**
     * 获取图片上传时间
     *
     * @param time
     * @param content
     */
    void getServerTime(long time, String content);

    /**
     * 查找副图
     * @param code
     * @return
     */
    List<ProductImage> findImageNotDefault(String code);
}