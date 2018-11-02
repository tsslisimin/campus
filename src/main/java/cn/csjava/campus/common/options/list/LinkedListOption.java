package cn.csjava.campus.common.options.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/25
 */
public class LinkedListOption extends AbstractListOption<String> {

    public LinkedListOption() {
        target = new LinkedList<>();
    }

    public LinkedListOption(int size, String... objs) {
        this();
        Collections.addAll(target, objs);
        for (int i = 0; i < size; i++) {
            target.add(null);
        }
    }
}
