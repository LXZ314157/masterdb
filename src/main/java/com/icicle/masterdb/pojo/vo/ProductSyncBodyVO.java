package com.icicle.masterdb.pojo.vo;

/**
 * @author lvxuezhan
 * @version 2019-10-11 15:44
 */
public class ProductSyncBodyVO {

    private String productCodes;
    private String syncSelect;
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(String productCodes) {
        this.productCodes = productCodes;
    }

    public String getSyncSelect() {
        return syncSelect;
    }

    public void setSyncSelect(String syncSelect) {
        this.syncSelect = syncSelect;
    }
}
