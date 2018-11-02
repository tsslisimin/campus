package cn.csjava.campus.question.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
public class QuestionItemOptionRequest {
    @Data
    public static class Find {


    }

    @Data
    public static class Add {
        /**
         * 模版id
         */
        @NotNull(message = "模版id缺失")
        private Long templateId;
        /**
         * 问卷item id
         */
        @NotNull(message = "问卷id缺失")

        private Long questionItemId;

        /**
         * 问卷类型 单选 多选
         *
         * @see cn.csjava.campus.question.enums.QuestionTypeEnum
         */
        @NotNull(message = "问卷类型缺失")
        private Integer questionType;
        /**
         * 问卷选项索引
         */

        @NotNull(message = "索引缺失")
        private Integer questionIndex;

        /**
         * 选项内容
         */
        @NotEmpty(message = "内容缺失")
        private String content;
    }
}
