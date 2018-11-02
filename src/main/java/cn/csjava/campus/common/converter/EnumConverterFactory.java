package cn.csjava.campus.common.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author：hcqi .
 * des:枚举转换工厂
 * email:hechuanqi.top@gmail.com
 * date: 2018/6/20
 */
public class EnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    private final Map<Class, Converter> converterMap = new WeakHashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return converterMap.computeIfAbsent(targetType, IntegerEnumConverter::new);
    }

    class IntegerEnumConverter<T extends BaseEnum> implements Converter<String, T> {
        private Map<String, T> enumMap = new HashMap<>();

        IntegerEnumConverter(Class<T> enumType) {
            T[] enums = enumType.getEnumConstants();
            for (T e : enums) {
                enumMap.put(e.getValue().toString(), e);
            }
        }

        @Override
        public T convert(String source) {
            return enumMap.get(source);
        }

    }
}
