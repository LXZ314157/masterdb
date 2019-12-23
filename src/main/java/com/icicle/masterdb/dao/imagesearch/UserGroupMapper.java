package com.icicle.masterdb.dao.imagesearch;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.UserGroup;

public interface UserGroupMapper extends MyMapper<UserGroup> {

    /**
     * 写入用户权限数据
     * @param userGroup
     * @return
     */
    int insertUserGroup(UserGroup userGroup);
}