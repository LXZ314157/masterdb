package com.icicle.masterdb.service;

import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.ViewPostList;
import com.icicle.masterdb.pojo.vo.PostVO;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-23 15:57:27.
*/
public interface ViewPostListService extends Service<ViewPostList> {

    PostVO findPostInfoByPostId(String postId,String lanCode);


    /**
     * 根据岗位编号和名称以及语言查出岗位列表
     * @param postIdOrName
     * @param lanCode
     * @return
     */
    List<PostVO> findByQueryCondition(String postIdOrName,String lanCode);

}