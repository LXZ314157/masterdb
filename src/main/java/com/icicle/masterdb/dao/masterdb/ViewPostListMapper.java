package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.ViewPostList;
import com.icicle.masterdb.pojo.vo.PostVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewPostListMapper extends MyMapper<ViewPostList> {

    PostVO findPostInfoByPostId(@Param("postId") String postId, @Param("lanCode") String lanCode);

    /**
     * 根据岗位编号和名称以及语言查出岗位列表
     * @param postIdOrName
     * @param lanCode
     * @return
     */
    List<PostVO> findByQueryCondition(@Param("postIdOrName") String postIdOrName,@Param("lanCode") String lanCode);

}