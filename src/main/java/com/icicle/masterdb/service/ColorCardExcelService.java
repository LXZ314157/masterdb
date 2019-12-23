package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ColorCard;
import com.icicle.masterdb.pojo.vo.ColorCardVO;

import java.io.InputStream;

/**
 * @author CodeGeneratorUtil
 */
public interface ColorCardExcelService extends Service<ColorCard> {
    /**
     * 批量导入色卡的解析
     * @param inputStream
     * @return
     */
    ColorCardVO parseExcel(InputStream inputStream);
}
