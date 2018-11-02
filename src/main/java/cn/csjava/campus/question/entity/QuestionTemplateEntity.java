package cn.csjava.campus.question.entity;

import cn.csjava.campus.common.base.AbstractEntity;
import cn.csjava.campus.common.util.BeanUtils;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author hcqi .
 * describe:问卷模版 文档
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/4
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "question_template")
public class QuestionTemplateEntity extends AbstractEntity {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 描述
     */
    private String describes;
    /**
     * 是否需要签名
     */
    private int signature;


    public QuestionTemplateEntity(Object o) {
        BeanUtils.copyProperties(o, this);
    }
}
