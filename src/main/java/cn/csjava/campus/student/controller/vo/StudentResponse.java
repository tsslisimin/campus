package cn.csjava.campus.student.controller.vo;

import cn.csjava.campus.common.base.AbstractEntity;
import cn.csjava.campus.common.util.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/19 0019
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class StudentResponse extends AbstractEntity {
    private long id;
    /**
     * 档案号
     */
    private String numberId;
    /**
     * 学生编号
     */
    private String studentNumber;
    /**
     * XXDM
     */
    private String xxdm;
    /**
     * 毕业学校
     */
    private String school;
    /**
     * 姓名
     */
    private String username;
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
     * 英文名
     */
    private String englishName;
    /**
     * 身份证号
     */
    private String cardNo;
    /**
     * 籍贯
     */
    private String studentNative;
    /**
     * 民族
     */
    private String nation;
    /**
     * 政治面貌
     */
    private String politicsStudent;
    /**
     * 家庭地址
     */
    private String familyAddress;
    /**
     * 户口
     */
    private String registerAddress;
    /**
     * 特长
     */
    private String speciality;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 邮编
     */
    private String postcode;
    /**
     * 父亲
     */
    private String fatherName;
    /**
     * 母亲
     */
    private String motherName;
    /**
     * 父亲单位
     */
    private String fatherWork;
    /**
     * 母亲单位
     */
    private String motherWork;
    /**
     * 父亲电话
     */
    private String fatherPhone;
    /**
     * 母亲电话
     */
    private String motherPhone;
    /**
     * 公办志愿
     */
    private String publicVolunteer;
    /**
     * 备注
     */
    private String remark;
    /**
     * LQXXDM
     */
    private String lqxxdm;
    /**
     * LQXXMC
     */
    private String lqxxmc;
    /**
     * 录取方式
     */
    private String admissions;
    /**
     * 全国学籍号
     */
    private String studentCode;


    public StudentResponse(Object o) {
        BeanUtils.copyProperties(o, this);
    }
}
