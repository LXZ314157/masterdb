package com.icicle.masterdb.util;

import com.alibaba.fastjson.JSON;
import com.icicle.masterdb.manager.SessionManager;
import com.icicle.masterdb.pojo.bo.LanguageText;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author liumingming
 * @version 2017-12-22 13:42.
 */
public class LanguageUtil {
    @Value(value = "classpath:language/lang.json")
    private Resource language;

    private static final String ZHS = "zhs";

    private static List<LanguageText> list;
    private static LanguageUtil languageUtil;

    @PostConstruct
    public void init() {
        languageUtil = this;
        try {
            list = getData(language.getFile());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getText(int no) {
        if (null == list) {
            return String.valueOf(no);
        }
        String lang = SessionManager.getLanguage();
        String[] ret = {String.valueOf(no)};
        list.stream().filter(l -> Objects.equals(l.getNo().intValue(), no)).findFirst()
                .ifPresent(r -> {
                    if (ZHS.toLowerCase().equals(lang.toLowerCase())) {
                        ret[0] = r.getZhsText();
                    } else {
                        ret[0] = r.getEnText();
                    }
                });
        return ret[0];
    }

    private List<LanguageText> getData(File file) {
        try {
            String jsonData = this.jsonRead(file);
            return JSON.parseArray(jsonData, LanguageText.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 读取文件类容为字符串
     *
     * @param file
     * @return
     */
    private String jsonRead(File file) {
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return buffer.toString();
    }
}
