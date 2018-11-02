package cn.csjava.campus.user.controller.vo;

import cn.csjava.campus.common.base.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/14 0014
 */
@Data
public class UserStudentResponse extends AbstractEntity {
    /**
     * 报名编号
     */
    private Long saId;
    /**
     * 学生姓名名称
     */
    private String studentName;
    /**
     * 毕业学校
     */
    private String school;
    /**
     * 班级
     */
    private String classes;
    /**
     * 身份证
     */
    private String cardNo;
    /**
     * 家庭住址详细地址
     */
    private String familyDetail;
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
     * 提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 问卷模板Id
     */
    private Long templateId;
    /**
     * 状态
     */
    private int status;
    /**
     * 户籍详细地址
     */
    private String registeredDetail;
    /**
     * 格式化后时间  2017-2018
     */
    private String formatYear;
    /**
     * 场次名称
     */
    private String venueName;
    /**
     * 民族
     */
    private String nation;
    /**
     * 添加人ID
     */
    private Long userId;

    private String image;
    /**
     * 学生编号
     */
    private String studentNumber;
    /**
     * 姓名
     */
    private String username;
    /**
     * 模版名称
     */
    private String templateName;


}
