package cn.csjava.campus.common.results;

import lombok.Data;

import java.util.Objects;

/**
 * @author：hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/2
 */
@Data
public class CampusResults<T> {
    private Integer code;
    private String msg;
    private T data;


    public boolean ok() {
        return Objects.equals(code, 0);
    }
}
