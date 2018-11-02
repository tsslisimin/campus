package cn.csjava.campus.student.controller.vo;

import cn.csjava.campus.common.base.AbstractEntity;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/11 0011
 */
@Data
public class StudentApplyRequest extends AbstractEntity {
    /**
     * 自增长id
     */
    private Long saId;
    /**
     * 报名编号
     */
    private String numberId;
    /**
     * 学生姓名名称
     */
    @NotEmpty(message = "学生姓名不能为空")
    private String studentName;
    /**
     * 身份证
     */
    @NotEmpty(message = "身份证号码不能为空")
    private String cardNo;
    /**
     * 性别
     */
    private String sex;
    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 曾用名
     */
    private String usernameOld;
    /**
     * 毕业学校
     */
    private String school;
    /**
     * 毕业班级
     */
    private String classes;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 民族
     */
    private String nation;
    /**
     * 籍贯
     */
    private String studentNative;
    /**
     * 户籍地址
     */
    private String registerAddress;
    /**
     * 户籍详细地址
     */
    private String registeredDetail;
    /**
     * 家庭住址
     */
    private String familyAddress;
    /**
     * 家庭住址详细地址
     */
    private String familyDetail;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 父亲名称
     */
    private String fatherName;
    /**
     * 父亲电话
     */
    @NotEmpty(message = "第一监护人的电话号码不能为空")
    private String fatherPhone;
    /**
     * 父亲单位
     */
    private String fatherWork;

    /**
     * 母亲名称
     */
    private String motherName;
    /**
     * 母亲电话
     */
    @NotEmpty(message = "第二监护人的电话号码不能为空")
    private String motherPhone;
    /**
     * 母亲单位
     */
    private String motherWork;
    /**
     * 用户ID ，创建人的ID
     */
    private String userId;
}
