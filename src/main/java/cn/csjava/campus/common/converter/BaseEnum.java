package cn.csjava.campus.common.converter;

/**
 * @author：hcqi .
 * des:父类枚举 所有枚举必须继承实现该接口 否则不会进行转换
 * email:hechuanqi.top@gmail.com
 * date: 2018/6/20
 */
public interface BaseEnum {
    /**
     * 获取值
     *
     * @return 数值
     */
    Integer getValue();
}
