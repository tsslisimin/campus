package cn.csjava.campus.school.controller.vo;

import cn.csjava.campus.common.base.AbstractEntity;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author：yyl .
 * describe:
 * email:zxc7752948@qq.com
 * date: 2018/7/7 0007
 */
@Data
public class SchoolYearVenueRequest extends AbstractEntity {
    /**
     * 自增长id
     */
    private Long syvId;
    /**
     * 学年名称
     */
    private String yearName;
    /**
     * 场次名称
     */
    @NotEmpty(message = "场次名称不能为空")
    private String venueName;
    /**
     * 学年id
     */
    @NotEmpty(message = "学年ID不能为空")
    private Long syId;
    /**
     * 报名开始时间
     */
    @NotEmpty(message = "报名开始时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    /**
     * 报名结束时间
     */
    @NotEmpty(message = "报名结束时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

}
