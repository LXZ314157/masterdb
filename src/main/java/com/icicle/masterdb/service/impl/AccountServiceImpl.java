package com.icicle.masterdb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icicle.masterdb.aop.annotation.LogAction;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.dao.imagesearch.*;
import com.icicle.masterdb.model.Account;
import com.icicle.masterdb.model.UserGroup;
import com.icicle.masterdb.model.ViewAccountData;
import com.icicle.masterdb.pojo.vo.AccountSelectVO;
import com.icicle.masterdb.pojo.vo.AccountVO;
import com.icicle.masterdb.service.AccountService;
import com.icicle.masterdb.util.LogUtil;
import com.icicle.masterdb.util.PageUtil;
import com.icicle.masterdb.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liumingming
 * @version 2018-01-30 13:40.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private DomainMapper domainMapper;

    @Resource
    private PromissionGroupMapper promissionGroupMapper;

    @Resource
    private ViewAccountDataMapper viewAccountDataMapper;

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private UserGroupMapper userGroupMapper;

    @Override
    public AccountSelectVO getSelectData() {
        AccountSelectVO vo = new AccountSelectVO();
        try {
            vo.setDomainList(domainMapper.selectAll());
            vo.setPromissionGroupList(promissionGroupMapper.selectAll());
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
        }
        return vo;
    }

    @Override
    public DataTableRecord getList(String sEcho, Integer pageIndex, Integer pageSize, String search) {
        DataTableRecord dataTableRecord = new DataTableRecord(sEcho, 0, 0, null);
        List<ViewAccountData> list;
        Integer pageNum = PageUtil.getPageNum(pageIndex, pageSize);
        Integer total;
        if (!StringUtils.isBlank(search)) {
            try {
                String con = StringUtil.gsFormat("%{0}%", search);
                Condition condition = new Condition(ViewAccountData.class);
                condition.createCriteria().andLike("loginname", con).orLike("truename", con);
                condition.setOrderByClause("id");
                PageHelper.startPage(pageNum, pageSize);
                list = viewAccountDataMapper.selectByCondition(condition);
            } catch (Exception ex) {
                list = new ArrayList<>();
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            }
            PageInfo pageInfo = new PageInfo(list);
            total = (int) pageInfo.getTotal();
            dataTableRecord.setAaData(pageInfo.getList());
        } else {
            try {
                PageHelper.startPage(pageNum, pageSize);
                list = viewAccountDataMapper.findAll();
            } catch (Exception ex) {
                list = new ArrayList<>();
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
            }
            PageInfo pageInfo = new PageInfo(list);
            total = (int) pageInfo.getTotal();
            dataTableRecord.setAaData(pageInfo.getList());
        }
        dataTableRecord.setITotalRecords(total);
        dataTableRecord.setITotalDisplayRecords(total);
        return dataTableRecord;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "新增图片检索系统帐号")
    public int save(AccountVO vo) {
        Account account = new Account();
        account.setLoginname(vo.getLoginName());
        account.setTruename(vo.getTrueName());
        account.setDomainId(vo.getDomainId());
        accountMapper.insertAccount(account);

        UserGroup userGroup = new UserGroup();
        userGroup.setUserId(account.getId());
        userGroup.setGroupId(vo.getGroupId());
        userGroupMapper.insertUserGroup(userGroup);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAction(logDesc = "编辑图片检索系统帐号")
    public int update(AccountVO vo) {
        Account account = new Account();
        account.setId(vo.getId());
        account.setLoginname(vo.getLoginName());
        account.setTruename(vo.getTrueName());
        account.setDomainId(vo.getDomainId());
        account.setManager(false);
        accountMapper.updateByPrimaryKeySelective(account);

        Condition condition = new Condition(UserGroup.class);
        condition.createCriteria().andEqualTo("userId", vo.getId());
        userGroupMapper.deleteByCondition(condition);

        UserGroup userGroup = new UserGroup();
        userGroup.setUserId(account.getId());
        userGroup.setGroupId(vo.getGroupId());
        userGroupMapper.insertUserGroup(userGroup);
        return 1;
    }
}
