package cn.csjava.campus.school.controller.vo;

import cn.csjava.campus.common.base.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/9 0009
 */
@Data
public class SchoolYearVenueResponse extends AbstractEntity{

    /**
     * 自增长id
     */
    private Long syvId;
    /**
     * 场次名称
     */
    private String venueName;
    /**
     * 学年名称
     */
    private String yearName;
    /**
     * 学年id
     */
    private Long syId;
    /**
     * 报名开始时间
     */
    private Date startDate;
    /**
     * 报名结束时间
     */
    private Date endDate;
    /**
     * 状态  开始招生
     */
    private Integer status;
    /**
     * 问卷模版的url
     */
    private String url;
    /**
     * 问卷模版的二维码url
     */
    private String imageUrl;
    /**
     * 简介内容
     */
    private String content;
    /**
     * 备注
     */
    private String remark;
    /**
     * 开始时间 + 截止时间
     */
    private String  startEndDate;
    /**
     * 学年格式化以后的
     */
    private String formatYear;
}

