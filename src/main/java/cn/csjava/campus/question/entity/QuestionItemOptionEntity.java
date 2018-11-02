package cn.csjava.campus.question.entity;

import cn.csjava.campus.common.base.AbstractEntity;
import cn.csjava.campus.common.util.BeanUtils;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author：hcqi .
 * describe: 问卷条目
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/5
 */
@Table(name = "question_item_option")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionItemOptionEntity extends AbstractEntity {
    /**
     * 问卷选项id
     */
    @Id
    private Long id;
    /**
     * 模版id
     */
    private Long templateId;
    /**
     * 问卷item id
     */
    private Long questionItemId;
    /**
     * 问卷选项索引
     */
    private Integer questionIndex;

    /**
     * 选项内容
     */


    private String content;

    /**
     * 孩子id
     */
    private Long saId;

    public QuestionItemOptionEntity(Object o) {
        BeanUtils.copyProperties(o, this);
    }
}
