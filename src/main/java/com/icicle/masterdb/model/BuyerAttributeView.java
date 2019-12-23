package com.icicle.masterdb.model;

import javax.persistence.*;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "buyer_attribute_view")
public class BuyerAttributeView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "buyer_attrib_def_id")
    private Integer buyerAttribDefId;

    @Column(name = "buyer_attrib_group_id")
    private Integer buyerAttribGroupId;

    @Column(name = "buyer_attrib_group_name")
    private String buyerAttribGroupName;

    @Column(name = "buyer_attrib_def_name")
    private String buyerAttribDefName;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return buyer_attrib_group_id
     */
    public Integer getBuyerAttribGroupId() {
        return buyerAttribGroupId;
    }

    /**
     * @param buyerAttribGroupId
     */
    public void setBuyerAttribGroupId(Integer buyerAttribGroupId) {
        this.buyerAttribGroupId = buyerAttribGroupId;
    }

    /**
     * @return buyer_attrib_group_name
     */
    public String getBuyerAttribGroupName() {
        return buyerAttribGroupName;
    }

    /**
     * @param buyerAttribGroupName
     */
    public void setBuyerAttribGroupName(String buyerAttribGroupName) {
        this.buyerAttribGroupName = buyerAttribGroupName;
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