package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.ProductMapper;
import com.icicle.masterdb.dao.masterdb.ViewCatalogMapper;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.model.*;
import com.icicle.masterdb.pojo.vo.ExcelCell;
import com.icicle.masterdb.pojo.vo.ProductExcelVO;
import com.icicle.masterdb.service.*;
import com.icicle.masterdb.service.constant.CommonConstant;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author CodeGeneratorUtil
 */
@Service
public class ProductExcelServiceImpl implements ProductExcelService {
    private final int PRODUCT_ROW_NUM = 37;
    private final int ESTIMATE_RATE = 0;
    private final int ORDER_NO = 1;
    private final int DEV_NO = 2;
    private final int STYLE_NO = 3;
    private final int MODEL_NO = 4;
    private final int LINE = 5;
    private final int PRODUCT_CODE = 6;
    private final int MATERIALNO = 7;
    private final int MATERIAL_NAME = 8;
    private final int PRODUCRCLASS_NAME = 9;
    private final int COLOR_NAME = 10;
    private final int UNIT_PRICE = 11;
    private final int PRODUCT_KIND = 12;
    private final int YEAR = 13;
    private final int NATURE_SEASON = 14;
    private final int DEV_SEASON = 15;
    private final int WAVE = 16;
    private final int BAND = 17;
    private final int ICICLE_GROUP = 18;
    private final int UP_DATE = 19;
    private final int COLORCARD_NO = 20;
    private final int COLORCARD_NAME = 21;
    private final int COLORSYSTERM_NAME = 22;
    private final int WORK_GROUP = 23;
    private final int SIZE_GROUP = 24;
    private final int BRAND = 25;
    private final int CODE = 26;
    private final int STANDARD = 27;
    private final int BATCH = 28;
    private final int UOM = 29;
    private final int STYLE_RULE = 30;
    private final int OPR = 31;
    private final int OPR_DATE = 32;
    private final int SUPPLIER = 33;
    private final int STANDARD_COST = 34;
    private final int CATEGORY_THIRD_LEVEL = 35;
    private final int MATERIAL_NAME_TAG = 36;
    private final int YEAR_LENGTH = 2;
    private final int ESTIMATE_RATE_LENRTH = 15;
    private final int VALID_LENTH_TWENTY = 20;
    private final int BEFOREYEAR = 20;


    @Resource
    private ViewCatalogMapper viewCatalogMapper;
    @Resource
    private FabricService fabricService;
    @Resource
    private ColorCardService colorCardService;
    @Resource
    private ProductService productService;
    @Resource
    private ProductColumnService productColumnService;

    @Resource
    private ProductMapper productMapper;
    @Resource
    private ViewSelectItemService viewSelectItemService;
    private List<ViewCatalog> viewCatalogList;
    private String workgroup = "";
    private List<String> productcodeList;
    private List<Fabric> fabricList;
    private List<ColorCard> colorCardList;
    private List<ViewSelectItem> viewSelectItemList;
    private List<String> codeList;
    private List<String[]> columList;
    private List<String> brandList;
    @Override
    public ProductExcelVO parseExcel(boolean update, String path) throws IOException, InvalidFormatException {
        try {
            brandList = productMapper.selectBrand();
            viewCatalogList = viewCatalogMapper.selectAll();
            viewSelectItemList = viewSelectItemService.findAllItem(StringUtil.itemTableNameList());
            fabricList = fabricService.selectFabricPart();
            colorCardList = colorCardService.selectCardPart();
            productcodeList = new ArrayList<>();
            codeList = null;
            columList = new ArrayList<>();
        } catch (Exception e) {
            LogUtil.getLogger(this.getClass()).error(e.getMessage(), e);
            return null;
        }
        ProductExcelVO productExcelVO = new ProductExcelVO();
        //产生的product集合
        List<Product> productList = new ArrayList<>();
        //产生的productAttributeExtend集合
        List<ProductAttributeExtend> productAttributeExtendList = new ArrayList<>();
        // 存在错误的行
        List<ExcelCell> errorRows = new ArrayList<>();
        List<ExcelCell> exictRows = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(new FileInputStream(path));
        Sheet sheet = workbook.getSheetAt(0);
        //以第一行数据为准，如果之后的行没有导入第一行所在列中包含的数据，则忽略
        List<Integer> indexRow = new ArrayList<>();
        //获取表头行，如果为空，则该文件不符合要求返回空
        Row row0 = sheet.getRow(0);
        if (row0 == null) {
            return null;
        }
        //获取列数
        int colNum = row0.getPhysicalNumberOfCells();
        if (colNum !=  MATERIAL_NAME_TAG + 1) {
            productExcelVO.setFlag(false);
            return productExcelVO;
        } else {
            productExcelVO.setFlag(true);
        }
        //获得总行数
        int rowNum = sheet.getLastRowNum() + 1;

        if (colNum != PRODUCT_ROW_NUM) {
            return null;
        }
        //获取第一行的内容
        Row firstRow = sheet.getRow(1);

        if (firstRow == null) {
            return null;
        }
        //以数据行的第一行为准，第一行之后的该列有数据，也不读这一列了
        for (int i = 0; i < colNum; i++) {
            if (firstRow.getCell(i) == null) {
                indexRow.add(i);
            }
        }
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        CellStyle styleExict = workbook.createCellStyle();
        styleExict.setFillForegroundColor(IndexedColors.RED.getIndex());
        styleExict.setFillPattern(CellStyle.SOLID_FOREGROUND);
        List<String> list = new ArrayList();
        for (int i = 0; i < rowNum; i++) {
            String[] colValues = new String[PRODUCT_ROW_NUM];
            Row row = sheet.getRow(i);
            if (row == null) {
                break;
            }
            for (int j = 0; j < colNum; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    continue;
                } else {
                    if (Cell.CELL_TYPE_NUMERIC == cell.getCellType() && HSSFDateUtil.isCellDateFormatted(cell)) {
                        colValues[j] = DateUtil.getDateStringWithSeperate("/", cell.getDateCellValue());
                    } else {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String s = cell.getRichStringCellValue().getString();
                        colValues[j] = StringUtils.isBlank(s.trim()) ? null : s.trim();
                    }
                }
            }
            //如果第一行中该列没有数据，后面的该列数据也都赋值为空
            if (!update) {
                for (Integer integer : indexRow) {
                    colValues[integer] = null;
                }
            }

            if (i == 0) {
                continue;
            }
            //sku为空直接忽略
            if (colValues[PRODUCT_CODE] != null) {
                columList.add(colValues);
                list.add(colValues[PRODUCT_CODE]);
            }
        }
        if (list != null && list.size() > 0) {
            codeList = getProductList(list);
        }
        if (columList.size() > 0) {
            for (int i = 0; i < columList.size(); i++) {
                String[] itm = columList.get(i);
                //验证重复性
                int vale = vaildExist(itm, update);
                if (vale != -999) {
                    exictRows.add(new ExcelCell(i + 2, vale));
                    continue;
                }

                //验证单元格正确性
                int checkRow = dataValidate(itm, update);
                if (checkRow != -999) {
                    errorRows.add(new ExcelCell(i + 2, checkRow));
                    continue;
                }
            }
        }
        //获取完全正确的产品list
        if (exictRows.size() == 0 && errorRows.size() == 0) {
            Product product = null;
            ProductAttributeExtend productAttributeExtend = null;
            List<Map<String,Object>> saleTaxRateList = productMapper.selectSaleTaxRate();
            for (String[] itrm : columList) {
                product = getProductByArray(itrm, update,saleTaxRateList);
                productAttributeExtend = getProductAttributeExtendByArray(itrm);
                if (product != null) {
                    if (product.getStandardCost() == null) {
                        product.setStandardCost(BigDecimal.ZERO);
                    }
                    productList.add(product);
                    productAttributeExtendList.add(productAttributeExtend);
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
                    if (exictRows != null && exictRows.size() > 0) {
                        if (exictRows.stream().filter(p -> p.getX() == n && p.getY() == m).count() > 0) {
                            cell.setCellStyle(styleExict);
                        }
                    }
                    if (errorRows != null && errorRows.size() > 0) {
                        if (errorRows.stream().filter(p -> p.getY() == m && p.getX() == n).count() > 0) {
                            //防止cell为空无法设背景色，先赋值
                            if (m == PRODUCRCLASS_NAME + 1 || m == BRAND + 1 || m == UNIT_PRICE + 1) {
                                row.createCell(j).setCellValue("空");
                                ExcelUtil.setCellColor(style, row, j);
                            } else {
                                cell.setCellStyle(style);
                            }
                        }
                    }
                }
            }
        }
        FileOutputStream fileOut = new FileOutputStream(path);
        workbook.write(fileOut);
        fileOut.close();

        productExcelVO.setProductList(productList);
        productExcelVO.setExictCodes(exictRows);
        productExcelVO.setErrorRows(errorRows);
        productExcelVO.setProductAttributeExtendList(productAttributeExtendList);
        return productExcelVO;
    }

    @Override
    public SXSSFWorkbook exportToExcel(List<String> titles, List<Map<String, Object>> exportDatas) {
        SXSSFWorkbook book = new SXSSFWorkbook();
        Sheet sheet1 = book.createSheet("sheet1");
        Row sheet1Row = sheet1.createRow(0);
        CellStyle cellStyle = book.createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);

        /**
         * 设置列名
         */
        Cell cell = null;
        Map<String, String> columns = productColumnService.inserProductColum();
        for (int i = 0; i < titles.size(); i++) {
            cell = sheet1Row.createCell(i);
            cell.setCellValue(productColumnService.matchTitle(titles.get(i), columns));
            cell.setCellStyle(cellStyle);
        }

        /**
         * 设置列值
         */
        int rows = 1;
        for (Map<String, Object> data : exportDatas) {
            Row row = sheet1.createRow(rows++);

            int initCellNo = 0;
            int titleSize = titles.size();
            for (int i = 0; i < titleSize; i++) {
                String key = titles.get(i);
                Object object = data.get(key);
                if (object == null) {
                    row.createCell(initCellNo).setCellValue("");
                } else {
                    row.createCell(initCellNo).setCellValue(String.valueOf(object));
                }
                initCellNo++;
            }
        }

        return book;

    }


    /**
     * 验证每行表格特定格子中的数据是否合法
     *
     * @param colValues
     * @return 该行是否合法
     */
    public int dataValidate(String[] colValues, boolean update) {
        if (colValues == null || colValues.length < STANDARD_COST) {
            //return "数据列数存在问题";
            return -1;
        } else {
            //8
            if (colValues[PRODUCT_CODE] != null) {
//                if (!productService.checkProductCode(colValues[PRODUCT_CODE])) {
//                    return PRODUCT_CODE + 1;
//                    //return "SKU";
//                }
                String productCode = colValues[PRODUCT_CODE];
//                含有重复款号报重复款号错误
                if (productcodeList != null && productcodeList.contains(productCode)) {
                    return PRODUCT_CODE + 1;
                    //return "SKU";
                }
                productcodeList.add(productCode);
            }
            if (!update) {
                if (colValues[PRODUCRCLASS_NAME] == null) {
                    return PRODUCRCLASS_NAME + 1;
                    //return "品名";
                }
                if (colValues[BRAND] == null) {
                    return BRAND + 1;
                }
                if(colValues[BRAND] != null){
                    if(!brandList.contains(colValues[BRAND])){
                        return BRAND + 1;
                        //return "品牌";
                    }
                }
                if(colValues[UNIT_PRICE] == null){
                    return UNIT_PRICE + 1;
                    //return "标准单价";
                }
            }
            //1
            if (colValues[ESTIMATE_RATE] != null && colValues[ESTIMATE_RATE].length() > ESTIMATE_RATE_LENRTH) {
                return ESTIMATE_RATE + 1;
                //return "预估倍率";
            }
            //2
            if (colValues[ORDER_NO] != null && !limitLengthTwenty(colValues[ORDER_NO])) {
                return ORDER_NO + 1;
                //return "订单序号";
            }
            //3
            if (colValues[DEV_NO] != null && !limitLengthTwenty(colValues[DEV_NO])) {
                return DEV_NO + 1;
                //return "开发号";
            }
            //4
            if (colValues[STYLE_NO] != null && !limitLengthTwenty(colValues[STYLE_NO])) {
                return STYLE_NO + 1;
                //return "款型号";
            }
            //5
            if (colValues[MODEL_NO] != null && !limitLengthTwenty(colValues[MODEL_NO])) {
                return MODEL_NO + 1;
                //return "样板号";
            }
            //6
            if (colValues[LINE] != null) {
                long selectLine = getViewSelectItemList(colValues[LINE], "select_line");
                if (selectLine == 0) {
                    return LINE + 1;
                    //return "线路";
                }
            }

            //8
            if (colValues[MATERIALNO] != null) {
//                定义面料编号的最小长度为5
                if (colValues[MATERIALNO].length() < MODEL_NO) {
                    return MATERIALNO + 1;
                    //return "面料编号";
                }
                if (colValues[MATERIAL_NAME] == null) {
                    return MATERIAL_NAME + 1;
                    //return "面料名称";
                }
//                long fabrics = getFabricList(colValues[MATERIALNO], colValues[MATERIAL_NAME]);
//                if (fabrics == 0) {
//                    return MATERIAL_NAME + 1;
//                    //return "面料名称";
//                }

            }
            //12
            if (colValues[UNIT_PRICE] != null && !StringUtil.isDecimal(colValues[UNIT_PRICE].trim())) {
                return UNIT_PRICE + 1;
                //return "零售价";
            }
            //13
            if (colValues[PRODUCT_KIND] != null) {
                List<String> costumeCategoryLevel1List = productMapper.selectCostumeCategoryLevel1();
                if(!costumeCategoryLevel1List.contains(colValues[PRODUCT_KIND])){
                    return PRODUCT_KIND + 1;
                    //return "品种";
                }
            }
//            年份长度要求4 14
            if (colValues[YEAR] != null) {
                List<String> yearList = ExcelUtil.getYearList();
                if(!yearList.contains(colValues[YEAR])){
                    return YEAR + 1;
                }
                //return "年份";
            }
            //15
            if (colValues[NATURE_SEASON] != null) {
                long natureSeason = getViewSelectItemList(colValues[NATURE_SEASON], "select_nature_season");
                if (natureSeason == 0) {
                    return NATURE_SEASON + 1;
                    //return "季节";
                }
            }
            //16
            if (colValues[DEV_SEASON] != null) {
                long devSeason = getViewSelectItemList(colValues[DEV_SEASON], "select_dev_season");
                if (devSeason == 0) {
                    return DEV_SEASON + 1;
                    //return "开发季";
                }
            }
            //17
            if (colValues[WAVE] != null) {
                long selectWave = getViewSelectItemList(colValues[WAVE], "select_wave");
                if (selectWave == 0) {
                    return WAVE + 1;
                    //return "波";
                }
            }
            //18
            if (colValues[BAND] != null) {
                long selectBand = getViewSelectItemList(colValues[BAND], "select_band");
                if (selectBand == 0) {
                    return BAND + 1;
                    //return "段";
                }
            }
            //19
            if (colValues[ICICLE_GROUP] != null) {
                long selectGroup = getViewSelectItemList(colValues[ICICLE_GROUP], "select_group");
                if (selectGroup == 0) {
                    return ICICLE_GROUP + 1;
                    //return "组别";
                }

            }
            String date = colValues[UP_DATE];
            if (colValues[UP_DATE] != null && !DateUtil.isValidDate(date)) {
                return UP_DATE + 1;
                //return "上市日期";


            }
            if (colValues[COLORCARD_NO] != null) {
                if (colValues[COLORCARD_NAME] == null) {
                    return COLORCARD_NAME + 1;
                    //return "色卡名";
                }
//                long colorCard = getCardList(colValues[COLORCARD_NO], colValues[COLORCARD_NAME]);
//                if (colorCard == 0) {
//                    return COLORCARD_NAME + 1;
//                    //return "色卡名";
//                }

            }
            if (colValues[COLORCARD_NAME] != null && colValues[COLORCARD_NO] == null) {
                return COLORCARD_NO + 1;
                //return "色卡编号";
            }
            //24
            if (colValues[WORK_GROUP] != null) {
                long workGroup = viewSelectItemList.stream().filter(d -> d.getItemValue().equals(colValues[WORK_GROUP]) && "select_work_group".equals(d.getTableName())).count();
                if (workGroup == 0) {
                    return WORK_GROUP + 1;
                    //return "工作组";
                }
            }
            //25
            if (colValues[SIZE_GROUP] != null) {
                long sizeGroup = getViewSelectItemList(colValues[SIZE_GROUP], "select_size_group");
                if (sizeGroup == 0) {
                    return SIZE_GROUP + 1;
                    //return "尺寸组";
                }
            }
            //28
            if (colValues[STANDARD] != null) {
                long selectStandard = getViewSelectItemList(colValues[STANDARD], "select_standard");
                if (selectStandard == 0) {
                    return STANDARD + 1;
                    //return "规格";
                }
            }
            //29
            if (colValues[BATCH] != null) {
                long selectBatch = getViewSelectItemList(colValues[BATCH], "select_batch");
                if (selectBatch == 0) {
                    return BATCH + 1;
                    //return "批次";
                }

            }
            //30
            if (colValues[UOM] != null) {
                long selectUom = getViewSelectItemList(colValues[UOM], "select_uom");
                if (selectUom == 0) {
                    return UOM + 1;
                    //return "单位";
                }
            }
            //31
            if (colValues[STYLE_RULE] != null) {
                long codeStandard = getViewSelectItemList(colValues[STYLE_RULE], "select_code_standard");
                if (codeStandard == 0) {
                    return STYLE_RULE + 1;
                    //return "款号规则";
                }
            }
            //32
            if (!StringUtils.isBlank(colValues[OPR_DATE]) && !DateUtil.isValidDate(colValues[OPR_DATE])) {
                return OPR_DATE + 1;
                //return "建档日期";
            } //34
            if (colValues[OPR] != null && !limitLengthTwenty(colValues[OPR])) {
                return OPR + 1;
                //return "建档人";
            }

            //34
            if (colValues[SUPPLIER] != null && !limitLengthTwenty(colValues[SUPPLIER])) {
                return SUPPLIER + 1;
                //return "供应商编号";
            }

            //35
            if (colValues[STANDARD_COST] != null && !StringUtil.isDecimal(colValues[STANDARD_COST])) {
                return STANDARD_COST + 1;
                //return "预估成本";
            }

            //36
            if (colValues[CATEGORY_THIRD_LEVEL] != null) {
                if(!limitLengthTwenty(colValues[CATEGORY_THIRD_LEVEL])){
                    return CATEGORY_THIRD_LEVEL + 1;
                }

                //验证品种是否是产品子类的小类
                int count = 0;
                if(StringUtils.isEmpty(colValues[PRODUCT_KIND])){
                    List<String> costumeCategoryLevel2List = productMapper.selectCostumeCategoryLevel2();
                    count = Collections.frequency(costumeCategoryLevel2List,colValues[CATEGORY_THIRD_LEVEL]);
                }else{
                    String productSecondLevel = productMapper.getProductSecondLevel(colValues[PRODUCT_KIND],colValues[CATEGORY_THIRD_LEVEL]);
                    count = StringUtils.isEmpty(productSecondLevel)?0:1;
                }
                if(count == 0){
                    return CATEGORY_THIRD_LEVEL + 1;
                }
                //return "商品小类";
            }

            return -999;
        }
    }


    /**
     * 根据更新和新建获取已经存在的款号
     *
     * @param list
     * @return
     */
    public List<String> getProductList(List<String> list) {
        if (list == null) {
            return null;
        }
        List<List<String>> prolist = ListUtil.splitArrayList(list, 50);
        List<String> codeList = new ArrayList<>();
        for (int i = 0; i < prolist.size(); i++) {
            List<String> productList = null;
            productList = productMapper.exictProductCode(prolist.get(i));
            codeList.addAll(productList);
        }
        return codeList;
    }


    /**
     * 返回错误款号所在单元格
     *
     * @param colValues
     * @param update
     * @return
     */
    public int vaildExist(String[] colValues, boolean update) {
        //更新情况不验证重复性

        if (codeList == null || codeList.size() == 0) {
            if (!update) {
                //新增直接返回无已经存在的
                return -999;
            } else {
                //更新时，为空，代表sku全部不存在，返回的全是错误
                return PRODUCT_CODE + 1;
            }

        }

        if (colValues == null || colValues.length < STANDARD_COST) {
            //return "数据列数存在问题";
            return 0;
        } else {
            if (colValues[PRODUCT_CODE] != null) {
                if (!update) {
                    //新增存在则错误
                    if (codeList.stream().filter(p -> p.equals(colValues[PRODUCT_CODE])).count() > 0) {
                        return PRODUCT_CODE + 1;
                    }
                } else {
                    //不存在则错误
                    if (codeList.stream().filter(p -> p.equals(colValues[PRODUCT_CODE])).count() <= 0) {
                        return PRODUCT_CODE + 1;
                    }
                }

            }
        }
        return -999;
    }

    /**
     * @param colValues
     * @return 得到product实体对象
     */

    public Product getProductByArray(String[] colValues, boolean update,List<Map<String,Object>> saleTaxRateList) {
        if (colValues == null || colValues.length < PRODUCT_ROW_NUM) {
            return null;
        } else {
            Product product = new Product();
            product.setProductCode(colValues[PRODUCT_CODE]);
            product.setProductName(colValues[PRODUCRCLASS_NAME]);
            String productKindCode = StringUtils.isEmpty(colValues[PRODUCT_KIND])?"":productMapper.getProductKind(colValues[PRODUCT_KIND]);//品种
            String workGroup = getViewSelectItemKey(colValues[WORK_GROUP],"select_work_group");//工作组
            if (!StringUtils.isBlank(productKindCode) && !StringUtils.isBlank(workGroup)) {
                String systemcode = workGroup.concat(productKindCode);
                if(systemcode!=""){
                    for (ViewCatalog viewCatalog : viewCatalogList) {
                        if (systemcode.equals(viewCatalog.getSystemCode())) {
                            product.setCateDl(viewCatalog.getCa1());
                            product.setCateZl(viewCatalog.getCa2());
                            product.setCateXl(viewCatalog.getCa3());
                        }
                    }
                }
            }
            if (colValues[UNIT_PRICE] != null) {
                product.setUnitPrice(new BigDecimal(colValues[UNIT_PRICE].trim()));
            }
            if (colValues[STANDARD_COST] != null) {
                product.setStandardCost(new BigDecimal(colValues[STANDARD_COST]));
            }
            product.setStatus(true);
            if (update) {
                product.setSyncStatus(1);
                product.setUom(getViewSelectItemKey(colValues[UOM],"select_uom"));
            } else {
                //新增
                product.setSyncStatus(2);
                if (colValues[UOM] == null) {
                    product.setUom("件");
                } else {
                    product.setUom(getViewSelectItemKey(colValues[UOM],"select_uom"));
                }
            }
            product.setCreatedBy(SessionManager.getLoginName());
            product.setCreationDate(DateUtil.now());
            product.setBrand(getViewSelectItemKey(colValues[BRAND],"select_brand"));
            product.setLastUpdatedBy(SessionManager.getLoginName());
            product.setLastUpdateDate(DateUtil.now());
            product.setSyncRecord(false);
            Integer productLineId = StringUtils.isEmpty(colValues[LINE])?0:productMapper.getProductLineId(colValues[LINE]);
            product.setProductLineId(productLineId.toString());
            product.setCategoryLevel1(productKindCode);
            String categorySecondLevelCode = StringUtils.isEmpty(productKindCode)?productMapper.getProductSecondLevels(colValues[CATEGORY_THIRD_LEVEL])
                    :productMapper.getProductSecondLevel(colValues[PRODUCT_KIND],colValues[CATEGORY_THIRD_LEVEL]);
            product.setCategoryLevel2(StringUtils.isEmpty(categorySecondLevelCode)?"":categorySecondLevelCode);
            Float saleTaxRate = 0.13f;
            if(!StringUtils.isEmpty(categorySecondLevelCode)){
                Map<String,Object> secondLevelMap = saleTaxRateList.stream().filter(a->a.get("category_code").toString().equals(CommonConstant.COSTUMECATEGORYKEY) && a.get("product_sub_category_code").toString().equals(categorySecondLevelCode) && a.get("sub_category_level").toString().equals("2")).findFirst().orElse(null);
                if(secondLevelMap.get("sale_tax_rate")!=null && !secondLevelMap.get("sale_tax_rate").toString().equals("") && !secondLevelMap.get("sale_tax_rate").toString().equals("0.0")){
                    saleTaxRate = Float.parseFloat(secondLevelMap.get("sale_tax_rate").toString());
                }else{
                    if(!StringUtils.isEmpty(productKindCode)){
                        Map<String,Object> firstLevelMap = saleTaxRateList.stream().filter(a->a.get("category_code").toString().equals(CommonConstant.COSTUMECATEGORYKEY) && a.get("product_sub_category_code").toString().equals(productKindCode) && a.get("sub_category_level").toString().equals("1")).findFirst().orElse(null);
                        if(firstLevelMap.get("sale_tax_rate")!=null && !firstLevelMap.get("sale_tax_rate").toString().equals("") && !firstLevelMap.get("sale_tax_rate").toString().equals("0.0")){
                            saleTaxRate = Float.parseFloat(firstLevelMap.get("sale_tax_rate").toString());
                        }
                    }
                }
            }
            product.setSaleRate(new BigDecimal(saleTaxRate));
            return product;
        }
    }


    public ProductAttributeExtend getProductAttributeExtendByArray(String[] colValues) {
        if (colValues.length < PRODUCT_ROW_NUM) {
            return null;
        } else {
            ProductAttributeExtend productAttributeExtend = new ProductAttributeExtend();
            productAttributeExtend.setEstimatedRate(colValues[ESTIMATE_RATE]);
            productAttributeExtend.setOrderNo(colValues[ORDER_NO]);
            productAttributeExtend.setDevNo(colValues[DEV_NO]);
            productAttributeExtend.setStyleNo(colValues[STYLE_NO]);
            productAttributeExtend.setModelNo(StringUtils.isEmpty(colValues[MODEL_NO])?"":colValues[MODEL_NO]);
            productAttributeExtend.setProductCode(colValues[PRODUCT_CODE]);
            productAttributeExtend.setMaterialNo(StringUtils.isEmpty(colValues[MATERIALNO])?"":colValues[MATERIALNO]);
            productAttributeExtend.setMaterialName(colValues[MATERIAL_NAME]);
            productAttributeExtend.setColourSystem(StringUtils.isEmpty(colValues[COLORSYSTERM_NAME])?"":colValues[COLORSYSTERM_NAME]);
            String productKind = colValues[PRODUCT_KIND]==null?"":colValues[PRODUCT_KIND];
            String productClass = productMapper.getProductKind(productKind);
            productAttributeExtend.setProductClass(StringUtils.isEmpty(productClass)?"":productClass);
            productAttributeExtend.setNatureSeason(getViewSelectItemKey(colValues[NATURE_SEASON],"select_nature_season"));
            productAttributeExtend.setSalesDate(colValues[UP_DATE]);
            productAttributeExtend.setColorCardNo(StringUtils.isEmpty(colValues[COLORCARD_NO])?"":colValues[COLORCARD_NO]);
            productAttributeExtend.setColorCardName(colValues[COLORCARD_NAME]);
            productAttributeExtend.setColorName(StringUtils.isEmpty(colValues[COLOR_NAME])?"":colValues[COLOR_NAME]);
            productAttributeExtend.setDevSeason(getViewSelectItemKey(colValues[DEV_SEASON],"select_dev_season"));
            productAttributeExtend.setWave(getViewSelectItemKey(colValues[WAVE],"select_wave"));
            productAttributeExtend.setIcicleBand(getViewSelectItemKey(colValues[BAND],"select_band"));
            productAttributeExtend.setIcicleGroup(getViewSelectItemKey(colValues[ICICLE_GROUP],"select_group"));
            productAttributeExtend.setLine(colValues[LINE]);
            if(!StringUtils.isEmpty(colValues[YEAR])){
                productAttributeExtend.setYear(Integer.parseInt(colValues[YEAR].substring(0,4)));
            }
            productAttributeExtend.setWorkGroup(getViewSelectItemKey(colValues[WORK_GROUP],"select_work_group"));
            productAttributeExtend.setSizeGroup(getViewSelectItemKey(colValues[SIZE_GROUP],"select_size_group"));
            productAttributeExtend.setCode(colValues[CODE]);
            productAttributeExtend.setStandard(getViewSelectItemKey(colValues[STANDARD],"select_standard"));
            if (colValues[BATCH] != null) {
                productAttributeExtend.setBatch(Integer.parseInt(getViewSelectItemKey(colValues[BATCH],"select_batch")));
            }
            productAttributeExtend.setStyleRule(getViewSelectItemKey(colValues[STYLE_RULE],"select_code_standard"));
            productAttributeExtend.setSupplier(colValues[SUPPLIER]);
            productAttributeExtend.setOpr(colValues[OPR]);
            productAttributeExtend.setOpDate(colValues[OPR_DATE]);
            productAttributeExtend.setMaterialNameTag(StringUtils.isBlank(colValues[MATERIAL_NAME_TAG])?"":colValues[MATERIAL_NAME_TAG]);
            return productAttributeExtend;
        }
    }
    private long getViewSelectItemList(String column, String tablename) {
        if(tablename.equals("select_line")){
            return viewSelectItemList.stream().filter(d -> d.getItemKey().equals(column) && d.getTableName().equals(tablename)).count();
        }else{
            return viewSelectItemList.stream().filter(d -> d.getItemValue().equals(column) && d.getTableName().equals(tablename)).count();
        }
    }

    private long getFabricList(String before, String after) {
        return fabricList.stream().filter(d -> d.getFabricCode().equals(before) && d.getFabricName().equals(after)).count();
    }

    private long getCardList(String before, String after) {
        return colorCardList.stream().filter(d -> d.getColorCardCode().equals(before) && d.getColorCardName().equals(after)).count();
    }
    private String getViewSelectItemKey(String column,String tablename){
        String itemKey = "";
        if(!StringUtils.isEmpty(column)){
            List<ViewSelectItem> viewSelectItemFilterList =viewSelectItemList.stream().filter(d -> d.getTableName().equals(tablename)).collect(Collectors.toList());
            if(!ListUtil.isBlank(viewSelectItemFilterList)){
                for(ViewSelectItem viewSelectItem : viewSelectItemFilterList){
                    if(viewSelectItem.getItemValue().equals(column)){
                        itemKey = viewSelectItem.getItemKey();
                        break;
                    }
                }
            }
        }
        return itemKey;
    }

    /**
     * 限长20位
     *
     * @param column
     * @return
     */
    private Boolean limitLengthTwenty(String column) {
        if (column.length() > VALID_LENTH_TWENTY) {
            return false;
        } else {
            return true;
        }
    }

}
