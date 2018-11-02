package cn.csjava.campus.school.entity;

import cn.csjava.campus.common.base.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author hcqi .
 * describe: 学年场次信息
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "school_year_vuene")
public class SchoolYearVenueEntity extends AbstractEntity {
    /**
     * 自增长id
     */
    @Id
    private Long syvId;
    /**
     * 场次名称
     */
    private String venueName;
    /**
     * 学年id
     */
    private Long syId;
    /**
     * 学年名称
     */
    private String yearName;
    /**
     * 报名开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    /**
     * 报名结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
