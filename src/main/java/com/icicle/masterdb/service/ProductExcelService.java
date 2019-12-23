package com.icicle.masterdb.service;

import com.icicle.masterdb.pojo.vo.ProductExcelVO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductExcelService {
    /**
     * 接续excel文件  得到一个封装好的店铺格式
     * @param update
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    ProductExcelVO parseExcel(boolean update,String path) throws IOException, InvalidFormatException;


    /**
     * 解析产品类别的excel数据
     * @param update
     * @param path
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
//    List<T> parseCategoryExcel(boolean update, String path) throws IOException, InvalidFormatException;

    /**
     * 根据所选列导出数据
     * @param titles
     * @param exportDatas
     */
    SXSSFWorkbook exportToExcel(List<String> titles, List<Map<String, Object>> exportDatas);
}
