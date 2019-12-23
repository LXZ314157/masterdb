package com.icicle.masterdb.util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.icicle.masterdb.service.constant.ServiceConstant.ASC;
import static com.icicle.masterdb.service.constant.ServiceConstant.DESC;

/**
 * @author liumingming
 * Created by liumingming on 2017/10/24.
 */
public class PageUtil {

    public static Integer getPageNum(Integer pageIndex, Integer pageSize) {
        return ((pageIndex + 1) % pageSize == 0) ? (pageIndex + 1) / pageSize : (pageIndex + 1) / pageSize + 1;
    }

    /**
     * 这个方法只会返回asc和esc两种字符串，用于确定页面需要的排序方式
     *
     * @param sortDir 客户端传过来的排序相关的字符串
     * @return
     */
    public static String orderMethod(String sortDir) {
        //如果客户端没传这个数据，或者传的值不符合要求时按升序排列
        if (StringUtils.isBlank(sortDir)) {
            return ASC;
        } else if (!DESC.equals(sortDir.toLowerCase()) &&
                !ASC.equals(sortDir.toLowerCase())) {
            return ASC;
        } else {
            return sortDir.toLowerCase();
        }
    }

    /**
     * @param sortCol 客户端传过来的排序字段
     * @param clazz   datatables 对应的VO
     * @return
     */
    public static String orderBy(int sortCol, Class clazz) {
        List<String> list = PojoConvertUtil.getProperties(clazz);
        if (ListUtil.isBlank(list)) {
            return null;
        } else if (sortCol < 0 || sortCol >= list.size()) {
            return null;
        } else {
            return list.get(sortCol);
        }
    }

}
