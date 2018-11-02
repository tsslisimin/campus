package cn.csjava.campus.question.document;

import lombok.Data;

import java.util.Map;

/**
 * @author：hcqi .
 * describe: 问卷条目
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/5
 */
@Data
public class QuestionItem {
    /**
     * 问卷id
     */
    private String questionId;
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
     * 问卷答案  key 为答案id 可以根据key 进行优先级排序 value 为答案
     */
    private Map<String, QuestionAnswerItem> answer;

}
