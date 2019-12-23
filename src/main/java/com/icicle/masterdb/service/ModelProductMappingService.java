package com.icicle.masterdb.service;


import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ModelProductMapping;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by CodeGeneratorUtil on 2017-12-05 15:28:06.
 *
 * @author liurenhua
 */
public interface ModelProductMappingService extends Service<ModelProductMapping> {

    /**
     * 将excel解析成对象数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    List<ModelProductMapping> parseExcel(InputStream inputStream) throws IOException, InvalidFormatException;

    /**
     * 批量分区插入
     *
     * @param list
     * @return
     */
    int saveList(List<ModelProductMapping> list);


    /**
     * 根据映射关系的Id删除映射关系
     *
     * @param mappingId
     * @return
     */
    int deleteByMappingId(Integer mappingId);


    /**
     * 单条更新图片和模特的映射关系
     *
     * @param modelProductMapping
     * @return
     */
    int updateMapping(ModelProductMapping modelProductMapping);
}