package com.icicle.masterdb.web;

import com.icicle.masterdb.core.AbstractPageController;
import com.icicle.masterdb.core.DataTableRecord;
import com.icicle.masterdb.core.Result;
import com.icicle.masterdb.core.ResultGenerator;
import com.icicle.masterdb.pojo.vo.AccountVO;
import com.icicle.masterdb.service.AccountService;
import com.icicle.masterdb.util.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author liumingming
 * @version 2018-01-30 11:32.
 */
@Controller
@RequestMapping(value = "/manager")

public class ManagerController extends AbstractPageController {
    @Resource
    private AccountService accountService;

    public ManagerController() {
        super("manager");
    }

    @GetMapping("/accountlist")
    public String accountList(Map<String, Object> map, HttpServletRequest request) {
        return this.freeMarkerViewResult("accountlist", map, request);
    }

    @GetMapping(value = "/getselectdata")
    @ResponseBody
    public Result getSelectData() {
        return ResultGenerator.genSuccessResult(accountService.getSelectData());
    }

    @PostMapping("/getaccountlist")
    @ResponseBody
    public DataTableRecord getAccountList(@RequestParam("sEcho") String sEcho,
                                          @RequestParam("iDisplayStart") Integer pageIndex,
                                          @RequestParam("iDisplayLength") Integer pageSize,
                                          @RequestParam("sSearch") String sSearch) {
        return accountService.getList(sEcho, pageIndex, pageSize, sSearch);
    }

    @PostMapping(value = "/addaccount")
    @ResponseBody
    public Result addAccount(HttpServletRequest request) {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        AccountVO vo = checkData(mRequest, false);
        int ret = accountService.save(vo);
        if (ret > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("新增帐号失败，请稍后重试");
        }
    }

    @PostMapping(value = "/updateaccount")
    @ResponseBody
    public Result updateAccount(HttpServletRequest request) {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        AccountVO vo = checkData(mRequest, true);
        int ret = accountService.update(vo);
        if (ret > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("编辑帐号失败，请稍后重试");
        }
    }

    private AccountVO checkData(MultipartHttpServletRequest mRequest, boolean checkId) {
        Integer accountId = null;
        if (checkId) {
            String id = mRequest.getParameter("accountId");
            if (StringUtils.isBlank(id)) {
                throw new SecurityException("用户id不能为空");
            }
            try {
                accountId = Integer.valueOf(id);
            } catch (Exception ex) {
                LogUtil.getLogger(this.getClass()).error(ex.getMessage(), ex);
                throw new SecurityException("用户id格式错误，请确认");
            }
        }
        String loginName = mRequest.getParameter("loginname");
        if (StringUtils.isBlank(loginName)) {
            throw new SecurityException("登录名不能为空");
        }

        String trueName = mRequest.getParameter("truename");
        if (StringUtils.isBlank(trueName)) {
            throw new SecurityException("显示名不能为空");
        }

        String domainId = mRequest.getParameter("domain");
        if (!StringUtils.isNumeric(domainId)) {
            throw new SecurityException("域数据错误");
        }
        String groupId = mRequest.getParameter("groupName");
        if (!StringUtils.isNumeric(groupId)) {
            throw new SecurityException("权限组数据错误");
        }

        AccountVO vo = new AccountVO();
        if (checkId) {
            vo.setId(accountId);
        }
        vo.setLoginName(loginName);
        vo.setTrueName(trueName);
        vo.setDomainId(Integer.valueOf(domainId));
        vo.setGroupId(Integer.valueOf(groupId));
        return vo;
    }
}