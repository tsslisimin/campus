package cn.csjava.campus.question.document;

import cn.csjava.campus.common.base.AbstractDocument;
import cn.csjava.campus.common.base.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

/**
 * @author：hcqi .
 * describe:问卷类型
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/4
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionTypeDocument extends AbstractDocument {
    @Id
    private String id;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 描述
     */
    private String describe;
    
}
