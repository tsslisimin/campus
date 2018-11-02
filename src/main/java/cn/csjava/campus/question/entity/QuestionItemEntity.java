package cn.csjava.campus.question.entity;

import cn.csjava.campus.common.base.AbstractEntity;
import cn.csjava.campus.common.util.BeanUtils;
import cn.csjava.campus.question.document.QuestionAnswerItem;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Map;

/**
 * @author：hcqi .
 * describe: 问卷条目
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/5
 */
@Table(name = "question_item")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionItemEntity extends AbstractEntity {
    /**
     * 问卷id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String objectId;
    /**
     * 模版id
     */
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
    private Date createDate;

    /**
     * 编辑类型 是否必填
     */
    private Integer editorType;
    /**
     * 文本输入  用于选择有的时候进行编写
     */
    private int inputText;
    /**
     * 是否是健康问卷
     */
    private int health;

    public QuestionItemEntity(Object o) {
        BeanUtils.copyProperties(o, this);
    }
}
