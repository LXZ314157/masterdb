package com.icicle.masterdb.service.impl;

import com.icicle.masterdb.dao.masterdb.ViewPostListMapper;
import com.icicle.masterdb.model.ViewPostList;
import com.icicle.masterdb.pojo.vo.PostVO;
import com.icicle.masterdb.service.ViewPostListService;
import com.icicle.masterdb.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-23 15:57:27.
*/
@Service
public class ViewPostListServiceImpl extends AbstractService<ViewPostList> implements ViewPostListService {
    @Resource
    private ViewPostListMapper viewPostListMapper;

    @Override
    public PostVO findPostInfoByPostId(String postId,String lanCode) {
        return viewPostListMapper.findPostInfoByPostId(postId,lanCode);
    }

    @Override
    public List<PostVO> findByQueryCondition(String postIdOrName, String lanCode) {
        return viewPostListMapper.findByQueryCondition(postIdOrName,lanCode);
    }
}