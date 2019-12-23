package com.icicle.masterdb.model;

import com.icicle.masterdb.util.StringUtil;

import javax.persistence.*;
import java.util.Objects;
/**
 * @author liurenhua
 */
@Table(name = "store_attribute")
public class StoreAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "store_no")
    private Integer storeNo;

    @Column(name = "attrib_def_id")
    private Integer attribDefId;

    @Column(name = "attrib_item_id")
    private Integer attribItemId;

    @Column(name = "store_attrib_value")
    private String storeAttribValue;

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
     * @return store_no
     */
    public Integer getStoreNo() {
        return storeNo;
    }

    /**
     * @param storeNo
     */
    public void setStoreNo(Integer storeNo) {
        this.storeNo = storeNo;
    }

    /**
     * @return attrib_def_id
     */
    public Integer getAttribDefId() {
        return attribDefId;
    }

    /**
     * @param attribDefId
     */
    public void setAttribDefId(Integer attribDefId) {
        this.attribDefId = attribDefId;
    }

    /**
     * @return attrib_item_id
     */
    public Integer getAttribItemId() {
        return attribItemId;
    }

    /**
     * @param attribItemId
     */
    public void setAttribItemId(Integer attribItemId) {
        this.attribItemId = attribItemId;
    }

    /**
     * @return store_attrib_value
     */
    public String getStoreAttribValue() {
        return storeAttribValue;
    }

    /**
     * @param storeAttribValue
     */
    public void setStoreAttribValue(String storeAttribValue) {
        this.storeAttribValue = storeAttribValue;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StoreAttribute) {
            StoreAttribute storeAttribute = (StoreAttribute) obj;
            boolean same = Objects.equals(storeAttribute.getAttribDefId(), this.getAttribDefId())
                    && Objects.equals(storeAttribute.getStoreNo(), this.getStoreNo());
            return same;
        }
        return super.equals(obj);
    }
}