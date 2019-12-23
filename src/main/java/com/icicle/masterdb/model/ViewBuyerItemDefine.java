package com.icicle.masterdb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "view_buyer_item_define")
public class ViewBuyerItemDefine {
    @Id
    @Column(name = "buyer_attrib_item_id")
    private Integer buyerAttribItemId;

    @Column(name = "buyer_attrib_def_id")
    private Integer buyerAttribDefId;

    @Column(name = "buyer_attrib_def_name")
    private String buyerAttribDefName;

    /**
     * @return buyer_attrib_item_id
     */
    public Integer getBuyerAttribItemId() {
        return buyerAttribItemId;
    }

    /**
     * @param buyerAttribItemId
     */
    public void setBuyerAttribItemId(Integer buyerAttribItemId) {
        this.buyerAttribItemId = buyerAttribItemId;
    }

    /**
     * @return buyer_attrib_def_id
     */
    public Integer getBuyerAttribDefId() {
        return buyerAttribDefId;
    }

    /**
     * @param buyerAttribDefId
     */
    public void setBuyerAttribDefId(Integer buyerAttribDefId) {
        this.buyerAttribDefId = buyerAttribDefId;
    }

    /**
     * @return buyer_attrib_def_name
     */
    public String getBuyerAttribDefName() {
        return buyerAttribDefName;
    }

    /**
     * @param buyerAttribDefName
     */
    public void setBuyerAttribDefName(String buyerAttribDefName) {
        this.buyerAttribDefName = buyerAttribDefName;
    }
}