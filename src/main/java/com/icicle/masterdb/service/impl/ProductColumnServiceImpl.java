package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.service.ProductColumnService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyuling
 * @version 2018/8/8 9:21
 */
@Service
public class ProductColumnServiceImpl implements ProductColumnService {
    public static final String PRO_CODE = "product_code";
    public static final String PRO_NAME = "product_name";
    public static final String SEC_CODE = "security_code";
    public static final String EST_RATE = "estimated_rate";
    public static final String ORDER_NO = "order_no";
    public static final String DEV_NO = "dev_no";
    public static final String STYLE_NO = "style_no";
    public static final String MODEL_NO = "model_no";
    public static final String LINE = "line";
    public static final String MATERIAL_NO = "material_no";
    public static final String MATERIAL_NAME = "material_name";
    public static final String COLOR_SYSTERM = "colour_system";
    public static final String UNIT_PRICE = "unit_price";
    public static final String PRODUCTCLASS_VALUE = "productclass_value";
    public static final String YEAR = "year";
    public static final String NATURE_SEASON = "nature_season";
    public static final String DEV_SEASON = "dev_season";
    public static final String WAVE = "wave";
    public static final String BAND = "band";
    public static final String ICICLE_GROUP = "icicle_group";
    public static final String SALES_DATE = "sales_date";
    public static final String COLOR_CARD_NO = "color_card_no";
    public static final String COLOR_CARD_NAME = "color_card_name";
    public static final String COLOR_NAME = "color_name";
    public static final String WORKGROUP_VALUE = "workgroup_value";
    public static final String SIZE_GROUP = "size_group";
    public static final String BRAND = "brand";
    public static final String CODE = "code";
    public static final String STANDARD = "standard";
    public static final String BATCH = "batch";
    public static final String UOM = "uom";
    public static final String STYLE_RULE = "style_rule";
    public static final String SUPPLIER = "supplier";
    public static final String OPR = "opr";
    public static final String OP_DATE = "op_date";
    public static final String STANDARD_COST = "standard_cost";

    @Override
    public String matchTitle(String column, Map<String, String> columns) {
        return columns.get(column);
    }

    @Override
    public Map<String, String> inserProductColum() {
        Map<String, String> columns = new HashMap<String, String>(0);
        columns.put(SEC_CODE, "商品码");
        columns.put(EST_RATE, "预估倍率");
        columns.put(ORDER_NO, "订单序号");
        columns.put(DEV_NO, "开发号");
        columns.put(STYLE_NO, "款型号");
        columns.put(MODEL_NO, "样板号");
        columns.put(LINE, "线路");
        columns.put(PRO_CODE, "产品编码");
        columns.put(MATERIAL_NO, "面料编号");
        columns.put(MATERIAL_NAME, "面料名称");
        columns.put(PRO_NAME, "品名");
        columns.put(COLOR_SYSTERM, "花色");
        columns.put(UNIT_PRICE, "零售价");
        columns.put(PRODUCTCLASS_VALUE, "品种");
        columns.put(YEAR, "年份");
        columns.put(NATURE_SEASON, "季节");
        columns.put(DEV_SEASON, "开发季");
        columns.put(WAVE, "波");
        columns.put(BAND, "段");
        columns.put(ICICLE_GROUP, "组");
        columns.put(SALES_DATE, "上市日期");
        columns.put(COLOR_CARD_NO, "色卡编号");
        columns.put(COLOR_CARD_NAME, "色卡名称");
        columns.put(COLOR_NAME, "颜色（色名）");
        columns.put(WORKGROUP_VALUE, "工作组");
        columns.put(SIZE_GROUP, "尺寸组");
        columns.put(BRAND, "品牌");
        columns.put(CODE, "统一编码");
        columns.put(STANDARD, "规格");
        columns.put(BATCH, "批次");
        columns.put(UOM, "单位");
        columns.put(STYLE_RULE, "款号规则");
        columns.put(SUPPLIER, "供应商编号");
        columns.put(OPR, "建档人");
        columns.put(OP_DATE, "建档日期");
        columns.put(STANDARD_COST, "预估成本");
        return columns;
    }
}
