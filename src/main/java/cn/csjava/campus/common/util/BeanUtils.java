package cn.csjava.campus.common.util;

import org.apache.poi.ss.formula.functions.T;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/6
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    public static <T> T copy(Object o, Class<T> c) {
        T instantiate = null;
        try {
            instantiate = c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        copyProperties(o, c);
        return instantiate;
    }
}
