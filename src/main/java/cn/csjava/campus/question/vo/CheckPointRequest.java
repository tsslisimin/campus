package cn.csjava.campus.question.vo;

import cn.csjava.campus.common.util.BeanUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/12
 */
public class CheckPointRequest {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Add {
        @NotEmpty(message = "正则为空")
        private String regular;
        @NotEmpty(message = "描述为空")
        private String describes;

        public Add(Object o) {
            BeanUtils.copyProperties(o, this);
        }
    }
}
