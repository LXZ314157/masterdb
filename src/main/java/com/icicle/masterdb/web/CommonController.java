package com.icicle.masterdb.web;

import com.icicle.masterdb.authorization.annotation.Authorization;
import com.icicle.masterdb.core.AbstractPageController;
import com.icicle.masterdb.util.LanguageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wangyuling
 * @version 2018/1/3 16:30
 */
@Controller
@RequestMapping("/common")
@Authorization
public class CommonController extends AbstractPageController {

    public CommonController() {
        super("common");
    }

    @GetMapping("/measure")
    public String measure(Map<String, Object> map, HttpServletRequest request) {
        map.put("home", LanguageUtil.getText(1560));
        map.put("module", LanguageUtil.getText(1000));
        map.put("cuurencyList", LanguageUtil.getText(1360));
        map.put("currencyCode", LanguageUtil.getText(1061));
        map.put("curencyName", LanguageUtil.getText(1363));
        map.put("currencyDesc", LanguageUtil.getText(1366));
        map.put("currencyPrecision", LanguageUtil.getText(1367));
        map.put("status", LanguageUtil.getText(1025));
        map.put("exec", LanguageUtil.getText(1014));
        map.put("addUom", LanguageUtil.getText(1382));
        map.put("uomCode", LanguageUtil.getText(1374));
        map.put("uomMeasure", LanguageUtil.getText(1359));
        map.put("uomClass", LanguageUtil.getText(1378));
        map.put("uomDesc", LanguageUtil.getText(1381));
        map.put("valid", LanguageUtil.getText(1165));
        map.put("unvalid", LanguageUtil.getText(1166));
        map.put("save", LanguageUtil.getText(1113));
        map.put("cancel", LanguageUtil.getText(1150));
        return this.freeMarkerViewResult("measure", map, request);
    }

    @GetMapping("/conversionrate")
    public String conversionRate(Map<String, Object> map, HttpServletRequest request) {
        map.put("home", LanguageUtil.getText(1560));
        map.put("exec", LanguageUtil.getText(1014));
        map.put("fromExchangeRate", LanguageUtil.getText(1392));
        map.put("toExchangeRate", LanguageUtil.getText(1394));
        map.put("exchangeRateDate", LanguageUtil.getText(1396));
        map.put("conversionEndDate", LanguageUtil.getText(1566));
        map.put("exchangeRate", LanguageUtil.getText(1399));
        map.put("chose", LanguageUtil.getText(1254));
        map.put("save", LanguageUtil.getText(1113));
        map.put("cancel", LanguageUtil.getText(1150));
        return this.freeMarkerViewResult("conversionrate", map, request);
    }

}
