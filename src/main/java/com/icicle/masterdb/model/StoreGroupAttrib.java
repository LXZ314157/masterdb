package com.icicle.masterdb.model;

import com.icicle.masterdb.util.StringUtil;

import javax.persistence.*;
import java.util.Objects;
/**
 * @author liurenhua
 */
@Table(name = "store_group_attrib")
public class StoreGroupAttrib {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "store_attrib_group_id")
    private Integer storeAttribGroupId;

    @Column(name = "store_attrib_def_id")
    private Integer storeAttribDefId;

    private Integer status;

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
     * @return store_attrib_group_id
     */
    public Integer getStoreAttribGroupId() {
        return storeAttribGroupId;
    }

    /**
     * @param storeAttribGroupId
     */
    public void setStoreAttribGroupId(Integer storeAttribGroupId) {
        this.storeAttribGroupId = storeAttribGroupId;
    }

    /**
     * @return store_attrib_def_id
     */
    public Integer getStoreAttribDefId() {
        return storeAttribDefId;
    }

    /**
     * @param storeAttribDefId
     */
    public void setStoreAttribDefId(Integer storeAttribDefId) {
        this.storeAttribDefId = storeAttribDefId;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {

        if (Objects.equals(obj.getClass().getName(), this.getClass().getName())) {
            StoreGroupAttrib s = (StoreGroupAttrib) obj;
            boolean same = Objects.equals(s.getStoreAttribDefId(), this.getStoreAttribDefId())
                    && Objects.equals(s.getStoreAttribGroupId(), this.getStoreAttribGroupId());
            return same;
        } else {
            return super.equals(obj);
        }

    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}