package cn.csjava.campus.student.entity;

import cn.csjava.campus.common.base.AbstractEntity;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 新生入学报名
 *
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/10 0010
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "student_apply")
@Builder
public class StudentApplyEntity extends AbstractEntity {
    /**
     * 自增长id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long saId;
    /**
     * 报名编号
     */
    private String numberId;
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
     * 毕业班级
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
    private Date commitDate;
    /**
     * 状态  0未答问卷 1待审核 2审核通过 3驳回
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
     * 名称电话
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
    /**
     * 问卷模板Id
     */
    private Long templateId;
    /**
     * 签名的图片
     */
    private String image;
}
