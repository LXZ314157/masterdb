package com.icicle.masterdb.pojo.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lvxuezhan
 * @version 2019-08-15 19:14
 */
public class SyncProductInfoVO {

    private Integer size_id;
    private String size_desc;
    private Integer size_martxcol;
    private String product_id;
    private String product_code;
    private String product_name;
    private String product_desc;
    private String dev_no;
    private String style_no;
    private String model_no;
    private String material_no;
    private String material_name;
    private String color_name;
    private String color_card_name;
    private String colour_system;
    private String uom;
    private String material_name_tag;
    private String author;
    private String field;
    private Date sales_date;
    private Date real_sales_date;
    private BigDecimal pur_taxdis;
    private BigDecimal sale_taxdis;
    private BigDecimal standard_cost;
    private String size_group_name;
    private String brand_name;
    private String product_line_name;
    private String year_name;
    private String nature_season_name;
    private String dev_season_name;
    private String cate_dl_name;
    private String cate_zl_name;
    private String cate_xl_name;
    private String line_name;
    private String wave_name;
    private String band_name;
    private String icicle_group_name;
    private String workgroup_value_name;
    private String publish_house;
    private Integer publish_date;
    private String expenditure_category_name;
    private String sub_expenditure_category_name;
    private String department_name;
    private String company_name;
    private String cost_center_name;
    private Integer creater;
    private Integer status;
    private String is_agt;
    private String is_bigmoney;
    private String is_material;
    private String is_present;
    private String invoice_show;
    private String is_advanced_ord;
    private Date expired_date;
    private String pck_minqty;
    private String size_code;
    private Integer limit_matapply_qty;
    private String color;
    private SyncMaterialContrainVO product_mat;

    public SyncMaterialContrainVO getProduct_mat() {
        return product_mat;
    }

    public void setProduct_mat(SyncMaterialContrainVO product_mat) {
        this.product_mat = product_mat;
    }

    public BigDecimal getStandard_cost() {
        return standard_cost;
    }

    public void setStandard_cost(BigDecimal standard_cost) {
        this.standard_cost = standard_cost;
    }

    public String getInvoice_show() {
        return invoice_show;
    }

    public void setInvoice_show(String invoice_show) {
        this.invoice_show = invoice_show;
    }

    public String getSize_code() {
        return size_code;
    }

    public void setSize_code(String size_code) {
        this.size_code = size_code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getDev_no() {
        return dev_no;
    }

    public void setDev_no(String dev_no) {
        this.dev_no = dev_no;
    }

    public String getStyle_no() {
        return style_no;
    }

    public void setStyle_no(String style_no) {
        this.style_no = style_no;
    }

    public String getModel_no() {
        return model_no;
    }

    public void setModel_no(String model_no) {
        this.model_no = model_no;
    }

    public String getMaterial_no() {
        return material_no;
    }

    public void setMaterial_no(String material_no) {
        this.material_no = material_no;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public String getMaterial_name_tag() {
        return material_name_tag;
    }

    public void setMaterial_name_tag(String material_name_tag) {
        this.material_name_tag = material_name_tag;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    public String getColor_card_name() {
        return color_card_name;
    }

    public void setColor_card_name(String color_card_name) {
        this.color_card_name = color_card_name;
    }

    public String getColour_system() {
        return colour_system;
    }

    public void setColour_system(String colour_system) {
        this.colour_system = colour_system;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Date getSales_date() {
        return sales_date;
    }

    public void setSales_date(Date sales_date) {
        this.sales_date = sales_date;
    }

    public Date getReal_sales_date() {
        return real_sales_date;
    }

    public void setReal_sales_date(Date real_sales_date) {
        this.real_sales_date = real_sales_date;
    }

    public BigDecimal getPur_taxdis() {
        return pur_taxdis;
    }

    public void setPur_taxdis(BigDecimal pur_taxdis) {
        this.pur_taxdis = pur_taxdis;
    }

    public BigDecimal getSale_taxdis() {
        return sale_taxdis;
    }

    public void setSale_taxdis(BigDecimal sale_taxdis) {
        this.sale_taxdis = sale_taxdis;
    }

    public String getSize_group_name() {
        return size_group_name;
    }

    public void setSize_group_name(String size_group_name) {
        this.size_group_name = size_group_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getProduct_line_name() {
        return product_line_name;
    }

    public void setProduct_line_name(String product_line_name) {
        this.product_line_name = product_line_name;
    }

    public String getYear_name() {
        return year_name;
    }

    public void setYear_name(String year_name) {
        this.year_name = year_name;
    }

    public String getNature_season_name() {
        return nature_season_name;
    }

    public void setNature_season_name(String nature_season_name) {
        this.nature_season_name = nature_season_name;
    }

    public String getDev_season_name() {
        return dev_season_name;
    }

    public void setDev_season_name(String dev_season_name) {
        this.dev_season_name = dev_season_name;
    }

    public String getCate_dl_name() {
        return cate_dl_name;
    }

    public void setCate_dl_name(String cate_dl_name) {
        this.cate_dl_name = cate_dl_name;
    }

    public String getCate_zl_name() {
        return cate_zl_name;
    }

    public void setCate_zl_name(String cate_zl_name) {
        this.cate_zl_name = cate_zl_name;
    }

    public String getCate_xl_name() {
        return cate_xl_name;
    }

    public void setCate_xl_name(String cate_xl_name) {
        this.cate_xl_name = cate_xl_name;
    }

    public String getLine_name() {
        return line_name;
    }

    public void setLine_name(String line_name) {
        this.line_name = line_name;
    }

    public String getWave_name() {
        return wave_name;
    }

    public void setWave_name(String wave_name) {
        this.wave_name = wave_name;
    }

    public String getBand_name() {
        return band_name;
    }

    public void setBand_name(String band_name) {
        this.band_name = band_name;
    }

    public String getIcicle_group_name() {
        return icicle_group_name;
    }

    public void setIcicle_group_name(String icicle_group_name) {
        this.icicle_group_name = icicle_group_name;
    }

    public String getWorkgroup_value_name() {
        return workgroup_value_name;
    }

    public void setWorkgroup_value_name(String workgroup_value_name) {
        this.workgroup_value_name = workgroup_value_name;
    }

    public String getPublish_house() {
        return publish_house;
    }

    public void setPublish_house(String publish_house) {
        this.publish_house = publish_house;
    }

    public Integer getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Integer publish_date) {
        this.publish_date = publish_date;
    }

    public String getExpenditure_category_name() {
        return expenditure_category_name;
    }

    public void setExpenditure_category_name(String expenditure_category_name) {
        this.expenditure_category_name = expenditure_category_name;
    }

    public String getSub_expenditure_category_name() {
        return sub_expenditure_category_name;
    }

    public void setSub_expenditure_category_name(String sub_expenditure_category_name) {
        this.sub_expenditure_category_name = sub_expenditure_category_name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCost_center_name() {
        return cost_center_name;
    }

    public void setCost_center_name(String cost_center_name) {
        this.cost_center_name = cost_center_name;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIs_agt() {
        return is_agt;
    }

    public void setIs_agt(String is_agt) {
        this.is_agt = is_agt;
    }

    public String getIs_bigmoney() {
        return is_bigmoney;
    }

    public void setIs_bigmoney(String is_bigmoney) {
        this.is_bigmoney = is_bigmoney;
    }

    public String getIs_material() {
        return is_material;
    }

    public void setIs_material(String is_material) {
        this.is_material = is_material;
    }

    public String getIs_present() {
        return is_present;
    }

    public void setIs_present(String is_present) {
        this.is_present = is_present;
    }

    public String getIs_advanced_ord() {
        return is_advanced_ord;
    }

    public void setIs_advanced_ord(String is_advanced_ord) {
        this.is_advanced_ord = is_advanced_ord;
    }


    public String getPck_minqty() {
        return pck_minqty;
    }

    public void setPck_minqty(String pck_minqty) {
        this.pck_minqty = pck_minqty;
    }

    public Integer getLimit_matapply_qty() {
        return limit_matapply_qty;
    }

    public void setLimit_matapply_qty(Integer limit_matapply_qty) {
        this.limit_matapply_qty = limit_matapply_qty;
    }

    public Integer getSize_id() {
        return size_id;
    }

    public void setSize_id(Integer size_id) {
        this.size_id = size_id;
    }

    public String getSize_desc() {
        return size_desc;
    }

    public void setSize_desc(String size_desc) {
        this.size_desc = size_desc;
    }

    public Integer getSize_martxcol() {
        return size_martxcol;
    }

    public void setSize_martxcol(Integer size_martxcol) {
        this.size_martxcol = size_martxcol;
    }

    public Date getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(Date expired_date) {
        this.expired_date = expired_date;
    }
}
