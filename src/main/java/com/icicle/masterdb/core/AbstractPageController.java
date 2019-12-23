package com.icicle.masterdb.core;

import com.icicle.masterdb.config.FtpConfig;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.pojo.bo.LocalUserEntity;
import com.icicle.masterdb.pojo.bo.UserPermission;
import com.icicle.masterdb.util.DateUtil;
import com.icicle.masterdb.util.ListUtil;
import com.icicle.masterdb.util.StringUtil;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 模板基类
 *
 * @author liumingming
 * @version 2017-10-19 15:27.
 */
public abstract class AbstractPageController {

    private final String defaultPage = "index";
    private String ctrlName = "";
    private static String templateLoaderPath = null;
    @Resource
    private FtpConfig ftpConfig;
    @Resource
    private FreeMarkerConfigurer fmc;

    /**
     * 控制器名称，不包含“Controller”后缀
     *
     * @param controllerName
     */
    public AbstractPageController(String controllerName) {
        this.ctrlName = controllerName.toLowerCase();
    }

    /**
     * 初始化 freemarker 模板地址
     *
     * @return
     */
    private boolean initTemplateLoaderPath() {
        if (templateLoaderPath != null) {
            return true;
        }

        //布局模板是否存在
        if (fmc != null) {
            MultiTemplateLoader utll = (MultiTemplateLoader) fmc.getConfiguration().getTemplateLoader();
            int n = utll.getTemplateLoaderCount();
            TemplateLoader tl = null;
            for (int i = 0; i < n; i++) {
                tl = utll.getTemplateLoader(i);
                //获取模板加载目录地址
                if (tl instanceof freemarker.cache.FileTemplateLoader) {
                    templateLoaderPath = ((freemarker.cache.FileTemplateLoader) tl)
                            .getBaseDirectory()
                            .getAbsolutePath();
                }
            }
        }
        return templateLoaderPath != null;
    }

    /**
     * 判断模板是否存在
     *
     * @param filePath
     * @return
     */
    private boolean isExistsTemplate(String filePath) {
        initTemplateLoaderPath();
        File file = new File(StringUtil.gsFormat("{0}/{1}", templateLoaderPath, filePath));
        boolean exist = file.exists();
        return exist;
    }

    /**
     * freemarker 布局视图解析
     *
     * @param prefix
     * @param pars
     * @return
     */
    public String freeMarkerViewResult(String prefix, Map<String, Object> pars, HttpServletRequest request) {
        //布局模板
        String layout = "layout.ftl";
        //静态内容
        String js = StringUtil.gsFormat("{0}/{1}js.ftl", ctrlName, prefix);
        if (!isExistsTemplate(js)) {
            js = "";
        }
        //页模板
        String body = StringUtil.gsFormat("{0}/{1}.ftl", ctrlName, prefix);

        //页面参数
        String title = "title";
        if (!pars.containsKey(title)) {
            pars.put(title, "主数据系统");
        }
        pars.put("language", SessionManager.getLanguage());
        pars.put("left_menu", buildMenu(request));
        pars.put("image_url", ftpConfig.getImageurl());
        LocalUserEntity user = SessionManager.getLocalUserEntity();
        if (null != user && user.getUserId().intValue() > 0) {
            pars.put("lonin_name", user.getTrueName());
        } else {
            pars.put("lonin_name", "");
        }
        pars.put("current_year", DateUtil.currentYear());
        pars.put("js_file_path", js);
        pars.put("body_file_path", body);

        //优先使用布局页
        if (isExistsTemplate(layout)) {
            //模板不需要后缀
            return layout.replaceAll("\\.ftl$", "");
        }
        //模板不需要后缀
        return body.replaceAll("\\.ftl$", "");
    }

    /**
     * 生成左侧菜单
     *
     * @param request
     * @return
     */
    private String buildMenu(HttpServletRequest request) {
        LocalUserEntity user = SessionManager.getLocalUserEntity();
        if (null == user) {
            return "";
        }
        List<UserPermission> list = user.getPermissionList();
        if (ListUtil.isBlank(list)) {
            return "";
        }
        Map<Integer, List<UserPermission>> map =
                list.stream().collect(Collectors.groupingBy(x -> x.moduleId));
        int[] modules = new int[map.size()];
        int i = 0;
        for (Integer key : map.keySet()) {
            modules[i] = key.intValue();
            i++;
        }
        Arrays.sort(modules);
        StringBuilder sb = new StringBuilder(8192);
        String controller = getController(request);
        String action = getAction(request);
        for (int j = 0; j < modules.length; j++) {
            List<UserPermission> item = map.get(Integer.valueOf(modules[j]));
            if (item.get(0).getModuleRank().intValue() > 1000) {
                continue;
            }
            sb.append("<li class=\"").append(getClass(true, controller,
                    item.get(0).getModulePath(), "", "")).append("\">");
            if (defaultPage.equals(item.get(0).getModulePath())) {
                sb.append("<a href=\"").append(request.getContextPath()).append("\" class=\"nav-link\">");
            } else {
                sb.append("<a href=\"javascript:;\" class=\"nav-link nav-toggle\">");
            }
            sb.append("<i class=\"").append(item.get(0).getModuleIcon()).append("\"></i>");
            sb.append("<span class=\"title\">").append(item.get(0).getModuleName()).append("</span>");
            if (getSelectHtml(controller, item.get(0).getModulePath())) {
                sb.append("<span class=\"selected\"></span>");
            }
            sb.append("</a>");
            sb.append("<ul class=\"sub-menu\">");
            List<UserPermission> curr = item.stream().filter(p -> p.getFunIsAction() && p.getFunIcon().length() > 0)
                    .collect(Collectors.toList());
            if (!ListUtil.isBlank(curr)) {
                for (UserPermission per : curr) {
                    sb.append("<li class=\"nav-item\">");
                    sb.append("<li class=\"").append(getClass(false, controller, per.getModulePath(),
                            action, per.getFunIdentify())).append("\">");
                    sb.append("<a href=\"").append(request.getContextPath()).append("/")
                            .append(per.getModulePath()).append("/").append(per.getFunIdentify())
                            .append("\" class=\"nav-link\">");
                    sb.append("<i class=\"").append(per.getFunIcon()).append("\"></i>").append(per.getFunName());
                    sb.append("</a>").append("</li>");
                }
            }
            sb.append("</ul>").append("</li>");
        }
        return sb.toString();
    }

    private String getClass(boolean checkModule, String controller, String moduleName, String action, String funcName) {
        String cls;
        if (checkModule) {
            cls = Objects.equals(moduleName, controller) ? "nav-item active open" : "nav-item";
        } else {
            cls = (Objects.equals(moduleName, controller) && Objects.equals(funcName, action))
                    ? "nav-item active open" : "nav-item";
        }
        return cls;
    }

    private boolean getSelectHtml(String controller, String moduleName) {
        return Objects.equals(controller.toLowerCase(), moduleName.toLowerCase());
    }

    private String getController(HttpServletRequest request) {
        String[] arr = getArray(request);
        if (null != arr && arr.length > 0) {
            return arr[0];
        }
        return defaultPage;
    }

    private String getAction(HttpServletRequest request) {
        String[] arr = getArray(request);
        if (null != arr && arr.length > 1) {
            return arr[1];
        }
        return defaultPage;
    }

    private String[] getArray(HttpServletRequest request) {
        String url = request.getRequestURI();
        String replace = StringUtil.gsFormat("{0}/", request.getContextPath());
        url = url.replaceFirst(replace, "");
        if (StringUtils.isBlank(url)) {
            return null;
        }
        String[] arr = url.split("/");
        return arr;
    }
}