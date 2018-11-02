package cn.csjava.campus.question.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
public class QuestionItemRequest {

    @Data
    public static class Find {
        /**
         * 问卷id
         */
        private long id;
        /**
         * 模版id
         */
        private long templateId;
        /**
         * 依赖的下层问题id
         */
        private Long dependId;

        /**
         * 问卷类型 单选 多选
         *
         * @see cn.csjava.campus.question.enums.QuestionTypeEnum
         */
        private Integer questionType;
        /**
         * 必填 选填
         */
        private Integer status;
        /**
         * 问卷索引
         */
        private Integer index;
        /**
         * 描述
         */
        private String describes;
        /**
         * 创建时间
         */
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date createDate;

        /**
         * 编辑类型 是否必填
         */
        private Integer editorType;

    }

    @Data
    public static class Add {
        /**
         * 模版id
         */
        @NotNull(message = "templateId 缺失")
        private Long templateId;
        /**
         * 依赖的下层问题id
         */
        private long dependId = -1;
        /**
         * 依赖对下层问题答案id
         */
        private long dependOptionId = -1;

        /**
         * 问卷类型 单选 多选
         *
         * @see cn.csjava.campus.question.enums.QuestionTypeEnum
         */
        @NotNull(message = "questionType 缺失")

        private Integer questionType;
        /**
         * 编辑类型 是否必填
         */
        private Integer editorType;

        /**
         * 问卷索引
         */
        @NotNull(message = "index 缺失")

        private Integer index;
        /**
         * 描述
         */
        private String describes;
    }
}
