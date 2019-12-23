package com.icicle.masterdb.pojo.vo;


import java.util.List;

public class OaParamVO {

    private String modetablename;

    private Integer modeid;

    private Integer userid;

    List<OaKeyValueVO> key_values;

    public OaParamVO setModetablename(String modetablename){
        this.modetablename = modetablename;
        return this;
    }

    public OaParamVO setModeid(Integer modeid){
        this.modeid = modeid;
        return this;
    }

    public OaParamVO setUserid(Integer userid){
        this.userid = userid;
        return this;
    }

    public String getModetablename() {
        return modetablename;
    }

    public Integer getModeid() {
        return modeid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setKey_values(List<OaKeyValueVO> key_values) {
        this.key_values = key_values;
    }

    public List<OaKeyValueVO> getKey_values() {
        return key_values;
    }
}
