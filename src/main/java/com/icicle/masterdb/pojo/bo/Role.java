package com.icicle.masterdb.pojo.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.icicle.masterdb.util.StringUtil;

/**
 * @author liumingming
 * @version 2017-12-19 10:32.
 */
public class Role {
    @JSONField(name = "RoleId")
    private Integer roleId;

    @JSONField(name = "RoleCode")
    private String roleCode;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
