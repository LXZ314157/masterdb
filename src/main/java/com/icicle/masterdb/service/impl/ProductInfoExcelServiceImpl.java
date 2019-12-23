package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.ProductInfo;
import com.icicle.masterdb.model.SkuSize;
import com.icicle.masterdb.pojo.vo.ExcelCell;
import com.icicle.masterdb.pojo.vo.ProductInfoExcelVO;
import com.icicle.masterdb.service.ProductInfoExcelService;
import com.icicle.masterdb.service.ProductService;
import com.icicle.masterdb.service.SkuSizeService;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.ExcelUtil;
import com.icicle.masterdb.util.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
@Service
public class ProductInfoExcelServiceImpl implements ProductInfoExcelService {
    private final int ROW_COUNT = 3;
    private final int PRODUCT_CODE = 0;
    private final int COLOR = 1;
    private final int SIZE = 2;
    private List<SkuSize> skuSizeList;
    private List<String> skuList;
    private List<String[]> columList;
    @Resource
    private SkuSizeService skuSizeService;

    @Resource
    private ProductService productService;

    @Override
    public ProductInfoExcelVO parseExcel(String path, boolean update) {
        skuSizeList = skuSizeService.findBySize();
        skuList = new ArrayList<>();
        ProductInfoExcelVO productInfoExcelVO = new ProductInfoExcelVO();
        List<ExcelCell> errorRow = new ArrayList<>();
        Workbook workbook = null;
        List<ProductInfo> productInfoList = new ArrayList<>();
        columList = new ArrayList<>();
        try {
            workbook = WorkbookFactory.create(new FileInputStream(path));
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
        if (colNum != ROW_COUNT) {
            productInfoExcelVO.setFlag(false);
            return productInfoExcelVO;
        } else {
            productInfoExcelVO.setFlag(true);
        }
        //获得总行数
        int rowNum = sheet.getLastRowNum() + 1;
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);

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
            //如果不是更新操作，那么把第一行中不含有的列，标为空
            if (!update) {
                for (Integer integer : indexRow) {
                    colValues[integer] = null;
                }
            }
            if (i == 0) {
                continue;
            }
            Integer checkRow=null;
            if(colValues[PRODUCT_CODE]!=null){
                columList.add(colValues);
                checkRow= dataValidate(colValues);
            }

            if (checkRow != null) {
                errorRow.add(new ExcelCell(i + 1, checkRow));
            }
        }

        //获取完全正确的产品list
        if (errorRow.size() == 0) {
            List<ProductInfo> productInfo = null;
            for (String[] itrm : columList) {
                productInfo = getProductInfo(itrm);
                if (productInfo != null) {
                    productInfoList.addAll(productInfo);
                }
            }
        } else {
            //错误单元格标颜色
            for (int i = 0; i < rowNum; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    break;
                }
                for (int j = 0; j < colNum; j++) {
                    Cell cell = row.getCell(j);
                    int m = j + 1, n = i + 1;
                    if (errorRow != null && errorRow.size() > 0) {
                        if (errorRow.stream().filter(p -> (int) p.getY() == m && p.getX() == n).count() > 0) {
                            if (m == SIZE + 1 && cell == null||(m == COLOR + 1 && cell == null)) {
                                row.createCell(j).setCellValue("空");
                                ExcelUtil.setCellColor(style,row,j);
                            }
                            else{
                                cell.setCellStyle(style);
                            }

                        }
                    }
                }
            }
        }
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        productInfoExcelVO.setProductInfoList(productInfoList);
        productInfoExcelVO.setErrorRows(errorRow);
        return productInfoExcelVO;
    }

    /**
     * 验证某个单元个的数字是否有错 如果有错 返回该单元格的所在列  如果没有错 则返回空
     *
     * @param colValues
     * @return
     */
    public Integer dataValidate(String[] colValues) {
        if (skuSizeList == null && skuSizeList.size() == 0) {
            return 0;
            //return "无尺码比对表";
        }
        if (colValues[PRODUCT_CODE] != null && !productService.checkProductCode(colValues[PRODUCT_CODE])) {
            return PRODUCT_CODE + 1;
            //return "产品编码";
        }
        if (colValues[COLOR] == null) {
            return COLOR + 1;
            //return "颜色";
        }
        if (colValues[SIZE] == null) {
            return SIZE + 1;
            //return "尺码";
        }
        if (colValues[SIZE] != null) {
            String[] str = colValues[SIZE].split(",");
            for (int i = 0; i < str.length; i++) {
                String size = str[i].toUpperCase();
                long skusize = skuSizeList.stream().filter(d -> d.getSizeCode().equals(size)).count();
                if (skusize == 0) {
                    if (StringUtils.isBlank(size)) {
                        continue;
                    } else {
                        return SIZE + 1;
                        //return "尺码";
                    }

                }
            }
        }
        return null;
    }

    public List<ProductInfo> getProductInfo(String[] colValues) {
        if (colValues == null || colValues.length < ROW_COUNT) {
            return null;
        }
        List<ProductInfo> productInfoList = new ArrayList<>();
        String[] str = colValues[SIZE].split(",");
        for (int i = 0; i < str.length; i++) {
            if (StringUtils.isBlank(str[i])) {
                continue;
            }
            String sku = colValues[PRODUCT_CODE].concat(str[i]);
            /**
             * 去掉重复的sku
             */
            if (skuList != null && skuList.contains(sku)) {
                continue;
            }
            skuList.add(sku);
            ProductInfo productInfo = new ProductInfo();
            productInfo.setSku(sku);
            productInfo.setProductCode(colValues[PRODUCT_CODE]);
            productInfo.setColor(colValues[COLOR]);
            productInfo.setStatus(true);
            productInfo.setCreatedBy(SessionManager.getLoginName());
            productInfo.setLastUpdatedBy(SessionManager.getLoginName());
            productInfo.setSize(str[i]);
            productInfo.setCreationDate(DateUtil.now());
            productInfo.setLastUpdateDate(DateUtil.now());
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }

}
