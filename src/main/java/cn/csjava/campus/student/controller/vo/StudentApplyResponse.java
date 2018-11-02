package cn.csjava.campus.student.controller.vo;

import cn.csjava.campus.common.base.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.util.Date;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/14 0014
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class StudentApplyResponse extends AbstractEntity {
    /**
     * 学生姓名名称
     */
    private String studentName;
    /**
     * 身份证
     */
    private String cardNo;
    /**
     * 性别
     */
    private String sex;
    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 曾用名
     */
    private String usernameOld;
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
     * 毕业学校
     */
    private String school;
    /**
     * 班级
     */
    private String classes;
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
     * 提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date commitDate;
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
    private String motherPhone;
    /**
     * 母亲单位
     */
    private String motherWork;
    /**
     * 用户ID ，创建人的ID
     */
    private Long userId;
}
