package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ColorCard;
import com.icicle.masterdb.pojo.vo.ColorCardVO;
import com.icicle.masterdb.service.ColorCardExcelService;
import com.icicle.masterdb.service.ColorCardService;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.ExcelUtil;
import com.icicle.masterdb.util.LogUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyuling
 */
@Service
public class ColorCardExcelServiceImpl extends AbstractService<ColorCard> implements ColorCardExcelService {
    private final int ROW_COUNT = 2;
    private final int COLORCARD_NO = 0;
    private final int COLORCARD_NAME = 1;
    List<String> colorCodeList;
    List<ColorCard> colorCardList;
    @Resource
    private ColorCardService colorCardService;

    @Override
    public ColorCardVO parseExcel(InputStream inputStream) {
        colorCodeList = new ArrayList<>();
        colorCardList = colorCardService.findAll();
        ColorCardVO colorCardVO = new ColorCardVO();
        Workbook workbook;
        List<ColorCard> colorCardList = new ArrayList<>();
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
                ColorCard colorCards = getColorCard(colValues);
                if (colorCards != null) {
                    colorCardList.add(colorCards);
                }

            }
        }
        colorCardVO.setColorCards(colorCardList);
        return colorCardVO;
    }

    /**
     * 验证某个单元个的数字是否有错 如果有错 返回该单元格的所在列  如果没有错 则返回空
     *
     * @param colValues
     * @return
     */
    public Boolean dataValidate(String[] colValues) {
        if (colValues[COLORCARD_NO] == null) {
            return false;
        }

        if (colValues[COLORCARD_NAME] == null) {
            return false;
        }
        //去除重复
        if (colorCodeList != null && colorCodeList.contains(colValues[0])) {
            return false;
        }
        colorCodeList.add(colValues[COLORCARD_NO]);
        if (!getColorNo(colValues[COLORCARD_NO])) {
            return false;
        }
        if (!getColorName(colValues[COLORCARD_NAME])) {
            return false;
        }
        return true;
    }

    public ColorCard getColorCard(String[] colValues) {
        if (colValues == null || colValues.length < ROW_COUNT) {
            return null;
        }
        ColorCard colorCard = new ColorCard();
        colorCard.setColorCardCode(colValues[COLORCARD_NO]);
        colorCard.setColorCardName(colValues[COLORCARD_NAME]);
        colorCard.setLanguage(SessionManager.getLanguage());
        colorCard.setCreatedBy(SessionManager.getLoginName());
        colorCard.setCreatedDate(DateUtil.now());
        colorCard.setLastUpdateBy(SessionManager.getLoginName());
        colorCard.setLastUpdateDate(DateUtil.now());
        colorCard.setStatus(1);
        return colorCard;
    }

    private Boolean getColorNo(String code) {
        if (code == null) {
            return false;
        }
        Long colorCard = colorCardList.stream().filter(d -> d.getColorCardCode().equals(code)).count();
        if (colorCard != 0) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean getColorName(String name) {
        if (name == null) {
            return false;
        }
        Long colorCard = colorCardList.stream().filter(d -> d.getColorCardName().equals(name)).count();
        if (colorCard != 0) {
            return false;
        } else {
            return true;
        }
    }
}
