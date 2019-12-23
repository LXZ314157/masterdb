package com.icicle.masterdb.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liumingming
 * @version 2017-10-27 10:08.
 * 时间工具类
 */
public class DateUtil {


    /**
     * 获取当前年份
     *
     * @return
     */
    public static String currentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 根据字符串返回date  若格式不符合要求  则返回null
     *
     * @param str
     * @return
     */
    public static Date getDateByString(String str,String pattern) {

        if (str == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    /**
     * 根据字符串返回date  若格式不符合要求  则返回null
     *
     * @param strs
     * @return
     */
    public static Date getDateByStrings(String strs,String pattern1,String pattern2) {

        if (strs == null) {
            return null;
        }

        SimpleDateFormat sdf1 = new SimpleDateFormat(pattern1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(pattern2);
        Date dates = null;
        try {
            dates = sdf1.parse(strs);
        } catch (ParseException e) {
            try{
                dates = sdf2.parse(strs);
            }catch (Exception e1){
                dates = null;
            }
        }
        return dates;
    }

    /**
     * 验证是否时间格式
     * @param dataValue
     * @return
     */
    public  static Boolean isDate(String dataValue){
        String rexp = "((\\d{2}(([02468][048])|([13579][26]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(dataValue);
        boolean dateType = mat.matches();
        return dateType;
    }

    /**
     * 验证日期格式的有效性
     * @param str
     * @return
     */
    public static boolean isValidDate(String str) {
        boolean convertSuccess=true;
        if(!StringUtils.isEmpty(str)){
            // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
            try {
                // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
                format.setLenient(false);
                format.parse(str);
            } catch (ParseException e) {
                try{
                    format1.setLenient(false);
                    format1.parse(str);
                }catch (Exception e1){
                    convertSuccess=false;
                }
            }
        }
        return convertSuccess;
    }


    /**
     * 返回一个Date的日期格式，并且按照指定的分割符号
     *
     * @param s
     * @param date
     * @return
     */
    public static String getDateStringWithSeperate(String s, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(String.format("yyyy%sMM%sdd", s, s));
        return sdf.format(date);
    }

    /**
     * 获取当前时间(格式化后的字符串)
     *
     * @return
     */
    public static String stringNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 将字符按一定样式转换为字符串输出
     * @return
     */
    public static String stringPatten(Date a) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(a);
    }

    /**
     * date转换为字符串类型
     * @param date
     * @return
     */
    public static String date2String(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String date2YYMMDD(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String date2YYMMDDs(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    public static Long convert2Timestamp(String time){
        try{
            return Date.parse(time.replaceAll("-","/"));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
