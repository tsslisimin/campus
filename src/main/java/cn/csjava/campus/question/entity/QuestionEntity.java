package cn.csjava.campus.question.entity;

import cn.csjava.campus.common.base.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionEntity extends AbstractEntity {

    private Long templateId;
    private String templateDes;
    private Date startDate;
    private Date endDate;
    private Integer status;
    private Long itemId;
    private String itemDes;
    private int editorType;
    private int itemIndex;
    private int questionType;
    private int optionIndex;
    private int inputText;
    private long optionId;
    private String optionContent;
    private String answer;
    private String templateName;
    private int signature;
    private int health;

}
