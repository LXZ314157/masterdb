package com.icicle.masterdb.service.constant;

import com.icicle.masterdb.pojo.vo.BurgeonTableVO;

import java.util.HashMap;
import java.util.Map;

public class MapConstant {

    public static Map<String,BurgeonTableVO> itemMap = new HashMap<>();

    static{
        initItemMap();
    }

    /**
     * 伯俊属性标识和主数据表名对应
     */
    private static void initItemMap(){
        itemMap.put("select_company",new BurgeonTableVO("C_CORP",""));
        itemMap.put("select_position",new BurgeonTableVO("HR_POST",""));
        itemMap.put("select_store_type",new BurgeonTableVO("C_STOREGRADE",""));
        itemMap.put("select_clerk_level",new BurgeonTableVO("C_STATION_LEVEL",""));
        itemMap.put("select_buyer_type",new BurgeonTableVO("C_CUSRANK",""));
        itemMap.put("select_store_level",new BurgeonTableVO("C_STOREGRADE",""));
        itemMap.put("select_store_category",new BurgeonTableVO("C_STORETYPE_JZ",""));
        itemMap.put("select_responsibilitycenter",new BurgeonTableVO("BAS_RES_CENTER",""));
        itemMap.put("select_material_department",new BurgeonTableVO("C_SUPPLIERDEPART",""));
        itemMap.put("select_expenditure_category",new BurgeonTableVO("C_PAYTYPE",""));
        itemMap.put("select_sub_expenditure_category",new BurgeonTableVO("C_PAYSUBCLASS",""));
        itemMap.put("select_mall_type",new BurgeonTableVO("C_STOREATTRIBVALUE","DIM4"));
        itemMap.put("select_zone",new BurgeonTableVO("C_STOREATTRIBVALUE","DIM8"));
        itemMap.put("select_sub_zone",new BurgeonTableVO("C_STOREATTRIBVALUE","DIM9"));
        itemMap.put("select_manage_center",new BurgeonTableVO("C_STOREATTRIBVALUE","DIM6"));
        itemMap.put("select_store_ownership",new BurgeonTableVO("C_STOREATTRIBVALUE","DIM1"));
        itemMap.put("select_store_image_category",new BurgeonTableVO("C_STOREATTRIBVALUE","DIM5"));
        itemMap.put("select_LOGO_version",new BurgeonTableVO("C_STOREATTRIBVALUE","DIM3"));
        itemMap.put("select_line",new BurgeonTableVO("M_DIM","DIM8"));
        itemMap.put("select_install_version",new BurgeonTableVO("C_STOREATTRIBVALUE","DIM7"));
        itemMap.put("select_store_class",new BurgeonTableVO("C_STOREATTRIBVALUE","DIM2"));
        itemMap.put("select_colour_system",new BurgeonTableVO("M_DIM","DIM14"));
        itemMap.put("select_color_no",new BurgeonTableVO("M_DIM","DIM13"));
        itemMap.put("select_buyer_mng_level",new BurgeonTableVO("C_CUSATTRIBVALUE","DIM5"));
        itemMap.put("select_buyer_pattern",new BurgeonTableVO("C_CUSATTRIBVALUE","DIM1"));
        itemMap.put("select_city_level",new BurgeonTableVO("C_CUSATTRIBVALUE","DIM2"));
        itemMap.put("select_product_line",new BurgeonTableVO("M_DIM","DIM1"));
        itemMap.put("select_band",new BurgeonTableVO("M_DIM","DIM10"));
        itemMap.put("select_brand",new BurgeonTableVO("M_DIM","DIM21"));
        itemMap.put("select_dev_season",new BurgeonTableVO("M_DIM","DIM4"));
        itemMap.put("select_group",new BurgeonTableVO("M_DIM","DIM11"));
        itemMap.put("select_nature_season",new BurgeonTableVO("M_DIM","DIM3"));
        itemMap.put("select_wave",new BurgeonTableVO("M_DIM","DIM9"));
        itemMap.put("select_work_group",new BurgeonTableVO("M_DIM","DIM12"));
    }

}
