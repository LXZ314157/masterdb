package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.dao.masterdb.ProductAttributeMapper;
import com.icicle.masterdb.dao.masterdb.ProductMapper;
import com.icicle.masterdb.dao.masterdb.ViewProductAttributeSelectMapper;
import com.icicle.masterdb.model.ProductAttribute;
import com.icicle.masterdb.model.ProductDimensionMerchandising;
import com.icicle.masterdb.model.ViewProductAttributeSelect;
import com.icicle.masterdb.service.ProductAttributeItemService;
import com.icicle.masterdb.service.ProductAttributeService;
import com.icicle.masterdb.service.ProductDimensionMerchandisingService;
import com.icicle.masterdb.util.ExcelUtil;
import com.icicle.masterdb.util.ListUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author CodeGeneratorUtil
 */
@Service
public class ProductAttributeServiceImpl extends AbstractService<ProductAttribute> implements ProductAttributeService {
    @Resource
    private ProductAttributeMapper productAttributeMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductDimensionMerchandisingService productDimensionMerchandisingService;

    private final int SINGLE_ITEM = 1;
    private final int MUL_ITEM = 2;
    private final int ROW_PRODUCT = 0;
    private final int ROW_COVER = 1;
    private final int ROW_PLAN_LOC = 2;
    private final int ROW_PURCHASE_LOC = 3;
    private final int ROW_DISPLAY = 4;
    private final int ROW_SERIES = 5;
    private final int ROW_SERIES_REMARK = 6;
    private final int ROW_ECO_MATERIAL = 7;
    private final int ROW_ECO_COLOR = 8;
    private final int ROW_ECO_TEC = 9;
    private final int ROW_ECO_REMARK = 10;
    private final int ROW_OFF_WEIBO = 11;
    private final int ROW_SELLING_POINT = 12;
    private final int ROW_RECORD = 13;
    /**
     * 选项之间的分隔符
     */
    private final String SEPERATOR = ",";
    /**
     * excel表格的列数
     */
    private int rowCount = 14;
    @Resource
    private ProductAttributeItemService productAttributeItemService;
    @Resource
    private ViewProductAttributeSelectMapper viewProductAttributeSelectMapper;

    private List<ViewProductAttributeSelect> itemVOList;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "新增或编辑企划属性值")
    public int editProductAttribute(List<ProductAttribute> list, String productCode) {
        if (ListUtil.isBlank(list) || StringUtils.isBlank(productCode)) {
            return 0;
        }
        List<ProductAttribute> updateList;
        List<ProductAttribute> insertList;
        int count = 0;
        List<ProductAttribute> attributeList = productAttributeMapper.findAttributeByCode(productCode);
        count += saveAttributeListAsModel(list, productCode);
        if (ListUtil.isBlank(attributeList)) {
            count += productAttributeMapper.insertList(list);
            return count;
        }
        /**
         *   如果插入的产品编码在数据库中已经存在，
         *   则将选项分组，已经存在的更新，不存在的插入
         */
        updateList = ListUtil.getSameOrDiffList(attributeList, list, true);
        insertList = ListUtil.getSameOrDiffList(attributeList, list, false);
        if (!ListUtil.isBlank(insertList)) {
            count += productAttributeMapper.insertList(insertList);
        }
        if (!ListUtil.isBlank(updateList)) {
            count += productAttributeMapper.updateAttributeList(updateList);
        }
        return count;
    }

    @Override
    public List<ProductAttribute> findAttributeByCode(String productCode) {
        return productAttributeMapper.findAttributeByCode(productCode);
    }

    @Override
    public int saveAttributeListAsModel(List<ProductAttribute> list, String productCode) {
        if (ListUtil.isBlank(list)) {
            return 0;
        }
        ProductDimensionMerchandising pdm = productDimensionMerchandisingService.findByCode(productCode);
        ProductDimensionMerchandising p = transferAttributeAsModel(list, productCode);
        int count;
        if (pdm == null) {
            count = productDimensionMerchandisingService.saveMerchandising(p);
        } else {
            count = productDimensionMerchandisingService.updateMerchandising(p);
        }
        return count;
    }


    /**
     * 将竖表数据转成横表
     *
     * @param list
     * @param productCode 产品编码
     * @return
     */
    public ProductDimensionMerchandising transferAttributeAsModel(List<ProductAttribute> list, String productCode) {
        ProductDimensionMerchandising p = new ProductDimensionMerchandising();
        p.setProductCode(productCode);
        String planningLocation = getAttributeValue(list, "planning_location");
        String purchaseLocation = getAttributeValue(list, "purchase_location");
        String displayLocation = getAttributeValue(list, "display_location");
        String series = getAttributeValue(list, "series");
        String seriesRemark = getAttributeValue(list, "series_remark");
        String ecoWayMaterial = getAttributeValue(list, "eco_way_material");
        String ecoWayColor = getAttributeValue(list, "eco_way_color");
        String ecoWayTec = getAttributeValue(list, "eco_way_technics");
        String ecoWayRemark = getAttributeValue(list, "eco_way_remark");
        String sellingPoint = getAttributeValue(list, "selling_point");
        String record = getAttributeValue(list, "record");
        String officialWeibo = getAttributeValue(list, "official_weibo");
        String checkCover = getAttributeValue(list, "single_search_cover");
        if (StringUtils.isNumeric(planningLocation)) {
            p.setPlanningLocation(Integer.parseInt(planningLocation));
        }
        if (StringUtils.isNumeric(purchaseLocation)) {
            p.setPurchaseLocation(Integer.parseInt(purchaseLocation));
        }
        if (StringUtils.isNumeric(displayLocation)) {
            p.setDisplayLocation(Integer.parseInt(displayLocation));
        }
        if (StringUtils.isNumeric(series)) {
            p.setSeries(Integer.parseInt(series));
        }
        if (StringUtils.isNumeric(ecoWayMaterial)) {
            p.setEcoWayMaterial(Integer.parseInt(ecoWayMaterial));
        }
        if (StringUtils.isNumeric(ecoWayColor)) {
            p.setEcoWayColor(Integer.parseInt(ecoWayColor));
        }
        if (StringUtils.isNumeric(ecoWayTec)) {
            p.setEcoWayTechnics(Integer.parseInt(ecoWayTec));
        }

        if (StringUtils.isNumeric(officialWeibo)) {
            int num = Integer.parseInt(officialWeibo);
            if (num == 1) {
                p.setOfficialWeibo(true);
            } else if (num == 0) {
                p.setOfficialWeibo(false);
            }
        }

        if (StringUtils.isNumeric(checkCover)) {
            int num = Integer.parseInt(checkCover);
            if (num == 1) {
                p.setSingleSearchCover(true);
            } else if (num == 0) {
                p.setSingleSearchCover(false);
            }
        }
        p.setEcoWayRemark(ecoWayRemark);

        if (StringUtils.isNumeric(sellingPoint)) {
            p.setSellingPoint(Integer.parseInt(sellingPoint));
        }

        p.setRecord(record);
        p.setSeriesRemark(seriesRemark);
        return p;
    }

    @Override
    public List<ProductDimensionMerchandising> transferAttrToMerchandising(List<ProductAttribute> list) {
        if (ListUtil.isBlank(list)) {
            return null;
        }

        List<ProductDimensionMerchandising> attributeList = new ArrayList<>();
        //把产品编码相同的放在一起
        Map<String, List<ProductAttribute>> map = list.stream().collect(Collectors.groupingBy(ProductAttribute::getProductCode));
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, List<ProductAttribute>> entry : map.entrySet()) {
                List<ProductAttribute> t = entry.getValue();
                if (!ListUtil.isBlank(t)) {
                    ProductDimensionMerchandising productDimensionMerchandising = transferAttributeAsModel(t, entry.getKey());
                    attributeList.add(productDimensionMerchandising);
                }
            }
        }
        return attributeList;
    }

    @Override
    public List<ProductAttribute> parseExcel(InputStream inputStream) throws IOException, InvalidFormatException {
        Workbook workbook;
        workbook = WorkbookFactory.create(inputStream);

        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            return null;
        }
        //结果
        List<ProductAttribute> resultList = new ArrayList<>();
        //获取列数
        int colNum = sheet.getLastRowNum() + 1;
        itemVOList = viewProductAttributeSelectMapper.selectAll();
        for (int i = 1; i < colNum; i++) {
            Row row = sheet.getRow(i);
            String[] colValues = ExcelUtil.parseRow(row, rowCount);
            //如果没有解析出数据，或数据长度为0
            if (colValues == null || colValues.length == 0
                    || StringUtils.isBlank(colValues[0])) {
                continue;
            }
            //去除表格中重复的数据
            if (codeRepeat(resultList, colValues[0])) {
                continue;
            }

            List<ProductAttribute> attributeList = getAttributeListByArray(colValues);
            if (!ListUtil.isBlank(attributeList)) {
                resultList.addAll(attributeList);
            }
        }
        //最后过滤一下，把数据库中已经存在的企划，把不存在的产品编码去除掉
        return filterAttribute(resultList);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveList(List<ProductAttribute> list) {
        if (ListUtil.isBlank(list)) {
            return 0;
        }
        int count = 0;
        List<List<ProductAttribute>> splitList = ListUtil.splitArrayList(list, 100);

        if (!ListUtil.isBlank(splitList)) {
            for (List<ProductAttribute> t : splitList) {
                count += super.save(t);
            }
        }

        return count;
    }

    /**
     * 去掉list中产品编码不存在的对象，
     * 去除list中在企划表中已经存在的编码
     *
     * @param list
     * @return
     */
    public List<ProductAttribute> filterAttribute(List<ProductAttribute> list) {

        if (ListUtil.isBlank(list)) {
            return list;
        }

        List<List<ProductAttribute>> splitArrayList = ListUtil.splitArrayList(list, 200);
        List<ProductAttribute> insertList = new ArrayList<>();
        if (!ListUtil.isBlank(splitArrayList)) {
            for (List<ProductAttribute> tempList : splitArrayList) {

                List<String> distinctList = new ArrayList<>();

                tempList.stream().forEach(p -> {
                    if (!distinctList.contains(p.getProductCode())) {
                        distinctList.add(p.getProductCode());
                    }
                });

                if (ListUtil.isBlank(distinctList)) {
                    continue;
                }
                List<String> legalCodeList = productMapper.findLegalProductCode(distinctList);
                List<String> existCodeList = productAttributeMapper.findExistCodeList(distinctList);
                if (ListUtil.isBlank(legalCodeList)) {
                    return null;
                }

                if (ListUtil.isBlank(existCodeList)) {
                    insertList.addAll(tempList);
                } else {
                    List<ProductAttribute> l = tempList.stream().filter(p -> (legalCodeList.contains(p.getProductCode())
                            && !existCodeList.contains(p.getProductCode()
                    ))).collect(Collectors.toList());
                    if (!ListUtil.isBlank(l)) {
                        insertList.addAll(l);
                    }
                }
            }
        }
        return insertList;
    }

    private List<ProductAttribute> getAttributeListByArray(String[] colValues) {

        List<ProductAttribute> list = new ArrayList<>();

        if (colValues == null || colValues.length < rowCount) {
            return null;
        }
        if (StringUtils.isBlank(colValues[ROW_PRODUCT])) {
            return null;
        }

        String productCode = colValues[ROW_PRODUCT];

        ProductDimensionMerchandising p = new ProductDimensionMerchandising();
        p.setProductCode(productCode);

        if (StringUtils.isNumeric(colValues[ROW_COVER])) {
            ProductAttribute t = getProductAttribute(colValues[ROW_COVER], 1, "single_search_cover", productCode);
            if (t != null) {
                list.add(t);
            }
        }


        if (!StringUtils.isBlank(colValues[ROW_PLAN_LOC])) {
            ProductAttribute t = getProductAttribute(colValues[ROW_PLAN_LOC], 2, "planning_location", productCode);
            if (t != null) {
                list.add(t);
            }
        }

        if (!StringUtils.isBlank(colValues[ROW_PURCHASE_LOC])) {
            ProductAttribute t = getProductAttribute(colValues[ROW_PURCHASE_LOC], 2, "purchase_location", productCode);
            if (t != null) {
                list.add(t);
            }
        }

        if (!StringUtils.isBlank(colValues[ROW_DISPLAY])) {
            ProductAttribute t = getProductAttribute(colValues[ROW_DISPLAY], 2, "display_location", productCode);
            if (t != null) {
                list.add(t);
            }
        }

        if (!StringUtils.isBlank(colValues[ROW_SERIES])) {
            ProductAttribute t = getProductAttribute(colValues[ROW_SERIES], 2, "series", productCode);
            if (t != null) {
                list.add(t);
            }
        }

        if (!StringUtils.isBlank(colValues[ROW_SERIES_REMARK])) {
            ProductAttribute t = getProductAttribute(colValues[ROW_SERIES_REMARK], 0, "series_remark", productCode);
            if (t != null) {
                list.add(t);
            }

        }

        if (!StringUtils.isBlank(colValues[ROW_ECO_MATERIAL])) {
            ProductAttribute t = getProductAttribute(colValues[ROW_ECO_MATERIAL], 2, "eco_way_material", productCode);
            if (t != null) {
                list.add(t);
            }
        }

        if (!StringUtils.isBlank(colValues[ROW_ECO_COLOR])) {
            ProductAttribute t = getProductAttribute(colValues[ROW_ECO_COLOR], 2, "eco_way_color", productCode);
            if (t != null) {
                list.add(t);
            }
        }

        if (!StringUtils.isBlank(colValues[ROW_ECO_TEC])) {
            ProductAttribute t = getProductAttribute(colValues[ROW_ECO_TEC], 2, "eco_way_technics", productCode);
            if (t != null) {
                list.add(t);
            }
        }

        if (!StringUtils.isBlank(colValues[ROW_ECO_REMARK])) {
            ProductAttribute t = getProductAttribute(colValues[ROW_ECO_REMARK], 0, "eco_way_remark", productCode);
            if (t != null) {
                list.add(t);
            }
        }

        if (!StringUtils.isBlank(colValues[ROW_OFF_WEIBO])) {
            ProductAttribute t = getProductAttribute(colValues[ROW_OFF_WEIBO], 1, "official_weibo", productCode);
            if (t != null) {
                list.add(t);
            }
        }

        if (!StringUtils.isBlank(colValues[ROW_SELLING_POINT])) {
            ProductAttribute t = getProductAttribute(colValues[ROW_SELLING_POINT], 2, "selling_point", productCode);
            if (t != null) {
                list.add(t);
            }
        }

        if (!StringUtils.isBlank(colValues[ROW_RECORD])) {
            ProductAttribute t = getProductAttribute(colValues[ROW_RECORD], 0, "record", productCode);
            if (t != null) {
                list.add(t);
            }
        }

        return list;
    }

    /**
     * 将横表中的某个值转换成竖表
     *
     * @param hasItem     是否有选项  有选项的将被转换为int型数字
     * @param attrCode    编码
     * @param productCode 产品编码
     * @param attrValue   属性的值
     * @return
     */
    private ProductAttribute getProductAttribute(String attrValue, int hasItem, String attrCode, String productCode) {

        ProductAttribute productAttribute = new ProductAttribute();
        productAttribute.setProductCode(productCode);
        productAttribute.setAttrCode(attrCode);
        if (hasItem == 1) {
            int num = 0;
            if (StringUtils.isNumeric(attrValue)) {
                num = Integer.parseInt(attrValue);
            }
            if (num == SINGLE_ITEM) {
                productAttribute.setAttrValue("1");
            } else {
                productAttribute.setAttrValue("0");
            }
        } else if (hasItem == MUL_ITEM) {
            int num = getCode(attrValue, attrCode);
            productAttribute.setAttrValue(String.valueOf(num));
        } else {
            productAttribute.setAttrValue(attrValue);
        }

        return productAttribute;
    }

    public String getAttributeValue(List<ProductAttribute> list, String attrCode) {
        if (ListUtil.isBlank(list)) {
            return null;
        }
        String[] value = new String[1];
        list.stream().filter(p -> Objects.equals(p.getAttrCode(), attrCode)).findFirst().ifPresent(p -> value[0] = p.getAttrValue());
        return value[0];
    }

    /**
     * 判断是否和已经录入的数据重复
     *
     * @param list
     * @param code
     * @return
     */
    private boolean codeRepeat(List<ProductAttribute> list, String code) {
        if (ListUtil.isBlank(list) || StringUtils.isBlank(code)) {
            return false;
        }
        return list.stream().anyMatch(p -> Objects.equals(code, p.getProductCode()));
    }

    private int getCode(String column, String code) {
        if (StringUtils.isBlank(column) || StringUtils.isBlank(code)) {
            return 0;
        }
        //临时数组，为方便使用lamda表达式
        int[] result = {0};

        if (!StringUtils.isBlank(column) && !ListUtil.isBlank(itemVOList)) {
            String[] planningPos = column.split(SEPERATOR);
            for (String t : planningPos) {
                if (!StringUtils.isBlank(t)) {
                    itemVOList.stream().filter(item -> Objects.equals(item.getName(), t) && Objects.equals(item.getDefCode(), code))
                            .findFirst().ifPresent(item -> result[0] = item.getCode().intValue() | result[0]);
                }
            }
            return result[0];
        }
        return 0;
    }

}