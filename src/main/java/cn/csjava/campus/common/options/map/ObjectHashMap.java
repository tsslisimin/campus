package cn.csjava.campus.common.options.map;

import java.util.function.Consumer;

/**
 * @author：hcqi .
 * des: 对象 map
 * email:hechuanqi.top@gmail.com
 * date: 2018/6/20
 */
public class ObjectHashMap extends AbstractHashMap<String, Object> {

    public ObjectHashMap() {
    }

    public ObjectHashMap(String k, Object v) {
        put(k, v);
    }

    public ObjectHashMap(ObjectHashMap target) {
        super();
        target.forEach(this::put);
    }
}
