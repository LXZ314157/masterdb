package com.icicle.masterdb.service;

import com.icicle.masterdb.pojo.vo.ProductInfoExcelVO;

/**
 * @author CodeGeneratorUtil
 */
public interface ProductInfoExcelService {
    /**
     * sku excel 表的解析
     * @param update
     * @return
     */
    ProductInfoExcelVO parseExcel(String path,boolean update);

}
