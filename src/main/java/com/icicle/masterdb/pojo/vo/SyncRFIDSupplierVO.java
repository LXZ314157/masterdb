package com.icicle.masterdb.pojo.vo;

/**
 * @author lvxuezhan
 * @version 2019-10-09 17:58
 */
public class SyncRFIDSupplierVO {

    private String buyer_id;
    private String buyer_name;
    private String buyer_desc;
    private String buyer_state;
    private String buyer_address;
    private String buyer_contact;
    private String buyer_phone;
    private Integer is_provider;
    private Integer is_customer;
    private Integer is_processor;

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getBuyer_desc() {
        return buyer_desc;
    }

    public void setBuyer_desc(String buyer_desc) {
        this.buyer_desc = buyer_desc;
    }

    public String getBuyer_state() {
        return buyer_state;
    }

    public void setBuyer_state(String buyer_state) {
        this.buyer_state = buyer_state;
    }

    public String getBuyer_address() {
        return buyer_address;
    }

    public void setBuyer_address(String buyer_address) {
        this.buyer_address = buyer_address;
    }

    public String getBuyer_contact() {
        return buyer_contact;
    }

    public void setBuyer_contact(String buyer_contact) {
        this.buyer_contact = buyer_contact;
    }

    public String getBuyer_phone() {
        return buyer_phone;
    }

    public void setBuyer_phone(String buyer_phone) {
        this.buyer_phone = buyer_phone;
    }

    public Integer getIs_provider() {
        return is_provider;
    }

    public void setIs_provider(Integer is_provider) {
        this.is_provider = is_provider;
    }

    public Integer getIs_customer() {
        return is_customer;
    }

    public void setIs_customer(Integer is_customer) {
        this.is_customer = is_customer;
    }

    public Integer getIs_processor() {
        return is_processor;
    }

    public void setIs_processor(Integer is_processor) {
        this.is_processor = is_processor;
    }
}
