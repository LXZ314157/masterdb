package com.icicle.masterdb.dao.masterdb;

import com.icicle.masterdb.core.MyMapper;
import com.icicle.masterdb.model.Post;
import com.icicle.masterdb.pojo.vo.PostVO;
import com.icicle.masterdb.pojo.vo.SyncPostVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper extends MyMapper<Post> {


    /**
     * 岗位批量同步到OA系统
     * @param postIdList
     * @param lanCode
     * @return
     */
    List<SyncPostVO> getSyncPostInfo(@Param("postIdList") String [] postIdList, @Param("lanCode") String lanCode);


    /**
     * 批量更新岗位同步标识
     * @param lastUpdateBy
     * @param postIdList
     * @return
     */
    int updateSynPost(@Param("lastUpdateBy") String lastUpdateBy,@Param("postIdList") String [] postIdList,@Param("lanCode") String lanCode);


    /**
     * 获取岗位列表
     * @return
     */
    List<PostVO> getPostList();

}