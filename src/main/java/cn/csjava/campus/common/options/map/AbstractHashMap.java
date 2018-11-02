package cn.csjava.campus.common.options.map;

import java.util.HashMap;

/**
 * @author：hcqi .
 * des: map 抽象 基类
 * email:hechuanqi.top@gmail.com
 * date: 2018/6/21
 */
public abstract class AbstractHashMap<K, V> extends HashMap<K, V> {


    public AbstractHashMap<K, V> append(K k, V v) {
        put(k, v);
        return this;
    }

    public Integer getInt(K key) {
        return getInt(key, -1);
    }

    public Integer getInt(K key, Integer defaultV) {
        if (!containsKey(key)) {
            return null;
        }
        return (Integer) get(key);
    }

    public String getString(K key) {
        return getString(key, "");
    }

    public String getString(K key, String defaultV) {
        if (!containsKey(key)) {
            return null;
        }
        return get(key).toString();
    }

    public Double getDouble(K key) {
        return getDouble(key, -1d);
    }

    public Double getDouble(K key, Double defaultV) {
        if (!containsKey(key)) {
            return null;
        }
        return (Double) get(key);
    }

    public Long getLong(K key) {
        return getLong(key, -1);
    }

    public Long getLong(K key, long defaultV) {
        if (!containsKey(key)) {
            return null;
        }
        return (Long) get(key);
    }
}
