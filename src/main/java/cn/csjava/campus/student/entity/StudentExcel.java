package cn.csjava.campus.student.entity;

import cn.csjava.campus.common.util.BeanUtils;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.*;

import java.util.Date;

/**
 * @author hcqi .
 * describe: 学生excel 信息
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/5
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class StudentExcel extends BaseRowModel {
    /**
     * 档案号
     */
    @ExcelProperty(index = 0)
    private String numberId;
    /**
     * 学生编号
     */
    @ExcelProperty(index = 1)
    private String studentNumber;
    /**
     * XXDM
     */
    @ExcelProperty(index = 2)
    private String xxdm;
    /**
     * 毕业学校
     */
    @ExcelProperty(index = 3)
    private String school;
    /**
     * 姓名
     */
    @ExcelProperty(index = 4)
    private String username;
    /**
     * 性别
     */
    @ExcelProperty(index = 5)
    private String sex;
    /**
     * 出生日期
     */
    @ExcelProperty(index = 6, format = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 曾用名
     */
    @ExcelProperty(index = 7)
    private String usernameOld;
    /**
     * 英文名
     */
    @ExcelProperty(index = 8)
    private String englishName;
    /**
     * 身份证号
     */
    @ExcelProperty(index = 9)
    private String cardNo;
    /**
     * 籍贯
     */
    @ExcelProperty(index = 10)
    private String studentNative;
    /**
     * 民族
     */
    @ExcelProperty(index = 11)
    private String nation;
    /**
     * 政治面貌
     */
    @ExcelProperty(index = 12)
    private String politicsStudent;
    /**
     * 家庭地址
     */
    @ExcelProperty(index = 13)
    private String familyAddress;
    /**
     * 户口
     */
    @ExcelProperty(index = 14)
    private String registerAddress;
    /**
     * 特长
     */
    @ExcelProperty(index = 15)
    private String speciality;
    /**
     * 联系电话
     */
    @ExcelProperty(index = 16)
    private String phone;
    /**
     * 邮编
     */
    @ExcelProperty(index = 17)
    private String postcode;
    /**
     * 父亲
     */
    @ExcelProperty(index = 18)
    private String fatherName;
    /**
     * 母亲
     */
    @ExcelProperty(index = 19)
    private String motherName;
    /**
     * 父亲单位
     */
    @ExcelProperty(index = 20)
    private String fatherWork;
    /**
     * 母亲单位
     */
    @ExcelProperty(index = 21)
    private String motherWork;
    /**
     * 父亲电话
     */
    @ExcelProperty(index = 22)
    private String fatherPhone;
    /**
     * 母亲电话
     */
    @ExcelProperty(index = 23)
    private String motherPhone;
    /**
     * 公办志愿
     */
    @ExcelProperty(index = 24)
    private String publicVolunteer;
    /**
     * 备注
     */
    @ExcelProperty(index = 25)
    private String remark;
    /**
     * LQXXDM
     */
    @ExcelProperty(index = 26)
    private String lqxxdm;
    /**
     * LQXXMC
     */
    @ExcelProperty(index = 27)
    private String lqxxmc;
    /**
     * 录取方式
     */
    @ExcelProperty(index = 28)
    private String admissions;
    /**
     * 全国学籍号
     */
    @ExcelProperty(index = 29)
    private String studentCode;


    public StudentExcel(Object o) {
        BeanUtils.copyProperties(o, this);
    }
}
