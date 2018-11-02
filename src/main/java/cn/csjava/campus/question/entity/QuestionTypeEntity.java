package cn.csjava.campus.question.entity;

import cn.csjava.campus.common.base.AbstractEntity;
import lombok.*;

import javax.persistence.Table;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/7
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question_type")
public class QuestionTypeEntity extends AbstractEntity {

    private Long id;
    private String describes;
    private Integer type;
    private String remark;
}
