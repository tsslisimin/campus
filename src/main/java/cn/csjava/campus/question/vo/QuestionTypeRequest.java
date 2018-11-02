package cn.csjava.campus.question.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
public class QuestionTypeRequest {
    @Data
    public static class Add {
        private String describes;
        @NotNull(message = "缺少类型参数")
        private Integer type;
        private String remark;
    }
}
