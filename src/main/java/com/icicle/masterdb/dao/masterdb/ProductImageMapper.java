package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ProductImage;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author liurenhua
 */
public interface ProductImageMapper extends MyMapper<ProductImage> {

    /**
     * 通过模特编号查找与其相关的图片
     *
     * @param modelCode
     * @return
     */
    List<ProductImage> findProImgByMolCode(String modelCode);

    /**
     * 通过产品编号查找与其相关的所有模特图
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
     * 根据图片Id删除图片
     *
     * @param imageId
     * @return
     */
    int deleteByImageId(Integer imageId);

    /**
     * 获取产品或者模特的默认显示图片
     *
     * @param code
     * @return
     */
    ProductImage findDefaultImage(String code);

    /**
     * 批量更新图片
     *
     * @param list
     * @return
     */
    int updateList(List<ProductImage> list);

    /**
     * 批量更新图片
     *
     * @param lastUpdatedBy
     * @param lastUpdatedDate
     * @param imgurls
     * @return
     */
    int updateBatch(String lastUpdatedBy, Date lastUpdatedDate, @Param("imgurls") List<String> imgurls);

    /**
     * 查找系统中已经存在的图片url
     *
     * @param urls
     * @return
     */
    List<String> findExistList(List<ProductImage> urls);

    /**
     * 查找副图
     * @param code
     * @return
     */
    List<ProductImage> findImageNotDefault(String code);
}