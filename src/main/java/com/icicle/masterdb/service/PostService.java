package com.icicle.masterdb.service;

import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Service;
import com.icicle.masterdb.model.Post;
import com.icicle.masterdb.pojo.vo.PostVO;
import com.icicle.masterdb.pojo.vo.SyncPostVO;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-18 11:56:09.
*/
public interface PostService extends Service<Post> {

    /**
     *
     * @param sEcho
     * @param pageIndex
     * @param pageSize
     * @param postIdName
     * @param lanCode
     * @return
     */
    DataTableRecord getPostList(String sEcho, Integer pageIndex, Integer pageSize, String postIdName, String lanCode);


    /**
     * 岗位批量同步到OA系统
     * @param postIdList
     * @return
     */
    List<SyncPostVO> getSyncPostInfo(String [] postIdList, String lanCode);


    /**
     * 批量更新岗位同步标识
     * @param lastUpdateBy
     * @param postIdList
     * @return
     */
    int updateSynPost(String lastUpdateBy, String [] postIdList,String lanCode);

    /**
     * 获取岗位列表
     * @return
     */
    List<PostVO> getPostList();


    /**
     * 获取岗位列表的workbook
     * @param list
     * @return */
    Workbook exportPostExcel(List<PostVO> list);

}