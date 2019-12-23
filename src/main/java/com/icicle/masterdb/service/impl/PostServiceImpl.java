package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.masterdb.PostMapper;
import com.icicle.masterdb.model.*;
import com.icicle.masterdb.pojo.vo.PostVO;
import com.icicle.masterdb.pojo.vo.SyncPostVO;
import com.icicle.masterdb.service.PostService;
import com.icicle.masterdb.core.AbstractService;
import com.icicle.masterdb.service.ViewPostListService;
import com.icicle.masterdb.util.ExcelUtil;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PageUtil;
import com.icicle.masterdb.util.StringUtil;
import com.icicle.masterdb.web.BuyerController;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
* @author  CodeGeneratorUtil
* @version 2019-01-18 11:56:09.
*/
@Service
public class PostServiceImpl extends AbstractService<Post> implements PostService {
    @Resource
    private PostMapper postMapper;

    @Resource
    private ViewPostListService viewPostListService;

    @Override
    public DataTableRecord getPostList(String sEcho, Integer pageIndex, Integer pageSize, String postIdName, String lanCode) {

        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        Condition condition = new Condition(Post.class);
        Example.Criteria criteria = condition.createCriteria();
        Example.Criteria criteria2 = condition.createCriteria();

        if (!StringUtils.isBlank(lanCode)) {
            criteria.andEqualTo("lanCode", lanCode);
        }
        if (!StringUtils.isBlank(postIdName)) {
            String words = StringUtil.gsFormat("%{0}%", postIdName);
            condition.and(criteria2.andLike("postId", words)
                    .orLike("postName", words));
        }
        try {
            List<ViewPostList> list = viewPostListService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
            Integer total = (int) pageInfo.getTotal();
            dataTableRecord.setITotalRecords(total);
            dataTableRecord.setITotalDisplayRecords(total);
            dataTableRecord.setAaData(pageInfo.getList());
            return dataTableRecord;
        } catch (Exception ex) {
            LogUtil.getLogger(BuyerController.class).error(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<SyncPostVO> getSyncPostInfo(String[] postIdList, String lanCode) {
        lanCode = StringUtils.isBlank(lanCode)?"zhs":lanCode;
        return postMapper.getSyncPostInfo(postIdList,lanCode);
    }

    @Override
    public int updateSynPost(String lastUpdateBy,String[] postIdList,String lanCode) {
        lanCode = StringUtils.isBlank(lanCode)?"zhs":lanCode;
        return postMapper.updateSynPost(lastUpdateBy,postIdList,lanCode);
    }

    @Override
    public List<PostVO> getPostList() {
        return postMapper.getPostList();
    }

    @Override
    public Workbook exportPostExcel(List<PostVO> list) {
        return ExcelUtil.getPostWorkbook(list);
    }
}