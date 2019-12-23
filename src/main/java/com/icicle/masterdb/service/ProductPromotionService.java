package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ProductImage;
import com.icicle.masterdb.model.ProductPromotion;
import com.icicle.masterdb.model.ViewProductAttributeSelect;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @author wangyuling
 * @version 2018/6/26 10:20
 */
public interface ProductPromotionService extends Service<ProductPromotion> {

    /**
     * 解析上传的excel文件
     *
     * @param inputStream
     * @return
     */
    List<ProductPromotion> parseExcel(InputStream inputStream);

    /**
     * 批量上传推广图片
     *
     * @param file
     * @return
     */
    List<ProductImage> uploadImage(MultipartFile[] file, List<ProductPromotion> list);

    /**
     * 获取推广列表
     *
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param sSearch
     * @return
     */
    DataTableRecord getList(String sEcho, Integer pageIndex, Integer pageSize, String sSearch);

    /**
     * 获取露出类型列表
     *
     * @return
     */
    List<ViewProductAttributeSelect> getshowTypeList();

    /**
     * 验证推广id是否存在
     *
     * @param id
     * @return
     */
    int checkPromotionId(String id);

    /**
     * 验证推广图片编码是否存在
     *
     * @param code
     * @return
     */
    int checkPromotionCode(String code);

    /**
     * 执行excel文件的写入
     * @param list
     * @return
     */
    int savePromotionList(List<ProductPromotion> list);
}
