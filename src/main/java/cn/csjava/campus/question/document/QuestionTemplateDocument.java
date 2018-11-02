package cn.csjava.campus.question.document;

import cn.csjava.campus.common.base.AbstractDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * @author hcqi .
 * describe:问卷模版 文档
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/4
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionTemplateDocument extends AbstractDocument {
    @Id
    private String id;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 问卷列表
     */
    private List<QuestionItem> questionItems;

}
