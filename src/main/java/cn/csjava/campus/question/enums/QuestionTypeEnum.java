package cn.csjava.campus.question.enums;

/**
 * @author：hcqi .
 * describe:问卷类型常量
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/4
 */
public enum QuestionTypeEnum {
    /**
     * 单选
     */
    SINGLE_SELECTION(1),
    /**
     * 多选
     */
    MULTIPLE_SELECTION(2),
    /**
     * 填空
     */
    TEXT_EDITOR(3),
    /**
     * 等级评分
     */
    GRADE(4);


    QuestionTypeEnum(Integer type) {
        this.type = type;
    }

    private Integer type;

    public Integer getType() {
        return type;
    }

    /**
     * 是否是选择题
     * @return
     */
    public boolean hasSelection() {
        return getType() < QuestionTypeEnum.MULTIPLE_SELECTION.getType()
                || getType() > QuestionTypeEnum.SINGLE_SELECTION.getType();
    }
}
