package cn.csjava.campus.question.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "question_answer")
public class QuestionAnswerEntity {
    @Id
    private Long id;
    private Long templateId;
    private Long questionId;
    private Long questionOptionId;
    private Long saId;
    private String userId;
    private Date commitTime;
    private String content;


}
