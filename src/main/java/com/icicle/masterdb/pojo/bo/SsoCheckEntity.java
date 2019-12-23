package com.icicle.masterdb.pojo.bo;

import com.icicle.masterdb.util.StringUtil;

/**
 * @author liumingming
 * @version 2017-12-18 15:38.
 */
public class SsoCheckEntity {
    private String ip;
    private String ticket;
    private String sid;

    public SsoCheckEntity() {
    }

    public SsoCheckEntity(String ip, String ticket, String sid) {
        this.ip = ip;
        this.ticket = ticket;
        this.sid = sid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
