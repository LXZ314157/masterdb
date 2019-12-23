package com.icicle.masterdb.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author CodeGeneratorUtil
 */
@Table(name = "product_attribute")
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "attr_code")
    private String attrCode;

    @Column(name = "attr_value")
    private String attrValue;

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
     * @return product_code
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return attr_code
     */
    public String getAttrCode() {
        return attrCode;
    }

    /**
     * @param attrCode
     */
    public void setAttrCode(String attrCode) {
        this.attrCode = attrCode;
    }

    /**
     * @return attr_value
     */
    public String getAttrValue() {
        return attrValue;
    }

    /**
     * @param attrValue
     */
    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ProductAttribute) {
            ProductAttribute p = (ProductAttribute) obj;
            boolean equal = Objects.equals(p.getProductCode(), this.getProductCode())
                    && Objects.equals(p.getAttrCode(), this.getAttrCode());
            return equal;
        }
        return super.equals(obj);
    }
}