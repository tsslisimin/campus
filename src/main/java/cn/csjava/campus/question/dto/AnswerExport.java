package cn.csjava.campus.question.dto;

import lombok.Data;

/**
 * @author hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/22
 */
@Data
public class AnswerExport {
    private String studentName;
    private String cardNo;
    private String sex;
    private String familyAddress;
    /**
     * 母亲名称
     */
    private String motherName;
    /**
     * 名称电话
     */
    private String motherPhone;
    private Integer status;
    /**
     * 父亲名称
     */
    private String fatherName;
    /**
     * 父亲电话
     */
    private String fatherPhone;
    private String content;
    private String describes;

    /**
     * 问题id
     */
    private long itemId;
    /**
     * 学生id
     */
    private long studentId;
}
