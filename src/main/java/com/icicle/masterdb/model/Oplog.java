package com.icicle.masterdb.model;

import com.icicle.masterdb.util.StringUtil;

import javax.persistence.Id;
import java.util.Date;

/**
 * @author CodeGeneratorUtil
 */
public class Oplog {
    @Id
    private Integer uid;

    private String optitle;

    private String opmethod;

    private Integer opresult;

    private String desc;

    private String opr;

    private String opip;

    private Date opdate;

    private Integer sysid;

    /**
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return optitle
     */
    public String getOptitle() {
        return optitle;
    }

    /**
     * @param optitle
     */
    public void setOptitle(String optitle) {
        this.optitle = optitle;
    }

    /**
     * @return opmethod
     */
    public String getOpmethod() {
        return opmethod;
    }

    /**
     * @param opmethod
     */
    public void setOpmethod(String opmethod) {
        this.opmethod = opmethod;
    }

    /**
     * @return opresult
     */
    public Integer getOpresult() {
        return opresult;
    }

    /**
     * @param opresult
     */
    public void setOpresult(Integer opresult) {
        this.opresult = opresult;
    }

    /**
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return opr
     */
    public String getOpr() {
        return opr;
    }

    /**
     * @param opr
     */
    public void setOpr(String opr) {
        this.opr = opr;
    }

    /**
     * @return opip
     */
    public String getOpip() {
        return opip;
    }

    /**
     * @param opip
     */
    public void setOpip(String opip) {
        this.opip = opip;
    }

    /**
     * @return opdate
     */
    public Date getOpdate() {
        return opdate;
    }

    /**
     * @param opdate
     */
    public void setOpdate(Date opdate) {
        this.opdate = opdate;
    }

    /**
     * @return sysid
     */
    public Integer getSysid() {
        return sysid;
    }

    /**
     * @param sysid
     */
    public void setSysid(Integer sysid) {
        this.sysid = sysid;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}