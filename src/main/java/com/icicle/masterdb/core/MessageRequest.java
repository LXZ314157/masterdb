package com.icicle.masterdb.core;

import com.alibaba.fastjson.annotation.JSONField;
import com.icicle.masterdb.util.StringUtil;

/**
 * @author lvxuezhan.
 * @date 2019/3/11.
 */
public class MessageRequest {
    private String id;
    private String body;
    @JSONField(name = "isEncrypt")
    private Boolean isEncrypt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getIsEncrypt() {
        return isEncrypt;
    }

    public void setIsEncrypt(Boolean encrypt) {
        isEncrypt = encrypt;
    }

    @Override
    public  String toString()
    {
        return StringUtil.toSimpleString(this);
    }
}
