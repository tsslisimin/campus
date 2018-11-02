package cn.csjava.campus.common.util;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/22
 */
public class StringUtils extends org.springframework.util.StringUtils {
    public static String emptyToStr(String str, String nulls) {
        return isEmpty(str) ? nulls : str;
    }

    public static String emptyToStr(String str) {
        return emptyToStr(str, "无");
    }
}
