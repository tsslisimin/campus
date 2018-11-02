package cn.csjava.campus.common.options.list;

import java.util.AbstractList;
import java.util.List;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/25
 */
abstract class AbstractListOption<T> {
    protected List<T> target;


    public AbstractListOption append(T t) {
        target.add(t);
        return this;
    }


    public List<T> build() {
        return target;
    }
}
